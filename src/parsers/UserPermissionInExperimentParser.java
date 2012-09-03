package parsers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class UserPermissionInExperimentParser {

	/**
	 * private variables
	 */
	private ListsParser parser;
	
	
	/**
	 * constructor method of the class.
	 * @param xml
	 */
	public UserPermissionInExperimentParser (String xml){
		this.parser = new ListsParser(xml, "userPermissionInExperiment");
	}
	
	
	/**
	 * 
	 * @return the number of permissions in experiment.
	 */
	public int getNumberExperimentPermissions(){
		return this.parser.getNumberElements();
	}
	
	
	/**
	 * 
	 * @param n
	 * @return the permission identification in 
	 * a position given.
	 */
	public String getUserID(int n){
		return this.parser.getElement(n,"userID");
	}
	
	
	/**
	 * 
	 * @param n
	 * @return the permission identification in a
	 * position given.
	 */
	public String getPermissionID(int n){
		return this.parser.getElement(n,"permissionID");
	}
	
	
	/**
	 * 
	 * @param n
	 * @return the permissionValue in a position given.
	 */
	public String getPermissionValue(int n){
		return this.parser.getElement(n, "permissionValue");
	}
	
	
	/**
	 * 
	 * @param n
	 * @return the decoded description of the permission in
	 * a position given.
	 */
	public String getPermissionDescription(int n){
		try{
			return URLDecoder.decode(this.parser.getElement(n,"permissionDescription"),"UTF-8");
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
			return "-5";
		}
		
	}
}
