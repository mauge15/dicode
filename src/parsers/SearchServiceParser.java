package parsers;

public class SearchServiceParser {
	
	
	ListsParser parser;
	
	
	public SearchServiceParser (String XML){
		this.parser = new ListsParser(XML,"service");
	}
	
	
	public int getNumberServices (){
		return this.parser.getNumberElements();
	}
	
	
	public String getID(int n){
		return this.parser.getElement(n,"id");
	}
}
