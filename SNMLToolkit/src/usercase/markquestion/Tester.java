package usercase.markquestion;

import java.io.IOException;

public class Tester {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String[] attributes = {"Subject string", "class {1,2}"};
		DataConverter.jsonToArff(
				"/Users/jinjingma/Documents/workspace/DataCollection/data/politics/content", 
				"test", attributes, "test");
	}

}
