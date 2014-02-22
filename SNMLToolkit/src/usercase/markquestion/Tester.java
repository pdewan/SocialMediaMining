package usercase.markquestion;

import java.io.IOException;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class Tester {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String[] attributes = {"Subject string", "class {c1, c2}"};
		DataConverter.jsonToArff(
				"/Users/jinjingma/Documents/workspace/DataCollection/data/politics/content", 
				"test", attributes, "test");
		
		DataSource source = new DataSource("test.arff");
		Instances data = source.getDataSet();
		Marker marker = new QuestionMarker();
		marker.mark(data, "{y, n}", "marked.txt");
	}

}
