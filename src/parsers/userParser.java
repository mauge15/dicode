package parsers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;



public class userParser {
	
	
	private UniqueParser parser;
	
	
	/**Constructor method
	 * It receives the XML String to write it into a file.
	 * @param xml
	 */
	public userParser(String xml){
		this.parser = new UniqueParser(xml, "user");
	}
	
	
	
	/** It returns the user's full name
	 * 
	 * @return
	 */
	public String getFullName(){
		try{
			return URLDecoder.decode(this.parser.getElement("fullName"), "UTF-8");
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
			return "-5";
		}
	}
	
	
	/** It returns the user's username
	 * 
	 * @return
	 */
	public String getUserName(){
		try{
			return URLDecoder.decode(this.parser.getElement("userName"), "UTF-8");
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
			return "-5";
		}
				
	}
	
	
	/** It returns the user's Organization
	 * 
	 * @return
	 */
	public String getOrganization(){
		try{
			return URLDecoder.decode(this.parser.getElement("organization"), "UTF-8");
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
			return "-5";
		}
	}
	
	
	/** It returns the user's eMail
	 * 
	 * @return
	 */
	public String geteMail(){
		try{
			return URLDecoder.decode(this.parser.getElement("email"), "UTF-8");
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
			return "-5";
		}		
	}
	

}
