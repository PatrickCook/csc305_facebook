package pcook01.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TopPanel extends JPanel {
	private JLabel appNameHeader;
	private MenuBarPanel menuBarPanel;
	
	public TopPanel () {
		 appNameHeader = new JLabel("Facebook");
		 appNameHeader.setBorder(new EmptyBorder(0,10,0,0));
		 
		 menuBarPanel = new MenuBarPanel();
		 
		 Decorator.setBoldWithSize(appNameHeader, 24);
		 appNameHeader.setForeground(Color.WHITE);
		 
		 setLayout(new GridLayout(0, 2, 0, 0));
		 this.add(appNameHeader);
		 this.add(menuBarPanel);
	}
}
 