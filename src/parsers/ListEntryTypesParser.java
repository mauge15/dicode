package parsers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;



public class ListEntryTypesParser {
	
	/**
	 * private variables
	 */
	private ListsParser parser;
	
	
	/**
	 * Constructor method. It receives the xml String
	 * for write it into a file.
	 * @param xml
	 */
	public ListEntryTypesParser (String xml){
		this.parser = new ListsParser(xml, "entryType");
	}
	
	
	/**
	 * This method return the number of entry types
	 * in the xml code.
	 * @return
	 */
	public int getNumberEntryTypes(){
		return this.parser.getNumberElements();
	}
	
	
	/**
	 * This method returns the name of a entry type
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
	 * entry type in a specific position 
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
