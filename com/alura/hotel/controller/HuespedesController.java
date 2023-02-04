package com.alura.hotel.controller;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alura.hotel.factory.ConnectionFactory;




public class HuespedesController {
	public int modificarH(int id, String nombre, String apellido, String fechanacimiento, String nacionalidad, String telefono, int id_reserva) throws SQLException {
		Connection con = new ConnectionFactory().recuperaConexion();
		
		Statement statement =  con.createStatement();
		
		statement.execute("UPDATE HUESPEDES SET "
                + " ID = "+ id + ","
                + " NOMBRE = '" + nombre + "'," 
                + " APELLIDO = '" + apellido + "',"
                + " FECHANACIMIENTO = '" + fechanacimiento + "',"
                + " NACIONALIDAD = '" + nacionalidad + "',"
                + " TELEFONO = '" + telefono + "',"
                + " ID_RESERVA = " + id_reserva 
                + " WHERE ID = " + id
                );
		
		return statement.getUpdateCount();
	}
	
	public int eliminarH(Integer id) throws SQLException {
		Connection con = new ConnectionFactory().recuperaConexion();
		
		Statement statement =  con.createStatement();
		
		statement.execute("DELETE FROM HUESPEDES WHERE ID = "+id);
		
		return statement.getUpdateCount();
	}
	
	public List<Map<String, String>> listar() throws SQLException{
		Connection con = new ConnectionFactory().recuperaConexion();

        Statement statement =  con.createStatement();
        
        statement.execute("SELECT ID,NOMBRE,APELLIDO,FECHANACIMIENTO,NACIONALIDAD,TELEFONO,ID_RESERVA FROM HUESPEDES");
        
        ResultSet resultSet =  statement.getResultSet();
        
        List<Map<String , String>> resultado = new ArrayList<>();
        
        while(resultSet.next()) {
        	Map<String, String> fila = new HashMap<>();
        	fila.put("ID", String.valueOf(resultSet.getInt("ID")));
        	fila.put("NOMBRE", String.valueOf(resultSet.getString("NOMBRE")));
        	fila.put("APELLIDO", String.valueOf(resultSet.getString("APELLIDO")));
        	fila.put("FECHANACIMIENTO", String.valueOf(resultSet.getDate("FECHANACIMIENTO")));
        	fila.put("NACIONALIDAD", String.valueOf(resultSet.getString("NACIONALIDAD")));
        	fila.put("TELEFONO", String.valueOf(resultSet.getString("TELEFONO")));
        	fila.put("ID_RESERVA", String.valueOf(resultSet.getInt("ID_RESERVA")));
        	
        	resultado.add(fila);
        } 
        
        con.close();
		
		return resultado ;
	}
	
	public void guardarH(Map<String, String> huesped) throws SQLException  {
		Connection con = new ConnectionFactory().recuperaConexion();
		
		Statement statement =  con.createStatement();
		
		statement.execute("INSERT INTO HUESPEDES(nombre,apellido,fechanacimiento,nacionalidad,telefono,id_reserva) "
				+ " VALUES('" + huesped.get("NOMBRE") + "','"
				+ huesped.get("APELLIDO") + "','"
				+ huesped.get("FECHANACIMIENTO") + "','"
				+ huesped.get("NACIONALIDAD") + "','"
				+ huesped.get("TELEFONO") + "',"
				+ huesped.get("ID_RESERVA") + ")", Statement.RETURN_GENERATED_KEYS);
		
		ResultSet resultSet = statement.getGeneratedKeys();
		
		while(resultSet.next()) {
			System.out.println(
					String.format("Fue ingresada el huesped con el ID : %d",
							resultSet.getInt(1))
					);
			resultSet.getInt(1);
		}
	}
}
