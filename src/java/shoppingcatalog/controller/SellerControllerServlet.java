package shoppingcatalog.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import shoppingcatalog.dao.SellerDAO;
import shoppingcatalog.dto.ItemDTO;


public class SellerControllerServlet extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String itemId=request.getParameter("itemId");
        String itemType=request.getParameter("itemType");
        String itemName=request.getParameter("itemaName");
        String itemDesc=request.getParameter("itemDesc");
        String itemPrice=request.getParameter("itemPrice");
        String itemImage=request.getParameter("itemImage");
       RequestDispatcher rd=null;
       try
       {
        boolean itemIdFound=SellerDAO.searchItemId(itemId);
        boolean result=false;
        
        if(!itemIdFound)
        {
            ItemDTO item=new ItemDTO();
            item.setItemId(Integer.parseInt(itemId));
            item.setItemType(itemType);
            item.setItemName(itemName);
            item.setItemDesc(itemDesc);
            item.setItemPrice(Integer.parseInt(itemPrice));
            item.setItemImage(itemImage);
            result=SellerDAO.registerItem(item);
        }
        request.setAttribute("result",result);
        request.setAttribute("itemIdFound",itemIdFound);
        System.out.println("Result is:"+result);
        rd=request.getRequestDispatcher("sellerresponse.jsp");
       }
       catch(Exception e)
       {
           request.setAttribute("exception",e);
           rd=request.getRequestDispatcher("showexception.jsp");
       }
       finally
       {
           rd.forward(request, response);
       }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}