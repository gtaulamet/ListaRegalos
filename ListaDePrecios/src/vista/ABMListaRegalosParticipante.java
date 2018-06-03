package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.ControladorListaRegalos;
import controlador.SistemaRegalos;
import modelo.Usuario;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.awt.event.ActionEvent;

public class ABMListaRegalosParticipante extends JFrame {

	private JPanel contentPane;
	private JTextField tfCodigo;
	private JTextField tfAgasajado;
	private JTextField tfMontoPart;
	private JTextField tfFechaFin;
	private JTextField tfFechaAgasajo;
	private JTextField tfMailAgasajado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ABMListaRegalosParticipante frame = new ABMListaRegalosParticipante();
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
	public ABMListaRegalosParticipante() {
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
		
		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setBounds(12, 52, 56, 16);
		contentPane.add(lblCdigo);
		
		JLabel lblNombreAgasajado = new JLabel("Nombre Agasajado");
		lblNombreAgasajado.setBounds(12, 81, 128, 16);
		contentPane.add(lblNombreAgasajado);
		
		JLabel lblMontoPorParticipante = new JLabel("Monto por Participante");
		lblMontoPorParticipante.setBounds(12, 110, 201, 16);
		contentPane.add(lblMontoPorParticipante);
		
		JLabel lblFechaFin = new JLabel("Fecha Fin");
		lblFechaFin.setBounds(12, 199, 56, 16);
		contentPane.add(lblFechaFin);
		
		JLabel lblFechaAgasajo = new JLabel("Fecha Agasajo");
		lblFechaAgasajo.setBounds(12, 169, 128, 16);
		contentPane.add(lblFechaAgasajo);
		
		tfCodigo = new JTextField();
		tfCodigo.setBounds(152, 52, 116, 22);
		contentPane.add(tfCodigo);
		tfCodigo.setColumns(10);
		
		tfAgasajado = new JTextField();
		tfAgasajado.setBounds(152, 78, 116, 22);
		contentPane.add(tfAgasajado);
		tfAgasajado.setColumns(10);
		
		tfMontoPart = new JTextField();
		tfMontoPart.setBounds(152, 107, 116, 22);
		contentPane.add(tfMontoPart);
		tfMontoPart.setColumns(10);
		
		tfFechaFin = new JTextField();
		tfFechaFin.setBounds(152, 199, 116, 22);
		contentPane.add(tfFechaFin);
		tfFechaFin.setColumns(10);
		
		tfFechaAgasajo = new JTextField();
		tfFechaAgasajo.setBounds(152, 169, 116, 22);
		contentPane.add(tfFechaAgasajo);
		tfFechaAgasajo.setColumns(10);
		
		JLabel lblMailAgasajado = new JLabel("Mail Agasajado");
		lblMailAgasajado.setBounds(10, 140, 130, 16);
		contentPane.add(lblMailAgasajado);
		
		tfMailAgasajado = new JTextField();
		tfMailAgasajado.setBounds(152, 137, 116, 22);
		contentPane.add(tfMailAgasajado);
		tfMailAgasajado.setColumns(10);		
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ROOT);
					Date fAg = format.parse(tfFechaAgasajo.getText());
					Date now = new Date();
					Date fFin = format.parse(tfFechaFin.getText());
					ControladorListaRegalos.GetInstance().crearListaRegalos(Integer.parseInt(tfCodigo.getText()),tfAgasajado.getText(),
							fAg, tfMailAgasajado.getText(),0,now,fFin,"Activo",
							SistemaRegalos.GetInstance().getUsuarioLogueado(),Float.parseFloat(tfMontoPart.getText()));
				}
				catch(Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
		});
		btnGuardar.setBounds(171, 252, 97, 25);
		contentPane.add(btnGuardar);
		
		JButton btnDarDeBaja = new JButton("Dar de Baja");
		btnDarDeBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDarDeBaja.setBounds(21, 252, 116, 25);
		contentPane.add(btnDarDeBaja);
		

	}
}
