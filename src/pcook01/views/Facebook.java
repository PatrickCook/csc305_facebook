package pcook01.views;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import pcook01.controllers.HomeController;
import pcook01.controllers.LoginController;
import pcook01.controllers.SettingsController;
import pcook01.controllers.SignupController;
import pcook01.controllers.UserController;
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
	
	private UserView userView;
	private UserController userController;
	
	private static User user;
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
			user = new User();
			loginView = new LoginView();
			loginController = new LoginController(this, user, loginView);
			rootPanel = loginView;
			break;
		case SIGNUP:
			user = new User();
			signupView = new SignupView();
			signupController = new SignupController(this, user, signupView);
			rootPanel = signupView;
			break;
		case HOME:
			homeView = new HomeView();
			rootPanel = homeView;
			break;
		case USER:
			userView = new UserView();
			rootPanel = userView;
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
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public enum State {
		LOGIN, SIGNUP, HOME, SETTINGS, USER, EXIT;
	}

}
