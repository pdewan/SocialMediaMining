package dataimport.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import dataimport.MsgDataConfig;
import dataimport.MessageData;

public class XmlFileReader {
	protected MsgDataConfig dataConfig;
	
	public XmlFileReader(){
		dataConfig = null;
	}
	
	public XmlFileReader(MsgDataConfig aDataConfig){
		this.setDataConfig(aDataConfig);
	}
	
	public void setDataConfig(MsgDataConfig aDataConfig){
		this.dataConfig = aDataConfig;
	}
	
	protected MessageData readInstanceEvent(XMLEvent event){
		return null;
	}
	
	public static void main(String[] args) throws FileNotFoundException, XMLStreamException{
		// First, create a new XMLInputFactory
	      XMLInputFactory inputFactory = XMLInputFactory.newInstance();
	      // Setup a new eventReader
	      InputStream in = new FileInputStream(new File("posts_short.xml"));
	      XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
	      // read the XML document
	      int i=0;
	      while (eventReader.hasNext()) {
	    	  if(i++>=10) break;
	    	  XMLEvent event = eventReader.nextEvent();
	    	  //if(event.isEndElement())
	    		  System.out.println(event.toString());
	      }
	}
	
}