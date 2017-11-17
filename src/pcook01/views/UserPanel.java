package pcook01.views;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class UserPanel extends JPanel {
	private JLabel userHeader;
	
	public UserPanel () {
		userHeader = new JLabel("User Name");
		this.add(userHeader);
	}
}
