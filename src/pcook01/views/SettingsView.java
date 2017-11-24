package pcook01.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import pcook01.models.User;
import singletons.Decorator;

public class SettingsView extends JPanel {
	private User user;
	private JLabel appNameHeader;
	private JTextField userTextField;
	private JPasswordField userPassField;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JButton backButton;
	private JButton saveButton;
	private JButton deactivateButton;

	public SettingsView(User user) {
		this.user = user;
		
		JPanel wrapper = new JPanel(new BorderLayout());
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.fill = GridBagConstraints.HORIZONTAL;

		usernameLabel = new JLabel("Username: ");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		panel.add(usernameLabel, gbc);

		userTextField = new JTextField(20);
		userTextField.setText(user.getUsername());
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		panel.add(userTextField, gbc);

		passwordLabel = new JLabel("Change Password: ");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		panel.add(passwordLabel, gbc);

		userPassField = new JPasswordField(20);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		panel.add(userPassField, gbc);

		deactivateButton = new JButton("Deactivate");
		backButton = new JButton("Back");
		saveButton = new JButton("Save");

		JPanel buttons = new JPanel();
		buttons.add(saveButton);
		buttons.add(backButton);
		buttons.add(deactivateButton);

		appNameHeader = new JLabel("Facebook", JLabel.CENTER);
		Decorator.setBoldWithSize(appNameHeader, 32);

		wrapper.add(appNameHeader, BorderLayout.PAGE_START);
		wrapper.add(panel, BorderLayout.CENTER);
		wrapper.add(buttons, BorderLayout.PAGE_END);
		wrapper.setPreferredSize(new Dimension(400, 200));
		wrapper.setMaximumSize(new Dimension(400, 200));
		wrapper.setMinimumSize(new Dimension(400, 200));

		add(wrapper);
	}

	public void addBackListener(ActionListener a) {
		backButton.addActionListener(a);
	}

	public void addSaveListener(ActionListener a) {
		saveButton.addActionListener(a);
	}
	
	public void addDeactivateListener(ActionListener a) {
		deactivateButton.addActionListener(a);
	}

	public String getUsernameInput() {
		return userTextField.getText();
	}

	public String getPasswordInput() {
		return new String(userPassField.getPassword());
	}

}
