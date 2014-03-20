package dataconvert.rule;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateBasicFeatureRule extends BasicFeatureRule {

	public DateBasicFeatureRule(String destFeatureName, String srcAttrName,
			int inOrder, int kth) {
		super(destFeatureName, srcAttrName, inOrder, kth);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void checkValid(Object val) throws Exception{
		if(!(val==null) && (val instanceof String)){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				format.parse((String)val);
			} catch (ParseException e) {
				throw new Exception("Date format must be yyyy-MM-dd HH:mm:ss");
			}
		}
	}
}
