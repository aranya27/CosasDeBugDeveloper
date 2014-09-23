package com.masejemplos;

import java.sql.*;
public class EjemploJDBC {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/EMP";
    static final String USER = "username";
    static final String PASS = "password";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String sql = "SELECT id, name FROM Employees";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                System.out.println("ID = "+rs.getInt("id")+". Name = "+rs.getString("name"));
            }
            rs.close(); stmt.close(); conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(ClassNotFoundException se){
            se.printStackTrace();
        }finally{
           try{
              if(stmt!=null) stmt.close();
           }catch(SQLException se2){}
           try{
              if(conn!=null) conn.close();
           }catch(SQLException se){}
        }
    }
}



