package parsers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class DataTypeDataParser {

	/**
	 * Private variable
	 */
	private UniqueParser parser;
	
	
	/**
	 * Constructor Method of the class.
	 * @param XML
	 */
	public DataTypeDataParser (String XML){
		this.parser = new UniqueParser(XML,"dataType");
	}
	
	/**
	 * 
	 * @return The Data Type name.
	 */
	public String getName(){
		try{
			return URLDecoder.decode(this.parser.getElement("name"), "UTF-8");
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
			return "-5";
		}	}
	
	
	/**
	 * 
	 * @return The decoded description of the data type.
	 */
	public String getDescription(){
		try{
			return URLDecoder.decode(this.parser.getElement("description"),"UTF-8");
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
			return "-5";			
		}
		
	}
}
