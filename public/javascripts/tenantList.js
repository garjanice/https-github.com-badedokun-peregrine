/**
 *  This function sets up the pagination.
 *  @author Benjamin J Cargile
 *  @version 1.0 -- 8/27/2015
 */


(function() {
	if(typeof tenantListSize !== "undefined"){
		var pathArray = window.location.pathname.split( '/' );
	    var listSortValue = window.location.search;
	    var numberOfPages = tenantListSize / pathArray[3];
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
	        end = numberOfPages;
	    }
	    
	    var tempPlaceholder = Number(pathArray[2]);
	    tempPlaceholder--;
	    var fragment = '';
	    if(listSortValue){
	    	if(pathArray[2] > 1){
	    		fragment += '<a class="pagination-link blue-link" href="/tenant/' + '1' + '/' + pathArray[3] +  '/' + pathArray[4]  + listSortValue + '"> << </a>';
		        fragment += '<a class="pagination-link blue-link" href="/tenant/' + tempPlaceholder + '/' + pathArray[3] +  '/' + pathArray[4]  + listSortValue + '"> < </a>';
		    }
		    for(var i=start; i <= end; i++){
		    	if(i == (tempPlaceholder + 1))
		    		fragment +=  i ;
		    	else 
		    		fragment += '<a class="pagination-link blue-link" href="/tenant/' + i + '/' + pathArray[3] + '/' + pathArray[4]  + listSortValue + '">' + i + '</a>';
		    }
		    tempPlaceholder = pathArray[2];
		    tempPlaceholder++;
		    if(pathArray[2] < numberOfPages){
		        fragment += '<a class="pagination-link blue-link" href="/tenant/' + tempPlaceholder + '/' + pathArray[3] +  '/' + pathArray[4] + listSortValue +'"> > </a>';
		        fragment += '<a class="pagination-link blue-link" href="/tenant/' + numberOfPages + '/' + pathArray[3] +  '/' + pathArray[4] + listSortValue +'"> >> </a>';
		    }
	    } else {
		    if(pathArray[2] > 1){
		    	fragment += '<a class="pagination-link blue-link" href="/tenant/' + '1' + '/' + pathArray[3] +  '/' + pathArray[4] +'"> << </a>';
		        fragment += '<a class="pagination-link blue-link" href="/tenant/' + tempPlaceholder + '/' + pathArray[3] +  '/' + pathArray[4] +'"> < </a>';
		    }
		    for(var i=start; i <= end; i++){
		    	if(i == (tempPlaceholder + 1))
		    		fragment +=  '<span class="pagination-link">' + i + '</span>' ;
		    	else 
		    		fragment += '<a class="pagination-link blue-link" href="/tenant/' + i + '/' + pathArray[3] + '/' + pathArray[4] +'">' + i + '</a>';
		    }
		    tempPlaceholder = pathArray[2];
		    tempPlaceholder++;
		    if(pathArray[2] < numberOfPages){
		        fragment += '<a class="pagination-link blue-link" href="/tenant/' + tempPlaceholder + '/' + pathArray[3] +  '/' + pathArray[4] +'"> > </a>';
		        fragment += '<a class="pagination-link blue-link" href="/tenant/' + numberOfPages + '/' + pathArray[3] +  '/' + pathArray[4] +'"> >> </a>';
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

function tenantListSort(type){
    
    var pathArray = window.location.pathname.split( '/' );
    var listSortValue = window.location.search;;
    if(listSortValue){
        if(type == 'tenantId'){
            if(pathArray[4] == 'descendingTenantId'){
                window.open('/tenant/1/' + pathArray[3] + '/ascendingTenantId' + listSortValue, "_self");
            }
            else {
                window.open('/tenant/1/' + pathArray[3] + '/descendingTenantId' + listSortValue, "_self");
            }
        } else if(type == 'name'){
            if(pathArray[4] == 'descendingName'){
                window.open('/tenant/1/' + pathArray[3] + '/ascendingName' + listSortValue, "_self");
            }
            else {
                window.open('/tenant/1/' + pathArray[3] + '/descendingName' + listSortValue, "_self");
            }
        } else if(type == 'createdDate'){
            if(pathArray[4] == 'descendingCreatedDate'){
                window.open('/tenant/1/' + pathArray[3] + '/ascendingCreatedDate' + listSortValue, "_self");
            }
            else {
                window.open('/tenant/1/' + pathArray[3] + '/descendingCreatedDate' + listSortValue, "_self");
            }
        } else if(type == 'serviceStartDate'){
            if(pathArray[4] == 'descendingServiceStartDate'){
                window.open('/tenant/1/' + pathArray[3] + '/ascendingServiceStartDate' + listSortValue, "_self");
            }
            else {
                window.open('/tenant/1/' + pathArray[3] + '/descendingServiceStartDate' + listSortValue, "_self");
            }
        } else if(type == 'status'){
            if(pathArray[4] == 'descendingStatus'){
                window.open('/tenant/1/' + pathArray[3] + '/ascendingStatus' + listSortValue, "_self");
            }
            else {
                window.open('/tenant/1/' + pathArray[3] + '/descendingStatus' + listSortValue, "_self");
            }
        }
    } else {
    	if(type == 'tenantId'){
            if(pathArray[4] == 'descendingTenantId'){
                window.open('/tenant/1/' + pathArray[3] + '/ascendingTenantId', "_self");
            }
            else {
                window.open('/tenant/1/' + pathArray[3] + '/descendingTenantId' , "_self");
            }
        } else if(type == 'name'){
            if(pathArray[4] == 'descendingName'){
                window.open('/tenant/1/' + pathArray[3] + '/ascendingName' , "_self");
            }
            else {
                window.open('/tenant/1/' + pathArray[3] + '/descendingName', "_self");
            }
        } else if(type == 'createdDate'){
            if(pathArray[4] == 'descendingCreatedDate'){
                window.open('/tenant/1/' + pathArray[3] + '/ascendingCreatedDate' , "_self");
            }
            else {
                window.open('/tenant/1/' + pathArray[3] + '/descendingCreatedDate' , "_self");
            }
        } else if(type == 'serviceStartDate'){
            if(pathArray[4] == 'descendingServiceStartDate'){
                window.open('/tenant/1/' + pathArray[3] + '/ascendingServiceStartDate', "_self");
            }
            else {
                window.open('/tenant/1/' + pathArray[3] + '/descendingServiceStartDate' , "_self");
            }
        } else if(type == 'status'){
            if(pathArray[4] == 'descendingStatus'){
                window.open('/tenant/1/' + pathArray[3] + '/ascendingStatus' , "_self");
            }
            else {
                window.open('/tenant/1/' + pathArray[3] + '/descendingStatus' , "_self");
            }
        }
    }
    
    
}
/**
 *  This function calls the specific URLs for querying the list of Risk Assessments.
 *  @author Benjamin J Cargile
 *  @version 1.0 -- 8/27/2015
 */
function tenantListSearch(){
    var SearchElement = document.getElementById('tenantListSearchText');
    var SearchText = SearchElement.value;
    var url =  window.location.pathname + '?query=' + SearchText;
    window.open(url, "_self");
}





onload = function() {
	
    if (!document.getElementsByTagName || !document.createTextNode) return;
    var rows = document.getElementById('tenantTable').getElementsByTagName('tbody')[0].getElementsByTagName('tr');
    for (i = 0; i < rows.length; i++) {
        rows[i].onclick = function() {
            //$('#policyViewButton').prop("disabled", false);
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
          
            var obj = {
                "val": String(index),
                }
            
            
            
            
            $.ajax({
				url: "/tenant/selected",
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

function deleteTenant(){
	if (!confirm('Are you sure want to delete this Tenant?')){
        return false;
    }
	$.ajax({
		url : "/tenant/delete",
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
function changeViewTenant(numberOfItems){
	var pathArray = window.location.pathname.split( '/' );
    var listSortValue = window.location.search;
    if(listSortValue){
    	window.open('/tenant/1/' + numberOfItems + '/' + pathArray[4] + listSortValue, "_self");
    } else{
    	window.open('/tenant/1/' + numberOfItems + '/' + pathArray[4], "_self");
    }
}

function goToTenantList(){
	window.open('/tenant/1/10/descandingName', "_self");
}
function goToTenantCreate(){
	window.open('/tenant/create', "_self");
}
function goToTenantView(){
	window.open('/tenant/view', "_self");
}
function goToTenantUpdate(){
	window.open('/tenant/update', "_self");
}