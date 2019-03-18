package shoppingcatalog.dbutil;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection 
{
   private static Connection conn;
   static
   {
       try{
           Class.forName("oracle.jdbc.OracleDriver");
           System.out.println("Driver loaded successfully..!");
           conn=DriverManager.getConnection("jdbc:oracle:thin:@//LAPTOP-UMDL3MOA:1521/XE","onlineshopping","shopping");   
       }
       catch(Exception ex)
       {
           System.out.println("Exception in opening connection...in DBConnection...!!!");
       }
   }
       public static Connection getConnection()
       {
           return conn;
       }
       public static void closeConnection(){
           try{
               conn.close();
           }
           catch(Exception ex){
               System.out.println("Exception in closing connection...!!!!");
           }
       }
   
}
