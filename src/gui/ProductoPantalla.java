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
import observer.IObservador;
import view.ProductoView;

public class ProductoPantalla extends JInternalFrame implements IObservador {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static ProductoPantalla instance;
	
	public static ProductoPantalla getInstance(){
		if (instance == null)
			instance = new ProductoPantalla();
		
		return instance;
	}
	
	JLabel lblProductos, lblIdProducto, lblCodProducto, lblTitulo, lblDescripcion, lblPrecio;
	JTextField txtIdProducto, txtCodProducto, txtTitulo, txtDescripcion, txtPrecio;
	JButton btnGuardar, btnCancelar, btnSeleccionarProducto, btnEliminarProducto;
	JComboBox<ProductoView> cmbProductos;
	
	Container cont = null;
	
	public ProductoPantalla(){
		configurar();
		eventos();
		Sistema.getInstance().agregarObservador(this);
	}
	
	private void configurar(){
		
		cont = this.getContentPane();
		cont.setLayout(null);
		
		lblProductos = new JLabel("Productos");
		lblProductos.setBounds(10, 20, 200, 30);
		cont.add(lblProductos);	
		
		cmbProductos = new JComboBox<ProductoView>();
		cmbProductos.setBounds(215, 20, 200, 30);
		cont.add(cmbProductos);
		
		this.cargarProductos();
		
		btnSeleccionarProducto = new JButton("Seleccionar");
		btnSeleccionarProducto.setBounds(420, 20, 150, 30);
		cont.add(btnSeleccionarProducto);
		
		btnEliminarProducto = new JButton("Eliminar");
		btnEliminarProducto.setBounds(420, 55, 150, 30);
		cont.add(btnEliminarProducto);
		
		lblIdProducto = new JLabel("ID. de Producto");
		lblIdProducto.setBounds(10, 55, 200, 30);
		cont.add(lblIdProducto);
		
		txtIdProducto= new JTextField();
		txtIdProducto.setBounds(215, 55, 200, 30);
		txtIdProducto.setEnabled(false);
		cont.add(txtIdProducto);
		
		lblCodProducto = new JLabel("Codigo");
		lblCodProducto.setBounds(10, 90, 200, 30);
		cont.add(lblCodProducto);
		
		txtCodProducto = new JTextField();
		txtCodProducto.setBounds(215, 90, 200, 30);
		cont.add(txtCodProducto);

		lblTitulo = new JLabel("Titulo");
		lblTitulo.setBounds(10, 125, 200, 30);
		cont.add(lblTitulo);
		
		txtTitulo= new JTextField();
		txtTitulo.setBounds(215, 125, 200, 30);
		cont.add(txtTitulo);
		
		lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(10, 160, 200, 30);
		cont.add(lblDescripcion);	

		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(215, 160, 200, 30);
		cont.add(txtDescripcion);
		
		lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(10, 195, 200, 30);
		cont.add(lblPrecio);
		
		txtPrecio = new JTextField();
		txtPrecio.setBounds(215, 195, 200, 30);
		cont.add(txtPrecio);
				
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
					
				Integer idProducto = null;
				
				if (!txtIdProducto.getText().isEmpty())
					idProducto = Integer.valueOf(txtIdProducto.getText());
				
				try {
					String codigo = txtCodProducto.getText();
					String titulo = txtTitulo.getText();
					String descripcion = txtDescripcion.getText();
					Float precio = 0F;
					
					if (!txtPrecio.getText().isEmpty())
						precio = Float.valueOf(txtPrecio.getText());
								
					if (idProducto == null){
						Integer idNuevoProducto = Sistema.getInstance().agregarProducto(codigo, titulo, descripcion, precio);
						txtIdProducto.setText(idNuevoProducto.toString());
						JOptionPane.showMessageDialog(null, "Producto guardado con éxito! ", "Admin. Productos", JOptionPane.INFORMATION_MESSAGE);
						actualizar();
					}else{
						Sistema.getInstance().modificarProducto(idProducto, codigo, titulo, descripcion, precio);
						JOptionPane.showMessageDialog(null, "Producto guardado con éxito! ", "Admin. Productos", JOptionPane.INFORMATION_MESSAGE);
						actualizar();
					}
				} catch (NegocioException e1) { 
					JOptionPane.showMessageDialog(null, "Error: " + e1.getMessage(), "Admin. Productos", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btnSeleccionarProducto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				ProductoView producto = (ProductoView) cmbProductos.getSelectedItem();
				
				if (producto.getIdProducto() != null){
					txtIdProducto.setText(producto.getIdProducto().toString());
					txtCodProducto.setText(producto.getCodigo());
					txtTitulo.setText(producto.getTitulo());
					txtDescripcion.setText(producto.getDescripcion());
					txtPrecio.setText(String.valueOf(producto.getPrecio()));
					btnEliminarProducto.setEnabled(true);
				} else {
					txtIdProducto.setText("");
					txtCodProducto.setText("");
					txtTitulo.setText("");
					txtDescripcion.setText("");
					txtPrecio.setText("");
					btnEliminarProducto.setEnabled(false);
				}
			}
		});
		
		btnEliminarProducto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Integer idProducto = Integer.valueOf(txtIdProducto.getText());
								
				try {
					Sistema.getInstance().eliminarProducto(idProducto);
					JOptionPane.showMessageDialog(null, "Producto eliminado con éxito! ", "Admin. Productos", JOptionPane.INFORMATION_MESSAGE);
					actualizar();
				} catch (NegocioException e1) { 
					JOptionPane.showMessageDialog(null, "Error: " + e1, "Admin. Productos", JOptionPane.ERROR_MESSAGE);
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
		btnEliminarProducto.setEnabled(false);
		cmbProductos.setSelectedIndex(0);
		txtIdProducto.setText("");
		txtCodProducto.setText("");
		txtTitulo.setText("");
		txtDescripcion.setText("");
		txtPrecio.setText("");
	}
	
	private void cargarProductos(){
		try {
			List<ProductoView> productoViews = Sistema.getInstance().obtenerTodosLosProductos();
			cmbProductos.removeAllItems();
			cmbProductos.addItem(new ProductoView("Nuevo Producto"));
			productoViews.forEach(c -> cmbProductos.addItem(c));
		} catch (NegocioException e) {
			JOptionPane.showMessageDialog(null, "Error: no se pudo cargar productos. " + e, "Admin. Productos", JOptionPane.ERROR_MESSAGE);
		} 
	}
	
	@Override
	public void actualizar() {
		limpiarForm();
		cargarProductos();
	}
}
