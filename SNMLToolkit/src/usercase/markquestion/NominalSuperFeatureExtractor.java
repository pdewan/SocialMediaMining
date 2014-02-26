package usercase.markquestion;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;

public class NominalSuperFeatureExtractor implements SuperFeatureExtractor{
	
	NominalSuperFeatureRule rule = null;
	
	public NominalSuperFeatureExtractor(NominalSuperFeatureRule rule){
		this.rule = rule;
	}
	
	//example: newfeatureNameAndType = "question? {y, n}"
	//pass null or "" to savepath if don't want to save
	public void extract(Instances instances, 
			String newfeatureNameAndType, 
			String savepath) throws IOException{
		
		String newfeatureName = 
				newfeatureNameAndType.substring(0, newfeatureNameAndType.indexOf(" "));
		String newfeatureType = newfeatureNameAndType.substring(newfeatureNameAndType.indexOf("{"));
		
		ArrayList<String> markerList = this.markerList(newfeatureType);		
		instances.insertAttributeAt(new Attribute(newfeatureName, markerList), instances.numAttributes());
		
		int n = instances.size();
		for(int i=0; i<n; i++){
			Instance instance = instances.get(i);
			int k = this.rule.condition(instance);			
			instance.setValue(instance.numAttributes()-1, markerList.get(k));
		}
		
		if(savepath != null && savepath.length()>0){
			for(int i=0; i < instances.numAttributes(); i++){
				if(instances.attribute(0).equals(instances.attribute(newfeatureName))){
					System.out.println(instances.attributeStats(0));
				}else{
					instances.deleteAttributeAt(0);
				}
			}			
			
			FileWriter writer = new FileWriter(savepath);
			writer.write(instances.toString());
			writer.flush();
			writer.close();				
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
