package pcook01.views;

import java.awt.FlowLayout;
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
		    settingsButton.setIcon(new ImageIcon("images/gear-icon.png"));
		    settingsButton.setBorder(null);
		} catch (Exception ex) {
		    System.out.println(ex);
		}
		
		add(settingsButton);
		add(signoutButton);
		
		//Set layout for buttons
		FlowLayout fl_MenuBarRightPanel = (FlowLayout) getLayout();
		fl_MenuBarRightPanel.setAlignment(FlowLayout.RIGHT);
	}
}
