package pcook01.views.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import singletons.Decorator;

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
}
 