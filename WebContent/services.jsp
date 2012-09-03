<%@ page session="true" import="java.util.*" %>
<%@ page import="dicode.ServicesManager" %>
<%@ page import="parsers.ListCategoriesParser" %>
<%@ page import="parsers.ListServicesParser" %>
<%@ page import="parsers.ServiceParser" %>
<%@ page import="dicode.UsersManager" %>
<%@ page import="parsers.userParser" %>
<%
String userName = "";
Integer userID = 0;
String ID ="";
HttpSession sessionOk = request.getSession();
if (sessionOk.getAttribute("userName") == null) {
%>
<jsp:forward page="index.jsp">
<jsp:param name="error" value="Login is needed"/>
</jsp:forward>

<%
} else {
userName = (String)sessionOk.getAttribute("userName");

ID = (String)sessionOk.getAttribute("userID");
userID = Integer.parseInt(ID);
}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
<title>DICODE Workbench - Services</title>
<meta http-equiv="Content-Type" content="text/html;charset=iso-8859-1" />
<link rel="stylesheet" type="text/css" href="images/style.css" />
<link rel="shortcut icon" href="images/favicon.ico"/>
<style>
    a.remove-button {
        display:block;
        width:12px;
        height:16px;
        background:url(images/remove.gif);
        }
        
         a.disabled-remove-button {
        display:block;
        width:12px;
        height:16px;
        background:url(images/remove-disabled.gif);
        cursor: default;
        }
</style>
<script src="js/jquery-1.7.1.js"></script>
<script src="ui/jquery.ui.core.js"></script>
<script src="ui/jquery.ui.widget.js"></script>
<script src="ui/jquery.ui.mouse.js"></script>
<script src="ui/jquery.effects.core.js"></script>
<script src="ui/jquery.ui.position.js"></script>
<script src="ui/jquery.ui.dialog.js"></script>
<script src="js/experimentInfo.js"></script>
<script src="js/workspaces.js"></script>
<script src="js/serviceInfo.js"></script>
<script type="text/javascript">
	<% out.println("var userName ='"+userName+"'");%>
	var listC;
	var listIDs;
	var pages;
	<%
	String listC = "listC = [";
	ServicesManager man = new ServicesManager();
	UsersManager manU = new UsersManager();
	String xml = man.listCategories();
	
	ListCategoriesParser pars = new ListCategoriesParser(xml);
	int nC = pars.getNumberCategories();
	int count;
	for (count=0; count < nC; count++) {
		listC += "['"+pars.getID(count)+"', '"+pars.getName(count)+"']";
		if (count != nC-1) listC+=", ";
	}
	listC += "];";

	out.println(listC);
	
	String listIDs = "listIDs = [";
	xml = man.listServices();
	ListServicesParser pars2 = new ListServicesParser(xml);
	int nIDs = pars2.getNumberServices();
	//System.out.println("Numero de servicios encontrados: "+nIDs);
	ServiceParser pars3;
	String id;
	String desc;
	String date;
	String name;
	String cat;
	String user;
	String uploader;
	userParser pars4;
	for (count=0; count < nIDs; count++) {
		id=pars2.getID(count);
		xml= man.service(id);
		System.out.println("service ID: "+id);
		pars3 = new ServiceParser(xml);
		desc = pars3.getDescription();
		date = pars3.getCreationDate();
		name = pars3.getName();
		cat = pars3.getCategoryID();
		user = pars3.getUploaderUser();
		//System.out.println("Uploaderuser ID: "+user);
		xml = manU.user(user);
		pars4 = new userParser(xml);
		uploader = pars4.getUserName();
		//System.out.println("Uploaderuser name"+uploader);
		listIDs += "['"+id+"', '"+name+"', '"+desc+"', '"+date+"' , '"+uploader+"' , '"+cat+"']";
		if (count != nIDs-1) listIDs+=", ";
	}
	listIDs += "];";
	
	out.println(listIDs);
	%>
</script>
</head>
<body onload="generateLayers()">
	<div id="main_div">
		<jsp:include page="head.jsp">
			<jsp:param name="userName" value="<%= userName%>"/>
		</jsp:include>
		<div id="main_content">
			<div id="left_column">
				<jsp:include page="menu.jsp"/>
			</div>
			<div class="content">
				<div class="content_title"></div>
				<div class="text">
					<div id ="back">

					</div>
				</div>
			</div>
		</div>
		<jsp:include page="foot.jsp"/>
	</div>
</body>
</html>