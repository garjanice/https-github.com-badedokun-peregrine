/**
 *  This function calls the specific URLs for querying the list of Procedures.
 *  @author Nilima Shinde.
 *  @version 1.0 
 */

		function searchTable()
		{
			var SearchElement = document.getElementById('pListSearchText');
		    var inputVal = SearchElement.value;

			var table = $('#pTable');
			table.find('tr').each(function(index, row)
			{
				var allCells = $(row).find('td');
				if(allCells.length > 0)
				{
					var found = false;
					allCells.each(function(index, td)
					{
						var regExp = new RegExp(inputVal, 'i');
						if(regExp.test($(td).text()))
						{
							found = true;
							return false;
						}
					});
					if(found == true)$(row).show();else $(row).hide();
				}
			});
		}

	
	

onload = function() {
    if (!document.getElementsByTagName || !document.createTextNode) return;
    var rows = document.getElementById('pTable').getElementsByTagName('tbody')[0].getElementsByTagName('tr');
    for (i = 0; i < rows.length; i++) {
        rows[i].onclick = function() {
            $('#pViewButton').prop("disabled", false);
            $('#pUpdateButton').prop("disabled", false);
            $('#pDeleteButton').prop("disabled", false);
            $('#pPrintButton').prop("disabled", false);
            var row = $(this);
            var rows2 = $('tr').not(':first');
            rows2.removeClass('highlight');
			row.addClass('highlight');
            var index = this.rowIndex - 1;
           var obj = {
                   "val": String(index)
           	}
            
            $.ajax({
				url: "/procedure/selected",
				type: 'POST',
				contentType: 'application/json; charset=utf-8',
				dataType: 'json',
				data: JSON.stringify(obj),
				success: function(res) {
                    if (res){
                        console.log("POST Success!");
                    }
                    else {
                        console.log("POST Failure!")	
                    }
                }
            })
        }   
    }
}
/* for delete procedure*/
function deleteP(){
	if (!confirm('Are you sure want to delete this Procedure?')){
        return false;
    }
	$.ajax({
		url : "/procedure/delete",
		type : 'DELETE',
		success : function(result) {
			if (result){
			console.log("DELETE Success!");
			location.reload();
			}
			else{
			console.log("DELETE Failure");
			}
		}
	});
}
