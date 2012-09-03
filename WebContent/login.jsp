	<div id="left_top">
				</div>
				<div class="left_content">
					<div class="menu_box">
						<h3>Login</h3>
						<div id="text_desno">
						<FORM method="post" action="Login">
						<table align="center" >
						<tr>
							<td id="leftMenu">User</td>
   							<td> <% String user;
   							if (request.getAttribute("errorPass")!=null){
 								user = request.getAttribute("user").toString();
 							}
   							else if(request.getAttribute("error")!=null){
   								user = request.getAttribute("userInv").toString();
   							}
 							else{user ="";
 							}%>
 							<input name="UName" type="text" size="12" maxlength="45" value="<%= user%>">
  							</td>
						</tr>
						<tr>
							<td id="leftMenu">Password</td>
   							<td> <% String pass;
   							if (request.getAttribute("errorName")!=null){
 								pass = request.getAttribute("pass").toString();
 							}
 							else{pass ="";
 							}%>
 							<input name="Lpassword" type="password" size="12" maxlength="20" value="<%= pass%>">
  							</td>
						</tr>
						<%
							if(request.getAttribute("errorName")!=null)
							{
								out.println("<tr>\n<td colspan=\"2\" id=\"mandatory\">"+request.getAttribute("errorName").toString()+"</td>\n</tr>\n");
							}
						%>
						<%
							if(request.getAttribute("errorPass")!=null)
							{
								out.println("<tr>\n<td colspan=\"2\" id=\"mandatory\">"+request.getAttribute("errorPass").toString()+"</td>\n</tr>\n");
								
							}
						%>
 						<%
							if(request.getAttribute("error")!=null)
							{
								out.println("<tr>\n<td colspan=\"2\" id=\"mandatory\">"+request.getAttribute("error").toString()+"</td>\n</tr>\n");
							}
						%>
						<tr>
    						<td align="right" colspan="2">
    								<a class="forget" href="remember.jsp">Forgot password?</a>
    								&nbsp;&nbsp;
							  		<input type="submit" value="Login">
							  </td>
 						</tr>
						</table>
						</FORM>

						<div class="hr">
							<hr/>
						</div>
						<p align="center">Need an account? <a href="registration.jsp" id="blueLink">Sign up</a></p>
						
						</div>
					</div>
					
				</div>
				
				<div id="left_bottom">
				</div>

