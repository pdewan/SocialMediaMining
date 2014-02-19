import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;


public class MyTestExample {

	public static void main(String[] args) throws Exception {
		String path = "./breast-cancer.arff";
		if(args != null && args.length>0){
			path = args[0];		
		}else{
			//throw new IOException("test IOException");
		}

		
		Instances data = DataSource.read(path);
		if (data.classIndex() == -1)
			   data.setClassIndex(data.numAttributes() - 1);
		
		
	}

}
