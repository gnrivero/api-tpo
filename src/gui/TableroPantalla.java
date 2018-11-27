package gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import controller.Sistema;
import excepciones.NegocioException;
import gui.forms.JFormLogin;
import model.Modulo;
import model.Permiso;
import model.TipoDeReclamo;
import model.Usuario;
import observer.IObservador;
import view.ReclamoView;

public class TableroPantalla extends JFrame implements IObservador  {
	
	private static final long serialVersionUID = -1088336668792687527L;

	//Singleton
	private static TableroPantalla tableroPantalla = null;
		
	private TableroPantalla(){
		Sistema.getInstance().agregarObservador(this);		
		configurar();
		eventos();		
	}
	
	public static TableroPantalla getInstance(){
		if (tableroPantalla == null)
			tableroPantalla = new TableroPantalla();
		
		return tableroPantalla;
	}
	//Singleton
	
	private Container container;
	
	private JLabel usuarioLogueado, lblReclamosIngresados, lblReclamosEnTratamiento, lblReclamosSolucionados;
	
	private JButton btnComenzarTratamiento, btnPasarAsolucionado, btnPasarACerrado, btnEditarReclamo;
	
	private JList<ReclamoView> lstReclamosIngresados;
	private JList<ReclamoView> lstReclamosEnTratamiento;
	private JList<ReclamoView> lstReclamosSolucionados;
	
	private DefaultListModel<ReclamoView> reclamosIngresadosModel = new DefaultListModel<ReclamoView>();
	private DefaultListModel<ReclamoView> reclamosEnTratamientoModel = new DefaultListModel<ReclamoView>();
	private DefaultListModel<ReclamoView> reclamosSolucionadosModel = new DefaultListModel<ReclamoView>();
	
	private JMenuBar barraMenu = new JMenuBar();
	private JMenuItem opcNuevoReclamo, opcAdminUsuarios, opcAdminClientes, opcAdminProductos, opcLogout, opcAcercaDe; 
	private JMenu ayuda = new JMenu("Ayuda");

	
	private ClientePantalla clientePantalla;
	private UsuarioPantalla usuarioPantalla;
	private ProductoPantalla productoPantalla;
	
	public Container getFrameContainer(){
		return container;
	}

	public JLabel getUsuarioLogueado() {
		return usuarioLogueado;
	}

	public void setUsuarioLogueado(JLabel usuarioLogueado) {
		this.usuarioLogueado = usuarioLogueado;
	}
		
	private void configurar(){
		
		container = getLayeredPane();
		container.setLayout(null);
				
		// ---- Menu Bar ----
		opcNuevoReclamo = new JMenuItem("Nuevo Reclamo");
		opcNuevoReclamo.setHorizontalAlignment(SwingConstants.LEFT);
		
		opcAdminUsuarios = new JMenuItem("Admin. Usuarios");
		opcAdminUsuarios.setHorizontalAlignment(SwingConstants.LEFT);
		
		opcAdminClientes = new JMenuItem("Admin. Clientes");
		opcAdminClientes.setHorizontalAlignment(SwingConstants.LEFT);
		
		opcAdminProductos = new JMenuItem("Admin. Productos");
		opcAdminProductos.setHorizontalAlignment(SwingConstants.LEFT);
		
		opcAcercaDe = new JMenuItem("Acerca de");
		opcAcercaDe.setHorizontalAlignment(SwingConstants.LEFT);
		
		opcLogout = new JMenuItem("Salir");
		opcLogout.setHorizontalAlignment(SwingConstants.LEFT);
		
		barraMenu.add(opcNuevoReclamo);		
		barraMenu.add(opcAdminUsuarios);
		barraMenu.add(opcAdminClientes);
		barraMenu.add(opcAdminProductos);
		barraMenu.add(opcAcercaDe);
			ayuda.add(opcAcercaDe);
		barraMenu.add(opcLogout);
				
		this.setJMenuBar(barraMenu);
		
		
		// ---- Usuario Logueado
		usuarioLogueado = new JLabel();
		usuarioLogueado.setBounds(30, 20, 200, 30);
		container.add(usuarioLogueado);
		
		// --- 
		
		lblReclamosIngresados = new JLabel("Ingresados");
		lblReclamosIngresados.setBounds(30, 165, 200, 30);
		container.add(lblReclamosIngresados);
		
		btnComenzarTratamiento = new JButton("Iniciar Tratamiento");
		btnComenzarTratamiento.setBounds(130, 165, 200, 30);
		container.add(btnComenzarTratamiento);
		
		lstReclamosIngresados = new JList<ReclamoView>();
		lstReclamosIngresados.setBounds(30, 200, 300, 400);
		lstReclamosIngresados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstReclamosIngresados.setModel(reclamosIngresadosModel);
		container.add(lstReclamosIngresados);
		
		lblReclamosEnTratamiento = new JLabel("En Tratamiento");
		lblReclamosEnTratamiento.setBounds(380, 165, 200, 30);
		container.add(lblReclamosEnTratamiento);
		
		btnPasarAsolucionado = new JButton("Solucionado");
		btnPasarAsolucionado.setBounds(530, 165, 150, 30);
		container.add(btnPasarAsolucionado);
		
		btnPasarACerrado = new JButton("Cerrado");
		btnPasarACerrado.setBounds(530, 130, 150, 30);
		container.add(btnPasarACerrado);		
		
		lstReclamosEnTratamiento = new JList<ReclamoView>();
		lstReclamosEnTratamiento.setBounds(380, 200, 300, 400);
		lstReclamosEnTratamiento.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstReclamosEnTratamiento.setModel(reclamosEnTratamientoModel);
		container.add(lstReclamosEnTratamiento);
		
		lblReclamosSolucionados = new JLabel("Solucionados");
		lblReclamosSolucionados.setBounds(730, 165, 200, 30);		
		container.add(lblReclamosSolucionados);
		
		lstReclamosSolucionados = new JList<ReclamoView>();
		lstReclamosSolucionados.setBounds(730, 200, 300, 400);		
		lstReclamosSolucionados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstReclamosSolucionados.setModel(reclamosSolucionadosModel);
		container.add(lstReclamosSolucionados);
		
		// ---
		
		clientePantalla = ClientePantalla.getInstance();
		container.add(clientePantalla, 1);
		
		usuarioPantalla = UsuarioPantalla.getInstance();
		container.add(usuarioPantalla);
		
		productoPantalla = ProductoPantalla.getInstance();
		container.add(productoPantalla);
		
		btnEditarReclamo = new JButton("Editar");	
		btnEditarReclamo.setBounds(380, 610, 150, 30);
		container.add(btnEditarReclamo);
		
		this.setVisible(true);
		this.setEnabled(false);
		this.setLocationRelativeTo(null);
	}
	
	private void eventos(){
		
		btnEditarReclamo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {				
				ReclamoView reclamo = lstReclamosEnTratamiento.getSelectedValue();
				ReclamoPantalla rp = new ReclamoPantalla(reclamo.getNroReclamo(), reclamo.getTipoDeReclamo());
				rp.setVisible(true);
				container.add(rp, 1);
			}
		});
		
		opcNuevoReclamo.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ReclamoPantalla reclamoPantalla = new ReclamoPantalla();			
				reclamoPantalla.setVisible(true);
				container.add(reclamoPantalla, 1);	
			}
		});

		opcAdminClientes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				clientePantalla.setVisible(true);
			}
		});
		
		opcAdminUsuarios.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				//UsuarioPantalla usuarioPantalla = new UsuarioPantalla();			
				usuarioPantalla.setVisible(true);
				//container.add(usuarioPantalla);
			}
		});

		opcAdminProductos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				productoPantalla.setVisible(true);
			}
		});
		
		opcLogout.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {			
				Sistema.getInstance().desloguearUsuario();
				checkPermisos();
				JFormLogin.getInstance(tableroPantalla);
			}
		});
		
		opcAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				JOptionPane.showMessageDialog(null, "Grupo 8:\n Maria Isabel Quevedo Pasquini\n Gonzalo Rivero\n Emilio Delgado\n Nazareno Agustin Rodriguez", "AI", JOptionPane.INFORMATION_MESSAGE);
			}
		});
				
		btnComenzarTratamiento.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					ReclamoView reclamo = lstReclamosIngresados.getSelectedValue();
					
					Sistema.getInstance().comenzarTratamientoReclamo(reclamo.getNroReclamo(), reclamo.getTipoDeReclamo());
					
					reclamosIngresadosModel.removeElement(reclamo);
					reclamosEnTratamientoModel.addElement(reclamo);					
					
				} catch (NegocioException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Cambio de estado", JOptionPane.ERROR_MESSAGE);
				}				
			}
		});
		
		btnPasarAsolucionado.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {			
				try {
					ReclamoView reclamo = lstReclamosEnTratamiento.getSelectedValue();
					
					Sistema.getInstance().marcarReclamoComoSolucionado(reclamo.getNroReclamo(), reclamo.getTipoDeReclamo());
					
					reclamosEnTratamientoModel.removeElement(reclamo);
					reclamosSolucionadosModel.addElement(reclamo);	
					
				} catch (NegocioException e1) {
			        JOptionPane.showMessageDialog(null, e1.getMessage(), "Cambio de estado", JOptionPane.ERROR_MESSAGE);
				}				
			}
		});
		
		btnPasarACerrado.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {			
				try {
					ReclamoView reclamo = lstReclamosEnTratamiento.getSelectedValue();
					
					Sistema.getInstance().cerrarReclamo(reclamo.getNroReclamo(), reclamo.getTipoDeReclamo());
					
					reclamosEnTratamientoModel.removeElement(reclamo);
					
				} catch (NegocioException e1) {
			        JOptionPane.showMessageDialog(null, e1.getMessage(), "Cambio de estado", JOptionPane.ERROR_MESSAGE);
				}				
			}
		});
	
	}
	
	private void completarListadosDeReclamos(List<TipoDeReclamo> tiposDeReclamos){
		
		try {
			
			List<ReclamoView> reclamos = Sistema.getInstance().obtenerReclamosPorTipo(tiposDeReclamos);
			
			reclamosIngresadosModel.clear();
			reclamosEnTratamientoModel.clear();
			reclamosSolucionadosModel.clear();
			
			for (ReclamoView reclamo : reclamos){
				
				switch(reclamo.getEstadoDeReclamo()){
					
					case INGRESADO:
						reclamosIngresadosModel.addElement(reclamo);
					break;
					case EN_TRATAMIENTO:
						reclamosEnTratamientoModel.addElement(reclamo);
					break;
					case SOLUCIONADO:
						reclamosSolucionadosModel.addElement(reclamo);
					break;
					default:
						//throw new UnsupportedOperationException("No hay listado para este estado de reclamo");
					break;
				}								
			}
			
		} catch (NegocioException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Obtencion de reclamos", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void checkPermisos(){
		
		boolean puedeCrearYeditarReclamos = Sistema.getInstance().tienePermisos(Modulo.CREAR_RECLAMO, Permiso.ESCRITURA);
		opcNuevoReclamo.setEnabled(puedeCrearYeditarReclamos);
		
		boolean puedeModificarEstadoReclamo = Sistema.getInstance().tienePermisos(Modulo.ESTADO_RECLAMO, Permiso.ESCRITURA);
		btnComenzarTratamiento.setEnabled(puedeModificarEstadoReclamo);
		btnPasarACerrado.setEnabled(puedeModificarEstadoReclamo);
		btnPasarAsolucionado.setEnabled(puedeModificarEstadoReclamo);
		
		opcAdminUsuarios.setEnabled(Sistema.getInstance().tienePermisos(Modulo.USUARIOS, Permiso.ESCRITURA));
		opcAdminProductos.setEnabled(Sistema.getInstance().tienePermisos(Modulo.PRODUCTO, Permiso.ESCRITURA));
		opcAdminClientes.setEnabled(Sistema.getInstance().tienePermisos(Modulo.CLIENTE, Permiso.ESCRITURA));		

	}
	
	@Override
	public void actualizar() {
		
		Usuario usuarioLogueado = Sistema.getInstance().getUsuarioLogueado();
		
		this.getUsuarioLogueado().setText("Hola " + usuarioLogueado.getUsername());
				
		this.completarListadosDeReclamos(usuarioLogueado.getRol().getTiposDeReclamo());
		
		checkPermisos();
	}
}