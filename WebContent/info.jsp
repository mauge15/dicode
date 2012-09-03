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
<title>DICODE Workbench - General Information</title>
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
					<h1>The Dicode project</h1>
					<p>The goal of the Dicode project is to facilitate and augment collaboration and decision making in data-intensive and cognitively-complex settings.</p>
					<p>To do so, it will exploit and build on the most prominent high-performance computing paradigms and large data processing technologies to meaningfully search, analyze and aggregate data existing in diverse, extremely large, and rapidly evolving sources.</p>
					<p>More specifically, the project's S&T objectives are to:</p>
					<ul>
						<li>fully understand the practices and needs of diverse communities and organizations as far as data-intensive and cognitively-complex collaboration and decision making is concerned</li>
						<li>provide a suite of innovative, adaptive and interoperable services that satisfies the full range of the associated requirements</li>
						<li>provide innovative work methodologies that exploit the abovementioned suite of services and advance the current practices in terms of efficicy, creativity, as well as time and cost effectiveness</li>
						<li>ensure usability and acceptability of the above services and work methodologies through their validation in real use cases</li>
					</ul>
					<br>
					<div class="hr"> <hr/> </div>
					<br>
					<h2>Use cases</h2>
					<ol>
						<li><strong>Clinico-Genomic Research Assimilator</strong><br>
								This use case focuses on scientific collaboration in the clinico-genomic research community managing translational research processes so that relevant findings and results are timely delivered
								It helps to explore, evaluate, disseminate and diffuse relative scientific results. The Clinico-Genomic Research Assimilator links and mines disparate clinical and post-genomic data sources
								<br>&nbsp;&nbsp; 
						</li>
						<li><strong>Trial of Rheumatoid Arthritis Treatment</strong><br>
								Focusing on medical decision making, this use case helps doctors and patients in the domain of Rheumatoid Arthritis treatment
								Dicode services will enable more effective collaborative decision making to speed up the introduction of life saving treatments<br>&nbsp;&nbsp; 
						</li>
						<li><strong>Opinion Mining from Unstructured Web 2.0 Data</strong><br>
								This use case concerns capturing tractable, commercially valuable information to support marketing decisions and strategies. 
								Through this use case, we aim to validate the Dicode suite of services for the automatic analyses of voluminous amounts of unstructured information from Web 2.0 platforms, blogging platforms (Twitter), and social network
						</li>
					</ol>
					<br>
					<div class="hr"> <hr/> </div>
					<br>
					<h2>Partners</h2>
					<table cellpadding="5">
						<tr>
							<td width="10%"><a href="http://www.cti.gr"><img src="images/cti.jpg"></a></td>
							<td width="37%"><a href="http://www.cti.gr" style="color: blue;">Research Academic Computer Technology Institute, Greece</a></td>
							<td width="5%"></td>
							<td width="10%"><a href="http://www.leeds.ac.uk"><img src="images/leeds.jpg"></a></td>
							<td width="37%"><a href="http://www.leeds.ac.uk" style="color: blue;">University of Leeds, United Kingdom</a></td>
						</tr>
						<tr>
							<td><a href="http://www.fraunhofer.de"><img src="images/fhg.jpg"></a></td>
							<td><a href="http://www.fraunhofer.de" style="color: blue;">Fraunhofer-Institut fr Intelligente Analyse- und Informationssysteme, Germany</a></td>
							<td></td>
							<td><a href="http://www.upm.es"><img src="images/upm.jpg"></a></td>
							<td><a href="http://www.upm.es" style="color: blue;">Universidad Polit&eacutecnica de Madrid, Spain</a></td>
						</tr>
						<tr>
							<td><a href="http://www.neofonie.de"><img src="images/neo.jpg"></a></td>
							<td><a href="http://www.neofonie.de" style="color: blue;">Neofonie GmbH, Germany</a></td>
							<td></td>
							<td><a href="http://www.image-analysis.org"><img src="images/ima.jpg"></a></td>
							<td><a href="http://www.image-analysis.org" style="color: blue;">Image Analysis Ltd, United Kingdom</a></td>
						</tr>
						<tr>
							<td><a href="http://www.bioacademy.gr"><img src="images/brf.jpg"></a></td>
							<td><a href="http://www.bioacademy.gr" style="color: blue;">Biomedical Research Foundation, Greece</a></td>
							<td></td>
							<td><a href="http://www.publicis.de"><img src="images/pub.jpg"></a></td>
							<td><a href="http://www.publicis.de" style="color: blue;">PWW GmbH, Germany</a></td>
						</tr>
					</table>
					<br>
					<div class="hr"> <hr/> </div>
					<br>
					<h2>Contact</h2>
						<table>
							<tr><td>Prof. Nikos Karacapilidis</td></tr>
							<tr><td>Research Academic Computer Technology Institute</td></tr>
							<tr><td>26504 Rio Patras, Greece</td></tr>
							<tr><td>Tel: +30 2610 960305</td></tr>
							<tr><td>E-mail: <a href="mailto:info@dicode-project.eu" style="color: blue;">info@dicode-project.eu</a> --- <a href="http://dicode-project.eu" style="color: blue;">http://dicode-project.eu</a></td></tr>
						</table>
					<br>
				</div>
			</div>
		</div>
		<jsp:include page="foot.jsp"/>
	</div>
</body>
</html>