package dicode;

import java.util.Random;

import javax.ws.rs.core.MediaType;

import parsers.URLparser;
import parsers.UserServicesConfigurationParser;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UsersManager {
	
	
	private String URL = URLparser.giveUri()+":" + URLparser.givePort() +"/"+ URLparser.givePackage()+"/users/";
	
	/**
	 * This method makes the call to the validateUser REST Service.
	 * 
	 * @param username
	 * @param passwd
	 * @return
	 */
	
	public String validateUser(String username, String passwd){
		String url = this.URL + "user/validateUser/" + username +"/"+passwd;
		RESTclient client = new RESTclient(url);
		return client.invokeGet();
	}
	
	/**
	 * This method makes the call to the isUser REST Service.
	 * @param username
	 * @return
	 */
	public String isUser(String username){
		String url = this.URL + "user/isUser/" + username;
		RESTclient client = new RESTclient(url);
		return client.invokeGet();
	}
	
	/**
	 * This method makes the call to the isMail REST service.
	 * @param mail
	 * @return
	 */
	public String isMail (String mail){
		String url = this.URL + "user/isMail/" + mail;
		RESTclient client = new RESTclient(url);
		return client.invokeGet();
	}
	
	/**
	 * This method makes the call to the addUser REST service.
	 * @param fulname
	 * @param username
	 * @param passwd
	 * @param organization
	 * @param email
	 * @param status
	 * @return
	 */
	
	public String addUser (String fullName, String username,
			   String passwd, String organization,
			   String email, String status){
		try{
			String url;
			String fName = URLEncoder.encode(fullName, "UTF-8");
			String org = URLEncoder.encode(organization, "UTF-8");
			String pass = URLEncoder.encode(passwd, "UTF-8");

			url = this.URL + "user/" + fName +"/"+username + "/" + pass + "/" + email + "/" + status;
			url = url + "?organization="+org;
			RESTclient client = new RESTclient(url);
			return client.invokePost();
			}catch (UnsupportedEncodingException e){
				e.printStackTrace();
				return "";
				}
			}
	
	/**
	 * This method makes the call to the activateUser REST service.
	 * @param id
	 * @return
	 */
	
	public String activateUser (String id){
		String url = this.URL + "user/" + id +
					 "?state=2";
		RESTclient client = new RESTclient(url);
		return client.invokePut();
	}
	
	/**
	 * This method makes the call to the updateUser REST service.
	 * @param idUser
	 * @param fullName
	 * @param userName
	 * @param passwd
	 * @param organization
	 * @param email
	 * @param state
	 * @return
	 */

	public String updateUser (String idUser, String fullName,
			  String userName,
			  String organization, String email){
		try{
			String fName = URLEncoder.encode(fullName,"UTF-8");
			String org = URLEncoder.encode(organization, "UTF-8");
			String url = this.URL + "user/" + idUser + "?name="+fName +"&username="+userName+"&mail="+email+"&org="+org;
			RESTclient client = new RESTclient(url);
			return client.invokePut();
			} catch (UnsupportedEncodingException e){
				e.printStackTrace();
				return "";
				}
			}
	
	
	/**
	 * This method makes the call to the updateState REST service.
	 * @param idUser
	 * @param idState
	 * @return
	 */
	
	public String updateState (String idUser, String idState){
		String url = this.URL + "user/" + idUser
					 + "?state=" + idState;
		RESTclient client = new RESTclient(url);
		return client.invokePut();
	}
	
	/**
	 * This method makes the call to the listUsers REST service.
	 * @return
	 */
	
	public String listUsers (){
		String url = this.URL + "user/";
		RESTclient client = new RESTclient(url);
		client.setAcceptType("text/xml");
		return client.invokeGet();
	}
	
	/**
	 * This method makes the call to the user REST service.
	 * @param idUser
	 * @return
	 */
	
	public String user (String idUser){
		String url = this.URL + "user/" + idUser;
		RESTclient client = new RESTclient(url);
		client.setAcceptType(MediaType.TEXT_XML);
		return client.invokeGet();
	}
	
	/**
	 * This method makes the call to the listRoles REST service.
	 * @return
	 */
	
	public String listRoles (){
		String url = this.URL + "roles";
		RESTclient client = new RESTclient(url);
		client.setAcceptType(MediaType.TEXT_XML);
		return client.invokeGet();
	}
	
	/**
	 * This method makes the call to the rolesUser REST service.
	 * @param idUser
	 * @return
	 */
	
	public String rolesUser (String idUser){
		String url = this.URL + "roles/" + idUser;
		RESTclient client = new RESTclient(url);
		client.setAcceptType(MediaType.TEXT_XML);
		return client.invokeGet();		
	}
	
	
	/**
	 * This method makes the call to the listStates REST service.
	 * @return
	 */
	
	
	public String listStates (){
		String url = this.URL + "user/listStates";
		RESTclient client = new RESTclient(url, MediaType.TEXT_XML);
		return client.invokeGet();
	}
	
	
	/**
	 * This method makes the call to the listPrivileges REST service.
	 * @return
	 */
	
	public String listPrivileges(){
		String url = this.URL + "roles/listPrivileges";
		RESTclient client = new RESTclient(url, MediaType.TEXT_XML);
		return client.invokeGet();
	}
	
	/**
	 * This method generates a random String for use it as a password.
	 * @return
	 */
	public String generatePass(){
		
		try{
			Random r = new Random();
			String token = Long.toString(Math.abs(r.nextLong()), 36);
			return token;
			
		}catch (Exception e){
			e.printStackTrace();
			return "fallo";
		}
	}
	
	/**
	 * This method makes the call to the deleteAllRoles REST service.
	 * @param userID
	 * @return
	 */
	
	public String deleteAllRoles(String userID){
		String url = this.URL + "roles/" + userID;
		RESTclient client = new RESTclient(url);
		return client.invokeDelete();
	}

	/**
	 * This method makes the call to the deleteRol REST service.
	 * @param userID
	 * @param rolID
	 * @return
	 */
	
	public String deleteRol(String userID, String rolID){
		String url = this.URL + "roles/" + userID + "/"+
					rolID;
		RESTclient client = new RESTclient(url);
		return client.invokeDelete();
	}
	
	
	/**
	 * This method makes the call to the deleteUser REST service.
	 * @param userID
	 * @return
	 */
	
	public String deleteUser (String userID){
		String url = this.URL + "user/" + userID;
		RESTclient client = new RESTclient(url);
		return client.invokeDelete();
	}
	
	/**
	 * This method makes the call to the updatePass REST service.
	 * @param userID
	 * @param pass
	 * @return
	 */
	
	public String updatePass (String userID, String pass){
		try{
			String passwd = URLEncoder.encode(pass, "UTF-8");
		
		String url = this.URL + "user/" + userID + 
					"?pass="+passwd;
		RESTclient client = new RESTclient(url);
		return client.invokePut();
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
			return "-6";
		}
	}
	
	/**
	 * This method makes the call to the userState REST service.
	 * @param userID
	 * @return
	 */
	
	public String userState (String userID){
		String url = this.URL + "user/userState/" + userID;
		RESTclient client = new RESTclient(url, MediaType.TEXT_XML);
		return client.invokeGet();
	}
	
	
	/**
	 * This method makes the call to the addConfirmation REST service.
	 * @param userID
	 * @param key
	 * @return
	 */	
	
	public String addConfirmation (String userID,
			   String key){
		String url = this.URL + "confirmation/" + userID +"/" + key;
		RESTclient client = new RESTclient(url);
		return client.invokePost();
		}
	
	
	/**
	 * This method makes the call to the deleteConfrimation REST service.
	 * @param userID
	 * @return
	 */
	
	public String deleteConfirmation (String userID){
		String url = this.URL + "confirmation/" + userID;
		RESTclient client = new RESTclient(url);
		return client.invokeDelete();
	}
	
	
	
	/**
	 * This method makes the call to the validateConfirmation REST service.
	 * @param userID
	 * @param key
	 * @return
	 */
	
	public String validateConfirmation (String userID,
			String key){
		String url = this.URL + "confirmation/validateConfirmation/" + userID +"/" + key;
		RESTclient client = new RESTclient(url);
		return client.invokeGet();
		}
	
	
	
	public String userWidgetConfiguration (String userID, String experimentID){
		String url = this.URL + "user/userWidgetConfiguration/" + userID + "/" + experimentID;
		RESTclient client = new RESTclient(url,MediaType.TEXT_XML);
		return client.invokeGet();
	}

	
	public String getNewWidgetConfiguration(String userID, String experimentID)
	{
		 String url = this.URL + "user/userWidgetConfiguration/" + userID +
         "/" + experimentID;
		 System.out.println("Consulting "+url);
		 RESTclient client = new RESTclient(url, MediaType.TEXT_XML);
		 String xml = client.invokeGet();
		 UserServicesConfigurationParser parser = new UserServicesConfigurationParser(xml);
		 int total = parser.getNumberUserServices();
		 int maxRow=0;
		 int chosenCol=1;
		 int maxCol=-5;
		 int col=0;
		 int row=0;
		 for (int i=0;i<=(total-1);i++)
		 {
			 
			 System.out.println("calling get ColID -"+parser.getColID(i)+"-");
			 System.out.println("calling get  Row -"+parser.getRow(i)+"-");
			 
			 try{
			 col = Integer.parseInt(parser.getColID(i).trim());
			 row = Integer.parseInt(parser.getRow(i).trim());
			 
			 System.out.println("everything went ok");
			 }
			 catch (Exception e)
			 {
				 System.out.println(e);
			 }
			 
			//Maxima fila...y obtenemos el numero de la columna	 
		     if (row>maxRow)
			 {
				 maxRow=row;
				 maxCol = col;
			 }
			 
		 }
		 if (maxCol==1)
		 {chosenCol=2;}
		 else if (maxCol==2)
		 {chosenCol=1;}
		 
		 int maxRowMin=0;
		 for (int i=0;i<=(total-1);i++)
		 {
			 
			 col = Integer.parseInt(parser.getColID(i).trim());
			 row = Integer.parseInt(parser.getRow(i).trim());
			 if (col==chosenCol)
			 {			 
			 //Maxima fila...y obtenemos el numero de la columna	 
		     if (row>maxRowMin)
			 {
				 maxRowMin=row;
			 }
			 }
			 
		 }
		 maxRowMin = maxRowMin+1;
		 String pos = maxRowMin+","+chosenCol; 
		 return pos;
	}


	public String saveUserWidgetConfiguration (String userID, String serviceID, String experimentID,  String colID, String row)
	{
		String url = this.URL + "/users/saveUserWidgetConfiguration/" + userID +
	                                 "/" + serviceID + "/" + experimentID + "/" + colID +
	                                 "/" + row;
		RESTclient client = new RESTclient(url);
		String res =  client.invokePost();
	    System.out.println("Save User Widget Config "+res);
	    return res;
	}
	
	public String updateUserWidgetConfiguration (String userID, String serviceID,String expID, String colID, String row)
	{
		String url = this.URL + "/users/experimentConfiguration/" + userID + "/"+serviceID+"/"+expID+"/"+colID+"/"+row;
		RESTclient client = new RESTclient(url);
		return client.invokePut();
	}
	
	
	public String deleteUserWidgetConfiguration (String userID, String expID, String srvID)
	{
		String url = this.URL + "/users/deleteUserWidgetConfiguration/" + userID+"/"+expID+"/"+srvID;
		RESTclient client = new RESTclient(url);
		return client.invokePost();
	}
}
