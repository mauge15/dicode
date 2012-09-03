<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="true" %>
<%@ page import="dicode.ServicesManager" %>
<%@ page import="dicode.ExperimentsManager" %>
<%@ page import="dicode.UsersManager" %>
<%@ page import="parsers.ListServiceTypesParser" %>
<%@ page import="parsers.ListUsersParser" %>
<%@ page import="parsers.ListInvocationMethodsParser" %>
<%@ page import="parsers.ListExperimentPermissionsParser" %>
<%@ page import="parsers.ListDataTypesParser" %>
<%@ page import="parsers.ListCategoriesParser" %>
<%@ page import="parsers.UserPermissionInExperimentParser" %>
<%@ page import="parsers.UserServicesConfigurationParser" %>
<%@ page import="parsers.userParser" %>
<%@ page import="parsers.SingleUserPermissionInExperimentParser" %>
<%@ page import="parsers.ListVisibilitiesParser" %>
<%@ page import="parsers.ExperimentParser" %>
<%@ page import="parsers.ServiceParser" %>
<%@ page import="parsers.ServicesInExperimentParser" %>
<%@ page import="parsers.WorkspaceInExperimentParser" %>
<%@ page import="com.sun.jersey.api.client.Client" %>
<%@ page import="com.sun.jersey.api.client.WebResource" %>
<%@ page import="com.sun.jersey.api.client.config.ClientConfig" %>
<%@ page import="com.sun.jersey.api.client.config.DefaultClientConfig" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script>
<%
String expID = request.getParameter("id");
out.println("var expID = "+expID);
//To obtain service types
ExperimentsManager manE = new ExperimentsManager();
UsersManager manU = new UsersManager();
String xml = manE.experiment(expID);
ExperimentParser pars9 = new ExperimentParser(xml);
String expName = pars9.getName();
String desc = pars9.getDescription();
String vis = pars9.getVisibilityID();
String cDate = pars9.getCreationDate();
String group = pars9.getGroup();
%>
</script>
<script src="js/jquery-1.7.1.js"></script>
<script src="ui/jquery.ui.core.js"></script>
<script src="ui/jquery.ui.widget.js"></script>
<script src="ui/jquery.ui.mouse.js"></script>
<script src="ui/jquery.ui.draggable.js"></script>
<script src="ui/jquery.ui.sortable.js"></script>
<script src="ui/jquery.effects.core.js"></script>
<script src="ui/jquery.ui.position.js"></script>
<script src="ui/jquery.ui.dialog.js"></script>
<script src="js/workspaceInfo.js"></script>
<link rel="stylesheet" type="text/css" href="css/workspaceInfo.css" />
<title>Workspace Info</title>
<style>
.button1 /* Button with CSS only */  
    {  
        background: url(images/schangebutton.png) 0 0;  
        height:31px;  
        width:116px;  
        display:block;  
    }  
    .button1:hover /* mouseOver */  
    {  
        background: url(images/schangebutton.png) 0 31px;  
    }  

.button2, .button2 a {  
        background: url(images/schangebutton.png) 0 -31px;  
        height:31px;  
        width:116px;  
        display:block;  
    }  
    .button2 a {  
        background-position: 0 0;  
    }  
</style>
</head>
<body>

<div id="expInfo" class="text_desno_info">
<div class="text">
<p>
<b>Name: </b><%=expName%><br> 
<b>Description: </b><%=desc%><br>
<b>Visibility: </b><%=vis%><br>
<b>Creation Date: </b><%=cDate%><br>
<b>Users in Experiment: </b>
</p>
</div>
</div>
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
String res = manE.userPermissionInExperiment(ID, expID);
SingleUserPermissionInExperimentParser parser = new SingleUserPermissionInExperimentParser(res);
String level = parser.getPermissionID();
if (level.equals("3")) {
%>
<div id="wrap_div">
<table id="header_table_left"><thead>
<tr><th colspan="2">Users in Experiment</th></tr></thead>
<tr><th width="76px">User</th></tr></table>
</table>

<div id="left_table">
<table id="rounded-corner"><thead></thead>
<tbody>
<% 
	ArrayList<String> idUsers = new ArrayList<String>();
		String permission = manE.userPermissionInExperiment(expID);
		UserPermissionInExperimentParser userPermissionInfo = new UserPermissionInExperimentParser(permission);
		int nPermissions = userPermissionInfo.getNumberExperimentPermissions();  	
		for (int j=0; j<nPermissions;j++)
		{
			String idUser = userPermissionInfo.getUserID(j);
		  	String xmlUser = manU.user(idUser);
		  	idUsers.add(idUser);
			userParser userInfo = new userParser(xmlUser);
			String idPermission = userPermissionInfo.getPermissionID(j);
			String checked="";
			if (idPermission.equals(new String("3")) || idPermission.equals(new String("4")) )
			{
		    	out.println("<tr id=\"guser"+idUser+"\" user=\""+userInfo.getUserName()+"\"><td>"+userInfo.getUserName()+"</td></tr>");
			}
			
	    }
%>	
</tbody>
</table>
</div>
</div>
<%
}
else if (level.equals("4"))
{
%>
<div id="wrap_div">
<table id="header_table_left"><thead>
<tr><th colspan="2">Authorized Users</th></tr></thead>
<tr><th width="76px">User</th><th >Actions</th></tr></table>
</table>

<div id="left_table">

<table id="rounded-corner"><thead></thead>
<tbody>
<% 
ArrayList<String> idUsers = new ArrayList<String>();
String permission = manE.userPermissionInExperiment(expID);
UserPermissionInExperimentParser userPermissionInfo = new UserPermissionInExperimentParser(permission);
int nPermissions = userPermissionInfo.getNumberExperimentPermissions();  	
for (int j=0; j<nPermissions;j++)
{
	String idUser = userPermissionInfo.getUserID(j);
	String xmlUser = manU.user(idUser);
	userParser userInfo = new userParser(xmlUser);
	String idPermission = userPermissionInfo.getPermissionID(j);
	if (idPermission.equals("3") || idPermission.equals("4"))
	{
		idUsers.add(idUser);
		if (!idUser.equals(ID))
		{
			String checked="";
			if (idPermission.equals(new String("4")))
			{
				checked="checked=\"yes\"" ;
			}
			String a = "<a href=\"javascript:remove(\'"+idUser+"\')\"><img src=\"images/remove.gif\"/>&nbsp;Remove</a>";
	    	out.println("<tr id=\"guser"+idUser+"\" user=\""+userInfo.getUserName()+"\"><td width=\"30%\" >"+userInfo.getUserName()+"</td><td>"+a+"<br><input value=\""+idUser+"\" class=\"granted_users\" iduser=\""+idUser+"\" type=\"checkbox\""+checked+" name=\"chk_"+idUser+"\"/>Full Control</td></tr>");
		}
	}
}
%>	
</tbody>
</table>
</div>
</div>
<div id="center_division">
<p style="text-align:center" class="pdivision">
<input type="button" name="&lt;" value="&lt" onclick="addUsers();"/>
</p>
</div>
<div id="right_table">
<h3 class="table-header">Non authorized Users</h3>
<select name="sel_non_granted" id="sel_non_granted" multiple="multiple">

<% 
	String usersList = manU.listUsers();
	ListUsersParser parserUser = new ListUsersParser(usersList);
	int nUsers = parserUser.getNumberUsers();
	for (int i =0;i<nUsers;i++)
	{
		String idUser= parserUser.getID(i);
		if (!idUsers.contains(idUser))
		{
			String xmlUser = manU.user(idUser);
			userParser userInfo = new userParser(xmlUser);
		    out.println("<option value=\""+idUser+"\">"+userInfo.getUserName()+"</option>");
		}
	}		
%>	
</select>
</div>
  
<div style="clear:both">
<p><a href="#" class="button1" onClick="savePermissions('<%=expID%>');"></a></p> 
</div>
<%
}
%>
</body>
</html>