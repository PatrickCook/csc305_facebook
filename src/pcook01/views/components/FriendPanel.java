package pcook01.views.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pcook01.models.User;

public class FriendPanel extends JButton {
	private User friend;
	
	public FriendPanel(User friend, ActionListener selectUser) {
		this.friend = friend;
		this.setText(friend.getUsername());
		this.setBackground(Color.WHITE);
		this.addActionListener(selectUser);
	}
	
	public User getFriend() {
		return friend;
	}
	
	@Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 40);
    }
	
	@Override
    public Dimension getMaximumSize() {
        return new Dimension(200, 40);
    }
	
	@Override
    public Dimension getMinimumSize() {
        return new Dimension(200, 40);
    }
}
