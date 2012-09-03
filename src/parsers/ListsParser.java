package parsers;

//import java.io.BufferedWriter;
//import java.io.FileWriter;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

//import dicode.openFile;



public class ListsParser {
	
	
	/**
	 * private variables
	 */
	//private FileWriter XML; 
	//private DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	//private openFile file = new openFile("tmp.xml");
	//private DocumentBuilder db;
	//private Document doc;
	private NodeList nodeLst;
	
	
	/**
	 * Constructor method. It receives the xml String
	 * for write it into a file.
	 * @param xml
	 */
	public ListsParser (String xml, String element){
		try{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xml));
			Document doc = db.parse(is);
			//doc.getDocumentElement().normalize();
			this.nodeLst = doc.getElementsByTagName(element);			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Auxiliary method for parse the elements of the
	 * xml code.
	 * @param n
	 * @param element
	 * @return
	 */
	public String getElement(int n, String element){
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
	
	
	/**
	 * This method return the number of invocation methods
	 * for one experiment in the xml code.
	 * @return
	 */
	public int getNumberElements(){
		return this.nodeLst.getLength();
	}
}
