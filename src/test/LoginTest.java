package test;

import controller.Sistema;

public class LoginTest {

	public static void main(String[] args) {
		
		
		Sistema.getInstance().loguearUsuario("jperez", "123456");
		
	}

}
