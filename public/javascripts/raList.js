

var pathArray = window.location.pathname.split( '/' );
var numberOfPages = rAFrontListSize / pathArray[3];
numberOfPages = Math.ceil(numberOfPages);

var paginationDiv = document.getElementById("paginationArray");
var start = pathArray[2] - 5;
var extra = 0;
if(start <= 0){
    start = 1;
    extra = 5 - pathArray[2]
}
var end = pathArray[2] + 5;
if(end > numberOfPages){
    end = numberOfPages
}
var tempPlaceholder = pathArray[2];
tempPlaceholder--;
var fragment = '';
if(pathArray[2] > 1){
    fragment += '<a class="pagination-link blue-link" href="/riskAssessment/' + tempPlaceholder + '/' + pathArray[3] + '"> < </a>';
}
for(var i=start; i <= end; i++){
    fragment += '<a class="pagination-link blue-link" href="/riskAssessment/' + i + '/' + pathArray[3] + '">' + i + '</a>';
}
tempPlaceholder = pathArray[2];
tempPlaceholder++;
if(pathArray[2] < numberOfPages){
    fragment += '<a class="pagination-link blue-link" href="/riskAssessment/' + tempPlaceholder + '/' + pathArray[3] + '"> > </a>';
}
paginationDiv.innerHTML = fragment;

