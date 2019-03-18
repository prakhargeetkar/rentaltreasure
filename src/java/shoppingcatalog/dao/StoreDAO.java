package shoppingcatalog.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import shoppingcatalog.dbutil.DBConnection;
import shoppingcatalog.dto.ItemDTO;
import shoppingcatalog.dto.ItemInfoDTO;
import shoppingcatalog.dto.OrderDTO;

public class StoreDAO 
{
    private static Statement st;
   private static PreparedStatement ps1,ps2,ps3,ps4,ps5,ps6;
    static
    {
        try
        {
         st=DBConnection.getConnection().createStatement();  
         ps1=DBConnection.getConnection().prepareStatement("select id,item_name from store_items where item_type=?");
         ps2=DBConnection.getConnection().prepareStatement("select item_type,item_name,item_price,item_desc,item_image from store_items where id=?");
         ps3=DBConnection.getConnection().prepareStatement("insert into order_master values(?,?,?,?)");
         ps4=DBConnection.getConnection().prepareStatement("insert into order_details values(?,?,?)");
         ps5=DBConnection.getConnection().prepareStatement("select count(*) as count from order_master");
         ps6=DBConnection.getConnection().prepareStatement("select order_id,order_amount,order_date from order_master where cust_name=?");
        }
       catch(Exception e)
       {
           System.out.println("Exception is.");
           e.printStackTrace();
       }
    }
    
    public static List<String> getItemTypes() throws SQLException
    {
        ArrayList<String> itemList=new ArrayList<String>();
        ResultSet rs=st.executeQuery("select distinct item_type from store_items");
        System.out.println("command executed successfully");
        while(rs.next())
        {
            itemList.add(rs.getString(1));
        }
        System.out.println("List is of "+itemList.size()+" items");
        return itemList;
    }

  public static List<ItemInfoDTO> getItemsByType(String itemType) throws SQLException
  {
      ArrayList<ItemInfoDTO> itemList=new ArrayList<ItemInfoDTO>();
      ps1.setString(1,itemType);
      System.out.println("Item type in Model is "+itemType);
      ResultSet rs=ps1.executeQuery();
      System.out.println("again Item type in Model is "+itemType);
      
      while(rs.next())
      {
       ItemInfoDTO obj=new ItemInfoDTO();
       obj.setItemId(rs.getInt(1));
       obj.setItemName(rs.getString(2));
          System.out.println("item is "+obj.getItemId()+","+obj.getItemName());
       itemList.add(obj);
      }
      System.out.println("List is of "+itemList.size()+" items");
     return itemList;
   }

  public static ItemDTO getItemDetails(int itemId) throws SQLException
  {
    ItemDTO obj=null;
    ps2.setInt(1, itemId);
    ResultSet rs=ps2.executeQuery();
    if(rs.next())
    {
        obj=new ItemDTO();
        obj.setItemId(itemId);
        obj.setItemType(rs.getString("item_type"));
        obj.setItemName(rs.getString("item_name"));
        obj.setItemPrice(rs.getDouble("item_price"));
        obj.setItemDesc(rs.getString("item_desc"));
        obj.setItemImage(rs.getString("item_image"));
    }
    return obj;
    
   }
  public static boolean addOrder(String name,ArrayList<ItemDTO> itemList,double totalAmount) throws SQLException
  {
       System.out.println("before updating order master");
    ResultSet rs=ps5.executeQuery();
    rs.next();
    int lastId=rs.getInt(1);
    String nextId="ORD-00"+(lastId+1);
    
    ps3.setString(1,nextId);
    ps3.setString(2,name);
    ps3.setDouble(3,totalAmount);
    java.util.Date today= new java.util.Date();
    long ms=today.getTime();
    java.sql.Date currDate=new java.sql.Date(ms);
     
    ps3.setDate(4, currDate);
    int ans1=ps3.executeUpdate();
    int count=0;
      System.out.println("Record inserted in order master..");
      for(ItemDTO item:itemList)
      {
          ps4.setString(1, nextId);
          ps4.setString(2, item.getItemName());
          ps4.setDouble(3, item.getItemPrice());
          int ans2=ps4.executeUpdate();
          if(ans2==1)
              ++count;
           
      }
      System.out.println("Record inserted in order details..");
      return (ans1==1 && count==itemList.size());
  }
  
  public static ArrayList<OrderDTO> getOrderByCustomer(String custName) throws SQLException
  {
      ArrayList<OrderDTO> orderList= new ArrayList<OrderDTO>();
     ps6.setString(1, custName);
     
     ResultSet rs=ps6.executeQuery();
     while(rs.next())
     {
         OrderDTO obj=new OrderDTO();
         obj.setOrderId(rs.getString(1));
         obj.setOrderAmount(rs.getDouble(2));
         obj.setOrderDate(rs.getDate(3));
         orderList.add(obj);
     }
     return orderList;
  }
  
}