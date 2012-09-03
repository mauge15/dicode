package parsers;

import java.io.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class URLparser {
	
	
	private static File file = new File("../webapps/dicode/WEB-INF/url.xml");
	
	
	private static DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	
	private static String getElement (String part, String element){
		try{			
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();		
			NodeList nodeLst = doc.getElementsByTagName(part);
			Node fstNode = nodeLst.item(0);
			Element fstElmnt = (Element) fstNode;
		
			NodeList fstNmElmntLst = fstElmnt.getElementsByTagName(element);
			Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
			NodeList fstNm = fstNmElmnt.getChildNodes();
	    
			return fstNm.item(0).getNodeValue();
		}catch (Exception e){
			e.printStackTrace();
			return "";
		}		
	}
		
	public static String giveUri(){
		return getElement("location", "URI");
	}
	
	public static String givePort(){
		return getElement("location", "port");
	}
	
	public static String givePackage(){
		return getElement("location", "package");
	}
	
	public static String giveContext(){
		return getElement("path", "context");
	}
	
	public static String giveHost(){
		return getElement("mail", "host");
	}
	
	public static String giveFrom(){
		return getElement("mail", "from");
	}
	
	public static String giveMailPort(){
		return getElement("mail", "port");		
	}
	
	public static String giveUsername(){
		return getElement("mail", "username");
	}
	
	public static String givePass(){
		return getElement ("mail", "pass");
	}
	
	public static String giveCIUsername() {
		return getElement("copeit", "username");
	}
	
	public static String giveCIPass() {
		return getElement("copeit", "pass");
	}
	
	public static String giveUserRest(){
		return getElement("userREST","user");
	}
	
	public static String giveUserRestPass(){
		return getElement("userREST","pass");
	}
}
