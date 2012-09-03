package parsers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class ListReturnTypesParser {
	/**
	 * private variable.
	 */
	private ListsParser parser;
	
	
	/**
	 * Constructor method of the class.
	 * @param xml
	 */
	public ListReturnTypesParser(String xml){
		this.parser = new ListsParser(xml, "returnType");
	}
	
	
	/**
	 * 
	 * @return The number of the return types in the system.
	 */
	public int getNumberReturnTypes(){
		return this.parser.getNumberElements();
	}
	
	
	/**
	 * 
	 * @param n
	 * @return The name of one return type in the list.
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
	 * 
	 * @param n
	 * @return The data type identification for one 
	 * return type in the list
	 */
	public String getDataTypeID(int n){
		return this.parser.getElement(n, "dataTypeID");
	}
}
