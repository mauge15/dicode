package parsers;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


public class ListRolesParser {
	/**
	 * Private Variable
	 */
	//private FileWriter XML; 
	//private DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	//private openFile file = new openFile("tmp.xml");
	//private DocumentBuilder db;
	//private Document doc;
	private NodeList nodeLst;
	
	
	/**
	 * Constructor method. It writes the xml String code
	 * to a file for parse it.
	 * @param xml
	 */
	public ListRolesParser(String xml){
		try{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			//this.XML = new FileWriter ("tmp.xml");
			//BufferedWriter bf = new BufferedWriter(this.XML);
			//bf.write(xml);
			//bf.close();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xml));
			Document doc = db.parse(is);
			//this.doc = this.db.parse(this.file.getFile());
			//this.doc.getDocumentElement().normalize();
			this.nodeLst = doc.getElementsByTagName("rol");
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
	private String getElement(int n, String element){
		try{
			Node node = this.nodeLst.item(n);
			Element elment = (Element) node;
			NodeList fstNmElmntLst = elment.getElementsByTagName(element);
			Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
			NodeList fstNm = fstNmElmnt.getChildNodes();
	    
			return fstNm.item(0).getNodeValue();
		}catch (NullPointerException e){
			return " ";
		}
		catch (Exception e){
			e.printStackTrace();
			return " ";
		}
	}
	
	
	private int getNumberElementsInside(int n, String element){
		try{
			Node node = this.nodeLst.item(n);
			Element elment = (Element) node;
			NodeList lst = elment.getElementsByTagName(element);
			return lst.getLength();
		}catch (NullPointerException e){
			return -1;
		}catch (Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	
	private String getElementInside (int n, int m,
									 String element){
		try{
			
			Node node = this.nodeLst.item(n);
			Element elment = (Element) node;
			NodeList lst = elment.getElementsByTagName("permission");
			
			
			Node node2 = lst.item(m);
			Element elment2 = (Element) node2;
			
			NodeList lst2 = elment2.getElementsByTagName(element);
			
			Element result = (Element) lst2.item(0);
			NodeList lstResult = result.getChildNodes();
			return lstResult.item(0).getNodeValue();
		}catch(NullPointerException e){
			return " ";
		}catch (Exception e){
			return " ";
		}
	}
	
	
	/**
	 * This method returns the number of roles
	 * in the system.
	 * @return
	 */
	public int getNumberRoles(){
		return this.nodeLst.getLength();
	}
	
	
	/**
	 * This method returns one id of one rol in
	 * a position given in the xml list.
	 * @param n
	 * @return
	 */
	public String getRolID(int n){
		return getElement(n, "id");
	}
	
	
	/**
	 * This method returns a rol name in a possition
	 * given in the xml list.
	 * @param n
	 * @return
	 */
	public String getRolName(int n){
		return getElement(n, "name");
	}
	
	
	/**
	 * This method returns the number of permissions
	 * @return
	 */
	public int getNumberPermissions(int n){
		return getNumberElementsInside(n, "permission");
	}
	
	public String getRolPermissionID(int n, int m){
		return getElementInside(n,m, "id");
	}
	
	
	public String getRolPermissionName(int n, int m){
		try{
			return URLDecoder.decode(getElementInside (n, m, "name"), "UTF-8");
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
			return "-5";
		}	}
	
	
	
	public String getRolPermissionDescription (int n, int m){
		try{
			return URLDecoder.decode(getElementInside (n, m, "description"), "UTF-8");
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
			return "-5";
		}
	}
	
}
