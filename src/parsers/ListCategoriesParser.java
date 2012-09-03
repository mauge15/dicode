package parsers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;


public class ListCategoriesParser {
	
	/**
	 * Private variables.
	 */
	private ListsParser parser;	
	
	
	/**
	 * Constructor Method. It receives the xml string for
	 * parse it.
	 * @param xml
	 */
	public ListCategoriesParser (String xml){
		this.parser = new ListsParser(xml,"serviceCategory");
	}
	
		
	/**
	 * This method returns the number of service categories.
	 * @return
	 */
	public int getNumberCategories(){
		return this.parser.getNumberElements();
	}
	
	
	/**
	 * This method returns the Id of one category n
	 * a specific position inside the xml code.
	 * @param n
	 * @return
	 */
	public String getID(int n){
		return this.parser.getElement(n,"id");
	}
	
	
	/**
	 * This method returns the Name of one category in
	 * a specific position inside the xml code.
	 *  
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
	 * This method returns the description of one 
	 * service category in a specific position inside
	 * the xml code.
 	 * @param n
	 * @return
	 */
	public String getDescription(int n){
		try{
			return URLDecoder.decode(this.parser.getElement(n,"description"),"UTF-8");
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
			return "-5";
		}
	}
	
	
}
