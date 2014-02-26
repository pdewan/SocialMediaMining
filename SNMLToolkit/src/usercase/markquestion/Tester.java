package usercase.markquestion;

import java.io.IOException;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.unsupervised.attribute.RenameAttribute;

public class Tester {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		String[] attributes = {"Content string"};//, "NumComments int", "dir {c1, c2}"};
		DataConverter.jsonToArff(
				"/Users/jinjingma/Documents/workspace/DataCollection/data/politics/content", 
				"test", attributes, "test");
		
		DataSource source = new DataSource("test.arff");
		Instances data = source.getDataSet();
		System.out.println(data.toString());
		
		NominalSuperFeatureRule rule = new SubjectHasQuestionRule();
		NominalSuperFeatureExtractor extractor = new NominalSuperFeatureExtractor(rule);
		extractor.extract(data, "question? {y, n}", "marked.arff");
		System.out.println(data.toString());
		/*
		Instances data2 = source.getDataSet();
		marker.mark(data2, "{y, n}", "marked2.arff");
		
		
		DataSource source1 = new DataSource("marked.arff");
		DataSource source2 = new DataSource("test.arff");
		Instances testMerge1 = source1.getDataSet();
		
		Instances testMerge2 = source2.getDataSet();
		Instances merged = Instances.mergeInstances(testMerge1, testMerge2);
		System.out.println(merged.toString());
		*/
	}

}
