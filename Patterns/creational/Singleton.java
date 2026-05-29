package Patterns.creational;


/**
 * Only one instance can be created for a single class
 */
public class Singleton {

    public static void main(String[] args) {

        DatabaseConnection connection = DatabaseConnection.getConnection();
        connection.query("Select * from Table_name where condition = Something");

    }
}

/**
 * When you need single instance shared across, you create singleton
 */
class DatabaseConnection {
    private static DatabaseConnection connection;
    private DatabaseConnection(){

    }
    public static DatabaseConnection getConnection(){
        if(connection==null){
            connection = new DatabaseConnection();
        }
        return connection;

    }

    public void query(String query){
        System.out.println("Executing query = " + query);

    }
}
