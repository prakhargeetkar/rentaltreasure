<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="shoppingcatalog.dto.OrderDTO"%>
<%@page import="shoppingcatalog.dao.StoreDAO"%>
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
         StringBuffer displayBlock=new StringBuffer("<h1>My Store-My Orders</h1>");
         displayBlock.append("<div style='float:left;'>");
         ArrayList<OrderDTO> orderList=StoreDAO.getOrderByCustomer(username);
         if(orderList.size()==0)
                 {
                  displayBlock.append("<p><strong>You have not placed any orders yet</strong></p>");
                 }
         else
         {
             SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
             displayBlock.append("<table border='1'>");
             displayBlock.append("<tr><th>OrderId</th><th>Order Amount</th><th>Order Date</th></th>");
             
             for(OrderDTO o:orderList)
             {
                 String dateStr=sdf.format(o.getOrderDate());
                 displayBlock.append("<tr><td>"+o.getOrderId()+"</td><td>"+o.getOrderAmount()+"</td><td>"+dateStr+"</td></tr>");
            
             }
             
         }
         displayBlock.append("</table>");
          displayBlock.append("<p><a href='StoreControllerServlet'>Continue Shopping</a>&nbsp;&nbsp;&nbsp;&nbsp;");
         displayBlock.append("<h4 id='logout'><a href='LoginControllerServlet?logout=abc'>Logout</a></h4>");
         out.println(displayBlock);
        }
        %>
    </body>
</html>