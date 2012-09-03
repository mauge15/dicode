package parsers;



public class UserServicesConfigurationParser {
	
	
	
	private ListsParser parser;
	
	public UserServicesConfigurationParser (String xml){
		this.parser = new ListsParser(xml,"service");
	}
	
	
	
	public int getNumberUserServices(){
		return parser.getNumberElements();
	}
	
	
	public String getServiceID(int n){
		return parser.getElement(n, "serviceID");
	}
	
	
	public String getRow(int n){
		return parser.getElement(n, "row");
	}
	
	
	public String getColID(int n){
		return parser.getElement(n, "colID");
	}
	
	
	
	
}