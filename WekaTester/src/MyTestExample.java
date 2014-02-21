import java.io.File;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.converters.JSONLoader;


public class MyTestExample {

	public static void main(String[] args) throws Exception {
		String path = "./dat/answers";
		File file = new File(path);
		File[] files = file.listFiles();
		
		JSONLoader jsonLoader = new JSONLoader();
		jsonLoader.setSource(files[1]);
		
		Instances data = jsonLoader.getDataSet();
		System.out.println(data.size());
		System.out.println(data.toSummaryString());
		
		
		
	}

}
