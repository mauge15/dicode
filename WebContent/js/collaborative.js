var collaboratives;
var expInfo;

function createNewCollaborative() 
{
	var title ='testtitle';
	var descr ='testdesc';
	var priv = '1';
	var modify ='1';
	var upload ='1';
	var uploadmaxsize ='1';
	var type = expInfo[5];
	var expID = expInfo[4];
	var url = 'CreateCollaborative?expid='+expID+'&title='+title+'&descr='+descr+'&private='+priv+
	'&modify='+modify+'&upload='+upload+'&uploadmaxsize='+uploadmaxsize+'&type='+type;
	alert(url);
	doCreateCW(url);
}

function doCreateCW(url) 
{
	alert('Estoy en doCreateCW');
	if( window.XMLHttpRequest ) 
	{
		req = new XMLHttpRequest();
	} 
	else 
	{
		req = new ActiveXObject('Microsoft.XMLHTTP');
	}
	req.open('GET', url, true);
	req.onreadystatechange=callbackNewCW;
	req.send(null);
}

function callbackNewCW()
{
	alert('Estoy en callbackNewCW');
	if( req.readyState == 4 )
	{
		if( req.status == 200 )
		{
			processNewCW();
		}
	}
}

function processNewCW() 
{
	alert(req.responseText);
	var id = req.responseXML.getElementsByTagName('id');
	//var code = req.responseXML.getElementsByTagName('code');
	collaboratives.length = ['\''+id+'\'', [],] ;
}


function collaborativeOptions (id) 
{
	var div = document.getElementById('cWOptions');
	removeAllChilds('cWSelect');
	var cOptions = document.getElementById('cWSelect');
	var cwSelect = document.createElement('select');
	cwSelect.setAttribute('id', 'selectCW');
	
	var cwList = createCWList();
	var option;
	var i;

	for (i=0;i<cwList.length;i++) 
	{
		option = document.createElement('option');
		option.setAttribute('value', i);
		if (i==id)
			option.setAttribute('selected', 'true');
		option.innerHTML = cwList[i][1];
		cwSelect.appendChild(option);
	}
	
	
	cOptions.appendChild(cwSelect);
	
	div.appendChild(cOptions);
	
	var list = '[';
	for (i=0; i<cwList.length;i++) 
	{
		list+='['+cwList[i][0]+',\''+cwList[i][1]+'\']';
		if (i!=cwList.length-1)
			list+=', ';
	}
	list+= ']';
	cwSelect.setAttribute('onchange', 'setVisible([document.getElementById(\'loadPanelGlass\')]); timerMsg(); createCWAux('+list+'); ');	
	
}

function createCWList () 
{
	var list = [];
	var i;
	for (i=0; i<collaboratives.length; i++) 
	{
		list[list.length]=[i, collaboratives[i][2]];
	}
	return list;
}

function createCWAux(list) 
{
	var index = document.getElementById('selectCW').selectedIndex;
	createCW(index, collaboratives[list[index][0]][1]);
}