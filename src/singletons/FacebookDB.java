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
		String sql, token, profileImgUrl;
		sql = "SELECT * FROM Users WHERE username=?";

		try (Connection conn = this.connect()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				id = rs.getInt("id");
				token = rs.getString("password_hash");
				profileImgUrl = rs.getString("profile_image_url");
				
				if (PasswordAuth.check(password, token)) {
					user.setId(id);
					user.setUsername(username);
					user.setProfileImgUrl(profileImgUrl);
					
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
	
	public boolean createUser(User user, String username, String passwordHash) {
		String sql1;
		sql1 = "INSERT INTO Users (username, password_hash) VALUES (?, ?);";

		try (Connection conn = this.connect()) {
			PreparedStatement pstmt = conn.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, username);
			pstmt.setString(2, passwordHash);
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
			  int id = rs.getInt(1);
			  user.setId(id);
			  user.setUsername(username);
			  
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
		
		sql = "SELECT DISTINCT * FROM\n" + 
				"	(SELECT  u1.username, p.post, created\n" + 
				"	FROM Users u\n" + 
				"	INNER JOIN Friends f ON u.id = f.friend_a\n" + 
				"	INNER JOIN Posts p ON p.user_id = f.friend_a OR p.user_id = friend_b\n" + 
				"	INNER JOIN Users u1 ON p.user_id = u1.id\n" + 
				"	WHERE u.id = ? \n" + 
				"	UNION ALL\n" + 
				"	SELECT username, post, created\n" + 
				"	FROM Users u\n" + 
				"	INNER JOIN Posts ON u.id = user_id\n" + 
				"	WHERE u.id = ?) temp\n" + 
				"ORDER BY created DESC;";

		try (Connection conn = this.connect()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user.getId());
			pstmt.setInt(2, user.getId());
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

		System.out.println("Loaded all posts.");
		
		return posts;
	}
	
	public ArrayList<Post> getUserPosts(User user) {
		String sql;
		ArrayList<Post> posts = new ArrayList<>();
		
		sql = "SELECT * FROM Posts WHERE user_id=?";

		try (Connection conn = this.connect()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user.getId());
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String post = rs.getString("post");
				
				posts.add(new Post(user.getUsername(), post));
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Loaded only user posts.");
		
		return posts;
	}
	
	public ArrayList<User> getAllFriends(User user) {
		String sql;
		ArrayList<User> friends = new ArrayList<>();
		
		sql = "SELECT *\n" + 
				"FROM Friends \n" + 
				"INNER JOIN Users ON id = friend_b\n" + 
				"WHERE friend_a = ?;";

		try (Connection conn = this.connect()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user.getId());

			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String url = rs.getString("profile_image_url");
				
				friends.add(new User(id, username, url));
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Loaded all friends.");
		
		return friends;
	}
	
	public boolean updateUser(int id, String username, String hash) {
		String sql;
		sql = "UPDATE Users SET "
				+ "username=?, "
				+ "password_hash=? "
				+ "WHERE id=?;";

		try (Connection conn = this.connect()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, hash);
			pstmt.setInt(3, id);
			
			if (pstmt.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Could not update user.");
		return false;
	}
	
	public boolean updateUserProfileImage(User user) {
		String sql;
		sql = "UPDATE Users SET profile_image_url=? WHERE username=?";

		try (Connection conn = this.connect()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getProfileImgUrl());
			pstmt.setString(2, user.getUsername());
			
			if (pstmt.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Could not update user profile image.");
		return false;
	}
	
	public void deleteUser(User user) {
		String sql1, sql2, sql3;
		
		sql1 = "DELETE FROM Users WHERE id=?;";
		sql2 = "DELETE FROM Friends WHERE friend_a=? OR friend_b=?;";
		sql3 = "DELETE FROM Posts WHERE user_id=?;";
		
		try (Connection conn = this.connect()) {
			PreparedStatement pstmt = conn.prepareStatement(sql1);	
			pstmt.setInt(1, user.getId());
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql2);	
			pstmt.setInt(1, user.getId());
			pstmt.setInt(2, user.getId());
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql3);	
			pstmt.setInt(1, user.getId());
			pstmt.executeUpdate();
		
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean followUser(User userA, User userB) {
		String sql;
		
		sql = "INSERT INTO Friends (friend_a, friend_b) VALUES (?, ?);";
		
		try (Connection conn = this.connect()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, userA.getId());
			pstmt.setInt(2, userB.getId());

			if (pstmt.executeUpdate() > 0) {
				return true;
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Could not follow user.");
		
		return false;
	}
	
	public void unfollowUser(User userA, User userB) {
		String sql;
		
		sql = "DELETE FROM Friends WHERE friend_a=? AND friend_b=?;";
		
		try (Connection conn = this.connect()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, userA.getId());
			pstmt.setInt(2, userB.getId());
			pstmt.executeUpdate();
		
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean isFollowing(User userA, User userB) {
		String sql;
		
		sql = "SELECT * FROM Friends WHERE friend_a=? AND friend_b=?;";
		
		try (Connection conn = this.connect()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userA.getId());
			pstmt.setInt(2, userB.getId());
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
	
	public ArrayList<User> getSearchResults(String query) {
		String sql;
		ArrayList<User> results = new ArrayList<>();
		
		sql = "SELECT * FROM Users WHERE username LIKE '%"+query+"%';";
		
		try (Connection conn = this.connect()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String url = rs.getString("profile_image_url");
				
				results.add(new User(id, username, url));
			}
		
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return results;
	}
}
