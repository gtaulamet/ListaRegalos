package vista;

import java.awt.EventQueue;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DTO.ListaDeRegalosDTO;
import DTO.ParticipanteListaDTO;
import DTO.UsuarioDTO;
import controlador.ControladorListaRegalos;
import controlador.ControladorUsuario;
import controlador.SistemaRegalos;
import modelo.ListaDeRegalos;
import modelo.ParticipanteLista;
import modelo.Usuario;

import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Map;
import java.util.Vector;

import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.WindowFocusListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;

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

	private Map<Integer,ParticipanteListaDTO> participantes;
	private Map<Integer,UsuarioDTO> usuarios;
	private JFrame This;
	private JFrame main;
	
	/**
	 * Create the frame.
	 */
	public ABMListaDeRegalosAdmin(ListaDeRegalosDTO lr, JFrame m) {
		This = this;
		main = m;
		setResizable(false);
		setTitle("Sistema de Listas de Regalos - Lista Administrada");
		setIconImage(Toolkit.getDefaultToolkit().getImage(".\\img\\regalos.png"));
		
		this.participantes = ControladorListaRegalos.GetInstance().BuscarParticipantesLista(lr.codigo);
		this.usuarios = ControladorUsuario.GetInstance().GetUsuarios();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 712, 524);
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
		tfCodigo.setEditable(false);
		tfCodigo.setBounds(168, 54, 116, 22);
		contentPane.add(tfCodigo);
		tfCodigo.setColumns(10);
		tfCodigo.setText(String.valueOf(lr.codigo));
		
		tfNombreAgasajado = new JTextField();
		tfNombreAgasajado.setEditable(false);
		tfNombreAgasajado.setBounds(168, 83, 116, 22);
		contentPane.add(tfNombreAgasajado);
		tfNombreAgasajado.setColumns(10);
		tfNombreAgasajado.setText(lr.nombreAgasajado);
		
		tfMontoParticipante = new JTextField();
		tfMontoParticipante.setEditable(false);
		tfMontoParticipante.setBounds(168, 112, 116, 22);
		contentPane.add(tfMontoParticipante);
		tfMontoParticipante.setColumns(10);
		tfMontoParticipante.setText(String.valueOf(lr.montoARecaudarXIntegrante));
		
		tfMontoRecaudado = new JTextField();
		tfMontoRecaudado.setEditable(false);
		tfMontoRecaudado.setBounds(169, 141, 116, 22);
		contentPane.add(tfMontoRecaudado);
		tfMontoRecaudado.setColumns(10);
		tfMontoRecaudado.setText(String.valueOf(lr.montoRecaudado));
		
		JLabel lblFechaInicio = new JLabel("Fecha Inicio");
		lblFechaInicio.setBounds(354, 86, 84, 16);
		contentPane.add(lblFechaInicio);
		
		JLabel lblFechaFin = new JLabel("Fecha Fin");
		lblFechaFin.setBounds(354, 115, 66, 16);
		contentPane.add(lblFechaFin);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(354, 144, 56, 16);
		contentPane.add(lblEstado);
		
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String aux = format.format(lr.fechaInicio);		
		
		tfFechaInicio = new JTextField();
		tfFechaInicio.setEditable(false);
		tfFechaInicio.setBounds(460, 83, 116, 22);
		contentPane.add(tfFechaInicio);
		tfFechaInicio.setColumns(10);
		tfFechaInicio.setText(aux);
		
		aux = format.format(lr.fechaFin);
		tfFechaFin = new JTextField();
		tfFechaFin.setEditable(false);
		tfFechaFin.setBounds(460, 112, 116, 22);
		contentPane.add(tfFechaFin);
		tfFechaFin.setColumns(10);
		tfFechaFin.setText(aux);
		
		tfEstado = new JTextField();
		tfEstado.setEditable(false);
		tfEstado.setBounds(460, 141, 116, 22);
		contentPane.add(tfEstado);
		tfEstado.setColumns(10);
		tfEstado.setText(lr.estado);
		
		JLabel lblUsuarios = new JLabel("Usuarios");
		lblUsuarios.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUsuarios.setBounds(22, 204, 56, 16);
		contentPane.add(lblUsuarios);
		
		JLabel lblParticipantes = new JLabel("Participantes");
		lblParticipantes.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblParticipantes.setBounds(354, 204, 116, 16);
		contentPane.add(lblParticipantes);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 229, 317, 225);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table_1 = new JTable();
		CompletarModeloUsuarios(participantes, usuarios, table);

		table.getColumnModel().getColumn(3).setPreferredWidth(45);
		scrollPane.setViewportView(table);
		
		Action add = new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        JTable tbl = (JTable)e.getSource();
		        int modelRow = Integer.valueOf( e.getActionCommand() );
		        //obtengo la fila que se seleccionó y el usuario que corresponde
		        Vector row = (Vector) ((DefaultTableModel)tbl.getModel()).getDataVector().elementAt(modelRow);
		        UsuarioDTO usuario = ControladorUsuario.GetInstance().GetUsuario(Integer.valueOf(row.get(0).toString()));
		        
		        //Agrego usuario como participante de la lista
		        ControladorListaRegalos.GetInstance().AgregarParticipanteLista(Integer.valueOf(row.get(0).toString()), lr.codigo);
		        
		        //Actualizo modelo de la tabla de participantes
		        DefaultTableModel dtmP = (DefaultTableModel) table_1.getModel();
		        dtmP.addRow(new Object[] {usuario.codigo, usuario.apellido, usuario.nombre, "<<"});
		        dtmP.fireTableDataChanged();
		        
		        //Actualizo modelo de la tabla de usuarios
		        DefaultTableModel dtmU = (DefaultTableModel) tbl.getModel();
		        dtmU.removeRow(modelRow);
		        dtmU.fireTableDataChanged();
		    }
		};		
		ButtonColumn bAdd = new ButtonColumn(table,add,3);
		bAdd.setMnemonic(KeyEvent.VK_D);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(354, 230, 326, 224);
		contentPane.add(scrollPane_1);
		
		
		CompletarModeloParticipantes(participantes, table_1);		

		table_1.getColumnModel().getColumn(3).setPreferredWidth(45);
		scrollPane_1.setViewportView(table_1);
		
		Action remove = new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        JTable tbl = (JTable)e.getSource();
		        int modelRow = Integer.valueOf( e.getActionCommand() );
		        //obtengo la fila que seleccionó y el participante a remover de la lista.
		        Vector row = (Vector) ((DefaultTableModel)tbl.getModel()).getDataVector().elementAt(modelRow);
		        UsuarioDTO usuario = ControladorUsuario.GetInstance().GetUsuario(Integer.valueOf(row.get(0).toString()));
		        
		        //Doy de baja el participante de la lista
		        ControladorListaRegalos.GetInstance().BajarParticipanteLista(Integer.valueOf(row.get(0).toString()), lr.codigo);
		        
		        //Actualizo el modelo de la tabla de participantes
		        DefaultTableModel dtm = (DefaultTableModel) tbl.getModel();
				dtm.removeRow(modelRow);
		        dtm.fireTableDataChanged();
		        
		        //Actualizo el modelo de la tabla de usuarios
		        DefaultTableModel dtmU = (DefaultTableModel) table.getModel();
		        dtmU.addRow(new Object[] {usuario.codigo, usuario.apellido, usuario.nombre, ">>"});
		        dtmU.fireTableDataChanged();
		    }
		};		
		
		ButtonColumn bRemove = new ButtonColumn(table_1,remove,3);
		bRemove.setMnemonic(KeyEvent.VK_D);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(12, 37, 668, 148);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblSeHaEliminado = new JLabel("Se ha eliminado la lista de regalos");
		lblSeHaEliminado.setForeground(new Color(0, 128, 0));
		lblSeHaEliminado.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSeHaEliminado.setBounds(59, 460, 581, 16);
		contentPane.add(lblSeHaEliminado);
		lblSeHaEliminado.setVisible(false);
		
		JButton btnEliminarLista = new JButton("Eliminar Lista");
		btnEliminarLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(This,
			            "¿Realmente desea dar de baja la lista de regalos?", "Sistema de Lista de regalos - Dar de baja la Lista de Regalos",
			            JOptionPane.YES_NO_OPTION);
				
			        if (result == JOptionPane.YES_OPTION) {
			        	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						ControladorListaRegalos.GetInstance().BorrarListaRegalos(lr.codigo);
						lblSeHaEliminado.setVisible(true);
						
						MainUsuario aux=(MainUsuario) m;
						aux.refrescar(aux.table_1, aux.table);
			          }
			        else if (result == JOptionPane.NO_OPTION)
			          setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);				

			}
		});
		btnEliminarLista.setEnabled(false);
		btnEliminarLista.setBounds(508, 13, 148, 25);
		panel.add(btnEliminarLista);
		

		if (lr.montoRecaudado == Float.valueOf(0)) {
			btnEliminarLista.setEnabled(true);
		}
	}
	
	private void CompletarModeloParticipantes(Map<Integer,ParticipanteListaDTO> participantes, JTable tbl) {
		String[] columnaParticipantes = new String[] {
				"Codigo","Apellido", "Nombre", ""
			};
		DefaultTableModel dtm = new DefaultTableModel() {
			boolean[] columnEditables = new boolean[] {
					false,false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
			};
		dtm.setColumnIdentifiers(columnaParticipantes);
		
		tbl.setModel(dtm);
		
		for (Map.Entry<Integer, ParticipanteListaDTO> e : participantes.entrySet()) {
	        dtm.addRow(new Object[] {
	        		e.getValue().usuario.codigo,
					e.getValue().usuario.apellido,
					e.getValue().usuario.nombre,
					"<<"
			});
		}
		
	}

	private void CompletarModeloUsuarios(Map<Integer,ParticipanteListaDTO> participantes, Map<Integer,UsuarioDTO> usuarios, JTable tbl) {
		String[] columnaUsuarios = new String[] {
				"Codigo","Apellido", "Nombre", ""
			};
		DefaultTableModel dtm = new DefaultTableModel() {
			boolean[] columnEditables = new boolean[] {
					false, false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
			};
		dtm.setColumnIdentifiers(columnaUsuarios);
		
		tbl.setModel(dtm);
		UsuarioDTO usuarioActual = SistemaRegalos.GetInstance().getUsuarioLogueado();
		
		for (Map.Entry<Integer, UsuarioDTO> e : usuarios.entrySet()) {
			if (!participantes.containsKey( e.getKey()) && e.getKey() != usuarioActual.codigo) {
		        dtm.addRow(new Object[] {
		        		e.getValue().codigo,
						e.getValue().apellido,
						e.getValue().nombre,
						">>"
				});
			}
		}
		
	}
}
