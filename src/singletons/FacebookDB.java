package singletons;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import pcook01.models.Post;
import pcook01.models.User;

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

	public boolean validateUser(User user, String username, String password) {
		int id;
		String sql, token;
		sql = "SELECT * FROM Users WHERE username=?";

		System.out.println(username + password);
		try (Connection conn = this.connect()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				id = rs.getInt("id");
				token = rs.getString("password_hash");
				if (PasswordAuth.check(password, token)) {
					user.setId(id);
					user.setUsername(username);
					
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
		sql = "SELECT * FROM Users WHERE username=?";

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
		sql = "INSERT INTO Users (username, password_hash) VALUES (?, ?);";

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
	
	public boolean createPost(User user, String post) {
		String sql, time;
		
		sql = "INSERT INTO Posts (user_id, post, created) VALUES (?, ?, ?);";
		time = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		
		try (Connection conn = this.connect()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, user.getId());
			pstmt.setString(2, post);
			pstmt.setString(3, time);
			
			if (pstmt.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Could not create post.");
		
		return false;
	}
	
	public ArrayList<Post> getAllPosts(User user) {
		String sql;
		ArrayList<Post> posts = new ArrayList<>();
		
		sql = "SELECT  DISTINCT u1.username, p.post\n" + 
				"FROM Users u\n" + 
				"INNER JOIN Friends f ON u.id = f.friend_a\n" + 
				"INNER JOIN Posts p ON p.user_id = f.friend_a OR p.user_id = friend_b\n" + 
				"INNER JOIN Users u1 ON p.user_id = u1.id\n" + 
				"WHERE u.id = ? ORDER BY p.created DESC;";

		try (Connection conn = this.connect()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user.getId());
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String username = rs.getString("username");
				String post = rs.getString("post");
				
				posts.add(new Post(username, post));
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Could not create post.");
		
		return posts;
	}
}
