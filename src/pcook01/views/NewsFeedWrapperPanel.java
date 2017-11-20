package pcook01.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class NewsFeedWrapperPanel extends JPanel {
	private JScrollPane scrollable;
	private JLabel newsFeedHeader;
	private NewsFeedPanel newsFeedPanel;
	
	
	public NewsFeedWrapperPanel () {
		setLayout(new BorderLayout(0, 0));
		
		newsFeedHeader = new JLabel("News Feed");
		newsFeedHeader.setBorder(new EmptyBorder(0,0,10,0));
		
		Decorator.setBoldWithSize(newsFeedHeader, 16);
		
		newsFeedPanel = new NewsFeedPanel();
		scrollable = new JScrollPane(newsFeedPanel);
		scrollable.setBorder(null);
		
		add(newsFeedHeader, BorderLayout.NORTH);
		add(scrollable, BorderLayout.CENTER);
		
	}
}
