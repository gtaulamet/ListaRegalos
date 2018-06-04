package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import controlador.ControladorListaRegalos;
import controlador.SistemaRegalos;
import modelo.ListaDeRegalos;
import modelo.Usuario;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import java.util.Map;
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
		Usuario u = SistemaRegalos.GetInstance().getUsuarioLogueado();
		Map<Integer, ListaDeRegalos> listaParticipante = ControladorListaRegalos.GetInstance().GetListasDelParticipante(u.getCodigo());
		Map<Integer, ListaDeRegalos> listaAdministrador = ControladorListaRegalos.GetInstance().GetListasAdministrador(u.getCodigo());
		
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
		tfNombre.setEditable(false);
		tfNombre.setBounds(90, 44, 116, 22);
		contentPane.add(tfNombre);
		tfNombre.setColumns(10);
		tfNombre.setText(u.getNombre());
		
		tfApellido = new JTextField();
		tfApellido.setEditable(false);
		tfApellido.setBounds(90, 73, 116, 22);
		contentPane.add(tfApellido);
		tfApellido.setColumns(10);
		tfApellido.setText(u.getApellido());
		
		tfEmail = new JTextField();
		tfEmail.setEditable(false);
		tfEmail.setBounds(90, 103, 116, 22);
		contentPane.add(tfEmail);
		tfEmail.setColumns(10);
		tfEmail.setText(u.getMail());
		
		JButton btnCrearLista = new JButton("Crear Lista");
		btnCrearLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame altaListaRegalos = new ABMListaRegalosParticipante(true);
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
		
		CompletarModeloListaParticipante(listaParticipante,table);

		table.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mousePressed(MouseEvent me) {
		    	JTable table =(JTable) me.getSource();
		    	Point p = me.getPoint();
		    	int row = table.rowAtPoint(p);
		    	
		    	if (me.getClickCount() == 2) {
	    			int linea = table.getSelectedRow();
	    			String codigo = table.getValueAt(linea, 0).toString();
	    			//Aca llamamos a la ventana que nos traera  los detalles del registro
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
		CompletarModeloListaAdministrador(listaAdministrador,table_1);

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
	    			ListaDeRegalos lr = ControladorListaRegalos.GetInstance().GetListaRegalos(Integer.parseInt(codigo));
	    			//Aca llamamos a la ventana que nos traera  los detalles del registro
	    			JFrame abmListaAdmin = new ABMListaDeRegalosAdmin(lr);
	    			abmListaAdmin.setVisible(true);
		    	}
		    }
		});			
	}

	
	private void CompletarModeloListaAdministrador(Map<Integer,ListaDeRegalos> listaAdministrador, JTable tbl) {
		String[] columnasAdministrador = new String[] {
				"C\u00F3digo", "Agasajado", "F. Inicio", "F. Fin", "Estado", "Monto Recaudado"
			};
		DefaultTableModel dtm = new DefaultTableModel() {
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
			};
		dtm.setColumnIdentifiers(columnasAdministrador);
		
		tbl.setModel(dtm);
		
		for (Map.Entry<Integer, ListaDeRegalos> e : listaAdministrador.entrySet()) {
	        dtm.addRow(new Object[] {
					e.getValue().getCodigo(),
					e.getValue().getNombreAgasajado(),
					e.getValue().getFechaInicio(),
					e.getValue().getFechaFin(),
					e.getValue().getEstado(),
					e.getValue().getMontoRecaudado()
			});
		}
		
	}

	
	private void CompletarModeloListaParticipante(Map<Integer,ListaDeRegalos> listaParticipante, JTable tbl) {
		String[] columnasParticipante = new String[] {
				"C\u00F3digo", "Agasajado", "F. Inicio", "F. Fin", "Estado", "Pag\u00F3"
			};

		DefaultTableModel dtm = new DefaultTableModel() {
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
			};
		dtm.setColumnIdentifiers(columnasParticipante);
		tbl.setModel(dtm);
		
		for (Map.Entry<Integer, ListaDeRegalos> e : listaParticipante.entrySet()) {
	        dtm.addRow(new Object[] {
					e.getValue().getCodigo(),
					e.getValue().getNombreAgasajado(),
					e.getValue().getFechaInicio(),
					e.getValue().getFechaFin(),
					e.getValue().getEstado(),
					"NO" //ver como completar este dato
			});
		}
	 
	}	
	private Object[][] CompletarModeloListaParticipante(Map<Integer, ListaDeRegalos> listaParticipante) {
		Object[][] aux = new Object [][] {};
		int k=0;
		for (Map.Entry<Integer, ListaDeRegalos> e : listaParticipante.entrySet()) {
			
				aux[k][0] = e.getValue().getCodigo();
				aux[k][1] = e.getValue().getNombreAgasajado();
				aux[k][2] = e.getValue().getFechaInicio();
				aux[k][3] = e.getValue().getFechaFin();
				aux[k][4] = e.getValue().getEstado();
				aux[k][5] = "NO"; //ver como hacemos para traer si pagó o no
				k++;
		}		
		return aux;
	}
}
