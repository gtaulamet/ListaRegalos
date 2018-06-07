package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import controlador.ControladorUsuario;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ABMAdministrador extends JFrame {

	private JPanel contentPane;
	private JTextField tfUser;
	private JPasswordField tfPass;

	/**
	 * Create the frame.
	 */
	public ABMAdministrador() {
		setTitle("Sistema de Lista de Regalos - Alta Administrador");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 339, 246);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAbmAdministrador = new JLabel("ABM ADMINISTRADOR");
		lblAbmAdministrador.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblAbmAdministrador.setBounds(12, 13, 218, 40);
		contentPane.add(lblAbmAdministrador);
		
		JLabel lblUser = new JLabel("User");
		lblUser.setBounds(22, 88, 56, 16);
		contentPane.add(lblUser);
		
		JLabel lblPass = new JLabel("Pass");
		lblPass.setBounds(22, 117, 56, 16);
		contentPane.add(lblPass);
		
		tfUser = new JTextField();
		tfUser.setBounds(90, 85, 116, 22);
		contentPane.add(tfUser);
		tfUser.setColumns(10);
		
		tfPass = new JPasswordField();
		tfPass.setBounds(90, 114, 116, 22);
		contentPane.add(tfPass);
		tfPass.setColumns(10);
		
		JLabel lblElAdministradorYa = new JLabel("El administrador ya existe. Corrobore el User");
		lblElAdministradorYa.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblElAdministradorYa.setForeground(Color.RED);
		lblElAdministradorYa.setBounds(19, 183, 318, 16);
		contentPane.add(lblElAdministradorYa);		
		lblElAdministradorYa.setVisible(false);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean seCreo = ControladorUsuario.GetInstance().CrearAdministrador(tfUser.getText(),tfPass.getText());
				if (!seCreo) {
					lblElAdministradorYa.setVisible(true);
				}else {
					lblElAdministradorYa.setVisible(false);
				}
			}
		});
		btnGuardar.setBounds(152, 149, 97, 25);
		contentPane.add(btnGuardar);
//		
//		JButton btnDarDeBaja = new JButton("Dar de Baja");
//		btnDarDeBaja.setBounds(208, 54, 129, 25);
//		contentPane.add(btnDarDeBaja);
//		

	}
}
