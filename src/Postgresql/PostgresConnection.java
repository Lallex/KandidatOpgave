package Postgresql;
import java.sql.*;

public class PostgresConnection {
    public static boolean ExecuteQuerry(String query){
        //Create stock item and insert into database.
        Statement stmt = null;
        java.sql.Connection conn = null;

        try {
            //Open connection
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres", "postgres", "admin");
            System.out.println("Connected to PostgreSQL database!");
            stmt = conn.createStatement();

            //Execute a query
            stmt.executeUpdate(query);
            System.out.println("Executed query.");

            stmt.close();
            return true;
        }catch (SQLException e) {
            System.out.println("SQL Exeption.");
            e.printStackTrace();
            return false;
        }finally {
            //finally block used to close resources
            try{
                if(stmt!=null) {
                    conn.close();
                }
            }catch(SQLException se){
                System.out.println("SQL Exeption while parsing.");
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static ResultSet ExecuteQuerryWithResultSet(String query){
        //Create stock item and insert into database.
        Statement stmt = null;
        java.sql.Connection conn = null;

        try {
            //Open connection
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres", "postgres", "admin");
            System.out.println("Connected to PostgreSQL database!");
            stmt = conn.createStatement();

            //Execute a query
            ResultSet resultSet = stmt.executeQuery(query);
            System.out.println("Executed query.");


            //stmt.close();
            return resultSet;
        }catch (SQLException e) {
            System.out.println("SQL Exeption.");
            e.printStackTrace();
            return null;
        }finally {
            //finally block used to close resources
            try{
                if(stmt!=null) {
                    conn.close();
                }
            }catch(SQLException se){
                System.out.println("SQL Exeption while parsing.");
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
