package usercase.markquestion;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;

public class NominalSuperFeatureExtractor extends SuperFeatureExtractor{
	
	NominalSuperFeatureRule rule = null;
	
	public NominalSuperFeatureExtractor(NominalSuperFeatureRule rule){
		this.rule = rule;
	}
	
	//example: newfeatureNameAndType = "question? {y, n}"
	//pass null or "" to savepath if don't want to save
	public void extract(Instances instances, 
			String superfeatureNameAndType) throws IOException{
		
		super.extract(instances, superfeatureNameAndType);
		
		ArrayList<String> markerList = this.markerList(superfeatureType);		
		instances.insertAttributeAt(new Attribute(this.superfeatureName, markerList), instances.numAttributes());
		
		int n = instances.size();
		for(int i=0; i<n; i++){
			Instance instance = instances.get(i);
			int k = this.rule.condition(instance);			
			instance.setValue(instance.numAttributes()-1, markerList.get(k));
		}		
		
	}
	
	//add new attribute to save marker
	//change former class to be a normal attribute
	protected ArrayList<String> markerList(String newfeatureNameAndType) {		
		String markers = newfeatureNameAndType.substring(1, newfeatureNameAndType.length()-1);
		markers = markers.replace(',', ' ');
		String[] markerArray = markers.split(" ");
		ArrayList<String> markerList = new ArrayList<String>();
		for(int i=0; i < markerArray.length; i++){
			if(markerArray[i].length()>0){
				markerList.add(markerArray[i]);
			}
		}
		return markerList;
	}
}
