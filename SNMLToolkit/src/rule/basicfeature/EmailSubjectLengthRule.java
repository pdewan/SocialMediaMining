package rule.basicfeature;

import dataimport.MessageData;
import dataimport.ThreadData;
import rule.NumericFeatureRule;

/**
 * Extract the char-length of starting email's subject
 *
 * @author Jinjing Ma (jinjingm@cs.unc.edu)
 * @version $1$
 */
public class EmailSubjectLengthRule extends NumericFeatureRule implements IBasicFeatureRule{

	/**
	   * Create an EmailSubjectLengthRule
	   * 
	   * @param destFeatureName name for extracted feature
	   */
	public EmailSubjectLengthRule(String destFeatureName) {
		super(destFeatureName);
	}

	/**
	 * Extract the char-length of starting email's subject
	 * from given thread
	 * 
	 * @param aThread the source thread data
	 * @return the char-length of starting email's subject 
	 * @throws Exception when extracted value is invalid
	 */
	@Override
	public Object extract(ThreadData aThread) throws Exception {
		
		MessageData msg = aThread.getKthEarlest(0);
		String subj = (String)msg.getAttribute("Subject");
		if(subj==null) return 0;
		
		double length = subj.length();
		
		return length;
	}

}
