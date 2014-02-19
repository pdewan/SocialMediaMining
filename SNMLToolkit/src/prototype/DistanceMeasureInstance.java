package prototype;

//user should implement DistanceMeasure<Instance> to customize

public class DistanceMeasureInstance implements DistanceMeasure<Instance>{
	@Override
	public double measure(Instance x, Instance y){
		double d = 0;
		
		DistanceMeasureWordbag measureWb = new DistanceMeasureWordbag();
		d += measureWb.measure((Wordbag)x.getFieldContent("Content"), (Wordbag)y.getFieldContent("Content"));
		
		return d;
	}

}
