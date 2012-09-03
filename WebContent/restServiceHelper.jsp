<%@ page import="dicode.ExperimentsManager" %>
<%@ page import="dicode.ServicesManager" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head></head><body>
<%

String op = request.getParameter("operation");
String res="";
if (op.equals("deleteServiceInExperiment"))
{
	ServicesManager manS = new ServicesManager();
	String srvID = request.getParameter("srvID");
	res = manS.deleteService(srvID);
}
else if (op.equals("deleteExperiment"))
{
	ExperimentsManager manE = new ExperimentsManager();
	String expID = request.getParameter("expID");
	res = manE.deleteExperiment(expID);
}
else if (op.equals("saveUserWidgetConfiguration"))
{
	ExperimentsManager manE = new ExperimentsManager();
	String expID = request.getParameter("expID");
	String usrID = request.getParameter("usrID");
	String srvID = request.getParameter("srvID");
	String col = request.getParameter("col");
	String row = request.getParameter("row");
	res = manE.saveUserWidgetConfiguration(usrID,srvID,expID,col,row);
}
else if (op.equals("updateUserWidgetConfiguration"))
{
	ExperimentsManager manE = new ExperimentsManager();
	String expID = request.getParameter("expID");
	String usrID = request.getParameter("usrID");
	String srvID = request.getParameter("srvID");
	String col = request.getParameter("col");
	String row = request.getParameter("row");
	res = manE.updateUserWidgetConfiguration(usrID,srvID,expID,col,row);
}
%>
<h2><%out.print(res);%></h2>
</body>
</html>