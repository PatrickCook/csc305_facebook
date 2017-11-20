package pcook01.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class NewPostPanel extends JPanel {
	private JLabel newPostHeader;
	private JTextPane newPostTextPane;
	private JButton newPostButton;
	private Component verticalStrut = Box.createVerticalStrut(20);
	
	public NewPostPanel () {
		setLayout(new BorderLayout(0, 0));
		
		newPostHeader = new JLabel("Make Post");
		newPostHeader.setBorder(new EmptyBorder(0,0,10,0));
		
		Decorator.setBoldWithSize(newPostHeader, 16);
		newPostTextPane = new JTextPane();
		newPostTextPane.setText("Whats on your mind?");
		newPostButton = new JButton("Post");
		
		add(newPostHeader, BorderLayout.NORTH);
		add(newPostTextPane, BorderLayout.CENTER);
		add(newPostButton, BorderLayout.EAST);
		add(verticalStrut, BorderLayout.SOUTH);
	}
}
