package pcook01.views;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class FriendsPanel extends JPanel {
	private JLabel friendsHeader;
	
	public FriendsPanel () {
		friendsHeader = new JLabel("Friends");
		
		this.add(friendsHeader);
	}
}
