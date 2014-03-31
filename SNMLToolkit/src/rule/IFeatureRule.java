package rule;

public interface IFeatureRule {

	public String getDestFeatureName();
	//Check if an object is a valid feature value for corresponding feature rule
	public void checkValid(Object val) throws Exception;

}
