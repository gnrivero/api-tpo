package gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.Sistema;
import excepciones.NegocioException;
import observer.IObservador;
import view.ClienteView;

public class ClientePantalla extends JInternalFrame implements IObservador {
	
	private static ClientePantalla instance;
	
	public static ClientePantalla getInstance(){
		if (instance == null)
			instance = new ClientePantalla();
		
		return instance;
	}
	
	
	private static final long serialVersionUID = 7954763123247437968L;
	
	JLabel lblClientes, lblIdCliente, lblNombre, lblDomicilio, lblTelefono, lblMail, lblHabilitado;
	JTextField txtIdCliente, txtNombre, txtDomicilio, txtTelefono, txtMail;
	JCheckBox chHabilitado;
	JButton btnGuardar, btnCancelar, btnSeleccionarCliente;
	JComboBox<ClienteView> cmbClientes;
	
	Container cont = null;
	
	private ClientePantalla(){
		configurar();
		eventos();
		Sistema.getInstance().agregarObservador(this);
	}
	
	private void configurar(){
		
		cont = this.getContentPane();
		cont.setLayout(null);
		
		lblClientes = new JLabel("Clientes");
		lblClientes.setBounds(10, 20, 200, 30);
		cont.add(lblClientes);	
		
		cmbClientes = new JComboBox<ClienteView>();
		cmbClientes.setBounds(215, 20, 200, 30);
		cont.add(cmbClientes);
		
		this.cargarClientes();
		
		btnSeleccionarCliente = new JButton("Cargar");
		btnSeleccionarCliente.setBounds(420, 20, 100, 30);
		cont.add(btnSeleccionarCliente);
		
		lblIdCliente = new JLabel("ID. de Cliente");
		lblIdCliente.setBounds(10, 55, 200, 30);
		cont.add(lblIdCliente);
		
		txtIdCliente = new JTextField();
		txtIdCliente.setBounds(215, 55, 200, 30);
		txtIdCliente.setEnabled(false);
		cont.add(txtIdCliente);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 90, 200, 30);
		cont.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(215, 90, 200, 30);
		cont.add(txtNombre);
		
		lblDomicilio = new JLabel("Domicilio");
		lblDomicilio.setBounds(10, 125, 200, 30);
		cont.add(lblDomicilio);
		
		txtDomicilio = new JTextField();
		txtDomicilio.setBounds(215, 125, 200, 30);
		cont.add(txtDomicilio);
		
		lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(10, 160, 200, 30);
		cont.add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(215, 160, 200, 30);
		cont.add(txtTelefono);
		
		lblMail = new JLabel("E-Mail");
		lblMail.setBounds(10, 195, 200, 30);
		cont.add(lblMail);
		
		txtMail = new JTextField();
		txtMail.setBounds(215, 195, 200, 30);
		cont.add(txtMail);
		
		lblHabilitado = new JLabel("Habilitado");
		lblHabilitado.setBounds(10, 230, 200, 30);
		cont.add(lblHabilitado);
		
		chHabilitado = new JCheckBox();
		chHabilitado.setBounds(215, 230, 200, 30);
		cont.add(chHabilitado);
				
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
	
	private void eventos(){
		
		btnGuardar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					
				Integer idCliente = null;
				
				if (!txtIdCliente.getText().isEmpty())
					idCliente = Integer.valueOf(txtIdCliente.getText());
				
				String nombre = txtNombre.getText();
				String domicilio = txtDomicilio.getText();
				String telefono = txtTelefono.getText();
				String mail = txtMail.getText();
				
				Date fechaBaja = null;
				if (!chHabilitado.isSelected()){
					fechaBaja = new Date();
				}				
				
				try {
					if (idCliente == null){
						Integer idNuevoCliente = Sistema.getInstance().agregarCliente(nombre, domicilio, telefono, mail);
						txtIdCliente.setText(idNuevoCliente.toString());
					}else{
						Sistema.getInstance().modificarCliente(idCliente, nombre, domicilio, telefono, mail, fechaBaja);
					}
				} catch (NegocioException e1) { 
					JOptionPane.showMessageDialog(null, "Error: " + e1, "Admin. Clientes", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btnSeleccionarCliente.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				ClienteView cliente = (ClienteView) cmbClientes.getSelectedItem();
				
				if (cliente.idCliente  != null && cliente != null){
					txtIdCliente.setText(cliente.idCliente.toString());
					txtNombre.setText(cliente.nombre);
					txtDomicilio.setText(cliente.domicilio);
					txtTelefono.setText(cliente.telefono);
					txtMail.setText(cliente.mail);
										
					chHabilitado.setSelected((cliente.fechaBaja == null));
					
				} else {
					
					txtIdCliente.setText("");
					txtNombre.setText("");
					txtDomicilio.setText("");
					txtTelefono.setText("");
					txtMail.setText("");										
					chHabilitado.setSelected(true);					
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
			cmbClientes.addItem(new ClienteView("Nuevo Cliente"));
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