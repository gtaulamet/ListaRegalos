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

public class ABMAdministrador extends JFrame {

	private JPanel contentPane;
	private JTextField tfUser;
	private JTextField tfPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ABMAdministrador frame = new ABMAdministrador();
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
	public ABMAdministrador() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 380, 246);
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
		
		tfPass = new JTextField();
		tfPass.setBounds(90, 114, 116, 22);
		contentPane.add(tfPass);
		tfPass.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(208, 150, 97, 25);
		contentPane.add(btnGuardar);
		
		JButton btnDarDeBaja = new JButton("Dar de Baja");
		btnDarDeBaja.setBounds(208, 54, 129, 25);
		contentPane.add(btnDarDeBaja);
	}

}
