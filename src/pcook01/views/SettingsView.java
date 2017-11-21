package pcook01.views;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SettingsView extends JPanel {

	public SettingsView () {
		setLayout(new BorderLayout());
		JLabel label = new JLabel("Settings View");
		
		add(label, BorderLayout.CENTER);
		
	}
}
