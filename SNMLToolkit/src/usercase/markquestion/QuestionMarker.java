package usercase.markquestion;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;

public class QuestionMarker implements Marker {
	
	@Override
	public void mark(Instances instances, String markers, String savepath) throws IOException {		
		makeMarkable(instances, markers);
		System.out.println(instances.numAttributes());
		int n = instances.size();
		for(int i=0; i<n; i++){
			Instance instance = instances.get(i);
			if(condition(instance)){
				instance.setValue(instance.numAttributes()-1, "y");
			}else{
				instance.setValue(instance.numAttributes()-1, "n");
			}
		}
		
		if(savepath != null && savepath.length()>0){
			for(int i=0; i <= instances.numAttributes(); i++){
				instances.deleteAttributeAt(0);
			}
			
			FileWriter writer = new FileWriter(savepath);
			writer.write(instances.toString());
			writer.flush();
			writer.close();				
		}

	}

	@Override
	public boolean condition(Instance instance) {
		for(int i=0; i < instance.numAttributes(); i++){
			if(instance.attribute(i).name().equals("Subject")){
				if(instance.stringValue(i).contains("?")){
					return true;
				}
			}
		}
		return false;
	}

	//add new attribute to save marker
	//change former class to be a normal attribute
	private void makeMarkable(Instances instances, String markers) {
		markers = markers.substring(1, markers.length()-1);
		markers = markers.replace(',', ' ');
		String[] markerArray = markers.split(" ");
		List<String> markerList = new ArrayList<String>();
		for(int i=0; i < markerArray.length; i++){
			if(markerArray[i].length()>0){
				markerList.add(markerArray[i]);
			}
		}
		instances.insertAttributeAt(new Attribute("Question?", markerList), instances.numAttributes());
	}

}
