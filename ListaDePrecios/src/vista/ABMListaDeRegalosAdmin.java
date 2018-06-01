package vista;

import java.awt.EventQueue;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class ABMListaDeRegalosAdmin extends JFrame {

	private JPanel contentPane;
	private JTextField tfCodigo;
	private JTextField tfNombreAgasajado;
	private JTextField tfMontoParticipante;
	private JTextField tfMontoRecaudado;
	private JTextField tfFechaInicio;
	private JTextField tfFechaFin;
	private JTextField tfEstado;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ABMListaDeRegalosAdmin frame = new ABMListaDeRegalosAdmin();
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
	public ABMListaDeRegalosAdmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 712, 502);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblListaDeRegalos = new JLabel("LISTA DE REGALOS");
		lblListaDeRegalos.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblListaDeRegalos.setBounds(12, 13, 205, 25);
		contentPane.add(lblListaDeRegalos);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setBounds(22, 57, 56, 16);
		contentPane.add(lblCdigo);
		
		JLabel lblNombreAgasajado = new JLabel("Nombre Agasajado");
		lblNombreAgasajado.setBounds(22, 86, 109, 16);
		contentPane.add(lblNombreAgasajado);
		
		JLabel lblMontoPorParticipante = new JLabel("Monto por Participante");
		lblMontoPorParticipante.setBounds(22, 115, 161, 16);
		contentPane.add(lblMontoPorParticipante);
		
		JLabel lblMontoRecaudado = new JLabel("Monto Recaudado");
		lblMontoRecaudado.setBounds(22, 144, 135, 16);
		contentPane.add(lblMontoRecaudado);
		
		tfCodigo = new JTextField();
		tfCodigo.setBounds(168, 54, 116, 22);
		contentPane.add(tfCodigo);
		tfCodigo.setColumns(10);
		
		tfNombreAgasajado = new JTextField();
		tfNombreAgasajado.setBounds(168, 83, 116, 22);
		contentPane.add(tfNombreAgasajado);
		tfNombreAgasajado.setColumns(10);
		
		tfMontoParticipante = new JTextField();
		tfMontoParticipante.setBounds(168, 112, 116, 22);
		contentPane.add(tfMontoParticipante);
		tfMontoParticipante.setColumns(10);
		
		tfMontoRecaudado = new JTextField();
		tfMontoRecaudado.setBounds(169, 141, 116, 22);
		contentPane.add(tfMontoRecaudado);
		tfMontoRecaudado.setColumns(10);
		
		JLabel lblFechaInicio = new JLabel("Fecha Inicio");
		lblFechaInicio.setBounds(354, 86, 84, 16);
		contentPane.add(lblFechaInicio);
		
		JLabel lblFechaFin = new JLabel("Fecha Fin");
		lblFechaFin.setBounds(354, 115, 66, 16);
		contentPane.add(lblFechaFin);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(354, 144, 56, 16);
		contentPane.add(lblEstado);
		
		tfFechaInicio = new JTextField();
		tfFechaInicio.setBounds(460, 83, 116, 22);
		contentPane.add(tfFechaInicio);
		tfFechaInicio.setColumns(10);
		
		tfFechaFin = new JTextField();
		tfFechaFin.setBounds(460, 112, 116, 22);
		contentPane.add(tfFechaFin);
		tfFechaFin.setColumns(10);
		
		tfEstado = new JTextField();
		tfEstado.setBounds(460, 141, 116, 22);
		contentPane.add(tfEstado);
		tfEstado.setColumns(10);
		
		JLabel lblUsuarios = new JLabel("Usuarios");
		lblUsuarios.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUsuarios.setBounds(22, 204, 56, 16);
		contentPane.add(lblUsuarios);
		
		JLabel lblParticipantes = new JLabel("Participantes");
		lblParticipantes.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblParticipantes.setBounds(354, 204, 116, 16);
		contentPane.add(lblParticipantes);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 229, 317, 182);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, ">>"},
				{null, null, ">>"},
				{null, null, ">>"},
				{null, null, ">>"},
			},
			new String[] {
				"Apellido", "Nombre", ""
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(2).setPreferredWidth(15);
		scrollPane.setViewportView(table);
		
		Action add = new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        JTable table = (JTable)e.getSource();
		        int modelRow = Integer.valueOf( e.getActionCommand() );
		        //((DefaultTableModel)table.getModel()).removeRow(modelRow);
		        
		    }
		};		
		ButtonColumn bAdd = new ButtonColumn(table,add,2);
		bAdd.setMnemonic(KeyEvent.VK_D);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(354, 230, 326, 181);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, "<<"},
				{null, null, "<<"},
				{null, null, "<<"},
				{null, null, "<<"},
			},
			new String[] {
				"Apellido", "Nombre", ""
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table_1.getColumnModel().getColumn(2).setPreferredWidth(15);
		scrollPane_1.setViewportView(table_1);
		
		Action remove = new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        JTable table = (JTable)e.getSource();
		        int modelRow = Integer.valueOf( e.getActionCommand() );
		        //((DefaultTableModel)table.getModel()).removeRow(modelRow);
		        
		    }
		};		
		ButtonColumn bRemove = new ButtonColumn(table_1,remove,2);
		bRemove.setMnemonic(KeyEvent.VK_D);
				
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(479, 422, 97, 25);
		contentPane.add(btnGuardar);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(12, 37, 668, 148);
		contentPane.add(panel);
	}
}
