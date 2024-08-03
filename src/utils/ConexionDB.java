package utils;

import java.sql.*;

public class ConexionDB {
   public PreparedStatement ps;
   public Connection con;
    public static Connection getConexion(){
        Connection con =null;
        try{
             Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3307/catalogo?serverTimezone=UTC";
			String user="root";
			String pass="";
			con = DriverManager.getConnection(url,user,pass);
            
            /*
            Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://containers-us-west-175.railway.app:7797/railway";
			String user="root";
			String pass="1loTBZ0rJfMexgbiXP5J";
			con = DriverManager.getConnection(url,user,pass);
            //Class.forName("com.mysql.cj.jdbc.Driver");
            //con=DriverManager.getConnection("jdbc:mysql://containers-us-west-175.railway.app:7797/railway?user=root");
*/
            
        }
        catch(Exception e){
            System.out.println("Error: "+e);
        }
        return con;
    }
    public static void main(String[] args) {
        getConexion();
    }
    public static ResultSet getTabla(String Consulta)
    {
        Connection con=getConexion();
        Statement st;
        ResultSet datos=null;
        try
        {
         st=con.createStatement();
         datos=st.executeQuery(Consulta);
        }
        catch(Exception e){
            System.out.println(e.toString());   
        }
        return datos;
        
        
    }
    
}


