package parsers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;


public class ListInvocationMethodsParser {
	
	/**
	 * private variables
	 */
	private ListsParser parser;
	
	
	/**
	 * This is the constructor method for the class.
	 * It receives the XML String code for parse it.
	 * @param XML
	 */
	public ListInvocationMethodsParser(String XML){
		this.parser = new ListsParser(XML, "invocationMethod");
	}
	
	
	/**
	 * This method return the number of invocation methods
	 * for one experiment in the xml code.
	 * @return
	 */
	public int getNumberMethods(){
		return this.parser.getNumberElements();
	}
	
	
	/**
	 * This method returns the id of a invocation method
	 * in a specific position inside the xml code.
	 * @return
	 */
	public String getID(int n){
		return this.parser.getElement(n, "id");
	}
	
	
	/**
	 * This method returns the name of a invocation method
	 * in a specific position inside the xml code.
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
	 * This method returns the description of the
	 * invocation method in a specific position 
	 * inside the xml code.
	 * @param n
	 * @return
	 */
	public String getDescription(int n){
		try{
			return URLDecoder.decode(this.parser.getElement(n,"description"), "UTF-8");
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
			return "-5";
		}
	}
}
