package gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.Sistema;
import excepciones.NegocioException;
import model.TipoDeReclamo;
import model.reclamo.ReclamoCompuesto;
import model.reclamo.ReclamoDistribucion;
import model.reclamo.ReclamoFacturacion;
import model.reclamo.ReclamoZona;
import observer.IObservador;
import view.ClienteView;

public class ReclamoPantalla extends JInternalFrame implements IObservador {
	
	private static ReclamoPantalla instance;
	
	public static ReclamoPantalla getInstance(){
		if(instance==null)
			instance = new ReclamoPantalla();
			
		return instance;
	}
	
	private ReclamoPantalla(){
		configurar();
		eventos();
		Sistema.getInstance().agregarObservador(this);
	}
	
	private Container cont;
	
	private JLabel lblNroReclamo,
				   lblDescripcion,
				   lblTipoDeReclamo,
				   lblEstado,
				   lblFechaDeCreacion,
				   lblFechaDeCierre,
				   lblCliente,
				   lblNroReclamoCompuesto;
	
	private JTextField txtNroReclamo,
					   txtDescripcion,
					   txtEstado,
					   txtFechaDeCreacion,
					   txtFechaDeCierre,
					   txtNroReclamoCompuesto,
					   txtZona					   
					   ;
	
	private JComboBox<TipoDeReclamo> cmbTiposDeReclamo;
	private JComboBox<ClienteView> cmbClientes;
	
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
		
		txtEstado = new JTextField();
		txtEstado.setBounds(215, 125, 200, 30);
		cont.add(txtEstado);
		
		lblFechaDeCreacion = new JLabel("Fecha de Creacion");
		lblFechaDeCreacion.setBounds(10, 160, 200, 30);
		cont.add(lblFechaDeCreacion);
		
		txtFechaDeCreacion = new JTextField();
		txtFechaDeCreacion.setBounds(215, 160, 200, 30);
		cont.add(txtFechaDeCreacion);
		
		lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(10, 195, 200, 30);
		cont.add(lblCliente);
		
		cmbClientes = new JComboBox<ClienteView>();
		cmbClientes.setBounds(215, 195, 200, 30);
		cont.add(cmbClientes);
		
		cargarClientes();
		
		lblNroReclamoCompuesto = new JLabel("Nro. Reclamo Compuesto");
		lblNroReclamoCompuesto.setBounds(10, 230, 200, 30);
		cont.add(lblNroReclamoCompuesto);
		
		txtNroReclamoCompuesto = new JTextField();
		txtNroReclamoCompuesto.setBounds(215, 230, 200, 30);
		cont.add(txtNroReclamoCompuesto);
				
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(10, 285, 200, 30);
		cont.add(btnGuardar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(215, 285, 200, 30);
		cont.add(btnCancelar);
		
		this.pack();
		this.setSize(600, 500);
		this.setClosable(true);	
		
	}
	
	public void eventos(){
		
		btnGuardar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				TipoDeReclamo tipoDeReclamo = (TipoDeReclamo) cmbTiposDeReclamo.getSelectedItem();
				
				String descripcion = txtDescripcion.getText();
				ClienteView cliente = (ClienteView) cmbClientes.getSelectedItem();
				
				
				switch(tipoDeReclamo){
					
					case ZONA:
						
						String zona = txtZona.getText();
						
						Sistema.getInstance().registrarReclamo(descripcion, tipoDeReclamo, cliente.idCliente, zona);
					break;
					case FACTURACION:																	
						Sistema.getInstance().registrarReclamo(descripcion, tipoDeReclamo, cliente, facturas);
					break;
					case CANTIDADES: 
					case FALTANTES:
					case PRODUCTO:
						Sistema.getInstance().registrarReclamo(descripcion, tipoDeReclamo, cliente, producto, cantidad);
					break;				
					default:
						throw new RuntimeException("El tipo de reclamo indicado no existe");
						
				}
			}
		});
		
		btnCancelar.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});		
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