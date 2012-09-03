<%@page contentType="text/html; charset=iso-8859-1"
session="true" language="java" import="java.util.*" %>
<%
String userName = "";
HttpSession sessionOk = request.getSession();
if (sessionOk.getAttribute("userName") != null) {
%>
<jsp:forward page="welcome.jsp"/>
<%
} 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
<title>DICODE Workbench</title>
<meta http-equiv="Content-Type" content="text/html;charset=iso-8859-1" />
<link rel="stylesheet" type="text/css" href="images/style.css" />
<link rel="shortcut icon" href="images/favicon.ico"/>

</head>
<body>

	<div id="main_div">
		<jsp:include page="head.jsp"/>
		<div id="main_content">
			<div id="left_column">
			<jsp:include page="login.jsp"/>
			</div>
			<div class="content">
				<div class="content_title"></div>
				<div class="main_text">
					<img src="images/banner650.png" alt="Welcome to the Dicode Framework!"/>
					<p>The Dicode Workbench is an integration framework that enables researches to carry out collaboratively experiments. The workbench integrates heterogeneous services and tools 
						from different scientific disciplines such as Bioinformatics or Medical Informatics within the same environment.</p>
					<p>Sign up now in the system to start using all the functionalities!!</p>
				</div>
			</div>
		</div>
		<jsp:include page="foot.jsp"/>
		
	</div>
</body>
</html>