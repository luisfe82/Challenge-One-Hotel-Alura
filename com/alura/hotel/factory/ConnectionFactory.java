package com.alura.hotel.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	//private DataSource datasource;
	
	//public ConnectionFactory() {
	//	var pooLedDataSource = new ComboPooledDataSource();
	//	pooLedDataSource.setJdbcUrl("jdbc:mysql://localhost/hotel?useTimeZone=true&serverTimeZone=UTC");
	//	pooLedDataSource.setUser("root");
	//	pooLedDataSource.setPassword("luis2891");
	//	pooLedDataSource.setMaxPoolSize(10);
		
	//	this.datasource = pooLedDataSource;
	//}
	
	//public Connection recuperaConexion(){
		//try {
		//	return this.datasource.getConnection();			
		//} catch (SQLException e) {
		//	throw new RuntimeException(e);
		//}
	//}
	public Connection recuperaConexion() throws SQLException {
		return DriverManager.getConnection(
                "jdbc:mysql://localhost/hotel?useTimeZone=true&serverTimeZone=UTC",
                "root", 
                "luis2891");
	}

}
