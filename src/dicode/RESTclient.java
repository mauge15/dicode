package dicode;

import javax.ws.rs.core.MediaType;

import parsers.URLparser;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class RESTclient {
	
	private String URL;
	private String AcceptType = MediaType.TEXT_PLAIN;
	private ClientConfig config = new DefaultClientConfig();
	private Client client = Client.create(this.config);
	
	
	public RESTclient(){
		 this.client.addFilter(new HTTPBasicAuthFilter(URLparser.giveUserRest(), URLparser.giveUserRestPass()));
		
	}
	
	public RESTclient (String URL){
		this.URL = URL;
		 this.client.addFilter(new HTTPBasicAuthFilter(URLparser.giveUserRest(), URLparser.giveUserRestPass()));
	}
	
	public RESTclient (String URL, String type){
		this.URL = URL;
		this.AcceptType = type;
		 this.client.addFilter(new HTTPBasicAuthFilter(URLparser.giveUserRest(), URLparser.giveUserRestPass()));
	}
	
	public String getURL(){
		return this.URL;
	}
	
	public String getAcceptType(){
		return this.AcceptType;
	}
	
	public void setURL(String URLnew){
		this.URL = URLnew;
	}
	
	public void setAcceptType (String newType){
		this.AcceptType = newType;
	}
	
	public String invokeGet(){
		WebResource service = this.client.resource(this.URL);
		return service.accept(this.AcceptType).get(String.class);
	}
	
	public String invokePost(){
		WebResource service = this.client.resource(this.URL);
		return service.accept(this.AcceptType).post(String.class);
	}

	public String invokePut(){
        WebResource service = this.client.resource(this.URL);
        return service.accept(this.AcceptType).put(String.class);
}
	
	public String invokeDelete(){
        WebResource service = this.client.resource(this.URL);
        return service.accept(this.AcceptType).delete(String.class);
}
	

	
}
