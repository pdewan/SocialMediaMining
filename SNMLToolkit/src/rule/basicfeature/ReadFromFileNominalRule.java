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
import rule.NominalFeatureRule;

public class ReadFromFileNominalRule extends NominalFeatureRule implements IBasicFeatureRule{

	HashMap<Integer, String> attrVals;
	
	public ReadFromFileNominalRule(String destFeatureName, String fileName) throws IOException {
		super(destFeatureName);
		
		attrVals = new HashMap<Integer, String>();
		ArrayList<String> domain = read(fileName);
		this.setDomain(domain);
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
		return attrVals.get(aThread.getThreadId());
	}

}
