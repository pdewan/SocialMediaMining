package prototype;

import java.util.ArrayList;

public class InstanceSet {
	ArrayList<Instance> instances;
	
	public InstanceSet(){
		this.instances = new ArrayList<Instance>();
	}
	
	public InstanceSet(int instanceNum){
		this.instances = new ArrayList<Instance>(instanceNum+1);
	}
	
	public InstanceSet(ArrayList<Instance> is){
		this.instances = is;
	}
	
	public InstanceSet(Instance[] is){
		instances = new ArrayList<Instance>(is.length+1);
		for(int i=0; i<is.length; i++){
			this.instances.add(is[i]);
		}
	}
	
	public void add(Instance i){
		this.instances.add(i);
	}
	
	public void remove(Instance i){
		this.instances.remove(i);
	}
	
	public void remove(int idx){
		this.instances.remove(idx);
	}
	
	public int size(){
		return instances.size();
	}
	
	public Instance get(int idx){
		return this.instances.get(idx);
	}
	
	//user define operation
	public Instance mean(){
		if(this.size()<1) return null;
		
		Instance m = new Instance("mean", this.get(0).getFieldNum());
		
		return m;
	}

}
