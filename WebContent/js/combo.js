function enableObject(list) 
{
	for (var i=0; i<list.length; i++)
		list[i].disabled=false;
}

function disableObject(list) 
{
	for (var i=0; i<list.length; i++)
		list[i].disabled=true;
}

function showObject(id) 
{
	var obj = document.getElementById(id);
	obj.style.visibility="visible";
	obj.style.display="block";
}

function hideObject(id)
{
	var obj = document.getElementById(id);
	obj.style.visibility="hidden";
	obj.style.display="none";
}

function addItem(item1,item2)
{
	obj=document.getElementById(item1);
	if (obj.selectedIndex==-1)
		return;
	for (var i=0; opt=obj.options[i]; i++) 
	{
		founded = 0;
 		if (opt.selected) 
 		{
 			valor=opt.value; // almacenar value
 			txt=obj.options[i].text; // almacenar el texto
 			obj2=document.getElementById(item2);
 			for (var j=0; opt2=obj2.options[j]; j++) 
 			{
 				if (txt==obj2.options[j].text)
 					founded=1;
 			}
 			if (!founded) 
 			{
 				opc = new Option(txt,valor);
 				eval(obj2.options[obj2.options.length]=opc);
 			}
 			obj.options[i]=null;
 		}
	}
}

function selectItems()
{
	var cont = 0;
	var sel = document.getElementById("hidden_granted");
	sel.innerHTML="";
	$(".granted_users").each(function(i,element){
		var idUser = $(element).attr("iduser");
		if ($(element).is(":checked"))
		{
		bool=true;
		temp = idUser+",4";
		}
		else
		{
			temp=idUser+",3";
		}
		var opt = document.createElement('input');
		opt.setAttribute('type', 'hidden');
		opt.setAttribute('name', 'granted[]');
		opt.setAttribute('value', temp);
		sel.appendChild(opt); 
		cont = cont + 1;	
	}
	);
	return false;
} 