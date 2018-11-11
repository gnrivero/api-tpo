package gui.forms;

import javax.swing.JFrame;

import model.Tablero;
import observer.IObservador;

public abstract class JFormBase extends JFrame implements IObservador{

	private static final long serialVersionUID = 1L;
	
	private Tablero modelo;
	
	public JFormBase(Tablero modelo){
		this.modelo = modelo;
		this.modelo.agregarObservador(this);
		this.setSize(640, 480);
		this.setLocationRelativeTo(null);
		this.setTitle("JFormBase");
	}	
	
	protected Tablero getModelo() {
		return modelo;
	}

}
