package dataconvert;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

import weka.core.Attribute;
import weka.core.Instances;

public class WekaArffWriter {
	/*
	public static void write(FlattenedThreadDataSet dataSet, 
			String dataSetName,
			String savePath) throws IOException{
		
		if(!savePath.endsWith(".arff")){
			System.out.println("Error in WekaArffWriter.write: "+ 
					savePath+" must end with \".arff\"");
			return;
		}
		
		File saveFile = new File(savePath);
		if(!saveFile.exists()){
			System.out.println("Error in WekaArffWriter.write: "+ 
					savePath+" invalid");
			return;
		}
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(saveFile));
		writer.write(printHeader(dataSet, dataSetName));
		writer.flush();
		
		//write data
		StringBuffer text = new StringBuffer();
		text.append(Instances.ARFF_DATA).append("\n");
		writer.write(text.toString());
		writer.flush();
		
		int threadNum = dataSet.size();
		for(int threadId = 0; threadId < threadNum; threadId++){
			writer.write(printData(dataSet.getThreadData(threadId), dataSet.attrTypes));
		}
		
		writer.close();
	}
	
	protected static String printData(FlattenedThreadData data, 
			HashMap<String, String> attrTypes){
		
		StringBuffer text = new StringBuffer();
		int attrId = 0; 
		int attrNum = attrTypes.size();
		for(Entry<String, String> attr : attrTypes.entrySet()){
			String type = attr.getValue();
			String name = attr.getKey();
			
			Scanner scanner = new Scanner(type);
			scanner.next();
			int vecLength = scanner.nextInt();
			scanner.close();
			
			if(data.getAttribute(name)==null)
			if(type.contains("vector")){
				double[] vals = (double[])data.getAttribute(name);
				for(int i=0; i < vecLength-1; i++){
					if(vals==null){
						text.append("?,");
					}else{
						text.append(vals[i]).append(",");
					}
				}
				if(vals==null){
					text.append("?");
				}else{
					text.append(vals[vals.length-1]);
				}
			}
			else{
				Object val = data.getAttribute(name);
				if(val==null){
					text.append("?");
				}else{
					text.append(val);
				}
			}
			
			if(attrId++ < attrNum-1){
				text.append(",");
			}			
		}
		text.append("\n");
		
		return text.toString();
	}
	
	protected static String printHeader(FlattenedThreadDataSet dataSet, 
			String dataSetName){
		
		StringBuffer text = new StringBuffer();
		text.append(Instances.ARFF_RELATION).append(" ").
			append(dataSetName).append("\n\n");
	
		for(Entry<String, String> attr : dataSet.attrTypes.entrySet()){
			String type = attr.getValue();
			if(type.contains("vector")){
				Scanner scanner = new Scanner(type);
				scanner.next();
				int vecLength = scanner.nextInt();
				scanner.close();
				for(int i=0; i<vecLength; i++){
					text.append(Attribute.ARFF_ATTRIBUTE).append(" ");
					text.append(attr.getKey()+i).append(" ");
					text.append("numeric");
				}
			}else{
				text.append(Attribute.ARFF_ATTRIBUTE).append(" ");
				text.append(attr.getKey()).append(" ");
				text.append(attr.getValue());
			}
			text.append("\n");			
		}
		text.append("\n");	
		
		return text.toString();
	}
*/
}
