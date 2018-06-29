package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DTO.AdministradorDTO;
import DTO.UsuarioDTO;
import controlador.ControladorListaRegalos;
import controlador.ControladorUsuario;
import controlador.SistemaRegalos;
import modelo.Administrador;
import modelo.ListaDeRegalos;
import modelo.ParticipanteLista;
import modelo.Usuario;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Map;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Administracion extends JFrame {

	private JPanel contentPane;
	public JTable table;
	private JTable table_1;
	private Administracion main;
	/**
	 * Create the frame.
	 */
	public Administracion() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(".\\img\\regalos.png"));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(e.getComponent(),
			            "¿Realmente desea salir?", "Sistema de Lista de regalos - Salir",
			            JOptionPane.YES_NO_OPTION);
				
			        if (result == JOptionPane.YES_OPTION) {
			        	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			        	LoginAdministrador login = new LoginAdministrador();
						login.setVisible(true);			        	
			          }
			        else if (result == JOptionPane.NO_OPTION)
			          setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

			}
		});
		
		main = this;
		
		setTitle("Sistema de Lista de Regalos - Administraci\u00F3n de Usuarios");
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 609, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAdministracin = new JLabel("ADMINISTRACI\u00D3N");
		lblAdministracin.setForeground(Color.RED);
		lblAdministracin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblAdministracin.setBounds(12, 13, 232, 29);
		contentPane.add(lblAdministracin);
		
		JLabel lblListadoDeUsuarios = new JLabel("Listado de Usuarios");
		lblListadoDeUsuarios.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblListadoDeUsuarios.setBounds(12, 89, 345, 16);
		contentPane.add(lblListadoDeUsuarios);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 119, 579, 113);
		contentPane.add(scrollPane);
		
		
		Map<Integer,UsuarioDTO> usuarios = ControladorUsuario.GetInstance().GetUsuarios();
		table = new JTable();
		CompletarModeloUsuarios(usuarios, table);
		scrollPane.setViewportView(table);
		
	    	
		JButton btnNuevoUsuario = new JButton("Nuevo Usuario");
		btnNuevoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame abmUsuario = new ABMUsuario(null,main);
				abmUsuario.setVisible(true);
			}
		});
		btnNuevoUsuario.setBounds(369, 85, 133, 25);
		contentPane.add(btnNuevoUsuario);
		
		JLabel lblListadoDeAdministradores = new JLabel("Listado de Administradores");
		lblListadoDeAdministradores.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblListadoDeAdministradores.setBounds(12, 267, 216, 16);
		contentPane.add(lblListadoDeAdministradores);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 296, 567, 113);
		contentPane.add(scrollPane_1);
		
		Map<String,AdministradorDTO> administradores = ControladorUsuario.GetInstance().GetAdministradores();
		table_1 = new JTable();
		CompletarModeloAdministradores(administradores, table_1);
		scrollPane_1.setViewportView(table_1);
		
		JButton btnNuevoAdministrador = new JButton("Nuevo Administrador");
		btnNuevoAdministrador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame abmadmin = new ABMAdministrador(main);
				abmadmin.setVisible(true);
			}
		});
		btnNuevoAdministrador.setBounds(369, 263, 157, 25);
		contentPane.add(btnNuevoAdministrador);
	}

	public void CompletarModeloAdministradores(Map<String,AdministradorDTO> administradores, JTable tbl) {
		String[] columnaAdministradores = new String[] {
				"User"
			};
		DefaultTableModel dtm = new DefaultTableModel() {
			boolean[] columnEditables = new boolean[] {
					false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
			};
		dtm.setColumnIdentifiers(columnaAdministradores);
		
		tbl.setModel(dtm);
		
		for (Map.Entry<String, AdministradorDTO> e : administradores.entrySet()) {
		        dtm.addRow(new Object[] {
		        		e.getValue().user
	        	});
		}
		
	}	
	public void refrescar() {
		CompletarModeloAdministradores(ControladorUsuario.GetInstance().GetAdministradores(), table_1);
		
	}
	
	private void CompletarModeloUsuarios(Map<Integer,UsuarioDTO> usuarios, JTable tbl) {
		String[] columnaUsuarios = new String[] {
				"C\u00F3digo", "User", "Nombre", "Apellido", "DNI", "E-mail", ""
			};
		DefaultTableModel dtm = new DefaultTableModel() {
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
			};
		dtm.setColumnIdentifiers(columnaUsuarios);
		
		tbl.setModel(dtm);
		
		for (Map.Entry<Integer, UsuarioDTO> e : usuarios.entrySet()) {
		        dtm.addRow(new Object[] {
		        		e.getValue().codigo,
		        		e.getValue().user,
		        		e.getValue().nombre,
						e.getValue().apellido,
						e.getValue().DNI,
						e.getValue().mail,
						"Editar"
	        	});
		}
		 Action edit = new AbstractAction()
	    	{
	    	    public void actionPerformed(ActionEvent e)
	    	    {
	    	    	System.out.println("Editar");
	    	    	JTable tbl = (JTable)e.getSource();
			        int modelRow = Integer.valueOf( e.getActionCommand() );
			        //obtengo la fila que se seleccionó y el usuario que corresponde
			        Vector row = (Vector) ((DefaultTableModel)tbl.getModel()).getDataVector().elementAt(modelRow);
			        UsuarioDTO usuario = ControladorUsuario.GetInstance().GetUsuario(Integer.valueOf(row.get(0).toString()));
			        JFrame abmUsuario = new ABMUsuario(usuario,main);
			        abmUsuario.setVisible(true);
	    	    }
	    	};		
	    	ButtonColumn bEdit = new ButtonColumn(table,edit,6);
	    	bEdit.setMnemonic(KeyEvent.VK_D);
	}
	
	public void refrescarUsuario(JTable tablaUsuario) {
		CompletarModeloUsuarios(ControladorUsuario.GetInstance().GetUsuarios(),tablaUsuario);
		
	}
	
	public void refrescarAdministrador(JTable tablaAdmin) {
		CompletarModeloAdministradores(ControladorUsuario.GetInstance().GetAdministradores(),tablaAdmin);
		
	}

}
