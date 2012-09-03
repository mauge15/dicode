package dicode;

import java.io.File;

public class openFile {

	private  File XML;
	
	public openFile (String name){
		this.XML = new File(name);
	}
	
	public File getFile(){
		return this.XML;
	}
	
	public void deletetmp(){
		this.XML.delete();
	}
}
