package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private String host;
	private String port;
	private String database;
	private String user;
	private String password;
	private String connectionUrl;
	
	
	//Singleton
	private static ConnectionFactory instancia = null;
	
	public static ConnectionFactory getInstancia() throws ClassNotFoundException{
		if(instancia == null)
			instancia = new ConnectionFactory();
		return instancia;
	}
	
	
	private ConnectionFactory() throws ClassNotFoundException {
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		
		//host = "bd"; port = "1433"; database = "TPO_AI"; user = "AI_4084_08"; password = "AI_4084_08";//LABO
		host = "localhost"; port = "1433"; database = "TPO_AI"; user = "SA"; password = "F3nix.83!";//LOCAL
		
		connectionUrl = "jdbc:sqlserver://" + host + ":" + port +";databaseName=" + database + ";User=" + user + ";Password=" + password;//para ejecución en localhost
	}		
	
	public Connection getConection() throws SQLException{
		return DriverManager.getConnection(connectionUrl);
	}
}
