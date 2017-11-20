package pcook01.views;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

public class NewsFeedPanel extends JPanel {
	private ArrayList<NewsFeedPostPanel> newsFeed;
	
	public NewsFeedPanel () {
		
		newsFeed = new ArrayList<>();
		newsFeed.add(new NewsFeedPostPanel());
		newsFeed.add(new NewsFeedPostPanel());
		newsFeed.add(new NewsFeedPostPanel());
		
		refreshNewsFeed();
	}
	
	/* Removes all posts and refreshes */
	public void refreshNewsFeed() {
		removeAll();
		setLayout(new GridLayout(newsFeed.size(), 1, 0, 50));
		
		for (Iterator<NewsFeedPostPanel> i = newsFeed.iterator(); i.hasNext(); ) {
			NewsFeedPostPanel post = i.next();
			add(post);
		}
	}
}
