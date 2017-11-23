package pcook01.views.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import pcook01.models.User;
import singletons.Decorator;
import singletons.FacebookDB;

public class FriendList extends JPanel {
	private User user;
	private JLabel friendsHeader;
	private JPanel friendListPanel;
	private ArrayList<User> friends;
	private GridBagConstraints gbc;

	
	public FriendList(User user) {
		this.user = user;
		
		setLayout(new BorderLayout());
		
		friendListPanel = new JPanel(new GridBagLayout());

		friends = new ArrayList<>();
		friendsHeader = new JLabel("Friends:");
		friendsHeader.setBorder(new EmptyBorder(0,10,0,0));
		
		Decorator.setBoldWithSize(friendsHeader, 16);
		
		add(friendsHeader, BorderLayout.NORTH);
		add(new JScrollPane(friendListPanel), BorderLayout.CENTER);
		setBorder(null);
	}
	
	/* Removes all friends and refreshes */
	public void loadUserFriends() {
		FacebookDB db = FacebookDB.getInstance();
		friends = db.getAllFriends(user);
		
		createFriendList();
	}
	
	public void createFriendList() {
		friendListPanel.removeAll();
		
		for (Iterator<User> i = friends.iterator(); i.hasNext(); ) {
			User user = i.next();
			FriendPanel panel = new FriendPanel(user);
			
			panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
			GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.anchor = GridBagConstraints.NORTH;
            gbc.weightx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            friendListPanel.add(panel, gbc);
            
            validate();
            repaint();
		}
	}
	
	 @Override
     public Dimension getPreferredSize() {
         return new Dimension(200, 300);
     }
}
