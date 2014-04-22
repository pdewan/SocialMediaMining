package usercase;

import java.util.ArrayList;

import dataconvert.IntermediateDataSet;
import dataimport.email.EmailThreadParser;
import rule.basicfeature.ContainsFollowMessageRule;
import rule.basicfeature.EmailRecipientIdsRule;
import rule.basicfeature.EmailRecipientNumRule;
import rule.basicfeature.EmailSenderIdRule;
import rule.basicfeature.EmailSubjectLengthRule;
import rule.basicfeature.FirstResponseTimeRule;
import rule.basicfeature.IBasicFeatureRule;
import rule.basicfeature.ReadFromFileNominalRule;
import rule.basicfeature.ResponseAvgCharLength;
import rule.basicfeature.ResponseNumRule;
import rule.basicfeature.copyraw.NominalRawFeatureRule;
import rule.filterrule.HasResponseFilterRule;
import rule.superfeature.ISuperFeatureRule;
import rule.superfeature.model.weka.IWekaModelRule;
import rule.superfeature.model.weka.WekaLinearRegressionModelRule;
import rule.superfeature.model.weka.WekaLogisticRegressionModelRule;
import dataconvert.BasicFeatureExtractor;
import dataconvert.SuperFeatureExtractor;
import dataconvert.ThreadDataFilter;
import dataconvert.WekaDataSet;
import dataconvert.WekaDataSetInitializer;
import dataimport.ThreadDataSet;

public class EmailResponseTimePrediction {

	public static void main(String[] args) throws Exception {
		
		EmailThreadParser emailParser = new EmailThreadParser();
		ThreadDataSet dataset1 = emailParser.parse("subjects.txt", "attachments.txt", "messages.txt");
		
		HasResponseFilterRule filterRule = new HasResponseFilterRule(null);
		ThreadDataFilter filter = new ThreadDataFilter();
		ThreadDataSet dataset2 = filter.filt(dataset1, filterRule);
				
		int addressNum = 170;
		
		IBasicFeatureRule[] basicRules = new IBasicFeatureRule[4];
		basicRules[0] = new EmailSenderIdRule("senderId", addressNum);
		basicRules[1] = new EmailRecipientIdsRule("recipient", addressNum);
		basicRules[2] = new EmailSubjectLengthRule("subjectLength");
		basicRules[3] = new EmailRecipientNumRule("recipientNum");
		
		IBasicFeatureRule[] basicRules1 = new IBasicFeatureRule[1];
		basicRules1[0] = new ContainsFollowMessageRule("hasResponse");
		
		IBasicFeatureRule[] basicRules2 = new IBasicFeatureRule[1];
		basicRules2[0] = new FirstResponseTimeRule("responseTime");
		
		WekaDataSetInitializer initializer = new WekaDataSetInitializer();
		BasicFeatureExtractor basicExtractor = new BasicFeatureExtractor(initializer);
		
		IntermediateDataSet featureSet1 = basicExtractor.extract(dataset1, "feature", basicRules);
		IntermediateDataSet measure1 = basicExtractor.extract(dataset1, "measure1", basicRules1);		
		IntermediateDataSet predictionSet1 = featureSet1.mergeByAttributes(measure1);
		predictionSet1.setTargetIndex();
		
		IntermediateDataSet featureSet2 = basicExtractor.extract(dataset2, "feature", basicRules);
		IntermediateDataSet measure2 = basicExtractor.extract(dataset2, "measure2", basicRules2);		
		IntermediateDataSet predictionSet2 = featureSet2.mergeByAttributes(measure2);
		predictionSet2.setTargetIndex();
		predictionSet2.save("temp.arff");
		
		IntermediateDataSet[] traintest1 = predictionSet1.splitToTrainAndTest(0.8);
		IntermediateDataSet[] traintest2 = predictionSet2.splitToTrainAndTest(0.8);
		
		ISuperFeatureRule[] logi = new ISuperFeatureRule[1];
		ArrayList<String> domain = new ArrayList<String>(2);
		domain.add("y"); domain.add("n");
		logi[0] = new WekaLogisticRegressionModelRule("hasResponse", domain);		
		((IWekaModelRule)logi[0]).train(traintest1[0], null);
		
		ISuperFeatureRule[] linear = new ISuperFeatureRule[1];
		linear[0] = new WekaLinearRegressionModelRule("responseTime");		
		((IWekaModelRule)linear[0]).train(traintest2[0], null);
		
		
		SuperFeatureExtractor superExtractor = new SuperFeatureExtractor(initializer);
		IntermediateDataSet finDataSet1 = superExtractor.extract(traintest1[1], "testCluster", logi);
		IntermediateDataSet finDataSet2 = superExtractor.extract(traintest2[1], "testCluster", linear);
		
		((IWekaModelRule)logi[0]).evaluate((WekaDataSet)traintest1[0], (WekaDataSet)traintest1[1]);
		((IWekaModelRule)linear[0]).evaluate((WekaDataSet)traintest2[0], (WekaDataSet)traintest2[1]);

	}

}
