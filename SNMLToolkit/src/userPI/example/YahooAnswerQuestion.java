package userPI.example;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;

@XmlRootElement(name="Question")
@XmlAccessorType(XmlAccessType.FIELD)
public class YahooAnswerQuestion {
	
	@XmlElement(name="id")
	private String id;
	
	 @XmlElement(name="Subject")
	    private String subject;
	 
	 @XmlElement(name="Content")
	 private String content;

    @XmlElement(name="Date")
    @XmlSchemaType(name="date")
    	private Date date;
    
    @XmlElement(name="Timestamp")
	private long timestamp;

    @XmlElement(name="NumAnswers")
    private int numAnswers;
    
    @XmlElement(name="NumComments")
    private int numComments;
    
    /*
    @XmlElementWrapper(name="Answers")
    @XmlElement(name="Answer")
    private List<Answer> answers;
	*/

}
