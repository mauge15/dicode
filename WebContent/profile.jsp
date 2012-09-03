<%@ page session="true" %>
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
<title>DICODE Workbench - Profile</title>
<meta http-equiv="Content-Type" content="text/html;charset=iso-8859-1" />
<link rel="stylesheet" type="text/css" href="images/style.css" />
<link rel="shortcut icon" href="images/favicon.ico"/>
<script type="text/javascript" language="javascript">
var passwords = true;
var validM = true;

function checkPassword() {
	passwords=false;
   var pass = document.passChange.nPassword.value;
   var Cpass = document.passChange.cNPassword.value;
   var obj = document.getElementById("hidden");
   if (pass!=Cpass) {
	   obj.style.visibility = "visible";
	   passwords=true;
	   document.passChange.submitPass.disabled = true;
   }
   else{
	   document.passChange.submitPass.disabled = false;
		obj.style.visibility = "hidden";
   }
}
function IsMail(){
	validM=false;
	var correct = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i;
	var mail = document.profile.mail.value;
	var obj = document.getElementById("ValidMail");
	if (mail.search(correct)==-1){
		document.profile.submitProf.disabled = true;
		validM=true;
		obj.style.visibility = "visible";
	}
	else{
		document.profile.submitProf.disabled = false;
		obj.style.visibility = "hidden";
	}
	
}
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
					<div id ="back">
						<h4>User profile</h4>
					
						<form name="profile" method="post" action="ChangeProfile">
						<table class="profile">
						<thead>
						<tr>
							<th class="col1"></th>
							<th class="col2"></th>
							<th class="col3"></th>
							<th class="col4"></th>
						</tr>
						</thead>
						<tr>
							<td>User Name</td>
   							<td><%= userName%></td>
						</tr>
						<tr>
							<td>Full Name</td>
   							<td>
   							<% String fullName = (String)request.getAttribute("fullName"); %> 
   							<input name="name" type="text" size="50" maxlength="150" value="<%= fullName%>"/>
  							</td>
  							<td id="mandatory">*</td>
  							<%
							if(request.getAttribute("errorFull")!=null)
							{
								out.println("<td colspan=\"2\" id=\"mandatory\">"+request.getAttribute("errorFull").toString()+"</td>\n");
							}
							%>
						</tr>
						<tr>
							<td>E-mail</td>
   							<td> 
 							<% String eMail = (String)request.getAttribute("eMail");%>
 							<input name="mail" type="text" size="50" maxlength="150" value="<%= eMail%>" onblur="IsMail()"/>
  							</td>
  							<td id="mandatory">*</td>
  							<%
							if(request.getAttribute("errorMail")!=null)
							{
								out.println("<td colspan=\"2\" id=\"mandatory\">"+request.getAttribute("errorMail").toString()+"</td>\n");
							} else {
								out.print("<td id=\"ValidMail\" style=\"visibility:hidden;\">Must be a valid mail</td>");
							}
  							%>
							</tr>
						<tr>
							<td>Organization</td>
   							<td> 
 							<% String organization = (String)request.getAttribute("organization");%>
 							<input name="organization" type="text" size="50" maxlength="45" value="<%= organization%>"/>
  							</td>
						</tr>
						<tr>
 							<td colspan="2" align="right" id="mandatory">* Mandatory fields</td>
 						</tr>
						<tr>
							  <td>
    							 <input name="submitProf" type="submit" value="Save changes"/>
    							</td>
 						</tr>
 						
 						</table>
 					</form>
 					<div class="hr">
						<hr/>
					</div>
 					<h4>Change password</h4>
 					<form name="passChange" method="post" action="ChangePassword">
						<table class="profile">
						<thead>
						<tr>
							<th class="col1"></th>
							<th class="col2"></th>
							<th class="col3"></th>
							<th class="col4"></th>
						</tr>
						</thead>
						<tr>
							<td>Password</td>
   							<td> 
 							<input name="password" type="password" size="50" maxlength="20"/>
  							</td>
   							<td id="mandatory">**</td>
  							<%
							if(request.getAttribute("errorPass")!=null)
							{
								out.println("<td id=\"mandatory\">"+request.getAttribute("errorPass").toString()+"</td>\n");
							}
							%>
						</tr>
						<tr>
							<td>New password</td>
   							<td> 
 							<input name="nPassword" type="password" size="50" maxlength="20"/>
  							</td>
  							<td></td>
  							<%
							if(request.getAttribute("errorNPass")!=null)
							{
								out.println("<td id=\"mandatory\">"+request.getAttribute("errorNPass").toString()+"</td>\n");
							}
							%>
						</tr>
						<tr>
							<td>Confirm new password</td>
   							<td> 
 							<input name="cNPassword" type="password" size="50" maxlength="20" onblur="checkPassword()"/>
  							</td>
  							<td></td>
  							<td id="hidden" style="visibility:hidden;" align="center"> 
 							Passwords do not match
  							</td>
						</tr>
						<tr>
 							<td colspan="2" align="right" id="mandatory">** Mandatory fields</td>
 						</tr>
						<tr>
							  <td>
    							 <input name="submitPass" type="submit" value="Save changes"/>
    							</td>
 						</tr>
 						
						</table>
 					</form>

				
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="foot.jsp"/>
	</div>
</body>
</html>