package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.reclamo.Reclamo;

public class ReclamoCompuestoCache {
	
	private static Map<Integer, List<Reclamo>> cache = new HashMap<>();
	
	//Metodos para Key de reclamo compuesto
	public void agregarReclamoCompuestoKey(Integer nroReclamoCompuesto){
		if(nroReclamoCompuesto!= null && !cache.containsKey(nroReclamoCompuesto))
			cache.put(nroReclamoCompuesto, new ArrayList<Reclamo>());
	}
	
	public void quitarReclamoCompuestoKey(Integer nroReclamoCompuesto){	
		cache.remove(nroReclamoCompuesto);
	}
	
	public void agregarReclamoSimple(Integer nroReclamoCompuesto, Reclamo reclamo){				
		cache.get(nroReclamoCompuesto).add(reclamo);		
	}
	
	public boolean existeReclamoCompuesto(Integer nroReclamoCompuesto){
		return cache.containsKey(nroReclamoCompuesto);
	}
	
	
	//Metodos para reclamos simples
	
//	public Reclamo obtenerReclamoSimple(Integer nroReclamo){
//		Iterator<Entry<Integer, Map<Integer, Reclamo>>> eit = cache.entrySet().iterator();
//	    while (eit.hasNext()) {
//	        Map.Entry<Integer, Map<Integer, Reclamo>> pair = (Map.Entry<Integer, Map<Integer, Reclamo>>) eit.next();	        
//	        Map<Integer, Reclamo> valor = (Map<Integer, Reclamo>) pair.getValue();	        
//	        if(valor.containsKey(nroReclamo))
//	        	return valor.get(nroReclamo);
//	    }
//	    return null;
//	}
	
	public List<Reclamo> obtenerReclamosSimplesPorReclamoCompuesto(Integer nroReclamoCompuesto){
	    return cache.get(nroReclamoCompuesto);
	}
	
}