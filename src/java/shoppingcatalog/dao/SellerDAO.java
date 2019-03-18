package shoppingcatalog.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import shoppingcatalog.dbutil.DBConnection;
import shoppingcatalog.dto.ItemDTO;

public class SellerDAO {
    public static PreparedStatement ps1,ps2;
    static
    {
        try
        {
            ps1=DBConnection.getConnection().prepareStatement("select from store_items where id=?");
            ps2=DBConnection.getConnection().prepareStatement("insert into store_items values (?,?,?,?,?,?)");
         }
        catch(Exception e)
        {
             System.out.println("Error in DB connection..");
        }
    }
    public static boolean searchItemId(String id)throws SQLException
    {
        ps1.setString(1,id);
        ResultSet rs=ps1.executeQuery();
        return rs.next();
    }
     public static boolean registerItem(ItemDTO item) throws SQLException
     {
         ps2.setInt(1,item.getItemId());
         ps2.setString(2,item.getItemType());
         ps2.setString(3,item.getItemName());
         ps2.setDouble(4,item.getItemPrice());
         ps2.setString(5,item.getItemDesc());
         ps2.setString(6,item.getItemImage());
         int ans=ps2.executeUpdate();
          return (ans==1);   
     }
}
