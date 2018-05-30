package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginAdministrador extends JFrame {

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
					LoginAdministrador frame = new LoginAdministrador();
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
	public LoginAdministrador() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLoginAdmin = new JLabel("Login ADMIN");
		lblLoginAdmin.setForeground(Color.RED);
		lblLoginAdmin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblLoginAdmin.setBounds(12, 13, 132, 29);
		contentPane.add(lblLoginAdmin);
		
		JLabel lblUsuaio = new JLabel("Usuaio");
		lblUsuaio.setBounds(33, 85, 56, 16);
		contentPane.add(lblUsuaio);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(33, 114, 89, 16);
		contentPane.add(lblContrasea);
		
		tfUsuario = new JTextField();
		tfUsuario.setBounds(145, 82, 116, 22);
		contentPane.add(tfUsuario);
		tfUsuario.setColumns(10);
		
		tfContrasenia = new JTextField();
		tfContrasenia.setBounds(145, 111, 116, 22);
		contentPane.add(tfContrasenia);
		tfContrasenia.setColumns(10);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Login de Administrador
				JFrame administracion = new Administracion();
				administracion.setVisible(true);
			}
		});
		btnIngresar.setBounds(248, 181, 97, 25);
		contentPane.add(btnIngresar);
	}

}
