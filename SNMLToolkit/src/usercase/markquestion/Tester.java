package usercase.markquestion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;

import dataimport.email.EmailThreadParser;
import dataimport.json.yahooanswers.YahooAnswersQuestionConfig;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.LinearRegression;
import weka.gui.explorer.ClassifierPanel;

public class Tester {

	public static void main(String[] args) throws Exception {
		
		EmailThreadParser emailParser = new EmailThreadParser();
		emailParser.parse("subjects.txt", "attachments.txt", "messages.txt");
		
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
