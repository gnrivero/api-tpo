package gui.forms;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import controller.Sistema;
import excepciones.NegocioException;
import model.TipoDeReclamo;
import view.ReclamoView;

public class TableroGui extends JFrame {
	
	private List<ReclamoView> reclamos = new ArrayList<ReclamoView>();
		
	public void cargarReclamos(){
		
		List<TipoDeReclamo> tiposDeReclamo = Sistema.getInstance().getUsuarioLogueado().getRol().getTiposDeReclamo();
		
		try {
			for( TipoDeReclamo tipo : tiposDeReclamo){		
				reclamos.addAll(Sistema.getInstance().obtenerReclamosPorTipo(tipo));
			}		
		} catch (NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}