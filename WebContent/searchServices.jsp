<%@ page session="true" %>
<%@ page import="dicode.ServicesManager" %>
<%@ page import="dicode.ExperimentsManager" %>
<%@ page import="dicode.UsersManager" %>
<%@ page import="parsers.ListServiceTypesParser" %>
<%@ page import="parsers.ListInvocationMethodsParser" %>
<%@ page import="parsers.ListDataTypesParser" %>
<%@ page import="parsers.ListCategoriesParser" %>
<%@ page import="parsers.UserPermissionInExperimentParser" %>
<%@ page import="parsers.userParser" %>
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
<title>DICODE Workbench</title>
<meta http-equiv="Content-Type" content="text/html;charset=iso-8859-1" />
<link rel="stylesheet" href="themes/base/jquery.ui.all.css"/>
<script src="js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.21.custom.min.js"></script>
<link rel="stylesheet" type="text/css" href="images_exp/style2.css" />
<script src="js/srvSearch.js"></script>
<script type="text/javascript">
var listST;
var listDT;
var listC;
var listGU;
/*expFilters*/

var divST;
var selST;

var divC;
var selC;

var divDT;
var selDT;

var divGU;
var selGU;

var listVis;
var expInfo;
var perm;
<%
out.println("var usrID = "+ID);
String listST = "";
String listDT = "";
String listC = "listC = [['0', 'Indifferent'],";
String listGU = "";

String id = request.getParameter("expid");
out.println("var expID = "+id);

String nm = request.getParameter("nm");
//To obtain service types
ServicesManager manS = new ServicesManager();
String xml;

int count;
//To obtain categories
xml = manS.listCategories();

ListCategoriesParser pars5 = new ListCategoriesParser(xml);
int nC = pars5.getNumberCategories();
for (count=0; count < nC; count++) {
	listC += "['"+pars5.getID(count)+"', '"+pars5.getName(count)+"']";
	if (count != nC-1) listC+=", ";
}
listC += "];";

out.println(listC);

ExperimentsManager manE = new ExperimentsManager();
xml = manE.userPermissionInExperiment(id);

UserPermissionInExperimentParser pars6 = new UserPermissionInExperimentParser(xml);
int nU = pars6.getNumberExperimentPermissions();
String uid;
UsersManager manU = new UsersManager();
userParser pars7;
for (count=0; count < nU; count++) {
	uid=pars6.getUserID(count);
	xml = manU.user(uid);
	pars7 = new userParser(xml);
	listGU += "['"+uid+"', '"+pars7.getUserName()+"']";
	if (uid.equals(ID))
		out.println("perm ="+pars6.getPermissionValue(count)+";");
	if (count != nU-1) listGU+=", ";
}
listGU =  "listGU = [['0', 'Indifferent'], "+listGU+"];";

out.println(listGU);

	%>
	

</script>
<style>

.button1 /* Button with CSS only */  
    {  
        background: url(images/button.png) 0 0;  
        height:31px;  
        width:116px;  
        display:block;  
    }  
    .button1:hover /* mouseOver */  
    {  
        background: url(images/button.png) 0 31px;  
    }  

.button2, .button2 a {  
        background: url(images/button.png) 0 -31px;  
        height:31px;  
        width:116px;  
        display:block;  
    }  
    .button2 a {  
        background-position: 0 0;  
    }  

</style>
<script>
$(document).ready(function(){  
       $('.button2 a').hover(function(){  
       $(this).stop().animate({'opacity' : '0'}, 500);  
       }, function(){$(this).stop().animate({'opacity' : '1'}, 500);});  
    });  
</script>
</head>
<body onload="initSearch();">
<div id="dialog-modal" title="DICODE Message" style="display: none;">
	<p>The service has been added.</p>
</div>
	<div id="search" class="text_desno_search">
		<form name="services" onsubmit="showSearch();return false;">
			<div>
				<div class="block">
					<label>Name: </label><input name="name" type="text"/>
				</div>
				<div class="block">
					<label>Category: </label><div id="divC"></div>
				</div>
				<div class="show" id="showF">
					<a href="javascript:toggleLayers(['showF','hideF','hide'])"><span>Display filters</span>
					<img class="iconWrap" src="images_exp/plus_button.png"/></a>
				</div>
				<div class="show" id="hideF" style="display: none;">
					<a href="javascript:toggleLayers(['showF','hideF','hide'])"><span>Hide filters</span>
					<img class="iconWrap" src="images_exp/less_button.png"/></a>
				</div>
				<div class="hide" id="hide">
					<!--  <div class="block"><label>Return data type</label><div id="divDT"></div></div>-->
					<% if (nU!=0) {%>
					<div class="block">
						<label>Published by</label><div id="divGU"></div>
					</div>
					<% }%>
				</div>
	
               <p><a href="#" class="button1" onclick="showSearch()"></a></p> <!-- CSS only -->  
				<div class="hide" id="resultsBlock" style="display: none;">
					<div class="hr"><hr/></div><div id="sResults"></div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>