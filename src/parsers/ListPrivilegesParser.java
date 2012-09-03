package parsers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class ListPrivilegesParser {
	/**
	 * private variable.
	 */
	private ListsParser parser;
	
	
	/**
	 * Constructor method. It receives a xml String for
	 * parse it.
	 * @param xml
	 */
	public ListPrivilegesParser(String xml){
		this.parser = new ListsParser(xml, "permission");
	}
	
	
	/**
	 * This method returns the Number of permissions
	 * in the system.	
	 * @return
	 */
	public int getNumberPrivileges(){
		return this.parser.getNumberElements();
	}
	
	
	/**
	 * This method returns the id of one permission
	 * in a position given in the xml code.	
	 * @param n
	 * @return
	 */
	public String getID(int n){
		return this.parser.getElement(n, "id");
	}
	
	
	/**
	 * This method returns the name of one permission
	 * in a position given in the xml code.
	 * @param n
	 * @return
	 */
	public String getName(int n){
		try{
			return URLDecoder.decode(this.parser.getElement(n, "name"), "UTF-8");
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
			return "-5";
		}	}
	
	
	/**
	 * This method returns the description of one permission
	 * in a position given in the xml code.	
	 * @param n
	 * @return
	 */
	public String getDescription(int n){
		try{
			return URLDecoder.decode(this.parser.getElement(n, "description"), "UTF-8");
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
			return "-5";
		}
	}
}
