//GLOBAL VARS
var listIDs;
var listVis;

//Number of items to show per page
var maxItems=10;
var pages;

//Number of characters to show in description
var descLength = 30;


function numPages() 
{
	var rem = listIDs.length % maxItems;
	if (rem==0)
		return listIDs.length / maxItems;
	else
		return ((listIDs.length -rem)/ maxItems)+1;
}

function getVisibility(id) 
{
	var found = false;
	var i = 0;
	var vis = 'Unknown';
	while (found==false) 
	{
		if (listVis[i][0]==id) 
		{
			found = true;
			vis = listVis[i][1];
		}
		i++;
	}
	return vis;
}


function noPermission()
{
	$( "#dialog-no-permission" ).dialog({
		height: 100,
		width: 290,
		position: "center",
		modal: true
	});  
}

function listExperiments(tag, page) 
{
	var init = (page-1)*maxItems;
	var end = init+maxItems-1;
	if (end>=listIDs.length)
		end=listIDs.length-1;
	var i;
  	var tr = document.createElement('tr');
  	var th1 = document.createElement('th');
  	th1.setAttribute('scope', 'col');
	th1.setAttribute('class', 'name');
	th1.innerHTML='Workspace Name';
	tr.appendChild(th1);
	var th2 = document.createElement('th');
  	th2.setAttribute('scope', 'col');
	th2.setAttribute('class', 'description');
	th2.innerHTML='Description';
	tr.appendChild(th2);
	var th3 = document.createElement('th');
  	th3.setAttribute('scope', 'col');
	th3.setAttribute('class', 'visibility');
	th3.innerHTML='Visibility';
	tr.appendChild(th3);
	var th4 = document.createElement('th');
  	th4.setAttribute('scope', 'col');
	th4.setAttribute('class', 'creator');
	th4.innerHTML='Created by';
	tr.appendChild(th4);
	var th5 = document.createElement('th');
  	th5.setAttribute('scope', 'col');
	th5.setAttribute('class', 'date');
	th5.innerHTML='Date';
	tr.appendChild(th5);
	
	
	var th6 = document.createElement('th');
  	th6.setAttribute('scope', 'col');
	th6.setAttribute('class', 'date');
	th6.innerHTML='Actions';
	tr.appendChild(th6);
	
	tag.appendChild(tr);
	var td;
	var a;
	for (i=init; i<=end; i++) {
		tr=document.createElement('tr');
		if (i%2==1)
			tr.setAttribute('class', 'odd');
		td=document.createElement('td');
		td.setAttribute('class', 'name');
		a=document.createElement('a');
		
		var permId = listIDs[i][6];
		if (permId!='0')
		{
			a.setAttribute('href','workspace.jsp?id='+listIDs[i][0]+'&nm='+listIDs[i][1]);
			a.setAttribute('target','_blank');
		}
		else
		{
			a.setAttribute('href','#');
			a.setAttribute('onclick','noPermission()');
		}
		
		a.innerHTML=listIDs[i][1];
		td.appendChild(a);
		tr.appendChild(td);
		td=document.createElement('td');
		td.setAttribute('class', 'description');
		//td.innerHTML=listIDs[i][2].substring(0,descLength);
		td.innerHTML=listIDs[i][2];
		tr.appendChild(td);
		td=document.createElement('td');
		td.setAttribute('class', 'visibility');
		td.innerHTML=getVisibility(listIDs[i][3]);
		tr.appendChild(td);
		td=document.createElement('td');
		td.setAttribute('class', 'creator');
		td.innerHTML=listIDs[i][5];
		tr.appendChild(td);
		td=document.createElement('td');
		td.setAttribute('class', 'date');
		td.innerHTML=listIDs[i][4];
		tr.appendChild(td);
		if (listIDs[i][5]==userName)
		{
		   activated="remove-button";
		   action = "javascript:remove_workspace("+listIDs[i][0]+")";
		}
		else
		{
			activated= "disabled-remove-button";
			action = "#";
		}
		
		td=document.createElement('td');
		td.setAttribute('class', 'date');
		td.innerHTML="<a class='"+activated+"' href='"+action+"'> </a>";
		tr.appendChild(td);
		
		
		tag.appendChild(tr);  
	}
	
}

function createHeader() 
{
	var thead = document.createElement('thead');
	var tr = document.createElement('tr');
	var th = document.createElement('th');
	th.setAttribute('scope', 'col');
	th.setAttribute('colspan', '6');
	var a = document.createElement('a');
	a.setAttribute('id', 'blueLink');
	a.setAttribute('href', 'newWorkspace.jsp');
	a.innerHTML='+ Create a new workspace...';
	th.appendChild(a);
	tr.appendChild(th);
	thead.appendChild(tr);
	return thead;
}

function createPagination(page) 
{
	var center = document.createElement('tfoot');
	center.setAttribute('align', 'center');
	var fTR = document.createElement('tr');
	var fTD = document.createElement('td');
	fTD.setAttribute('colspan', '6');
	var table = document.createElement('table');
	table.setAttribute('class','tablePages');
	var tr = document.createElement('tr');
	//If it's not the first page write previous buttons
	if (page!=1) 
	{
		var first = document.createElement('td');
		a=document.createElement('a');
		a.setAttribute('href','#');
		a.setAttribute('onclick','showLayer(1);');
		a.innerHTML='&lt&lt';
		first.appendChild(a);
		tr.appendChild(first);
		var prev = document.createElement('td');
		a=document.createElement('a');
		a.setAttribute('href','#');
		a.setAttribute('onclick','showLayer('+(page-1)+');');
		a.innerHTML='&lt';
		prev.appendChild(a);
		tr.appendChild(prev);
	}
	
	var i;
	var iPrev;
	for (i=page-2; (i<=page-1); i++)
	{
		if (i>0) 
		{
			iPrev = document.createElement('td');
			a=document.createElement('a');
			a.setAttribute('href','#');
			a.setAttribute('onclick','showLayer('+i+');');
			a.innerHTML=i;
			iPrev.appendChild(a);
			tr.appendChild(iPrev);
		}
	}
	var actual = document.createElement('td');
	actual.innerHTML=page;
	tr.appendChild(actual);
	
	var iNext;
	
	for (i=page+1; (i<=page+2);i++)
	{
		if (i<=pages) 
		{
			iNext = document.createElement('td');
			a=document.createElement('a');
			a.setAttribute('href','#');
			a.setAttribute('onclick','showLayer('+i+');');
			a.innerHTML=i;
			iNext.appendChild(a);
			tr.appendChild(iNext);
		}
	}
	if (page!=pages) 
	{
		var next = document.createElement('td');
		a=document.createElement('a');
		a.setAttribute('href','#');
		a.setAttribute('onclick','showLayer('+(page+1)+');');
		a.innerHTML='&gt';
		next.appendChild(a);
		tr.appendChild(next);
		var last = document.createElement('td');
		a=document.createElement('a');
		a.setAttribute('href','#');
		a.setAttribute('onclick','showLayer('+(pages)+');');
		a.innerHTML='&gt&gt';
		last.appendChild(a);
		tr.appendChild(last);
	}
	table.appendChild(tr);
	fTD.appendChild(table);
	fTR.appendChild(fTD);
	center.appendChild(fTR);
	return center;
}

function generateLayer(page) 
{
	var div = document.getElementById('back');
	var layer = document.createElement('table');
	layer.setAttribute('id', 'layer'+page);
	layer.setAttribute('class', 'experiments');

	var thead = createHeader();

	var tpag = createPagination(page);

	var tbody = document.createElement('tbody');
	listExperiments(tbody,page);

	layer.appendChild(thead);
	layer.appendChild(tbody);
	layer.appendChild(tpag);
	layer.style.visibility="hidden";
	layer.style.display="none";
	div.appendChild(layer);
}

function showLayer(page) 
{
	var i;
	for (i=1; i<=pages;i++) 
	{
		if (i!=page) 
		{
			document.getElementById('layer'+i).style.visibility="hidden";
			document.getElementById('layer'+i).style.display="none";
		} 
		else 
		{
			document.getElementById('layer'+i).style.visibility="visible";
			document.getElementById('layer'+i).style.display="block";
		}
	}
}

function generateLayers () 
{
	var i;
	pages = numPages();
	if (pages!=0) {
		for (i=1; i<=pages; i++) 
		{
			generateLayer(i);
		}
		showLayer(1);
	}
	else 
	{
		var div = document.getElementById('back');
		var layer = document.createElement('table');
		layer.setAttribute('id', 'layer');
		layer.setAttribute('class', 'experiments');
		var thead = createHeader();
		layer.appendChild(thead);
		div.appendChild(layer);
	}
}