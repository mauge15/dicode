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
 <title>DICODE Framework - Registration</title>
<meta http-equiv="Content-Type" content="text/html;charset=iso-8859-1" />
<link rel="stylesheet" type="text/css" href="images/style.css" />
<link rel="shortcut icon" href="images/favicon.ico"/>


<script type="text/javascript" language="JavaScript">
var passwords = false;
var mails = false;
var validM = false;
var validUN = false;
function checkPassword() {
		passwords=false;
	   var pass = document.register.password.value;
	   var Cpass = document.register.Cpassword.value;
	   var obj = document.getElementById("check");
	   if (pass!=Cpass) {
		   obj.style.visibility = "visible";
		   passwords=true;
		   document.register.submitReg.disabled = true;
	   }
	   else{
		   if (!mails|| !validM || !validUN )
		   document.register.submitReg.disabled = false;
			obj.style.visibility = "hidden";
	   }
	}



function checkMail(){
	mails=false;
	var mail = document.register.mail.value;
	var Cmail = document.register.Cmail.value;
	var obj = document.getElementById("CheckMail");
	if(mail!=Cmail){
		mails=true;
		obj.style.visibility = "visible";
		document.register.submitReg.disabled = true;
	}
	else{
		if (!passwords && !validM && !validUN )
			document.register.submitReg.disabled = false;
		obj.style.visibility = "hidden";
	}
}
function IsMail(){
	validM=false;
	var correct = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i;
	var mail = document.register.mail.value;
	var obj = document.getElementById("ValidMail");
	if (mail.search(correct)==-1){
		document.register.submitReg.disabled = true;
		validM=true;
		obj.style.visibility = "visible";
	}
	else{
		if (!passwords && !mails && !validUN)
			document.register.submitReg.disabled = false;
		obj.style.visibility = "hidden";
	}
	
}
function IsUsrName(){
	validUN=false;
	var correct = /^[A-Z0-9._]+$/i;
	var uname = document.register.userName.value;
	var obj = document.getElementById("ValidUsrName");
	if (uname.search(correct)==-1){
		document.register.submitReg.disabled = true;
		validUN=true;
		obj.style.visibility = "visible";
	}
	else{
		if (!passwords && !validM && !mails)
			document.register.submitReg.disabled = false;
		obj.style.visibility = "hidden";
	}
	
}


</script>
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

				<h4>Create a new account</h4>
				<form method="post" action="Register" name="register">
					<table>
						<tr>
							<td width="120">Full Name: </td>
   						<td colspan="2">
   							<%String full="";
   							if(((request.getAttribute("errorUser"))!=null)||((request.getAttribute("errorMail"))!=null)||((request.getAttribute("errorCMail"))!=null)||((request.getAttribute("errorPassword"))!=null)||((request.getAttribute("errorCPassword"))!=null)){
   								full=request.getAttribute("full").toString();
   							}
   							%>
 								<input name="name" type="text" size="50" value="<%= full%>" maxlength="150"/>
  						</td>
  						<td id="mandatory">*</td>
						</tr>
						<%
							if(request.getAttribute("errorFull")!=null)
							{
								out.println("<tr>\n<td colspan=\"2\" id=\"mandatory\">"+request.getAttribute("errorFull").toString()+"</td>\n</tr>\n");
							}
						%>
						<tr>
							<td>User Name: </td>
   						<td colspan="2">
   							<%String userN="";
   							if(((request.getAttribute("errorFull"))!=null)||((request.getAttribute("errorMail"))!=null)||((request.getAttribute("errorCMail"))!=null)||((request.getAttribute("errorPassword"))!=null)||((request.getAttribute("errorCPassword"))!=null)){
   								userN=request.getAttribute("userN").toString();
   							}
   							%>
 								<input name="userName" type="text" size="50" value="<%= userN%>" maxlength="45" onblur="IsUsrName()"/>
  						</td>
  						<td id="mandatory">*</td>
  						<td id="ValidUsrName" style="visibility:hidden;">Must be Alphanumeric . or _</td>
						</tr>
						<%
							if(request.getAttribute("errorUser")!=null)
							{
								out.println("<tr>\n<td colspan=\"2\" id=\"mandatory\">"+request.getAttribute("errorUser").toString()+"</td>\n</tr>\n");
							}
						%>
						<%if(request.getAttribute("errorReg")!=null)
							{
								out.println("<tr>\n<td colspan=\"2\" id=\"mandatory\">"+request.getAttribute("errorReg").toString()+"</td>\n</tr>\n");
							} 
						%>
						
						<tr>
							<td>E-mail: </td>
   						<td colspan="2">
   							<%String mail="";
   							if(((request.getAttribute("errorFull"))!=null)||((request.getAttribute("errorUser"))!=null)||((request.getAttribute("errorCMail"))!=null)||((request.getAttribute("errorPassword"))!=null)||((request.getAttribute("errorCPassword"))!=null)){
   								mail=request.getAttribute("mail").toString();
   							}
   							%>
 								<input name="mail" type="text" size="50" value="<%= mail%>" maxlength="150" onblur="IsMail()"/>
  						</td>
  						<td id="mandatory">*</td>
  						<td id="ValidMail" style="visibility:hidden;">Invalid mail</td>
						</tr>
							<%
								if(request.getAttribute("errorMail")!=null)
								{
									out.println("<tr>\n<td colspan=\"2\" id=\"mandatory\">"+request.getAttribute("errorMail").toString()+"</td>\n</tr>\n");
								}
								if(request.getAttribute("errorReg2")!=null)
							{
								out.println("<tr>\n<td colspan=\"2\" id=\"mandatory\">"+request.getAttribute("errorReg2").toString()+"</td>\n</tr>\n");
							} 
						%>
						<tr>
							<td>Confirm e-mail: </td>
   						<td colspan="2">
   							<%String Cmail="";
   							if(((request.getAttribute("errorFull"))!=null)||((request.getAttribute("errorMail"))!=null)||((request.getAttribute("errorUser"))!=null)||((request.getAttribute("errorPassword"))!=null)||((request.getAttribute("errorCPassword"))!=null)){
   								Cmail=request.getAttribute("Cmail").toString();
   							}
   							%>
 								<input name="Cmail" type="text" size="50" value="<%= Cmail%>" maxlength="150" onblur="checkMail()"/>
  						</td>
  						<td id="mandatory">*</td>
  						<td id="CheckMail" style="visibility:hidden;"> Mails do not match </td>
							<%
								if(request.getAttribute("errorCMail")!=null)
								{
									out.println("<tr>\n<td colspan=\"2\" id=\"mandatory\">"+request.getAttribute("errorCMail").toString()+"</td>\n</tr>\n");
								}
							%>
   						
						</tr>
						<tr>
							<td>Password: </td>
   						<td colspan="2">
   							<%String password="";
   							if(((request.getAttribute("errorFull"))!=null)||((request.getAttribute("errorMail"))!=null)||((request.getAttribute("errorCMail"))!=null)||((request.getAttribute("errorUser"))!=null)||((request.getAttribute("errorCPassword"))!=null)){
   								password=request.getAttribute("password").toString();
   							}
   							%>
 								<input name="password" type="password" size="50" value="<%= password%>"  maxlength="20"/>
  						</td>
  						<td id="mandatory">*</td>
						</tr>
						<%
							if(request.getAttribute("errorPassword")!=null)
							{
								out.println("<tr>\n<td colspan=\"2\" id=\"mandatory\">"+request.getAttribute("errorPassword").toString()+"</td>\n</tr>\n");
							}
						%>
						<tr>
							<td>Confirm password: </td>
   						<td colspan="2">
   							<%String Cpass="";
   							if(((request.getAttribute("errorFull"))!=null)||((request.getAttribute("errorMail"))!=null)||((request.getAttribute("errorCMail"))!=null)||((request.getAttribute("errorPassword"))!=null)||((request.getAttribute("errorUser"))!=null)){
   								Cpass=request.getAttribute("Cpass").toString();
   							}
   							%>
 								<input name="Cpassword" type="password" size="50" value="<%= Cpass%>" maxlength="20" onblur="checkPassword()"/>
  						</td>
  						<td id="mandatory">*</td>
							<%
								if(request.getAttribute("errorCPassword")!=null)
								{
									out.println("<tr>\n<td colspan=\"2\" id=\"mandatory\">"+request.getAttribute("errorCPassword").toString()+"</td>\n</tr>\n");
								}
							%>
   						<td id="check" style="visibility:hidden;" align="center"> Passwords do not match </td>
						</tr>
						<tr>
							<td>Organization: </td>
   						<td colspan="2">
   							<input name="Organization" type="text" size="50" maxlength="45" />
  						</td>
						</tr>
						<tr>
							<td/>
							<td align="center">
    							<input name="submitReg" type="submit" value="Register"/>
    						</td>
							<td align="center">
    							<input name="resetButton" type="reset" value="Empty fields"/>
    						</td>
 						</tr>
					</table>
				</form>
					
				</div>
			</div>
		</div>
		<jsp:include page="foot.jsp"/>
		
	</div>
</body>
</html>