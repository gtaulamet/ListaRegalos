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
import javax.swing.JButton;
import java.awt.event.ActionListener;
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
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"C\u00F3digo", "User", "Nombre", "Apellido", "DNI", "E-mail"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton btnNuevoUsuario = new JButton("Nuevo Usuario");
		btnNuevoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame abmUsuario = new ABMUsuario();
				//VER DE HACE DE OTRA FORMA
				abmUsuario.getContentPane().getComponents()[15].setEnabled(false);
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

}
