package rule;

/**
 * Basic interface of all feature extracting rules
 *
 * @author Jinjing Ma (jinjingm@cs.unc.edu)
 * @version $1$
 */
public interface IFeatureRule {

	public String getDestFeatureName();
	//Check if an object is a valid feature value for corresponding feature rule
	public void checkValid(Object val) throws Exception;

}
