package gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.Sistema;
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
	private JButton btnCargarReclamo, btnCargarVariosReclamos;
	private JButton btnAdministrarUsuarios, btnAdminClientes;
		
	public JLabel getUsuarioLogueado() {
		return usuarioLogueado;
	}

	public void setUsuarioLogueado(JLabel usuarioLogueado) {
		this.usuarioLogueado = usuarioLogueado;
	}
		
	private void configurar(){
		
		container = this.getContentPane();
		container.setLayout(null);
		
		usuarioLogueado = new JLabel();
		usuarioLogueado.setBounds(10, 10, 100, 30);
		container.add(usuarioLogueado);
		
		btnCargarReclamo = new JButton("Cargar Reclamo");
		btnCargarReclamo.setBounds(10, 40, 150, 30);
		container.add(btnCargarReclamo);
		
		btnCargarVariosReclamos = new JButton("Cargar Varios Reclamos");
		btnCargarVariosReclamos.setBounds(170, 40, 180, 30);
		container.add(btnCargarVariosReclamos);
		
		btnAdministrarUsuarios = new JButton("Admin. Usuarios");
		btnAdministrarUsuarios.setBounds(360, 40, 150, 30);
		container.add(btnAdministrarUsuarios);
		
		btnAdminClientes = new JButton("Admin. Clientes");
		btnAdminClientes.setBounds(515, 40, 150, 30);
		container.add(btnAdminClientes);
		
//		if(Sistema.getInstance().getUsuarioLogueado() == null){
//			JFormLogin login = new JFormLogin(Sistema.getInstance().getTablero());
//		}
		
		this.setSize(800, 600);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
	private void eventos(){
		
		btnAdminClientes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ClientePantalla clientePantalla = ClientePantalla.getInstance();
				clientePantalla.moveToFront();
				clientePantalla.setVisible(true);				
				container.add(clientePantalla);
			}
		});
		
		btnCargarReclamo.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ReclamoPantalla reclamoPantalla = ReclamoPantalla.getInstance();
				reclamoPantalla.moveToFront();
				reclamoPantalla.setVisible(true);
				container.add(reclamoPantalla);
			}
			
		});
		
	}

	@Override
	public void actualizar() {
		this.getUsuarioLogueado().setText("Hola " + Sistema.getInstance().getUsuarioLogueado().getUsername());	
	}
}