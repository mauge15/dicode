var expInfo;
var divC;
var divG;

function loadWidgetContainer(uri, srvName) 
{
	divC = document.getElementById('dialogBoxPopUp');
	divG = document.getElementById('ppPanelGlass');
	redimensionateContainer();
	var allSize = [document.getElementById('main_div').style.width,document.getElementById('main_div').style.height];
	divG.style.width=allSize[0]+"px";
	divG.style.height=allSize[1]+"px";
	removeAllChilds('dialogBoxPopUp');
	removeAllChilds('ppPanelGlass');
	var divBody = createContainer(divC);
	createHeaderContainer(divBody, srvName);
	createMiddleContainer(divBody, uri);
	createBottomContainer(divBody);
	setVisible([divC,divG]);
	divG.setAttribute('onclick', 'setHidden([divC,divG])');
}


function loadMenuContainer(uri, srvName) 
{
	divC = document.getElementById('dialogBoxPopUp');
	divG = document.getElementById('ppPanelGlass');
	redimensionateContainer();
	var allSize = [document.getElementById('main_div').style.width,document.getElementById('main_div').style.height];
	divG.style.width=allSize[0]+"px";
	divG.style.height=allSize[1]+"px";
	removeAllChilds('dialogBoxPopUp');
	removeAllChilds('ppPanelGlass');
	var divBody = createContainer(divC);
	createHeaderContainer(divBody, srvName);
	createMenuMiddleContainer(divBody, uri);
	createBottomContainer(divBody);
	setVisible([divC,divG]);
	divG.setAttribute('onclick', 'setHidden([divC,divG])');
}


function createMenuMiddleContainer(div, uri) 
{
	var tr = document.createElement('tr');
	tr.setAttribute('class','dialogMiddle');
	div.appendChild(tr);
	var td = document.createElement('td');
	td.setAttribute('class','dialogMiddleLeft');
	tr.appendChild(td);
	var divInner = document.createElement('div');
	divInner.setAttribute('class','dialogMiddleLeftInner');
	td.appendChild(divInner);
	td = document.createElement('td');
	td.setAttribute('class','dialogMiddleCenter');
	tr.appendChild(td);
	divInner = document.createElement('div');
	divInner.setAttribute('class','dialogMiddleCenterInner');
	td.appendChild(divInner);
	var content = document.createElement('iframe');
	if (uri!='') 
	{ 
		content.setAttribute('width', '450');
		content.setAttribute('height', '450');
		content.setAttribute('frameborder', '0');
		content.setAttribute('marginheight', '0');
		content.setAttribute('marginwidth', '0');
		content.setAttribute('framespacing', '0');
		content.setAttribute('border', '0');
		content.setAttribute('top', '0');
		content.setAttribute('src', uri+'&width=450&height=450&color=0066bb&expid='+expInfo[4]);
	}
	divInner.appendChild(content);
	td = document.createElement('td');
	td.setAttribute('class','dialogMiddleRight');
	tr.appendChild(td);
	divInner = document.createElement('div');
	divInner.setAttribute('class','dialogMiddleRightInner');
	td.appendChild(divInner);
}


function createInfoContainer(div, uri) 
{
	var tr = document.createElement('tr');
	tr.setAttribute('class','dialogMiddle');
	div.appendChild(tr);
	var td = document.createElement('td');
	td.setAttribute('class','dialogMiddleLeft');
	tr.appendChild(td);
	var divInner = document.createElement('div');
	divInner.setAttribute('class','dialogMiddleLeftInner');
	td.appendChild(divInner);
	td = document.createElement('td');
	td.setAttribute('class','dialogMiddleCenter');
	tr.appendChild(td);
	divInner = document.createElement('div');
	divInner.setAttribute('class','dialogMiddleCenterInner');
	td.appendChild(divInner);
	var content = document.createElement('iframe');
	if (uri!='') 
	{ 
		content.setAttribute('width', '450');
		content.setAttribute('height', '450');
		content.setAttribute('frameborder', '0');
		content.setAttribute('marginheight', '0');
		content.setAttribute('marginwidth', '0');
		content.setAttribute('framespacing', '0');
		content.setAttribute('border', '0');
		content.setAttribute('top', '0');
		content.setAttribute('src', uri+'&width=450&height=450&color=0066bb&expid='+expInfo[4]);
	}
	divInner.appendChild(content);
	td = document.createElement('td');
	td.setAttribute('class','dialogMiddleRight');
	tr.appendChild(td);
	divInner = document.createElement('div');
	divInner.setAttribute('class','dialogMiddleRightInner');
	td.appendChild(divInner);
}


function createMiddleContainerM(uri) 
{
	var content = document.createElement('iframe');
	if (uri!='') { 
	content.setAttribute('width', '450');
	content.setAttribute('height', '450');
	content.setAttribute('frameborder', '0');
	content.setAttribute('marginheight', '0');
	content.setAttribute('marginwidth', '0');
	content.setAttribute('framespacing', '0');
	content.setAttribute('border', '0');
	content.setAttribute('top', '0');
	content.setAttribute('src', uri+'?width=450&height=450&color=0066bb&expid='+expInfo[4]);
	}
	return content;
}


function setVisible(list) 
{
	for (var i=0; i<list.length; i++) 
	{
		list[i].style.visibility="visible";
		list[i].style.display="block";
	}
}

function setHidden(list) {
	for (var i=0; i<list.length; i++) 
	{
		list[i].style.visibility="hidden";
		list[i].style.display="none";
	}
}

function createMiddleContainer(div, uri) 
{
	var tr = document.createElement('tr');
	tr.setAttribute('class','dialogMiddle');
	div.appendChild(tr);
	
	var td = document.createElement('td');
	td.setAttribute('class','dialogMiddleLeft');
	tr.appendChild(td);
	var divInner = document.createElement('div');
	divInner.setAttribute('class','dialogMiddleLeftInner');
	td.appendChild(divInner);
	
	td = document.createElement('td');
	td.setAttribute('class','dialogMiddleCenter');
	tr.appendChild(td);
	divInner = document.createElement('div');
	divInner.setAttribute('class','dialogMiddleCenterInner');
	td.appendChild(divInner);
	var content = document.createElement('iframe');
	if (uri!='') 
	{ 
		content.setAttribute('width', '450');
		content.setAttribute('height', '450');
		content.setAttribute('frameborder', '0');
		content.setAttribute('marginheight', '0');
		content.setAttribute('marginwidth', '0');
		content.setAttribute('framespacing', '0');
		content.setAttribute('border', '0');
		content.setAttribute('top', '0');
		content.setAttribute('src', uri+'?width=450&height=450&color=0066bb&expid='+expInfo[4]);
	}
	divInner.appendChild(content);
	
	
	td = document.createElement('td');
	td.setAttribute('class','dialogMiddleRight');
	tr.appendChild(td);
	divInner = document.createElement('div');
	divInner.setAttribute('class','dialogMiddleRightInner');
	td.appendChild(divInner);
}


function createMiddleContainerAjax(div, uri) {
	var tr = document.createElement('tr');
	tr.setAttribute('class','dialogMiddle');
	div.appendChild(tr);
	
	var td = document.createElement('td');
	td.setAttribute('class','dialogMiddleLeft');
	tr.appendChild(td);
	var divInner = document.createElement('div');
	divInner.setAttribute('class','dialogMiddleLeftInner');
	td.appendChild(divInner);
	
	td = document.createElement('td');
	td.setAttribute('class','dialogMiddleCenter');
	tr.appendChild(td);
	divInner = document.createElement('div');
	divInner.setAttribute('class','dialogMiddleCenterInner');
	td.appendChild(divInner);
	var content = document.createElement('div');
	content.setAttribute("id", "ajaxDiv");
	var request = $.ajax({
		  url: uri,
		  type: "GET",
		  dataType: "html"
		});

		request.done(function(msg) {
			
		  $("#ajaxDiv").html( msg );
		});

		request.fail(function(jqXHR, textStatus) {
		  alert( "Request failed: " + textStatus );
		});
	
	divInner.appendChild(content);
	
	
	td = document.createElement('td');
	td.setAttribute('class','dialogMiddleRight');
	tr.appendChild(td);
	divInner = document.createElement('div');
	divInner.setAttribute('class','dialogMiddleRightInner');
	td.appendChild(divInner);
}


function createBottomContainer(div) {
	var tr = document.createElement('tr');
	tr.setAttribute('class','dialogBottom');
	div.appendChild(tr);
	
	var td = document.createElement('td');
	td.setAttribute('class','dialogBottomLeft');
	tr.appendChild(td);
	var divInner = document.createElement('div');
	divInner.setAttribute('class','dialogBottomLeftInner');
	td.appendChild(divInner);
	
	td = document.createElement('td');
	td.setAttribute('class','dialogBottomCenter');
	tr.appendChild(td);
	divInner = document.createElement('div');
	divInner.setAttribute('class','dialogBottomCenterInner');
	td.appendChild(divInner);
	
	td = document.createElement('td');
	td.setAttribute('class','dialogBottomRight');
	tr.appendChild(td);
	divInner = document.createElement('div');
	divInner.setAttribute('class','dialogBottomRightInner');
	td.appendChild(divInner);
}

function createHeaderContainer(div, srvName) {
	var tr = document.createElement('tr');
	tr.setAttribute('class','dialogTop');
	div.appendChild(tr);
	
	var td = document.createElement('td');
	td.setAttribute('class','dialogTopLeft');
	tr.appendChild(td);
	var divInner = document.createElement('div');
	divInner.setAttribute('class','dialogTopLeftInner');
	td.appendChild(divInner);
	
	td = document.createElement('td');
	td.setAttribute('class','dialogTopCenter');
	tr.appendChild(td);
	divInner = document.createElement('div');
	divInner.setAttribute('class','dialogTopCenterInner');
	td.appendChild(divInner);
	var divCaption = document.createElement('div');
	divCaption.setAttribute('class', 'Caption');
	divCaption.innerHTML=srvName;
	divInner.appendChild(divCaption);
	
	td = document.createElement('td');
	td.setAttribute('class','dialogTopRight');
	tr.appendChild(td);
	divInner = document.createElement('div');
	divInner.setAttribute('class','dialogTopRightInner');
	td.appendChild(divInner);
}

function createContainer(div) {
	var divN = document.createElement('div');
	divN.setAttribute('class','');
	div.appendChild(divN);
	var table = document.createElement('table');
	table.setAttribute('class','');
	table.setAttribute('cellsapcing','0');
	table.setAttribute('cellpadding','0');
	divN.appendChild(table);
	var tbody = document.createElement('tbody');
	table.appendChild(tbody);
	return tbody;
}

function redimensionateContainer() {
	
	var xm=500;
	var ym=500;
	var size = winSize();
	var xM=(xm>=size[0])? xm:size[0];
	var yM=(ym>=size[1])? ym:size[1];
	
	divC.style.left=(xM/2)-(xm/2)+'px';
	divC.style.top=(yM/2)-(ym/2)+'px';
}

function winSize() {
  var size = [0, 0];
  if (typeof window.innerWidth != 'undefined')
  {
    size = [
        window.innerWidth,
        window.innerHeight
    ];
  }
  else if (typeof document.documentElement != 'undefined'
      && typeof document.documentElement.clientWidth !=
      'undefined' && document.documentElement.clientWidth != 0)
  {
 size = [
        document.documentElement.clientWidth,
        document.documentElement.clientHeight
    ];
  }
  else   {
    size = [
        document.getElementsByTagName('body')[0].clientWidth,
        document.getElementsByTagName('body')[0].clientHeight
    ];
  }
  return size;
}

