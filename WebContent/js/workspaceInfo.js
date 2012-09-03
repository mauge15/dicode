/**
 * 
 */
function remove(id)
{
	var user = $("#guser"+id).attr("user");
	$("#sel_non_granted").append('<option value="'+id+'">'+user+'</option>');
	$("#guser"+id).remove();
}

function addUsers() 
{
	obj=$("#sel_non_granted");
	if (obj.selectedIndex==-1)
		return;
	$("#sel_non_granted :selected").each(function(i, selected){
		var txt = $(selected).text(); 
		var valor = $(selected).val(); 
		var id = "guser"+valor;
		var a = '<a href="javascript:remove(\''+valor+'\')"><img src=\"images/remove.gif\"/>&nbsp;Remove</a>';
		$('#rounded-corner > tbody:last').append('<tr id="'+id+'" user="'+txt+'"><td id="guser'+valor+'" user="'+txt+'">'+txt+'</td><td>'+a+'<br><input value="'+valor+'" class="granted_users" id="chk_'+valor+'" iduser="'+valor+'" type="checkbox"/>Full Control</td></tr>');
		$(selected).remove();
	});	
}


function savePermissions(expID)
{
	var listPermissions = new Array();
	var temp;
	var cont=0;
	var bool=false;
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
		listPermissions[cont] = temp;
		cont = cont + 1;
	});
		
	$("#sel_non_granted option").each(function () {
		var idUser = $(this).val();
		temp=idUser+",0";
		listPermissions[cont] = temp;
		cont = cont + 1;
    });
	
	if (bool)
	{
		var request = $.ajax({
			type: "POST",
			url: "UpdatePermissions",
			data: { "permissions[]": listPermissions, "exp": expID }
		});
		request.done(function( msg ) {
			alert( "Permissions saved ");
		});
		request.fail(function(jqXHR, textStatus) {
			alert( "Request failed: " + textStatus );
			alert(jqXHR.responseText);
		});
	}
	else
	{
		window.alert("At least one user must have Full Control in this workspace");
	}
}
	