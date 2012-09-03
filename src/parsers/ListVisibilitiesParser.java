package parsers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class ListVisibilitiesParser {
	
	/**
	 * Private Variable.
	 */
	private ListsParser parser;
	
	/**
	 * Constructor method. It receives the XML code and
	 * writes it into a file for parse it.
	 * @param XML
	 */
	public ListVisibilitiesParser (String XML){
		this.parser = new ListsParser(XML,"experimentVisibility");
	}
	
	
	/**
	 * 
	 * @return the number of visibilities registered in
	 * the system.
	 */
	public int getNumberVisibilities(){
		return this.parser.getNumberElements();
	}
	
	
	/**
	 * 
	 * @param n
	 * @return the identification for one visibility in the
	 * position given.
	 */
	public String getID(int n){
		return this.parser.getElement(n, "id");
	}
	
	
	/**
	 * 
	 * @param n
	 * @return the name for one visibility in the
	 * position given.
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
	 * 
	 * @param n
	 * @return the decoded description of one visibility in the
	 * position given.
	 */
	public String getDescription(int n){
		try{
			return URLDecoder.decode(this.parser.getElement(n, "description"),"UTF-8");
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
			return "-5";
		}
		
	}
	

}
