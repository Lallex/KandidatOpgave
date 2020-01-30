import java.sql.*;
import Postgresql.PostgresConnection;

public class Location {
    int id;
    int rack;
    int shelf;
    int shelfSpace;

    public Location(int id, int rack, int shelf, int shelfSpace) {
        this.id = id;
        this.rack = rack;
        this.shelf = shelf;
        this.shelfSpace = shelfSpace;
    }

    public boolean create(){
        //Create new location object
        ResultSet resultSet = PostgresConnection.ExecuteQuerryWithResultSet("SELECT COUNT(*) FROM location;");
        int tableSize = 0;

        try {
            resultSet.next();
            tableSize = resultSet.getInt("count");
            System.out.println(tableSize);
        }catch (SQLException e){
            e.printStackTrace();
        }

        if (tableSize < 1000) {
            String query = "INSERT INTO location (rack, shelf, shelf_space)\n" +
                    "VALUES (" + this.rack +
                    ", " + this.shelf +
                    ", " + this.shelfSpace +
                    ");";
            boolean b = PostgresConnection.ExecuteQuerry(query);
            return b;
        }else{
            System.out.println("There are too many rows in table article.");
            return false;
        }
    }

    public boolean delete(){
        //Delete location object
        String query = "DELETE FROM location\n" +
                "WHERE location_id = '" + this.id +
                "';";
        boolean b = PostgresConnection.ExecuteQuerry(query);
        return b;
    }

    public boolean update(){
        String query = "UPDATE location\n" +
                "SET rack = '" + this.rack +
                "', " +
                "shelf = '" + this.shelf +
                "', " +
                "shelf_space = '" + this.shelfSpace +
                "'\n" +
                "WHERE location_id = '" + this.id +
                "';";
        boolean b = PostgresConnection.ExecuteQuerry(query);
        return b;
    }

    public boolean insertLocations(int racks, int shelfsPrRack, int shelfSpacesPrShelf){
        //Inserts spaces into database
        String query;
        Statement stmt = null;
        Connection conn = null;
        int i = 0;
        try {
            //Open connection
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres", "postgres", "admin");
            System.out.println("Connected to PostgreSQL database!");
            stmt = conn.createStatement();

            for (int rack = 1; rack <= 50; rack++) {
                for (int shelf = 1; shelf <= 5; shelf++) {
                    for (int shelfSpace = 1; shelfSpace <= 4; shelfSpace++) {
                        query = "INSERT INTO location (rack, shelf, shelf_space)" +
                                "VALUES (" +
                                rack +
                                ", " +
                                shelf +
                                ", " +
                                shelfSpace +
                                ")" +
                                ";";

                        //Execute a query
                        stmt.executeUpdate(query);
                        System.out.println("Executed query inserted location (" + rack + ", " + shelf + ", " + shelfSpace + ")");
                        i++;
                        query = null;
                    }
                }
            }
            System.out.println(i + " locations inserted.");
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
}
