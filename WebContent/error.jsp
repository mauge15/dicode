<%@page contentType="text/html; charset=iso-8859-1"
session="true" language="java" import="java.util.*" %>
<%
String userName = "";
HttpSession sessionOk = request.getSession();
String title = (request.getParameter("title") != null) ? request.getParameter("title") : "An error has occurred!!";
String msg = (request.getParameter("msg") != null) ? request.getParameter("msg") : "An indetermined error has happened. Please, contact the system administrator";
String link = (request.getParameter("link") != null) ? request.getParameter("link") : "http://hodgkin.dia.fi.upm.es:8080/dicode";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
<title>DICODE Workbench - Error!</title>
<meta http-equiv="Content-Type" content="text/html;charset=iso-8859-1" />
<link rel="stylesheet" type="text/css" href="images/style.css" />
<link rel="shortcut icon" href="images/favicon.ico"/>
</head>

<body>
	<div id="main_div">
		<jsp:include page="head.jsp">
			<jsp:param name="userName" value="<%= userName%>"/>
		</jsp:include>
		<div id="main_content">
			<div id="left_column">
				<%
				if (sessionOk.getAttribute("userName") == null) {
				%>
				<jsp:include page="login.jsp"/>
				<% } else { %>
				<jsp:include page="menu.jsp"/>
				<% } %>
			</div>
			<div class="content">
				<div class="content_title"></div>
				<div class="text">
					<div id ="back">
						<h4><% out.print(title); %></h4>
						<div class="hr"> <hr/> </div>
						<br/>
						<p><% out.print(msg); %> </p>
						<p align="right"><% out.print("<a href=\"" + link + "\" style=\"font-weight: bold; color: blue;\">Go back...</a>"); %>
						</p>
						<br/>
	  				<div class="hr"> <hr/> </div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="foot.jsp"/>
		</div>
</body>
</html>