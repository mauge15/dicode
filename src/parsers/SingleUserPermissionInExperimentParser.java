package parsers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;



public class SingleUserPermissionInExperimentParser {

	/**
	 * private variables
	 */
	private UniqueParser parser;
	
	
	/**
	 * constructor method of the class.
	 * @param xml
	 */
	public SingleUserPermissionInExperimentParser (String xml){
		this.parser = new UniqueParser(xml, "userPermissionInExperiment");
	}
	
	
	/**
	 * 
	 * @param n
	 * @return the permission identification in 
	 * a position given.
	 */
	public String getUserID(){
		return this.parser.getElement("userID");
	}
	
	
	/**
	 * 
	 * @param n
	 * @return the permission identification in a
	 * position given.
	 */
	public String getPermissionID(){
		if (this.parser.getElement("permissionID").equals(" "))
		{
			return "0";
		}
		else
		{
		return this.parser.getElement("permissionID");
		}
	}
	
	
	/**
	 * 
	 * @param n
	 * @return the permissionValue in a position given.
	 */
	public String getPermissionValue(){
		return this.parser.getElement("permissionValue");
	}
	
	
	/**
	 * 
	 * @param n
	 * @return the decoded description of the permission in
	 * a position given.
	 */
	public String getPermissionDescription(){
		try{
			return URLDecoder.decode(this.parser.getElement("permissionDescription"),"UTF-8");
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
			return "-5";
		}
		
	}
}
