package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import controlador.SistemaRegalos;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.Toolkit;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField tfUsuario;
	private JPasswordField pfContrasenia;
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(".\\img\\regalos.png"));
		setTitle("Sistema de Lista de Regalos - Login");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		
//		JLabel background=new JLabel(new ImageIcon(".\\img\\regalos.png"));
//		add(background);
//		background.setLayout(new FlowLayout());
		
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
		
		JLabel lblContraseaIncorrecta = new JLabel("Usuario y/o Contrase\u00F1a Incorrectos!");
		lblContraseaIncorrecta.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblContraseaIncorrecta.setForeground(Color.RED);
		lblContraseaIncorrecta.setBounds(93, 177, 297, 16);
		lblContraseaIncorrecta.setVisible(false);
		contentPane.add(lblContraseaIncorrecta);		

		pfContrasenia = new JPasswordField();
		pfContrasenia.setBounds(171, 145, 115, 22);
		contentPane.add(pfContrasenia);
		
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblContraseaIncorrecta.setVisible(false);
				//Logueo
				if (SistemaRegalos.GetInstance().Login(tfUsuario.getText(),  new String(pfContrasenia.getPassword()))) {
					JFrame mainUsuario = new MainUsuario();
					mainUsuario.setVisible(true);
					dispose();	
				}else {
					lblContraseaIncorrecta.setVisible(true);
				}
				
			}
		});
		btnIngresar.setBounds(305, 215, 97, 25);
		contentPane.add(btnIngresar);
		
		JLabel lblAdministracin = new JLabel("Administraci\u00F3n");
		lblAdministracin.setForeground(Color.BLUE);
//	    Font font = lblAdministracin.getFont();
//	    Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
//	    attributes.put(TextAttribute.UNDERLINE, 1);
//	    lblAdministracin.setFont(font.deriveFont(attributes));
	    
		lblAdministracin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame loginAdmin = new LoginAdministrador();
				loginAdmin.setVisible(true);
				dispose();
			}
		});
		lblAdministracin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblAdministracin.setBounds(31, 219, 128, 16);
		contentPane.add(lblAdministracin);
		
		JLabel label = new JLabel("______________");
		label.setBounds(31, 219, 116, 16);
		contentPane.add(label);
		
		JLabel lblLoginSistema = new JLabel("Login - Sistema de Lista de Regalos");
		lblLoginSistema.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblLoginSistema.setBounds(31, 23, 359, 40);
		contentPane.add(lblLoginSistema);

	}
}
