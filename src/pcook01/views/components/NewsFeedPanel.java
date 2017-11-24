package pcook01.views.components;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import pcook01.models.Post;
import pcook01.models.User;
import singletons.Decorator;
import singletons.FacebookDB;

public class NewsFeedPanel extends JPanel {
	private boolean isProfile;
	private JPanel newsFeedPanel;
	private JScrollPane scrollable;
	private JLabel newsFeedHeader;
	private JLabel message;
	
	public NewsFeedPanel (boolean isUserProfile) {
		isProfile = isUserProfile;
		
		setLayout(new BorderLayout(0, 0));
		newsFeedPanel = new JPanel();
		
		newsFeedHeader = new JLabel("News Feed");
		newsFeedHeader.setBorder(new EmptyBorder(0,0,10,0));
		
		Decorator.setBoldWithSize(newsFeedHeader, 16);
		
		scrollable = new JScrollPane(newsFeedPanel);
		scrollable.setBorder(null);
		
		add(newsFeedHeader, BorderLayout.NORTH);
		add(scrollable, BorderLayout.CENTER);
	}
	
	public void populateNewsFeed(User user, boolean showWall) {
		ArrayList<Post> posts;
		FacebookDB db = FacebookDB.getInstance();
		
		newsFeedPanel.removeAll();
		
		if (showWall && isProfile) {
			posts = db.getUserPosts(user);
		} 
		else if (showWall && !isProfile) {
			posts = db.getAllPosts(user);
		} 
		else {
			message = new JLabel("Follow " + user.getUsername() + " to see their wall!");
			newsFeedPanel.add(message);
			return;
		}
		
		newsFeedPanel.setLayout(new GridLayout(posts.size(), 1, 0, 25));
		
		for (Iterator<Post> i = posts.iterator(); i.hasNext(); ) {
			Post post = i.next();
			NewsFeedPostPanel panel = new NewsFeedPostPanel(post);
			newsFeedPanel.add(panel);
		}
		
		scrollable.getVerticalScrollBar().setValue(0);
		revalidate();
		repaint();
	}
}
