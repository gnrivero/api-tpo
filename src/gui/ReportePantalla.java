package gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import controller.Sistema;
import dao.ReclamoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;
import excepciones.NegocioException;
import observer.IObservador;

public class ReportePantalla extends JInternalFrame implements IObservador {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static ReportePantalla instance;

	public static ReportePantalla getInstance() throws AccesoException, ConexionException, NegocioException, SQLException {
		if (instance == null)
			instance = new ReportePantalla();

		return instance;
	}

	JLabel lblRankingClientes, lblCantReclamosMes, lblRankingTratados, lblTiempoPromedio;
	JTable tblRankingClientes, tblCantReclamosMes, tblRankingTratados, tblTiempoPromedio;
	JButton btnSalir;

	Container cont = null;

	public ReportePantalla() throws AccesoException, ConexionException, NegocioException, SQLException {
		configurar();
		eventos();
		Sistema.getInstance().agregarObservador(this);
	}

	private void configurar() throws AccesoException, ConexionException, NegocioException, SQLException {
		cont = this.getContentPane();
		cont.setLayout(null);

		// cantidad de reclamos tratados por mes 

		lblCantReclamosMes = new JLabel("Cantidad de reclamos por mes");
		lblCantReclamosMes.setBounds(10, 10, 230, 30);
		cont.add(lblCantReclamosMes);
		
		ResultSet rs1 = ReclamoDAO.getInstancia().cantReclamosMes();
		tblCantReclamosMes = new JTable(rsCompleteTable(rs1));
		tblCantReclamosMes.setBounds(10, 40, 380, 200);
		tblCantReclamosMes.setEnabled(false);
		cont.add(tblCantReclamosMes);
		
		// ranking de clientes con mayor cantidad de reclamos
		
		lblRankingClientes = new JLabel("Ranking de clientes con mayor cantidad de reclamos");
		lblRankingClientes.setBounds(420, 10, 380, 30);
		cont.add(lblRankingClientes);
		
		ResultSet rs2 = ReclamoDAO.getInstancia().rankingClientes();
		tblRankingClientes = new JTable(rsCompleteTable(rs2));
		tblRankingClientes.setBounds(420, 40, 380, 200);
		tblRankingClientes.setEnabled(false);
		cont.add(tblRankingClientes);
		
		// tiempo promedio de respuesta de los reclamos por responsable
		
		lblTiempoPromedio = new JLabel("Tiempo promedio de respuesta por responsable");
		lblTiempoPromedio.setBounds(10, 270, 380, 30);
		cont.add(lblTiempoPromedio);
		
		ResultSet rs3 = ReclamoDAO.getInstancia().tiempoPromedio();
		tblTiempoPromedio = new JTable(rsCompleteTable(rs3));
		tblTiempoPromedio.setBounds(10, 310, 380, 200);
		tblTiempoPromedio.setEnabled(false);
		cont.add(tblTiempoPromedio);
		
		// ranking de tratamiento de reclamos
		
		lblRankingTratados = new JLabel("Ranking de tratamiento de reclamos");
		lblRankingTratados.setBounds(420, 270, 380, 30);
		cont.add(lblRankingTratados);
		
		ResultSet rs4 = ReclamoDAO.getInstancia().rankingTratados();
		tblRankingTratados = new JTable(rsCompleteTable(rs4));
		tblRankingTratados.setBounds(420, 310, 380, 200);
		tblRankingTratados.setEnabled(false);
		cont.add(tblRankingTratados);
		
		//--
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(310, 530, 190, 30);
		cont.add(btnSalir);

		this.pack();
		this.setLocation(10, 100);
		this.setSize(820, 630);;
		this.setClosable(true);
	}

	private void eventos() {
		btnSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
	
	public static DefaultTableModel rsCompleteTable(ResultSet rs)
	        throws SQLException {

	    ResultSetMetaData metaData = rs.getMetaData();

	    // columns
	    Vector<String> columnas = new Vector<String>();
	    int cantColumnas = metaData.getColumnCount();
	    for (int columna = 1; columna <= cantColumnas; columna++) {
	        columnas.add(metaData.getColumnName(columna));
	    }

	    // data
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int idColumna = 1; idColumna <= cantColumnas; idColumna++) {
	            vector.add(rs.getObject(idColumna));
	        }
	        data.add(vector);
	    }
	    return new DefaultTableModel(data, columnas);
	}

	@Override
	public void actualizar() {
		ResultSet rs1 = null;
		try {
			rs1 = ReclamoDAO.getInstancia().cantReclamosMes();
		} catch (AccesoException | ConexionException | NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			tblCantReclamosMes.setModel(rsCompleteTable(rs1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ResultSet rs2 = null;
		try {
			rs2 = ReclamoDAO.getInstancia().rankingClientes();
		} catch (AccesoException | ConexionException | NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			tblRankingClientes.setModel(rsCompleteTable(rs2));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ResultSet rs3 = null;
		try {
			rs3 = ReclamoDAO.getInstancia().tiempoPromedio();
		} catch (AccesoException | ConexionException | NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			tblTiempoPromedio.setModel(rsCompleteTable(rs3));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ResultSet rs4 = null;
		try {
			rs4 = ReclamoDAO.getInstancia().rankingTratados();
		} catch (AccesoException | ConexionException | NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			tblRankingTratados.setModel(rsCompleteTable(rs4));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
