/*srvSearch*/
var listL;
var origLength;
var divR;
var req;
var strlength = 10;

/*addService*/
var services;
var req;

/*addService*/

function callbackIncludeSrv()
{
	if( req.readyState == 4 )
	{
		if( req.status == "200" )
		{
			var content = req.responseText;
			var item = content.split(",");
			var aux = new Array();
			aux[0] = item[3];
			aux[1] = item[4];
			item.splice(3,2,aux);
			if (content!="0")
			{
				parent.loadWidget2(item);
				$( "#dialog-modal" ).dialog({
					height: 80,
					position: "center",
					modal: true
				});  
			}
			else
			{
				alert('You can\'t add this service. It is already in your workspace');
			}		
		}
	}
}

function doIncludeService(url) 
{
	if( window.XMLHttpRequest ) 
	{
		req = new XMLHttpRequest();
	} 
	else 
	{
		req = new ActiveXObject('Microsoft.XMLHTTP');
	}
	req.open('GET', url, true);    
	req.onreadystatechange=callbackIncludeSrv;
	req.send(null);
}

function addService(usrID, exID, srvID) 
{
	var url= 'IncludeService?usr='+usrID+'&exp='+exID+'&srv='+srvID+'&row=1&col=1';
	doIncludeService(url);
}

function removeAllChilds(a) {
	var aNode=document.getElementById(a);
	if (aNode!=null) {
	while(aNode.hasChildNodes())
		aNode.removeChild(aNode.firstChild);
	}
	}

/*srvSearch*/
function removeAllChildsNode(a) {
	while(a.hasChildNodes())
		a.removeChild(a.firstChild);	
}

function toggleLayer(whichLayer) {
	var elem, vis;
	if( document.getElementById ) // this is the way the standards work
	elem = document.getElementById( whichLayer );
	else if( document.all ) // this is the way old msie versions work
	  elem = document.all[whichLayer];
	else if( document.layers ) // this is the way nn4 works
	elem = document.layers[whichLayer];
	vis = elem.style;
	// if the style.display value is blank we try to figure it out here
	if(vis.display==''&&elem.offsetWidth!=undefined&&elem.offsetHeight!=undefined)
	vis.display = (elem.offsetWidth!=0&&elem.offsetHeight!=0)?'block':'none';
	vis.display = (vis.display==''||vis.display=='block')?'none':'block';
	}

function toggleLayers(list) {
	var i;
	for (i=0; i<list.length; i++)
	toggleLayer(list[i]);
	}

function createNewSelect(div, sel, selName) {
	sel = document.getElementById(selName);
    if (sel)
        div.removeChild(sel);
    sel = document.createElement('select');
    sel.setAttribute('id', selName);
    sel.setAttribute('name', selName);
    div.appendChild(sel);
    return sel;
}

function returnName(pair) {
	return pair[1];
}

/*services*/
function returnID(pair) {
	return pair[0];
}

function addOption(sel, text, pos) {
    var opt = document.createElement('option');
    opt.text = text;
    opt.value = pos;
    if (!document.all)
        sel.appendChild(opt); // standard way -> doesn't work on i.e.
    else
        sel.add(opt, pos);    // i.e. way
}

function loadSelect (div, list, selName, sel) {
	var i;
	sel = createNewSelect(div,sel, selName);
	for (i = 0; i < list.length; i++) {
	    element = returnName(list[i]);
	    id = returnID(list[i]);
	    addOption(sel, element, id);
	}
}

function initSearch()
{
divST = document.getElementById('divST');
divC = document.getElementById('divC');
divDT = document.getElementById('divDT');
loadSelect(divC, listC, 'category',selC);
if (listGU.length!=0) {
	divGU = document.getElementById('divGU');
	loadSelect(divGU, listGU, 'gUser',selGU);
}
}

function showResults(block) {
	var elem;
	if( document.getElementById ) 
	{// this is the way the standards work
		elem = document.getElementById(block);
	}
	else if( document.all ) // this is the way old msie versions work
	{
		elem = document.all(block);
	}
	else if( document.layers ) // this is the way nn4 works
	{
		elem = document.layers(block);
	}
	elem.style.visibility="visible";
	elem.style.display="block";
}

function addTabResults() 
{
	var i;
	var sel;
	var table;
	var div;
	for (i=0; i<listL.length;i++)
	{
		sel = document.getElementById('tab'+listL[i]);
		table = sel.parentNode;
		div = table.parentNode;
		removeAllChildsNode(div);
		generateTabs(div,i,listL[i]);
	}
}

function processSearch() 
{
	var servicesID = req.responseXML.getElementsByTagName('id');
	var servicesName = req.responseXML.getElementsByTagName('name');
	var i;
	var label;
	label = document.createElement('label');
	label.innerHTML = servicesID.length + ' services founded';
	divR.appendChild(label);
	if (servicesID.length>0) 
	{
		var table = document.createElement('table');
		var tr;
		var td;
		var a;
		var name;
	}
	for (i=0; i<servicesID.length; i++) 
	{
		tr=document.createElement('tr');
		td=document.createElement('td');
		name = servicesName[i].firstChild.nodeValue;
		name = (name.length>strlength) ? (name.substring(0,strlength)+'...') : name; 
		td.innerHTML = name.substring(0,strlength);//.data
		tr.appendChild(td);
		td=document.createElement('td');
		a=document.createElement('a');
		a.setAttribute('href', '#');
		a.innerHTML='[+ Use]';
		a.setAttribute('onclick', 'addService('+usrID+' , '+expID+', '+servicesID[i].firstChild.nodeValue+')');
		td.appendChild(a);
		tr.appendChild(td);
		table.appendChild(tr); 
	}
	if (servicesID.length>0) 
	{
		divR.appendChild(table);
	}
}

function callbackSearch()
{
	// Cheching status
	if( req.readyState == 4 )
	{
		// Checking result HTTP 200)
		if( req.status == 200 )
		{
			processSearch();
		}
	}
}
function doSearch(url) 
{
	if( window.XMLHttpRequest ) 
	{
		req = new XMLHttpRequest();
	} 
	else 
	{
		req = new ActiveXObject('Microsoft.XMLHTTP');
	}
    req.open('GET', url, true);
    req.onreadystatechange=callbackSearch;
    req.send(null);
}



function showSearch () 
{
	var nm = document.services.name.value;

	var ct = document.services.category.value;
	var gu = document.services.gUser.value;
	
	var url= 'Search?nm='+encodeURIComponent(nm)+'&ct='+encodeURIComponent(ct)+'&gu='+encodeURIComponent(gu);
	if (url != 'Search?nm=&tp=0&ct=0&dt=0&gu=0') 
	{
		divR=document.getElementById('sResults');
		removeAllChilds('sResults');
		doSearch(url);
		showResults('resultsBlock');
	}
}