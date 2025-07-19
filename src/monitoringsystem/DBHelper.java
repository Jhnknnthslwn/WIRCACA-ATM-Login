/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitoringsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

/**
 *
 * @author mel
 */
public class DBHelper {
    
     public static Statement sr;
    public static Connection conn;
    public static PreparedStatement getData;
   
    public static PreparedStatement fname;
    
    
    static{
        try{
            
        String url = "jdbc:mysql://localhost:3306/studentdata";
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(url,"root","");
        
        sr =conn.createStatement();
        
        getData =conn.prepareStatement("Select * from studentdata where studentid like ? or Firstname like ? or Surname like? or Section like ? or id like ?");
        
        
        
        
    }catch(SQLException e)
    {
        e.printStackTrace();
    }   catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static ResultSet getData(String val1, String val2, String val3, String val4, String val5) throws SQLException{
        getData.setString(1,"%"+val1+"%");
        getData.setString(2,"%"+val2+"%");
        getData.setString(3,"%"+val3+"%");
        getData.setString(4,"%"+val4+"%");
        getData.setString(5,"%"+val5+"%");
        return getData.executeQuery();
    }
   
}
