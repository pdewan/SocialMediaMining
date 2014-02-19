package prototype;

import java.util.ArrayList;

public class Kmeans {
	DistanceMeasure measurement;
	int K;
	
	public Kmeans(DistanceMeasure dm, int k){
		this.measurement = dm;
		this.K = k;
	}

	public Kmeans(int k){
		this.measurement = new DistanceMeasureInstance();
		this.K = k;
	}
	
	//centers would be returned by Train as trained model
	//other training result: instance-cluster, cluster size, variance, etc. will be print out / saved
	public InstanceSet Train(InstanceSet trainingData){
		InstanceSet centers = randomCenterPick(trainingData, 1);
		
		return centres;
	}
	
	//user can assign initial centers as they like.
	//centers are defaultly random picked from training data
	public InstanceSet Train(InstanceSet trainingData, InstanceSet centers){
		
		
		return centers;
	}
	
	//randomCenterPickTime is defaultly 1
	public InstanceSet Train(InstanceSet trainingData, int randomCenterPickTime){
		InstanceSet centers = randomCenterPick(trainingData, randomCenterPickTime);
		
		return centers;
	}
	
	public InstanceSet randomCenterPick(InstanceSet trainingData, int randomCenterPickTime){
		InstanceSet centers = new InstanceSet(K);
		int max = trainingData.size() - 1;
		
		return centers;
	}
}
