package pcook01.views;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SignupView extends JPanel {

	public SignupView () {
		setLayout(new BorderLayout());
		JLabel label = new JLabel("Signup View");
		
		add(label, BorderLayout.CENTER);
		
	}
}