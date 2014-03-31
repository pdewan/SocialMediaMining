package usercase.markquestion;


import java.io.File;

import rule.BinaryFeatureRule;
import rule.NominalFeatureRule;
import rule.basicfeature.ContainsFollowMessageRule;
import rule.basicfeature.FollowMessageNumRule;
import rule.basicfeature.IBasicFeatureRule;
import rule.basicfeature.TextBinaryFeatureRule;
import rule.basicfeature.copyraw.DateRawFeatureRule;
import rule.basicfeature.copyraw.NumericRawFeatureRule;
import rule.basicfeature.copyraw.RawFeatureRule;
import rule.basicfeature.copyraw.StringRawFeatureRule;
import rule.superfeature.ISuperFeatureRule;
import rule.superfeature.copy.NominalCopySuperFeatureRule;
import rule.superfeature.model.IModelRule;
import rule.superfeature.model.mallet.MalletParallelLDAModelRule;
import rule.superfeature.model.weka.IWekaModelRule;
import rule.superfeature.model.weka.WekaDecissionTreeModelRule;
import rule.superfeature.model.weka.WekaClassifyModelRule;
import dataconvert.BasicFeatureExtractor;
import dataconvert.SuperFeatureExtractor;
import dataconvert.IntermediateDataSet;
import dataconvert.WekaDataSet;
import dataconvert.WekaDataSetInitializer;
import dataimport.MsgDataConfig;
import dataimport.ThreadData;
import dataimport.ThreadDataSet;
import dataimport.email.EmailDataConfig;
import dataimport.email.EmailThreadParser;
import dataimport.json.JsonDataConfig;
import dataimport.json.JsonThreadParser;
import dataimport.json.yahooanswers.YahooAnswersAnswerConfig;
import dataimport.json.yahooanswers.YahooAnswersQuestionConfig;


public class Tester {

	public static void main(String[] args) throws Exception {
		
		JsonDataConfig qconfig = new YahooAnswersQuestionConfig();
		JsonDataConfig aconfig = new YahooAnswersAnswerConfig();
		JsonThreadParser parser = new JsonThreadParser(qconfig, aconfig, "Date");
		
		File dirfile = new File("/Users/jinjingma/Documents/workspace/DataCollection/data/politics/answers");
		ThreadDataSet threads = parser.parseDirectory(dirfile);
		//System.out.println(threads.toString());
		
		
		IBasicFeatureRule[] basicRules = new IBasicFeatureRule[3];
		basicRules[0] = new StringRawFeatureRule("QuestionContent", YahooAnswersQuestionConfig.CONTENT);
		
		String[] pronouns = {"I", "you", "my"};
		basicRules[1] = new TextBinaryFeatureRule("hasPronoun", YahooAnswersQuestionConfig.SUBJECT, pronouns);
		basicRules[2] = new ContainsFollowMessageRule("hasAnswer");
		
		
		WekaDataSetInitializer initializer = new WekaDataSetInitializer();
		BasicFeatureExtractor extractor = new BasicFeatureExtractor(initializer);	
		IntermediateDataSet simpleDestDataSet = extractor.extract(threads, "simple1", basicRules);
		
		IntermediateDataSet[] trainTest = simpleDestDataSet.splitToTrainAndTest(0.8);
		IntermediateDataSet train = trainTest[0];
		IntermediateDataSet test = trainTest[1];
		
		
		SuperFeatureExtractor extractor2 = new SuperFeatureExtractor(initializer);
		
		
		ISuperFeatureRule[] superRules1 = new ISuperFeatureRule[3];
		String[] attrNames = {"QuestionContent"};
		superRules1[0] = new MalletParallelLDAModelRule("ContentTopic", attrNames, 5);
		((IModelRule)superRules1[0]).train(train, null);
		((IModelRule)superRules1[0]).save("malletLDA.txt");
		
		
		superRules1[1] = new NominalCopySuperFeatureRule("hasPronoun", "hasPronoun", 
				((BinaryFeatureRule)basicRules[1]).getDomain());
		superRules1[2] = new NominalCopySuperFeatureRule("hasAnswer", "hasAnswer",
				((BinaryFeatureRule)basicRules[1]).getDomain());
		
		train =  extractor2.extract(train, "train1", superRules1);
		test = extractor2.extract(test, "test1", superRules1);
		
		test.save("groundTruth.arff");

		ISuperFeatureRule[] superRules2 = new ISuperFeatureRule[1];
		superRules2[0] = new WekaDecissionTreeModelRule("hasAnswerJ48", 
				((NominalFeatureRule)superRules1[2]).getDomain());
		
		((WekaClassifyModelRule)superRules2[0]).setTargetIndex((WekaDataSet)train, train.numAttributes()-1);
		((WekaClassifyModelRule)superRules2[0]).setTargetIndex((WekaDataSet)test, test.numAttributes()-1);
		((IModelRule)superRules2[0]).train(train, null);
		((WekaClassifyModelRule)superRules2[0]).evaluate((WekaDataSet)train, (WekaDataSet)test);
		/*
		train =  extractor2.extract(train, "train2", superRules2);
		IntermediateDataSet test2 = extractor2.extract(test, "test2", superRules2);
		
		test2.save("testSetResult.arff");
				
		((WekaClassifyModelRule)superRules2[0]).evaluate((WekaDataSet)train, (WekaDataSet)test);
		*/
		
		
		/*
		EmailThreadParser emailParser = new EmailThreadParser();
		ThreadDataSet threads = emailParser.parse("subjects.txt", "attachments.txt", "messages.txt");
		//System.out.println(threads.toString());			
		
		
		
		IBasicFeatureRule[] rules1 = new IBasicFeatureRule[1];
		//rules1[0] = new DateRawFeatureRule("startDate", "Date", MsgDataConfig.DATEFORMAT);
		rules1[0] = new NumericRawFeatureRule("attatchNum", EmailDataConfig.ATTACHMENT_NUM, RawFeatureRule.ACCENDING, 0);
		
		IBasicFeatureRule[] rules2 = new IBasicFeatureRule[2];
		String[] hasThese = {"Fwd"};		
		rules2[1] = new TextBinaryFeatureRule("isFwd", EmailDataConfig.SUBJECT, hasThese);
		rules2[0] = new FollowMessageNumRule("responseNum");
		
		WekaDataSetInitializer initializer = new WekaDataSetInitializer();
		BasicFeatureExtractor extractor = new BasicFeatureExtractor(initializer);
		
		IntermediateDataSet destDataSet1 = extractor.extract(threads, "test", rules1);
		IntermediateDataSet destDataSet2 = extractor.extract(threads, "test2", rules2);
		IntermediateDataSet destDataSet = destDataSet1.mergeByAttributes(destDataSet2);
		
	
		
		ISuperFeatureRule[] sr = new ISuperFeatureRule[1];
		
		
		
		IModelRule kmeans = new WekaKmeansModelRule("cluster", 3);
		kmeans.train(destDataSet, null);
		sr[0] = kmeans;
		
		SuperFeatureExtractor extractor2 = new SuperFeatureExtractor(initializer);
		IntermediateDataSet finDataSet = extractor2.extract(destDataSet, "testCluster", sr);
		
		//destDataSet1.save("testIntermediate.arff");
		
		System.out.println(finDataSet.toString());
		
		// TODO Auto-generated method stub
		/*
		String[] attributes = {"NumAnswers", "Answers"};
		YahooAnswersQuestionConfig config = new YahooAnswersQuestionConfig();
		YahooAnswersJsonToArffDataConverter converter = 
				new YahooAnswersJsonToArffDataConverter(config);
		converter.jsonToArff(
				"/Users/jinjingma/Documents/workspace/DataCollection/data/politics/content", 
				"test", attributes, "test");
		/*
		BufferedReader reader = new BufferedReader(new FileReader(
				"test.arff"));
		String line;
		while((line=reader.readLine())!=null){
			System.out.println(line);
		}
		*/
		/*
		DataSource source = new DataSource("test.arff");
		Instances data = source.getDataSet();
		/*
		NominalSuperFeatureRule rule = new SubjectHasQuestionRule();
		NominalSuperFeatureExtractor extractor = new NominalSuperFeatureExtractor(rule);
		extractor.extract(data, "question? {y, n}");
		extractor.saveResult(data, "marked.arff");
		
		data = source.getDataSet();
		NumericSuperFeatureRule rule2 = new ContentWordNumRule();
		NumericSuperFeatureExtractor extractor2 = new NumericSuperFeatureExtractor(rule2);
		extractor2.extract(data, "wordnum");
		extractor2.saveResult(data, "counted.arff");
		*/
		//---------------------------
		/*
		DataSource source1 = new DataSource("marked.arff");
		DataSource source2 = new DataSource("counted.arff");
		DataSource source3 = new DataSource("test.arff");
		
		Instances testMerge1 = source1.getDataSet();		
		Instances testMerge2 = source2.getDataSet();
		Instances testMerge3 = source3.getDataSet();
		
		Instances tmp = Instances.mergeInstances(testMerge1, testMerge2);
		Instances testPrediction = Instances.mergeInstances(tmp, testMerge3);
		System.out.println(testPrediction.toString());
		
		testPrediction.setClassIndex(testPrediction.numAttributes()-1);
		*/
		
		/*
		DataSource source3 = new DataSource("weather.numeric.arff");
		Instances testPrediction = source3.getDataSet();
		testPrediction.setClassIndex(testPrediction.numAttributes()-1);
		
		//test cross-validation
		int numFolds = 2;
		Random random = new Random(System.currentTimeMillis());
		for(int i=0; i< numFolds; i++){
			testPrediction.randomize(random);
			Instances curCvTrainData = testPrediction.trainCV(numFolds, i);
			Instances curCvTestData = testPrediction.testCV(numFolds, i);
			//...
		}
		
		int trainSize = (int) Math.round(testPrediction.numInstances() * 80
                / 100);
        int testSize = testPrediction.numInstances() - trainSize;
        Instances train = new Instances(testPrediction, 0, trainSize);
        Instances test = new Instances(testPrediction, trainSize, testSize);
			
        LinearRegression model = new LinearRegression();
		model.buildClassifier(train);
		System.out.println(model);
			
		Evaluation eval = new Evaluation(train);
		 eval.evaluateModel(model, test);
		 System.out.println(eval.toSummaryString("\nResults\n======\n", false));
		
		*/
		
		//double c[] = model.coefficients();
		
	}

}
