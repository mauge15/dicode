package parsers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class UserStateParser {
	
	/**
	 * Private Variable
	 */
	private UniqueParser parser;
	
	
	/**
	 * Constructor Method. It receives the XML String code
	 * and writes it into a file for parse it.
	 * @param XML
	 */
	public UserStateParser (String XML){
		this.parser = new UniqueParser(XML,"userState");
	}
	
	
	/**
	 * This method return the user state identification.
	 * @return
	 */
	public String getID(){
		return this.parser.getElement("id");
	}
	
	
	/**
	 * This method returns the user state name.
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
	 * this method returns the user state description.
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
