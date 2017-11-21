package pcook01.views.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import singletons.Decorator;

public class NewsFeedPostPanel extends JPanel {
	private JLabel user;
	private JTextArea userPost;
	private JPanel postAddOns;
	
	private static String defaultUser = "Default User";
	private static String defaultPost = "Default post, pretty creative right?!";
	
	public NewsFeedPostPanel() {
		this(defaultUser, defaultPost);
	}
	
	public NewsFeedPostPanel (String userStr, String userPostStr) {
		setLayout(new BorderLayout(0, 0));
		setBackground(Color.white);
		
		postAddOns = new JPanel();
		postAddOns.setLayout(new BorderLayout());
		postAddOns.setBackground(Color.white);
		
		user = new JLabel(userStr);
		user.setBorder(new EmptyBorder(0,0,5,0));
		
		userPost = new JTextArea();
		userPost.setText(userPostStr);
		
		ImageIcon image = new ImageIcon("src/images/thumbs-up-icon.png");
		JButton likeButton = new JButton("Like", image);
		postAddOns.add(likeButton, BorderLayout.WEST);
		
		Decorator.setBoldWithSize(user,  14);
		Decorator.setFontSize(userPost, 12);
		
		add(user, BorderLayout.NORTH);
		add(userPost, BorderLayout.CENTER);
		add(postAddOns, BorderLayout.SOUTH);

		
	}
	
	 public Dimension getPreferredSize() {
         return new Dimension(getWidth(), 100);
     }
 }
