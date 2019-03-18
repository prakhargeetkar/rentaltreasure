<%@page import="shoppingcatalog.dto.ItemDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="styles/stylesheet.css">
        <script type="text/javascript" src="scripts/jquery.js"></script>
        <script type="text/javascript" src="scripts/showitems.js"></script>
        <title>Store items</title>
    </head>
    <body>
        <%@include file="logo.html" %>
        <%
        String username=(String)session.getAttribute("username");
        if(username==null)
        {
            session.invalidate();
            response.sendRedirect("accessdenied.html");
        }
        else
        {   
            ItemDTO item=(ItemDTO)request.getAttribute("item");
             StringBuffer displayBlock=new StringBuffer("<h1>My Store-Item Details</h1><p><em>You are viewing:</em><br>");
             displayBlock.append("<strong><a href='StoreControllerServlet'>"+item.getItemType()+"&gt;</a>"+item.getItemName()+"</strong></p>");
             displayBlock.append("<div style='float:left;'>");
             displayBlock.append("<img src=\'images/"+item.getItemImage()+"'></div>");
             displayBlock.append("<div style='float:left;padding-left: 12px'>");
             displayBlock.append("<p><strong>Description</strong><br>"+item.getItemDesc()+"</p>");
             displayBlock.append("<p><strong>Price:</strong>Rs."+item.getItemPrice()+"</p>");
             displayBlock.append("<p><a href='addtocart.jsp?itemId="+item.getItemId()+"'>Add to Take it On Rent</a></p></div>");
             displayBlock.append("<h4 id='logout'><a href='myorders.jsp'>My Orders</a>&nbsp;&nbsp;&nbsp;<a href='LoginControllerServlet?logout=logout'>Logout</a></h4>");    
             out.println(displayBlock);
        }
        %>
    </body>
</html>
