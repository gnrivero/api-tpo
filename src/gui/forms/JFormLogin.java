package gui.forms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import controller.Sistema;
import excepciones.NegocioException;

public class JFormLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	
	//Singleton
	private static JFormLogin login = null;
		
	private JFormLogin() {
		super();
		//Sistema.getInstance().agregarObservador(this);
		configurar();
		eventos();
	}	

	private void configurar() {
		crearTopPanel();
		crearBotPanel();
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		frame.setAlwaysOnTop(true);
		frame.add(top);
		frame.add(bot);
		frame.setTitle("Login");
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

	public static JFormLogin getInstance(){
		if (login == null)
			login = new JFormLogin();
		return login;
	}
	//Singleton
	
	private JPanel top, bot;
	private JFrame frame;
	private JTextField txtUser = new JTextField();
	private JPasswordField txtPass = new JPasswordField();
	private JButton btnLogin = new JButton("LOGIN");
	private JButton btnExit = new JButton("EXIT");
	
	public void crearTopPanel() {
		top = new JPanel();
		top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
		top.add(new JLabel("User:"));
		top.add(txtUser);
		top.add(new JLabel("Password:"));
		top.add(txtPass);
	}
	
	public void crearBotPanel() {
		bot = new JPanel();
		bot.setLayout(new BoxLayout(bot, BoxLayout.X_AXIS));
		bot.add(btnLogin);
		bot.add(btnExit);
	}
	
	private void eventos() {
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed (ActionEvent evt) {
				try {			
					Sistema.getInstance().loguearUsuario(txtUser.getText(), txtPass.getText());	// ver si se puede usar otro método para recuperar el texto del password								
					System.out.println("Hola " + Sistema.getInstance().getUsuarioLogueado().getUsername());
					frame.dispose();
				} catch (NegocioException e) {
					//System.out.println("Error al hacer login");
					//e.printStackTrace();
					frame.setAlwaysOnTop(false);
					System.out.println("Error de login");
			        JOptionPane.showMessageDialog(null, "Usuario o password incorrectos", "ERROR: Login", JOptionPane.ERROR_MESSAGE);
			        frame.setAlwaysOnTop(true);
				}
			}
		});
		
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e){
				System.exit(0);
			}
		});
	}
}
