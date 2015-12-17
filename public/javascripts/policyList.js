/**
 *  This function sets up the pagination.
 *  @author Benjamin J Cargile
 *  @version 1.0 -- 8/27/2015
 */


(function() {
	if(typeof policyListSize != "undefined"){
		var pathArray = window.location.pathname.split( '/' );
	    var policyListSortValue = window.location.search;
	    var numberOfPages = policyListSize / pathArray[3];
	    numberOfPages = Math.ceil(numberOfPages);
	
	    var paginationDiv = document.getElementById("policyPaginationArray");
	    var start = pathArray[2] - 5;
	    var extra = 0;
	    if(start <= 0){
	        start = 1;
	        extra = 5 - pathArray[2];
	    }
	    var end = Number(pathArray[2]) + 5;
	    
	    if(end > numberOfPages){
	        end = numberOfPages;
	    }
	    
	    var tempPlaceholder = Number(pathArray[2]);
	    tempPlaceholder--;
	    var fragment = '';
	    if(policyListSortValue){
	    	if(pathArray[2] > 1){
	    		fragment += '<a class="pagination-link blue-link" href="/policy/' + '1' + '/' + pathArray[3] +  '/' + pathArray[4] + policyListSortValue + '"> << </a>';
		        fragment += '<a class="pagination-link blue-link" href="/policy/' + tempPlaceholder + '/' + pathArray[3] +  '/' + pathArray[4] + policyListSortValue + '"> < </a>';
		    }
		    for(var i=start; i <= end; i++){
		    	if(i == (tempPlaceholder + 1))
		    		fragment +=  i ;
		    	else 
		    		fragment += '<a class="pagination-link blue-link" href="/policy/' + i + '/' + pathArray[3] + '/' + pathArray[4] +policyListSortValue + '">' + i + '</a>';
		    }
		    tempPlaceholder = pathArray[2];
		    tempPlaceholder++;
		    if(pathArray[2] < numberOfPages){
		        fragment += '<a class="pagination-link blue-link" href="/policy/' + tempPlaceholder + '/' + pathArray[3] + policyListSortValue +  '/' + pathArray[4] +'"> > </a>';
		        fragment += '<a class="pagination-link blue-link" href="/policy/' + numberOfPages + '/' + pathArray[3] + policyListSortValue +  '/' + pathArray[4] +'"> >> </a>';
		    }
	    } else {
		    if(pathArray[2] > 1){
		    	fragment += '<a class="pagination-link blue-link" href="/policy/' + '1' + '/' + pathArray[3] +  '/' + pathArray[4] +'"> << </a>';
		        fragment += '<a class="pagination-link blue-link" href="/policy/' + tempPlaceholder + '/' + pathArray[3] +  '/' + pathArray[4] +'"> < </a>';
		    }
		    for(var i=start; i <= end; i++){
		    	if(i == (tempPlaceholder + 1))
		    		fragment +=  '<span class="pagination-link">' + i + '</span>' ;
		    	else 
		    		fragment += '<a class="pagination-link blue-link" href="/policy/' + i + '/' + pathArray[3] + '/' + pathArray[4] +'">' + i + '</a>';
		    }
		    tempPlaceholder = pathArray[2];
		    tempPlaceholder++;
		    if(pathArray[2] < numberOfPages){
		        fragment += '<a class="pagination-link blue-link" href="/policy/' + tempPlaceholder + '/' + pathArray[3] +  '/' + pathArray[4] +'"> > </a>';
		        fragment += '<a class="pagination-link blue-link" href="/policy/' + numberOfPages + '/' + pathArray[3] +  '/' + pathArray[4] +'"> >> </a>';
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

function policyListSort(type){
    
    var pathArray = window.location.pathname.split( '/' );
    var policyListSortValue = window.location.search;;
    if(policyListSortValue){
        if(type == 'policyId'){
            if(pathArray[4] == 'descendingPolicyId'){
                window.open('/policy/1/' + pathArray[3] + '/ascendingPolicyId?' + policyListSortValue, "_self");
            }
            else {
                window.open('/policy/1/' + pathArray[3] + '/descendingPolicyId?' + policyListSortValue, "_self");
            }
        } else if(type == 'name'){
            if(pathArray[4] == 'descendingName'){
                window.open('/policy/1/' + pathArray[3] + '/ascendingName?' + policyListSortValue, "_self");
            }
            else {
                window.open('/policy/1/' + pathArray[3] + '/descendingName?' + policyListSortValue, "_self");
            }
        } else if(type == 'description'){
            if(pathArray[4] == 'descendingDescription'){
                window.open('/policy/1/' + pathArray[3] + '/ascendingDescription?' + policyListSortValue, "_self");
            }
            else {
                window.open('/policy/1/' + pathArray[3] + '/descendingDescription?' + policyListSortValue, "_self");
            }
        } else if(type == 'version'){
            if(pathArray[4] == 'descendingVersion'){
                window.open('/policy/1/' + pathArray[3] + '/ascendingVersion?' + policyListSortValue, "_self");
            }
            else {
                window.open('/policy/1/' + pathArray[3] + '/descendingVersion?' + policyListSortValue, "_self");
            }
        } else if(type == 'classification'){
            if(pathArray[4] == 'descendingClassification'){
                window.open('/policy/1/' + pathArray[3] + '/ascendingClassification?' + policyListSortValue, "_self");
            }
            else {
                window.open('/policy/1/' + pathArray[3] + '/descendingClassification?' + policyListSortValue, "_self");
            }
        }
    } else {
    	if(type == 'policyId'){
            if(pathArray[4] == 'descendingPolicyId'){
                window.open('/policy/1/' + pathArray[3] + '/ascendingPolicyId', "_self");
            }
            else {
                window.open('/policy/1/' + pathArray[3] + '/descendingPolicyId' , "_self");
            }
        } else if(type == 'name'){
            if(pathArray[4] == 'descendingName'){
                window.open('/policy/1/' + pathArray[3] + '/ascendingName' , "_self");
            }
            else {
                window.open('/policy/1/' + pathArray[3] + '/descendingName', "_self");
            }
        } else if(type == 'description'){
            if(pathArray[4] == 'descendingDescription'){
                window.open('/policy/1/' + pathArray[3] + '/ascendingDescription' , "_self");
            }
            else {
                window.open('/policy/1/' + pathArray[3] + '/descendingDescription' , "_self");
            }
        } else if(type == 'version'){
            if(pathArray[4] == 'descendingVersion'){
                window.open('/policy/1/' + pathArray[3] + '/ascendingVersion', "_self");
            }
            else {
                window.open('/policy/1/' + pathArray[3] + '/descendingVersion' , "_self");
            }
        } else if(type == 'classification'){
            if(pathArray[4] == 'descendingClassification'){
                window.open('/policy/1/' + pathArray[3] + '/ascendingClassification' , "_self");
            }
            else {
                window.open('/policy/1/' + pathArray[3] + '/descendingClassification' , "_self");
            }
        }
    }
    
    
}
/**
 *  This function calls the specific URLs for querying the list of Risk Assessments.
 *  @author Benjamin J Cargile
 *  @version 1.0 -- 8/27/2015
 */
function policyListSearch(){
    var SearchElement = document.getElementById('policyListSearchText');
    var SearchText = SearchElement.value;
    var url =  window.location.pathname + '?query=' + SearchText;
    window.open(url, "_self");
}





onload = function() {
	
    if (!document.getElementsByTagName || !document.createTextNode) return;
    var rows = document.getElementById('policyTable').getElementsByTagName('tbody')[0].getElementsByTagName('tr');
    for (i = 0; i < rows.length; i++) {
        rows[i].onclick = function() {
            $('#policyViewButton').prop("disabled", false);
            //$('#raUpdateButton').prop("disabled", false);
            //$('#raDeleteButton').prop("disabled", false);
            //$('#raPrintButton').prop("disabled", false);
            var row = $(this);
            var rows2 = $('tr').not(':first');
            rows2.removeClass('highlight');
			row.addClass('highlight');
            var index = this.rowIndex - 1;
            
            
            var id = row.attr('id');
            currentPolicyUUID = id;
            /*if(policyListSortValue[1]){
                var obj = {
                    "val": String(index),
                    "sort": pathArray[4],
                    "query": policyListSortValue
                    }
            } else {
                var obj = {
                    "val":String(index),
                    "sort":raListSortValue[0],
                    "query":"UNDEFINED"
                    }
            
            }
            
           /* $.ajax({
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
            })*/
            
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
 * This function is for changing the number of items in the table. 
 * @author Benjamin J Cargile (someone else made this before me)
 * @version 1.0 -- 9/2/2015
 * 
 */
function changeViewPolicy(numberOfItems){
	var pathArray = window.location.pathname.split( '/' );
    var policyListSortValue = window.location.search;
    if(policyListSortValue){
    	window.open('/policy/1/' + numberOfItems + '/' + pathArray[4] + policyListSortValue, "_self");
    } else{
    	window.open('/policy/1/' + numberOfItems + '/' + pathArray[4], "_self");
    }
}

function goToPolicyView(){
	window.open("/policy/view/" + currentPolicyUUID, "_self");
	
}
function goToPolicyUpdate(){
	window.open("/policy/update/" + currentPolicyUUID, "_self");
	
}
function goToPolicyCreate(){
	window.open("/policy/create", "_self");
	
}