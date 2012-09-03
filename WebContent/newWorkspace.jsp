<%@ page session="true" %>
<%@ page import="java.io.*" %>
<%@ page import="dicode.UsersManager" %>
<%@ page import="parsers.ListUsersParser" %>
<%@ page import="parsers.userParser" %>
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
<title>DICODE Workbench - Create Workspace</title>
<meta http-equiv="Content-Type" content="text/html;charset=iso-8859-1" />
<link rel="stylesheet" type="text/css" href="images/style.css" />
<link rel="shortcut icon" href="images/favicon.ico"/>
<link rel="stylesheet" href="themes/base/jquery.ui.all.css"/>
<script src="js/jquery-1.7.1.js"></script>
<script src="ui/jquery.ui.core.js"></script>
<script src="ui/jquery.ui.widget.js"></script>
<script src="ui/jquery.ui.mouse.js"></script>
<script src="ui/jquery.ui.draggable.js"></script>
<script src="ui/jquery.ui.sortable.js"></script>
<script src="ui/jquery.effects.core.js"></script>
<script src="ui/jquery.ui.position.js"></script>
<script src="ui/jquery.ui.dialog.js"></script>
<script src="js/newExperiment.js"></script>
<script src="js/combo.js"></script>
<script type="text/javascript">
  var listU;
  <%
  String listU = "listU = [";
  
  UsersManager man = new UsersManager();
  String xml = man.listUsers();
  
  ListUsersParser pars = new ListUsersParser(xml);
  int nU = pars.getNumberUsers();
  int count;
  userParser pars2;
  String uid;
  for (count=0; count < nU; count++) {
    uid =pars.getID(count);
    if (!ID.equals(uid)) {
      xml = man.user(uid);
      pars2 = new userParser(xml);
      listU += "['"+uid+"', '"+pars2.getUserName()+"']";
      if (count != nU-1)
        listU+=", ";
    }
  }
  listU += "];";

  out.println(listU);
  %>
</script>
</head>
<body onload="initPage();hideObject('usersData');">
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
            <h4>Creation of a new workspace</h4>
            <form name="workspace" method="get" action="CreateExperiment" id="newWorkspace">
              <div>
                <div id="basicData">
                  <table class="newExperimentTable">
                    <tr>
                      <td>Name:</td>
                      <td>
                        <input class="longInput2" id="workspaceName" name="nm" type="text" size="50" maxlength="50"/>
                        <span id="workspaceNameError" class="error">Workspace name must be defined</span>
                      </td>
                    </tr>
                    <tr>
                      <td>Description:</td>
                      <td>
                        <textarea class="description2" name="ds" maxlength="150" onfocus="if(this.value=='Insert your description here...')this.value=''">Insert your description here...</textarea>
                        <div class="info_caracteres" id="lowerInfo"><span>150</span> characters left</div>
                      </td>
                    </tr>
                    <tr>
                      <td>Tags:</td>
                      <td>
                        <input class="longInput2" name="tgs" type="text" size="50" maxlength="50"/>
                        <div id="lowerInfo"><span>Separate tags with commas</span></div>
                      </td>
                    </tr>
                    <tr>
                      <td>Domain:</td>
                      <td>
                        <select name="dm" id="domain">
                          <option value="" disabled="disabled" selected="selected">Select a domain</option>
                          <optgroup label="Biological">
                            <option>DNA</option>
                            <option>RNA</option>
                            <option>Expression</option>
                            <option>Gene</option>
                            <option>Microarray</option>
                            <option>Genome</option>
                            <option>Phylogenetics</option>
                            <option>Polymorphism</option>
                            <option>Protein</option>
                          </optgroup>
                          <optgroup label="Medical">
                            <option>Image</option>
                            <option>Literature</option>
                            <option>eHealth</option>
                            <option>Nursing</option>
                            <option>Public health</option>
                            <option>HIS</option>
                            <option>Disease</option>
                            <option>Wearables</option>
                            <option>EHR</option>
                            <option>Anatomy</option>
                          </optgroup>
                          <optgroup label="Information mining">
                             <option>Data mining</option>
                             <option>Text mining</option>
                             <option>Web mining</option>
                           </optgroup>
                           <optgroup label="Information mining - Text mining">
                             <option>Document mining</option>
                             <option>Opinion mining</option>
                             <option>Social media mining</option>
                           </optgroup>
                           <optgroup label="Information mining - Web mining">
                             <option>Web content mining</option>
                             <option>Web structure mining</option>
                             <option>Web usage mining</option>
                           </optgroup>
                         </select>
                         <span id="domainError" class="error">Workspace domain must be defined</span>
                       </td>
                     </tr>
                   </table>
                 </div>
                 <div id="visibilityData">
                   <div>
                     <table  class="expUsers">
                       <tr>
                         <td><label>Visibility</label></td>
                         <td><input type="radio" value="1" name="vs" id="vs_public" onclick="hideObject('usersData');" checked=checked/>Public</td>
                         <td><input type="radio" value="2" name="vs" id="vs_restricted" onclick="showObject('usersData');" />Restricted</td>
                       </tr>
                     </table>
                   </div>
                   <div id="usersData">
                     <table  class="expUsers">
                       <tr>
                         <td><label>Users granted:</label></td>
                         <td></td>
                         <td><label>All users:</label></td>
                       </tr>
                       <tr>
                         <td>
 
                           <table id="usersGranted">
                           <thead><tr><th>User name</th><th>Full Control</th><th>Actions</th></tr></thead>
                           <tbody></tbody>
                           </table>
                         </td>
                         <td class="vertical">
                           <input name="add" type="button" value="&laquo;" onclick="addItem2('allUsers', 'usersGranted')"/>
   
                         </td>
                         <td>
                           <select name="allUsers" id="allUsers" multiple="multiple" size="5">
                             <option disabled="disabled" value="-1">-- Select users --</option>
                           </select>
                         </td>
                       </tr>
                     </table>
                   </div>
                 </div>
                 <div id="hidden_granted"></div>
                 <div id="createButton">
                   <table class="expUsers">
                     <tr>
                       <td>
                         <input type="submit" value="Create Workspace" onclick="selectItems();"/>
                       </td>
                     </tr>
                   </table>
                 </div>
               
               </div>
             </form>
           </div>
         </div>
       </div>
     </div>
     <jsp:include page="foot.jsp"/>
   </div>
 </body>
</html>