package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controlador.ControladorListaRegalos;
import controlador.ControladorUsuario;
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

public class Administracion extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Administracion frame = new Administracion();
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
	public Administracion() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		
		Map<Integer,Usuario> usuarios = ControladorUsuario.GetInstance().GetUsuarios();
		table = new JTable();
		CompletarModeloUsuarios(usuarios, table);
		scrollPane.setViewportView(table);
		
		
		 Action edit = new AbstractAction()
	    	{
	    	    public void actionPerformed(ActionEvent e)
	    	    {
	    	    	System.out.println("Editar");
	    	    	JTable tbl = (JTable)e.getSource();
			        int modelRow = Integer.valueOf( e.getActionCommand() );
			        //obtengo la fila que se seleccionó y el usuario que corresponde
			        Vector row = (Vector) ((DefaultTableModel)tbl.getModel()).getDataVector().elementAt(modelRow);
			        Usuario usuario = ControladorUsuario.GetInstance().GetUsuario(Integer.valueOf(row.get(0).toString()));
			        JFrame abmUsuario = new ABMUsuario(usuario);
					abmUsuario.setVisible(true);
					dispose();
	    	    }
	    	};		
	    	ButtonColumn bEdit = new ButtonColumn(table,edit,6);
	    	bEdit.setMnemonic(KeyEvent.VK_D);
	    	
		JButton btnNuevoUsuario = new JButton("Nuevo Usuario");
		btnNuevoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame abmUsuario = new ABMUsuario(null);
				abmUsuario.setVisible(true);
				dispose();
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
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null},
				{null},
				{null},
			},
			new String[] {
				"User"
			}
		));
		scrollPane_1.setViewportView(table_1);
		
		JButton btnNuevoAdministrador = new JButton("Nuevo Administrador");
		btnNuevoAdministrador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame abmadmin = new ABMAdministrador();
				abmadmin.setVisible(true);
			}
		});
		btnNuevoAdministrador.setBounds(369, 263, 157, 25);
		contentPane.add(btnNuevoAdministrador);
	}
	
	private void CompletarModeloUsuarios(Map<Integer,Usuario> usuarios, JTable tbl) {
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
		
		for (Map.Entry<Integer, Usuario> e : usuarios.entrySet()) {
		        dtm.addRow(new Object[] {
		        		e.getValue().getCodigo(),
		        		e.getValue().getUser(),
		        		e.getValue().getNombre(),
						e.getValue().getApellido(),
						e.getValue().getDNI(),
						e.getValue().getMail(),
						"Editar"
	        	});
		}
		
	}

}
