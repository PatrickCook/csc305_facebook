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
	private ArrayList<NewsFeedPostPanel> newsFeed;
	private JPanel newsFeedPanel;
	private JScrollPane scrollable;
	private JLabel newsFeedHeader;
	
	public NewsFeedPanel () {
		setLayout(new BorderLayout(0, 0));
		newsFeedPanel = new JPanel();
		
		newsFeedHeader = new JLabel("News Feed");
		newsFeedHeader.setBorder(new EmptyBorder(0,0,10,0));
		
		Decorator.setBoldWithSize(newsFeedHeader, 16);
		
		scrollable = new JScrollPane(newsFeedPanel);
		scrollable.setBorder(null);
		
		add(newsFeedHeader, BorderLayout.NORTH);
		add(scrollable, BorderLayout.CENTER);
		
		newsFeed = new ArrayList<>();
	}
	
	public void populateNewsFeed(User user) {
		FacebookDB db = FacebookDB.getInstance();
		ArrayList<Post> posts = db.getAllPosts(user);
		
		newsFeedPanel.removeAll();
		newsFeedPanel.setLayout(new GridLayout(posts.size(), 1, 0, 25));
		newsFeed.clear();
		
		for (Iterator<Post> i = posts.iterator(); i.hasNext(); ) {
			Post post = i.next();
			NewsFeedPostPanel panel = new NewsFeedPostPanel(post);
			newsFeedPanel.add(panel);
		}
		
	}
}
