package gui;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.Sistema;
import gui.forms.JFormLogin;

public class TableroPantalla extends JFrame {
	
	private static final long serialVersionUID = -1088336668792687527L;
	
	//Singleton
	private static TableroPantalla tableroPantalla = null;
		
	private TableroPantalla(){
		configurar();
		eventos();
	}
	
	public static TableroPantalla getInstance(){
		if (tableroPantalla == null)
			tableroPantalla = new TableroPantalla();
		
		return tableroPantalla;
	}
	//Singleton
	private JLabel usuarioLogueado;
	
	public JLabel getUsuarioLogueado() {
		return usuarioLogueado;
	}

	public void setUsuarioLogueado(JLabel usuarioLogueado) {
		this.usuarioLogueado = usuarioLogueado;
	}
	
	
	private void configurar(){
		
		Container container = this.getContentPane();
		container.setLayout(null);
		
		
		usuarioLogueado = new JLabel("");
		usuarioLogueado.setBounds(10, 10, 100, 30);
		container.add(usuarioLogueado);
		
		if(Sistema.getInstance().getUsuarioLogueado() == null){
			JFormLogin login = new JFormLogin(Sistema.getInstance().getTablero());
		}
		
		this.setSize(800, 600);
		this.setVisible(true);
	}
	
	private void eventos(){
		
	}
}