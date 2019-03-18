<%
    boolean itemIdFound=(Boolean)request.getAttribute("itemIdFound");
    boolean result=(Boolean)request.getAttribute("result");
    if(itemIdFound==true)
    {
        out.println("iap");
    }
    else if(result==false)
    {
        out.println("failure");
    }
    else
    {
        out.println("success");
    }
  
%>  