package app;

import javax.swing.JFrame;

import controller.Sistema;
import model.Tablero;
import vista.forms.JFormLogin;

public class App {

	public static void main(String[] args) {
		//Pantalla de login	
		Tablero modelo = Sistema.getInstance().getTablero();
		JFrame f = new JFormLogin(modelo);
	}
}