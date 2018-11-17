package test;

import java.util.ArrayList;
import java.util.List;

import dao.DAOhelper;

public class DaoHelperTest {
	
	public static void main(String[] args){
		
		List<String> palabras = new ArrayList<String>();
		palabras.add("hola");
		palabras.add("comova");
		
		List<Integer> enteros = new ArrayList<Integer>();
		enteros.add(1234556);
		enteros.add(6543211);
		
		System.out.println(DAOhelper.escribirSentenciaIn(palabras));
		System.out.println(DAOhelper.escribirSentenciaIn(enteros));
		
		System.exit(0);
	}

}
