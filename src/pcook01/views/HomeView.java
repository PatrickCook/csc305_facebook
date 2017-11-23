package pcook01.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import pcook01.models.User;
import pcook01.views.components.FriendList;
import pcook01.views.components.MenuBarPanel;
import pcook01.views.components.NewPostPanel;
import pcook01.views.components.NewsFeedPanel;
import pcook01.views.components.UserPanel;
import singletons.Decorator;

public class HomeView extends JPanel {
	private User user;
	private TopPanel topPanel;
	private SidePanel sidePanel;
	private CenterPanel centerPanel;
	
	public HomeView (User user) {
		this.user = user;
		
		topPanel = new TopPanel();
		sidePanel = new SidePanel();
		centerPanel = new CenterPanel();
		
		setLayout(new BorderLayout(0,0));
		
		add(sidePanel, BorderLayout.WEST);
		add(centerPanel, BorderLayout.CENTER);
		add(topPanel, BorderLayout.NORTH);
	}
	
	public TopPanel getTopPanel() {
		return topPanel;
	}
	
	public SidePanel getSidePanel() {
		return sidePanel;
	}	
	
	public CenterPanel getCenterPanel() {
		return centerPanel;
	}	
	
	public class TopPanel extends JPanel {
		private JLabel appNameHeader;
		private JPanel panel;
		private JTextField searchTextField;
		private MenuBarPanel menuBarPanel;
		
		public TopPanel () {
			 appNameHeader = new JLabel("Facebook");
			 appNameHeader.setBorder(new EmptyBorder(0,10,0,0));
			 
			 menuBarPanel = new MenuBarPanel();
			 
			 Decorator.setBoldWithSize(appNameHeader, 26);
			 appNameHeader.setForeground(Color.WHITE);
			 setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
			 setLayout(new BorderLayout());
			 
			 panel = new JPanel();
		     panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		     panel.setBorder(BorderFactory.createEmptyBorder(6, 30, 6, 6));


		     searchTextField = new JTextField(60);
		     panel.add(searchTextField);

		     panel.add(Box.createRigidArea(new Dimension(6, 0)));

		     JButton findButton = new JButton("Search"); 
		     panel.add(findButton);
		     panel.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
			 
			 this.add(appNameHeader, BorderLayout.WEST);
			 this.add(panel, BorderLayout.CENTER);
			 this.add(menuBarPanel, BorderLayout.EAST);
		}
		
		public void addSignoutListener(ActionListener e) {
			menuBarPanel.addSignoutListener(e);
		}
		
		public void addSettingsListener(ActionListener e) {
			menuBarPanel.addSettingsListener(e);
		}
	}
	
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
		
		public void refreshFeed() {
			newsFeedPanel.populateNewsFeed(user);
		}
		
		public void resetNewPostPanel() {
			remove(newPostPanel);
			newPostPanel = new NewPostPanel();
			add(newPostPanel, BorderLayout.NORTH);
			
			revalidate();
			repaint();
		}
		
		public String getNewPost() {
			return newPostPanel.getNewPostText();
		}
		
		public void addNewPostListener(ActionListener e) {
			newPostPanel.addNewPostListener(e);
		}
	}
	
	
	public class SidePanel extends JPanel {
		private UserPanel userPanel;
		private FriendList friendsPanel;
		
		public SidePanel() {
			userPanel = new UserPanel(user);
			friendsPanel = new FriendList(user);
			setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
			
			this.add(userPanel);
			this.add(friendsPanel);
		
		}
		public void loadUserFriends() {
			friendsPanel.loadUserFriends();
		}
		
		public void reloadProfilePhoto() {
			userPanel.reloadProfilePhoto();
		}
		
		public void addUploadListener(ActionListener e) {
			userPanel.addUploadListener(e);
		}
	}
}
