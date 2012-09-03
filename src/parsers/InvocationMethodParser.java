package parsers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;


public class InvocationMethodParser {
	
	
	/**
	 * Private variables
	 */
	private UniqueParser parser;
	
	
	/**
	 * Constructor method.
	 * It receives the xml String for 
	 * write it into a file. the it parses the file.
	 * @param xml
	 */
	public InvocationMethodParser(String xml){
		this.parser = new UniqueParser(xml, "invocationMethod");
	}
	
		
	/**
	 * This method returns a String with the
	 * name of the invocation method.
	 * @return
	 */
	public String getName (){
		try{
			return URLDecoder.decode(this.parser.getElement("name"), "UTF-8");
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
			return "-5";
		}	}
	
	
	/**
	 * This method returns a String with the
	 * Description of the invocation method.
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
}
