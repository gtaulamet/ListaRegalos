package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.ControladorListaRegalos;
import controlador.SistemaRegalos;
import modelo.ListaDeRegalos;
import modelo.Usuario;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ConfirmarBajaLista extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private MainUsuario main;

	/**
	 * Create the dialog.
	 */
	public ConfirmarBajaLista(ListaDeRegalos lr, JFrame f, MainUsuario m) {
		main = m;
		setResizable(false);
		setBounds(100, 100, 433, 189);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JButton okButton = new JButton("Aceptar");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Obtengo el usuario que está logueado
					Usuario u = SistemaRegalos.GetInstance().getUsuarioLogueado();
					
					//Doy de baja el usuario como participante de la lista (baja lógica)
					ControladorListaRegalos.GetInstance().BajarParticipanteLista(u.getCodigo(), lr.getCodigo());
					
					dispose();
					f.dispose();
					m.repaint();
					
				}
			});
			okButton.setBounds(114, 77, 81, 25);
			contentPanel.add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			cancelButton.setBounds(207, 77, 81, 25);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}
		{
			JLabel lblrealmenteDeseaDar = new JLabel("\u00BFRealmente desea dar de baja el registro?");
			lblrealmenteDeseaDar.setFont(new Font("Tahoma", Font.BOLD, 17));
			lblrealmenteDeseaDar.setBounds(28, 23, 369, 41);
			contentPanel.add(lblrealmenteDeseaDar);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}

}
