package com.alura.hotel.views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.alura.hotel.controller.HuespedesController;
import com.alura.hotel.controller.ReservasController;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.util.Optional;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloH;
	private JLabel labelAtras;
	private JLabel labelExit;
	private JTabbedPane panel;
	int xMouse, yMouse;	
	private HuespedesController huespedesController;
	private ReservasController reservasController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Busqueda() {
		super("Huespedes");
		
		this.huespedesController = new HuespedesController();
		this.reservasController = new ReservasController(); 			
		
		colocarPanel();
	}
	
	private void colocarPanel() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/com/alura/hotel/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); 
		setUndecorated(true);
		
		colocarConfigurarTablas();
		colocarConfigararEtiquetas();
		colocarConfigurarBotones();
	}

	private void colocarConfigurarBotones() {
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);			     
			}
		});		
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				modificar();
			}
		});
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnEliminar = new JPanel();
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				eliminar();
			}
		});
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
		
	}

	private void colocarConfigararEtiquetas() {
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/com/alura/hotel/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
	}
	
	private void colocarConfigurarTablas() {		
		
		panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);		
		
		
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/com/alura/hotel/imagenes/reservado.png")), tbReservas, null);
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		
		cargarTablaReservas();
		
		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/com/alura/hotel/imagenes/pessoas.png")), tbHuespedes, null);
		modeloH = (DefaultTableModel) tbHuespedes.getModel();
		modeloH.addColumn("Nº Id");
		//modeloH.addColumn("Numero de Huesped");
		modeloH.addColumn("Nombre");
		modeloH.addColumn("Apellido");
		modeloH.addColumn("Fecha de Nacimiento");
		modeloH.addColumn("Nacionalidad");
		modeloH.addColumn("Telefono");
		modeloH.addColumn("Numero de Reserva");
		
		cargarTablaHuespedes();			
	}
	private boolean tieneFilaElegidaHuespedes() {
		return tbHuespedes.getSelectedRowCount() == 0 || tbHuespedes.getSelectedColumnCount() == 0;
	}
	
	private boolean tieneFilaElegidaReservas() {
		return tbReservas.getSelectedRowCount() == 0 || tbReservas.getSelectedColumnCount() == 0;
	}
	
	private void modificar() {
		switch (panel.getSelectedIndex()) {
		case 0:
			if(tieneFilaElegidaReservas() ) {
				JOptionPane.showMessageDialog(this, "Por favor, elije un item");
			}else {
				Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
				.ifPresentOrElse(fila -> {
					Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
					String fechaentrada = modelo.getValueAt(tbReservas.getSelectedRow(), 1).toString();
					String fechasalida = modelo.getValueAt(tbReservas.getSelectedRow(), 2).toString();
					Integer valor = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 3).toString());
					String formadepago = modelo.getValueAt(tbReservas.getSelectedRow(), 4).toString();
					
					int cantidadModificada;
					
					try {
						cantidadModificada = this.reservasController.modificar(id,fechaentrada,fechasalida,valor,formadepago);
					} catch (SQLException e) {
						throw new RuntimeException(e);
					}				
					
					
					JOptionPane.showMessageDialog(this,cantidadModificada + " Item modificado con éxito!");
					
				},() -> JOptionPane.showMessageDialog(this,"Porfavor, elije un item"));
			}
			break;
		case 1:
			if(tieneFilaElegidaHuespedes()) {
				JOptionPane.showMessageDialog(this, "Por favor, elije un item");
			}else {
				Optional.ofNullable(modeloH.getValueAt(tbHuespedes.getSelectedRow(),tbHuespedes.getSelectedColumn()))
					.ifPresentOrElse(fila ->{
						Integer id = Integer.valueOf(modeloH.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
						String nombre = modeloH.getValueAt(tbHuespedes.getSelectedRow(), 1).toString();
						String apellido =  modeloH.getValueAt(tbHuespedes.getSelectedRow(), 2).toString();
						String fechanacimiento = modeloH.getValueAt(tbHuespedes.getSelectedRow(), 3).toString();
						String nacionalidad = modeloH.getValueAt(tbHuespedes.getSelectedRow(), 4).toString();
						String telefono = modeloH.getValueAt(tbHuespedes.getSelectedRow(), 5).toString();
						Integer id_reserva = Integer.valueOf(modeloH.getValueAt(tbHuespedes.getSelectedRow(), 6).toString());
						
						int cantidadModificada2;
						
						try {
							cantidadModificada2 = this.huespedesController.modificarH(id,nombre,apellido,fechanacimiento,nacionalidad,telefono,id_reserva);
						} catch (SQLException e) {
							throw new RuntimeException(e);
						}								
						
						JOptionPane.showMessageDialog(this,cantidadModificada2 +  " Item modificado con éxito!");
					
				},() -> JOptionPane.showMessageDialog(this,"Porfavor, elije un item"));
			}
			break;
		}
	}
	
	private void eliminar() {	
		
		switch (panel.getSelectedIndex()) {
		case 0:
			if(tieneFilaElegidaReservas() ) {
				JOptionPane.showMessageDialog(this, "Por favor, elije un item");
			}else {
			Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(),tbReservas.getSelectedColumn()))
				.ifPresentOrElse(fila ->{
					Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(),0).toString());
					
					int cantidadEliminada;
					
					try {
						cantidadEliminada = this.reservasController.eliminar(id);
					} catch (SQLException e) {
						throw new RuntimeException(e);
					}
					
					modelo.removeRow(tbReservas.getSelectedRow());
					
					JOptionPane.showMessageDialog(this,cantidadEliminada + " Item eliminado con éxito!");
					
				},() -> JOptionPane.showMessageDialog(this,"Porfavor, elije un item"));
			}
			break;
		case 1:
			if(tieneFilaElegidaHuespedes()) {
				JOptionPane.showMessageDialog(this, "Por favor, elije un item");
			}else {
				Optional.ofNullable(modeloH.getValueAt(tbHuespedes.getSelectedRow(),tbHuespedes.getSelectedColumn()))
					.ifPresentOrElse(fila ->{
						Integer id = Integer.valueOf(modeloH.getValueAt(tbHuespedes.getSelectedRow(),0).toString());
						
						int cantidadEliminada2;
						
						try {
							cantidadEliminada2 = this.huespedesController.eliminarH(id);
						} catch (SQLException e) {
							throw new RuntimeException(e);
						}
						
						modeloH.removeRow(tbHuespedes.getSelectedRow());
						
						JOptionPane.showMessageDialog(this,cantidadEliminada2 +  " Item eliminado con éxito!");
					
				},() -> JOptionPane.showMessageDialog(this,"Porfavor, elije un item"));
			}
			break;
		}
	}

	private void cargarTablaHuespedes() { 
		try {
			var huespedes = this.huespedesController.listar();
			
			try {
				huespedes.forEach(huesped -> modeloH.addRow(new Object[] {
						huesped.get("ID"),
						huesped.get("NOMBRE"),
						huesped.get("APELLIDO"),
						huesped.get("FECHANACIMIENTO"),
						huesped.get("NACIONALIDAD"),
						huesped.get("TELEFONO"),
						huesped.get("ID_RESERVA")
						}));
			}catch(Exception e) {
				throw e;
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}		
	}
	
	/**
	 * 
	 */
	private void cargarTablaReservas() {		
		try {
			var reservas = this.reservasController.listarR();
			
			try {
				reservas.forEach(reserva -> modelo.addRow(new Object[] {
						reserva.get("ID"),
						reserva.get("FECHAENTRADA"),
						reserva.get("FECHASALIDA"),
						reserva.get("VALOR"),
						reserva.get("FORMADEPAGO")
						}));
			}catch(Exception e) {
				throw e;
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		} 
		
	}

//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX(); 
	        yMouse = evt.getY();  
	    } 

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen(); 
	        int y = evt.getYOnScreen(); 
	        this.setLocation(x - xMouse, y - yMouse); 
}
}
