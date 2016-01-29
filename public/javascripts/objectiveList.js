
/**
 *  This function sets up the pagination.
 *  @author Benjamin J Cargile
 *  @version 1.0 -- 8/27/2015
 */

(function() {
	if(objectiveFrontListSize){
		var pathArray = window.location.pathname.split( '/' );
	    var objectiveListSortValue = window.location.search;
	    var numberOfPages = objectiveFrontListSize / pathArray[3];
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
	    if(objectiveListSortValue){
	    	if(pathArray[2] > 1){
		        fragment += '<a class="pagination-link blue-link" href="/objective/' + tempPlaceholder + '/' + pathArray[3] +  '/' + pathArray[4] + objectiveListSortValue + '"> < </a>';
		    }
		    for(var i=start; i <= end; i++){
		        fragment += '<a class="pagination-link blue-link" href="/objective/' + i + '/' + pathArray[3] + '/' + pathArray[4] + objectiveListSortValue + '">' + i + '</a>';
		    }
		    tempPlaceholder = pathArray[2];
		    tempPlaceholder++;
		    if(pathArray[2] < numberOfPages){
		        fragment += '<a class="pagination-link blue-link" href="/objective/' + tempPlaceholder + '/' + pathArray[3] + objectiveListSortValue +  '/' + pathArray[4] +'"> > </a>';
		    }
	    } else {
		    if(pathArray[2] > 1){
		        fragment += '<a class="pagination-link blue-link" href="/objective/' + tempPlaceholder + '/' + pathArray[3] +  '/' + pathArray[4] +'"> < </a>';
		    }
		    for(var i=start; i <= end; i++){
		        fragment += '<a class="pagination-link blue-link" href="/objective/' + i + '/' + pathArray[3] + '/' + pathArray[4] +'">' + i + '</a>';
		    }
		    tempPlaceholder = pathArray[2];
		    tempPlaceholder++;
		    if(pathArray[2] < numberOfPages){
		        fragment += '<a class="pagination-link blue-link" href="/objective/' + tempPlaceholder + '/' + pathArray[3] +  '/' + pathArray[4] +'"> > </a>';
		    }
	    }
	    paginationDiv.innerHTML = fragment;
	}
}());

/**
 *  This function calls the specific URLs for sorting the list of objectives.
 *  @author Benjamin J Cargile
 *  @version 1.0 -- 8/27/2015
 */

function objectiveListSort(type){
    
    var pathArray = window.location.pathname.split( '/' );
    var objectiveListSortValue = window.location.search;;
    if(objectiveListSortValue){
        if(type == 'ObjectiveId'){
            if(pathArray[4] == 'descendingObjectiveId'){
                window.open('/objective/1/' + pathArray[3] + '/ascendingObjectiveId?' + objectiveListSortValue, "_self");
            }
            else {
                window.open('/objective/1/' + pathArray[3] + '/descendingObjectiveId?' + objectiveListSortValue, "_self");
            }
        } else if(type == 'Name'){
            if(pathArray[4] == 'descendingName'){
                window.open('/objective/1/' + pathArray[3] + '/ascendingName?' + objectiveListSortValue, "_self");
            }
            else {
                window.open('/objective/1/' + pathArray[3] + '/descendingName?' + objectiveListSortValue, "_self");
            }
        } else if(type == 'ObjectiveLevel'){
            if(pathArray[4] == 'descendingObjectiveLevel'){
                window.open('/objective/1/' + pathArray[3] + '/ascendingObjectiveLevel?' + objectiveListSortValue, "_self");
            }
            else {
                window.open('/objective/1/' + pathArray[3] + '/descendingObjectiveLevel?' + objectiveListSortValue, "_self");
            }
        } else if(type == 'ObjectiveType'){
            if(pathArray[4] == 'descendingObjectiveType'){
                window.open('/objective/1/' + pathArray[3] + '/ascendingObjectiveType?' + objectiveListSortValue, "_self");
            }
            else {
                window.open('/objective/1/' + pathArray[3] + '/descendingObjectiveType?' + objectiveListSortValue, "_self");
            }
        }
    } else {
        if(type == 'ObjectiveId'){
            if(pathArray[4] == 'descendingObjectiveId'){
                window.open('/objective/1/' + pathArray[3] + '/ascendingObjectiveId' , "_self");
            }
            else {
                window.open('/objective/1/' + pathArray[3] + '/descendingObjectiveId' , "_self");
            }
        } else if(type == 'Name'){
            if(pathArray[4] == 'descendingName'){
                window.open('/objective/1/' + pathArray[3] + '/ascendingName' , "_self");
            }
            else {
                window.open('/objective/1/' + pathArray[3] + '/descendingName' , "_self");
            }
        } else if(type == 'ObjectiveLevel'){
            if(pathArray[4] == 'descendingObjectiveLevel'){
                window.open('/objective/1/' + pathArray[3] + '/ascendingObjectiveLevel' , "_self");
            }
            else {
                window.open('/objective/1/' + pathArray[3] + '/descendingObjectiveLevel' , "_self");
            }
        } else if(type == 'ObjectiveType'){
            if(pathArray[4] == 'descendingObjectiveType'){
                window.open('/objective/1/' + pathArray[3] + '/ascendingObjectiveType' , "_self");
            }
            else {
                window.open('/objective/1/' + pathArray[3] + '/descendingObjectiveType' , "_self");
            }
        }
    }
    
    
}
/**
 *  This function calls the specific URLs for querying the list of objective.
 *  @author Benjamin J Cargile
 *  @version 1.0 -- 8/27/2015
 */
function objectiveListSearch(){
    var SearchElement = document.getElementById('objectiveListSearchText');
    var SearchText = SearchElement.value;
    var url =  window.location.pathname + '?query=' + SearchText;
    window.open(url, "_self");
}

onload = function() {
    if (!document.getElementsByTagName || !document.createTextNode) return;
    var rows = document.getElementById('objectiveTable').getElementsByTagName('tbody')[0].getElementsByTagName('tr');
    for (i = 0; i < rows.length; i++) {
        rows[i].onclick = function() {
            $('#objectiveViewButton').prop("disabled", false);
            $('#objectiveUpdateButton').prop("disabled", false);
            $('#objectiveDeleteButton').prop("disabled", false);
            $('#objectivePrintButton').prop("disabled", false);
            var row = $(this);
            var rows2 = $('tr').not(':first');
            rows2.removeClass('highlight');
			row.addClass('highlight');
            var index = this.rowIndex - 1;
            
            var pathArray = window.location.pathname.split( '/' );
            var actualIndex = (pathArray[2] - 1) * pathArray[3] + index;
            //alert(actualIndex);
            var objectiveListSortValue = window.location.search;
            if(objectiveListSortValue[1]){
                var obj = {
                    "val": String(index),
                    "sort": pathArray[4],
                    "query": objectiveListSortValue
                    }
            } else {
                var obj = {
                    "val":String(index),
                    "sort":objectiveListSortValue[0],
                    "query":"UNDEFINED"
                    }
            
            }
            $.ajax({
				url: "/objective/selected",
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
 * This function confirms the deletion of an objective
 * and then perform a ajax call to cause the deletion.
 * @author Benjamin J Cargile (someone else made this before me)
 * @version 1.0 -- 9/2/2015
 * 
 */
function deleteObjective(){
	if (!confirm('Are you sure want to delete this objective?')){
        return false;
    }
	$.ajax({
		url : "/objective/delete",
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
function goObjectiveFrontPage(){
	window.open("/objective/1/10/ascendingName", "_self");
}

/**
 * This function is for changing the number of items in the table. 
 * @author Benjamin J Cargile (someone else made this before me)
 * @version 1.0 -- 9/2/2015
 * 
 */
function changeViewObjective(numberOfItems){
	var pathArray = window.location.pathname.split( '/' );
    var objectiveListSortValue = window.location.search;
    if(objectiveListSortValue){
    	window.open('/objective/1/' + numberOfItems + '/' + pathArray[4] + objectiveListSortValue, "_self");
    } else{
    	window.open('/objective/1/' + numberOfItems + '/' + pathArray[4], "_self");
    }
 }
