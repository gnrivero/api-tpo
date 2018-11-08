package gui;

import java.awt.Container;

import javax.swing.JFrame;

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
	
	private void configurar(){
		
		Container container = this.getContentPane();
		container.setLayout(null);
		
		
		
		this.setSize(800, 600);
		this.setVisible(true);
	}
	
	private void eventos(){
		
	}
}