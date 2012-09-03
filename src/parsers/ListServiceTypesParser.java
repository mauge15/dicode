package parsers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class ListServiceTypesParser {
	/**
	 * Private Variable
	 */
	ListsParser parser;
	
	
	/**
	 * Constructor method. It receives the XML String code
	 * and writes it into a file for parser it.
	 * @param XML
	 */
	public ListServiceTypesParser (String XML){
		this.parser = new ListsParser(XML,"serviceType");
	}
	
	
	/**
	 * This method return the number of service types in 
	 * the system.
	 * @return
	 */
	public int getNumberServiceTypes (){
		return this.parser.getNumberElements();
	}
	
	
	/**
	 * This method returns the id of one service type
	 * in a position given.
	 * @param n
	 * @return
	 */
	public String getID(int n){
		return this.parser.getElement(n,"id");
	}
	
	
	/**
	 * This method returns the name of one service type
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
	
	
	/**
	 * 
	 * @param n
	 * @return The decoded description for one service type in
	 * the position given.
	 */
	public String getDescrpiton(int n){
		try{
			return URLDecoder.decode(this.parser.getElement(n, "description"),"UTF-8");
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
			return "-5";
		}
		
	}
}
