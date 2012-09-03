package parsers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class ParamTypeParser {
	
	/**
	 * Private Variable
	 */
	private UniqueParser parser;
	
	
	/**
	 * Constructor Method. It receives the XML code and
	 * writes it into a file for parse it.
	 * @param XML
	 */
	public ParamTypeParser (String XML){
		this.parser = new UniqueParser(XML, "paramType");
	}
	
	
	/**
	 * This method returns the parameter type name.
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
	 * This method returns the parameter type description.
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

}
