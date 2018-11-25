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
import view.RolView;
import view.UsuarioView;

public class UsuarioPantalla extends JInternalFrame implements IObservador {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static UsuarioPantalla instance;
	
	public static UsuarioPantalla getInstance(){
		if (instance == null)
			instance = new UsuarioPantalla();
		
		return instance;
	}
	
	JLabel lblUsuarios, lblIdUsuario, lblUserName, lblPassword, lblRol, lblHabilitado;
	JTextField txtIdUsuario, txtUserName, txtPassword;
	JCheckBox chHabilitado;
	JButton btnGuardar, btnCancelar, btnSeleccionarUsuario;
	JComboBox<UsuarioView> cmbUsuarios;
	JComboBox<RolView> cmbRoles;
	
	Container cont = null;
	
	public UsuarioPantalla(){
		configurar();
		eventos();
		Sistema.getInstance().agregarObservador(this);
	}
	
	private void configurar(){
		
		cont = this.getContentPane();
		cont.setLayout(null);
		
		lblUsuarios = new JLabel("Usuarios");
		lblUsuarios.setBounds(10, 20, 200, 30);
		cont.add(lblUsuarios);	
		
		cmbUsuarios = new JComboBox<UsuarioView>();
		cmbUsuarios.setBounds(215, 20, 200, 30);
		cont.add(cmbUsuarios);
		
		this.cargarUsuarios();
		
		btnSeleccionarUsuario = new JButton("Seleccionar");
		btnSeleccionarUsuario.setBounds(420, 20, 150, 30);
		cont.add(btnSeleccionarUsuario);
		
		lblIdUsuario= new JLabel("ID. de Usuario");
		lblIdUsuario.setBounds(10, 55, 200, 30);
		cont.add(lblIdUsuario);
		
		txtIdUsuario= new JTextField();
		txtIdUsuario.setBounds(215, 55, 200, 30);
		txtIdUsuario.setEnabled(false);
		cont.add(txtIdUsuario);
		
		lblUserName= new JLabel("Usuario");
		lblUserName.setBounds(10, 90, 200, 30);
		cont.add(lblUserName);
		
		txtUserName = new JTextField();
		txtUserName.setBounds(215, 90, 200, 30);
		cont.add(txtUserName);

		lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 125, 200, 30);
		cont.add(lblPassword);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(215, 125, 200, 30);
		cont.add(txtPassword);
		
		lblRol = new JLabel("Rol");
		lblRol.setBounds(10, 160, 200, 30);
		cont.add(lblRol);	
		
		cmbRoles = new JComboBox<RolView>();
		cmbRoles.setBounds(215, 160, 200, 30);
		cont.add(cmbRoles);
		
		this.cargarRoles();
		
		lblHabilitado = new JLabel("Habilitado");
		lblHabilitado.setBounds(10, 195, 200, 30);
		cont.add(lblHabilitado);
		
		chHabilitado = new JCheckBox();
		chHabilitado.setBounds(215, 195, 200, 30);
		cont.add(chHabilitado);
				
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(10, 230, 200, 30);
		cont.add(btnGuardar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(215, 230, 200, 30);
		cont.add(btnCancelar);
		
		this.pack();
		this.setLocation(10, 100);
		this.setSize(600, 330);
		this.setClosable(true);		
	}
	
	private void eventos(){
		
		btnGuardar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					
				Integer idUsuario= null;
				
				if (!txtIdUsuario.getText().isEmpty())
					idUsuario= Integer.valueOf(txtIdUsuario.getText());
				
				String username = txtUserName.getText();
				String password = txtPassword.getText();
				RolView rol = (RolView) cmbRoles.getSelectedItem();
				
				Date fechaBaja = null;
				if (!chHabilitado.isSelected()){
					fechaBaja = new Date();
				}
				
				try {
					if (idUsuario == null){
						Integer idNuevoUsuario = Sistema.getInstance().crearNuevoUsuario(username, password, rol.getIdRol());
						txtIdUsuario.setText(idNuevoUsuario.toString());
						JOptionPane.showMessageDialog(null, "Usuario guardado con éxito! ", "Admin. Usuarios", JOptionPane.INFORMATION_MESSAGE);
						actualizar();
					}else{
						Sistema.getInstance().modificarUsuario(idUsuario, username, password, fechaBaja, rol.getIdRol());
						JOptionPane.showMessageDialog(null, "Usuario guardado con éxito! ", "Admin. Usuario", JOptionPane.INFORMATION_MESSAGE);
						actualizar();
					}
				} catch (NegocioException e1) { 
					JOptionPane.showMessageDialog(null, "Error: " + e1, "Admin. Usuarios", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btnSeleccionarUsuario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				UsuarioView usuario = (UsuarioView) cmbUsuarios.getSelectedItem();
				
				if (usuario.getIdUsuario() != null){
					txtIdUsuario.setText(usuario.idUsuario.toString());
					txtUserName.setText(usuario.username);
					txtPassword.setText(usuario.password);
					cmbRoles.setSelectedIndex(usuario.rol.getIdRol()-1);
					chHabilitado.setSelected((usuario.fechaBaja == null));
				} else {
					txtIdUsuario.setText("");
					txtUserName.setText("");
					txtPassword.setText("");
					cmbRoles.setSelectedIndex(0);
					chHabilitado.setSelected(true);
				}
			}
		});
		
		btnCancelar.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				limpiarForm();
				dispose();
			}
		});
	}
	
	private void limpiarForm() {
		cmbUsuarios.setSelectedIndex(0);
		txtIdUsuario.setText("");
		txtUserName.setText("");
		txtPassword.setText("");
		cmbRoles.setSelectedIndex(0);
		chHabilitado.setSelected(true);
	}
	
	private void cargarUsuarios(){
		try {
			List<UsuarioView> usuarioViews = Sistema.getInstance().obtenerTodosLosUsuarios(false);			
			cmbUsuarios.removeAllItems();
			cmbUsuarios.addItem(new UsuarioView("Nuevo Usuario"));
			usuarioViews.forEach(c -> cmbUsuarios.addItem(c));
		} catch (NegocioException e) {
			JOptionPane.showMessageDialog(null, "Error: no se pudo cargar usuarios. " + e, "Admin. Usuarios", JOptionPane.ERROR_MESSAGE);
		} 
	}
	
	private void cargarRoles() {
		try {
			List<RolView> rolesViews = Sistema.getInstance().obtenerRoles();
			cmbRoles.removeAllItems();
			rolesViews.forEach(r -> cmbRoles.addItem(r));
		} catch (NegocioException e) {
			JOptionPane.showMessageDialog(null, "Error: no se pudo cargar roles. " + e, "Admin. Usuarios", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void actualizar() {
		limpiarForm();
		cargarUsuarios();
	}
	
}
