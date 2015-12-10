
/**
 *  This function sets up the pagination.
 *  @author Benjamin J Cargile
 *  @version 1.0 -- 8/27/2015
 */

(function() {
	if(rAFrontListSize){
		var pathArray = window.location.pathname.split( '/' );
	    var raListSortValue = window.location.search;
	    var numberOfPages = rAFrontListSize / pathArray[3];
	    numberOfPages = Math.ceil(numberOfPages);
	
	    var paginationDiv = document.getElementById("paginationArray");
	    var start = pathArray[2] - 5;
	    var extra = 0;
	    if(start <= 0){
	        start = 1;
	        extra = 5 - pathArray[2];
	    }
	    var end = Number(pathArray[2]) + 5;
	    
	    if(end > numberOfPages){
	        end = numberOfPages
	    }
	    
	    var tempPlaceholder = pathArray[2];
	    tempPlaceholder--;
	    var fragment = '';
	    if(raListSortValue){
	    	if(pathArray[2] > 1){
		        fragment += '<a class="pagination-link blue-link" href="/riskAssessment/' + tempPlaceholder + '/' + pathArray[3] +  '/' + pathArray[4] + raListSortValue + '"> < </a>';
		    }
		    for(var i=start; i <= end; i++){
		        fragment += '<a class="pagination-link blue-link" href="/riskAssessment/' + i + '/' + pathArray[3] + '/' + pathArray[4] +raListSortValue + '">' + i + '</a>';
		    }
		    tempPlaceholder = pathArray[2];
		    tempPlaceholder++;
		    if(pathArray[2] < numberOfPages){
		        fragment += '<a class="pagination-link blue-link" href="/riskAssessment/' + tempPlaceholder + '/' + pathArray[3] + raListSortValue +  '/' + pathArray[4] +'"> > </a>';
		    }
	    } else {
		    if(pathArray[2] > 1){
		        fragment += '<a class="pagination-link blue-link" href="/riskAssessment/' + tempPlaceholder + '/' + pathArray[3] +  '/' + pathArray[4] +'"> < </a>';
		    }
		    for(var i=start; i <= end; i++){
		        fragment += '<a class="pagination-link blue-link" href="/riskAssessment/' + i + '/' + pathArray[3] + '/' + pathArray[4] +'">' + i + '</a>';
		    }
		    tempPlaceholder = pathArray[2];
		    tempPlaceholder++;
		    if(pathArray[2] < numberOfPages){
		        fragment += '<a class="pagination-link blue-link" href="/riskAssessment/' + tempPlaceholder + '/' + pathArray[3] +  '/' + pathArray[4] +'"> > </a>';
		    }
	    }
	    paginationDiv.innerHTML = fragment;
	}
}());

/**
 *  This function calls the specific URLs for sorting the list of Risk Assessments.
 *  @author Benjamin J Cargile
 *  @version 1.0 -- 8/27/2015
 */

function raListSort(type){
    
    var pathArray = window.location.pathname.split( '/' );
    var raListSortValue = window.location.search;;
    if(raListSortValue){
        if(type == 'risk'){
            if(pathArray[4] == 'descendingRisk'){
                window.open('/riskAssessment/1/' + pathArray[3] + '/ascendingRisk?' + raListSortValue, "_self");
            }
            else {
                window.open('/riskAssessment/1/' + pathArray[3] + '/descendingRisk?' + raListSortValue, "_self");
            }
        } else if(type == 'severity'){
            if(pathArray[4] == 'descendingSeverity'){
                window.open('/riskAssessment/1/' + pathArray[3] + '/ascendingSeverity?' + raListSortValue, "_self");
            }
            else {
                window.open('/riskAssessment/1/' + pathArray[3] + '/descendingSeverity?' + raListSortValue, "_self");
            }
        } else if(type == 'likelihood'){
            if(pathArray[4] == 'descendingLikelihood'){
                window.open('/riskAssessment/1/' + pathArray[3] + '/ascendingLikelihood?' + raListSortValue, "_self");
            }
            else {
                window.open('/riskAssessment/1/' + pathArray[3] + '/descendingLikelihood?' + raListSortValue, "_self");
            }
        } else if(type == 'consequence'){
            if(pathArray[4] == 'descendingConsequence'){
                window.open('/riskAssessment/1/' + pathArray[3] + '/ascendingConsequence?' + raListSortValue, "_self");
            }
            else {
                window.open('/riskAssessment/1/' + pathArray[3] + '/descendingConsequence?' + raListSortValue, "_self");
            }
        }
    } else {
        if(type == 'risk'){
            if(pathArray[4] == 'descendingRisk'){
                window.open('/riskAssessment/1/' + pathArray[3] + '/ascendingRisk' , "_self");
            }
            else {
                window.open('/riskAssessment/1/' + pathArray[3] + '/descendingRisk' , "_self");
            }
        } else if(type == 'severity'){
            if(pathArray[4] == 'descendingSeverity'){
                window.open('/riskAssessment/1/' + pathArray[3] + '/ascendingSeverity' , "_self");
            }
            else {
                window.open('/riskAssessment/1/' + pathArray[3] + '/descendingSeverity' , "_self");
            }
        } else if(type == 'likelihood'){
            if(pathArray[4] == 'descendingLikelihood'){
                window.open('/riskAssessment/1/' + pathArray[3] + '/ascendingLikelihood' , "_self");
            }
            else {
                window.open('/riskAssessment/1/' + pathArray[3] + '/descendingLikelihood' , "_self");
            }
        } else if(type == 'consequence'){
            if(pathArray[4] == 'descendingConsequence'){
                window.open('/riskAssessment/1/' + pathArray[3] + '/ascendingConsequence' , "_self");
            }
            else {
                window.open('/riskAssessment/1/' + pathArray[3] + '/descendingConsequence' , "_self");
            }
        }
    }
    
    
}
/**
 *  This function calls the specific URLs for querying the list of Risk Assessments.
 *  @author Benjamin J Cargile
 *  @version 1.0 -- 8/27/2015
 */
function raListSearch(){
    var SearchElement = document.getElementById('raListSearchText');
    var SearchText = SearchElement.value;
    var url =  window.location.pathname + '?query=' + SearchText;
    window.open(url, "_self");
}

onload = function() {
    if (!document.getElementsByTagName || !document.createTextNode) return;
    var rows = document.getElementById('raTable').getElementsByTagName('tbody')[0].getElementsByTagName('tr');
    for (i = 0; i < rows.length; i++) {
        rows[i].onclick = function() {
            $('#raViewButton').prop("disabled", false);
            $('#raUpdateButton').prop("disabled", false);
            $('#raDeleteButton').prop("disabled", false);
            $('#raPrintButton').prop("disabled", false);
            var row = $(this);
            var rows2 = $('tr').not(':first');
            rows2.removeClass('highlight');
			row.addClass('highlight');
            var index = this.rowIndex - 1;
            
            var pathArray = window.location.pathname.split( '/' );
            var actualIndex = (pathArray[2] - 1) * pathArray[3] + index;
            //alert(actualIndex);
            var raListSortValue = window.location.search;
            if(raListSortValue[1]){
                var obj = {
                    "val": String(index),
                    "sort": pathArray[4],
                    "query": raListSortValue
                    }
            } else {
                var obj = {
                    "val":String(index),
                    "sort":raListSortValue[0],
                    "query":"UNDEFINED"
                    }
            
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
    }
}
/**
 * This function confirms the deletion of a Risk Assessment
 * and then perform a ajax call to cause the deletion.
 * @author Benjamin J Cargile (someone else made this before me)
 * @version 1.0 -- 9/2/2015
 * 
 */
function deleteRA(){
	if (!confirm('Are you sure want to delete this Risk Assessment?')){
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
}
/**
 * This function is the for the Cancel Button
 * @author Benjamin J Cargile 
 * @version 1.0 -- 9/2/2015
 * 
 */
function goRAFrontPage(){
	window.open("/riskAssessment/1/10/descendingRisk", "_self");
}

/**
 * This function is for changing the number of items in the table. 
 * @author Benjamin J Cargile (someone else made this before me)
 * @version 1.0 -- 9/2/2015
 * 
 */
function changeViewRA(numberOfItems){
	var pathArray = window.location.pathname.split( '/' );
    var raListSortValue = window.location.search;
    if(raListSortValue){
    	window.open('/riskAssessment/1/' + numberOfItems + '/' + pathArray[4] + raListSortValue, "_self");
    } else{
    	window.open('/riskAssessment/1/' + numberOfItems + '/' + pathArray[4], "_self");
    }
 }
