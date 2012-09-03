package dicode;

import javax.ws.rs.core.MediaType;

import parsers.URLparser;

public class manager {
	
	private String URL = URLparser.giveUri()+":" + URLparser.givePort() +"/"+ URLparser.givePackage();
	
	//This method makes the call to a validateUser REST Service.
	public String validateUser(String username, String passwd){
		String url = this.URL + "/users/validateUser/" + username +"/"+passwd;
		RESTclient client = new RESTclient(url);
		return client.invokePost();
	}
	
	//This method makes the call to a isUser REST Service.
	public String isUser(String username){
		String url = this.URL + "/users/isUser/" + username;
		RESTclient client = new RESTclient(url);
		return client.invokeGet();
	}
	
	public String isMail (String mail){
		String url = this.URL + "/users/isMail/" + mail;
		RESTclient client = new RESTclient(url);
		return client.invokeGet();
	}
	
	public String addUser (String fulname, String username,
						   String passwd, String organization,
						   String email, String status){
		String url = this.URL + "/users/addUser/" + fulname +"/"+
					 username + "/" + passwd + "/" + organization +
					 "/" + email + "/" + status;
		RESTclient client = new RESTclient(url);
		return client.invokePost();
	}
	
	public String activateUser (String id){
		String url = this.URL + "/users/isMail/" + id;
		RESTclient client = new RESTclient(url);
		return client.invokeGet();
	}
	
	public String updateUser (String idUser, String fullName,
							  String userName, String passwd,
							  String organization, String email,
							  String state){
		String url = this.URL + "/users/updateUser" + idUser + "/"+
					 fullName +"/"+ userName + "/" + passwd + "/" +
					 organization + "/" + email + "/" + state;
		RESTclient client = new RESTclient(url);
		return client.invokePost();
	}
	
	public String updateState (String idUser, String idState){
		String url = this.URL + "/users/updateState/" + idUser
					 + "/" + idState;
		RESTclient client = new RESTclient(url);
		return client.invokeGet();
	}
	
	public String listUsers (){
		String url = this.URL + "/users/listUsers/";
		RESTclient client = new RESTclient(url);
		client.setAcceptType("MediaType.TEXT_XML");
		return client.invokeGet();
	}
	
	public String user (String idUser){
		String url = this.URL + "/users/user/" + idUser;
		RESTclient client = new RESTclient(url);
		client.setAcceptType(MediaType.TEXT_XML);
		return client.invokeGet();
	}
	
	public String listRoles (){
		String url = this.URL + "/users/listRoles";
		RESTclient client = new RESTclient(url);
		client.setAcceptType(MediaType.TEXT_XML);
		return client.invokeGet();
	}
	
	public String rolesUser (String idUser){
		String url = this.URL + "/users/rolesUsers";
		RESTclient client = new RESTclient(url);
		client.setAcceptType(MediaType.TEXT_XML);
		return client.invokeGet();		
	}
	
	public String listStates (){
		String url = this.URL + "/users/listStates";
		RESTclient client = new RESTclient(url, MediaType.TEXT_XML);
		return client.invokeGet();
	}
	
	public String listPrivileges(){
		String url = this.URL + "/users/listPrivileges";
		RESTclient client = new RESTclient(url, MediaType.TEXT_XML);
		return client.invokeGet();
	}

}