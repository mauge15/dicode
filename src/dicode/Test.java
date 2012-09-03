package dicode;

import java.io.IOException;


public class Test {
public static void main(String[]args) throws IOException
{
	//ServicesManager1 manS = new ServicesManager1();
	//String res = manS.deleteServiceInExperiment("23");
	//res = manS.deleteServiceInExperiment("23", "1");
	//String res = manS.deleteService("23");
	
	ExperimentsManager1 manE = new ExperimentsManager1();

	/*String res = manE.userPermissionInExperiment("19", "14");
	SingleUserPermissionInExperimentParser parser = new SingleUserPermissionInExperimentParser(res);
	System.out.println("ID permiso "+parser.getPermissionID());*/
	//String res = manE.addExperiment("Document+Viewer+Tests", "Testing+purposes", "19", "1", "32","", "Document Mining");
	
	//String res = manS.addService("19", "Forum Summarization", "Forum Summarization","la descripcion blalala", "http://imash.leeds.ac.uk/dicode/wp4/Stats2-Forum/Topic_Clouds.html", "1", "2", "1", "");
	
	String res = manE.deleteExperiment("13");
	System.out.println("asfaff");
	System.out.println("Las respuesta es "+res);
	/*
	String res = manE.addUserPermissionInExperiment("19", "14", "3");
	
	//String res = manE.deleteExperiment("15");
	//System.out.println("REs "+res);
	//String res = manE.saveUserWidgetConfiguration("19", "14", "1", "2", "3");
	//String res = manE.deleteUserWidgetConfiguration("19","26","1");
	//System.out.println("Resultado "+res);
	
	*/
}

    
}
