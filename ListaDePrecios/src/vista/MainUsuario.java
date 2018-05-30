package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

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
		btnCrearLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame altaListaRegalos = new AltaListaRegalos();
				altaListaRegalos.setVisible(true);
			}
		});
		btnCrearLista.setBounds(360, 102, 97, 25);
		contentPane.add(btnCrearLista);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 211, 573, 89);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{new Integer(1), "Juan Gomez", "25/05/2018", "25/06/2018", "Activo", "No"},
				{new Integer(2), "Carlos Garcia", "30/05/2018", "30/06/2018", "Activo", "S\u00EC"},
				{null, null, null, null, null, null},
			},
			new String[] {
				"C\u00F3digo", "Agasajado", "F. Inicio", "F. Fin", "Estado", "Pag\u00F3"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		table.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mousePressed(MouseEvent me) {
		    	JTable table =(JTable) me.getSource();
		    	Point p = me.getPoint();
		    	int row = table.rowAtPoint(p);
		    	
		    	if (me.getClickCount() == 2) {
	    			int linea = table.getSelectedRow();
	    			String codigo = table.getValueAt(linea, 0).toString();
	    			//Aca llamamos a la ventana que nos traera el los detalles del registro
		    	}
		    }
		});		
		
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
				{new Integer(3), "Ruben Ruiz", "26/05/2018", "26/06/2018", "Activo", new Float(1500.0f)},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"C\u00F3digo", "Agasajado", "F. Inicio", "F. Fin", "Estado", "Monto Recaudado"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Object.class, Object.class, Object.class, Object.class, Float.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_1.getColumnModel().getColumn(1).setPreferredWidth(130);
		table_1.getColumnModel().getColumn(5).setPreferredWidth(123);
		scrollPane_1.setViewportView(table_1);
		
		table_1.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mousePressed(MouseEvent me) {
		    	JTable table =(JTable) me.getSource();
		    	Point p = me.getPoint();
		    	int row = table.rowAtPoint(p);
		    	
		    	if (me.getClickCount() == 2) {
	    			int linea = table.getSelectedRow();
	    			String codigo = table.getValueAt(linea, 0).toString();
	    			//Aca llamamos a la ventana que nos traera el los detalles del registro
		    	}
		    }
		});			
	}
}
