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
<title>DICODE Workbench - Forgot password</title>
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
				<div class="text">
					<h4>Did you forget your password?</h4>
  				<div class="hr"> <hr/> </div>
					<form method="post" action="Mail">
						<table>
							<tr><td>&nbsp;</td></tr>
							<tr>
								<td colspan="3">
									<p>To re-establish the password, please type the email address that you used in the registration process</p>
								</td>
							</tr>
							<tr>
								<td>E-mail address: </td>
								<td><input name="newMail" type="text" size="50"/></td>
	  						<td><input type="submit" value="Submit"/></td>
	  					</tr>
							<tr><td>&nbsp;</td></tr>
	  					<%
								if(request.getAttribute("errorMail")!=null)
								{
									out.println("<tr>\n<td colspan=\"2\" id=\"mandatory\">"+request.getAttribute("errorMail").toString()+"</td>\n</tr>\n");
								}
							%>
	  				</table>	
  				</form>
  				<div class="hr"> <hr/> </div>
					<p>If you need a new account, please <a href="registration.jsp" id="blueLink">sign up</a></p>
				</div>
			</div>
		</div>
		<jsp:include page="foot.jsp"/>
	</div>
</body>
</html>