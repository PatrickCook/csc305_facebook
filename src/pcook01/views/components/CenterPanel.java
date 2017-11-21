package pcook01.views.components;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class CenterPanel extends JPanel {
	private NewPostPanel newPostPanel;
	private NewsFeedPanel newsFeedPanel;
	
	public CenterPanel () {
		setLayout(new BorderLayout(0, 0));
		setBorder(new EmptyBorder(0,10,0,0));
		
		newPostPanel = new NewPostPanel();
		newsFeedPanel = new NewsFeedPanel();
		
		add(newPostPanel, BorderLayout.NORTH);
		add(newsFeedPanel, BorderLayout.CENTER);
		
	}
}
