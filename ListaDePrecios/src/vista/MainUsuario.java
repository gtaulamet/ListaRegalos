package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Point;

import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import controlador.ControladorListaRegalos;
import controlador.SistemaRegalos;
import modelo.ListaDeRegalos;
import modelo.ParticipanteLista;
import modelo.Usuario;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.EventObject;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;

public class MainUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField tfNombre;
	private JTextField tfApellido;
	private JTextField tfEmail;
	public JTable table;
	public JTable table_1;
	private MainUsuario main;
	private Usuario u;
	/**
	 * Create the frame.
	 */
	public MainUsuario() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(e.getComponent(),
			            "¿Realmente desea salir?", "Sistema de Lista de regalos - Salir",
			            JOptionPane.YES_NO_OPTION);
				
			        if (result == JOptionPane.YES_OPTION) {
			        	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			        	Login login = new Login();
						login.setVisible(true);			        	
			          }
			        else if (result == JOptionPane.NO_OPTION)
			          setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

			}
		});
		main = this;
		setResizable(false);
		setTitle("Sistema de Lista de Regalos - Main Usuario");
		u = SistemaRegalos.GetInstance().getUsuarioLogueado();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
				JFrame altaListaRegalos = new ABMListaRegalosParticipante(true, new ListaDeRegalos(),main);
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
		
		CompletarModeloListaParticipante(SistemaRegalos.GetInstance().getListaParticipante(),table);

		table.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mousePressed(MouseEvent me) {
		    	JTable tbl =(JTable) me.getSource();
		    	Point p = me.getPoint();
		    	int row = tbl.rowAtPoint(p);
		    	
		    	if (me.getClickCount() == 2) {
	    			int linea = tbl.getSelectedRow();
	    			String codigo = tbl.getValueAt(linea, 0).toString();

	    			ListaDeRegalos lr = ControladorListaRegalos.GetInstance().GetListaRegalos(Integer.parseInt(codigo));
	    			JFrame abmListaParticipante = new ABMListaRegalosParticipante(false,lr, main);
	    			abmListaParticipante.setVisible(true);
		    	}
		    }
		});		
		
		JLabel lblListasDeRegalo = new JLabel("Listas de Regalo - Como participante");
		lblListasDeRegalo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblListasDeRegalo.setBounds(12, 182, 305, 16);
		contentPane.add(lblListasDeRegalo);
		
		JLabel lblListasDeRegalo_1 = new JLabel("Listas de Regalo - Como administrador");
		lblListasDeRegalo_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblListasDeRegalo_1.setBounds(12, 336, 281, 16);
		contentPane.add(lblListasDeRegalo_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 365, 573, 96);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		CompletarModeloListaAdministrador(SistemaRegalos.GetInstance().getListaAdministrador(),table_1);

		table_1.getColumnModel().getColumn(1).setPreferredWidth(130);
		table_1.getColumnModel().getColumn(5).setPreferredWidth(123);
		scrollPane_1.setViewportView(table_1);
		
		JButton btnRecargarlista = new JButton();
		btnRecargarlista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				refrescar(table_1,table);
			}
		});
		try {
			btnRecargarlista.setIcon(new ImageIcon(".\\img\\refresh.png"));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		btnRecargarlista.setBounds(515, 166, 32, 32);
		contentPane.add(btnRecargarlista);

		
		
		table_1.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mousePressed(MouseEvent me) {
		    	JTable tbl =(JTable) me.getSource();
		    	Point p = me.getPoint();
		    	int row = tbl.rowAtPoint(p);
		    	
		    	if (me.getClickCount() == 2) {
	    			int linea = tbl.getSelectedRow();
	    			String codigo = tbl.getValueAt(linea, 0).toString();
	    			ListaDeRegalos lr = ControladorListaRegalos.GetInstance().GetListaRegalos(Integer.parseInt(codigo));
	    			//Aca llamamos a la ventana que nos traera  los detalles del registro
	    			JFrame abmListaAdmin = new ABMListaDeRegalosAdmin(lr,main);
	    			abmListaAdmin.setVisible(true);
		    	}
		    }
		});	
	}

	//Completo el modelo de la tabla tbl con los datos de las listas en las que el usuario es administrador
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
		
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String fi = "";
		String ff = "";
		
		for (Map.Entry<Integer, ListaDeRegalos> e : listaAdministrador.entrySet()) {
			fi = format.format(e.getValue().getFechaInicio());
			ff = format.format(e.getValue().getFechaFin());
	        dtm.addRow(new Object[] {
					e.getValue().getCodigo(),
					e.getValue().getNombreAgasajado(),
					fi,
					ff,
					e.getValue().getEstado(),
					e.getValue().getMontoRecaudado()
			});
		}
		
	}

	//Completo el modelo de la tabla tbl con los datos de las listas en las que el usuario es participante
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
		
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String fi = "";
		String ff = "";		
		
		for (Map.Entry<Integer, ListaDeRegalos> e : listaParticipante.entrySet()) {
			String pago = "";
			Map<Integer, ParticipanteLista> aux = e.getValue().GetParticipantes();
			if (aux != null && aux.get(u.getCodigo()).isPago()) {
				pago ="Si";
			}else {
				pago ="No";
			}
			fi = format.format(e.getValue().getFechaInicio());
			ff = format.format(e.getValue().getFechaFin());
	        dtm.addRow(new Object[] {
					e.getValue().getCodigo(),
					e.getValue().getNombreAgasajado(),
					fi,
					ff,
					e.getValue().getEstado(),
					pago
			});
		}
	 
	}	
	public void refrescar(JTable adm, JTable part) {
		CompletarModeloListaAdministrador(SistemaRegalos.GetInstance().getListaAdministrador(), adm);
		CompletarModeloListaParticipante(SistemaRegalos.GetInstance().getListaParticipante(),part);
	}
}
