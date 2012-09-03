package parsers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class OrderParamsParser {
	
	/**
	 * Private variable.
	 */
	private ListsParser parser;
	
	
	/**
	 * Constructor method. It receives the XML code and
	 * writes it into a file for parse it.
	 * @param XML
	 */
	public OrderParamsParser(String XML){
		this.parser = new ListsParser(XML, "param");
	}
	
	
	/**
	 * This method returns the parameters number for one
	 * service.
	 * @return
	 */
	public int getNumberParams(){
		return this.parser.getNumberElements();
	}
	
	
	/**
	 * This method returns the parameter name of one service
	 * in the position given in the xml code.
	 * @param n
	 * @return
	 */
	public String getName(int n){
		try{
			return URLDecoder.decode(this.parser.getElement(n, "name"), "UTF-8");
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
			return "-5";
		}
	}
	
	
	/**
	 * This method returns the parameter position in one service.
	 * @param n
	 * @return
	 */
	public String getPosition(int n){
		try{
			return URLDecoder.decode(this.parser.getElement(n, "position"), "UTF-8");
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
			return "-5";
		}
	}
	
	
	/**
	 * 
	 * @param n
	 * @return the parameter type identification in a
	 * position given.
	 */
	public String getParamTypeID(int n){
		return this.parser.getElement(n, "dataTypeID");
	}
	
	
	/**
	 * 
	 * @param n
	 * @return the parameter type identification in a
	 * position given.
	 */
	public String getID (int n){
		return this.parser.getElement(n, "id");
	}

}
