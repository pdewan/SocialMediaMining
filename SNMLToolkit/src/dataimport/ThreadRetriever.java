package dataimport;

public class ThreadRetriever {
	public ThreadDataSet targetThreadDataSet;
	public MsgDataConfig aMsgDataConfig;
	
	public ThreadRetriever(ThreadDataSet aThreadDataSet){
		this.targetThreadDataSet = aThreadDataSet;
	}
	
	public ThreadRetriever(){
		this.targetThreadDataSet = new ThreadDataSet();
	}
	
	public void sortMsgIntoThread(MessageData aMsgData){
		Object threadIdObj = aMsgData.getAttribute(MsgDataConfig.THREADID);
		if(threadIdObj==null){
			System.out.println("Error in ThreadRetriever.sortMsgIntoThread: "
					+ "no thread id available of message");
			return;
		}
		int threadId = (int)threadIdObj;
		this.targetThreadDataSet.addMsgData(threadId, aMsgData);
	}
	
	public void trimThreadDataSetToSize(){
		this.targetThreadDataSet.trimToSize();
	}
	
	//public abstract ThreadDataSet retrieveThreads();
	
}
