package dicode;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import dicode.Encrypt;
import javax.ws.rs.core.UriBuilder;

import org.apache.commons.codec.binary.Base64;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.ClientFilter;

public class CopeRESTClient {
	
	static ClientFilter filter = new ClientFilter() {
	    private ArrayList<Object> cookies;

	    @Override
	    public ClientResponse handle(ClientRequest request) throws ClientHandlerException {
	        if (cookies != null) {
	            request.getHeaders().put("Cookie", cookies);
	        }
	        ClientResponse response = getNext().handle(request);
	        if (response.getCookies() != null) {
	            if (cookies == null) {
	                cookies = new ArrayList<Object>();
	            }
	            // simple addAll just for illustration (should probably check for duplicates and expired cookies)
	            cookies.addAll(response.getCookies());
	            System.out.println("Numero de cookies: "+cookies.size());
	        }
	        return response;
	    }
	};
	
	public static String copeItLogin (String usrName, String pass) throws Exception {
		
		String CurrDate=getCurrDate();

		String data = "user="+URLEncoder.encode(usrName,"UTF-8")+"&pass="+URLEncoder.encode(pass,"UTF-8")+"&tmpstamp="+CurrDate;
		return getURLEncoded(data, "UTF-8");
	}
	
	public static String copeItLoginComplete (String usrName, String pass) throws Exception {
		ClientConfig config = new DefaultClientConfig ();
		Client client = Client.create (config);
		client.addFilter(filter);
		//DefaultApacheHttpClientConfig config = new DefaultApacheHttpClientConfig ();
		//config.getProperties().put(ApacheHttpClientConfig.PROPERTY_HANDLE_COOKIES, true);
		//ApacheHttpClient client = ApacheHttpClient.create (config);
		
		String CurrDate=getCurrDate();

		String data = "user="+URLEncoder.encode(usrName,"UTF-8")+"&pass="+URLEncoder.encode(pass,"UTF-8")+"&tmpstamp="+CurrDate;
		System.out.println("STRING TO ENCODE -> "+data);
		String URLEncoded = getURLEncoded(data, "UTF-8");
		WebResource service = client.resource(getBaseURI()+"/?op=login&hashData="+URLEncoded);
		return service.get(String.class);
	}
	
	public static String copeItRegister (String usrName, String pass, String email) throws Exception {
		ClientConfig config = new DefaultClientConfig ();
		Client client = Client.create ( config );
		
		String CurrDate=getCurrDate();
		
		String data = "user="+URLEncoder.encode(usrName,"UTF-8")+"&pass="+URLEncoder.encode(pass,"UTF-8")+"&email="+email+"&tmpstamp="+CurrDate;
		String URLEncoded = getURLEncoded(data, "UTF-8");
		WebResource service = client.resource(getBaseURI()+"/?op=register&hashData="+URLEncoded);
		return service.get(String.class);
	}
	
	public static String copeItDeleteUser (String usrName) throws Exception {
		ClientConfig config = new DefaultClientConfig ();
		Client client = Client.create ( config );
		
		String CurrDate=getCurrDate();
		
		String data = "user="+URLEncoder.encode(usrName,"UTF-8")+"&tmpstamp="+CurrDate;
		String URLEncoded = getURLEncoded(data, "UTF-8");
		WebResource service = client.resource(getBaseURI()+"/?op=delete&hashData="+URLEncoded);
		return service.get(String.class);
	}
	
	public static String copeItUpdateEmail (String usrName, String email) throws Exception {
		ClientConfig config = new DefaultClientConfig ();
		Client client = Client.create ( config );
		
		String CurrDate=getCurrDate();
		
		String data = "user="+URLEncoder.encode(usrName,"UTF-8")+"&email="+email+"&tmpstamp="+CurrDate;
		String URLEncoded = getURLEncoded(data, "UTF-8");
		WebResource service = client.resource(getBaseURI()+"/?op=updateemail&hashData="+URLEncoded);
		return service.get(String.class);
	}
	
	public static String copeItUpdatePassword (String usrName, String pass) throws Exception {
		ClientConfig config = new DefaultClientConfig ();
		Client client = Client.create ( config );
		
		String CurrDate=getCurrDate();
		
		String data = "user="+URLEncoder.encode(usrName,"UTF-8")+"&pass="+URLEncoder.encode(pass,"UTF-8")+"&tmpstamp="+CurrDate;
		String URLEncoded = getURLEncoded(data, "UTF-8");
		WebResource service = client.resource(getBaseURI()+"/?op=updatepass&hashData="+URLEncoded);
		return service.get(String.class);
	}
	
	public static String copeItLogout (String usrName) throws Exception {
		String CurrDate=getCurrDate();
		
		String data = "user="+URLEncoder.encode(usrName,"UTF-8")+"&tmpstamp="+CurrDate;
		return getURLEncoded(data, "UTF-8");
	}
	
	public static String copeItLogoutComplete (String usrName) throws Exception {
		ClientConfig config = new DefaultClientConfig ();
		Client client = Client.create (config);
		client.addFilter(filter);
		//DefaultApacheHttpClientConfig config = new DefaultApacheHttpClientConfig ();
		//config.getProperties().put(ApacheHttpClientConfig.PROPERTY_HANDLE_COOKIES, true);
		//ApacheHttpClient client = ApacheHttpClient.create (config);
		String CurrDate=getCurrDate();
		
		String data = "user="+URLEncoder.encode(usrName,"UTF-8")+"&tmpstamp="+CurrDate;
		String URLEncoded = getURLEncoded(data, "UTF-8");
		WebResource service = client.resource(getBaseURI()+"/?op=logout&hashData="+URLEncoded);
		return service.get(String.class);
	}
	
	public static String copeItCreateCW (String title, String descr, String priv,
			String modify, String upload, String uploadmaxsize,
			String type) throws Exception {
		ClientConfig config = new DefaultClientConfig ();
		Client client = Client.create (config);
		client.addFilter(filter);
		//DefaultApacheHttpClientConfig config = new DefaultApacheHttpClientConfig ();
		//config.getProperties().put(ApacheHttpClientConfig.PROPERTY_HANDLE_COOKIES, true);
		//ApacheHttpClient client = ApacheHttpClient.create (config);
		String CurrDate=getCurrDate();
		//title=testtitle&private=1&modify=1&upload=1&uploadmaxsize=1&type=1
		String data = "title="+URLEncoder.encode(title,"UTF-8")+
					  "&descr="+URLEncoder.encode(descr,"UTF-8")+
					  "&private="+priv+"&modify="+modify+"&upload="+upload+
					  "&uploadmaxsize="+uploadmaxsize+"&type="+type+"&tmpstamp="+CurrDate;
		System.out.println(data);
		String URLEncoded = getURLEncoded(data, "UTF-8");
		WebResource service = client.resource(getBaseURI()+"/?op=creatework&hashData="+URLEncoded);
		return service.get(String.class);
	}
	
	public static String copeItUpdateCW (String id, String title, String descr,
			String priv, String modify, String upload, String uploadmaxsize,
			String type) throws Exception {
		ClientConfig config = new DefaultClientConfig ();
		Client client = Client.create ( config );
		
		String CurrDate=getCurrDate();
		//title=testtitle&private=1&modify=1&upload=1&uploadmaxsize=1&type=1
		String data = "id="+id+
					  "&title="+URLEncoder.encode(title,"UTF-8")+
					  "&descr="+URLEncoder.encode(descr,"UTF-8")+
					  "&private="+priv+"&modify="+modify+"&upload="+upload+
					  "&uploadmaxsize="+uploadmaxsize+"&type="+type+"&tmpstamp="+CurrDate;
		String URLEncoded = getURLEncoded(data, "UTF-8");
		WebResource service = client.resource(getBaseURI()+"/?op=updatework&hashData="+URLEncoded);
		return service.get(String.class);
	}
	
	public static String copeItDeleteObject (String id, String spaceID) throws Exception {
		ClientConfig config = new DefaultClientConfig ();
		Client client = Client.create ( config );
		
		String CurrDate=getCurrDate();
		String data = "id="+id+"&space_id="+spaceID+"&tmpstamp="+CurrDate;
		String URLEncoded = getURLEncoded(data, "UTF-8");
		WebResource service = client.resource(getBaseURI()+"/?op=deletedoc&hashData="+URLEncoded);
		return service.get(String.class);
	}
	
	public static String copeItGrantPrivileges (String user, String type) throws Exception {
		ClientConfig config = new DefaultClientConfig ();
		Client client = Client.create (config);
		client.addFilter(filter);
		//DefaultApacheHttpClientConfig config = new DefaultApacheHttpClientConfig ();
		//config.getProperties().put(ApacheHttpClientConfig.PROPERTY_HANDLE_COOKIES, true);
		//ApacheHttpClient client = ApacheHttpClient.create (config);
		String CurrDate=getCurrDate();
		String data = "user="+URLEncoder.encode(user,"UTF-8")+
					  "&type="+type+"&tmpstamp="+CurrDate;
		System.out.println("STRING TO ENCODE -> "+data);
		String URLEncoded = getURLEncoded(data, "UTF-8");
		WebResource service = client.resource(getBaseURI()+"/?op=grantpriv&hashData="+URLEncoded);
		return service.get(String.class);
	}
	
	public static String copeItAdminGrantPrivileges (String user, String type) throws Exception {
		ClientConfig config = new DefaultClientConfig ();
		Client client = Client.create ( config );
		
		String CurrDate=getCurrDate();
		String data = "user="+URLEncoder.encode(user,"UTF-8")+
					  "&type="+type+"&tmpstamp="+CurrDate;
		System.out.println("STRING TO ENCODE -> "+data);
		String URLEncoded = getURLEncoded(data, "UTF-8");
		WebResource service = client.resource(getBaseURI()+"/?op=grantprivadmin&hashData="+URLEncoded);
		return service.get(String.class);
	}
	
	public static String copeItRemovePriv (String user, String type) throws Exception {
		ClientConfig config = new DefaultClientConfig ();
		Client client = Client.create ( config );
		
		String CurrDate=getCurrDate();
		String data = "user="+URLEncoder.encode(user,"UTF-8")+
					  "&type="+type+"&tmpstamp="+CurrDate;
		String URLEncoded = getURLEncoded(data, "UTF-8");
		WebResource service = client.resource(getBaseURI()+"/?op=removepriv&hashData="+URLEncoded);
		return service.get(String.class);
	}
	
	public static String copeItListCW (String type) throws Exception {
		ClientConfig config = new DefaultClientConfig ();
		Client client = Client.create ( config );
		
		String CurrDate=getCurrDate();
		String data = "type="+type+"&tmpstamp="+CurrDate;
		String URLEncoded = getURLEncoded(data, "UTF-8");
		WebResource service = client.resource(getBaseURI()+"/?op=listworkspaces&hashData="+URLEncoded);
		return service.get(String.class);
	}
	
	public static String copeItCreateUC (String title) throws Exception {
		ClientConfig config = new DefaultClientConfig ();
		Client client = Client.create ( config );
		client.addFilter(filter);
		String CurrDate=getCurrDate();
		String data = "title="+URLEncoder.encode(title, "UTF-8")+"&tmpstamp="+CurrDate;
		System.out.println("STRING TO ENCODE -> "+data);
		String URLEncoded = getURLEncoded(data, "UTF-8");
		WebResource service = client.resource(getBaseURI()+"/?op=createcomm&hashData="+URLEncoded);
		return service.get(String.class);
	}
	
	public static String copeItUploadFile(String spaceId, String title, String posx, String posy, String contentUrl) throws Exception {
		ClientConfig config = new DefaultClientConfig ();
		Client client = Client.create ( config );
		client.addFilter(filter);
		String CurrDate=getCurrDate();
		String data = "space_id="+spaceId+"&title="+URLEncoder.encode(title, "UTF-8")+"&content_url="+contentUrl+"&posx="+posx+"&posy="+posy+"&tmpstamp="+CurrDate;
		System.out.println("STRING TO ENCODE -> "+data);
		String URLEncoded = getURLEncoded(data, "UTF-8");
		WebResource service = client.resource(getBaseURI()+"/?op=uploadfile&hashData="+URLEncoded);
		return service.get(String.class);
	}
	private static String getCurrDate() {
		Date date = new Date();
	    SimpleDateFormat formatter;
	    
	    formatter = new SimpleDateFormat("yyyyMMddHHmmss");
	    formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
	    return formatter.format(date);
	}
	
	public static URI getBaseURI () {
		//return UriBuilder . fromUri ("http://copeit.cti.gr/user").build();
		return UriBuilder.fromUri ("http://dicodedev.cti.gr/user").build();
	}
		
	private static String getURLEncoded(String data, String encode) throws UnsupportedEncodingException, Exception {
		byte [] encriptedBytes= Encrypt.encrypt(data.getBytes(encode), encode);
		String encodedStr = Base64.encodeBase64String(encriptedBytes);
		return URLEncoder.encode(encodedStr,encode);
		
	}
	
	
	
}
