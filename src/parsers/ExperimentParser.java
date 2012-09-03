package parsers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class ExperimentParser {

	
	private UniqueParser parser;
	
	/**
	 * This is the constructor method of the class.
	 * @param xml
	 */
	public ExperimentParser(String xml){
		this.parser = new UniqueParser(xml,"experiment");
	}
	
	
	/**
	 * 
	 * @return The String with the experiment name.
	 */
	public String getName(){
		try{
			return URLDecoder.decode(this.parser.getElement("name"),"UTF-8");
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
			return "-5";
		}
		
	}
	
	
	/**
	 * 
	 * @return The String with the experiment description decoded.
	 */
	public String getDescription(){
		try{
			return URLDecoder.decode(this.parser.getElement("description"),"UTF-8");
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
			return "-5";
		}
		
	}
	
	
	/**
	 * 
	 * @return The String with the experiment visibility id.
	 */
	public String getVisibilityID(){
		return this.parser.getElement("visibilityID");
	}
	
	
	/**
	 * 
	 * @return The String with the experiment creator user id.
	 */
	public String getCreatorID(){
		return this.parser.getElement("creatorID");
	}
	
	
	/**
	 * 
	 * @return The String with the experiment creation date.
	 */
	public String getCreationDate(){
		return this.parser.getElement("creationDate");
	}
	
	/**
	 * 
	 * @return The String with the experiment creation date.
	 */
	public String getGroup(){
		return this.parser.getElement("group");
	}
}
