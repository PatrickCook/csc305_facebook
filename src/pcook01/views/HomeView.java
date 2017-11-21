package pcook01.views;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import pcook01.models.User;
import pcook01.views.components.CenterPanel;
import pcook01.views.components.SidePanel;
import pcook01.views.components.TopPanel;

public class HomeView extends JPanel {
	private TopPanel topPanel;
	private SidePanel sidePanel;
	private CenterPanel centerPanel;
	
	public HomeView () {
		topPanel = new TopPanel();
		sidePanel = new SidePanel();
		centerPanel = new CenterPanel();
		
		setLayout(new BorderLayout(0,0));
		
		add(sidePanel, BorderLayout.WEST);
		add(centerPanel, BorderLayout.CENTER);
		add(topPanel, BorderLayout.NORTH);
	}
}
