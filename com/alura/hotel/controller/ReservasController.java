package com.alura.hotel.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import com.alura.hotel.factory.ConnectionFactory;

public class ReservasController {
	
	public int modificar(int id, String fechaentrada, String fechasalida, int valor , String formadepago) throws SQLException {
		Connection con = new ConnectionFactory().recuperaConexion();
		
		Statement statement =  con.createStatement();
		
		statement.execute("UPDATE RESERVAS SET "
                + " ID = "+ id + ","
                + " FECHAENTRADA = '" + fechaentrada + "'," 
                + " FECHASALIDA = '" + fechasalida + "',"
                + " VALOR = " + valor + ","
                + " FORMADEPAGO = '" + formadepago + "'"
                + " WHERE ID = " + id
                );
		
		return statement.getUpdateCount();
	}
	
	public int eliminar(Integer id) throws SQLException {
		Connection con = new ConnectionFactory().recuperaConexion();
		
		Statement statement =  con.createStatement();
		
		statement.execute("DELETE FROM RESERVAS WHERE ID = "+id);
		
		return statement.getUpdateCount();
		
		
		
		
	}
	
	public List<Map<String, String>> listarR() throws SQLException{
		Connection con = new ConnectionFactory().recuperaConexion();

        Statement statement =  con.createStatement();
        
        statement.execute("SELECT ID,FECHAENTRADA,FECHASALIDA,VALOR,FORMADEPAGO FROM RESERVAS");
        
        ResultSet resultSet =  statement.getResultSet();
        
        List<Map<String , String>> resultado = new ArrayList<>();
        
        while(resultSet.next()) {
        	Map<String, String> fila = new HashMap<>();
        	fila.put("ID", String.valueOf(resultSet.getInt("ID")));
        	fila.put("FECHAENTRADA", String.valueOf(resultSet.getDate("FECHAENTRADA")));
        	fila.put("FECHASALIDA", String.valueOf(resultSet.getDate("FECHASALIDA")));
        	fila.put("VALOR", String.valueOf(resultSet.getInt("VALOR")));
        	fila.put("FORMADEPAGO", String.valueOf(resultSet.getString("FORMADEPAGO")));
        	
        	resultado.add(fila);
        } 
        
        con.close();
		
		return resultado ;
	}
	
	public void guardar(Map<String, String> reservacion) throws SQLException {
		Connection con = new ConnectionFactory().recuperaConexion();
		
		Statement statement =  con.createStatement();
		
		statement.execute("INSERT INTO RESERVAS(fechaentrada,fechasalida,valor,formadepago) "
				+ " VALUES('" + reservacion.get("FECHAENTRADA") + "','"
				+ reservacion.get("FECHASALIDA") + "',"
				+ reservacion.get("VALOR") + ",'"
				+ reservacion.get("FORMADEPAGO") + "')", Statement.RETURN_GENERATED_KEYS);
		
		ResultSet resultSet = statement.getGeneratedKeys();
		
		while(resultSet.next()) {
			JOptionPane.showMessageDialog(null, "Fue ingresada la reservacion con el ID :" + resultSet.getInt(1));
			resultSet.getInt(1);
		}
	}
}
