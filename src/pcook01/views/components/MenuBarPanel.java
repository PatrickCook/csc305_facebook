package pcook01.views.components;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class MenuBarPanel extends JPanel {
	private JButton settingsButton;
	private JButton signoutButton;
	
	public MenuBarPanel () {
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		
		settingsButton = new JButton();
		signoutButton = new JButton("Signout");
		
		try {
		    settingsButton.setIcon(new ImageIcon("src/images/gear-icon.png"));
		    settingsButton.setBorder(null);
		} catch (Exception ex) {
		    System.out.println(ex);
		}
		
		add(signoutButton);
		add(settingsButton);
				
		//Set layout for buttons
		FlowLayout fl = (FlowLayout) getLayout();
		fl.setAlignment(FlowLayout.RIGHT);
	}
	
	public void addSignoutListener(ActionListener e) {
		signoutButton.addActionListener(e);
	}
	
	public void addSettingsListener(ActionListener e) {
		settingsButton.addActionListener(e);
	}
}
