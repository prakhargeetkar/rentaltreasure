var username,password;
$(document).ready(function(){
    
        $("#registerbtn").click(function(){
            connect();
        });
    });

    function validate()
    {
        password=$("#password").val();
        username=$("#username").val();
       
        var ret=true;
        if(username==="")
        {
            $("#sp1").html("*Username Required").css("color","red").fadeIn(1);
            ret=false;
        }
        if(password==="")
        { 
            $("#sp2").html("*Password required").css("color","red").fadeIn(1);
            ret= false;
        }
        $("#sp1").fadeOut(4000);
        $("#sp2").fadeOut(4000);
        return ret;
    }
    function connect(){
        if(!validate())
        return;
        
        var data={username:username,password:password};
    var request=$.post("RegistrationControllerServlet",data,processresponse);
    request.error(handleError);
}
function processresponse(responseText){
    var resp=responseText;
    resp=resp.trim();
    if(resp=="uap")
    {
           $("#regresult").html("User already present with this username! Try again..!").css("color","red");
           
    }
    else if(resp=="success")
    {
        $("#regresult").fadeIn();
        $("#regresult").html("Registration successful.. Redirecting to the home page..!!").css("color","green");
       setTimeout(home,3000);
    }
     else if(resp=="failure")
     {
      $("#regresult").html("Registration failed..Try again later..").css("color","red");
   }
   else
   {
       $("#regresult").html("Some problem at the server....").css("color","red");
   }
    $("#regresult").fadeOut(5000);
}
function handleError(xhr,textStatus)
{
    if(textStatus==='error')
        $("#regresult").html("Error is "+xhr.status);
    
}

function home()
{
    window.location="home.html";
}
    


