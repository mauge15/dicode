package parsers;

public class RolesUserParser {
	
	/**
	 * Private variable
	 */
	private ListsParser parser;
	
	
	/**
	 * Constructor method. It receives the XML code and
	 * writes it into a file for parse it.
	 * @param XML
	 */
	public RolesUserParser (String XML){
		this.parser = new ListsParser(XML,"rol");
	}
	
	
	/**
	 * This method returns the number of roles for one user.
	 * @return
	 */
	public int getNumberRoles(){
		return this.parser.getNumberElements();
	}
	
	
	/**
	 * This method returns the rol identification in a 
	 * position given in the xml code.
	 * @param n
	 * @return
	 */
	public String getRolID(int n){
		return this.parser.getElement(n, "id");
	}

}
