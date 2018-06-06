package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import controlador.ControladorUsuario;
import controlador.SistemaRegalos;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.JButton;

public class ABMUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField tfCodigo;
	private JTextField tfUser;
	private JPasswordField tfPass;
	private JTextField tfNombre;
	private JTextField tfApellido;
	private JTextField tfDNI;
	private JTextField tfEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ABMUsuario frame = new ABMUsuario();
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
	public ABMUsuario() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 427, 397);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAbmUsuario = new JLabel("ABM USUARIO");
		lblAbmUsuario.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblAbmUsuario.setBounds(12, 13, 152, 16);
		contentPane.add(lblAbmUsuario);
		
		JLabel lblError = new JLabel("");
		lblError.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblError.setForeground(Color.RED);
		lblError.setBounds(99, 284, 222, 14);
		contentPane.add(lblError);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setBounds(12, 80, 56, 16);
		contentPane.add(lblCdigo);
		
		JLabel lblUser = new JLabel("User");
		lblUser.setBounds(12, 109, 56, 16);
		contentPane.add(lblUser);
		
		JLabel lblPass = new JLabel("Pass");
		lblPass.setBounds(12, 138, 56, 16);
		contentPane.add(lblPass);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(12, 167, 56, 16);
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(12, 196, 56, 16);
		contentPane.add(lblApellido);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(12, 225, 56, 16);
		contentPane.add(lblDni);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(12, 254, 56, 16);
		contentPane.add(lblEmail);
		
		tfCodigo = new JTextField();
		tfCodigo.setBounds(99, 77, 116, 22);
		contentPane.add(tfCodigo);
		tfCodigo.setColumns(10);
		
		tfUser = new JTextField();
		tfUser.setBounds(99, 106, 116, 22);
		contentPane.add(tfUser);
		tfUser.setColumns(10);
		
		tfPass = new JPasswordField();
		tfPass.setBounds(99, 135, 116, 22);
		contentPane.add(tfPass);
		tfPass.setColumns(10);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(99, 164, 116, 22);
		contentPane.add(tfNombre);
		tfNombre.setColumns(10);
		
		tfApellido = new JTextField();
		tfApellido.setBounds(99, 193, 116, 22);
		contentPane.add(tfApellido);
		tfApellido.setColumns(10);
		
		tfDNI = new JTextField();
		tfDNI.setBounds(99, 222, 116, 22);
		contentPane.add(tfDNI);
		tfDNI.setColumns(10);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(99, 251, 116, 22);
		contentPane.add(tfEmail);
		tfEmail.setColumns(10);
		
		JButton btnDarDeBaja = new JButton("Dar de Baja");
		btnDarDeBaja.setBounds(223, 40, 131, 25);
		contentPane.add(btnDarDeBaja);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(223, 318, 97, 25);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					 Pattern pattern = Pattern.compile("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@" +
						      "[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$");
				 
					 Matcher matcher = pattern.matcher(tfEmail.getText());
				        if (!matcher.matches()) {
				        	throw new Exception("EMail invalido"); 
				        }
					
				        
				ControladorUsuario.GetInstance().CrearUsuario(tfUser.getText(), tfNombre.getText(), tfApellido.getText(), new String(tfPass.getPassword()), tfEmail.getText(), tfDNI.getText());
				dispose();
				}
				catch(Exception ex) {
					System.out.println(ex.getMessage());
					lblError.setText("Error: "+ ex.getMessage());
					lblError.setForeground(Color.RED);
					lblError.setVisible(true);
				}
			}
		});
		contentPane.add(btnGuardar);
	/*
	 public void actionPerformed(ActionEvent e) {
				lblContraseaIncorrecta.setVisible(false);
				//Logueo Administrado
				if (SistemaRegalos.GetInstance().LoginAdmin(tfUsuario.getText(), new String(tfPass.getPassword()))) {
					JFrame administracion = new Administracion();
					administracion.setVisible(true);
					dispose();	
				}else {
					lblContraseaIncorrecta.setVisible(true);
				}
			}*/
	}

}
