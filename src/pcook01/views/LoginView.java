package pcook01.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import singletons.Decorator;

public class LoginView extends JPanel {

	private JLabel appNameHeader;
	private JTextField userTextField;
	private JPasswordField userPassField;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JButton loginButton;
	private JButton cancelButton;
	private JButton signupButton;

	public LoginView() {
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
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		panel.add(userTextField, gbc);

		passwordLabel = new JLabel("Password: ");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		panel.add(passwordLabel, gbc);

		userPassField = new JPasswordField(20);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		panel.add(userPassField, gbc);

		loginButton = new JButton("Login");
		cancelButton = new JButton("Cancel");
		signupButton = new JButton("Signup");


		JPanel buttons = new JPanel();
		buttons.add(loginButton);
		buttons.add(cancelButton);
		buttons.add(signupButton);
		
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
	
	public void addLoginListener(ActionListener a) {
		loginButton.addActionListener(a);
	}
	
	public void addCancelListener(ActionListener a) {
		cancelButton.addActionListener(a);
	}
	
	public void addSignupListener(ActionListener a) {
		signupButton.addActionListener(a);
	}
	
	public String getUsernameInput() {
		return userTextField.getText();
	}
	
	public String getPasswordInput() {
		return new String(userPassField.getPassword());
	}
}
