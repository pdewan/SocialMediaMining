package dataimport.json;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONObject;

import dataconvert.IntermediateData;
import dataimport.MessageData;
import dataimport.MsgDataConfig;
import dataimport.ThreadData;
import dataimport.ThreadDataSet;
import weka.core.Attribute;
import weka.core.Instances;

public class JsonThreadParser {
	
	static JsonDataConfig STARTMSGCONFIG;
	static JsonDataConfig FOLLOWMSGCONFIG;
	protected static String keyDateAttrName = "Date";
	
	//User should call this constructor if there are >=1 attributes are date format string
	//Or the date attribute's name is not "Date"
	public JsonThreadParser(JsonDataConfig startMsgConfig, JsonDataConfig followMsgConfig, String keyDateAttrName){
		STARTMSGCONFIG = startMsgConfig;
		FOLLOWMSGCONFIG = followMsgConfig;
		JsonThreadParser.keyDateAttrName = keyDateAttrName;
	}
	
	public JsonThreadParser(JsonDataConfig startMsgConfig, JsonDataConfig followMsgConfig){
		STARTMSGCONFIG = startMsgConfig;
		FOLLOWMSGCONFIG = followMsgConfig;
		JsonThreadParser.keyDateAttrName = "Date";
	}
	
	
	protected static void parseMsgObject(JSONObject json, ThreadData thread, JsonDataConfig config) throws Exception{
		
		MessageData msg = new MessageData();
		
		for(Entry<String, String> entry : config.attrTypeEntries()){
			String attrName = entry.getKey();
			String attrType = entry.getValue();
			
			if(!json.has(attrName)) continue;
			
			Object obj = json.get(attrName);
			//System.out.println(o);
			if(obj instanceof String){							
				String s = (String)obj;
	
				switch(attrType){
				case(JsonDataConfig.INT): 
					msg.addAttribute(attrName, Integer.parseInt(s));
					//msg.addAttribute(attrName, json.getInt(attrName));
					break;
				case(JsonDataConfig.DOUBLE):
					msg.addAttribute(attrName, Double.parseDouble(s));
					//msg.addAttribute(attrName, json.getDouble(attrName));
					break;
				case(JsonDataConfig.STRING):
					msg.addAttribute(attrName, s);
					//msg.addAttribute(attrName, json.getString(attrName));
					break;
				case(JsonDataConfig.TYPEDATE):
					SimpleDateFormat readformat = new SimpleDateFormat(config.getDateReadFormat());
					Date date = readformat.parse(s);
					SimpleDateFormat saveformat = new SimpleDateFormat(JsonDataConfig.DATEFORMAT_DEFAULT);
					if(attrName.equals(keyDateAttrName)){				
						msg.addAttribute(MsgDataConfig.DATE_DEFAULT, saveformat.format(date));
					}else{
						msg.addAttribute(attrName, saveformat.format(date));
					}
					break;
				default:
					break;
				}
			}else if(obj instanceof JSONObject){
				JSONObject jsonobj = (JSONObject)obj;
				if(attrType.equals(JsonDataConfig.MESSAGE)){								
					attrName = attrName.substring(0, attrName.length()-1);
					if(jsonobj.has(attrName)){
						Object tmp = jsonobj.get(attrName);
						if(tmp instanceof JSONArray){
							JSONArray arr = (JSONArray)tmp;
							for(int i=0; i<arr.length(); i++){
								JSONObject follow = arr.getJSONObject(i);
								parseMsgObject(follow, thread, FOLLOWMSGCONFIG);
							}
						}
					}
				}
			}else if(obj instanceof JSONArray){
				JSONArray arr = (JSONArray)obj;
				if(attrType.equals(JsonDataConfig.MESSAGE)){													
					for(int i=0; i<arr.length(); i++){
						JSONObject follow = arr.getJSONObject(i);
						parseMsgObject(follow, thread, FOLLOWMSGCONFIG);
					}					
				}
			}
		}
		
		thread.addMsgData(msg);		
	}
	
	public ThreadData parseSingleFile(
			File jsonfile) throws Exception{
		
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
		
		ThreadData thread = new ThreadData();
		parseMsgObject(json, thread, STARTMSGCONFIG);
		
		//System.out.println(thread.toString());
		return thread;	
	}
	
	public ThreadDataSet parseDirectory(
			File dirfile) throws Exception{
		
		if(!dirfile.isDirectory()){
			throw new Exception(dirfile.getAbsolutePath() + " suppose to be a directory");
		}
		
		ThreadDataSet threadSet = new ThreadDataSet();
		parseDirectory(dirfile, threadSet);
		
		return threadSet;
	}
	
	protected void parseDirectory(
			File dirfile, ThreadDataSet threadSet) throws Exception{
		
		if(!dirfile.isDirectory()){
			throw new Exception(dirfile.getAbsolutePath() + " suppose to be a directory");
		}
		
		File[] files = dirfile.listFiles();
		for(int i=0; i< files.length; i++){
			if(files[i].isDirectory()){
				parseDirectory(files[i], threadSet);
			}
			if(!files[i].getName().endsWith(".txt")){
				continue;
			}
			//System.out.println("parsing "+files[i].getName());
			threadSet.setThreadData(threadSet.size(), parseSingleFile(files[i]));
		}
	}

}
