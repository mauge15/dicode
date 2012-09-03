package parsers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class CoPeItParser {

	/**
	 * Private variable
	 */
	private UniqueParser parser;
	
	
	public CoPeItParser (String XML){
		this.parser = new UniqueParser(XML,"response");
	}
	
	
	public String getCode(){
		return this.parser.getElement("error");
	}
	
	
	public String getDescription(){
		try{
			return URLDecoder.decode(this.parser.getElement("message"), "UTF-8");
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
			return "-5";
		}
	}
}