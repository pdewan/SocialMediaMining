package usercase.markquestion;

import java.io.IOException;

import weka.core.Instance;
import weka.core.Instances;

public interface Marker {
	//example: markers = {y, n}
	//pass null or "" to savepath if don't want to save
	public void mark(Instances instances, String markers, String savepath) throws IOException; 
	boolean condition(Instance instance);
	

}
