$(document).ready(function(){
    
    
    function getTitles(){
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
        $('#state').find('option').remove();
        $('#state').append('<option>select country</option>');
        
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
    
});