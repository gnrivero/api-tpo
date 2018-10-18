package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static ConnectionFactory instancia;
	//String connectionUrl = "jdbc:sqlserver://bd;databaseName=TPO_AI;user=AI_4084_08;password=AI_4084_08";//para ejecución en Laboratorio
	String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=TPO_AI;User=SA;Password=F3nix.83!";//para ejecución en localhost
	
	private ConnectionFactory() throws ClassNotFoundException{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	}
	
	public static ConnectionFactory getInstancia() throws ClassNotFoundException{
		if(instancia == null)
			instancia = new ConnectionFactory();
		return instancia;
	}
	
	public Connection getConection() throws SQLException{
		return DriverManager.getConnection(connectionUrl);
	}
}
