package pcook01.views;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TopPanel extends JPanel {
	private JLabel appNameHeader;
	private MenuBarPanel menuBarPanel;
	
	public TopPanel () {
		 appNameHeader = new JLabel("Facebook");
		 menuBarPanel = new MenuBarPanel();
		 
		 setLayout(new GridLayout(0, 2, 0, 0));
		 this.add(appNameHeader);
		 this.add(menuBarPanel);
	}
}
 