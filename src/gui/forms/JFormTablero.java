package gui.forms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import model.Tablero;

public class JFormTablero extends JFormBase {
	
	public JFormTablero(Tablero modelo) {
		super(modelo);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Tablero");
		this.setSize(300, 300);
		JMenuBar menu = new JMenuBar();
		
		JMenu inicio = new JMenu("Inicio");
		menu.add(inicio);
		
		JMenuItem opcNuevo = new JMenuItem("Nuevo reclamo...");
		inicio.add(opcNuevo);
		opcNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// abrir formulario para nuevo reclamo del tipo que corresponda según el rol
			}
		});
		
		JMenuItem opcSalir = new JMenuItem("Salir");
		inicio.add(opcSalir);
		opcSalir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				System.exit(0);
			}
		});
		
		JMenu ayuda = new JMenu("Ayuda");
		menu.add(ayuda);
		
		JMenuItem acercaDe = new JMenuItem("Acerca de...");
		ayuda.add(acercaDe);
		acercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				JOptionPane.showMessageDialog(null, "Grupo 8:\n María Isabel Quevedo Pasquini\n Gonzalo Rivero\n Emilio Delgado\n Nazareno Agustín Rodríguez", "Aplicaciones Interactivas", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		this.setJMenuBar(menu);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void actualizar(String dato) {
		// TODO Auto-generated method stub
		
	}
	
}
