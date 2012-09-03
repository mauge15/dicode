
package parsers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class WorkspaceInExperimentParser {

	
	/**
	 * Private variable.
	 */
	private ListsParser parser;
	
	
	public WorkspaceInExperimentParser(String XML){
		this.parser = new ListsParser(XML,"workspace");
	}
	
	
	
	public int getNumberWorkspaces(){
		return this.parser.getNumberElements();
	}
	
	
	
	public String getID(int n){
		return this.parser.getElement(n, "id");
	}
	
	
	
	public String getCview(int n){
		return this.parser.getElement(n, "cView");
	}
	
	
	
	public String getFview(int n){
		return this.parser.getElement(n, "fView");
	}
	
	
	
	public String getAview(int n){
		return this.parser.getElement(n, "aView");
	}
	
	
	
	public String getName(int n){
		try{
			return URLDecoder.decode(this.parser.getElement(n, "name"), "UTF-8");
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
			return "-5";
		}
	}
	
	
	
	public String getDescription(int n){
		try{
			return URLDecoder.decode(this.parser.getElement(n, "description"), "UTF-8");
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
			return "-5";
		}
	}
}
