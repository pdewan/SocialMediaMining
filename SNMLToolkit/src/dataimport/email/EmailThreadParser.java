package dataimport.email;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import dataimport.MessageData;
import dataimport.ThreadDataSet;
import dataimport.ThreadRetriever;

public class EmailThreadParser {
	
	public void parseMsgSubjectInfo(File subjectInfoFile, ArrayList<MessageData> msgs) throws IOException{
		//ArrayList<MessageData> msgs = new ArrayList<MessageData>();		
		
		BufferedReader reader = new BufferedReader(new FileReader(subjectInfoFile));
		String line;
		while((line=reader.readLine())!=null){
			Scanner scanner = new Scanner(line);
			String tmp = scanner.next();
			int msgId = Integer.parseInt(tmp.substring("Message:".length()));
			
			String subject = line.substring(line.indexOf("Subject:")+"Subject:".length());
			
			scanner.close();
			
			MessageData msg = null;
			if(msgId >= msgs.size() ){
				int i = msgs.size();
				for(; i<=msgId; i++){
					msgs.add(null);
				}
			}
			msg = msgs.get(msgId);
			if(msg==null){
				msg = new MessageData();
				msg.addAttribute(EmailDataConfig.ID, msgId);
				msgs.set(msgId, msg);
			}			
			msg.addAttribute(EmailDataConfig.SUBJECT, subject);			
		}
		reader.close();
		msgs.trimToSize();
	}
	
	public void parseMsgAttachmentInfo(File attachmentInfoFile, ArrayList<MessageData> msgs) throws IOException{		
		BufferedReader reader = new BufferedReader(new FileReader(attachmentInfoFile));
		String line;
		while((line=reader.readLine())!=null){
			Scanner scanner = new Scanner(line);
			scanner.next();
			int msgId = scanner.nextInt();
			
			String tmp = scanner.next();		
			double attachNum = Double.parseDouble(tmp.substring("Num_Attachments:".length()));
					
			String[] attachments = null;
			if(attachNum>0){	
				tmp = scanner.next();
				tmp = tmp.substring("AttachedFiles:[".length(), tmp.length()-1);
				attachments = tmp.split(",");
			}
			
			scanner.close();
			
			MessageData msg = null;
			if(msgId >= msgs.size() ){
				int i = msgs.size();
				for(; i<=msgId; i++){
					msgs.add(null);
				}
			}
			msg = msgs.get(msgId);
			if(msg==null){
				msg = new MessageData();
				msg.addAttribute(EmailDataConfig.ID, msgId);
				msgs.set(msgId, msg);
			}			
			msg.addAttribute(EmailDataConfig.ATTACHMENT_NUM, attachNum);
			msg.addAttribute(EmailDataConfig.ATTACHMENTS, attachments);
		}
		reader.close();
		msgs.trimToSize();
	}
	
	public ThreadDataSet parseThreadInfo(File threadInfoFile, ArrayList<MessageData> msgs) throws IOException, ParseException{
		ThreadRetriever retriever = new ThreadRetriever();
		SimpleDateFormat oriDateFormat = new SimpleDateFormat("MMM dd HH:mm:ss yyyy");
		SimpleDateFormat destDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		BufferedReader reader = new BufferedReader(new FileReader(threadInfoFile));
		String line;
		while((line=reader.readLine())!=null){
			Scanner scanner = new Scanner(line);
			String tmp = scanner.next();
			int msgId = Integer.parseInt(tmp.substring("Message:".length()));
			
			tmp = scanner.next();
			int threadId = Integer.parseInt(tmp.substring("Thread:".length()));
			
			tmp = scanner.next();
			int fromId = Integer.parseInt(tmp.substring("From:[".length(), tmp.length()-1));
			
			tmp = scanner.next();
			tmp = tmp.substring("Recipients:[".length(), tmp.length()-1);
			String[] recipients = tmp.split(",");
			
			tmp = scanner.next();
			String dayOfWeek = tmp.substring("Received-Date:".length());
			String month = scanner.next();
			String day = scanner.next();
			String time = scanner.next();
			String timezone = scanner.next();
			String year = scanner.next();
			Date d = oriDateFormat.parse(month +" "+ day +" "+ time +" "+year);
			String date = destDateFormat.format(d);
			
			scanner.close();
			
			MessageData msg = null;
			if(msgId >= msgs.size() ){
				int i = msgs.size();
				for(; i<=msgId; i++){
					msgs.add(null);
				}
			}
			msg = msgs.get(msgId);
			if(msg==null){
				msg = new MessageData();
				msg.addAttribute(EmailDataConfig.ID, msgId);
				msgs.set(msgId, msg);
			}			
			msg.addAttribute(EmailDataConfig.THREADID, threadId);
			msg.addAttribute(EmailDataConfig.FROM, fromId);
			msg.addAttribute(EmailDataConfig.RECIPIENTS, recipients);
			msg.addAttribute(EmailDataConfig.DAYOFWEEK, dayOfWeek);
			msg.addAttribute(EmailDataConfig.DATE, date);
			msg.addAttribute(EmailDataConfig.TIMEZONE, timezone);	
						
			retriever.sortMsgIntoThread(msg);
			//System.out.println(retriever.targetThreadDataSet.getThreadData(threadId).msgDatas.toString());
		}
		reader.close();
		
		retriever.trimThreadDataSetToSize();
		return retriever.targetThreadDataSet;
	}
	
	public ThreadDataSet parse(String msgSubjectInfoPath, 
			String msgAttachInfoPath,
			String threadInfoPath) throws IOException, ParseException{
		
		File msgSubjectInfo = new File(msgSubjectInfoPath);
		File msgAttachInfo = new File(msgAttachInfoPath);
		File threadInfo = new File(threadInfoPath);
		/*
		if(!msgSubjectInfo.exists()){
			System.out.println("Error in EmailThreadParser.parse: "
					+ "file "+msgSubjectInfoPath +" does not exist");
			return null;
		}
		if(!msgAttachInfo.exists()){
			System.out.println("Error in EmailThreadParser.parse: "
					+ "file "+msgAttachInfoPath +" does not exist");
			return null;
		}
		if(!threadInfo.exists()){
			System.out.println("Error in EmailThreadParser.parse: "
					+ "file "+threadInfoPath +" does not exist");
			return null;
		}
		*/
		
		 ArrayList<MessageData> msgs = new  ArrayList<MessageData>();
		 this.parseMsgSubjectInfo(msgSubjectInfo, msgs);
		 this.parseMsgAttachmentInfo(msgAttachInfo, msgs);
		 ThreadDataSet threads = this.parseThreadInfo(threadInfo, msgs);
		 
		 return threads;		
	}

}
