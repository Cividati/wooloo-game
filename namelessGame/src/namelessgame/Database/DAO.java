package namelessgame.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Henrique Barcia Lang
 */
public abstract class DAO {
    // Object for database connection.
    protected Connection con;
    
    // Object for dynamic SQL commands manipulation.
    protected PreparedStatement pst;
    
    // Object for static SQL commands manipulation.
    protected Statement st;
    
    // Object that references the table resulted from a search (SELECT).
    protected ResultSet rs;
    
    private String database = "namelessGame";
    private String url = "jdbc:mysql://localhost:3306/" + database + "?useTimezone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false";
    private String user = "root";
    private String password = "admin";
    
    public boolean connectToDatabase()
    {
        boolean connected = false;
        
        try
        {
            con = DriverManager.getConnection(url, user, password);
            
            connected = true;
        }
        catch(SQLException e)
        {
            System.err.println("Error when connecting to database... " + e.getMessage());
        }
        
        return connected;
    }
}

