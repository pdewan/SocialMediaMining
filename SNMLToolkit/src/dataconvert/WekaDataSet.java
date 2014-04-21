package dataconvert;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVSaver;

public class WekaDataSet implements IntermediateDataSet {

	protected Instances dataset;
	
	public WekaDataSet(String datasetName, int capacity){
		dataset = new Instances(datasetName, new ArrayList<Attribute>(), capacity);	
	}
	
	public WekaDataSet(Instances dataset){
		this.dataset = dataset;
	}
	
	public Instances getDataSet(){
		return dataset;
	}
	
	@Override
	public void addDataInstance(IntermediateData inst) throws Exception {
		if(!(inst instanceof WekaData)){
			throw new Exception("Wrong data type passed in");
		}

		Instance wekaInst = ((WekaData)inst).getInstValue();
		dataset.add(wekaInst);
	}

	public Attribute attribute(int index){
		return dataset.attribute(index);
	}
	

	@Override
	public void save(String path) throws Exception{
		if(path.endsWith(".arff")){
			ArffSaver saver = new ArffSaver();
			saver.setInstances(dataset);
			saver.setFile(new File(path));
			saver.writeBatch();
		}else if(path.endsWith(".csv")){
			CSVSaver saver = new CSVSaver();
			saver.setInstances(dataset);
			saver.setFile(new File(path));
			saver.writeBatch();
		}
	}
	
	@Override
	public String toString(){
		return dataset.toString();
	}

	@Override
	public IntermediateDataSet mergeByAttributes(IntermediateDataSet anotherDataSet) throws Exception {
		if(!(anotherDataSet instanceof WekaDataSet)){
			throw new Exception("uncampatible dataset type");
		}
		
		Instances insts = Instances.mergeInstances(dataset, ((WekaDataSet)anotherDataSet).getDataSet());
		return new WekaDataSet(insts);
	}

	@Override
	public IntermediateData getDataInstance(int index) throws Exception {
		WekaData inst = new WekaData(dataset.get(index));
		inst.setRelatedDataset(this);
		return inst;
	}


	@Override
	public int size() {
		return dataset.numInstances();
	}

	@Override
	public IntermediateDataSet mergeByDataInstances(IntermediateDataSet[] otherDataSets)
			throws Exception {
		if(otherDataSets==null || otherDataSets.length<1){ 
			throw new Exception("empty input");
		}
		if(!(otherDataSets instanceof WekaDataSet[])){
			throw new Exception("uncampatible datasets type");
		}
		
		WekaDataSet[] datasets = (WekaDataSet[])otherDataSets;
		
		Instances insts = new Instances(datasets[0].getDataSet());
		for(int i=1; i<datasets.length; i++){
			for(int j=0; j<datasets[i].size(); j++){
				insts.add(((WekaData)datasets[i].getDataInstance(j)).getInstValue());
			}
		}
		return new WekaDataSet(insts);
	}

	public void randomize(){
		dataset.randomize(new java.util.Random(System.currentTimeMillis()));
	}
	
	public void randomize(int seed){
		dataset.randomize(new java.util.Random(seed));
	}
	
	@Override
	public void setTargetIndex(){
		if(dataset.classIndex()<0){
			dataset.setClassIndex(dataset.numAttributes()-1);
		}
	}
	
	@Override
	public void setTargetIndex(int index){
		if(index>=0 && index < dataset.numAttributes()){
			dataset.setClassIndex(index);
		}
	}
	
	@Override
	public IntermediateDataSet[] splitToTrainAndTest(double trainPercent)
			throws Exception {
		
		if(trainPercent >=1 || trainPercent<=0){
			throw new Exception("invalid trainPercent: "+trainPercent);
		}
		
		int trainSize = (int) Math.round(dataset.numInstances() * trainPercent);
		int testSize = dataset.numInstances() - trainSize;		
		Instances train = new Instances(dataset, 0, trainSize);
		Instances test = new Instances(dataset, trainSize, testSize);
		
		IntermediateDataSet[] splits = new IntermediateDataSet[2];
		splits[0] = new WekaDataSet(train);
		splits[1] = new WekaDataSet(test);
		
		return splits;
	}

	@Override
	public IntermediateDataSet[] splitToFolds(int foldNum) throws Exception {
		if(foldNum <=1){
			throw new Exception("invalid foldNum: "+foldNum);
		}
		
		dataset.stratify(foldNum);
		
		IntermediateDataSet[] splits = new IntermediateDataSet[foldNum];
		for(int i=0; i<foldNum; i++){
			splits[i] = new WekaDataSet(dataset.testCV(foldNum, i));
		}
		
		return splits;
	}
	
	public IntermediateDataSet[] getFold(int foldNum, int fold) throws Exception {
		if(foldNum <=1){
			throw new Exception("invalid foldNum: "+foldNum);
		}
		
		Instances tmp = new Instances(dataset);
		tmp.randomize(new java.util.Random(System.currentTimeMillis()));
		tmp.stratify(foldNum);
		
		IntermediateDataSet[] splits = new IntermediateDataSet[2*foldNum];
		for(int i=0; i<foldNum; i++){
			splits[2*i] = new WekaDataSet(tmp.trainCV(foldNum, i));
			splits[2*i+1] = new WekaDataSet(tmp.testCV(foldNum, i));
		}
		
		return splits;
	}

	@Override
	public int numAttributes() throws Exception {
		return dataset.numAttributes();
	}

}
