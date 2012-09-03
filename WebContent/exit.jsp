<%@ page session="true" %>
<%@ page import="dicode.CopeRESTClient" %>
<%
HttpSession sessionOk = request.getSession();
String userName = "";
String service = "";
if (sessionOk.getAttribute("userName") == null) {
%>
	<jsp:forward page="index.jsp">
	<jsp:param name="error" value="Login is needed"/>
	</jsp:forward>
<%
} else {
	userName = (String)sessionOk.getAttribute("userName");
	try {
    //System.out.print(CopeRESTClient.copeItLogin(userName,password));
    service = CopeRESTClient.copeItLogout(userName);
  } catch (Exception e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  } finally {
    sessionOk.invalidate();
  }
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
      copeItCall('logout', '<%=service%>');
    </script>
  </head>
  <body>
    <jsp:forward page="index.jsp"/>
  </body>
</html>
