package parsers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class ListServicesParser {
	
	/**
	 * Private Variable
	 */
	ListsParser parser;
	
	
	/**
	 * Constructor method. It receives the XML String code
	 * and writes it into a file for parser it.
	 * @param XML
	 */
	public ListServicesParser (String XML){
		this.parser = new ListsParser(XML,"service");
	}
	
	
	/**
	 * This method return the number of services in 
	 * the system.
	 * @return
	 */
	public int getNumberServices (){
		return this.parser.getNumberElements();
	}
	
	
	/**
	 * This method returns the id of one service
	 * in a position given.
	 * @param n
	 * @return
	 */
	public String getID(int n){
		return this.parser.getElement(n,"id");
	}
	
	
	/**
	 * This method returns the name of one service
	 * in a position given.
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
}
