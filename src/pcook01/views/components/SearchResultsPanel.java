package pcook01.views.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


import pcook01.models.User;
import singletons.FacebookDB;

public class SearchResultsPanel extends JPanel {
	private JPanel resultsPanel;
	private JLabel message;
	private ArrayList<User> results;
	private ActionListener selectUserActionListener;

	
	public SearchResultsPanel() {
		setLayout(new BorderLayout());
		
		resultsPanel = new JPanel();
		resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));

		results = new ArrayList<>();

		add(new JScrollPane(resultsPanel), BorderLayout.CENTER);
	}
	
	/* Removes all results and refreshes */
	public void loadSearchResults(String query) {
		FacebookDB db = FacebookDB.getInstance();
		results = db.getSearchResults(query);
		
		if (results.isEmpty()) {
			message = new JLabel("No search results");
			resultsPanel.add(message, BorderLayout.NORTH);
		}
		
		populateSearchResults();
	}
	
	public void populateSearchResults() {
		resultsPanel.removeAll();
		
		for (Iterator<User> i = results.iterator(); i.hasNext(); ) {
			User user = i.next();
			FriendPanel panel = new FriendPanel(user, selectUserActionListener);     
            resultsPanel.add(panel);
		}
		
		validate();
        repaint();
	}
	
	public void addSelectUserListener(ActionListener e) {
		selectUserActionListener = e;
	}
	
	 @Override
     public Dimension getPreferredSize() {
         return new Dimension(400, 300);
     }
}
