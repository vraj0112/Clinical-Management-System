package clinical.management.system;

import java.sql.*;

class connection
{
    public Statement s;
    public Connection c;
    
    public void createConnection()
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicms","root","");
            s = c.createStatement();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    public void disconnectConnection()
    {
        try
        {
            c.close();
        }
        catch(Exception e){}
    }
}