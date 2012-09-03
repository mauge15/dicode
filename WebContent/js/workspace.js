
function deleteService(exID, srvID,  usrID) 
{
	var request;
	if (confirm("Dou you want to delete this service from the workspace?")) 
	{
		request = $.ajax({
			type: "GET",
			url: "DeleteService",
			data: 
			{ 
				"exp": exID, 
				"srv": srvID,
				"usr": usrID
		    }
		});
		request.complete(function (jqXHR, textStatus) {
			var res = jqXHR.responseText;
			if (res=='1') 
			{
				$("#drag_"+srvID).remove();
				alert( " Widget Deleted ");
			}
			else
			{
				alert('You can\'t delete this service. ERROR ('+res+')');
			}
		});
		
		request.fail(function(jqXHR, textStatus) {
			alert( "Request failed: " + textStatus );
			alert(jqXHR.responseText);
		});
		
		
	}
}

function popup()
{
    $("#popup").dialog({                
    	height: 450,
    	width: 440,
    	position: "center",
    	modal: true
    });
}

function closeSession(change,id)
{
	executedAction = true;
	if (change)
	{
		 $( "#dialog-close-workspace" ).dialog({
             height: 200,
             width: 300,
             position: "center",
             modal: true,
             buttons: {
 				"Save": function() {
 					save_config(id);
 					$( this ).dialog( "close" );
 					window.close();
 				},
 				"Don't Save": function() {
 					$( this ).dialog( "close" );
 					window.close();
 				},
 				Cancel: function() {
 					$( this ).dialog( "close" );
 					executedAction = false;
 				}
 			}
     });  	
	}
	else
	{
		window.close();
	}
}


function addUsers() 
{
	obj = $("#sel_non_granted");
	if (obj.selectedIndex==-1)
		return;
	$("#sel_non_granted :selected").each(function(i, selected)
			{
		var txt = $(selected).text(); 
		var valor = $(selected).val(); 
		var id = "guser"+valor;
		var a = '<a href="javascript:remove(\''+valor+'\')"><img src=\"images/remove.gif\"/>&nbsp;Remove</a>';
		$('#rounded-corner > tbody:last').append('<tr id="'+id+'" user="'+txt+'"><td id="guser'+valor+'" user="'+txt+'">'+txt+'</td><td>'+a+'<br><input value="'+valor+'" class="granted_users" id="chk_'+valor+'" iduser="'+valor+'" type="checkbox"/>Full Control</td></tr>');
		$(selected).remove();
	});	
}
	
function remove(id)
{
	var user = $("#guser"+id).attr("user");
	$("#sel_non_granted").append('<option value="'+id+'">'+user+'</option>');
	$("#guser"+id).remove();
}
	
	
function savePermissions(expID)
{
	var listPermissions = new Array();
	var temp;
	var cont = 0;
	var bool = false;
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

function loadWidget3(srvName, pos, elem) 
{
	srvID = 200;
	var divDrag = document.createElement('div');
	divDrag.setAttribute('class','nodrag');
	divDrag.setAttribute('id','drag_'+srvID+'_w');
	divDrag.setAttribute('serviceID',srvID);
	divDrag.setAttribute('posicion',pos[0]+","+pos[1]);
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
	button = document.createElement('img');
	button.setAttribute('class', 'gwt-Button');
	button.setAttribute('src', 'change.png');
	button.setAttribute('onclick', 'moveService(\'2\', \''+srvID+'\',\'collab\',\'0\',this)');
	buttons.appendChild(button);
	title.appendChild(divName);
	title.appendChild(buttons);
	header.appendChild(title);
	divDrag.appendChild(header);
	var zone=document.createElement('div');
	zone.setAttribute('class', 'text_desno');
	zone.setAttribute('id', 'srvWidget'+srvID);
	var layDD = document.createElement('div');
	layDD.setAttribute('id','layDD'+srvID);
	layDD.setAttribute('class', 'divLay');
	layDD.setAttribute('ondragenter', 'cancel(event)');
	layDD.setAttribute('ondrop', 'drop(event,\''+srvID+'\')');
	layDD.setAttribute('ondragover', 'cancel(event)');
	var wrapper = document.createElement('div');
	wrapper.setAttribute('width', '175');
	wrapper.setAttribute('height', '170');
	var content = document.createElement("img");
	content.setAttribute("id","collabImage");
	content.setAttribute("src","images/collab.jpg");
	zone.appendChild(layDD);
	zone.appendChild(content);
	zone.appendChild(wrapper);
	divDrag.appendChild(zone);
	return divDrag;
}

function collab_is_widget()
{
	var band = false;
	$.each( $("#left_column").children(), function(i, l){
		temp = $(l).attr("id");
		if (temp=="drag_200_w")
		{
			band=true;
		}
	});
	if (band == false)
	{
		$.each( $("#right_column").children(), function(i, l){
			temp = $(l).attr("id");
			if (temp=="drag_200_w")
			{
				band=true;
			}
		});
	}
	return band;
}
	

function save_config(ID)
{
	var band = collab_is_widget();
	change = false;
	var success = true;
	if (!band)
	{
		$.each($("#left_column").children(), function(i, l){
			var posi = $(l).attr("posicion");
			var arr = posi.split(",");
			var col = arr[0];
			var row=arr[1];
	        var srvID = $(l).attr("serviceid");
			var request = $.ajax({
				type: "POST",
				url: "restServiceHelper.jsp",
				data: { "operation": "updateUserWidgetConfiguration", "usrID": ID, "srvID":srvID,"expID":expID,"col":col,"row":row }
			
			});
			
			request.fail(function(jqXHR, textStatus) {
				alert( "Request failed: " + textStatus );
				alert(jqXHR.responseText);
				success=false;
			});
			
			
		});
		
		$.each( $("#right_column").children(), function(i, l){
			var posi = $(l).attr("posicion");
			var arr = posi.split(",");
			var col = arr[0];var row=arr[1];
			var srvID = $(l).attr("serviceid");
			var request = $.ajax({
				type: "POST",
				url: "restServiceHelper.jsp",
				data: { "operation": "updateUserWidgetConfiguration", "usrID": ID, "srvID":srvID,"expID":expID,"col":col,"row":row }
			
			});
			
			request.fail(function(jqXHR, textStatus) {
				alert( "Request failed: " + textStatus );
				alert(jqXHR.responseText);
				success=false;
			});
			
			
		});
		$( "#dialog-modal" ).dialog({
			height: 80,
			position: "center",
			modal: true
		});  
	}
	else
	{
		var posWid = $(".center_content").attr("posicion");
		$.each( $("#left_column").children(), function(i, l){
			var posi = $(l).attr("posicion");
			var srvID ;
			if (posi==posWid)
			{
				srvID = $(".center_content").attr("serviceid");
			}
			else
			{
				srvID = $(l).attr("serviceid");
			}
			var arr = posi.split(",");
			var col = arr[0];var row=arr[1];
			var request = $.ajax({
				type: "POST",
				url: "restServiceHelper.jsp",
				data: { "operation": "updateUserWidgetConfiguration", "usrID": ID, "srvID":srvID,"expID":expID,"col":col,"row":row }
			
			});
			
			request.fail(function(jqXHR, textStatus) {
				alert( "Request failed: " + textStatus );
				alert(jqXHR.responseText);
				success=false;
			});
		});
		
		$.each( $("#right_column").children(), function(i, l){
			var posi = $(l).attr("posicion");
			var arr = posi.split(",");
			var col = arr[0];var row=arr[1];
			var srvID;
			if (posi==posWid)
			{
				srvID = $(".center_content").attr("serviceid");
			}
			else
			{
				srvID = $(l).attr("serviceid");
			}
			var request = $.ajax({
				type: "POST",
				url: "restServiceHelper.jsp",
				data: { "operation": "updateUserWidgetConfiguration", "usrID": ID, "srvID":srvID,"expID":expID,"col":col,"row":row }
			
			});
			
			request.fail(function(jqXHR, textStatus) {
				alert( "Request failed: " + textStatus );
				alert(jqXHR.responseText);
				success=false;
			});
		});
		if (success)
			{
		$( "#dialog-modal" ).dialog({
			height: 80,
			position: "center",
			modal: true
		});  
			}
	}	  
}


function moveService(expID, srvID, uri, status, button)
{
	change = true;
	var central_content = $("#main_content").children("#drag_200");
	if (central_content.length==0)
	{
		central_content =  $("#main_content").children(".center_content");
	}
	else if ($(central_content).css("display")=="none")
	{
		central_content =  $("#main_content").children(".center_content");
	}
	var id_central_content  = $(central_content).attr("id");
	var pos ;
	var widget;
	var minimized_widget;
	
	//status 0 = Move Widget or Collaborative to the center
	//status 1 = Move Widget to its column
	if(status == 0)
	{
		if (srvID!=200)
		{
			//Move Widget to the center
			widget = "#drag_"+srvID;
			if (id_central_content=="drag_200")
			{
				//Exchange Widget with Collaborative
				var tempCollab = $("#center_CW").clone();
				$("#drag_200").css("display", "none");
				pos = $(widget).attr("posicion");
				pos = pos.split(",");
				minimized_widget = loadWidget3("Collaborative", pos,tempCollab);
			}
			else
			{
				//Exchange Widget with Widget
				var tempServID = $("#"+id_central_content).attr("serviceid");
				var tempUri = $("#"+id_central_content).attr("uri");
				$("#"+id_central_content).attr("status","0");
				$("#drag_"+tempServID).switchClass( "center_content", "nodrag", 1000 );
			 	$("#header"+tempServID).switchClass( "headerWidget_big","headerWidget",  1000 );
			 	$("#title"+tempServID).switchClass( "titleWidget_big","titleWidget",  1000 );
			 	$("#buttonsWidget_"+tempServID ).css('display', '');
			 	$("#services"+tempServID).attr('width', '175');
				$("#services"+tempServID).attr('height', '170');
				
				tempUri = $("#services"+tempServID).attr("src");
			 	
			 	var newURI = tempUri.replace("510","170");
			 	newURI = newURI.replace("550","175");
			 	
			 	$( "#services"+tempServID ).attr('src', newURI);
				
				$("#drag_"+tempServID).find("#minimize").attr("onClick","moveService('"+expID+"','"+tempServID+"','"+tempUri+"','0',this)");	
				
				//Exchange the position of the Widget that has to be moved to the center
				pos = $(widget).attr("posicion");
				pos = pos.split(",");
				minimized_widget = "#drag_"+tempServID;
			}
			if (pos[0]==1)
			{
				var pos2 = pos[1]-2;
				if (pos2==-1)
				{
					$("#left_column").prepend($(minimized_widget));
				}
				else
				{
					var temp = $("#left_column").children().get(pos2);
					$(temp).after($(minimized_widget));
				}
			}
			else
			{
				var pos2 = pos[1]-2;
				if (pos2==-1)
				{
					$("#right_column").prepend($(minimized_widget));
				}
				else
				{
					var temp = $("#right_column").children().get(pos2);
					$(temp).after($(minimized_widget));
				}
			}
			
			$( "#main_content" ).append($(widget));
			$( "#layDD" + srvID ).switchClass("divLay","divLay_big",1000);
		 	$( "#drag_" + srvID ).switchClass( "nodrag", "center_content", 1000 );
			$( "#drag_" + srvID ).attr( "status", "1");
		 	$( "#header" + srvID ).switchClass( "headerWidget", "headerWidget_big", 1000 );
		 	$( "#title" + srvID ).switchClass( "titleWidget", "titleWidget_big", 1000 );
		 	$( "#services" + srvID ).attr('width', '560');
		 	$( "#buttonsWidget_" + srvID ).css('display', 'none');
		 	$( "#services" + srvID ).attr('height', '520');

		 	uri = $("#services" + srvID).attr("src");
		 	
		 	var newURI = uri.replace("170","510");
		 	newURI = newURI.replace("175","550");
		 	
		 	$( "#services" + srvID ).attr('src', newURI);
		 	$(button).attr("onClick","moveService('" + expID +"','" + srvID + "','" + uri + "','1',this)");	
		}
		else
		{
			//Move Collaborative from a column to Central Screen
			tempServID = $("#"+id_central_content).attr("serviceid");
			pos = $("#drag_200_w").attr("posicion");
			pos = pos.split(",");
			$("#drag_200_w").remove();
			$("#drag_200").css("display", "");
			tempUri = $("#"+id_central_content).attr("uri");
			$("#"+id_central_content).attr("status","0");
			$("#drag_"+tempServID).switchClass( "center_content", "nodrag", 1000 );
		 	$("#header"+tempServID).switchClass( "headerWidget_big","headerWidget",  1000 );
		 	$("#title"+tempServID).switchClass( "titleWidget_big","titleWidget",  1000 );
		 	$("#buttonsWidget_"+tempServID ).css('display', '');
		 	$("#services"+tempServID).attr('width', '175');
			$("#services"+tempServID).attr('height', '170');
			
			tempUri = $("#services"+tempServID).attr("src");
		 	
		 	var newURI = tempUri.replace("510","170");
		 	newURI = newURI.replace("550","175");
		 	
		 	$( "#services"+tempServID ).attr('src', newURI);
			
			$("#drag_"+tempServID).find("#minimize").attr("onClick","moveService('"+expID+"','"+tempServID+"','"+tempUri+"','0',this)");
			minimized_widget = "#drag_"+tempServID;
			if (pos[0]==1)
			{
				var pos2= pos[1]-2;
				if (pos2==-1)
				{
					$("#left_column").prepend($(minimized_widget));
				}
				else
				{
					var temp = $("#left_column").children().get(pos2);
					$(temp).after($(minimized_widget));
				}
			}
			else
			{
				var pos2= pos[1]-2;
				if (pos2==-1)
				{
					$("#right_column").prepend($(minimized_widget));
				}
				else
				{
					var temp = $("#right_column").children().get(pos2);
					$(temp).after($(minimized_widget));
				}
			}
		}
	}
	return false;
}


$(function() {
	$( "#right_column" ).sortable({ handle: "h3" , cursor: "move", connectWith: "#left_column",
	update: function(event, ui) 
	{
		change = true;
		$.each( $("#right_column").children(), function(i, l){
			   $(l).attr("posicion","2,"+(i+1));
			 });
		$.each( $("#left_column").children(), function(i, l){
			   $(l).attr("posicion","1,"+(i+1));
			 });
	}
	});
	$("#left_column").sortable({handle: "h3", cursor: "move",connectWith: "#right_column" ,	
		update: function(event, ui) {
			change = true;
			$.each( $("#left_column").children(), function(i, l){
				   $(l).attr("posicion","1,"+(i+1));
				 });
			
			$.each( $("#right_column").children(), function(i, l){
				   $(l).attr("posicion","2,"+(i+1));
				 });
		}
	});

});


$(window).bind("beforeunload", function() { 
	if (executedAction == false)
	{
		if (change)
		{
			 $( "#dialog-close-workspace" ).dialog({
	             height: 200,
	             width: 300,
	             position: "center",
	             modal: true,
	             buttons: {
	 				"Save": function() {
	 					save_config(id);
	 					$( this ).dialog( "close" );
	 					window.close();
	 				},
	 				"Don't Save": function() {
	 					$( this ).dialog( "close" );
	 					window.close();
	 				},
	 				Cancel: function() {
	 					$( this ).dialog( "close" );
	 					executedAction = false;
	 				}}
	 		});
		}
	}
});


function help()
{
	$( "#dialog-help" ).dialog({
		height: 500,
		width: 500,
		position: "center",
		modal: true
	});  
}


function add_service()
{
	$( "#dialog-search-service" ).dialog({
		height: 500,
        width: 500,
        position: "center",
        modal: true
     });  
}

function workspace_info()
{
	$( "#dialog-workspace-info" ).dialog({
		height: 580,
		width: 550,
		position: "center",
		modal: true
     });  
}