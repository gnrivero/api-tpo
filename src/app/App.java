package app;

import javax.swing.JFrame;


import gui.TableroPantalla;
import gui.forms.JFormLogin;


public class App {

	public static void main(String[] args) {
		
		TableroPantalla tablero = TableroPantalla.getInstance();
		tablero.setTitle("Tablero de Gestion de Reclamos");
		tablero.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//tablero.setEnabled(false);  // faltaría que JFormLogin le avise al Tablero que el login estuvo OK y que lo habilite con setEnabled(true)
		JFormLogin login = JFormLogin.getInstance();
		
	}
}