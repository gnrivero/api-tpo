package app;

import javax.swing.JFrame;
import model.Tablero;
import vista.forms.JFormLogin;

public class App {

	public static void main(String[] args) {
		//Pantalla de login	
		Tablero modelo = new Tablero();
		JFrame f = new JFormLogin(modelo);
	}
}