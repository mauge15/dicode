package parsers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class ListStatesParser {
	
	/**
	 * Private Variable.
	 */
	private ListsParser parser;
	
	
	/**
	 * Constructor method. It receives the XML code
	 * and writes it into a file for parse it.
	 * @param XML
	 */
	public ListStatesParser (String XML){
		this.parser = new ListsParser(XML,"userState");
	}
	
	
	/**
	 * This method returns the number of the user states
	 * in the system.
	 * @return
	 */
	public int getNumberUserStates(){
		return this.parser.getNumberElements();
	}
	
	
	/**
	 * This method returns one unser state id in a position
	 * given in the xml code.
	 * @param n
	 * @return
	 */
	public String getID (int n){
		return this.parser.getElement(n, "id");
	}
	
	
	/**
	 * This method returns one user state name in a position
	 * given in the xml code.
	 * @param n
	 * @return
	 */
	public String getName (int n){
		try{
			return URLDecoder.decode(this.parser.getElement(n, "name"), "UTF-8");
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
			return "-5";
		}
	}
	
	
	/**
	 * This method returns one user state description in 
	 * a position given in the xml code.
	 * @param n
	 * @return
	 */
	public String getDescription (int n){
		try{
			return URLDecoder.decode(this.parser.getElement(n, "description"),"UTF-8");
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
			return "-5";
		}
	}

}
