package parsers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class ListDataTypesParser {
	
	/**
	 * private variables
	 */
	private ListsParser parser;
	
	
	
	/**
	 * Constructor method. It receives the xml String
	 * for write it into a file.
	 * @param xml
	 */
	public ListDataTypesParser (String xml){
		this.parser = new ListsParser(xml,"dataType");
	}
	
	
	
	/**
	 * This method return the number of data types
	 * in the xml code.
	 * @return
	 */
	public int getNumberDataTypes(){
		return this.parser.getNumberElements();
	}
	
	
	
	/**
	 * This method returns the id of one data type in
	 * a position given inside the xml code.
	 * @param n
	 * @return
	 */
	public String getID(int n){
		return this.parser.getElement(n,"id");
	}
	
	
	
	/**
	 * This method returns the name of a data type
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
	 * data type in a specific position 
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
	
	
	
	/**
	 * This method returns the format of the data type 
	 * in a specific position inside the xml code.
	 * @param n
	 * @return
	 */
	public String getFormat(int n){
		return this.parser.getElement(n,"format");
	}
	
	
}
