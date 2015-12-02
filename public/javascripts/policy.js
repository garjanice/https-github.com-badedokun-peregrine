function HandleBrowseClick() {
	document.getElementById("browse").click();
}
		
function HandleChange() {
	var fileinput = document.getElementById("browse");
	var textinput = document.getElementById("filename");
	textinput.value = fileinput.value;  
	fileinput.addEventListener("change", function(event){
		event.stopPropagation();    
   		var reader = new FileReader();        
   		reader.onload = function(event){
   			event.stopPropagation();
       		var contents = event.target.result;
       		document.getElementById('policybody').value = contents;            
   		};        
   		reader.onerror = function(event){
   			console.error("File could not be read! Code " + event.target.error.code);
   		};        
   		console.log("Filename: " + fileinput.files[0].name);
   		reader.readAsText(fileinput.files[0]);        
	}, false);
}