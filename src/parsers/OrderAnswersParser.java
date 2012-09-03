package parsers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class OrderAnswersParser {
	/**
	 * Private variable
	 */
	private ListsParser parser;
	
	
	/**
	 * Constructor method. It receives the XML code and
	 * writes it into a file for parse it.
	 * @param XML
	 */
	public OrderAnswersParser (String XML){
		this.parser = new ListsParser(XML, "logEntry");
	}
	
	
	/**
	 * This method return the answers number for one Entry log.
	 * @return
	 */
	public int getNumberAnswers(){
		return this.parser.getNumberElements();
	}
	
	
	/**
	 * This method returns the author identification of one
	 * answer for one entry log.
	 * @param n
	 * @return
	 */
	public String getAuthorID(int n){
		return this.parser.getElement(n, "authorID");
	}
	
	
	/**
	 * This method returns the date in which the answer was
	 * made.
	 * @param n
	 * @return
	 */
	public String getTimeStamp(int n){
		return this.parser.getElement(n, "timeStamp");
	}
	
	
	/**
	 * This method returns the answer message for one entry log.
	 * @param n
	 * @return
	 */
	public String getMessage(int n){
		try{
			return URLDecoder.decode(this.parser.getElement(n, "message"),"UTF-8");
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
			return "-5";
		}
	}
	
	
	/**
	 * This method returns the number of answer for the answer in
	 * a possition given in the XML code.
	 * @param n
	 * @return
	 */
	public String getAnswers(int n){
		return this.parser.getElement(n, "answers");
	}
	
	
	/**
	 * This method returns the Entry log type identification of
	 * the answer in a position given in the XML code.
	 * @param n
	 * @return
	 */
	public String getTypeID(int n){
		return this.parser.getElement(n, "typeID");
	}

}
