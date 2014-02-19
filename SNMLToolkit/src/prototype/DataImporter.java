package prototype;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Scanner;



public class DataImporter {
	
	public DataImporter(){
		
	}
	
	static public void configData(String dataConfigPath) throws IOException{
		File dataConfigFile = new File(dataConfigPath);
		if(dataConfigFile.exists()){
			System.out.println("data configuration file does not exist");
		}
		
		BufferedReader reader = new BufferedReader(new FileReader(dataConfigPath));
	       String line = null;
	       short i=0;
	       while((line = reader.readLine()) != null){
	       		if(line.startsWith("%")) continue;	       		
	       		line = line.substring(1);
	       		
	       		if(line.startsWith("#")) {
	       			DataConfig.setFieldNum(Short.parseShort(line));
	       		} else{
	       			DataConfig.addField(line.substring(0, line.indexOf(' ')), 
	       					line.substring(line.indexOf(' ')+1), 
	       					i++);
	       		}
	       		
	       }
	}
	   
	static public enum TextImportMethod{
		RAW, 			//import raw data
		DICTINDEXED, 	//import text as indexed words in Dictionary
		WORDBAG			//import text as bag of words
	}
	static public ArrayList<Instance> dataImport(String dataDirPath, TextImportMethod method) throws IOException, InstantiationException, IllegalAccessException{
       File dataDir = new File(dataDirPath);
       File[] labelDirs = dataDir.listFiles();   
       
       short k=0; 
       int instanceNum = 0;
       for (int i = 0; i < labelDirs.length; i++) {	      
	       if (labelDirs[i].isDirectory()) {
	    	   k ++;
	    	   instanceNum += labelDirs[i].listFiles().length;
	       }
       }
       DataConfig.setLabelNum(k);      
       ArrayList<Instance> instances = new ArrayList<Instance>(instanceNum+5);
    		   
       short label=0;
       for (int i = 0; i < labelDirs.length; i++) {	      
	       if (labelDirs[i].isDirectory()) {
	    	   DataConfig.addLabel(labelDirs[i].getName(), label);
	    	   
	    	   File[] dataFiles = labelDirs[i].listFiles();
	    	   for(int j=0; j<dataFiles.length; j++){
	    		   instances.add(new Instance(DataConfig.fieldNum, label));
	    		   int instanceIndex = instances.size()-1;
	    		   
	    		   BufferedReader reader = new BufferedReader(new FileReader(dataFiles[i]));
	    		   String line = null;
	    		   short curFieldIdx = -1;
	    		   String curFieldName = "";
	    		   boolean readingContent = false;
	    		   
	    	       while((line = reader.readLine()) != null){
	    	    	   if(readingContent){//read a new field
	    	    		   Class type = DataConfig.fieldType.get(curFieldIdx);
	    	    		   Object content = type.newInstance();
	    	    		   
	    	    		   if((line = reader.readLine())!=null && line.length()>0){	    	    			   
		    	    		   if(content instanceof Integer){
		    	    			   int intContent = Integer.parseInt(line);
		    	    			   instances.get(instanceIndex).addField(curFieldIdx, intContent);
		    	    		   }
		    	    		   else if (content instanceof Double){
		    	    			   double dbContent = Double.parseDouble(line);
		    	    			   instances.get(instanceIndex).addField(curFieldIdx, dbContent);
		    	    		   }
		    	    		   else if (content instanceof Boolean){
		    	    			   boolean blContent = Boolean.parseBoolean(line);
		    	    			   instances.get(instanceIndex).addField(curFieldIdx, blContent);
		    	    		   }
		    	    		   else if (type.isArray()){
		    	    			   //TODO: support it later
		    	    		   }
		    	    		   else if (content instanceof String){
		    	    			   String strContent = line;
		    	    			   switch(method){
		    	    			   		case RAW: 
		    	    			   			instances.get(instanceIndex).addField(curFieldIdx, strContent);
		    	    			   		case DICTINDEXED: 
		    	    			   			instances.get(instanceIndex).addField(curFieldIdx, new DictIndexedText(strContent)); 
		    	    			   		case WORDBAG:
		    	    			   			instances.get(instanceIndex).addField(curFieldIdx, new Wordbag(strContent)); 
		    	    			   }	    			   
		    	    		   }
	    	    		   }
	    	    		   readingContent = false;
	    	    	   }
	    	    	  
	    	    	   if(line.startsWith("@")){
	    	    		   curFieldName = line.substring(1);
	    	    		   if(DataConfig.containsField(curFieldName)){
	    	    			   curFieldIdx = DataConfig.getFieldIdx(curFieldName);
	    	    			   readingContent = true;
	    	    		   } else{
	    	    			   readingContent = false;
	    	    		   }
	    	    	   }
	    	    		 
	    	       }
	    	   }
	    	   
	    	   label++;
	       }
       }
	   	
       return instances;		
	}

}
