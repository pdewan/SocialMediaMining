package rule;

import java.util.ArrayList;

public abstract class NominalFeatureRule extends FeatureRule {

	protected ArrayList<String> domain;
	
	
	public NominalFeatureRule(String destFeatureName) {
		super(destFeatureName);
		domain = new ArrayList<String>();
	}
	
	
	public NominalFeatureRule(String destFeatureName, ArrayList<String> aDomain) {
		super(destFeatureName);
		domain = aDomain;
	}
	
	public ArrayList<String> getDomain() {
		return domain;
	}
	
	public void setDomain(ArrayList<String> aDomain){
		domain = aDomain;
	}
	
		//Check if a string is in its domain.
		@Override
		public void checkValid(Object val) throws Exception{
			
			if(val!=null){
				if(val instanceof String){
					for(int i=0; i < domain.size(); i++){
						if(domain.get(i).equals(val)){
							return;
						}
					}
				}
				throw new Exception("invalid attribute value");
			}
			
		}

		

}
