package parsers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;



public class InvokeServiceParser {
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
	public InvokeServiceParser(String xml){
		this.parser = new UniqueParser(xml, "invokeInformation");
	}
	
		
	/**
	 * This method returns a String with the 
	 * name of the type.
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
	 * This method returns a String with the
	 * Description of the type.
	 * @return
	 */
	
	public String getAcceptType(){
		return this.parser.getElement("acceptType");
	}
	
	
	/**
	 * This method returns a String with the
	 * format of the type.
	 * @return
	 */
	public String getInvocationMethod(){
		return this.parser.getElement("invocationMethod");
	}

}
