package pcook01.views;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class MenuBarPanel extends JPanel {
	private JButton settingsButton;
	private JButton signoutButton;
	
	public MenuBarPanel () {
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		
		settingsButton = new JButton("Settings");
		signoutButton = new JButton("Signout");
		
		//Set layout for buttons
		FlowLayout fl_MenuBarRightPanel = (FlowLayout) getLayout();
		fl_MenuBarRightPanel.setAlignment(FlowLayout.RIGHT);
	}
}
