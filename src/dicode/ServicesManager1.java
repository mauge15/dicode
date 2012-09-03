package dicode;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;


import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import parsers.InvokeServiceParser;
import parsers.OrderParamsParser;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class ServicesManager1 {
	
	private String URL ="http://maimonides.dia.fi.upm.es:8080/DICODE-REST/services/"; 
			
	
	/**
	 * This method makes the call to the listServices REST service.
	 * @return
	 */
	public String listServices(){
		String url = this.URL + "service";
		RESTclient client = new RESTclient(url,MediaType.TEXT_XML);
		return client.invokeGet();
	}
	
	
	/**
	 * This method makes the call to the service REST service.
	 * @param serviceID
	 * @return
	 */
	public String service(String serviceID){
		String url = this.URL + "service/"+ serviceID;
		RESTclient client = new RESTclient(url,MediaType.TEXT_XML);
		return client.invokeGet();
	}
	
	
	/**
	 * This method makes the call to the isServiceName REST service.
	 * @param name
	 * @return
	 */
	public String isServiceName(String name){
		try{
			String nm = URLEncoder.encode(name, "UTF-8");
			String url = this.URL + "service/isServiceName/"+ nm;
			RESTclient client = new RESTclient(url);
			return client.invokeGet();
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * This method makes the call to the isServiceURI REST service.
	 * @param uRI
	 * @return
	 */
	public String isServiceURI(String uRI){
		try{
			String uri = URLEncoder.encode(uRI, "UTF-8");
			String url = this.URL + "service/isServiceURI?URI="+ uri;
			RESTclient client = new RESTclient(url);
			return client.invokeGet();
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * This method makes the call to the listDataTypes REST service.	
	 * @return
	 */
	public String listDataTypes (){
		String url = this.URL + "listDataTypes";
		RESTclient client = new RESTclient(url, MediaType.TEXT_XML);
		return client.invokeGet();
	}
	
	
	/**
	 * This method makes the call to the listParamTypes REST service.
	 * @param serviceTypeID
	 * @return
	 */
	public String listParamTypes (String serviceTypeID){
		String url = this.URL + "listParamTypes/" + serviceTypeID;
		RESTclient client = new RESTclient(url, MediaType.TEXT_XML);
		return client.invokeGet();
	}
	
	
	/**
	 * This method makes the call to the listInvocationMethods REST service.
	 * @param serviceTypeID
	 * @return
	 */
	public String listInvocationMethods (String serviceTypeID){
		String url = this.URL + "listInvocationMethods/" + serviceTypeID;
		RESTclient client = new RESTclient(url, MediaType.TEXT_XML);
		return client.invokeGet();
	}
	
	
	/**
	 * This method makes the call to the paramType REST service.
	 * @param idParam
	 * @return
	 */
	public String paramType (String paramID){
		String url = this.URL + "paramType/" + paramID;
		RESTclient client = new RESTclient(url, MediaType.TEXT_XML);
		return client.invokeGet();
	}
	
	
	/**
	 * This method makes the call to the dataType REST service.
	 * @param idParam
	 * @return
	 */
	public String dataType (String paramID){
		String url = this.URL + "dataType/" + paramID;
		RESTclient client = new RESTclient(url, MediaType.TEXT_XML);
		return client.invokeGet();		
	}
	
	
	/**
	 * This method makes the call to the orderParams REST service.
	 * @param serviceID
	 * @return
	 */
	public String orderParams (String serviceID){
		String url = this.URL + "orderParams/" + serviceID;
		RESTclient client = new RESTclient (url, MediaType.TEXT_XML);
		return client.invokeGet();
	}
	
	
	/**
	 * This method makes the call to the invocationMethod REST service.
	 * @param serviceID
	 * @return
	 */
	public String invocationMethod (String serviceID){
		String url = this.URL + "invocationMethod" +"/"+ serviceID;
		RESTclient client = new RESTclient (url, MediaType.TEXT_XML);
		return client.invokeGet();
	}
	
	
	/**
	 * This method makes the call to the addService REST Service.
	 * @param upUser
	 * @param Name
	 * @param Description
	 * @param URI
	 * @param creation
	 * @param mod
	 * @param Active
	 * @param state
	 * @param invocationID
	 * @param typeID
	 * @param categoryID
	 * @return
	 */
	
	public String addService (String upUser, String Name, String shortName, String Description,
			 String URI, String Active, 
			 String state, String categoryID, String tags)
	{
		System.out.println("Adding Service");
		try
		{
			String name = URLEncoder.encode(Name, "UTF-8");
			String desc = URLEncoder.encode(Description, "UTF-8");
			String Uri = URLEncoder.encode(URI, "UTF-8");
			shortName = URLEncoder.encode(shortName,"UTF-8");
			tags = URLEncoder.encode(tags,"UTF-8");
			String url = this.URL + "service/" + upUser + "/" + name + "/"  + Active + "/" + state + "/" + categoryID + "/" + shortName + "?description="+desc +"&URI="+Uri+"&tags="+tags;
			RESTclient client = new RESTclient (url);
			//return client.invokePost() + " " + url;
			String id = client.invokePost();
			System.out.println("The result is "+id);
			return id;
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * This method makes the call to the updateService REST Service.
	 * @param id
	 * @param name
	 * @param desc
	 * @param URI
	 * @param type
	 * @param tags
	 * @return
	 */
	public String updateService (String id, String name, String shortName,
		     String desc, String URI,
		     String type, String tags){
		try{
			String nm = URLEncoder.encode(name, "UTF-8");
			String sn = URLEncoder.encode(shortName, "UTF-8");
			String des = URLEncoder.encode(desc, "UTF-8");
			String Uri = URLEncoder.encode(URI, "UTF-8");
			String tgs = URLEncoder.encode(tags, "UTF-8");

			String url = this.URL + "service/" + id + "?name=" +
				nm + "&category="  + type + "&desc="+des +
				"&URI="+Uri+"&tags="+tgs;
			RESTclient client = new RESTclient(url);
			String res = "0";
			String res1 = client.invokePut();
			
			String res2 = updateShortName(id, sn);
			
			if (res1.equals("1") && res2.equals("1"))
			{
				res = "1";
			}
			
			return res;
		}catch(Exception e){
			e.printStackTrace();
			return "-5";
		}
	}
	
	/**
	 * This method makes the call to the updateActive REST service.
	 * @param serviceID
	 * @param value
	 * @return
	 */
	
	public String updateActive (String serviceID, String value){
		String url = this.URL + "service/" + serviceID + "?active=" + 
							value;
		RESTclient client = new RESTclient(url);
		return client.invokePut();
	}
	
	
	/**
	 * This method makes the call to the addParameter REST service.
	 * @param name
	 * @param Position
	 * @param dataType
	 * @param parameterTypeID
	 * @param serviceID
	 * @return
	 */
	public String addParameter (String name, String position,
								String dataType, String parameterTypeID,
								String serviceID){
		
		try{	
			String nm = URLEncoder.encode(name, "UTF-8");		
			String url = this.URL + "addParameter/" + nm+ "/" +
						position + "/" + dataType + "/" + parameterTypeID + "/" +
						serviceID;
			RESTclient client = new RESTclient(url);
			return client.invokeGet();
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
			return "";
		}
	}
	
	
	/**
	 * This method makes the call to the deleteParameter REST service.
	 * @param serviceID
	 * @param name
	 * @return
	 */
	public String deleteParameter (String serviceID, String name){
		try{
			
			String nm = URLEncoder.encode(name, "UTF-8");
		
			String url = this.URL + "deleteParameter/" 
					 + serviceID + "/" + nm;
			RESTclient client = new RESTclient(url);
			return client.invokeGet();
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
			return "";
		}
	}
	
	
	/**
	 * This method makes the call to the serviceName REST service.
	 * @param serviceID
	 * @return
	 */
	public String serviceName (String serviceID){
		String url = this.URL + "serviceName/"
					 + serviceID;
		RESTclient client = new RESTclient(url,MediaType.TEXT_XML);
		return client.invokeGet();
	}
	
	
	/**
	 * This method makes the call to the listCategories REST service.
	 * @return
	 */
	public String listCategories(){
		String url = this.URL + "service/listCategories";
		RESTclient client = new RESTclient(url,MediaType.TEXT_XML);
		return client.invokeGet();
	}
	
	
	/**
	 * This method makes the call to the servicesInCategory REST service.
	 * @param experimentID
	 * @param categoryID
	 * @return XML String with all the services cataloged in one category.
	 */

	public String servicesInCategory(String experimentID,
			 String categoryID){
		String url = this.URL + "service/servicesInCategory/" + 
		experimentID + "/" + categoryID;
		RESTclient client = new RESTclient(url,MediaType.TEXT_XML);
		return client.invokeGet();
		}
	
	
	/**
	 * This method makes the call to the listServiceTypes REST service.
	 * @return XML String with all the service Types in the system.
	 */
	public String listServiceTypes(){
		String url = this.URL + "listServiceTypes";
		RESTclient client = new RESTclient(url, MediaType.TEXT_XML);
		return client.invokeGet();
	}
	
	
	/**
	 * This method makes the call to the invokeService REST service
	 * and then uses the information given by the service for invoke
	 * a REST service.
	 * @param idservice
	 * @param values
	 * @return The information given by the service invoked.
	 */
	public String invokeRESTService(String idservice,
									List<String> values){
		MultivaluedMap <String,String> queryParams = new MultivaluedMapImpl();
		
		List <String> ids = new ArrayList<String>();
		OrderParamsParser parser = new OrderParamsParser(orderParams(idservice));
		int i;
		int tope = parser.getNumberParams();
		for (i = 0; i < tope; i++){
			ids.add(parser.getID(i));
		}
		
		queryParams.put("ids", ids);
		queryParams.put("Values", values);
		
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource resource = client.resource(this.URL + "invokeService");
		
		
		String XML = resource.path(idservice).path(Integer.toString(tope)).queryParams(queryParams).
		accept(MediaType.TEXT_XML).get(String.class);
		
		
		InvokeServiceParser parserInvoke = new InvokeServiceParser(XML);
		
		String uri = parserInvoke.getURI();
		String accept = parserInvoke.getAcceptType();
		
		
		RESTclient client2 = new RESTclient(uri, accept);
		
		
		if (parserInvoke.getInvocationMethod().equals("GET")){
			return client2.invokeGet();
		}else{
			return client2.invokePost();
		}
		
		
	}
	
	
	/**
	 * This method makes the call to the updateURI REST service.
	 * @param id
	 * @param URI
	 * @return "1" if the URI has been updated or "0" otherwise.
	 */
	public String updateURI (String id, String URI){
		try{
			String Uri = URLEncoder.encode(URI, "UTF-8");
			String url = this.URL + "service/" + id
						+ "?URI=" + Uri;
			RESTclient client = new RESTclient(url);
			return client.invokePut();
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
			return "";
		}
	}
	
	
	/**
	 * This method makes the call to the addServiceReturnsDataType REST service.
	 * @param name
	 * @param serviceID
	 * @param dataTypeID
	 * @return "1" if the return type has been added or "0" otherwise.
	 */
	public String addServiceReturnsDataType (String name,String serviceID, 
											String dataTypeID){
		try {
		String nm = URLEncoder.encode(name, "UTF-8");
		String url = this.URL + "addServiceReturnsDataType/" + 
						nm + "/" + serviceID + "/" + dataTypeID;
		RESTclient client = new RESTclient(url);
		return client.invokePost();
		} catch (UnsupportedEncodingException e){
			e.printStackTrace();
			return "";
		}
	}
	
	
	/**
	 * This method makes the call to the updateInvocationMethod REST service.
	 * @param serviceID
	 * @param invocationMethodID
	 * @return "1" if the invocation method has been updated
	 * 		   "0" otherwise.
	 */
	public String updateInvocationMethod (String serviceID,
									   String invocationMethodID){
		String url = this.URL + "updateInvocationMethod/" + 
						serviceID + "/" + invocationMethodID;
		RESTclient client = new RESTclient(url);
		return client.invokePost();
	}
	
	
	/**
	 * This method makes the call to the updateDataType REST service.
	 * @param id
	 * @param name
	 * @param description
	 * @return "1" if the data type has been updated or "0" otherwise.
	 */
	public String updateDataType (String id, String name, String description){
		
		try{
			String nm = URLEncoder.encode(name,"UTF-8");
		
			String desc = URLEncoder.encode(description,"UTF-8");
		
			String url = this.URL + "updateDataTypes/" +
				id + "?name=" + nm + "&description=" + desc;
			RESTclient client = new RESTclient(url);
			return client.invokePost();
		}catch(Exception e){
			e.printStackTrace();
			return "-5";
		}
	}
	
	
	/**
	 * This method makes the call to the listReturnTypes REST service.
	 * @param serviceID
	 * @return XML String with all the types that one service can return.
	 */
	public String listReturnTypes (String serviceID){
		String url = this.URL + "listReturnTypes/" + serviceID;
		RESTclient client = new RESTclient(url, MediaType.TEXT_XML);
		return client.invokeGet();
	}
	
	
	/**
	 * This method makes the call to the dataTypeData REST service.
	 * @param dataTypeID
	 * @return XML String with the information of one dataType 
	 * 			given one data type identification
	 */
	public String dataTypeData (String dataTypeID){
		String url = this.URL + "dataTypeData/" + dataTypeID;
		RESTclient client = new RESTclient(url, MediaType.TEXT_XML);
		return client.invokeGet();
	}
	
	public String searchService (List<String> params){
		MultivaluedMap <String,String> queryParams = new MultivaluedMapImpl();
		queryParams.put("params",params);
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource resource = client.resource(this.URL + "searchService");
		System.out.println(resource.queryParams(queryParams));
		return resource.queryParams(queryParams).accept(MediaType.TEXT_XML).get(String.class);
	}
	
	
	public String deleteServiceInExperiment (String serviceID, String experimentID)
	{
		String url = this.URL + "experimentIncludesService/" + serviceID +"/" + experimentID;
		RESTclient client = new RESTclient(url);
		return client.invokeDelete();
	}

	public String deleteServiceInExperiment (String serviceID)
	{
		String url = this.URL + "experimentIncludesService/" + serviceID;
		RESTclient client = new RESTclient(url);
		return client.invokeDelete();
	}
	
	public String deleteServiceInExperimentConfiguration (String serviceID, String experimentID)
	{
		String url = this.URL + "service/experimentConfiguration/" + serviceID + "/" + experimentID;
		RESTclient client = new RESTclient(url);
		return client.invokeDelete();
	}
	
	
	
	public String deleteServiceInExperimentConfiguration (String serviceID)
	{
		String url = this.URL + "service/experimentConfiguration/" + serviceID;
		RESTclient client = new RESTclient(url);
		return client.invokeDelete();
	}
	
	public String deleteService (String serviceID)
	{
    String url = this.URL + "service/" + serviceID;
	RESTclient client = new RESTclient(url);
	return client.invokeDelete();
	}
	
	
	public String updateShortName (String serviceID, String shortName){
        String url = this.URL + "service/"+serviceID+"?shortName="+shortName;
        RESTclient client = new RESTclient(url);
        return client.invokePut();
}
	
	
}
