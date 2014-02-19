package prototype;

//Here the distance is defined as (x-y)T(x-y) for K-means
//user can define distance differently, such as |x-y| for K-medians

import java.util.HashMap;
import java.util.Map.Entry;

public class DistanceMeasureWordbag implements DistanceMeasure<Wordbag> {
	
	@Override
	public double measure(Wordbag x, Wordbag y){
		double d = 0;
		for(Entry<Integer, Double> e : x.entrySet()){
			int i = e.getKey();
			if(y.containsWordIndex(i)){
				d += Math.pow(e.getValue()-y.getFreq(i), 2);
			}else{
				d += Math.pow(e.getValue(), 2);
			}
		}
		for(Entry<Integer, Double> e : y.entrySet()){
			int i = e.getKey();
			if(!x.containsWordIndex(i)){
				d += Math.pow(e.getValue(), 2);
			}
		}
		
		return d;
	}

}
