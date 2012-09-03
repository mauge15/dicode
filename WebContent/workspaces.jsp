<%@ page session="true" %>
<%@ page import="dicode.ExperimentsManager" %>
<%@ page import="dicode.UsersManager" %>
<%@ page import="parsers.ListExperimentsParser" %>
<%@ page import="parsers.ExperimentParser" %>
<%@ page import="parsers.userParser" %>
<%@ page import="parsers.ListVisibilitiesParser" %>
<%@ page import="parsers.SingleUserPermissionInExperimentParser" %>
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
<title>DICODE Workbench - Workspaces</title>
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

<link rel="stylesheet" href="themes/base/jquery.ui.all.css"/>
<script>
<% out.println("var userName ='"+userName+"';");%>
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
<script src="js/experimentInfo.js"></script>
<script src="js/workspaces.js"></script>
<script type="text/javascript">
	var listIDs;
	var listVis;
	var pages;
	<%
	ExperimentsManager manE = new ExperimentsManager();
	String listIDs = "listIDs = [";
	String listVis = "listVis = [";
	String xml = manE.listVisibilities();
	ListVisibilitiesParser pars4 = new ListVisibilitiesParser(xml);
	int nVis = pars4.getNumberVisibilities();
	int count;
	for (count=0; count < nVis; count++) {
		listVis += "['"+pars4.getID(count)+"', '"+pars4.getName(count)+"']";
		if (count != nVis-1) listVis+=", ";
	}
	listVis+="];";
	out.println(listVis);
	
	xml = manE.listExperiments(); //listExperiments pendiente de incorporar
	ListExperimentsParser pars = new ListExperimentsParser(xml);
	int nIDs = pars.getNumberExperiments();
	ExperimentParser pars2;
	UsersManager manU = new UsersManager();
	userParser pars3;

	String expID;
	String desc;
	String date;
	String name;
	String vis;
	String creatorID;
	String perm;
	SingleUserPermissionInExperimentParser aux1;
	String aux;
	
	for (count=0; count < nIDs; count++) {
		expID=pars.getID(count);
		xml= manE.experiment(expID);
		aux = manE.userPermissionInExperiment(ID, expID);
		aux1 = new SingleUserPermissionInExperimentParser(aux);
		perm = aux1.getPermissionID();
		pars2 = new ExperimentParser(xml);
		desc = pars2.getDescription();
		date = pars2.getCreationDate();
		name = pars2.getName();
		vis = pars2.getVisibilityID();
		creatorID = pars2.getCreatorID();
		xml = manU.user(creatorID);
		pars3 = new userParser(xml);
		listIDs += "['" + expID + "', '" + name + "', '" + desc + "', '" + vis +
		             "', '" + date + "', '" + pars3.getUserName() + "', '" +
		             perm + "']";
		if (count != nIDs-1) listIDs+=", ";
	}
	listIDs += "];";
	out.println(listIDs);
	%>
</script>
</head>
<body  onload="generateLayers()">
<div id="dialog-no-permission" title="DICODE Message" style="display: none;">
	<p>The experiment you are trying to access is private.</p>
</div>
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