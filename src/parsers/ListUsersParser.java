package parsers;

public class ListUsersParser {

	/**
	 * Private Variable.
	 */
	private ListsParser parser;
	
	
	/**
	 * Constructor method. It receives the XML code and
	 * writes it into a file for parse it.
	 * @param XML
	 */
	public ListUsersParser (String XML){
		this.parser = new ListsParser(XML,"appUser");
	}
	
	
	/**
	 * This method returns the number of users in the
	 * system.
	 * @return
	 */
	public int getNumberUsers(){
		return this.parser.getNumberElements();
	}
	
	
	/**
	 * This method returns one user identification in 
	 * the position given in the XML code.
	 * @param n
	 * @return
	 */
	public String getID(int n){
		return this.parser.getElement(n, "id");
	}
}
