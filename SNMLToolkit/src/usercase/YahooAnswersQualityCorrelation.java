package usercase;

import java.io.File;

import rule.basicfeature.IBasicFeatureRule;
import rule.basicfeature.ReadFromFileNominalRule;
import rule.basicfeature.ReadFromFileNumericRule;
import rule.basicfeature.ReadFromFileNumericVectorRule;
import rule.basicfeature.ResponseAvgCharLength;
import rule.basicfeature.ResponseNumRule;
import rule.basicfeature.copyraw.NominalRawFeatureRule;
import rule.superfeature.ISuperFeatureRule;
import rule.superfeature.WeightedSumRule;
import rule.superfeature.model.weka.IWekaModelRule;
import rule.superfeature.model.weka.WekaLinearRegressionModelRule;
import dataconvert.BasicFeatureExtractor;
import dataconvert.IntermediateDataSet;
import dataconvert.SuperFeatureExtractor;
import dataconvert.WekaDataSet;
import dataconvert.WekaDataSetInitializer;
import dataimport.ThreadDataSet;
import dataimport.json.JsonDataConfig;
import dataimport.json.JsonThreadParser;
import dataimport.json.yahooanswers.YahooAnswersAnswerConfig;
import dataimport.json.yahooanswers.YahooAnswersDataConfig;
import dataimport.json.yahooanswers.YahooAnswersQuestionConfig;

public class YahooAnswersQualityCorrelation {

	public static void main(String[] args) throws Exception {
		JsonDataConfig qconfig = new YahooAnswersQuestionConfig();
		JsonDataConfig aconfig = new YahooAnswersAnswerConfig();
		JsonThreadParser parser = new JsonThreadParser(qconfig, aconfig, YahooAnswersDataConfig.DATE_DEFAULT);
		
		File dirfile = new File("data/YahooAnswers/rawdata");
		ThreadDataSet threads = parser.parseDirectory(dirfile);
		
		
		IBasicFeatureRule[] basicRulesQ = new IBasicFeatureRule[4];
		basicRulesQ[0] = new ReadFromFileNumericVectorRule("questionType", "data/YahooAnswers/type.txt");
		basicRulesQ[1] = new ReadFromFileNumericVectorRule("gratitude", "data/YahooAnswers/gratitude.txt");
		basicRulesQ[2] = new ReadFromFileNumericVectorRule("priorEffort", "data/YahooAnswers/priorEffort.txt");
		basicRulesQ[3] = new ReadFromFileNumericVectorRule("topic", "data/YahooAnswers/topic.txt");
		
		WekaDataSetInitializer initializer = new WekaDataSetInitializer();
		BasicFeatureExtractor basicExtractor = new BasicFeatureExtractor(initializer);	
		IntermediateDataSet featureSet = basicExtractor.extract(threads, "questionFeature", basicRulesQ);
		
		IBasicFeatureRule[] basicRulesA = new IBasicFeatureRule[4];
		basicRulesA[0] = new ResponseNumRule("answerNum");
		basicRulesA[1] = new ResponseAvgCharLength("answerAvgL", YahooAnswersDataConfig.CONTENT);
		basicRulesA[2] = new ReadFromFileNumericRule("ratedAnswerQuality", "data/YahooAnswers/ratedAnswerQuality.txt");
		basicRulesA[3] = new ReadFromFileNumericRule("ratedAnswerEffort", "data/YahooAnswers/ratedAnswerEffort.txt");
		
		IntermediateDataSet measurementSet = basicExtractor.extract(threads, "answerMeasure", basicRulesA);
		
		ISuperFeatureRule[] superRulesAnswer = new ISuperFeatureRule[1];
		double[] weight = {0.2, 0.2, 0.3, 0.3};
		superRulesAnswer[0] = new WeightedSumRule("answerQuality", weight);
		
		SuperFeatureExtractor superExtractor = new SuperFeatureExtractor(initializer);	
		IntermediateDataSet combinedMeasurement = superExtractor.extract(measurementSet, "answerMeasure", superRulesAnswer);
		
		IntermediateDataSet dataset = featureSet.mergeByAttributes(combinedMeasurement);
		dataset.setTargetIndex();
		
		ISuperFeatureRule[] finalRule = new ISuperFeatureRule[1];
		finalRule[0] = new WekaLinearRegressionModelRule(null);
		((IWekaModelRule)finalRule[0]).train(dataset, null);
		((IWekaModelRule)finalRule[0]).evaluate((WekaDataSet)dataset, (WekaDataSet)dataset);
		
	}

}
