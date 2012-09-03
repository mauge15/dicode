package parsers;

public class ListExperimentsParser {
	
	/**
	 * private variable.
	 */
	private ListsParser parser;
	
	
	/**
	 * Constructor method of the class.
	 * @param xml
	 */
	public ListExperimentsParser (String xml){
		this.parser = new ListsParser(xml,"experiment");
	}
	
	
	/**
	 * 
	 * @return The number of experiments in the system.
	 */
	public int getNumberExperiments(){
		return this.parser.getNumberElements();
	}
	
	
	/**
	 * 
	 * @param n
	 * @return The experiment identification.
	 */
	public String getID(int n){
		return this.parser.getElement(n, "id");
	}
	
	
	/**
	 * 
	 * @param n
	 * @return The experiment visibility
	 */
	public String getVisibilityID (int n){
		return this.parser.getElement(n, "visibilityID");
	}
}
