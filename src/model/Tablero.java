package model;

import java.util.List;

import model.reclamo.Reclamo;
import observer.Observado;


public class Tablero extends Observado {
	
	private String nombre;
	private List <Reclamo> reclamos;
	
	public List <Reclamo> cargarReclamo ()
	{
		return reclamos;
		
	}
	public void agregarReclamo(Reclamo reclamo)
	{
		
	}

}
