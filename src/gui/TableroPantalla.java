package gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import controller.Sistema;
import gui.forms.JFormLogin;
import observer.IObservador;

public class TableroPantalla extends JFrame implements IObservador  {
	
	private static final long serialVersionUID = -1088336668792687527L;

	//Singleton
	private static TableroPantalla tableroPantalla = null;
		
	private TableroPantalla(){
		Sistema.getInstance().agregarObservador(this);		
		configurar();
		eventos();		
	}
	
	public static TableroPantalla getInstance(){
		if (tableroPantalla == null)
			tableroPantalla = new TableroPantalla();
		
		return tableroPantalla;
	}
	//Singleton
	
	private Container container;
	
	private JLabel usuarioLogueado;
	private JButton btnCargarReclamo;
	private JButton btnAdministrarUsuarios, btnAdminClientes, btnAdminProducto;
	
	private JMenuBar menu = new JMenuBar();
	private JMenu inicio = new JMenu("Inicio");
	private JMenuItem opcNuevo = new JMenuItem("Nuevo reclamo...");
	private JMenuItem opcLogout = new JMenuItem("Logout");
	private JMenu ayuda = new JMenu("Ayuda");
	private JMenuItem acercaDe = new JMenuItem("Acerca de...");
	
	private ClientePantalla clientePantalla;
	
	public Container getFrameContainer(){
		return container;
	}

	public JLabel getUsuarioLogueado() {
		return usuarioLogueado;
	}

	public void setUsuarioLogueado(JLabel usuarioLogueado) {
		this.usuarioLogueado = usuarioLogueado;
	}
		
	private void configurar(){
		
		container = getLayeredPane();
		container.setLayout(null);

		usuarioLogueado = new JLabel();
		usuarioLogueado.setBounds(10, 10, 200, 30);
		container.add(usuarioLogueado);
		
		btnCargarReclamo = new JButton("Cargar Reclamo");
		btnCargarReclamo.setBounds(10, 40, 150, 30);
		container.add(btnCargarReclamo);
		
		btnAdministrarUsuarios = new JButton("Admin. Usuarios");
		btnAdministrarUsuarios.setBounds(180, 40, 180, 30);
		container.add(btnAdministrarUsuarios);
		
		btnAdminClientes = new JButton("Admin. Clientes");
		btnAdminClientes.setBounds(400, 40, 150, 30);
		container.add(btnAdminClientes);
		
		btnAdminProducto = new JButton("Admin. Producto");
		btnAdminProducto.setBounds(600, 40, 150, 30);
		container.add(btnAdminProducto);
		
		clientePantalla = ClientePantalla.getInstance();
		container.add(clientePantalla);
		
//		if(Sistema.getInstance().getUsuarioLogueado() == null){
//			JFormLogin login = new JFormLogin(Sistema.getInstance().getTablero());
//		}
		menu.add(inicio);
		inicio.add(opcNuevo);
		inicio.add(opcLogout);
		menu.add(ayuda);
		ayuda.add(acercaDe);
		this.setJMenuBar(menu);
		
		this.setVisible(true);
		this.setEnabled(false);
		this.setLocationRelativeTo(null);
	}
	
	private void eventos(){
		
		btnAdminClientes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				clientePantalla.setVisible(true);
			}
		});
		
		btnCargarReclamo.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ReclamoPantalla reclamoPantalla = new ReclamoPantalla();			
				reclamoPantalla.setVisible(true);
				container.add(reclamoPantalla);	
			}
		});
		
		opcNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// abrir formulario para nuevo reclamo del tipo que corresponda según el rol
			}
		});
		
		opcLogout.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				//System.exit(0);
				System.out.println("Chau " + Sistema.getInstance().getUsuarioLogueado().getUsername());
				Sistema.getInstance().desloguearUsuario();
				tableroPantalla.setEnabled(false);
				JFormLogin.getInstance(tableroPantalla);
				
			}
		});
		
		acercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				JOptionPane.showMessageDialog(null, "Grupo 8:\n María Isabel Quevedo Pasquini\n Gonzalo Rivero\n Emilio Delgado\n Nazareno Agustín Rodríguez", "AI", JOptionPane.INFORMATION_MESSAGE);
			}
		});
	
	}
	
	@Override
	public void actualizar() {
		this.getUsuarioLogueado().setText("Hola " + Sistema.getInstance().getUsuarioLogueado().getUsername());
	}
}