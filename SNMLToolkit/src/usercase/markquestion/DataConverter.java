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

public class DataConverter {
	
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
			case("double"):
				writer.write(String.valueOf(json.getDouble(field[0])));
			case("long"):
				writer.write(String.valueOf(json.getLong(field[0])));
			case("string"):
				writer.write("'"+json.getString(field[0])+"'");
			case("date"):
				writer.write("\""+json.getString(field[0])+"\"");
			default:
				//TODO: deal with nominal later
			}
			
			if(i < attributes.length-1){
				writer.write(",");
			}
		}
		
		writer.write(","+label+"\n");
		
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
		
		BufferedWriter listwriter = new BufferedWriter(new FileWriter(arffFileName+".txt"));
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
