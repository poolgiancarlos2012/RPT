package model.cnx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Cnx{
    
    static String HOST = "SERVER400";
    static String BD = "RSFACCAR";
    static String USER = "sa";
    static String PASS = "Andinars08";
    static String PORT = "1433";
    static String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static String CONEXION = "jdbc:sqlserver://"+HOST+":"+PORT+";databaseName="+BD;
    
    Connection conn = null;
    
    public Cnx(){
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(CONEXION,USER,PASS);
            
            if(conn!=null){
                System.out.println("Conexion a base de datos "+BD+" OK");
            }
            
        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e){
            System.out.println(e);
        } catch (Exception e){
            System.out.println(e);
        }
    }
    
    public Connection getConnection(){
        return conn;
    }
    
    public void desconectar(){
        conn = null;
    }
    
//    public static void main(String[] args){
//        Cnx con = new Cnx();
//    }
    
}

