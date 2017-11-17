package pcook01.views;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class SidePanel extends JPanel {
	private UserPanel userPanel;
	private FriendsPanel friendsPanel;
	
	public SidePanel() {
		userPanel = new UserPanel();
		friendsPanel = new FriendsPanel();
		
		setLayout(new BorderLayout(0, 0));
		this.add(userPanel, BorderLayout.NORTH);
		this.add(friendsPanel, BorderLayout.CENTER);
	}
}
