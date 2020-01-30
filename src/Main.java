import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import Postgresql.PostgresConnection;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);  // Create a Scanner object

        /*
        //Simple login
        System.out.println("Enter username:");
        String username = scan.nextLine();  // Read user input as String
        System.out.println("Username is: " + username);  // Output username

        System.out.println("Enter password:");
        String password = scan.nextLine();  // Read user input as String

        Member member = new Member(0, username, password);
        boolean login isMember = member.isMember();
        */

        if (true){ //login == true
            System.out.println("Login successful.");

            System.out.println("Menu options: \n" +
                    "1: User requested functionalities.\n" +
                    "2: Article.\n" +
                    "3: Location.\n" +
                    "4: StockItem.");
            int menuOption = scan.nextInt(); // Read user input as int
            scan.nextLine(); // Takes extra newline

            //_____________________________Main menu_____________________________
            switch (menuOption){
                case 1:
                    System.out.println("Write the id of the specific article you would like to find the locations of.");
                    int userArticleId = scan.nextInt();
                    System.out.println("");
                    String queryItemLocation = "SELECT * FROM location\n" +
                            "RIGHT JOIN stockitem ON location.location_id = stockitem.location_id_stockitem\n" +
                            "WHERE article_id_stockitem = '" + userArticleId +
                            "';";

                    try {
                        ResultSet resultSetLocation = PostgresConnection.ExecuteQuerryWithResultSet(queryItemLocation);
                        while (resultSetLocation.next()) {
                            int rack = resultSetLocation.getInt("rack");
                            int shelf = resultSetLocation.getInt("shelf");
                            int shelfSpace = resultSetLocation.getInt("shelf_space");
                            int locatioinId = resultSetLocation.getInt("location_id");
                            int articleIdStockitem = resultSetLocation.getInt("article_id_stockitem");

                            System.out.println(rack + "\t" + shelf +
                                    "\t" + shelfSpace + "\t" + locatioinId +
                                    "\t" + articleIdStockitem +
                                    "\n All stock items of article and each location.");
                        }
                    }catch (SQLException e){
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.println("Menu options for object Article: \n" +
                            "1: Create.\n" +
                            "2: Delete.\n" +
                            "3: Update.\n" +
                            "4: Test class.");
                    int articleOption = scan.nextInt(); // Read user input as int
                    scan.nextLine(); // Takes extra newline

                    //_____________________________Article menu_____________________________
                    switch (articleOption){
                        case 1:
                            // Create Article.
                            System.out.println("Write article name of the stock item that you wish to create.");
                            String createArticleName = scan.nextLine(); // Read user input as String

                            System.out.println("Write the price of the article to be inserted.");
                            double createPrice = scan.nextDouble(); // Read user input as double

                            Article createArticle = new Article(0, createArticleName, createPrice);
                            boolean ba1 = createArticle.create();
                            if (ba1 == true){
                                System.out.println("Article successfully inserted into database.");
                            }else{
                                System.out.println("Insert failed");
                            }
                            break;
                        case 2:
                            // Delete Article.
                            System.out.println("Write article id that should be deleted.");
                            int deleteArticleId = scan.nextInt(); // Read user input as int

                            Article deleteArticle = new Article(deleteArticleId, null,0);

                            boolean ba2 = deleteArticle.delete();
                            if (ba2 == true){
                                System.out.println("Article successfully deleted from database.");
                            }else{
                                System.out.println("Deletion failed");
                            }
                            break;
                        case 3:
                            //Update Article.
                            System.out.println("Write location id of location that should be changed.");
                            int updateArticleId = scan.nextInt(); // Read user input as int

                            System.out.println("Write rack number that should be changed into.");
                            String updateArticleName = scan.nextLine(); // Read user input as String

                            System.out.println("Write shelf number that should be changed into.");
                            double updateArticlePrice = scan.nextDouble(); // Read user input as double

                            Article updateArticle = new Article(updateArticleId, updateArticleName, updateArticlePrice);
                            boolean ba3 = updateArticle.update();
                            if (ba3 == true){
                                System.out.println("Article successfully updated database.");
                            }else{
                                System.out.println("Update failed");
                            }
                            break;
                        case 4:
                            Article testArticle = new Article(25, "test item", 20.25);
                            testArticle.create();
                            testArticle.price = 50.45;
                            testArticle.update();
                            testArticle.name = "test item updated";
                            testArticle.update();
                            testArticle.delete();
                            break;
                    }

                    break;
                case 3:
                    System.out.println("Menu options for object Location: \n" +
                            "1: Create.\n" +
                            "2: Delete.\n" +
                            "3: Update.\n" +
                            "4: Test class");
                    int locationOption = scan.nextInt(); // Read user input as int
                    scan.nextLine(); // Takes extra newline

                    //_____________________________Location menu_____________________________
                    switch (locationOption){
                        case 1:
                            // Create Location.
                            System.out.println("Write the location you wish to insert into the database.\n" +
                                    "Start with the rack number.");
                            int createRack = scan.nextInt(); // Read user input as int

                            System.out.println("Now write the the shelf number.");
                            int createShelf = scan.nextInt(); // Read user input as int

                            System.out.println("Now write the shelf space of the item.");
                            int createShelfSpace = scan.nextInt(); // Read user input as int

                            if (createRack != 0 && createShelf != 0 && createShelfSpace != 0){
                                Location createLocation = new Location(0, createRack, createShelf, createShelfSpace);
                                boolean bl1 = createLocation.create();
                                if (bl1 == true){
                                    System.out.println("Location successfully inserted into database.");
                                }else{
                                    System.out.println("Insert failed");
                                }
                            }else{
                                System.out.println("Create failed, one of the input values was 0.");
                            }
                            break;
                        case 2:
                            // Delete Location.
                            System.out.println("Write stock item id that should be deleted.");
                            int deleteLocationId = scan.nextInt(); // Read user input as int

                            Location deleteLocation = new Location(deleteLocationId, 0, 0, 0);

                            boolean bl2 = deleteLocation.delete();
                            if (bl2 == true){
                                System.out.println("Loctione successfully deleted from database.");
                            }else{
                                System.out.println("Deletion failed");
                            }
                            break;
                        case 3:
                            // Update Location.
                            System.out.println("Write location id of stock item that should be changed.");
                            int updateLocationId = scan.nextInt(); // Read user input as int

                            System.out.println("Write rack number that should be changed.");
                            int updateLocationRack = scan.nextInt(); // Read user input as int

                            System.out.println("Write shelf number that should be changed.");
                            int updateLocationShelf = scan.nextInt(); // Read user input as int

                            System.out.println("Write shelf space number that should be changed.");
                            int updateLocationShelfSpace = scan.nextInt(); // Read user input as int

                            if (updateLocationId != 0 && updateLocationRack != 0 && updateLocationShelf != 0 && updateLocationShelfSpace != 0) {
                                Location updateLocation = new Location(updateLocationId, updateLocationRack, updateLocationShelf, updateLocationShelfSpace);
                                boolean bl3 = updateLocation.update();
                                if (bl3 == true) {
                                    System.out.println("Location successfully updated database.");
                                } else {
                                    System.out.println("Update failed");
                                }
                            }else {
                                System.out.println("Create failed, one of the input values was 0.");
                            }
                            break;
                        case 4:
                            Location testLocation = new Location(1050, 60, 1, 1);
                            testLocation.create();
                            testLocation.rack = 61;
                            testLocation.update();
                            testLocation.shelf = 2;
                            testLocation.update();
                            testLocation.shelfSpace = 3;
                            testLocation.update();
                            testLocation.delete();
                            break;
                    }
                    break;
                case 4:
                    System.out.println("Menu options for object StockItem: \n" +
                            "1: Create.\n" +
                            "2: Delete.\n" +
                            "3: Update.\n" +
                            "4: Test class.");
                    int optionStockItem = scan.nextInt(); // Read user input as int
                    scan.nextLine(); // Takes extra newline

                    //_____________________________StockItem menu_____________________________
                    switch(optionStockItem) {
                        case 1:
                            // Create StockItem
                            System.out.println("Write article name of the stock item that you wish to create.");
                            String createStockItemName = scan.nextLine(); // Read user input as String

                            System.out.println("Write the location of which the stock item should be storged.\n" +
                                    "First type which rack the item should be stored on.");
                            int createStockItemRack = scan.nextInt(); // Read user input as int

                            System.out.println("Now write the the shelf number.");
                            int createStockItemShelf = scan.nextInt(); // Read user input as int

                            System.out.println("Now write the shelf space of the item.");
                            int createStockItemShelfSpace = scan.nextInt(); // Read user input as int

                            if (createStockItemRack != 0 && createStockItemShelf != 0 && createStockItemShelfSpace != 0 && createStockItemName != null){
                                Location location = new Location(0, createStockItemRack, createStockItemShelf, createStockItemShelfSpace);
                                StockItem createStockItem = new StockItem(0, createStockItemName, 0, location);
                                boolean bs1 = createStockItem.create();
                                if (bs1 == true){
                                    System.out.println("Stock item successfully inserted into database.");
                                }else{
                                    System.out.println("Insert failed");
                                }
                            }else{
                                System.out.println("Create failed, one of the input values was 0.");
                            }
                            break;
                        case 2:
                            // Delete stock item.
                            System.out.println("Write stock item id that should be deleted.");
                            int deleteStockItemArticleId = scan.nextInt(); // Read user input as int
                            int deleteStockItemLocationId = scan.nextInt(); // Read user input as int

                            StockItem deleteStockItem = new StockItem(deleteStockItemArticleId, null, 0,  new Location(deleteStockItemLocationId, 0,0,0));
                            boolean b2 = deleteStockItem.delete();
                            if (b2 == true){
                                System.out.println("Stock item successfully deleted from database.");
                            }else{
                                System.out.println("Deletion failed");
                            }
                            break;
                        case 3:
                            // Update stock item.
                            System.out.println("Write location id of stock item that should be changed.");
                            int updateStockItemLocationId = scan.nextInt(); // Read user input as int

                            System.out.println("Write article id of stock item that should be changed.");
                            int updateStockItemArticleId = scan.nextInt(); // Read user input as int

                            System.out.println("Write what you would like to change the location to by id.");
                            int locationIdChangeTo = scan.nextInt(); // Read user input as int

                            System.out.println("Write what you would like to change the article to by id.");
                            int articleIdChangeTo = scan.nextInt(); // Read user input as int

                            StockItem si3 = new StockItem(updateStockItemArticleId, null, 0,  new Location(updateStockItemLocationId, 0, 0,0));
                            boolean b3 = si3.update(locationIdChangeTo, articleIdChangeTo);
                            if (b3 == true){
                                System.out.println("Stock item successfully updated database.");
                            }else{
                                System.out.println("Update failed");
                            }
                            break;
                        case 4:
                            Article testStockItemArticle = new Article( 2, "shoe", 30.10);
                            Location testStockItemLocation = new Location(1070, 40, 1, 1);
                            StockItem testStockItem = new StockItem(testStockItemArticle.id, testStockItemArticle.name, testStockItemArticle.price, testStockItemLocation);
                            testStockItem.create();
                            testStockItem.location.id = 793;
                            testStockItem.delete();
                            break;
                    }
                    break;
                default:
                    // code block
            }

            // Close scanner resource.
            scan.close();
        }else{
            System.out.println("Login Failed.");
        }
    }
}
