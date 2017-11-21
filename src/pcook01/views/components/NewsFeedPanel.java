package pcook01.views.components;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import singletons.Decorator;

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
		newsFeed.add(new NewsFeedPostPanel());
		newsFeed.add(new NewsFeedPostPanel());
		newsFeed.add(new NewsFeedPostPanel());
		
		refreshNewsFeed();
	}
	
	/* Removes all posts and refreshes */
	public void refreshNewsFeed() {
		newsFeedPanel.removeAll();
		newsFeedPanel.setLayout(new GridLayout(newsFeed.size(), 1, 0, 50));
		
		for (Iterator<NewsFeedPostPanel> i = newsFeed.iterator(); i.hasNext(); ) {
			NewsFeedPostPanel post = i.next();
			newsFeedPanel.add(post);
		}
	}
}
