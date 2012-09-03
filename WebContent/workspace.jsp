<%@ page session="true" %>
<%@ page import="dicode.ServicesManager" %>
<%@ page import="dicode.ExperimentsManager" %>
<%@ page import="dicode.UsersManager" %>
<%@ page import="parsers.URLparser" %>
<%@ page import="parsers.ListServiceTypesParser" %>
<%@ page import="parsers.ListInvocationMethodsParser" %>
<%@ page import="parsers.ListDataTypesParser" %>
<%@ page import="parsers.ListCategoriesParser" %>
<%@ page import="parsers.UserPermissionInExperimentParser" %>
<%@ page import="parsers.UserServicesConfigurationParser" %>
<%@ page import="parsers.userParser" %>
<%@ page import="parsers.ListVisibilitiesParser" %>
<%@ page import="parsers.ExperimentParser" %>
<%@ page import="parsers.ServiceParser" %>
<%@ page import="parsers.ServicesInExperimentParser" %>
<%@ page import="parsers.WorkspaceInExperimentParser" %>
<%@ page import="parsers.SingleUserPermissionInExperimentParser" %>
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
String pass = "";
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
pass = (String)sessionOk.getAttribute("upss");
}

String expID = request.getParameter("id");
ExperimentsManager manE = new ExperimentsManager();
String res = manE.userPermissionInExperiment(ID, expID);
SingleUserPermissionInExperimentParser suser = new SingleUserPermissionInExperimentParser(res);
String perID = suser.getPermissionID();
if (perID.equals("0")) {
%>
<jsp:forward page="error.jsp">
<jsp:param name="msg" value="You have no permission to access to this 
workspace. Please, try to access it through workspaces page"/>
<jsp:param name="link" value="workspaces.jsp"/>
</jsp:forward>  
<%
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
<title>DICODE Workbench - Create Workspace</title>
<meta http-equiv="Content-Type" content="text/html;charset=iso-8859-1" />
<link rel="stylesheet" type="text/css" href="images_exp/style2.css" />
<link rel="stylesheet" type="text/css" href="images_exp/widgetContainer.css" />
<link rel="shortcut icon" href="images/favicon.ico"/>
<script src="newcollaborative/newcollaborative.nocache.js"></script>
<link type="text/css" href="css/ui-lightness/jquery-ui.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.21.custom.min.js"></script>
<script src="js/dragdrop.js"></script>
<script src="js/expLayers.js"></script>
<script src="js/serviceWidget.js"></script>
<script src="js/collaborative.js"></script>
<script src="js/popUpContent.js"></script>
<script src="js/timer.js"></script>
<script type="text/javascript">
	var executedAction = false;
	var changed=false;
	var listL = ['Mind-map view','Forum view', 'Argumentation view'];
	var origLength = 5;
	var listST;
	var listDT;
	var listC;
	var listGU;
	var listVis;
	var expInfo;
	var services;
	var perm;
	var collaboratives;
	var change = false;
    
	<%
	String listST = "";
	String listDT = "";
	String listC = "listC = [['0', 'Indifferent'],";
	String listGU = "";
	out.println("var usrID = "+ID+";");
	out.println("var userName=\'"+userName+"\';");
	
	out.println("var pass=\'"+pass+"\';");
	String id = request.getParameter("id");
	out.println("var expID = "+id+";");
	String nm = request.getParameter("nm");
	//To obtain service types
	ServicesManager manS = new ServicesManager();
	String xml="";
	int count;
	
	String path = URLparser.giveContext();
	out.println ("var path = '"+path+"/';");
	
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
	
	String listVis = "listVis = [";
	xml = manE.listVisibilities();
	ListVisibilitiesParser pars8 = new ListVisibilitiesParser(xml);
	int nVis = pars8.getNumberVisibilities();
	for (count=0; count < nVis; count++) {
		listVis += "['"+pars8.getID(count)+"', '"+pars8.getName(count)+"']";
		if (count != nVis-1) listVis+=", ";
	}
	listVis+="];";
	out.println(listVis);
	
	
	xml = manE.experiment(id);
	ExperimentParser pars9 = new ExperimentParser(xml);
	
	String expName = pars9.getName();
	String desc = pars9.getDescription();
	String vis = pars9.getVisibilityID();
	String cDate = pars9.getCreationDate();
	String group = pars9.getGroup();
	//FALTA MODIFICATION DATE
		

	//Servicios Por Usuario

	xml = manU.userWidgetConfiguration(ID,id);
	UserServicesConfigurationParser pars20 = new UserServicesConfigurationParser(xml);
	int nSInExp = pars20.getNumberUserServices();
	String services="services = [";
	ServiceParser pars11;
	String title = "";
	for (count=0; count < nSInExp; count++) {
		xml = manS.service(pars20.getServiceID(count));
		pars11 = new ServiceParser(xml);
		String alias = pars11.getShortName();
		alias = alias.trim();
		if (!alias.equals(""))
		{
			title = alias;
		}
		else
		{
			
			title = pars11.getName();
			if (title.length()>15)
				{
					title = title.substring(0,15);
				}
		}
		services+="['"+pars20.getServiceID(count)+"','"+pars20.getColID(count)+"','"+pars20.getRow(count)+
		            "',['"+title+"','"+pars11.getURI()+"']]";
		if (count != nSInExp-1) services+=", ";
	}
	services+="];";
	//Fin SErvicios Por Usuario
	
	
	String expInfo = "expInfo = ['"+expName+"','"+desc+"','"+vis+"','"+cDate+"','"+id+"', '"+group+"'];";
	sessionOk.setAttribute("typeID", group);
	sessionOk.setAttribute("expID", id);
	out.println(expInfo);
	out.println(services);
	
	xml=manE.workSpacesInExperiment(id);
	WorkspaceInExperimentParser pars12 = new WorkspaceInExperimentParser(xml);
	int nWS = pars12.getNumberWorkspaces();
	System.out.println("Numero de Workspaces: "+nWS);
	String collaboratives = "collaboratives = [";
	if (nWS==0) {
		collaboratives+="['null,null,null']";
	}
	for (count=0; count < nWS; count++) {
		collaboratives+= "['"+pars12.getID(count)+"',['"+pars12.getCview(count)+
		                   "','"+pars12.getFview(count)+"','"+pars12.getAview(count)+
		                   "'],'"+pars12.getName(count)+"']";
		if (count != nWS-1) collaboratives+=", ";
	}
	collaboratives+="];";
	if (nWS==0) {
		collaboratives="collaboratives=[['1',[null],'CW test 1']]";
	}
	out.println(collaboratives);
	out.println("var cwID;");
	%>
</script>
<script src="js/browser.js"></script>
<script src="js/workspace.js"></script>
</head>

<body onload="timerMsg(); createCW(0, collaboratives[0][1]); loadServices(); timer();listener();">

<div id="dialog-modal" title="DICODE Message" style="display:none;">
	<p>The configuration has been saved.</p>
</div>

<div id="popup" style="display: none;">
      <iframe id="popupf"></iframe>
</div>

<div id="dialog-help" title="DICODE Help" style="display: none;">
       <h1>WORKSPACE HELP</h1>
       <h2>Drag & Drop</h2>
       <p>You can Drag some elements in the workspace.
       These elements are some hyperlinks in the widgets.
          You can Drag these elements to the collaborative enviroment in the middle of the screen. And also
          you can Drag these elements into two different widgets. </p>
</div>

<div id="dialog-search-service" title="Search Services" style="display: none;">
	<iframe width="450" height="450" frameborder="0" marginheight="0" marginwidth="0" framespacing="0" border="0" 
	top="0" src="<%=path%>/searchServices.jsp?width=450&height=450&color=0066bb&expid=<%=expID%>"></iframe>
</div>

<div id="dialog-close-workspace" title="Leaving Workspace" style="display: none;">
<p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>Do you want to save configuration before closing?</p>
</div>

<div id="dialog-workspace-info" title="Workspace Info" style="display: none;">
	<iframe width="480" height="540" frameborder="0" marginheight="0" marginwidth="0" framespacing="0" border="0" 
	top="0" src="<%=path%>/workspaceInfo.jsp?id=<%=id%>"></iframe>
</div>

<div id="ppPanelGlass" class="ppPanelGlass"></div>
<div id="dialogBoxPopUp" class="dialogBoxPopUp"></div>
<div id="loadPanelGlass" class="loadPanelGlass"></div>

<div id="main_div">
	<jsp:include page="head.jsp"><jsp:param name="userName" value="<%= userName%>"/></jsp:include>
		
	<div id="chromemenu2">
		<ul>
			<li><a href="#" onclick="add_service();">Add Services</a></li>
	        <li><a href="#" onclick="save_config('<%=ID%>')">Save Config</a></li>
	        <li><a href="#" onclick="workspace_info();">Workspace Info</a></li>
	        <li><a href="#" onclick="help();">Help</a></li>
	        <li><a href="#" onclick="closeSession(change,'<%=ID%>')">Exit</a></li>
	    </ul>
	</div>
			
	<div id="main_content">
		<div id="left_column"><!-- Filled dynamically --></div>
		<div id="right_column"><!-- Filled dynamically --></div>
				
		<div class="center_content_collab" id="drag_200" serviceid="0" posicion="0,0" uri="#">
			<div class="headerWidget_big" id="header200">
				<div id="title200" class="titleWidget_big">
					<div class="divName_collab"><h3 class="experiment">Collaborative Workspace</h3></div>
				</div>
			</div>
		
		<div class="text_desno" id="srvWidget200">
			<div id="layDD6" class="divLay" ondragenter="cancel(event)" ondrop="drop(event,'6')" ondragover="cancel(event)"></div>
			<div id="center_CW">
				<div id="cWOptions" class="cWOptions">
					<div id="newCollaborativeButton"></div>
					<div id="cWSelect"></div>
				</div>
				<div id="center_column"></div>
			</div>
		</div>		
	</div>
</div>

<div id="footer">
	<div class="footer_text">Copyright &copy; 2011 Dicode project * <a href="http://dicode-project.eu/">About DICODE</a> * <a href="mailto:info@dicode-project.eu">Contact</a> 
		<div class="footer_img">
			<a href="http://jigsaw.w3.org/css-validator/check/referer">
    		<img style="border:0;width:50px" src="http://jigsaw.w3.org/css-validator/images/vcss" alt="¡CSS Válido!" />
			</a>
		</div>
	</div>
</div>
</div>

<div id="widgetContainer"></div>
</body>
</html>