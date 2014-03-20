package dataimport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;


public class ThreadData {
	/*	messageDatas in this thread
	 * 	Sorted by date&time
	 */
	public TreeSet<MessageData> msgDatas;
		
	public ThreadData(){
		msgDatas = new TreeSet<MessageData>(new Comparator<MessageData>(){
            public int compare(MessageData a, MessageData b){
            	Object dateObjA = a.getAttribute(MsgDataConfig.DATE);
            	Object dateObjB = b.getAttribute(MsgDataConfig.DATE);
            	if(dateObjA==null || dateObjB==null){
            		return 0;
            	}
            	
            	String dateA = (String)dateObjA;
            	String dateB = (String)dateObjB;
            	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            	
				try {
					Date dA = dateFormat.parse(dateA);
					Date dB = dateFormat.parse(dateB);
	            	return dA.compareTo(dB);
				} catch (ParseException e) {
					e.printStackTrace();
					return 0;
				}          	
            }
		});
        
	}
	
	/*
	public void setStartPost(PostData aStartPost){
		startPost = aStartPost;
	}
	
	public void addFollowPost(PostData aFollowPost){
		followPosts.add(aFollowPost);
	}
	*/
	
	public void addMsgData(MessageData aMsgData){
		msgDatas.add(aMsgData);
	}
	
	public int size(){
		return msgDatas.size();
	}
	
	public MessageData getKthEarlest(int k) throws Exception{
		int msgNum = size();
		if(k >= msgNum){
			return null;
			//throw new Exception("Error in ThreadData.getKthEarlest: request out of boundary");
		}
		
		Iterator<MessageData> iter = msgDatas.iterator();
		for(int i=0; i < k && iter.hasNext(); i++);
		return iter.next();		
	}
	
	public MessageData getKthLatest(int k) throws Exception{
		int msgNum = size();
		if(k >= msgNum){
			return null;
			//throw new Exception("Error in ThreadData.getKthLatest: request out of boundary");
		}
		
		Iterator<MessageData> iter = msgDatas.descendingIterator();
		for(int i=0; i < k && iter.hasNext(); i++);
		return iter.next();		
	}
}