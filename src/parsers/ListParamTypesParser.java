package parsers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class ListParamTypesParser {
	/**
	 * private variables
	 */
	private ListsParser parser;
	
	
	/**
	 * Constructor method. It receives the xml String
	 * for write it into a file.
	 * @param xml
	 */
	public ListParamTypesParser (String xml){
		this.parser = new ListsParser(xml, "paramType");
	}
	
	
	/**
	 * This method return the number of invocation methods
	 * for one experiment in the xml code.
	 * @return
	 */
	public int getNumberParamTypes(){
		return this.parser.getNumberElements();
	}
	
	
	/**
	 * This method returns the id of one invocation method
	 * inside a position given in the xml code.
	 * @param n
	 * @return
	 */
	public String getID(int n){
		return this.parser.getElement(n, "id");
	}
	
	
	
	/**
	 * This method returns the name of the parameter type
	 * in a specific position in the xml code.
	 * @param n
	 * @return
	 */
	public String getName(int n){
		try{
			return URLDecoder.decode(this.parser.getElement(n,"name"), "UTF-8");
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
			return "-5";
		}
	}
	
	
	/**
	 * This method returns the description of the parameter type
	 * in a specific position in the xml code.
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
