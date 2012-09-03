package parsers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class ServiceParser {
	
	/**
	 * Private variable
	 */
	private UniqueParser parser;
	
	
	/**
	 * Constructor method. It receives the XML code and
	 * writes it into a file for parse it.
	 * @param XML
	 */
	public ServiceParser (String XML){
		this.parser = new UniqueParser(XML,"service");
	}
	
	
	/**
	 * This method returns the service name.
	 * @return
	 */
	public String getName(){
		try{
			return URLDecoder.decode(this.parser.getElement("name"), "UTF-8");
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
			return "-5";
		}
	}
	
	
	
	/**
	 * This method returns the service alias.
	 * @return
	 */
	public String getShortName(){
		try{
			return URLDecoder.decode(this.parser.getElement("shortName"), "UTF-8");
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
			return "-5";
		}
	}
	
	
	/**
	 * This method returns the service description.
	 * @return
	 */
	public String getDescription(){
		try{
			return URLDecoder.decode(this.parser.getElement("description"), "UTF-8");
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
			return "-5";
		}
	}
	
	
	/**
	 * 
	 * @return
	 */
	public String getURI(){
		try{
			return URLDecoder.decode(this.parser.getElement("URI"), "UTF-8");
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
			return "-5";
		}
	}
	
	
	/**
	 * This method returns the service creation Date.
	 * @return
	 */
	public String getCreationDate(){
		return this.parser.getElement("creationDate");
	}
	
	
	/**
	 * This method returns the service last modification Date.
	 * @return
	 */
	public String getModificationDate(){
		return this.parser.getElement("modificationDate");
	}
	
	
	/**
	 * This method returns the value of the Active camp.
	 * @return
	 */
	public String getActive(){
		return this.parser.getElement("isActive");
	}
	
	
	/**
	 * 
	 * @return the service identification.
	 */
	public String getID(){
		return this.parser.getElement("id");
	}
	
	
	/**
	 * 
	 * @return the uploader user identification.
	 */
	public String getUploaderUser(){
		return this.parser.getElement("uploaderUserID");
	}
	
	
	/**
	 * 
	 * @return the service XML document.
	 */
	public String getXML(){
		return this.parser.getElement("XML");
	}
	
	
	/**
	 * 
	 * @return the service state identification.
	 */
	public String getStateID(){
		return this.parser.getElement("stateID");
	}
	
	
	/**
	 * 
	 * @return the service invocation method identification.
	 */
	public String getInvocationMethod(){
		return this.parser.getElement("invocationMethodID");
	}
	
	
	/**
	 * 
	 * @return the service type identification.
	 */
	public String getTypeID(){
		return this.parser.getElement("typeID");
	}
	
	
	/**
	 * 
	 * @return the service category identification.
	 */
	public String getCategoryID(){
		return this.parser.getElement("categoryID");
	}
	
	/**
	 * This method returns the service tags.
	 * @return
	 */
	public String getTags(){
		try{
			return URLDecoder.decode(this.parser.getElement("tags"), "UTF-8");
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
			return "-5";
		}
	}
	
}
