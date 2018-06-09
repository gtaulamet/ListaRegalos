package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import DTO.ListaDeRegalosDTO;
import DTO.UsuarioDTO;
import controlador.ControladorListaRegalos;
import controlador.ControladorUsuario;
import controlador.SistemaRegalos;
import modelo.ListaDeRegalos;
import modelo.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JFormattedTextField;

public class ABMListaRegalosParticipante extends JFrame {

	private JPanel contentPane;
	private JTextField tfAgasajado;
	private JFormattedTextField tfMontoPart;
	private JFormattedTextField tfFechaFin;
	private JFormattedTextField tfFechaAgasajo;
	private JFormattedTextField tfMailAgasajado;

	private ListaDeRegalosDTO lr;
	private UsuarioDTO u;
	private JFrame f;
	private JFrame m;

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public ABMListaRegalosParticipante(boolean nuevo, ListaDeRegalosDTO listaregalos, JFrame main)  {
		this.f = this;
		this.lr= listaregalos;
		this.m = main;
		setIconImage(Toolkit.getDefaultToolkit().getImage(".\\img\\regalos.png"));
		
		setTitle("Sistema de Listas de Regalos - Lista Participante");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 347, 342);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblListaDeRegalos = new JLabel("LISTA DE REGALOS");
		lblListaDeRegalos.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblListaDeRegalos.setBounds(12, 13, 273, 26);
		contentPane.add(lblListaDeRegalos);
		
		JLabel lblNombreAgasajado = new JLabel("Nombre Agasajado");
		lblNombreAgasajado.setBounds(12, 57, 128, 16);
		contentPane.add(lblNombreAgasajado);
		
		JLabel lblMontoPorParticipante = new JLabel("Monto por Participante");
		lblMontoPorParticipante.setBounds(12, 86, 201, 16);
		contentPane.add(lblMontoPorParticipante);
		
		JLabel lblFechaFin = new JLabel("Fecha Fin");
		lblFechaFin.setBounds(12, 175, 56, 16);
		contentPane.add(lblFechaFin);
		
		JLabel lblFechaAgasajo = new JLabel("Fecha Agasajo");
		lblFechaAgasajo.setBounds(12, 145, 128, 16);
		contentPane.add(lblFechaAgasajo);
		
		tfAgasajado = new JTextField();
		tfAgasajado.setBounds(152, 54, 116, 22);
		contentPane.add(tfAgasajado);
		tfAgasajado.setColumns(10);
		
		tfMontoPart = new JFormattedTextField(new DecimalFormat("#.#"));
		tfMontoPart.setText("0.0");
		tfMontoPart.setBounds(152, 83, 116, 22);
		contentPane.add(tfMontoPart);
		tfMontoPart.setColumns(10);
		

		try {
			MaskFormatter mk = new MaskFormatter("##/##/####");
			mk.setPlaceholder("_");
			tfFechaAgasajo = new JFormattedTextField(mk);
		
		} catch(Exception ex) {
			tfFechaAgasajo = new JFormattedTextField();
		
		}
		try {
			MaskFormatter mk = new MaskFormatter("##/##/####");
			mk.setPlaceholder("_");
		
			tfFechaFin = new JFormattedTextField(mk);
		} catch(Exception ex) {
		
			tfFechaFin = new JFormattedTextField();
		}
		
		
		tfFechaAgasajo.setBounds(152, 145, 116, 22);
		contentPane.add(tfFechaAgasajo);
		tfFechaAgasajo.setColumns(10);
		
		
		
		tfFechaFin.setBounds(152, 175, 116, 22);
		contentPane.add(tfFechaFin);
		tfFechaFin.setColumns(10);
		
		JLabel lblMailAgasajado = new JLabel("Mail Agasajado");
		lblMailAgasajado.setBounds(12, 115, 130, 16);
		contentPane.add(lblMailAgasajado);

		tfMailAgasajado = new JFormattedTextField();
		tfMailAgasajado.setBounds(152, 113, 116, 22);
		contentPane.add(tfMailAgasajado);
		tfMailAgasajado.setColumns(10);		
		
		JLabel lblElRegistroSe = new JLabel("El registro se ha guardado con \u00E9xito");
		lblElRegistroSe.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblElRegistroSe.setForeground(new Color(0, 128, 0));
		lblElRegistroSe.setBounds(48, 204, 254, 16);
		contentPane.add(lblElRegistroSe);
		lblElRegistroSe.setVisible(false);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblElRegistroSe.setVisible(false);
				try {
					//Formateo datos a guardar
					DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ROOT);
					Date now = new Date();
					Date fAg = null;
					Date fFin = null;
					
					try {
						fAg = format.parse(tfFechaAgasajo.getText());
						fFin = format.parse(tfFechaFin.getText());
					}
					catch(Exception ex) {
						System.out.println(ex.getMessage());
						lblElRegistroSe.setText("Corrobore las fechas, son obligatorias");
						lblElRegistroSe.setForeground(Color.RED);
						lblElRegistroSe.setVisible(true);
					}
					
					if (fAg != null && fFin != null && tfAgasajado.getText() != "" &&
							Float.parseFloat(tfMontoPart.getText()) != Float.parseFloat("0.0") &&
							tfMailAgasajado.getText() != "") {
					
						Pattern pattern = Pattern.compile("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@" +
								 "[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$");
						 
						 Matcher matcher = pattern.matcher(tfMailAgasajado.getText());
						if (!matcher.matches()) {
							throw new Exception("Mail invalido");
						}
						
						try {
						//Llamo al controlador de lista de regalos para que cree la lista.
						ControladorListaRegalos.GetInstance().CrearListaRegalos(tfAgasajado.getText(),
								fAg, tfMailAgasajado.getText(),0,now,fFin,"Activo",
								SistemaRegalos.GetInstance().getUsuarioLogueado(),Float.parseFloat(tfMontoPart.getText()));
						} catch (Exception ex) {
							System.out.println(ex.getMessage());
							lblElRegistroSe.setText("No se pudo grabar en la base el registro.");
							lblElRegistroSe.setForeground(Color.RED);
							lblElRegistroSe.setVisible(true);
							
						}
						lblElRegistroSe.setText("El registro se ha guardado con Éxito");
						lblElRegistroSe.setForeground(new Color(0,128,0));
						lblElRegistroSe.setVisible(true);
	
						m.dispose();
						m = new MainUsuario();
						m.setVisible(true);
						dispose();
					} else {
						throw new Exception("Corrobore los campos obligatorios.");
					}
				}
				catch(Exception ex) {
					System.out.println(ex.getMessage());
					lblElRegistroSe.setText(ex.getMessage());
					lblElRegistroSe.setForeground(Color.RED);
					lblElRegistroSe.setVisible(true);
				}
			}
		});
		btnGuardar.setBounds(171, 228, 97, 25);
		contentPane.add(btnGuardar);
		
		JLabel lblSeHaDado = new JLabel("Se ha dado de baja de la lista de regalos");
		lblSeHaDado.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSeHaDado.setForeground(new Color(0, 128, 0));
		lblSeHaDado.setBounds(12, 266, 305, 16);
		contentPane.add(lblSeHaDado);
		lblSeHaDado.setVisible(false);
		
		
		JButton btnDarDeBaja = new JButton("Dar de Baja");
		btnDarDeBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(f,
			            "¿Realmente desea darse de baja de la lista de regalos?", "Sistema de Lista de regalos - Darse de baja de Lista de Regalos",
			            JOptionPane.YES_NO_OPTION);
				
			        if (result == JOptionPane.YES_OPTION) {
			        	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			        	u = SistemaRegalos.GetInstance().getUsuarioLogueado();
						
						//Doy de baja el usuario como participante de la lista (baja lógica)
						ControladorListaRegalos.GetInstance().BajarParticipanteLista(u.codigo, lr.codigo);			 
						MainUsuario aux = (MainUsuario)m;
						
						aux.refrescar(aux.table_1, aux.table);
						dispose();
						
			          }
			        else if (result == JOptionPane.NO_OPTION)
			          setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

				
//				ConfirmarBajaLista confirmar = new ConfirmarBajaLista(lr, f, m);
//				confirmar.setVisible(true);
//				
//				//Obtengo el usuario que está logueado
//				u = SistemaRegalos.GetInstance().getUsuarioLogueado();
//				
//				//Doy de baja el usuario como participante de la lista (baja lógica)
//				ControladorListaRegalos.GetInstance().BajarParticipanteLista(u.getCodigo(), lr.getCodigo());
//				
//				lblSeHaDado.setVisible(true);
			}
		});

		
		
		
		btnDarDeBaja.setBounds(21, 228, 116, 25);
		contentPane.add(btnDarDeBaja);
		
		JLabel label = new JLabel("*");
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		label.setForeground(Color.RED);
		label.setBounds(272, 57, 13, 16);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("*");
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_1.setBounds(272, 86, 13, 16);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("*");
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_2.setBounds(272, 116, 13, 16);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("*");
		label_3.setForeground(Color.RED);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_3.setBounds(272, 145, 13, 16);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("*");
		label_4.setForeground(Color.RED);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_4.setBounds(272, 175, 13, 16);
		contentPane.add(label_4);
		
		//Valido si es nueva lista o lista existente
		if (nuevo) {
			btnDarDeBaja.setVisible(false);
		}else {
			tfAgasajado.setText(lr.nombreAgasajado);
			tfMontoPart.setText(String.valueOf(lr.montoARecaudarXIntegrante));
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			
			String aux = format.format(lr.fechaFin);
			tfFechaFin.setText(aux);
			
			aux = format.format(lr.fechaAgasajo);
			tfFechaAgasajo.setText(aux);
			tfMailAgasajado.setText(lr.mailAgasajado);
			tfAgasajado.setEditable(false);
			tfMontoPart.setEditable(false);
			tfFechaFin.setEditable(false);
			tfFechaAgasajo.setEditable(false);
			tfMailAgasajado.setEditable(false);
			btnGuardar.setVisible(false);
		}
		

		

	}
}
