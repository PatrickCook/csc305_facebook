package pcook01.views.components;


import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class SidePanel extends JPanel {
	private UserPanel userPanel;
	private FriendList friendsPanel;
	
	public SidePanel() {
		userPanel = new UserPanel();
		friendsPanel = new FriendList();
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		this.add(userPanel);
		this.add(friendsPanel);
	
	}
}
