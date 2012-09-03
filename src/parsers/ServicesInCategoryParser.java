package parsers;

public class ServicesInCategoryParser {
	
	/**
	 * Private variable.
	 */
	private ListsParser parser;
	
	
	/**
	 * Constructor Method. It receives the XML code and
	 * writes it into a file for parse it.
	 * @param XML
	 */
	public ServicesInCategoryParser(String XML){
		this.parser = new ListsParser(XML,"service");
	}
	
	
	/**
	 * This method returns the number of service in one
	 * category for one experiment.
	 * @return
	 */
	public int getNumberServices(){
		return this.parser.getNumberElements();
	}
	
	
	/**
	 * This method returns the service id for a service
	 * in one category for one experiment in a position
	 * given in the XML code.
	 * @param n
	 * @return
	 */
	public String getID(int n){
		return this.parser.getElement(n, "id");
	}

}
