package dataimport;

import java.util.ArrayList;

public class ThreadDataSet{
	protected ArrayList<ThreadData> threads;
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<threads.size(); i++){
			if(threads.get(i)!=null){ 
				sb.append(threads.get(i).toString());
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}
	//thread id must be integer
	public ThreadDataSet(){
		threads = new ArrayList<ThreadData>();
	}
	
	public ThreadDataSet(int threadNum){
		threads = new ArrayList<ThreadData>(threadNum+1);
	}
	
	//Setup an empty thread at threadId only if it's not initialized yet
	public void setThreadData(int threadId){
		if(this.threads.get(threadId)==null){
			this.setThreadData(threadId, new ThreadData());
		}
	}
	
	public void setThreadData(int threadId, ThreadData aThread){
		if(threadId >= threads.size()){
			threads.add(null);
		}
		threads.set(threadId, aThread);
	}
	
	public void addThreadData(ThreadData aThread){
		threads.add(aThread);
	}
	/*
	public void setThreadData(String threadId, ThreadData aThread){
		this.setThreadData(Integer.parseInt(threadId), aThread);
	}
	*/
	
	//Setup an empty thread at threadId only if it's not initialized yet
	public ThreadData getDataInstance(int threadId){
		if(threadId >= threads.size() ){
			int i = threads.size();
			for(; i<=threadId; i++){
				threads.add(null);
			}
		}
		if(this.threads.get(threadId)==null){
			this.setThreadData(threadId, new ThreadData());
		}
		return threads.get(threadId);
	}
	/*
	public ThreadData getThreadData(String threadId){
		return threads.get(Integer.parseInt(threadId));
	}
	*/
	
	public void addMsgData(int threadId, MessageData aMsgData){
		ThreadData thread = this.getDataInstance(threadId);
		thread.addMsgData(aMsgData);
	}
	
	public int size(){
		return threads.size();
	}
	
	public void trimToSize(){
		threads.trimToSize();
	}
}
