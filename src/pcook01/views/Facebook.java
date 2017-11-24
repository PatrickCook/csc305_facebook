package pcook01.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import pcook01.controllers.HomeController;
import pcook01.controllers.LoginController;
import pcook01.controllers.ProfileController;
import pcook01.controllers.SettingsController;
import pcook01.controllers.SignupController;

import pcook01.models.User;

public class Facebook {
	
	private JFrame frame;
	private JPanel rootPanel;
	
	private LoginView loginView;
	private LoginController loginController;
	
	private SignupView signupView;
	private SignupController signupController;
	
	private HomeView homeView;
	private HomeController homeController;
	
	private SettingsView settingsView;
	private SettingsController settingsController;
	
	private ProfileView profileView;
	private ProfileController profileController;
	
	private static User client, selectedUser;
	private State state;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Facebook window = new Facebook();
					window.frame.setVisible(true);
					window.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Facebook() {
		state = State.LOGIN;
		
		frame = new JFrame();
		frame.setSize(1000, 800);
		rootPanel = new JPanel();
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		frame.getContentPane().removeAll();
		
		switch (state) {
		case LOGIN:
			client = new User();
			loginView = new LoginView();
			loginController = new LoginController(this, client, loginView);
			rootPanel = loginView;
			break;
		case SIGNUP:
			client = new User();
			signupView = new SignupView();
			signupController = new SignupController(this, client, signupView);
			rootPanel = signupView;
			break;
		case HOME:
			selectedUser = new User();
			homeView = new HomeView(client);
			homeController = new HomeController(this, client, selectedUser, homeView);
			rootPanel = homeView;
			break;
		case USER_PROFILE:
			System.out.println(selectedUser.getUsername());
			profileView = new ProfileView(client, selectedUser);
			profileController = new ProfileController(this, client, selectedUser, profileView);
			rootPanel = profileView;
			break;
		case SETTINGS:
			settingsView = new SettingsView();
			rootPanel = settingsView;
			break;
		case EXIT:
			System.out.println("Facebook is closing.");
			System.exit(0);
		default:
			System.err.println("error: Unknown application state.");
			System.exit(-1);
		}
		
		frame.getContentPane().add(rootPanel);
		frame.revalidate();
		frame.repaint();
	}
	
	public void changeState(State newState) {
		state = newState;
		initialize();
	}
	
	public enum State {
		LOGIN, SIGNUP, HOME, SETTINGS, USER_PROFILE, EXIT;
	}

}
