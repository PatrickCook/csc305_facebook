package pcook01.views;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class CenterPanel extends JPanel {
	private NewPostPanel newPostPanel;
	private NewsFeedWrapperPanel newsFeedWrapperPanel;
	
	public CenterPanel () {
		setLayout(new BorderLayout(0, 0));
		setBorder(new EmptyBorder(0,10,0,0));
		
		newPostPanel = new NewPostPanel();
		newsFeedWrapperPanel = new NewsFeedWrapperPanel();
		
		add(newPostPanel, BorderLayout.NORTH);
		add(newsFeedWrapperPanel, BorderLayout.CENTER);
		
	}
}
