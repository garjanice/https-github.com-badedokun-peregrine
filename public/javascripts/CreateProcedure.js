$(document).ready(function()
{
	 function getLanguages(){
		    var xhttp = new XMLHttpRequest();
		    xhttp.open("GET","getLanguages");
		    xhttp.send();


		        xhttp.onreadystatechange = function(){
		            if (xhttp.readyState == 4 && xhttp.status == 200)
		            {
		                    $('#language').append(xhttp.responseText);
		            }

		        }
		    
		        
		    }
	 getLanguages();
});
