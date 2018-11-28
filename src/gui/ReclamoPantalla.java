package gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.Sistema;
import excepciones.NegocioException;
import model.EstadoDeReclamo;
import model.TipoDeReclamo;
import observer.IObservador;
import view.AuditoriaReclamoView;
import view.ClienteView;
import view.FacturaView;
import view.ProductoView;
import view.ReclamoView;

public class ReclamoPantalla extends JInternalFrame implements IObservador {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -710535408957575880L;
	
	private Container cont;
	
	private JLabel lblNroReclamo,
				   lblDescripcion,
				   lblTipoDeReclamo,
				   lblEstado,
				   lblFechaDeCreacion,
				   lblFechaDeCierre,
				   lblCliente,
				   //lblNroReclamoCompuesto,
				   lblZona,
				   lblFactura,
				   lblProducto,
				   lblCantidad,
				   lblTiposReclamosHijos,
				   lblReclamosHijos,
				   tituloAuditoria
				   ;
	
	private JTextField txtNroReclamo,
					   txtDescripcion,					   
					   txtFechaDeCreacion,
					   txtFechaDeCierre,
					   //txtNroReclamoCompuesto,
					   txtZona,
					   txtCantidad
					   ;
	
	private JComboBox<EstadoDeReclamo> cmbEstadoActual;
	private JComboBox<TipoDeReclamo> cmbTiposDeReclamo;
	private JComboBox<ClienteView> cmbClientes;
	private JComboBox<ProductoView> cmbProductos;
	private JList<FacturaView> lstFacturas;
	private JList<TipoDeReclamo> lstTiposReclamosHijos;
	private DefaultListModel<FacturaView> facturaListModel = new DefaultListModel<>();	
	
	private JButton btnGuardar, btnCancelar;
	
	//Datos
	private Integer nroReclamoCompuesto = null; 
	
	public ReclamoPantalla(){
		configurar();
		eventos();
		Sistema.getInstance().agregarObservador(this);
	}
	
	public ReclamoPantalla(Integer nroReclamo, TipoDeReclamo tipoDeReclamo){		
		
		this();
		
		if(nroReclamo != null){
			this.txtNroReclamo.setText(nroReclamo.toString());			
			this.cmbTiposDeReclamo.setSelectedItem(tipoDeReclamo);
			completarFormulario(false);
		}			
	}
	
	public ReclamoPantalla(TipoDeReclamo tipoDeReclamo, ClienteView cliente, Integer nroReclamoCompuesto){
		
		this();
		
		this.cmbTiposDeReclamo.setSelectedItem(tipoDeReclamo);
		
		if(nroReclamoCompuesto != null)
			this.nroReclamoCompuesto = nroReclamoCompuesto;
		
		cmbClientes.setSelectedItem(cliente);
		
		this.btnGuardar.setText("Agregar y cerrar");
		this.setTitle("Agregar reclamo derivado");
		
		this.setVisible(true);
	}
	
	
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
		
		lblTiposReclamosHijos = new JLabel();
		lblTiposReclamosHijos.setBounds(450, 20, 150, 200);
		lblTiposReclamosHijos.setBorder(BorderFactory.createTitledBorder("Reclamos a crear"));
		lblTiposReclamosHijos.setVisible(false);				
		cont.add(lblTiposReclamosHijos);				
		
		lstTiposReclamosHijos = new JList<TipoDeReclamo>();
		lstTiposReclamosHijos.setBounds(20, 30, 100, 95);
		lstTiposReclamosHijos.setVisible(false);
		lstTiposReclamosHijos.setFixedCellWidth(100);
		lblTiposReclamosHijos.add(lstTiposReclamosHijos);
		
		lblReclamosHijos = new JLabel();
		lblReclamosHijos.setBounds(620, 20, 330, 200);
		lblReclamosHijos.setBorder(BorderFactory.createTitledBorder("Reclamos Derivados"));
		lblReclamosHijos.setVisible(false);	
		lblReclamosHijos.setVerticalTextPosition(SwingConstants.TOP);
		cont.add(lblReclamosHijos);	
		
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
		cmbEstadoActual.setEnabled(false);
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
		lstFacturas.setBounds(215, 335, 200, 60);
		cont.add(lstFacturas);
		
		lblProducto = new JLabel("Producto");
		lblProducto.setBounds(10,  400, 200, 30);
		cont.add(lblProducto);
		
		cmbProductos = new JComboBox<>();
		cmbProductos.setBounds(215, 400, 200, 30);
		cont.add(cmbProductos);
		
		lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(10,  435, 200, 30);
		cont.add(lblCantidad);
		
		txtCantidad = new JTextField();
		txtCantidad.setBounds(215, 435, 200, 30);
		cont.add(txtCantidad);
		
		//FIN: ----Propios de cada Reclamo ----
		
		tituloAuditoria = new JLabel();
		tituloAuditoria.setBounds(450, 265, 500, 200);
		tituloAuditoria.setBorder(BorderFactory.createTitledBorder("Registro de Cambios"));
		cont.add(tituloAuditoria);
		
//		JLabel lblAuditoria = new JLabel(auditoria.toString());
//		lblAuditoria.setBounds(10,10,500,30);
//		lblAuditoria.setVisible(true);
//		tituloAuditoria.add(lblAuditoria);

		//------ Botones --------
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(10, 480, 200, 30);
		cont.add(btnGuardar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(215, 480, 200, 30);
		cont.add(btnCancelar);
		
		this.pack();
		this.setLocation(10, 100);
		this.setSize(1000, 600);
	}
	
	public void eventos(){
		
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
					
					//lblNroReclamoCompuesto.setEnabled(true);
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
														
							if (cmbProductos.getItemCount() == 0)
								cargarProductos();
							
						break;
						case COMPUESTO:
							//lblNroReclamoCompuesto.setEnabled(false);
							
							DefaultListModel<TipoDeReclamo> tipoDeReclamoListModel = new DefaultListModel<TipoDeReclamo>();							
							for (TipoDeReclamo tipo : TipoDeReclamo.values()){
								if(!TipoDeReclamo.COMPUESTO.equals(tipo))
									tipoDeReclamoListModel.addElement(tipo);
							}
							lstTiposReclamosHijos.setModel(tipoDeReclamoListModel);
							
							lblTiposReclamosHijos.setVisible(true);
							lstTiposReclamosHijos.setVisible(true);
							lblReclamosHijos.setVisible(true);
							
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
				
				if(TipoDeReclamo.FACTURACION.equals(tipoDeReclamo) && cliente != null){
					List<FacturaView> facturas;
					try {
						facturas = Sistema.getInstance().obenerFacturasPorCliente(cliente.getIdCliente());								
						facturaListModel.removeAllElements();
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
				
				if(TipoDeReclamo.COMPUESTO.equals(cmbTiposDeReclamo.getSelectedItem())
						&& !txtNroReclamo.getText().isEmpty()){
							try {
								ReclamoView reclamoCompuesto = Sistema.getInstance().obtenerReclamosPorNumeroYTipo(Integer.valueOf(txtNroReclamo.getText()), (TipoDeReclamo) cmbTiposDeReclamo.getSelectedItem());

								if(reclamoCompuesto.getReclamosHijos().isEmpty()){
									JOptionPane.showMessageDialog(null, "El reclamo compuesto no contiene reclamos derivados, guarde los mismos antes de cerrar", "Reclamo Incompleto", JOptionPane.ERROR_MESSAGE);
								}else{
									TableroPantalla.getInstance().decrementarLayerCount();
									dispose();
								}
							
							} catch (NumberFormatException | NegocioException e1) {
								e1.printStackTrace();//"Logueo" error
							}					
				}else{
					dispose();

					TableroPantalla.getInstance().decrementarLayerCount();
					TableroPantalla.getInstance().getFrameContainer().remove(ReclamoPantalla.this);										
				}								
			}				
		});		
	}
	
	
	private void completarFormulario(boolean estoyGuardando){
		
		try{
			
			TipoDeReclamo tipoDeReclamo = (TipoDeReclamo) cmbTiposDeReclamo.getSelectedItem();
			
			Integer nroReclamo = (txtNroReclamo.getText().isEmpty()) ?  null : Integer.valueOf(txtNroReclamo.getText());
			Integer nroReclamoCompuesto = this.nroReclamoCompuesto;
			String descripcion = txtDescripcion.getText();
			ClienteView cliente = (ClienteView) cmbClientes.getSelectedItem();
			EstadoDeReclamo estado = (EstadoDeReclamo) cmbEstadoActual.getSelectedItem();
			
			ReclamoView reclamoView = null;
			
			switch(tipoDeReclamo){
				
				case ZONA:				
					
					String zona = txtZona.getText();
					
					if(estoyGuardando){
						nroReclamo = Sistema.getInstance().registrarReclamo(nroReclamo, descripcion, tipoDeReclamo, estado, cliente.getIdCliente(), zona, nroReclamoCompuesto);												
						
						if(nroReclamoCompuesto != null){
							dispose();
							return;
						}
					}
					reclamoView = Sistema.getInstance().obtenerReclamoZona(nroReclamo);
					
					txtZona.setText(reclamoView.getZona());
					
				break;
				case FACTURACION:
					
					List<FacturaView> selectedFacturas = lstFacturas.getSelectedValuesList();
					int[] selectedIndices = lstFacturas.getSelectedIndices();
														
					List<Integer> nrosFacturas = new ArrayList<Integer>();
					selectedFacturas.forEach(f -> nrosFacturas.add(f.getNroFactura()));					
					
					if (estoyGuardando){
						nroReclamo = Sistema.getInstance().registrarReclamo(nroReclamo, descripcion, tipoDeReclamo, cliente.getIdCliente(), nrosFacturas, nroReclamoCompuesto);
						
						if(nroReclamoCompuesto != null)
							dispose();
					}
					
					if (nroReclamo != null)
						reclamoView = Sistema.getInstance().obtenerReclamoFacturacion(nroReclamo);
					
					cmbClientes.setSelectedItem(reclamoView.getCliente());
					
					if (selectedIndices.length == 0){
						selectedIndices = new int[reclamoView.getFacturasReclamadas().size()];						
						int i = 0;
						for(FacturaView factura : reclamoView.getFacturasReclamadas()){							
							selectedIndices[i] = facturaListModel.indexOf(factura);		
							i++;
						}
					}										
					
					lstFacturas.setSelectedIndices(selectedIndices);
					
				break;
				case CANTIDADES:
				case FALTANTES:
				case PRODUCTO:
					
					ProductoView producto = (ProductoView) cmbProductos.getSelectedItem();
					Integer cantidad = (txtCantidad.getText().isEmpty()) ? null : Integer.valueOf(txtCantidad.getText());
					
					if(estoyGuardando){
						nroReclamo = Sistema.getInstance().registrarReclamo(nroReclamo, descripcion, tipoDeReclamo, estado, cliente.getIdCliente(), producto.getIdProducto(), cantidad, nroReclamoCompuesto);
						
						if(nroReclamoCompuesto != null)
							dispose();
					}
						
										
					reclamoView = Sistema.getInstance().obtenerReclamoDistribucion(nroReclamo);
					
					cmbProductos.setSelectedItem(reclamoView.getProducto());
					txtCantidad.setText(reclamoView.getCantidad());
					
				break;
				case COMPUESTO:
					
					if(estoyGuardando)
						nroReclamo = Sistema.getInstance().registrarReclamoCompuesto(nroReclamo, descripcion, tipoDeReclamo, estado, cliente.getIdCliente());
					
					reclamoView = Sistema.getInstance().obtenerReclamoCompuesto(nroReclamo);
					
					int y = 20;
					for(ReclamoView hojas : reclamoView.getReclamosHijos()){						
						JLabel reclamoHoja = new JLabel(hojas.toString());
						reclamoHoja.setBounds(10, y, 290, 200);
						reclamoHoja.setVisible(true);
						lblReclamosHijos.add(reclamoHoja);
						y = y + 20;
					}
					
					List<TipoDeReclamo> tiposDeReclamosSeleccionados = lstTiposReclamosHijos.getSelectedValuesList();
					
					TableroPantalla tablero = TableroPantalla.getInstance();
					for(TipoDeReclamo tipoSeleccionado : tiposDeReclamosSeleccionados){
						tablero.getFrameContainer().add(new ReclamoPantalla(tipoSeleccionado, cliente, nroReclamo), tablero.incrementarLayerCount());
					}
					
				break;
				default:
					throw new RuntimeException("El tipo de reclamo indicado no existe");
			
			}
			
			txtNroReclamo.setText(reclamoView.getNroReclamo().toString());
			txtDescripcion.setText(reclamoView.getDescripcion());			
			cmbTiposDeReclamo.setSelectedItem(reclamoView.getTipoDeReclamo());
			
			if(nroReclamo != null || nroReclamoCompuesto != null)
				cmbTiposDeReclamo.setEnabled(false);
			
			cmbEstadoActual.setSelectedItem(reclamoView.getEstadoDeReclamo());
			cmbEstadoActual.setEnabled(false);
			
			if(!TipoDeReclamo.FACTURACION.equals(tipoDeReclamo))
				cmbClientes.setSelectedItem(reclamoView.getCliente());
			
			txtFechaDeCreacion.setText(reclamoView.getFechaDeReclamo());
			txtFechaDeCierre.setText(reclamoView.getFechaDeCierre());
			
			listarAditorias(reclamoView.getAuditorias());
			
//			if(reclamoView.getNroReclamoCompuesto() != null)
//				txtNroReclamoCompuesto.setText(reclamoView.getNroReclamoCompuesto().toString());
								
		}catch(NegocioException ne){
			ne.printStackTrace();
		}
	}
	
	private void listarAditorias(List<AuditoriaReclamoView> auditorias){
		int y = 15;
		for (AuditoriaReclamoView auditoria : auditorias){			 	
			JLabel lblAuditoria = new JLabel(auditoria.toString());
			lblAuditoria.setBounds(10,y,500,30);
			lblAuditoria.setVisible(true);
			tituloAuditoria.add(lblAuditoria);
			y = y + 15;
		}
	}
	
	private void cargarProductos(){
		try {
			List<ProductoView> productos = Sistema.getInstance().obtenerTodosLosProductos();			
			cmbProductos.removeAllItems();
			cmbProductos.addItem(new ProductoView("Seleccionar"));
			productos.forEach(p -> cmbProductos.addItem(p));
			
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	}
	
	private void cargarClientes(){
		try {
			List<ClienteView> clientesViews = Sistema.getInstance().obtenerTodosLosClientes(false);
			cmbClientes.removeAllItems();
			cmbClientes.addItem(new ClienteView("Seleccionar"));
			clientesViews.forEach(c -> cmbClientes.addItem(c));
		} catch (NegocioException e) {
			JOptionPane.showMessageDialog(null, "Error: no se pudo cargar clientes. " + e, "Admin. Clientes", JOptionPane.ERROR_MESSAGE);
		} 
	}

	@Override
	public void actualizar() {
		cargarClientes();
		cargarProductos();
	}
}
