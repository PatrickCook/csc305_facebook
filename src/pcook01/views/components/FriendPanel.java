package pcook01.views.components;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import pcook01.models.User;

public class FriendPanel extends JPanel {
	private User friend;
	private JLabel friendName;
	
	public FriendPanel(User friend) {
		this.friend = friend;
		this.friendName = new JLabel(friend.getUsername());
		this.add(friendName);
		this.setBackground(Color.WHITE);
	}
	
	@Override
    public Dimension getPreferredSize() {
        return new Dimension(getWidth(), 40);
    }
}
