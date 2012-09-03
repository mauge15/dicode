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


public class UniqueParser {
	
	//private FileWriter XML; 
	//private DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	//private openFile file = new openFile("tmp.xml");
	//private DocumentBuilder db;
	//private Document doc;
	private NodeList nodeLst;
	private Node fstNode;
	private Element fstElmnt;
	
	
	/**Constructor method
	 * It receives the XML String to write it into a file.
	 * @param xml
	 */
	public UniqueParser(String xml, String element){
		try{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xml));
			Document doc = db.parse(is);
			//doc.getDocumentElement().normalize();
			this.nodeLst = doc.getElementsByTagName(element);
	
			this.fstNode = nodeLst.item(0);
			this.fstElmnt = (Element) this.fstNode;
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Auxiliary method for return the elements.
	 * @param element
	 * @return
	 */
	public String getElement(String element){
		try{
			
			NodeList fstNmElmntLst = fstElmnt.getElementsByTagName(element);
			Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
			NodeList fstNm = fstNmElmnt.getChildNodes();
	    
			return fstNm.item(0).getNodeValue();
		}catch (NullPointerException e){
			return " ";
		}catch (Exception e){
			e.printStackTrace();
			return " ";
		}
	}
}
