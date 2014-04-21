package dataconvert;

import rule.filterrule.ThreadFilterRule;
import dataimport.ThreadData;
import dataimport.ThreadDataSet;

public class ThreadDataFilter {
	
	public ThreadDataSet filt(ThreadDataSet threads, ThreadFilterRule rule) throws Exception{
		ThreadDataSet newThreads = new ThreadDataSet();
		newThreads.addThreadData(null);
		for(int i=1; i<threads.size(); i++){
			ThreadData thread = threads.getDataInstance(i);
			if((Boolean)(rule.extract(thread))==true){
				newThreads.addThreadData(thread);
			}
		}
		newThreads.trimToSize();
		return newThreads;
	}

}
