package gui.forms;

import javax.swing.JFrame;

import observer.IObservador;

public abstract class JFormBase extends JFrame implements IObservador{

	private static final long serialVersionUID = 1L;
	
	//protected static Tablero modelo;
	
	public JFormBase(){
		//this.modelo = modelo;
		//this.modelo.agregarObservador(this);
		this.setSize(640, 480);
		this.setLocationRelativeTo(null);
		this.setTitle("JFormBase");
	}	
	/*
	protected Tablero getModelo() {
		return modelo;
	}*/

}
