/* serviceWidget */
var expID;
var expInfo;
var services;
var srvNameLength = 18;


/*serviceWidget*/


function loadWidget2(item) {
	//Obtener el nombre del servicio y su url
	var srvName=(item[3][0].length>srvNameLength) ? item[3][0].substring(0,srvNameLength) : item[3][0];
	var uri=item[3][1];
	var srvID = item[0];
	
	//Cargar el div
	var col=item[1];
	if (col=='1')
		col='left_column';
	else if (col=='2')
	col = 'right_column';
	var div=document.getElementById(col);
	var divDrag = document.createElement('div');
	divDrag.setAttribute('class','nodrag');
	divDrag.setAttribute('id','drag_'+srvID);
	divDrag.setAttribute('serviceID',srvID);
	divDrag.setAttribute('posicion',item[1]+","+item[2]);
	divDrag.setAttribute('uri',uri);
	var header=document.createElement('div');
	header.setAttribute('class','headerWidget');
	header.setAttribute('id', 'header'+srvID);
	var title=document.createElement('div');
	title.setAttribute('id','title'+srvID);
	title.setAttribute('class','titleWidget');
	var divName = document.createElement('div');
	divName.setAttribute('class','divName');
	var h3=document.createElement('h3');
	h3.setAttribute('class','experiment');
	h3.innerHTML=srvName;
	divName.appendChild(h3);
	var buttons=document.createElement('div');
	buttons.setAttribute('class','buttonsWidget');
	buttons.setAttribute('id','buttonsWidget_'+srvID);
	
	/*var button = document.createElement('img');
	button.setAttribute('class', 'gwt-Button');
	button.setAttribute('src', 'maximize.png');
	button.setAttribute('title','Maximize Widget');
	button.setAttribute('onclick', 'loadWidgetContainer(\''+uri+'\', \''+srvName+'\')');
	buttons.appendChild(button);*/
	
	button = document.createElement('img');
	button.setAttribute('class', 'gwt-Button');
	button.setAttribute('src', 'change.png');
	button.setAttribute('id', 'minimize');
	button.setAttribute('title','Move');
	button.setAttribute('onclick', 'moveService(\''+expID+'\', \''+srvID+'\',\''+uri+'\',\'0\',this)');
	buttons.appendChild(button);
	
	button = document.createElement('img');
	button.setAttribute('class', 'gwt-Button');
	button.setAttribute('src', 'close.png');
	button.setAttribute('title','Remove Widget');
	
	button.setAttribute('onclick', 'deleteService(\''+expID+'\', \''+srvID+'\', \''+usrID+'\')');
	buttons.appendChild(button);
	
	title.appendChild(divName);
	title.appendChild(buttons);
	
	
	header.appendChild(title);
	divDrag.appendChild(header);
	//div.appendChild(header);
	var zone=document.createElement('div');
	//zone.setAttribute('id', 'services2');
	zone.setAttribute('class', 'text_desno');
	zone.setAttribute('id', 'srvWidget'+srvID);
	var layDD = document.createElement('div');
	layDD.setAttribute('id','layDD'+srvID);
	layDD.setAttribute('class', 'divLay');
	layDD.setAttribute('ondragenter', 'cancel(event)');
	layDD.setAttribute('ondrop', 'drop(event,\''+srvID+'\')');
	layDD.setAttribute('ondragover', 'cancel(event)');
	//layDD.setAttribute('onmouseover', 'setVisibleOpacity(\'layDD'+srvID+'\')');
	//layDD.setAttribute('onmouseout', 'setHiddenOpacity(\'layDD'+srvID+'\')');
	//addEvent(layDD, 'dragenter', cancel);
	//addEvent(layDD, 'drop', drop);
	//addEvent(layDD, 'dragover', cancel);
	var content = document.createElement('iframe');
	content.setAttribute('id', 'services'+srvID);
	if (uri!='') { 
	content.setAttribute('width', '185');
	content.setAttribute('height', '170');
	content.setAttribute('frameborder', '0');
	content.setAttribute('marginheight', '0');
	content.setAttribute('marginwidth', '0');
	content.setAttribute('framespacing', '0');
	content.setAttribute('border', '0');
	content.setAttribute('top', '0');
	content.setAttribute('class','widget-container');
	var sep="?";
	if (uri.indexOf("?")!=-1)
	{
		sep = "&";
	}
	content.setAttribute('src', uri+sep+'width=185&height=170&color=0066bb&expid='+expInfo[4]+"&srvid="+srvID);
	//addEvent(content, 'mouseout', processMouseOut);
	//addEvent(content, 'mouseover', processMouseOver);
	content.setAttribute('onmouseout', 'processMouseOut()');
	content.setAttribute('onmouseover', 'processMouseOver(\''+srvID+'\')');
	}
	zone.appendChild(layDD);
	zone.appendChild(content);
	//div.appendChild(zone);
	divDrag.appendChild(zone);
	div.appendChild(divDrag);
}





function nextItem(srvs) {
	var pos=-1;
	var col=-1;
	var row=2000;
	var i;
	for (i=0;i<srvs.length;i++) {
		if (srvs[i][1]>col || ((srvs[i][1]==col)&&(srvs[i][2]<row))) {
			pos=i;
			col=srvs[i][1];
			row=srvs[i][2];
		}
	}
	return pos;
}



function loadServices() {
	var pos;
	srvAux = services.slice(0);
	while (srvAux.length>0) {
		pos = nextItem(srvAux);
		//loadWidget(srvAux[pos],pos);
		loadWidget2(srvAux[pos],pos);
		srvAux.splice(pos,1);
	}
}


