package pcook01.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import java.awt.Component;
import javax.swing.Box;

public class Facebook {

	private JFrame frame;
	private TopPanel topPanel;
	private SidePanel sidePanel;
	private CenterPanel centerPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Facebook window = new Facebook();
					window.frame.setVisible(true);
					window.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Facebook() {
		topPanel = new TopPanel();
		sidePanel = new SidePanel();
		centerPanel = new CenterPanel();
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(800, 600);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.getContentPane().add(sidePanel, BorderLayout.WEST);
		frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
		frame.getContentPane().add(topPanel, BorderLayout.NORTH);
		topPanel.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		//JPanel sidePanel = new JPanel();
		
		
		//JPanel userPanel = new JPanel();
		//sidePanel.add(userPanel, BorderLayout.NORTH);
		
		//JLabel userHeader = new JLabel("User Name");
		//userPanel.add(userHeader);
		
		//JPanel friendsPanel = new JPanel();
		//sidePanel.add(friendsPanel, BorderLayout.CENTER);
		
		//JLabel friendsHeader = new JLabel("Friends");
		//friendsPanel.add(friendsHeader);
		
		//JPanel topPanel = new JPanel();
		
		//
		//topPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		//JLabel appNameHeader = new JLabel("Facebook");
		//topPanel.add(appNameHeader);
		
		//JPanel MenuBarRightPanel = new JPanel();
		//MenuBarRightPanel.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		//FlowLayout fl_MenuBarRightPanel = (FlowLayout) MenuBarRightPanel.getLayout();
		//fl_MenuBarRightPanel.setAlignment(FlowLayout.RIGHT);
		//topPanel.add(MenuBarRightPanel);
		
		//JButton settingsButton = new JButton("Settings");
		//MenuBarRightPanel.add(settingsButton);
		
		//JButton signoutButton = new JButton("Signout");
		//MenuBarRightPanel.add(signoutButton);
		
		//JPanel centerPanel = new JPanel();
		
		//centerPanel.setLayout(new BorderLayout(0, 0));
		
		//JPanel newPostPanel = new JPanel();
		//centerPanel.add(newPostPanel, BorderLayout.NORTH);
		//newPostPanel.setLayout(new BorderLayout(0, 0));
		
		//JLabel newPostHeader = new JLabel("Make Post");
		//newPostPanel.add(newPostHeader, BorderLayout.NORTH);
		
		//JTextPane newPostTextPane = new JTextPane();
		//newPostTextPane.setText("Whats on your mind?");
		//newPostPanel.add(newPostTextPane, BorderLayout.CENTER);
		
		//JButton newPostButton = new JButton("Post");
		//newPostPanel.add(newPostButton, BorderLayout.EAST);
		
		//Component verticalStrut = Box.createVerticalStrut(20);
		//newPostPanel.add(verticalStrut, BorderLayout.SOUTH);
		
		//JPanel newsFeedWrapperPanel = new JPanel();
		//centerPanel.add(newsFeedWrapperPanel, BorderLayout.CENTER);
		//newsFeedWrapperPanel.setLayout(new BorderLayout(0, 0));
		
		//JLabel newsFeedHeader = new JLabel("News Feed");
		//newsFeedWrapperPanel.add(newsFeedHeader, BorderLayout.NORTH);
		
		//JPanel newsFeedPanel = new JPanel();
		//newsFeedWrapperPanel.add(newsFeedPanel, BorderLayout.CENTER);
		//newsFeedPanel.setLayout(new GridLayout(2, 1, 0, 0));
		
		//JPanel examplePostPanel = new JPanel();
		//newsFeedPanel.add(examplePostPanel);
		//examplePostPanel.setLayout(new BorderLayout(0, 0));
		
		//JLabel user = new JLabel("User Posting");
		//examplePostPanel.add(user, BorderLayout.NORTH);
		
		//JTextPane userPostTextPane = new JTextPane();
		//userPostTextPane.setText("Example post by some creative human being!");
		//examplePostPanel.add(userPostTextPane, BorderLayout.CENTER);
	}

}
