package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField tfUsuario;
	private JTextField tfContrasenia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(68, 109, 56, 16);
		contentPane.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(68, 148, 91, 16);
		contentPane.add(lblContrasea);
		
		tfUsuario = new JTextField();
		tfUsuario.setBounds(170, 106, 116, 22);
		contentPane.add(tfUsuario);
		tfUsuario.setColumns(10);
		
		tfContrasenia = new JTextField();
		tfContrasenia.setBounds(171, 145, 116, 22);
		contentPane.add(tfContrasenia);
		tfContrasenia.setColumns(10);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Logueo
				JFrame mainUsuario = new MainUsuario();
				mainUsuario.setVisible(true);
				dispose();
			}
		});
		btnIngresar.setBounds(305, 215, 97, 25);
		contentPane.add(btnIngresar);
		
		JLabel lblAdministracin = new JLabel("Administraci\u00F3n");
		lblAdministracin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame loginAdmin = new LoginAdministrador();
				loginAdmin.setVisible(true);
				dispose();
			}
		});
		lblAdministracin.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAdministracin.setBounds(31, 219, 128, 16);
		contentPane.add(lblAdministracin);
		
		JLabel lblLoginSistema = new JLabel("Login - Sistema de Lista de Regalos");
		lblLoginSistema.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblLoginSistema.setBounds(31, 23, 359, 40);
		contentPane.add(lblLoginSistema);
	}
}
