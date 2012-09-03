package parsers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class OrderEntriesParser {
	
	/**
	 * Private variable.
	 */
	private ListsParser parser;
	
	
	/**
	 * Constructor method. It receives the XML code and
	 * writes it into a file for parse it.
	 * @param XML
	 */
	public OrderEntriesParser (String XML){
		this.parser = new ListsParser(XML, "logEntry");
	}
	
	
	/**
	 * This method return the number of the log entries
	 * for one type and in one experiment.
	 * @return
	 */
	public int getNumberEntries(){
		return this.parser.getNumberElements();
	}
	
	
	/**
	 * This method returns the log entry author identification
	 * for one position given in the XML code.
	 * @param n
	 * @return
	 */
	public String getAuthorID(int n){
		return this.parser.getElement(n, "authorID");
	}
	
	
	/**
	 * This method returns the log entry date in which that entry
	 * was created.
	 * @param n
	 * @return
	 */
	public String getTimeStamp(int n){
		return this.parser.getElement(n, "timeStamp");
	}
	
	
	/**
	 * This method returns the log entry message.
	 * @param n
	 * @return
	 */
	public String getMessage(int n){
		try{
			return URLDecoder.decode(this.parser.getElement(n, "message"), "UTF-8");
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
			return "-5";
		}
	}
	
	
	/**
	 * This method returns the answers number for the log entry
	 * in a position given in the XML code.
	 * @param n
	 * @return
	 */
	public String getAnswers(int n){
		return this.parser.getElement(n, "answers");
	}
	
	
	/**
	 * This method returns the log entry type identification for
	 * the log entry in the position given. 
	 * @param n
	 * @return
	 */
	public String getTypeID(int n){
		return this.parser.getElement(n, "typeID");
	}

}