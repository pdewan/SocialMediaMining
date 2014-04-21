package rule.basicfeature;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import rule.NumericFeatureRule;
import dataimport.ThreadData;

public class ReadFromFileNumericRule extends NumericFeatureRule implements IBasicFeatureRule{
	
	HashMap<Integer, Double> attrVals;
	
	public ReadFromFileNumericRule(String destFeatureName, String fileName) throws IOException {
		super(destFeatureName);
		attrVals = new HashMap<Integer, Double>();
		read(fileName);
	}
	
	private void read(String fileName) throws IOException{
		
		File file = new File(fileName);
		if(file.exists()){
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = null;
			while((line=reader.readLine())!=null){
				Scanner scanner = new Scanner(line);
				int id = scanner.nextInt();
				double val  = scanner.nextDouble();
				scanner.close();
				attrVals.put(id, val);
			}
			reader.close();
		}

	}

	@Override
	public Object extract(ThreadData aThread) throws Exception {
		return attrVals.get(aThread.getThreadId());
	}

}
