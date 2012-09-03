package dicode;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import javax.ws.rs.core.MediaType;

public class ExperimentsManager1 {
	
	
	private String URL = "http://hodgkin.dia.fi.upm.es:8080/DICODE-REST/experiments/";
	
	/**
	 * This method makes the call to the listEntryTypes REST service.
	 * @return
	 */
	public String listEntryTypes(){
		String url = this.URL + "listEntryTypes";
		RESTclient client = new RESTclient(url,MediaType.TEXT_XML);
		return client.invokeGet();
	}
	
	
	/**
	 * This method makes the call to the orderEntries REST service.
	 * @param experimentID
	 * @return
	 */
	public String orderEntries (String experimentID){
		String url = this.URL + "orderEntries/" +
					experimentID;
		RESTclient client = new RESTclient(url,MediaType.TEXT_XML);
		return client.invokeGet();
	}
	
	
	/**
	 * This method makes the call to the orderEntriesInType REST service.
	 * @param experimentID
	 * @param entryTypeID
	 * @return
	 */
	public String orderEntriesInType (String experimentID,
									  String entryTypeID){
		String url = this.URL + "orderEntriesInType/" + 
					experimentID + "/" + entryTypeID;
		RESTclient client = new RESTclient(url, MediaType.TEXT_XML);
		return client.invokeGet();
	}
	
	
	/**
	 * This method makes the call to the addExperiment REST service.
	 * @param name
	 * @param description
	 * @param creationTimeStmp
	 * @param modificationTimeStamp
	 * @param creatorUser
	 * @param visibility
	 * @param logBook
	 * @return
	 */
	public String addExperiment (String name, String description,
								 String creatorUser, String visibility,
								 String logBook, String tags,
								 String domain){
		try{
			
			/* These sentences are for take care of the blank spaces
			 * in the url request.*/
		
			String nm = URLEncoder.encode(name, "UTF-8");
			String desc = URLEncoder.encode(description, "UTF-8");
			String tgs = URLEncoder.encode(tags, "UTF-8");
			String dom = URLEncoder.encode(domain, "UTF-8");
			String url = this.URL + "experiment/" +
						nm + "/" + desc + "/" + 
						creatorUser + "/" + 
						visibility + "/" + 
						logBook + "/" +
						dom + "?tags="+tgs;
			RESTclient client = new RESTclient(url);
			return client.invokePost();
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
			return "";
		}
	}
	
	
	/**
	 * This method makes the call to the orderAnswers REST service.
	 * @param entryID
	 * @return
	 */
	public String orderAnswers (String entryID){
		String url = this.URL + "orderAnswers/" +
					 entryID;
		RESTclient client = new RESTclient(url, MediaType.TEXT_XML);
		return client.invokeGet();
	}
	
	
	/**
	 * This method makes the call to the addLogEntry REST service.
	 * @param timeStamp
	 * @param message
	 * @param answers
	 * @param logBook
	 * @param authorID
	 * @param parentEntryID
	 * @param typeID
	 * @return
	 */
	public String addLogEntry (String timeStamp, String message,
							   String answers, String logBook,
							   String authorID, String parentEntryID,
							   String typeID){
		try{
			/* This sentence is for take care of the blank spaces
			 * in the url request.*/
			String text = URLEncoder.encode(message, "UTF-8");
			String url = this.URL + "addLogEntry/" + timeStamp + "/" +
						text + "/" + answers + "/" + logBook + "/" + authorID + "/" +
						parentEntryID + "/" + typeID;
			RESTclient client = new RESTclient(url);
			return client.invokeGet();
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
			return "";
		}
	}
	
	
	/**
	 * This method makes the call to the experiment REST service.
	 * @param id
	 * @return XML String with the experiment relevant information.
	 */	
	public String experiment (String id){
		String url = this.URL + "experiment/" + id;
		RESTclient client = new RESTclient(url,MediaType.TEXT_XML);
		return client.invokeGet();
	}
	
	
	/**
	 * This method makes the call to the addLogBook REST service.	
	 * @return "1" if the logBook has been added to the system or
	 * 			"0" otherwise.
	 */
	public String addLogBook (){
		String url = this.URL + "addLogBook";
		RESTclient client = new RESTclient (url);
		return client.invokePost();
	}
	
	
	/**
	 * This method makes the call to the listExperiments REST service.
	 * @return XML String with all the experiment identifications registered
	 * 			in the system.
	 */
	public String listExperiments (){
		String url = this.URL + "listExperiments";
		RESTclient client = new RESTclient (url, MediaType.TEXT_XML);
		return client.invokeGet();
	}
	
	
	/**
	 * This method makes the call to the listVisibilities REST service.
	 * @return XML String with all the experiment visibilities.
	 */
	public String listVisibilities (){
		String url = this.URL + "listVisibilities";
		RESTclient client = new RESTclient (url, MediaType.TEXT_XML);
		return client.invokeGet();
	}
	
	
	/**
	 * This method makes the call to the userPermissionInExperiment REST service.
	 * @param experimentID
	 * @return XML String with all the users who have permissions in the experiment.
	 */
	public String userPermissionInExperiment (String experimentID){
		String url = this.URL + "userPermissionInExperiment/" + experimentID;
		RESTclient client = new RESTclient (url, MediaType.TEXT_XML);
		return client.invokeGet();
	}
	
	/**
	 * This method makes the call to the userPermissionInExperiment REST service.
	 * Recently created 30/05/2012 
	 * @param experimentID
	 * @return XML String with all the users who have permissions in the experiment.
	 */
	public String userPermissionInExperiment (String userID,
			String experimentID) {
		String url = "http://maimonides.dia.fi.upm.es:8080/DICODE-REST2/experiments/userPermissionInExperiment/" + userID + "/" +
		experimentID;
		RESTclient client = new RESTclient (url, MediaType.TEXT_XML);
		return client.invokeGet();
	}
	
	
	/**
	 * This method makes the call to the listExperimentPermissions REST service.
	 * @return XML String with all the experiment permissions in the system.
	 */
	public String listExperimentPermissions (){
		String url = this.URL+ "listExperimentPermissions";
		RESTclient client = new RESTclient(url, MediaType.TEXT_XML);
		return client.invokeGet();
	}
	
	
	/**
	 * This method makes the call to the addUserPermissionInExperiment REST service.
	 * @param userID
	 * @param experimentID
	 * @param experimentPermissionID
	 * @return "1" if the permission has been added or "0" otherwise.
	 */
	public String addUserPermissionInExperiment(String userID,
										String experimentID,
										String experimentPermissionID){
		String url = this.URL + "userPermissionInExperiment/" + userID + "/" +
								experimentID + "/" + experimentPermissionID;
		RESTclient client = new RESTclient(url);
		return client.invokePost();
	}
	
	
	/**
	 * This method makes the call to the updateUserPermissionInExperiment REST service.
	 * Created 31/05/2012
	 * @param userID
	 * @param experimentID
	 * @param experimentPermissionID
	 * @return "1" if the permission has been correctly updated or "0" otherwise.
	 */
	public String updateUserPermissionInExperiment (String userID,
            String experimentID, String permissionID){
		String url = this.URL + "updateUserPermissionInExperiment/"+userID+
		"/"+experimentID+"/"+permissionID;
		RESTclient client = new RESTclient(url);
		return client.invokePost();
		}
	
	
	
	/**
	 * This method makes the call to the addWorkSpace REST service.
	 * @param experimentID
	 * @param code
	 * @return "1" if the Workspace has been added or "0" otherwise.
	 */
	public String addWorkspace (String experimentID,
								String cView,
								String fView,
								String aView,
								String name,
								String description){
		try{
			String nm = URLEncoder.encode(name, "UTF-8");
			String desc = URLEncoder.encode(description, "UTF-8");
			String url = this.URL + "addWorkspace/" + experimentID +
	  		 "/"+ cView + "/" + fView + "/" + aView +
	  		 "/"+ nm + "/" + desc;
			RESTclient client = new RESTclient (url);
			return client.invokePost();
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
			return "-20";
		}
		
	}
	
	/**
	 * 
	 * @param experimentID
	 * @param newName
	 * @return
	 */
	public String modifyExperimentName (String experimentID,
									    String newName){
		try {
			String newNm = URLEncoder.encode(newName, "UTF-8");
			String url = this.URL + "modifyExperimentName/" + experimentID + "/" +
			 newNm;
			RESTclient client = new RESTclient(url);
			return client.invokePost();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		
	}
	
	
	/**
	 * 
	 * @param experimentID
	 * @param newDescription
	 * @return
	 */
	public String modifyExperimentDescription (String experimentID, 
											   String newDescription){
		try{
			String newDesc = URLEncoder.encode(newDescription, "UTF-8");
			String url = this.URL + "modifyExperimentDescription/" + experimentID +
							"/" + newDesc;
			RESTclient client = new RESTclient(url);
			return client.invokePost();
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
			return "";
		}
	}
	
	public String servicesInExperiment (String experimentID){
		String url = this.URL + "servicesInExperiment/" + experimentID;
		RESTclient client = new RESTclient(url, MediaType.TEXT_XML);
		return client.invokeGet();
	}
	
	
	public String colName (String colID){
		String url = this.URL + "colName/" + colID;
		RESTclient client = new RESTclient(url, MediaType.TEXT_XML);
		return client.invokeGet();
	}
	
	
	public String changeServiceInExperiment (String experimentID,
											 String serviceID,
											 String row,
											 String colID){
		String url = this.URL + "changeServiceInExperiment/" + experimentID +
					"/" + serviceID + "/" + row + "/" + colID;
		RESTclient client = new RESTclient(url);
		return client.invokePost();
	}
	
	
	public String addServiceInExperiment (String experimentID,
										  String serviceID,
										  String row,
										  String colID){
		
		String url = this.URL + "addServiceInExperiment/" + experimentID +
					"/" + serviceID + "/" + row + "/" + colID;
		RESTclient client = new RESTclient(url);
		return client.invokePost();
	}
	
	
	public String deleteServiceInExperiment (String experimentID,
											 String serviceID){
		String url = this.URL + "deleteServiceInExperiment/" + experimentID + 
					"/" + serviceID;
		RESTclient client = new RESTclient(url);
		return client.invokePost();
	}
	

	public String workSpacesInExperiment (String experimentID){
		String url = this.URL + "workspacesInExperiment/" + experimentID;
		RESTclient client = new RESTclient(url,MediaType.TEXT_XML);
		return client.invokeGet();
	}
	
	
	
	public String workspaceInformation (String workspaceID){
		String url = this.URL + "workspaceInformation/" + workspaceID;
		RESTclient client = new RESTclient(url);
		return client.invokeGet();
	}
	
	
	
	public String modifyRowCol (String experimentID,
						 		String serviceID,
						 		String row,
						 		String col){
		String url = this.URL + "modifyRowCol/" + experimentID +
		 			 "/" + serviceID + "/" + row + "/" + col;
		RESTclient client = new RESTclient(url);
		return client.invokePost();
	}
	
	
	
	public String addExperimentGroup (String id,
									  String group){
		String url = this.URL + "addExperimentGroup/" + id +
					 "/"+group;
		RESTclient client = new RESTclient(url);
		return client.invokePost();
	}

	public String deleteExperiment (String id){
        String url = this.URL + "experiment/" + id;
        RESTclient client = new RESTclient(url);
        return client.invokeDelete();
}
	
	public String saveUserWidgetConfiguration (String userID,
			 String serviceID, String experimentID,
			 String colID, String row){
		
		String url = this.URL + "experimentConfiguration/" + userID +
					"/" + serviceID + "/" + experimentID + "/" + colID +
					"/" + row;
		RESTclient client = new RESTclient(url);
		return client.invokePut();
	}
	
	public String deleteUserWidgetConfiguration (String userID,
			 String serviceID, String experimentID){
		
		String url = this.URL + "experimentConfiguration/" + userID +
					"/" + experimentID + "/" +serviceID ;
		RESTclient client = new RESTclient(url);
		return client.invokeDelete();
	}
	
}
