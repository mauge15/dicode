var listL;
var iL;
var collaboratives;
var services;
var cwID;


function removeAllChilds(a) {
	var aNode=document.getElementById(a);
	if (aNode!=null) {
	while(aNode.hasChildNodes())
		aNode.removeChild(aNode.firstChild);
	}
	}


function showLayer(page) 
{
	var i;
	var elem, vis;
	for (i=0; i<listL.length;i++) 
	{
		if( document.getElementById ) 
		{// this is the way the standards work
			elem = document.getElementById('layer'+i);
		}
		else if( document.all ) // this is the way old msie versions work
			elem = document.all('layer'+i);
		else if( document.layers ) // this is the way nn4 works
			elem = document.layers('layer'+i);
		vis = elem.style;
		if (i!=page) 
		{
			vis.visibility="hidden";
			vis.display="none";
		} 
		else 
		{
			vis.visibility="visible";
			vis.display="block";
		}
	}
}

function generateTabs(div, index, name) 
{
	var table = document.createElement('table');
	var tr = document.createElement('tr');
	tr.setAttribute("id", "tab"+name);
	var i;
	var td;
	for (i=0;i<listL.length;i++)
	{
		td = document.createElement('td');
		if (listL[i]!=name) 
		{
			td.setAttribute('class', 'unselected');
			a = document.createElement('a');
			a.setAttribute('onclick', 'showLayer('+i+')');
		} 
		else 
		{
			td.setAttribute('class', 'selected');
			a = document.createElement('p');
		}
		a.setAttribute('class', 'tabText');
		a.innerHTML=listL[i];
		//CONTROLAR EL ESTILO CUANDO LA PESTAÑA SEA 'index'
		td.appendChild(a);
		tr.appendChild(td);
		}
	table.appendChild(tr);
	div.appendChild(table);
}

function createCollaborative(div, i, name, cView)
{
	var content = document.createElement('div');
	content.setAttribute('id', name);
	content.setAttribute('class', 'folderContent');
	var layDDCW = document.createElement('div');
	layDDCW.setAttribute('id', 'layDDCW');
	layDDCW.setAttribute('class', 'layDDCW');
	layDDCW.setAttribute('ondragenter', 'cancel(event)');
	layDDCW.setAttribute('ondrop', 'dropCW(event)');
	layDDCW.setAttribute('ondragover', 'cancel(event)');
	//layDDCW.setAttribute('onmouseover', 'setVisibleOpacity(\'layDDCW\')');
	//layDDCW.setAttribute('onmouseout', 'setHiddenOpacity(\'layDDCW\')');
	var object = document.createElement('object');
	object.setAttribute('id', 'copeitObject');
	object.setAttribute('width', '100%');
	object.setAttribute('height', '100%');
	object.setAttribute('data', 'http://dicodedev.cti.gr/'+cView);
	object.setAttribute('onload', 'setHidden([document.getElementById(\'loadPanelGlass\')]);');
	object.setAttribute('onmouseout', 'processMouseOut()');
	object.setAttribute('onmouseover', 'processMouseOver(\'0\')');
	content.appendChild(object);
	div.appendChild(layDDCW);
	div.appendChild(content);
}

function createArgumentation(div, i, name, aView)
{
	var content = document.createElement('div');
	content.setAttribute('id', name);
	content.setAttribute('class', 'folderContent');
	var object = document.createElement('object');
	object.setAttribute('width', '100%');
	object.setAttribute('height', '100%');
	object.setAttribute('data', 'http://dicodedev.cti.gr/workspace/formal/agora.aspx?ID='+aView);
	content.appendChild(object);
	div.appendChild(content);
}

function createDiscussion(div, i, name, cView, fView)
{
	var content = document.createElement('div');
	content.setAttribute('id', name);
	content.setAttribute('class', 'folderContent');
	var object = document.createElement('object');
	object.setAttribute('width', '100%');
	object.setAttribute('height', '100%');
	var path;
	if (fView == '')
		path='http://dicodedev.cti.gr/Workspace/Forum/Topic.aspx?forumId='+cView+'&stay=1'; //cView
	else
		path='http://dicodedev.cti.gr/Workspace/Forum/Topic.aspx?topicId='+fView+'&forumId='+cView+'&stay=1';
	object.setAttribute('data', path);
	content.appendChild(object);
	div.appendChild(content);
}

function generateLayer(i, name, views) 
{
  var div = document.getElementById('center_column');
  //Primero creo la capa general
  var layer = document.createElement('div');
  layer.setAttribute('id', 'layer'+i);
  layer.setAttribute('class', 'layer');
  
  //Creo el div para la barra de pestañas
  var dTabs = document.createElement('div');
  dTabs.setAttribute('class', 'tabBar');
  generateTabs(dTabs,i,name);
  
  var box;
  //Creo el div del contenido
  var dContent = document.createElement('div');
  dContent.setAttribute('class', 'folder');
  switch (name) 
  {
  case 'Mind-map view':
	  createCollaborative(dContent, i, 'Collaborative', views[0]);
	  break;
  case 'Argumentation view':
	  createArgumentation(dContent, i, 'Argumentation', views[2]);
	  break;
  case 'Forum view':
	  createDiscussion(dContent, i, 'Discussion', views[0], views[1]);
	  break;
  }
  
  layer.appendChild(dTabs);
  layer.appendChild(dContent);
  layer.style.visibility="hidden";
  layer.style.display="none";
  div.appendChild(layer);
  return box;
}

function generateLayers(id, views) {
  var i;
  //alert('He entrado en crear layers');
  for (i=0;i<listL.length; i++) 
  {
	//alert('Generando capa '+i+' para elemento '+listL[i]);
	  generateLayer(i, listL[i], views);
    //alert('Generada capa '+i+' para elemento '+listL[i]);
  }
  //alert('Voy a mostrar la capa');
  showLayer(0); 
  collaborativeOptions(id);
}

function createCW(id, views) 
{
	cwID=id;
	if (views!='null') 
	{
		removeAllChilds('center_column');
		generateLayers(id,views);
	}
}

function timerMsg() 
{
	t = setTimeout('alertMsg()', 15000);
}

function alertMsg() 
{
	var panel, vis;
	if(document.getElementById) // this is the way the standards work
		panel = document.getElementById('loadPanelGlass');
	else if( document.all ) // this is the way old msie versions work
		panel = document.all('loadPanelGlass');
	else if( document.layers ) // this is the way nn4 works
		panel = document.layers('loadPanelGlass');
	vis=panel.style;
	if ((vis.visibility=="")||(vis.visibility=="visible")) 
	{
		alert('Sorry but any widget could not be loaded. Please try later. If the problem persists send an email to Dicode administrator.');
		setHidden([document.getElementById('loadPanelGlass')]);
	}
}