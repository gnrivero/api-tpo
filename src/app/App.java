package app;

import javax.swing.JFrame;

import gui.TableroPantalla;

public class App {

	public static void main(String[] args) {
		
		TableroPantalla tablero = TableroPantalla.getInstance();
		tablero.setTitle("Tablero de Gestion de Reclamos");
		tablero.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
	}
}