if ($.browser.webkit) 
{
	document.write("<link rel='stylesheet' type='text/css' href='css/firefox.css'/>");
}
else if ($.browser.msie) 
{
	document.write("<link rel='stylesheet' type='text/css' href='css/explorer.css'/>");
}
else 
{
	document.write("<link rel='stylesheet' type='text/css' href='css/firefox.css'/>");
}