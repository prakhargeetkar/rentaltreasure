/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingcatalog.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import shoppingcatalog.dbutil.DBConnection;
import shoppingcatalog.dto.UserDTO;

/**
 *
 * @author Dell
 */
public class LoginDAO 
{
    private static PreparedStatement ps;
    static
    {
    try
    {
    ps=DBConnection.getConnection().prepareStatement("select * from members where username=? and password=? and membertype=?");
    }
    catch(Exception ex)
    {
        System.out.println("Error in DB connection..");
    }
    }
    public static boolean validateUser(UserDTO user) throws SQLException
    {
        System.out.println(user.getUsertype());
        System.out.println(user.getUsername());
        ps.setString(1,user.getUsername());
        ps.setString(2,user.getPassword());
        ps.setString(3,user.getUsertype());
        ResultSet rs=ps.executeQuery();
        return rs.next();
    }
    
}
