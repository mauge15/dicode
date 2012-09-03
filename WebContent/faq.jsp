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
 <title>DICODE Workbench - FAQs</title>
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
				<jsp:include page="menu.jsp"/>
			</div>
			<div class="content">
				<div class="content_title"></div>
				<div class="text">
					<h1>Frequently Asked Questions</h1>
					<p>This section is devoted to solve common questions and problems related with the Dicode framework use</p>
					<div class="hr"> <hr/> </div>
					<p class="question"><span class="questionTitle">Question 1: </span>Is it possible to change my username?</p>
				  <p class="answer"><span class="answerTitle">Answer 1: </span>No, it cannot be modified. The username is an unique identifier for each user within the framework. Whether the user needs a different username, a new account must be created</p>
					<div class="hr"> <hr/> </div>
					<p class="question"><span class="questionTitle">Question 2: </span>Can I modify my profile information?</p>
				  <p class="answer"><span class="answerTitle">Answer 2: </span>Yes, all the profile information can be updated in the <a href="profile.jsp">profile section</a></p>
					<div class="hr"> <hr/> </div>
					<p class="question"><span class="questionTitle">Question 3: </span>How can I change my password?</p>
				  <p class="answer"><span class="answerTitle">Answer 3: </span>The password can be changed within the <a href="profile.jsp">profile section</a>. The current and the new passwords are required to perform the change</p>
				</div>
			</div>
		</div>
				<jsp:include page="foot.jsp"/>
	</div>
</body>
</html>