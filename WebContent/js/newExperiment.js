//GLOBAL VARS

var listU;
var selU;
var idU;

function addOption(sel, text, pos) {
    var opt = document.createElement('option');
    opt.text = text;
    opt.value = pos;
    opt.setAttribute('iduser', pos);
    if (!document.all)
        sel.appendChild(opt); // standard way -> doesn't work on i.e.
    else
        sel.add(opt, pos);    // i.e. way
}

function loadSelect (div, list, selName, sel) {
	var i;
	//sel = createNewSelect(div,sel, selName);
	for (i = 0; i < list.length; i++) {
	    element = list[i][1];
	    id = list[i][0];
	    addOption(sel, element, id);
	}
}

function loadUsers (div, sel) {
	loadSelect(div, listU, 'allUsers', sel);
}

function loadUsersSelect() {
	var sel = null;
	var parent;
	sel = document.getElementById('allUsers');
	if (sel) {
		parent = sel.parentNode;
		loadUsers (parent, sel);
	}
}

//On load ----------------------------------------------------------------------------------------

//function to be called on page load.
function initPage() {
	idU = document.getElementById('allUsers');
	loadUsersSelect();
}

function remove(id)
{
	var user = $("#guser"+id).attr("user");
	$("#allUsers").append('<option iduser="'+id+'" value="'+id+'">'+user+'</option>');
	$("#user"+id).remove();
}

function addItem2(item1,item2) 
{
	obj=$("#allUsers");
	//item1 all users
	//item2 users granted
	if (obj.selectedIndex==-1)
		return;
		
	$("#allUsers :selected").each(function(i, selected){ 
		var txt = $(selected).text(); 
		var valor = $(selected).val(); 
		var id = "user"+valor;
		var a = '<a href="javascript:remove(\''+valor+'\')">Remove</a>';
		$('#usersGranted > tbody:last').append('<tr id="'+id+'"><td id="guser'+valor+'" user="'+txt+'">'+txt+'</td><td><input class="granted_users" type="checkbox" name="chk_granted[]" value="'+valor+',3" iduser="'+valor+'" /></td><td>'+a+'</td></tr>');
		$(selected).remove();
	});	
}


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

$(document).ready(function(){
	$('textarea').keyup(function(){
		var limit = $(this).attr('maxlength');
		if($(this).val().length < limit) {
			// muestra la cantidad de caracteres que resta dentro del span
			$(this).next('.info_caracteres').find('span').html(limit-$(this).val().length);
		}else{
			// si esta llego al limite muestra 0
			$(this).next('.info_caracteres').find('span').html('0');
			return false; 
		}
	});
	
	$("#newWorkspace").submit(function(){  
	    if(requiredField($("#workspaceName")) && requiredField($("#domain")))  
	        return true  
	    else  
	        return false;  
	});  
});