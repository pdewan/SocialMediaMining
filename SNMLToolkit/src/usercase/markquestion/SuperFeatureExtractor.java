package usercase.markquestion;

import java.io.FileWriter;
import java.io.IOException;

import weka.core.Instances;

public abstract class SuperFeatureExtractor {

	protected String superfeatureName = "";
	protected String superfeatureType = "";
	
	//example: markers = {y, n}
	//pass null or "" to savepath if don't want to save
	public void extract(Instances instances, String superfeatureNameAndType) throws IOException{
		if(superfeatureNameAndType.contains(" ")){
			this.superfeatureName = 
					superfeatureNameAndType.substring(0, superfeatureNameAndType.indexOf(" "));
			this.superfeatureType = 
					superfeatureNameAndType.substring(superfeatureNameAndType.indexOf(" ")+1);
		}else{
			this.superfeatureName = superfeatureNameAndType;
		}
	}

	public void saveResult(Instances instances, String savepath) throws IOException{
		if(savepath == null || !savepath.endsWith(".arff")){
			System.out.println("error SuperFeatureExtractor.saveResult: invalid savepath");
			return;
		}
			for(int i=0; i <= instances.numAttributes(); i++){
				if(instances.attribute(0).name().equals(superfeatureName)){
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
