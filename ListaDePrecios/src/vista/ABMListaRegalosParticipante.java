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
import java.awt.Color;

public class ABMListaRegalosParticipante extends JFrame {

	private JPanel contentPane;
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
					ABMListaRegalosParticipante frame = new ABMListaRegalosParticipante(false);
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
	public ABMListaRegalosParticipante(boolean nuevo) {
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
		
		tfMontoPart = new JTextField();
		tfMontoPart.setBounds(152, 83, 116, 22);
		contentPane.add(tfMontoPart);
		tfMontoPart.setColumns(10);
		
		tfFechaFin = new JTextField();
		tfFechaFin.setBounds(152, 175, 116, 22);
		contentPane.add(tfFechaFin);
		tfFechaFin.setColumns(10);
		
		tfFechaAgasajo = new JTextField();
		tfFechaAgasajo.setBounds(152, 145, 116, 22);
		contentPane.add(tfFechaAgasajo);
		tfFechaAgasajo.setColumns(10);
		
		JLabel lblMailAgasajado = new JLabel("Mail Agasajado");
		lblMailAgasajado.setBounds(10, 116, 130, 16);
		contentPane.add(lblMailAgasajado);
		
		tfMailAgasajado = new JTextField();
		tfMailAgasajado.setBounds(152, 113, 116, 22);
		contentPane.add(tfMailAgasajado);
		tfMailAgasajado.setColumns(10);		
		
		JLabel lblElRegistroSe = new JLabel("El registro se ha guardado con \u00E9xito");
		lblElRegistroSe.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblElRegistroSe.setForeground(new Color(0, 128, 0));
		lblElRegistroSe.setBounds(48, 204, 237, 16);
		contentPane.add(lblElRegistroSe);
		lblElRegistroSe.setVisible(false);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ROOT);
					Date fAg = format.parse(tfFechaAgasajo.getText());
					Date now = new Date();
					Date fFin = format.parse(tfFechaFin.getText());
					ControladorListaRegalos.GetInstance().crearListaRegalos(tfAgasajado.getText(),
							fAg, tfMailAgasajado.getText(),0,now,fFin,"Activo",
							SistemaRegalos.GetInstance().getUsuarioLogueado(),Float.parseFloat(tfMontoPart.getText()));
					lblElRegistroSe.setText("El registro se ha guardado con \\u00E9xito");
					lblElRegistroSe.setForeground(new Color(0,128,0));
					lblElRegistroSe.setVisible(true);
				}
				catch(Exception ex) {
					System.out.println(ex.getMessage());
					lblElRegistroSe.setText("No se pudo grabar en la base el registro.");
					lblElRegistroSe.setForeground(Color.RED);
					lblElRegistroSe.setVisible(true);
				}
			}
		});
		btnGuardar.setBounds(171, 228, 97, 25);
		contentPane.add(btnGuardar);
		
		JButton btnDarDeBaja = new JButton("Dar de Baja");
		btnDarDeBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDarDeBaja.setBounds(21, 228, 116, 25);
		contentPane.add(btnDarDeBaja);
		if (nuevo) {
			btnDarDeBaja.setVisible(false);
		}
		

		

	}
}
