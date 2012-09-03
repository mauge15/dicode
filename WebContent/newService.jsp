<%@ page session="true" import="java.util.*" %>
<%@ page import="parsers.ListInvocationMethodsParser" %>
<%@ page import="dicode.ServicesManager" %>
<%@ page import="parsers.ListServiceTypesParser" %>
<%@ page import="parsers.ListDataTypesParser" %>
<%@ page import="parsers.ListCategoriesParser" %>
<%@ page import="parsers.ListParamTypesParser" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
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
<title>DICODE Workbench - New Service</title>
<meta http-equiv="Content-Type" content="text/html;charset=iso-8859-1" />
<link rel="stylesheet" type="text/css" href="images/style.css" />
<link rel="shortcut icon" href="images/favicon.ico"/>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="js/combo.js"></script>
<script type="text/javascript" src="js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="js/newService.js"></script>
<script type="text/javascript">
	var listC;
	<%
	String listC = "listC = [";
	//To obtain service types
	ServicesManager man = new ServicesManager();
	String xml;
	int count;
	
	//To obtain categories
	xml = man.listCategories();
	
	ListCategoriesParser pars5 = new ListCategoriesParser(xml);
	int nC = pars5.getNumberCategories();
	for (count=0; count < nC; count++) {
		listC += "['"+pars5.getID(count)+"', '"+pars5.getName(count)+"']";
		if (count != nC-1) listC+=", ";
	}
	listC += "];";

	out.println(listC);
	%>
</script>
</head>
<body onload="initPage()">
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
					<h4>Publish a new service</h4>
						<form name="service" id="formPublishService" method="post" action="PublishService">
							<table class="newServiceTable">
								<tr>
									<td>Name:</td>
									<td>
										<input name="nameService" id="nameService" type="text" maxlength="45" />
										<span id="nameServiceError" class="error">Service name must be defined</span>
									</td>
									
									<td>Type:</td>
									<td>
										<div id="divC"></div>
										<span id="categoryError" class="error">Service type must be defined</span>
									</td>
								</tr>
								<tr>
									<td>Alias: <p class="field_info">(Max. 15 char)</p></td>
									<td>
										<input name="aliasService" id = "aliasService" type="text" maxlength="20" />
										<span id="aliasServiceError" class="error">Service alias must be defined</span>
									</td>
								</tr>
								<tr>
									<td>Description:</td>
									<td colspan="4">
										<textarea class="description" name="description" maxlength="150" onfocus="if(this.value=='Insert your description here...')this.value=''">Insert your description here...</textarea>
										<div class="info_caracteres" id="lowerInfo"><span>150</span> characters left</div>  
									</td>
								</tr>
								<tr>
                      				<td>Sensemaking operations:</td>
                      				<td colspan="4">
                      					<select name="tags" id="tags" multiple="multiple" size="5">
                      					<option>Abstracting</option>
                      					<option>Activity Identification</option>
                      					<option>Advising</option>
                      					<option>AnalyseDataset</option>
                      					<option>Analysing</option>
                      					<option>BiologicalExplanation</option>
                      					<option>Blood Vessels Identification</option>
                      					<option>Classification</option>
                      					<option>Clustering</option>
                      					<option>ClusteringOpinion</option>
                      					<option>Collecting</option>
                      					<option>Communicating</option>
                      					<option>Comparing</option>
                      					<option>Comparing Previous Data</option>
                      					<option>Comparing Previous Reports</option>
                      					<option>Comparing Tissue Profile</option>
                      					<option>ComparingGenes</option>
                      					<option>ComparingPlatform</option>
                      					<option>Correcting</option>
                      					<option>Detailing</option>
                      					<option>Documenting</option>
                      					<option>Dynamika Report</option>
                      					<option>EmergingTheme</option>
                      					<option>Enhancement Identification</option>
                      					<option>Extracting</option>
                      					<option>Filtering</option>
                      					<option>FilteringNoise</option>
                      					<option>Follow Up</option>
                      					<option>Fusing</option>
                      					<option>GED	</option>
                      					<option>Handover</option>
                      					<option>Hypotheses Generation</option>
                      					<option>Identification</option>
                      					<option>IdentifyDatasets</option>
                      					<option>Inflammation Identification</option>
                      					<option>InfluenceAnalysis</option>
                      					<option>Interpreting</option>
                      					<option>Linking</option>
                      					<option>LinkingOpinion</option>
                      					<option>Manipulating</option>
                      					<option>Marking Region of Interest</option>
                      					<option>Matching</option>
                      					<option>Monitoring</option>
                      					<option>Monitoring Change</option>
                      					<option>Monitoring Condition</option>
                      					<option>Monitoring Progress</option>
                      					<option>MonitoringSocialMedia</option>
                      					<option>Motion Correction</option>
                      					<option>Naming</option>
                      					<option>Negotiating</option>
                      					<option>Noise Correction</option>
                      					<option>Ordering</option>
                      					<option>Organising</option>
                      					<option>Reasoning</option>
                      					<option>Refining</option>
                      					<option>Retrieval</option>
                      					<option>Schematising</option>
                      					<option>Search</option>
                      					<option>SearchDataset</option>
                      					<option>Segmenting</option>
                      					<option>Semantic Matching</option>
                      					<option>Sensemaking Operation</option>
                      					<option>SourceLinking</option>
                      					<option>Structuring</option>
                      					<option>Suggesting</option>
                      					<option>Summarising</option>
                      					<option>Tendency Identification</option>
                      					<option>Transforming</option>
                      					<option>Visualisation</option>
                      					<option>Zoning</option>
                      					<option>Zooming</option>
                      					<option>Smoothing</option>
                      					<option>Spatial smoothing</option>
                      					<option>Temporal smoothing</option>
                      					</select>
                      				</td>
                    			</tr>

								<tr>
									<td>URI:</td>
									<td colspan="4">
										<input class="longInput" id="URIService" name="URIService" type="text" maxlength="200"/>
										<span id="URIServiceError" class="error">Service URI must be defined</span>
									</td>
								</tr>
							</table>
							
							<table>
								<tr>
									<td><input type="submit" name="publishServ" value="Publish" /></td>
									<td><input type="button" name="cancelServ" value="Reset" onclick="location.href = 'newService.jsp'"/></td>
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