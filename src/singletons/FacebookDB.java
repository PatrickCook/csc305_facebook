package singletons;



 
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class FacebookDB {
	private static Connection conn;
	private static FacebookDB uniqueInstance;
 
	private FacebookDB () {
		String url = "jdbc:sqlite:src/db/facebook.db";
		 
        try (Connection c = DriverManager.getConnection(url)) {
            if (c != null) {
                System.out.println("facebook.db has been opened");
                conn = c;
            }
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
	
	public static FacebookDB getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new FacebookDB();
		}
		return uniqueInstance;
	}
	
	public boolean validateUser(String username, String password) {
		return true;
	}
}
