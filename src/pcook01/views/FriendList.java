package pcook01.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import pcook01.models.User;

public class FriendList extends JPanel {
	private User defaultUser;
	private JLabel friendsHeader;
	private JPanel friendListPanel;
	private HashSet<FriendPanel> friendList;
	private GridBagConstraints gbc;

	
	public FriendList() {
		setLayout(new BorderLayout());
		
		friendListPanel = new JPanel(new GridBagLayout());

		defaultUser = new User();
		friendList = new HashSet<>();
		friendsHeader = new JLabel("Friends:");
		friendsHeader.setBorder(new EmptyBorder(0,10,0,0));

		
		Decorator.setBoldWithSize(friendsHeader, 16);
		
		friendList.add(new FriendPanel(defaultUser));
		friendList.add(new FriendPanel(defaultUser));
		friendList.add(new FriendPanel(defaultUser));
		friendList.add(new FriendPanel(defaultUser));
		friendList.add(new FriendPanel(defaultUser));
		friendList.add(new FriendPanel(defaultUser));
		friendList.add(new FriendPanel(defaultUser));
		friendList.add(new FriendPanel(defaultUser));
		friendList.add(new FriendPanel(defaultUser));
		friendList.add(new FriendPanel(defaultUser));
		friendList.add(new FriendPanel(defaultUser));
		friendList.add(new FriendPanel(defaultUser));
		
		add(friendsHeader, BorderLayout.NORTH);
		add(new JScrollPane(friendListPanel), BorderLayout.CENTER);
		setBorder(null);
		refreshFriendList();
	}
	
	/* Removes all friends and refreshes */
	public void refreshFriendList() {
		
		for (Iterator<FriendPanel> i = friendList.iterator(); i.hasNext(); ) {
			FriendPanel post = i.next();

			post.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
			GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.weightx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            friendListPanel.add(post, gbc);
            
            validate();
            repaint();
		}
	}
	
	 @Override
     public Dimension getPreferredSize() {
         return new Dimension(200, 300);
     }
}
