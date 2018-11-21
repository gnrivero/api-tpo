package app;

import java.awt.Dimension;

import javax.swing.JFrame;


import gui.TableroPantalla;
import gui.forms.JFormLogin;


public class App {

	public static void main(String[] args) {
		
		TableroPantalla tablero = TableroPantalla.getInstance();
		tablero.setTitle("Tablero de Gestion de Reclamos");
		tablero.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tablero.setExtendedState(JFrame.MAXIMIZED_BOTH);
		tablero.setMinimumSize(new Dimension(800, 600));
		tablero.isResizable();
		tablero.setVisible(true);
		JFormLogin.getInstance(tablero);		
	}
}