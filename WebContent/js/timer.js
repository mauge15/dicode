var n = 0;

function timer(){
	n= n+1;
	callSession(n);	
	setTimeout("timer()",600000);
}


function callbackSessionUser(){
	// Cheching status
    if( req.readyState == 4 )
    {
    	if( req.status == 200 )
    	{
    		processsessionUser();
    		alert(req.responseText);
         }
    }
}


function callSession(n)
{
	var url = 'http://maimonides.dia.fi.upm.es:8080/Sessions/renovateSession/'+userName+'/'+pass;
	if( window.XMLHttpRequest ) 
	{
		req = new XMLHttpRequest();
	} 
	else 
	{
	    req = new ActiveXObject('Microsoft.XMLHTTP');
	}
	req.open('GET', url, true);    
	req.onreadystatechange=callbackSessionUser;
	req.send(null);
}