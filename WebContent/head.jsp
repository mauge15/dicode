<div id="header">
	<div id="left">
		<a href="welcome.jsp" rel="index">
			<img class="leftImg" src="images/logo_dicode.png" alt="dicode">
		</a>
	</div>
	<% if ((request.getParameter("userName")!= null) && (!request.getParameter("userName").equals(""))) { %>
	<div id="right">
		Hello, <a href="Profile"><%= request.getParameter("userName").toString() %></a>
	</div>
	<%} %>
</div>
