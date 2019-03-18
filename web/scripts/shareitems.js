var itemId,itemType,itemName,itemDesc,itemPrice,itemImage;
$(document).ready(function(){
    
        $("#registerbtn").click(function(){
            connect();
        });
    });

    function validate()
    {
        itemId=$("#itemId").val();
        itemType=$("#itemType").val();
        itemName=$("#itemName").val();
        itemDesc=$("#itemDesc").val();
        itemPrice=$("#itemPrice").val();
        itemImage=$("#itemImage").val();
        var ret=true;
        if(itemId==="")
        {
            $("#sp1").html("*Item Id Required").css("color","red").fadeIn(1);
            ret=false;
        }
        if(itemType==="")
        { 
            $("#sp2").html("*Item Type required").css("color","red").fadeIn(1);
            ret= false;
        }
        if(itemName==="")
        { 
            $("#sp3").html("*Item Name required").css("color","red").fadeIn(1);
            ret= false;
        }
        if(itemDesc==="")
        { 
            $("#sp4").html("*Item Description required").css("color","red").fadeIn(1);
            ret= false;
        }
        if(itemPrice==="")
        { 
            $("#sp5").html("*Item Price required").css("color","red").fadeIn(1);
            ret= false;
        }
        if(itemImage==="")
        { 
            $("#sp6").html("*Item Image required").css("color","red").fadeIn(1);
            ret= false;
        }
        $("#sp1").fadeOut(4000);
        $("#sp2").fadeOut(4000);
        $("#sp3").fadeOut(4000);
        $("#sp4").fadeOut(4000);
        $("#sp5").fadeOut(4000);
        $("#sp6").fadeOut(4000);
        return ret;
    }
    function connect(){
        if(!validate())
        return;
        
        var data={itemId:itemId,itemType:itemType,itemName:itemName,itemDesc:itemDesc,itemPrice:itemPrice,itemImage:itemImage};
    var request=$.post("SharerControllerServlet",data,processresponse);
    request.error(handleError);
}
function processresponse(responseText){
    var resp=responseText;
    resp=resp.trim();
    if(resp=="iap")
    {
           $("#regresult").html("Item already present with this username! Try again..!").css("color","red");
           
    }
    else if(resp=="success")
    {
        $("#regresult").html("Item Added successful.. Redirecting to the home page..!!").css("color","green");
       setTimeout(home,3000);
    }
     else if(resp=="failure")
     {
      $("#regresult").html("Item Cannot be Added..Try again later..").css("color","red");
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
    


