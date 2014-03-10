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
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import dataimport.json.JsonDataConfig;
import dataimport.json.yahooanswers.YahooAnswersDataConfig;
import weka.core.Attribute;
import weka.core.Instances;
import weka.core.Utils;
import weka.core.converters.ConverterUtils.DataSource;

public class YahooAnswersJsonToArffDataConverter {
	
	JsonDataConfig dataConfig;
	//List<JsonArrayFeatureExtractor> answersFeatureExtractor;
	
	public YahooAnswersJsonToArffDataConverter(
			JsonDataConfig config//,
		/*	List<JsonArrayFeatureExtractor> featureExtractors*/){
		this.dataConfig = config;
		//this.answersFeatureExtractor = featureExtractors;
	}
	
	// example: attributes = {"Comment", "NumAnswers"}, or {QuestionConfig.COMMENT, QuestionConfig.NUMANSWERS}
	private void convertSingleFile(
			File jsonfile, 
			String[] attributes, 	
			String label, 
			BufferedWriter writer) throws IOException{
		
		BufferedReader reader = new BufferedReader(new FileReader(jsonfile));
		StringBuilder sb = new StringBuilder();
		String line;
		while((line=reader.readLine())!=null){
			sb.append(line);
		}
		
		JSONObject json = new JSONObject(sb.toString());
		//get rid of wraps
		if(json.has("query")){
			json = json.getJSONObject("query");
		}
		if(json.has("results")){
			json = json.getJSONObject("results");
		}
		if(json.has("Question")){
			json = json.getJSONObject("Question");
		}
		
		for(int i=0; i < attributes.length; i++){			
			
			Object fieldValue = json.get(attributes[i]);
			if(fieldValue instanceof JSONObject ||
					fieldValue instanceof JSONArray){
				
			}
			
			/*
			if(attributes[i].equals("Answers")){
				JSONArray answers = json.getJSONArray("Answers");
				for(int k=0; k<answersFeatureExtractor.size(); k++){
					double result = answersFeatureExtractor.get(k).extract(answers);
					writer.write(String.valueOf(result));
				}
				continue;
			}			
			*/
			
			String type = dataConfig.getFieldType(attributes[i]);
			if(type==null){
				System.out.println("error in JsonToArffDataConverter.convertSingleFile: attribute " +
										attributes[i] + "unsupported");
				continue;
			}
			
			switch(type){
			case("int"): 
				writer.write(String.valueOf(json.getInt(attributes[i])));
				break;
			case("double"):
				writer.write(String.valueOf(json.getDouble(attributes[i])));
				break;
			case("long"):
				writer.write(String.valueOf(json.getLong(attributes[i])));
				break;
			case("string"):
				//TODO: deal with "'" later, now just replace with " ".
				//TODO: deal with "\n" later, now just replace with " \\n ".
				String s = json.getString(attributes[i]).replaceAll("'", "");
				s = s.replaceAll("\n", " \\n ");
				writer.write("'"+s+"'");
				break;
			case("JSONStructure"):
				Object o = json.get(attributes[i]);
				String os = o.toString();
				os = os.replaceAll("'", "");
				os = os.replaceAll("\n", " \\n ");
				writer.write("'"+os+"'");
				break;
			case("date"):
				writer.write("\""+json.getString(attributes[i])+"\"");
				break;
			default: //nomial
				if(!attributes[i].toLowerCase().contains("class") && 
						!attributes[i].toLowerCase().contains("dir")){
					writer.write("'"+json.getString(attributes[i])+"'");
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
	
	private String printArffHeader(
			String relation, 
			String[] attributes){
		
		StringBuffer text = new StringBuffer();
		text.append(Instances.ARFF_RELATION).append(" ").
			append(relation).append("\n\n");
		
		for(int i=0; i < attributes.length; i ++){
			/*
			if(attributes[i].equals("Answers")){				
				for(int k=0; k<answersFeatureExtractor.size(); k++){
					text.append(Attribute.ARFF_ATTRIBUTE).append(" ");
					text.append(answersFeatureExtractor.get(k).featureName).append(" ");
					text.append("numeric");
				}
			}else{	*/					
				text.append(Attribute.ARFF_ATTRIBUTE).append(" ");
				text.append(attributes[i]).append(" ");
				
				String fieldType = this.dataConfig.getFieldType(attributes[i]);
				if(fieldType.equals("int") || fieldType.equals("double") || fieldType.equals("long")){
					text.append("numeric");
				}else if(fieldType.equals("JSONStructure")){
					text.append("string");
				}else{
					text.append(fieldType);
				}
			//}
			text.append("\n");			
		}
		text.append("\n");
		
		text.append(Instances.ARFF_DATA).append("\n");
		
		return text.toString();
	}

	public void jsonToArff(
			String dirPath, 
			String relation, 
			String[] attributes, 
			String arffFileName) throws IOException{
		
		File dir = new File(dirPath);
		if(!dir.exists() || !dir.isDirectory()){
			System.out.println("path is not for data directory");
			return;
		}
		/*
		for(int i=0; i < attributes.length; i ++){					
			if(attributes[i].equals("Answers") && answersFeatureExtractor == null){
				System.out.println("error in YahooAnswersJsonToArffDataConverter: "
						+ "no feature extractor for JSONArray 'Answers'");
				return;
			}
		}
		*/
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(arffFileName+".arff"));
		writer.write(printArffHeader(relation, attributes));
		writer.flush();
		
		BufferedWriter listwriter = new BufferedWriter(new FileWriter(arffFileName+".list"));
		long k=1;
		
		File[] subDirs = dir.listFiles();
		for(int i=0; i < subDirs.length; i++){
			if(!subDirs[i].isDirectory()){
				if(subDirs[i].getName().endsWith(".txt")){			
					convertSingleFile(subDirs[i], attributes, "?", writer);
					listwriter.write(k++ +" "+subDirs[i].getName()+"\n");
				}
			}else{
				File[] files = subDirs[i].listFiles();
				for(int j=0; j < files.length; j++){
					convertSingleFile(files[j], attributes, subDirs[i].getName(), writer);
					listwriter.write(k++ +" "+subDirs[i].getName()+"/"+files[j].getName() +"\n");
				}
			}
		}
		
		writer.close();
		listwriter.flush();
		listwriter.close();
		
	}
	
	
}
