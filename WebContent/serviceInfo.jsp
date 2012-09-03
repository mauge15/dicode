<%@ page session="true" import="java.util.*" %>
<%@ page import="dicode.ServicesManager" %>
<%@ page import="parsers.URLparser" %>
<%@ page import="parsers.ServiceParser" %>
<%@ page import="parsers.ListCategoriesParser" %>
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
<script src="js/serviceDisplay.js"></script>
<script type="text/javascript">
  var srvID;
	var name;
	var uri;
	var creation;
	var modification;
	var desc;
	var tags;
	var category;
	<%
	String srvID = (String)request.getParameter("srv");
	sessionOk.setAttribute("srvID", srvID);
	String name = (String)request.getParameter("srvName");
	String title;
	String msg;
	String catID;
	String category = "";
	String [] taglist;
	String tags;
	String contextPath = URLparser.giveContext();
	ServicesManager man = new ServicesManager();
	//Check if there is a service id or name parameter
	if (srvID==null || name==null) {
		title = "Invalid operation";
		response.sendRedirect(response.encodeRedirectURL(contextPath + 
				"/error.jsp?title=" + title + "&link=" + contextPath +
				"/services.jsp"));
	} else {
		//Check if it is a valid service
		//String result = man.isService(name, "test");
		String result = srvID;
		if (result.equals("0")) {
			title = "Invalid operation";
			msg = "The service '"+name+"' is not registered";
			response.sendRedirect(response.encodeRedirectURL(contextPath +
					"/error.jsp?msg=" + msg + "&title=" + title + "&link=" + contextPath +
					"/services.jsp"));
		} else {
			if (!result.equals(srvID)) {
				msg = "The service '" + name + "' can not be loaded. Please, contact" +
			      "the Dicode Workbench administrator (" + result + ")";
				response.sendRedirect(response.encodeRedirectURL(contextPath +
						"/error.jsp?msg=" + msg + "&link=" + contextPath +
						"/services.jsp"));
			} else {

				String xml;
	
				//Get the rest of the information
				xml = man.service(srvID);
				ServiceParser pars1 = new ServiceParser(xml);
				name = "name = '"+pars1.getName()+"';";
				String desc = "desc = '"+pars1.getDescription()+"';";
				String alias = "alias = '"+pars1.getShortName()+"';";
				String creation = "creation = '"+pars1.getCreationDate()+"';";
				String modification = "modification = '"+pars1.getModificationDate()+"';";
				String uri = "uri = '"+pars1.getURI()+"';";
				// Check if the uploaderuser is viewing the service information
				if (pars1.getUploaderUser().equals(ID))
					out.println("var pr = 100;");
				catID = pars1.getCategoryID();
				int i;
				// Loop for tags
				taglist = pars1.getTags().split(",");
				tags = "tags = [";
				if (taglist.length>0) {
				  for (i=0; i< taglist.length; i++) {
					  tags += "'"+taglist[i]+"'";
					  if (i!=taglist.length-1)
					    tags += ", ";
					}
				}
				tags += "];";
				// Get the category
				xml = man.listCategories();
				ListCategoriesParser pars2 = new ListCategoriesParser(xml);
				int count = pars2.getNumberCategories();
				for (i=0;i<count;i++) {
					if (pars2.getID(i).equals(catID))
						category = "category = '" + pars2.getName(i) + "';";
				}
				
				out.println("srvID = " + srvID + ";");
				out.println(alias);
				out.println(name);
				out.println(desc);
				out.println(creation);
				out.println(modification);
				out.println(uri);
				out.println(tags);
				out.println(category);
			}
		}
	}
	%>
	
</script>
</head>
<body>
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
					<div id="back">
					</div>
					<div id="permButts">
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="foot.jsp"/>
	</div>
</body>
</html>