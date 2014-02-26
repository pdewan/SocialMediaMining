package usercase.markquestion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import org.json.JSONObject;

import weka.core.Attribute;
import weka.core.Instances;
import weka.core.Utils;
import weka.core.converters.ConverterUtils.DataSource;

public class DataConverter {
	/*
	//Merge several arff files containing partial attributes of the same dataset
	// It's user's responsibility to make sure the order of the instances in each file matches
	// filepaths[] is for attributes, which means if an arff file contains attribute "class"
	// the attribute would be renamed as filepaths[i]'s name
	// Set classfilepath to be null or "" to merge unlabeled files
	// The classfilepath must point to a arff file whose last attribute must be "class"
	static public Instances mergeArffFiles(
			String[] filepaths, 
			String classfilepath) throws Exception{	
		
		if(filepaths == null){
			System.out.println("error in DataConverter.mergeArffFiles"
					+ "null value of filepaths passed in");
			return null;
		}
		

		int n = filepaths.length;
		File[] files = new File[n];
		for(int i=0; i < n; i++){
			files[i] = new File(filepaths[i]);
			if(!files[i].isFile() || !filepaths[i].endsWith(".arff")){
				System.out.println("error in DataConverter.mergeArffFiles: "
						+filepaths[i]+" is not ARFF file");
				return null;
			}
		}
		
		if(classfilepath == null || classfilepath.length() <= 0){
			return mergeArffFilesWithoutLabel(filepaths);
		}else{
			return mergeArffFilesWithLabel(filepaths, classfilepath);
		}
	}
	
	static private Instances mergeArffFilesWithoutLabel(
			String[] filepaths) throws Exception{			
		
		int n = filepaths.length;
	
		DataSource source;
		Instances[] instances = new Instances[n];		
		for(int i=0; i < n; i++){
			source = new DataSource(filepaths[i]);
			instances[i] = source.getDataSet();
			
			for(int j=0; j < instances[i].numAttributes(); j++){
				Attribute attr;
				if(instances[i].classIndex() != j){
					attr = instances[i].attribute(j);
				}else{
					attr = instances[i].attribute(j).copy(new File(filepaths[i]).getName());
				}
				instances[0].insertAttributeAt(attr, instances[i].numAttributes());
			}
		}
		return instances[0];		
	}
		
	static private Instances mergeArffFilesWithLabel(
			String[] filepaths, 
			String classfilepath) throws Exception{	
		
		Instances instance = mergeArffFilesWithoutLabel(filepaths);	
		
		DataSource source = new DataSource(classfilepath);
		Instances classInstance = source.getDataSet();		
		
		for(int j=0; j < classInstance.numAttributes(); j++){
			instance.insertAttributeAt(classInstance.attribute(j), classInstance.numAttributes());
		}		
		instance.setClassIndex(instance.numAttributes()-1);
		
		return instance;		
	}
	*/
	
	static private void jsonToArffSingleFile(
			File jsonfile, 
			String[] attributes, 	// example: attributes = {"Comment string", "class {1, 2}"}
			String label, 
			BufferedWriter writer) throws IOException{
		
		BufferedReader reader = new BufferedReader(new FileReader(jsonfile));
		StringBuilder sb = new StringBuilder();
		String line;
		while((line=reader.readLine())!=null){
			sb.append(line);
		}
		
		JSONObject json = new JSONObject(sb.toString());
		
		for(int i=0; i < attributes.length; i++){
			String[] field = attributes[i].split(" ");	//TODO: don't support name containing space now
			switch(field[1].toLowerCase()){
			case("int"): 
				writer.write(String.valueOf(json.getInt(field[0])));
				break;
			case("double"):
				writer.write(String.valueOf(json.getDouble(field[0])));
				break;
			case("long"):
				writer.write(String.valueOf(json.getLong(field[0])));
				break;
			case("string"):
				writer.write("'"+json.getString(field[0])+"'");
				break;
			case("date"):
				writer.write("\""+json.getString(field[0])+"\"");
				break;
			default: //nomial
				if(!attributes[i].toLowerCase().contains("class") && 
						!attributes[i].toLowerCase().contains("dir")){
					writer.write("'"+json.getString(field[0])+"'");
				}
				break;
			}
			
			if(i < attributes.length-1){
				writer.write(",");
			}
		}
		
		if(attributes[attributes.length-1].toLowerCase().contains("class") || 
				attributes[attributes.length-1].toLowerCase().contains("dir")){
			writer.write(label);
		}
		writer.write("\n");
		writer.flush();
	}
	
	static private String printArffHeader(
			String relation, 
			String[] attributes){
		
		StringBuffer text = new StringBuffer();
		text.append(Instances.ARFF_RELATION).append(" ").
			append(relation).append("\n\n");
		
		for(int i=0; i < attributes.length; i ++){
			text.append(Attribute.ARFF_ATTRIBUTE).append(" ");
			text.append(attributes[i].substring(0, attributes[i].indexOf(" "))).append(" ");
			
			String fieldType = attributes[i].substring(attributes[i].indexOf(" ")+1).toLowerCase();		
			if(fieldType.equals("int") || fieldType.equals("double") || fieldType.equals("long")){
				text.append("numeric");
			}else{
				text.append(attributes[i].substring(attributes[i].indexOf(" ")+1));
			}
			text.append("\n");			
		}
		text.append("\n");
		
		text.append(Instances.ARFF_DATA).append("\n");
		
		return text.toString();
	}

	static public void jsonToArff(
			String dirPath, 
			String relation, 
			String[] attributes, 
			String arffFileName) throws IOException{
		
		File dir = new File(dirPath);
		if(!dir.exists() || !dir.isDirectory()){
			System.out.println("path is not for data directory");
			return;
		}
		for(int i=0; i < attributes.length; i ++){
			if(!attributes[i].contains(" ")){
				System.out.println("wrong attribute input");
				return;
			}
		}
		
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(arffFileName+".arff"));
		writer.write(printArffHeader(relation, attributes));
		writer.flush();
		
		BufferedWriter listwriter = new BufferedWriter(new FileWriter(arffFileName+".list"));
		long k=1;
		
		File[] subDirs = dir.listFiles();
		for(int i=0; i < subDirs.length; i++){
			if(!subDirs[i].isDirectory()){
				if(subDirs[i].getName().endsWith(".txt")){			
					jsonToArffSingleFile(subDirs[i], attributes, "?", writer);
					listwriter.write(k++ +" "+subDirs[i].getName()+"\n");
				}
			}else{
				File[] files = subDirs[i].listFiles();
				for(int j=0; j < files.length; j++){
					jsonToArffSingleFile(files[j], attributes, subDirs[i].getName(), writer);
					listwriter.write(k++ +" "+subDirs[i].getName()+"/"+files[j].getName() +"\n");
				}
			}
		}
		
		writer.close();
		listwriter.flush();
		listwriter.close();
		
	}
	
	
}
