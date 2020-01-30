import Postgresql.PostgresConnection;

public class StockItem extends Article {
    public Location location;

    public StockItem(int id, String name, double price, Location location){
        super(id, name, price);
        this.name = name;
        this.price = price;
        this.location = location;
    }

    public boolean create(){
        //Create stock item and insert into database.
        String query = "INSERT INTO stockitem (location_id_stockitem, article_id_stockitem)\n" +
                "VALUES (\n" +
                "(SELECT location_id FROM location WHERE rack = '" + this.location.rack +
                "'\n" +
                "\tAND shelf = '" + this.location.shelf + "'\n" +
                "\tAND shelf_space = '" + this.location.shelfSpace + "'),\n" +
                "(SELECT article_id FROM article WHERE article_name LIKE '" + this.name +
                "'));";
        boolean b = PostgresConnection.ExecuteQuerry(query);
        return b;
    }

    @Override
    public boolean delete(){
        //Create delete
        String query = "DELETE FROM stockitem\n" +
                "WHERE stockitem.location_id_stockitem = '" + this.location.id + "'\n" +
                "AND stockitem.article_id_stockitem = '" + this.id +
                "';";
        boolean b = PostgresConnection.ExecuteQuerry(query);
        return b;
    }

    public boolean update(int locationId, int articleId){
        //Update object (name, price)

        String query = "UPDATE stockitem\n" +
                "SET location_id_stockitem = '"+ locationId +
                "', article_id_stockitem = '" + articleId +
                "'\n" +
                "WHERE location_id_stockitem = '" + this.location.id +
                "'\n" +
                "AND article_id_stockitem = '" + this.id +
                "';";

        boolean b = PostgresConnection.ExecuteQuerry(query);
        return b;
    }
}
