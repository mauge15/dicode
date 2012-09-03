// GLOBAL VARS
// List of service types, invocation methods, data types, parameter types and categories

/*services*/
var catID;
var listC;

var divC;

var selC;

//FUNCTIONS

function returnID(pair) {
  return pair[0];
}
function returnName(pair) {
	return pair[1];
}

function addOption(sel, text, pos) {
  var opt = document.createElement('option');
  opt.text = text;
  opt.value = pos;
  if (pos == catID)
    opt.setAttribute('selected', 'selected');
  if (!document.all)
    sel.appendChild(opt); // standard way -> doesn't work on i.e.
  else
    sel.add(opt, pos);    // i.e. way
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

function loadSelect (div, list, selName, sel) {
	var i;
	sel = createNewSelect(div,sel, selName);
	for (i = 0; i < list.length; i++) {
	    element = returnName(list[i]);
	    id = returnID(list[i]);
	    addOption(sel, element, id);
	}
}


//On load ----------------------------------------------------------------------------------------
function requiredField(field)
{  
	var errorField = $("#"+$(field).attr("id")+"Error");
    if(field.val()=="")
    {  
        $(errorField).show();
        return false;  
    }  
    else
    {  
        $(errorField).hide(); 
        return true;  
    }  
}
onload = function () {
  divC = document.getElementById('divC');
  loadSelect(divC, listC, 'category',selC);
};

$(document).ready(function(){
	
	$("#updateService").submit(function(){  
	    if(requiredField($("#nameService")) && requiredField($("#aliasService")) && requiredField($("#URIService")) && requiredField($("#category")) )  
	        return true  
	    else  
	        return false;  
	});  
	
	$('textarea').keyup(function(){
		var limit = $(this).attr('maxlength');
		if($(this).val().length < limit) 
		{
			// muestra la cantidad de caracteres que resta dentro del span
			$(this).next('.info_caracteres').find('span').html(limit-$(this).val().length);
		}
		else
		{
			// si esta llego al limite muestra 0
			$(this).next('.info_caracteres').find('span').html('0');
			return false; 
		}
	});
});