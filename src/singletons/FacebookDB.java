package singletons;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FacebookDB {
	private static Connection conn = null;
	private static FacebookDB uniqueInstance;
	private static Statement stmt = null;

	private FacebookDB() {
		connect();
	}

	public static FacebookDB getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new FacebookDB();
		}
		return uniqueInstance;
	}

	private Connection connect() {
		// SQLite connection string
		String url = "jdbc:sqlite:src/db/facebook.db";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	public boolean validateUser(String username, String password) {
		String sql, token;
		sql = "SELECT * FROM User WHERE username=?";

		try (Connection conn = this.connect()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				token = rs.getString("password_hash");
				if (PasswordAuth.check(password, token)) {
					System.out.println("User authenticated.");
					return true;
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Could not authenticate user.");
		return false;
	}
	
	public boolean containsUser(String username) {
		String sql;
		sql = "SELECT * FROM User WHERE username=?";

		try (Connection conn = this.connect()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean createUser(String username, String passwordHash) {
		String sql;
		sql = "INSERT INTO User (username, password_hash) VALUES (?, ?);";

		try (Connection conn = this.connect()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, passwordHash);
			
			if (pstmt.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Could not create user.");
		return false;
	}
}
