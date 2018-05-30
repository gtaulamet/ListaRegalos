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

public class AltaListaRegalos extends JFrame {

	private JPanel contentPane;
	private JTextField tfCodigo;
	private JTextField tfAgasajado;
	private JTextField tfMontoPart;
	private JTextField tfFechaFin;
	private JTextField tfFechaAgasajo;
	private JTextField tfEstado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaListaRegalos frame = new AltaListaRegalos();
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
	public AltaListaRegalos() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 347, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblListaDeRegalos = new JLabel("ALTA LISTA DE REGALOS");
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
		lblFechaFin.setBounds(12, 139, 56, 16);
		contentPane.add(lblFechaFin);
		
		JLabel lblFechaAgasajo = new JLabel("Fecha Agasajo");
		lblFechaAgasajo.setBounds(12, 168, 128, 16);
		contentPane.add(lblFechaAgasajo);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(12, 197, 56, 16);
		contentPane.add(lblEstado);
		
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
		tfFechaFin.setBounds(152, 136, 116, 22);
		contentPane.add(tfFechaFin);
		tfFechaFin.setColumns(10);
		
		tfFechaAgasajo = new JTextField();
		tfFechaAgasajo.setBounds(152, 165, 116, 22);
		contentPane.add(tfFechaAgasajo);
		tfFechaAgasajo.setColumns(10);
		
		tfEstado = new JTextField();
		tfEstado.setBounds(152, 194, 116, 22);
		contentPane.add(tfEstado);
		tfEstado.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(162, 241, 97, 25);
		contentPane.add(btnGuardar);
	}
}
