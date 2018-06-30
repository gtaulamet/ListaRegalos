package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.SistemaRegalos;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginAdministrador extends JFrame {

	private JPanel contentPane;
	private JTextField tfUsuario;
	private JPasswordField tfPass;
	private boolean logueo = false;
	/**
	 * Create the frame.
	 */
	public LoginAdministrador() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(".\\img\\regalos.png"));
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				if (!logueo) {
					Login login = new Login();
					login.setVisible(true);
				}
			}
		});
		setTitle("Sistema de Lista de Regalos - Login Administrador");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 378, 264);
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
		
		tfPass = new JPasswordField();
		tfPass.setBounds(145, 112, 116, 20);
		contentPane.add(tfPass);
		
		JLabel lblContraseaIncorrecta = new JLabel("Usuario y/o Contrase\u00F1a Incorrectos!");
		lblContraseaIncorrecta.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblContraseaIncorrecta.setForeground(Color.RED);
		lblContraseaIncorrecta.setBounds(33, 143, 263, 16);
		lblContraseaIncorrecta.setVisible(false);
		contentPane.add(lblContraseaIncorrecta);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblContraseaIncorrecta.setVisible(false);
				//Logueo Administrado
				if (SistemaRegalos.GetInstance().LoginAdmin(tfUsuario.getText(), new String(tfPass.getPassword()))) {
					JFrame administracion = new Administracion();
					administracion.setVisible(true);
					logueo = true;
					dispose();	
				}else {
					lblContraseaIncorrecta.setVisible(true);
				}
			}
		});
		btnIngresar.setBounds(248, 181, 97, 25);
		contentPane.add(btnIngresar);
		

		
		
	}
}
