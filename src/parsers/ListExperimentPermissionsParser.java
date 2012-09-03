package parsers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class ListExperimentPermissionsParser {
	
	/**
	 * private variables
	 */
	private ListsParser parser;
	
	
	/**
	 * Constructor method of the class.
	 * @param xml
	 */
	public ListExperimentPermissionsParser (String xml){
		this.parser = new ListsParser(xml, "experimentPermission");
	}
	
	
	/**
	 * 
	 * @return the number of experiment permissions.
	 */
	public int getNumberExperimentPermissions(){
		return this.parser.getNumberElements();
	}
	
	
	/**
	 * 
	 * @param n
	 * @return the identification of one permission in the list.
	 */
	public String getID(int n){
		return this.parser.getElement(n,"id");
	}
	
	
	/**
	 * 
	 * @param n
	 * @return the value of the experiment permission.
	 */
	public String getValue(int n){
		return this.parser.getElement(n,"value");
	}
	
	
	/**
	 * 
	 * @param n
	 * @return The decoded experiment permission.
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
