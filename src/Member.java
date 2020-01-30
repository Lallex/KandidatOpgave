import java.sql.*;
import Postgresql.PostgresConnection;

public class Member {
    int id;
    String username;
    String password;

    public Member(int id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public boolean create(){
        //Create new location object
        String query = "INSERT INTO member (username, password)\n" +
                "VALUES (" + this.username +
                ", " + this.password +
                ");";
        boolean b = PostgresConnection.ExecuteQuerry(query);
        return b;
    }

    public boolean delete(){
        //Delete location object
        String query = "DELETE FROM member\n" +
                "WHERE member_id = '" + this.id +
                "';";
        boolean b = PostgresConnection.ExecuteQuerry(query);
        return b;
    }

    public boolean update(){
        //Update location
        String query = "UPDATE member\n" +
                "SET username = '" + this.username +
                "', password = '" + this.password +
                "'" +
                "WHERE member_id = '" + this.id +
                "';";
        boolean b = PostgresConnection.ExecuteQuerry(query);
        return b;
    }

    public boolean isMember(){
        Statement stmt = null;
        Connection conn = null;
        String user = null;
        String pass = null;
        int id = 0;

        //SQL query to get member with the same username as the parameter.
        String query = "SELECT * FROM member WHERE username = '" + username + "';";

        try{
            //Open connection
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres", "postgres", "admin");
            System.out.println("Connected to PostgreSQL database!");
            stmt = conn.createStatement();

            //Executing the query
            ResultSet rs = stmt.executeQuery(query);
            //Get results with java resultset
            while (rs.next()) {
                id = rs.getInt("member_id");
                user = rs.getString("username");
                pass = rs.getString("password");
            }
            //Close statement
            stmt.close();
        }catch (SQLException e) {
            System.out.println("SQL Exeption.");
            e.printStackTrace();
            return false;
        }finally {
            //Finally block used to close resources
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

        if (this.password.equals(pass))
            if (this.username.equals(user)) {
                //Print the results of java resultset
                //System.out.format("%s, %s, %s\n", id, user, pass);
                return true;
            } else {
                System.out.println("Username does not match, something is wrong with the data.");
                return false;
            }
        else {
            System.out.println("Password does not correspond to the username.");
            return false;
        }
    }
}
