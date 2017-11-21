package pcook01.views;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class UserView extends JPanel {

	public UserView () {
		setLayout(new BorderLayout());
		JLabel label = new JLabel("User View");
		
		add(label, BorderLayout.CENTER);
		
	}
}