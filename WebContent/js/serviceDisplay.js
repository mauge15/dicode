//GLOBAL VARS
var srvID;
var category; 
var tags;
var name; 
var uri; 
var creation; 
var modification; 
var desc;
var pr;

var addEvent = function(obj, evType, fn){
	  if (obj.addEventListener) { //W3C DOM
	    obj.addEventListener(evType, fn, false);
	  } else if (obj.attachEvent) { //IE DOM
	    obj['e' + evType + fn] = fn;
	    obj[evType + fn] = function(){
	      obj["e" + evType + fn](self.event);
	    };
	    obj.attachEvent("on" + evType, obj[evType + fn]);
		}
	};
	
function showInfo() 
{
	var rowspan = tags.length;
	var obj=document.getElementById('back');
	var title = document.createElement('h4');
	title.innerHTML='Service details';
	var table = document.createElement('table');
	table.setAttribute('class','serviceInfo');
	table.setAttribute('name','serviceInfo');
	var thead = document.createElement('thead');
	var tr = document.createElement('tr');
	tr.setAttribute('class','head');
	var th = document.createElement('th');
	th.setAttribute('class','col1');
	tr.appendChild(th);
	th = document.createElement('th');
	th.setAttribute('class','col2');
	tr.appendChild(th);
	th = document.createElement('th');
	th.setAttribute('class','col3');
	tr.appendChild(th);
	th = document.createElement('th');
	th.setAttribute('class','col4');
	tr.appendChild(th);
	thead.appendChild(tr);
	table.appendChild(thead);
	
	var tbody = document.createElement('tbody');
	tr = document.createElement('tr');
	var td;
	td=document.createElement('td');
	td.setAttribute('colspan', '4');
	td.setAttribute('class', 'description');
	td.innerHTML=name;
	tr.appendChild(td);
	tbody.appendChild(tr);
	
	tr = document.createElement('tr');
	td=document.createElement('td');
	td.setAttribute('rowspan', rowspan);
	td.setAttribute('class', 'dark');
	td.innerHTML='Type';
	tr.appendChild(td);
	td=document.createElement('td');
	td.setAttribute('rowspan', rowspan);
	td.innerHTML=category;
	tr.appendChild(td);
	td=document.createElement('td');
	td.setAttribute('rowspan', rowspan);
	td.setAttribute('class', 'dark');
	td.innerHTML='Sensemarking operations';
	tr.appendChild(td);
	
	if (tags.length > 0) 
	{
		var i;
		for (i=0;i<tags.length;i++) 
		{
			td=document.createElement('td');
			td.innerHTML=tags[i];
			tr.appendChild(td);
			tbody.appendChild(tr);
			if (i!=tags.length-1)
				tr = document.createElement('tr');
		}
	} 
	else 
	{
		td = document.createElement('td');
		tr.appendChild(td);
		tbody.appendChild(tr);
	}
	
	//Alias
	tr = document.createElement('tr');
	td = document.createElement('td');
	td.setAttribute('class', 'dark');
	td.innerHTML='Short Name (Alias)';
	tr.appendChild(td);
	td=document.createElement('td');
	td.setAttribute('colspan', '3');
	td.innerHTML=alias;
	tr.appendChild(td);
	tbody.appendChild(tr);
	//alias
	
	tr = document.createElement('tr');
	td=document.createElement('td');
	td.setAttribute('class', 'dark');
	td.innerHTML='URI';
	tr.appendChild(td);
	td=document.createElement('td');
	td.setAttribute('colspan', '3');
	td.innerHTML=uri;
	tr.appendChild(td);
	tbody.appendChild(tr);
	
	if (desc!=' ') 
	{
		tr = document.createElement('tr');
		td=document.createElement('td');

		td.setAttribute('class', 'dark');
		td.innerHTML='Description';
		tr.appendChild(td);

		td=document.createElement('td');
		td.setAttribute('colspan', '3');
		td.innerHTML=desc;
		tr.appendChild(td);
		tbody.appendChild(tr);
	}
	
	
	tr = document.createElement('tr');
	td=document.createElement('td');
	td.setAttribute('class', 'dark');
	td.innerHTML='Creation date';
	tr.appendChild(td);
	td=document.createElement('td');
	td.innerHTML=creation;
	tr.appendChild(td);
	td=document.createElement('td');
	td.setAttribute('class', 'dark');
	td.innerHTML='Modification date';
	tr.appendChild(td);
	td=document.createElement('td');
	td.innerHTML=modification;
	tr.appendChild(td);
	
	tbody.appendChild(tr);
	table.appendChild(tbody);
	
	obj.appendChild(title);
	obj.appendChild(table);
}

function callUpdate(event) 
{
	var path = 'updService.jsp?srvID=' + srvID + '&srvName=' + name;
	window.open(path, '_self');
}

function callDelete(event) 
{
	if (confirm("You are about to delete this service. Are you sure?\n (This operation can not be undone)")) 
	{
		var path = 'DeleteServiceDB?srvID=' + srvID + '&srvName=' + name;
		window.open(path, '_self');
	}
}

function loadUpdateButton () 
{
	var obj = document.getElementById('permButts');
	var div = document.createElement('div');
	div.setAttribute('id', 'updateSrv');
	var updButt = document.createElement('input');
	updButt.setAttribute('type', 'button');
	updButt.setAttribute('value', 'Update');
	addEvent(updButt,'click', callUpdate);
	div.appendChild(updButt);
	obj.appendChild(div);
	
}

function loadDeleteButton () 
{
	var obj = document.getElementById('permButts');
	var div = document.createElement('div');
	div.setAttribute('id', 'deleteSrv');
	var delButt = document.createElement('input');
	delButt.setAttribute('type', 'button');
	delButt.setAttribute('value', 'Delete');
	addEvent(delButt,'click', callDelete);
	div.appendChild(delButt);
	obj.appendChild(div);
	
}

onload = function () {
	showInfo();
	if (pr==100) 
	{
		loadUpdateButton();
		loadDeleteButton();
	}	
};