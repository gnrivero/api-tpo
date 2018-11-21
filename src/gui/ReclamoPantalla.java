package gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.Sistema;
import excepciones.NegocioException;
import model.EstadoDeReclamo;
import model.TipoDeReclamo;
import observer.IObservador;
import view.ClienteView;
import view.FacturaView;
import view.ProductoView;
import view.ReclamoView;

public class ReclamoPantalla extends JInternalFrame implements IObservador {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -710535408957575880L;


	public ReclamoPantalla(){
		configurar();
		eventos();
		Sistema.getInstance().agregarObservador(this);
	}
	
	public ReclamoPantalla(Integer nroReclamo){		
		
		this();
		
		if(nroReclamo != null){
			this.txtNroReclamo.setText(nroReclamo.toString());
			completarFormulario(false);
		}			
	}
	
	public ReclamoPantalla(TipoDeReclamo tipoDeReclamo, Integer nroReclamoCompuesto){
		
		this();
		
		this.cmbTiposDeReclamo.setSelectedItem(tipoDeReclamo);
		
		if(nroReclamoCompuesto != null)
			this.txtNroReclamoCompuesto.setText(nroReclamoCompuesto.toString());
		
		this.setVisible(true);
	}
	
	private Container cont;
	
	private JLabel lblNroReclamo,
				   lblDescripcion,
				   lblTipoDeReclamo,
				   lblEstado,
				   lblFechaDeCreacion,
				   lblFechaDeCierre,
				   lblCliente,
				   lblNroReclamoCompuesto,
				   lblZona,
				   lblFactura,
				   lblProducto,
				   lblCantidad,
				   lblTiposReclamosHijos
				   ;
	
	private JTextField txtNroReclamo,
					   txtDescripcion,					   
					   txtFechaDeCreacion,
					   txtFechaDeCierre,
					   txtNroReclamoCompuesto,
					   txtZona,
					   txtCantidad
					   ;
	
	private JComboBox<EstadoDeReclamo> cmbEstadoActual;
	private JComboBox<TipoDeReclamo> cmbTiposDeReclamo;
	private JComboBox<ClienteView> cmbClientes;
	private JComboBox<ProductoView> cmbProductos;
	private JList<FacturaView> lstFacturas;
	private JList<TipoDeReclamo> lstTiposReclamosHijos;
	
	private JButton btnGuardar, btnCancelar;
	
	
	public void configurar(){
		
		cont = this.getContentPane();
		cont.setLayout(null);
		
		lblNroReclamo = new JLabel("Nro. Reclamo");
		lblNroReclamo.setBounds(10, 20, 200, 30);		
		cont.add(lblNroReclamo);
		
		txtNroReclamo = new JTextField();
		txtNroReclamo.setBounds(215, 20, 200, 30);
		txtNroReclamo.setEnabled(false);
		cont.add(txtNroReclamo);
		
		lblTiposReclamosHijos = new JLabel("Reclamos derivados");
		lblTiposReclamosHijos.setBounds(450, 20, 200, 30);
		lblTiposReclamosHijos.setVisible(false);				
		cont.add(lblTiposReclamosHijos);				
		
		lstTiposReclamosHijos = new JList<TipoDeReclamo>();
		lstTiposReclamosHijos.setBounds(450, 55, 200, 150);
		lstTiposReclamosHijos.setVisible(false);
		cont.add(lstTiposReclamosHijos);
		
		lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(10, 55, 200, 30);
		cont.add(lblDescripcion);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(215, 55, 200, 30);		
		cont.add(txtDescripcion);
		
		lblTipoDeReclamo = new JLabel("Tipo");
		lblTipoDeReclamo.setBounds(10, 90, 200, 30);
		cont.add(lblTipoDeReclamo);
		
		cmbTiposDeReclamo = new JComboBox<TipoDeReclamo>();
		cmbTiposDeReclamo.setBounds(215, 90, 200, 30);
		cont.add(cmbTiposDeReclamo);
		
		for(TipoDeReclamo tipo: TipoDeReclamo.values()){
			cmbTiposDeReclamo.addItem(tipo);
		}
		
		lblEstado = new JLabel("Estado");
		lblEstado.setBounds(10, 125, 200, 30);
		cont.add(lblEstado);
		
		cmbEstadoActual = new JComboBox<>();
		cmbEstadoActual.setBounds(215, 125, 200, 30);
		cont.add(cmbEstadoActual);				
		
		for (EstadoDeReclamo estado : EstadoDeReclamo.values()){
			cmbEstadoActual.addItem(estado);
		}
		
		lblFechaDeCreacion = new JLabel("Fecha de Creacion");
		lblFechaDeCreacion.setBounds(10, 160, 200, 30);
		cont.add(lblFechaDeCreacion);
		
		txtFechaDeCreacion = new JTextField();
		txtFechaDeCreacion.setBounds(215, 160, 200, 30);
		txtFechaDeCreacion.setEnabled(false);
		cont.add(txtFechaDeCreacion);
		
		lblFechaDeCierre = new JLabel("Fecha de Cierre");
		lblFechaDeCierre.setBounds(10, 195, 200, 30);		
		cont.add(lblFechaDeCierre);
		
		txtFechaDeCierre = new JTextField();
		txtFechaDeCierre.setBounds(215, 195, 200, 30);
		txtFechaDeCierre.setEnabled(false);
		cont.add(txtFechaDeCierre);
		
		lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(10, 230, 200, 30);
		cont.add(lblCliente);
		
		cmbClientes = new JComboBox<ClienteView>();
		cmbClientes.setBounds(215, 230, 200, 30);
		cont.add(cmbClientes);
		
		cargarClientes();
		
		lblNroReclamoCompuesto = new JLabel("Nro. Reclamo Compuesto");
		lblNroReclamoCompuesto.setBounds(10, 265, 200, 30);		
		cont.add(lblNroReclamoCompuesto);
		
		txtNroReclamoCompuesto = new JTextField();
		txtNroReclamoCompuesto.setBounds(215, 265, 200, 30);
		txtNroReclamoCompuesto.setEnabled(false);
		cont.add(txtNroReclamoCompuesto);
		
		//---- Propios de cada Reclamo ----
		
		lblZona = new JLabel("Zona");
		lblZona.setBounds(10, 300, 200, 30);
		cont.add(lblZona);
		
		txtZona = new JTextField();
		txtZona.setBounds(215, 300, 200, 30);
		cont.add(txtZona);
		
		lblFactura = new JLabel("Factura");
		lblFactura.setBounds(10, 335, 200, 30);
		cont.add(lblFactura);
		
		lstFacturas = new JList<>();
		lstFacturas.setBounds(215, 335, 200, 30);
		cont.add(lstFacturas);
		
		lblProducto = new JLabel("Producto");
		lblProducto.setBounds(10,  370, 200, 30);
		cont.add(lblProducto);
		
		cmbProductos = new JComboBox<>();
		cmbProductos.setBounds(215, 370, 200, 30);
		cont.add(cmbProductos);
		
		lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(10,  405, 200, 30);
		cont.add(lblCantidad);
		
		txtCantidad = new JTextField();
		txtCantidad.setBounds(215, 405, 200, 30);
		cont.add(txtCantidad);
		
		//FIN: ----Propios de cada Reclamo ----
		
		//------ Botones --------
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(10, 440, 200, 30);
		cont.add(btnGuardar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(215, 440, 200, 30);
		cont.add(btnCancelar);
		
		this.pack();
		this.setLocation(10, 100);
		this.setSize(800, 600);
		this.setClosable(true);
	}
	
	public void eventos(){
		
		cmbEstadoActual.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				
			}
		});
		
		cmbTiposDeReclamo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					lblZona.setEnabled(false);
					txtZona.setEnabled(false);
					
					lblFactura.setEnabled(false);
					lstFacturas.setEnabled(false);
					
					lblProducto.setEnabled(false);
					cmbProductos.setEnabled(false);
					
					lblCantidad.setEnabled(false);
					txtCantidad.setEnabled(false);
					
					lblNroReclamoCompuesto.setEnabled(true);
					lblTiposReclamosHijos.setVisible(false);
					lstTiposReclamosHijos.setVisible(false);
					
					TipoDeReclamo tipoDeReclamo = (TipoDeReclamo) cmbTiposDeReclamo.getSelectedItem();							
									
					switch(tipoDeReclamo){
						
						case ZONA:
							lblZona.setEnabled(true);
							txtZona.setEnabled(true);
							
						break;
						case FACTURACION:
							
							lblFactura.setEnabled(true);
							lstFacturas.setEnabled(true);
							
						break;
						case CANTIDADES:
						case FALTANTES:
						case PRODUCTO:
							lblProducto.setEnabled(true);
							cmbProductos.setEnabled(true);
							lblCantidad.setEnabled(true);
							txtCantidad.setEnabled(true);
							
							cargarProductos();
							
						break;
						case COMPUESTO:
							lblNroReclamoCompuesto.setEnabled(false);
														
							DefaultListModel<TipoDeReclamo> tipoDeReclamoListModel = new DefaultListModel<TipoDeReclamo>();							
							for (TipoDeReclamo tipo : TipoDeReclamo.values()){
								if(!TipoDeReclamo.COMPUESTO.equals(tipo))
									tipoDeReclamoListModel.addElement(tipo);
							}
							lstTiposReclamosHijos.setModel(tipoDeReclamoListModel);
							
							lblTiposReclamosHijos.setVisible(true);
							lstTiposReclamosHijos.setVisible(true);
							
						break;
						default:
							throw new RuntimeException("El tipo de reclamo indicado no existe");							
					}
					
				}catch(Exception ex){
					ex.printStackTrace();
				}							
			}
		});
		
		cmbClientes.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				ClienteView cliente = (ClienteView) cmbClientes.getSelectedItem();				
				TipoDeReclamo tipoDeReclamo = (TipoDeReclamo) cmbTiposDeReclamo.getSelectedItem();
				
				if(TipoDeReclamo.FACTURACION.equals(tipoDeReclamo)){
					List<FacturaView> facturas;
					try {
						facturas = Sistema.getInstance().obenerFacturasPorCliente(cliente.getIdCliente());
						DefaultListModel<FacturaView> facturaListModel = new DefaultListModel<>();				
						facturas.forEach(f -> facturaListModel.addElement(f));
						
						lstFacturas.setModel(facturaListModel);
					} catch (NegocioException e1) {
						e1.printStackTrace();
					}
				}
			}			
		});
		
		btnGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				completarFormulario(true);				
			}
		});
		
		btnCancelar.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});		
	}
	
	
	private void completarFormulario(boolean estoyGuardando){
		
		try{
			
			TipoDeReclamo tipoDeReclamo = (TipoDeReclamo) cmbTiposDeReclamo.getSelectedItem();
			
			Integer nroReclamo = (txtNroReclamo.getText().isEmpty()) ?  null : Integer.valueOf(txtNroReclamo.getText());
			String descripcion = txtDescripcion.getText();
			ClienteView cliente = (ClienteView) cmbClientes.getSelectedItem();
			EstadoDeReclamo estado = (EstadoDeReclamo) cmbEstadoActual.getSelectedItem();
			
			ReclamoView reclamoView = null;
			
			switch(tipoDeReclamo){
				
				case ZONA:				
					
					String zona = txtZona.getText();
					
					if(estoyGuardando)
						nroReclamo = Sistema.getInstance().registrarReclamo(nroReclamo, descripcion, tipoDeReclamo, estado, cliente.getIdCliente(), zona);														
					
					reclamoView = Sistema.getInstance().obtenerReclamoZona(nroReclamo);							
					txtZona.setText(reclamoView.getZona());
					
				break;
				case FACTURACION:
					
					List<FacturaView> selectedFacturas = lstFacturas.getSelectedValuesList();
					List<Integer> nrosFacturas = new ArrayList<>();
					selectedFacturas.forEach(f -> nrosFacturas.add(f.getNroFactura()));					
					
					Sistema.getInstance().registrarReclamo(descripcion, tipoDeReclamo, cliente.getIdCliente(), nrosFacturas);
					
				break;
				case CANTIDADES:
				case FALTANTES:
				case PRODUCTO:
					
					ProductoView producto = (ProductoView) cmbProductos.getSelectedItem();
					Integer cantidad = Integer.valueOf(txtCantidad.getText());
					
					if(estoyGuardando)
						nroReclamo = Sistema.getInstance().registrarReclamo(nroReclamo, descripcion, tipoDeReclamo, estado, cliente.getIdCliente(), producto.getIdProducto(), cantidad);
					
					reclamoView = Sistema.getInstance().obtenerReclamoDistribucion(nroReclamo);
					
					cmbProductos.setSelectedItem(reclamoView.getProducto());
					txtCantidad.setText(reclamoView.getCantidad());
					
				break;
				case COMPUESTO:
					
					if(estoyGuardando)
						nroReclamo = Sistema.getInstance().registrarReclamoCompuesto(nroReclamo, descripcion, tipoDeReclamo, estado, cliente.getIdCliente());
					
					reclamoView = Sistema.getInstance().obtenerReclamosPorNumeroYTipo(nroReclamo, tipoDeReclamo);
					
					List<TipoDeReclamo> tiposDeReclamosSeleccionados = lstTiposReclamosHijos.getSelectedValuesList();
					
					for(TipoDeReclamo tipoSeleccionado : tiposDeReclamosSeleccionados){
						TableroPantalla.getInstance().getFrameContainer().add(new ReclamoPantalla(tipoSeleccionado, nroReclamo));
					}	
									
				break;
				default:
					throw new RuntimeException("El tipo de reclamo indicado no existe");
					
			}
			
			txtNroReclamo.setText(reclamoView.getNroReclamo().toString());
			txtDescripcion.setText(reclamoView.getDescripcion());
			cmbTiposDeReclamo.setSelectedItem(reclamoView.getTipoDeReclamo());
			cmbEstadoActual.setSelectedItem(reclamoView.getEstadoDeReclamo());
			cmbClientes.setSelectedItem(reclamoView.getCliente());
			txtFechaDeCreacion.setText(reclamoView.getFechaDeReclamo());
			txtFechaDeCierre.setText(reclamoView.getFechaDeCierre());
			
			if(reclamoView.getNroReclamoCompuesto() != null)
				txtNroReclamoCompuesto.setText(reclamoView.getNroReclamoCompuesto().toString());
								
		}catch(NegocioException ne){
			ne.printStackTrace();
		}
	}
	
	private void cargarProductos(){
		try {
			List<ProductoView> productos = Sistema.getInstance().obtenerProductos();			
			productos.forEach(p -> cmbProductos.addItem(p));
			
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	}
	
	private void cargarClientes(){
		try {
			List<ClienteView> clientesViews = Sistema.getInstance().obtenerTodosLosClientes(false);
			cmbClientes.removeAllItems();
			clientesViews.forEach(c -> cmbClientes.addItem(c));
		} catch (NegocioException e) {
			JOptionPane.showMessageDialog(null, "Error: no se pudo cargar clientes. " + e, "Admin. Clientes", JOptionPane.ERROR_MESSAGE);
		} 
	}

	@Override
	public void actualizar() {		
		cargarClientes();		
	}
}
