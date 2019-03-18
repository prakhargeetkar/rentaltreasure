var username,password,usertype;
var url;
$(document).ready(function(){
     $("#username").keypress(function(){
            cleartext();
         });
        
        $("#password").keypress(function(){
             cleartext();
        });
        $("#loginbtn").click(function(){
            connect();
        });
    });
function cleartext()
{
    $("#sp1").text("");
    $("#sp2").text("");
    $("#loginresult").text("");
}
    function validate(){
        cleartext();
        username=$("#username").val();
        password=$("#password").val();
        usertype=$("#usertype").val();
    
        var ret=true;
        if(username==="")
        { $("#sp1").text("*User Name required").css("color","red");
        ret= false;
        }
        if(password==="")
        { $("#sp2").text("*Password required").css("color","red");
        ret= false;
        }
        
            return ret;
    }
    function connect(){
        if(validate()===false)
            return;
    
    var data={username:username,password:password,usertype:usertype};
    var request=$.post("LoginControllerServlet",data,processresponse);
    request.error(handleError);
}
function processresponse(responseText){
    var resp=responseText;
    var pagename;
    resp=resp.trim();
    if(resp.indexOf("jsessionid")!==-1)
    {
           $("#loginresult").css("color","green");
    if(usertype==="ADMIN")
        pagename="Options";
    
    else
        pagename="Store";
     
     $("#loginresult").html("Login accepted.. Redirecting to the "+pagename+" page..!!");
       url=resp;
       setTimeout(redirectuser,3000);
       
    }
}
function handleError(xhr,textStatus)
{
    if(textStatus==='error')
        $("#loginresult").html("Error is "+xhr.status);
    
}
function redirectuser()
{
    window.location=url;
}


