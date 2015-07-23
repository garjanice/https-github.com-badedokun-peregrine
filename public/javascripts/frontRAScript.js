$(document).ready(function(){
	$(".delete-btn").click(function() {
		
		 // Confirm
        if ( ! confirm('Are you sure want to delete this Criteria?')){
            return false;
        }
		
		$.ajax({
			url : "/riskAssessment/delete",
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
	});
});
	
	$(function() {
		var rows = $('tr').not(':first');
		
		rows.on('click', function(e) {
			var row = $(this);
			if ((e.ctrlKey || e.metaKey) || e.shiftKey) {
				row.addClass('highlight');
			} else {
				rows.removeClass('highlight');
				row.addClass('highlight');
				
				var index = $(row).index(); //table row index
				var obj = {
						"val":String(index)
				}
				
				$.ajax({
					url: "/riskAssessment/selected",
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
				
		})

	});
		$(document).bind('selectstart dragstart', function(e) {
			e.preventDefault();
			return false;
		});
