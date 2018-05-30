package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class MainUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField tfNombre;
	private JTextField tfApellido;
	private JTextField tfEmail;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUsuario frame = new MainUsuario();
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
	public MainUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 615, 521);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblUsuario.setBounds(12, 13, 177, 16);
		contentPane.add(lblUsuario);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(22, 47, 56, 16);
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(22, 76, 56, 16);
		contentPane.add(lblApellido);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(22, 106, 56, 16);
		contentPane.add(lblEmail);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(90, 44, 116, 22);
		contentPane.add(tfNombre);
		tfNombre.setColumns(10);
		
		tfApellido = new JTextField();
		tfApellido.setBounds(90, 73, 116, 22);
		contentPane.add(tfApellido);
		tfApellido.setColumns(10);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(90, 103, 116, 22);
		contentPane.add(tfEmail);
		tfEmail.setColumns(10);
		
		JButton btnCrearLista = new JButton("Crear Lista");
		btnCrearLista.setBounds(360, 102, 97, 25);
		contentPane.add(btnCrearLista);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 211, 573, 89);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"C\u00F3digo", "Agasajado", "F. Inicio", "F. Fin", "Estado", "Pag\u00F3"
			}
		));
		
		JLabel lblListasDeRegalo = new JLabel("Listas de Regalo - Participante");
		lblListasDeRegalo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblListasDeRegalo.setBounds(12, 182, 305, 16);
		contentPane.add(lblListasDeRegalo);
		
		JLabel lblListasDeRegalo_1 = new JLabel("Listas de Regalo - Administrador");
		lblListasDeRegalo_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblListasDeRegalo_1.setBounds(12, 336, 281, 16);
		contentPane.add(lblListasDeRegalo_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 365, 573, 96);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"C\u00F3digo", "Agasajado", "F. Inicio", "F. Fin", "Estado", "Monto Recaudado"
			}
		));
		table_1.getColumnModel().getColumn(1).setPreferredWidth(130);
		table_1.getColumnModel().getColumn(5).setPreferredWidth(123);
		scrollPane_1.setViewportView(table_1);
	}
}
