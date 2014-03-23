package dataconvert;

import java.io.File;
import java.util.ArrayList;

import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVSaver;

public class WekaDataSet implements IntermediateDataSet {

	Instances dataset;
	
	public WekaDataSet(String datasetName, int capacity){
		dataset = new Instances(datasetName, new ArrayList<Attribute>(), capacity);	
	}
	
	public WekaDataSet(Instances dataset){
		this.dataset = dataset;
	}
	
	@Override
	public void addInstance(IntermediateData inst) throws Exception {
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
	public void insertAttributeAt(int index) throws Exception{
		// TODO Auto-generated method stub

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

}
