package parsers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class DataTypeParser {
	
	/**
	 * Private Variables.
	 */
	private UniqueParser parser;
	
	
	/**
	 * Constructor method.
	 * It receives the xml String for 
	 * write it into a file. the it parses the file.
	 * @param xml
	 */
	public DataTypeParser(String xml){
		this.parser = new UniqueParser(xml, "dataType");
	}
	
		
	/**
	 * This method returns a String with the 
	 * name of the type.
	 * @return
	 */
	public String getName(){
		try{
			return URLDecoder.decode(this.parser.getElement("name"), "UTF-8");
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
			return "-5";
		}	}
	
	
	/**
	 * This method returns a String with the
	 * Description of the type.
	 * @return
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
	 * This method returns a String with the
	 * format of the type.
	 * @return
	 */
	public String getFormat(){
		return this.parser.getElement("format");
	}
}
