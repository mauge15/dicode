

function remove_workspace(exID) 
{
	if (confirm("You are about to delete this workspace. Are you sure?\n(This operation can not be undone)")) 
	{
		var url="restServiceHelper.jsp?operation=deleteExperiment&expID="+exID;
		var req = $.ajax({
			  type: "get",
			  url: url,
			  success: function(data) {
				    window.alert("Workspace Deleted");
				    location.reload(true);
				    }
			});	
		
	}
	req.fail(function(jqXHR, textStatus) {
	  alert( "Request failed: " + textStatus );
	});
}


