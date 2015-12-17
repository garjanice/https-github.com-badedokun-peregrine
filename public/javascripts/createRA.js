/**
 *  This function validates the CreateRA form, allowing only alphabets and numbers
 *  @author Barath Ravikumar
 *  @version 1.0 -- 10/13/2015
 */

$(document).ready(function(){
	var isCreateRA_valid = false;
	$('#vmsg').hide();	
    $('#raCreation input, #raCreation textarea').not([type="submit"]).attr('required','true');
	$('#raCreation input, #raCreation textarea').on('input', function() {
		var input=$(this);
		var allowed_chars = /^[a-zA-Z0-9 ]+$/;
		var is_valid = allowed_chars.test(input.val());
		if(is_valid){
			$('#vmsg').hide();
			$(this).css('color','black');  
			isCreateRA_valid = true;
			//
		}else{
			console.log("Invalid entry")
			$('#vmsg').show();
			$(this).css('color','red');        			
			$('html,body').animate({
				   scrollTop: $("#vmsg").offset().top
				});
			$($(this)).attr('value', ""); 
			isCreateRA_valid = false;
		}		        
	})	 
	
	$('#raCreation').on("submit", function(event){
		
		console.log("validate form");
		if(!isCreateRA_valid){
			$('html,body').animate({
				   scrollTop: $("#vmsg").offset().top
				});
			event.preventDefault();
		}
		
	});
	
	
})  