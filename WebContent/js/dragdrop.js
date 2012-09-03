var services;
var cwID;
var collaboratives;
var req;
var dPet;
var rMessage;

var addEvent = function(obj, evType, fn)
{
	  if (obj.addEventListener) 
	  {
	    obj.addEventListener(evType, fn, false);
	  } 
	  else if (obj.attachEvent) 
	  {
		  obj['e' + evType + fn] = fn;
		  obj[evType + fn] = function(){
	      obj["e" + evType + fn](self.event);};
	      obj.attachEvent("on" + evType, obj[evType + fn]);
	  }
};
	
function cancel(e) 
{
	if (e.preventDefault) 
	{
		e.preventDefault();
	}
	e.returnValue = false;
	return false;
} 

function setVisibleOpacity(name) 
{
	var div = document.getElementById(name);
	div.setAttribute('style', 'opacity:0.3; background-color:#000000; visibility:visible; display: block;');
}

function setHiddenOpacity(name) 
{
	var div = document.getElementById(name);
	div.setAttribute('style', 'background-color: transparent; visibility:visible; display: block;');
}

function processMouseOut() 
{
	var div;
	var i;
	var len = services.length;
	for(i=0; i < len; i++)
	{
		div = document.getElementById('layDD'+services[i][0]);
		if (div!=null)
		{
			div.removeAttribute('style');
		}
	}
	div = document.getElementById('layDDCW');
	if (div!=null)
	{
		div.removeAttribute('style');
	}
	isOverIFrame = false;
	top.focus();
}

function reload(frame)
{
	frame.data = frame.data;
}


function processMouseOver(i) 
{
	var div;
	var len = services.length;
	for(var j=0; j < len; j++)
	{
		if (i != services[j][0])
		{
			div = document.getElementById('layDD'+services[j][0]);
			if (div != null)
			{
				div.setAttribute("style",'visibility: visible; display: block;');
			}
	    }
	}
	if (i!=0) 
	{
		div = document.getElementById('layDDCW');
		if (div != null)
		{
			div.setAttribute("style",'visibility: visible; display: block;');
		}
	}
	isOverIFrame = true;
}


function processDragEnter(e) 
{
	if (e.preventDefault) 
	{
		e.preventDefault();
	}
	e.dataTransfer.effectAllowed = "copy";
	return false;
}

function processDragOver(e) 
{
	if (e.preventDefault) 
	{
		e.preventDefault();
	}
	e.dataTransfer.effectAllowed = "copy";
	return false;
}


function integration(iframe, url, text)
{
	iframe.setAttribute('src',url+'?term='+text);
}

function drop(e,i) 
{
	e.dataTransfer.effectAllowed = "copy";
	if (e.preventDefault) e.preventDefault();	
	var iframe=document.getElementById('services'+i);
	if (iframe.contentWindow.postMessage) 
	{
		/*var sour = e.dataTransfer.getData('Text');
		message = 'document,'+sour;
		iframe.contentWindow.postMessage(sMessage,"*");
		ddPet="";*/
		if (rMessage==" " || rMessage==undefined)
		{
			iframe.contentWindow.postMessage(e.dataTransfer.getData('Text'),"*");
			rMessage=" ";
		} 
		else 
		{
			iframe.contentWindow.postMessage(rMessage,"*");
			rMessage=" ";
		}
	} 
	else 
	{
		alert("Your browser does not support the postMessage");
	}
	return false;
}

function processUploadFile() 
{
	res = req.responseText;
}

function callbackUploadFile()
{
	if( req.readyState == 4 )
	{
		if( req.status == 200 )
		{
			var object = document.getElementById("copeitObject");
			reload(object);
		}
	}
}

function doUploadFile(url) 
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
	req.onreadystatechange=callbackUploadFile;
	req.send(null);
}

function dropCW(e) 
{
	e.dataTransfer.effectAllowed = "copy";
	if (e.preventDefault) e.preventDefault();
	var source = e.dataTransfer.getData('Text');
	var id = collaboratives[cwID][1][0];
	var divcoor = document.getElementById("Collaborative");
	var posx = e.clientX - (divcoor.offsetLeft + 10) + (document.all ? document.scrollLeft : window.pageXOffset);
	var posy = e.clientY - (divcoor.offsetTop + 10) + (document.all ? document.scrollTop : window.pageYOffset);
	var hiper = new String(e.dataTransfer.getData('text/x-moz-url'));
	var parts = hiper.split(source,2);
	var url= 'UploadFile?spaceId='+id+'&title='+parts[1]+'&posx='+posx+'&posy='+posy+'&contentUrl='+encodeURIComponent(source);
	doUploadFile(url);
	rMessage= " ";
}

function OnMessage(e) 
{	
	//Message Handler
	rMessage = e.data;
	ddPet = e.data;
	var obj = jQuery.parseJSON(rMessage);
	if (obj.PopUp!=undefined)
	{
		var frm = document.getElementById("popupf");
		var url = obj.PopUp;
		frm.setAttribute('src',url);
		frm.setAttribute('width',401);
		frm.setAttribute('height',410);
		popup();
	}
	else if ( obj.Item.type=="Action" && obj.Item.name=="Update")
	{
		if (document.getElementById("drag_"+obj.Item.id).getAttribute("uri") != obj.Item.uri)
		{
			document.getElementById("drag_"+obj.Item.id).setAttribute("uri",obj.Item.uri);
			document.getElementById("services"+obj.Item.id).setAttribute("src",obj.Item.uri);
		}    
		rMessage = " ";	
	}
}

function listener() 
{
	addEvent(window, "message", OnMessage);
}

