package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import controlador.ControladorListaRegalos;
import controlador.ControladorUsuario;
import controlador.SistemaRegalos;
import modelo.ListaDeRegalos;
import modelo.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
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

	private JFrame f;
	/**
	 * Create the frame.
	 */
	public ABMUsuario(Usuario u) {
		this.f = this;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 309, 397);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAbmUsuario = new JLabel("ABM USUARIO");
		lblAbmUsuario.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblAbmUsuario.setBounds(12, 13, 152, 16);
		contentPane.add(lblAbmUsuario);
		
		JLabel lblError = new JLabel("");
		lblError.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblError.setForeground(Color.RED);
		lblError.setBounds(99, 284, 184, 14);
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
		btnDarDeBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(f,
			            "¿Realmente desea eliminmar el usuario?", "Sistema de Lista de regalos - Darse de baja de Usuario",
			            JOptionPane.YES_NO_OPTION);
				
			        if (result == JOptionPane.YES_OPTION) {
			        	try {
			        	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			        	
			        	ControladorUsuario.GetInstance().DardeBajaUsuario(Integer.parseInt(tfCodigo.getText()));
			        	/*
						//Doy de baja el usuario como participante de la lista (baja lógica)
						ControladorListaRegalos.GetInstance().BajarParticipanteLista(u.getCodigo(), lr.getCodigo());			 
						MainUsuario aux = (MainUsuario)m;
						
						aux.refrescar(aux.table_1, aux.table);
						dispose();
						
						*/
			        	}catch(Exception ex) {
							System.out.println(ex.getMessage());
							lblError.setText("Error: "+ ex.getMessage());
							lblError.setForeground(Color.RED);
							lblError.setVisible(true);
						}
			        	
			          }
			        else if (result == JOptionPane.NO_OPTION)
			          setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		btnDarDeBaja.setBounds(10, 309, 131, 25);
		contentPane.add(btnDarDeBaja);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(166, 309, 97, 25);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					 Pattern pattern = Pattern.compile("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@" +
							 "[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$");
					 
					 Matcher matcher = pattern.matcher(tfEmail.getText());
					if (!matcher.matches()) {
						throw new Exception("EMail invalido"); 
					}
					if(tfUser.getText()=="") {
						throw new Exception("User Obligatorio");
					}
					if(tfUser.getText()=="" ||tfNombre.getText()==""||tfApellido.getText()==""||tfEmail.getText()=="" ||
							( u==null && tfNombre.getText()=="")) {
						throw new Exception("Corrobore los campos obligatorios.");
					}
					//Si es un alta	
					if (u==null) {       
						ControladorUsuario.GetInstance().CrearUsuario(tfUser.getText(), tfNombre.getText(), tfApellido.getText(), new String(tfPass.getPassword()), tfEmail.getText(), tfDNI.getText());
					}else {
						//Si es una modificacion
						ControladorUsuario.GetInstance().ModificarUsuario(Integer.parseInt(tfCodigo.getText()), tfUser.getText(), tfNombre.getText(), tfApellido.getText(), new String(tfPass.getPassword()), tfEmail.getText(), tfDNI.getText());
					}
					 JFrame adimistrador = new Administracion();
					 adimistrador.setVisible(true);
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
		
		JLabel label = new JLabel("*");
		label.setForeground(Color.RED);
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		label.setBounds(225, 81, 46, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("*");
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_1.setBounds(225, 110, 46, 14);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("*");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_2.setForeground(Color.RED);
		label_2.setBounds(225, 139, 46, 14);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("*");
		label_3.setForeground(Color.RED);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_3.setBounds(225, 168, 46, 14);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("*");
		label_4.setForeground(Color.RED);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_4.setBounds(225, 197, 46, 14);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("*");
		label_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_5.setForeground(Color.RED);
		label_5.setBounds(225, 255, 46, 14);
		contentPane.add(label_5);
		

		if (u!=null) {
			tfCodigo.setText(String.valueOf(u.getCodigo()));
			tfCodigo.setEditable(false);
			tfUser.setText(String.valueOf(u.getUser()));
			tfUser.setEditable(false);
			tfNombre.setText(u.getNombre());
			tfApellido.setText(u.getApellido());
			tfDNI.setText(String.valueOf(u.getDNI()));
			tfEmail.setText(u.getMail());
		}else {
			tfCodigo.setEnabled(false);
			btnDarDeBaja.setEnabled(false);
		}
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
