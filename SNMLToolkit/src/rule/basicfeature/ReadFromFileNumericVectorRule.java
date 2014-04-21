package rule.basicfeature;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

import dataimport.ThreadData;
import rule.NumericVectorFeatureRule;

public class ReadFromFileNumericVectorRule extends NumericVectorFeatureRule implements IBasicFeatureRule{
	
	HashMap<Integer, String> attrVals;
	ArrayList<String> domain;
	
	public ReadFromFileNumericVectorRule(String featureName, String fileName) throws IOException {
		super(featureName, 0);
		
		attrVals = new HashMap<Integer, String>();
		domain = read(fileName);
		this.length = domain.size(); 
	}
	
	private ArrayList<String> read(String fileName) throws IOException{
		HashSet<String> domainSet = new HashSet<String>();
		
		File file = new File(fileName);
		if(file.exists()){
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = null;
			while((line=reader.readLine())!=null){
				Scanner scanner = new Scanner(line);
				int id = scanner.nextInt();
				String val  = scanner.next();
				scanner.close();
				attrVals.put(id, val);
				domainSet.add(val);
			}
			reader.close();
		}
		
		ArrayList<String> domain = new ArrayList<String>();
		Iterator<String> it = domainSet.iterator();
	    while(it.hasNext()){
	           domain.add(it.next());
	    }
		domain.trimToSize();
	    return domain;
	}

	@Override
	public Object extract(ThreadData aThread) throws Exception {
		double[] val = new double[length];
		for(int i=0; i<val.length; i++){
			val[i]=0;
		}
		
		int index = domain.indexOf(attrVals.get(aThread.getThreadId()));		
		val[index] = 1;
		
		return val;
	}
	

}
