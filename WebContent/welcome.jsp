<%@ page session="true" %>
<%@ page import="com.sun.jersey.api.client.Client" %>
<%@ page import="com.sun.jersey.api.client.WebResource" %>
<%@ page import="com.sun.jersey.api.client.config.ClientConfig" %>
<%@ page import="com.sun.jersey.api.client.config.DefaultClientConfig" %>

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
 <title>Welcome</title>
<meta http-equiv="Content-Type" content="text/html;charset=iso-8859-1" />
<link rel="stylesheet" type="text/css" href="images/style.css" />
<link rel="shortcut icon" href="images/favicon.ico"/>
<script type="text/javascript" src="js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="js/jquery/jquery.serviceCall.js"></script>
<script type="text/javascript">
	copeItCall('login', '<%=sessionOk.getAttribute("service")%>');
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
					<h1>Welcome to the Dicode Workbench</h1>
					<p>
					  The Dicode workbench is an advanced web application enabling the integration of multiple tools under the same interface. It is based on a flexible software 
					  architecture to facilitate the adaptation of the user interface to different user communities and types of activities.
					</p>
					<p>
					  The Dicode workbench allows
  					<ul>
  						<li>using Dicode's tools and services in a web-based solution customizable the specific needs of the different scenarios (biological, medical, opinion mining...)</li>
  						<li>integrating and executing already existing tools and services</li>
  					</ul>
					</p>
					<p>Like the Dicode project, the Dicode workbench has been built using only open source technologies</p>
					<br/>
					<div class="hr"> <hr/> </div>
					<h2>News!!</h2>
					<p><i>No news at this moment...  <span style="color:#FF0000;">(27/07/2011)</span></i></p>
				</div>
			</div>
		</div>
				<jsp:include page="foot.jsp"/>
	</div>
</body>
</html>