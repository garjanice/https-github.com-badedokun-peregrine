$(document).ready(function(){
    
    
    function getTitles(){
        $('#title').find('option').remove();
        $('#title').append('<option></option>');
        
        var xhttp = new XMLHttpRequest();
        xhttp.open("GET","getTitles");
        xhttp.send();
        
        
        xhttp.onreadystatechange = function(){
            if (xhttp.readyState == 4 && xhttp.status == 200) {
                    $('#title').append(xhttp.responseText);
            }
            
        }
        
    }
    
    
    function getCountries(){
        $('#country').find('option').remove();
        $('#country').append('<option></option>');
        
        var xhttp = new XMLHttpRequest();
        xhttp.open("GET","getCountries");
        xhttp.send();
        
        
        xhttp.onreadystatechange = function(){
            if (xhttp.readyState == 4 && xhttp.status == 200) {
                    $('#country').append(xhttp.responseText);
            }
            
        }
        
    }
    
    

    function getLanguages(){
        
        $('#language').find('option').remove();
        $('#language').append('<option></option>');
    
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET","getLanguages");
    xhttp.send();


        xhttp.onreadystatechange = function(){
            if (xhttp.readyState == 4 && xhttp.status == 200) {
                    $('#language').append(xhttp.responseText);
            }

        }
    
        
    }
    
    
    function getTimeZones(){
        
                $('#tzone').find('option').remove();
        $('#tzone').append('<option></option>');
        
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET","getTimezones");
    xhttp.send();


        xhttp.onreadystatechange = function(){
            if (xhttp.readyState == 4 && xhttp.status == 200) {
                    $('#tzone').append(xhttp.responseText);
            }

        }
    
        
    }    

    
    $('#country').change(function(){
        
            function getStates(){
                
                $('#state').find('option').remove();
                console.log($('#country').val());
                var xhttp = new XMLHttpRequest();
                
                if( $('#country').val().toLowerCase() === 'United States'.toLowerCase()){
                    $('#state').replaceWith('<select  id="state" name="state" class="select-style userProfileCreation-state-text" ></select>');
                    xhttp.open("GET","getStates/US");
                }
                else if( $('#country').val().toLowerCase() === 'Canada'.toLowerCase()){
                    $('#state').replaceWith('<select  id="state" name="state" class="select-style userProfileCreation-state-text" ></select>');
                    xhttp.open("GET","getStates/CA");
                    //xhttp.open("GET","getStates/CA");
                }else{
                    $('#state').replaceWith('<input  id="state" name="state" class="userProfileCreation-state-text" ></input>');
                }
                
                
                xhttp.send();


                xhttp.onreadystatechange = function(){
                    if (xhttp.readyState == 4 && xhttp.status == 200) {
                            $('#state').append(xhttp.responseText);
                    }

                }

            }
        
        getStates();
    });
    
    /*Temporary*/
    $('#city').click(function(){
        $('#city').replaceWith('<input  id="city" name="city" class="userProfileCreation-city-text" ></input>');
        $('#city').focus();
    })
    
    /*Temporary*/
    $('#locale').click(function(){
        $('#locale').replaceWith('<input  id="locale" name="locale" class="userProfileCreation-locale-text" ></input>');
        $('#locale').focus();
    })
    
    getTitles();
    getCountries();
    getLanguages();
    getTimeZones();
    
    
    $('#userProfileCreation').on("submit", function(event){
       
        event.preventDefault();
        
        
        function getCoordinates(){
            
            var state = $('#state option:selected').text();
            if(state === '')
            {
                state = $('#state').val() ;
            }
            
           var address =  $('#address1').val()+' '+$('#address2').val() + ' '+ $('#city').val() + ' '+ state + ' ' +
               $('#country option:selected').text();
            
            address = address.split(' ').join('+');
            
            var geoCodeQuery = 'http://maps.google.com/maps/api/geocode/json?address='+address+'&sensor=false'
                
            var xhttp = new XMLHttpRequest();
            xhttp.open("GET",geoCodeQuery);
            xhttp.send();
            
            xhttp.onreadystatechange = function(){
            
                if(xhttp.readyState == 4 && xhttp.status == 200){
                        console.log(xhttp.responseText);
                        var response = $.parseJSON(xhttp.responseText);
                        var latitude = response.results[0].geometry.location.lat;
                        var longitude  = response.results[0].geometry.location.lng;
                    
                    
                        var latitudeInput = $("<input>")
                                       .attr("type", "hidden")
                                       .attr("name", "latitude").val(latitude);
                    
                        var longitudeInput = $("<input>")
                                       .attr("type", "hidden")
                                       .attr("name", "latitude").val(longitude);
                    
                        console.log(latitude);
						console.log(latitudeInput);
						$('#userProfileCreation').append('<input type="hidden" name="latitude" value='+latitude.toString()+' />');
                        $('#userProfileCreation').append('<input type="hidden" name="longitude" value='+longitude.toString()+' />');
                    

                    //    $('#userProfileCreation').append(latitudeInput);
                    //    $('#userProfileCreation').append(longitudeInput);

                   }
                    if( $("#pword").val() === $("#cpword").val() )
                    {
                        $('#userProfileCreation').submit();
                        $('#userProfileCreation').off("submit");
                    }else{
                        alert("Password and Confirm Password must be the same")
                    }
            }
            
            
        }

        getCoordinates();
        
		//$('#userProfileCreation').submit();
//        console.log("validate form");
//        if(!isCreateRA_valid){
//            $('html,body').animate({
//                   scrollTop: $("#vmsg").offset().top
//                });
//            event.preventDefault();
//        }

    });
    
    


    
});