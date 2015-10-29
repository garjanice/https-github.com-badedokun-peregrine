$(document).ready(function(){
	 searchTenant();
	
	function initializeTenantList()
	{
		var select = document.getElementById("popupTenantSelection");
		var length = select.options.length;
		for (i = length-1; i >= 0; i--) {
		  select.remove[i];
		}
	}
	
	function searchTenant()
	{						
		  var xhttp = new XMLHttpRequest();
		  xhttp.open("GET", "cacheTenants");
		  xhttp.send();
		  
		  xhttp.onreadystatechange = function() {
		    if (xhttp.readyState == 4 && xhttp.status == 200) {
		     var jsonObjArray = $.parseJSON(xhttp.responseText);
		     	 jsonObjArray.forEach(function (value){
			    	 $('#popupTenantSelection')
			         .append($("<option></option>")
			         .attr("value",value)
			         .text(value)); 
			     })
		    }
		  }						  
	}
	
	
	
	$("#tenantSearch").click(function(e) {						
	    //$("#popupTenantSelectionBox").dialog();
	    initializeTenantList();
	    //searchTenant();
	    document.getElementById('popup_background').style.display='block';
		document.getElementById('popupTenantSelectionBox').style.display='block';
		
	});

	$("#popupTenantSelection").change(function(e) {
		initializeTenantList();
		$("#popupTenantSelectionBox").hide();
		$("#tenantId").val($("#popupTenantSelection").val());
		document.getElementById('popupTenantSelectionBox').style.display='none';
		document.getElementById('popup_background').style.display='none';
	});
});
