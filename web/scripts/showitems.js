function getItemNames(itemType)
{
item=itemType;
var para=document.getElementById(item);
var span=para.getElementsByTagName("span")[0];
var spanText=span.innerHTML.trim();
if(spanText.indexOf("+")!==-1)
{
    span.innerHTML="-"+itemType;
}
else if(spanText.indexOf("-")!==-1)
{
 span.innerHTML="+"+itemType;
 $("#"+item+" .itemnames").hide("slow");
 
 return;
} 
var mydata={itemType:itemType};
var request=$.post("StoreControllerServlet",mydata,processresponse);
request.error(handleError);
}
function processresponse(responseText)
{
    var para=document.getElementById(item);
    var resp=responseText;
    resp=resp.trim();
    var olddiv=para.getElementsByClassName("itemnames")[0];
    if(typeof olddiv!=='undefined')
    {
        para.removeChild(olddiv);
    }
    var newdiv=document.createElement("div");
    newdiv.setAttribute("class","itemnames");
    newdiv.innerHTML=resp;
    para.appendChild(newdiv);
}
function handleError(xhr,textStatus)
{
    if(textStatus==='error')
        $("#regresult").html("Error is "+xhr.status);
    
}
