import Postgresql.PostgresConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Article {
    int id;
    String name;
    double price;

    public Article(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public boolean create(){
        //Create object
        ResultSet resultSet = PostgresConnection.ExecuteQuerryWithResultSet("SELECT COUNT(*) FROM article;");
        int tableSize = 0;
        try {
            resultSet.next();
            tableSize = resultSet.getInt("count");
            System.out.println(tableSize);
        }catch (SQLException e){
            e.printStackTrace();
        }

        if (tableSize < 200) {
            String query = "INSERT INTO article (article_name, price)\n" +
                    "VALUES ('" + this.name +
                    "', '" + this.price +
                    "');";
            boolean b = PostgresConnection.ExecuteQuerry(query);
            return b;
        }else{
            System.out.println("There are too many rows in table article.");
            return false;
        }
    }

    public boolean delete(){
        //Delete object
        String query = "DELETE FROM article\n" +
                "WHERE article_id = '" + this.id +
                "';";
        boolean b = PostgresConnection.ExecuteQuerry(query);
        return b;
    }

    public boolean update(){
        //Update object
        String query = "UPDATE article\n" +
                "SET article_name = '" + this.name +
                "', price = '" + this.price +
                "'\n" +
                "WHERE article_id = '" + this.id +
                "';";
        boolean b = PostgresConnection.ExecuteQuerry(query);
        return b;
    }
}
