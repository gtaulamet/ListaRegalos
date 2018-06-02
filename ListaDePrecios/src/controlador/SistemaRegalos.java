package controlador;
import java.awt.EventQueue;
import java.util.Vector;

import modelo.Usuario;
import persistencia.AdmPersistenciaUsuario;
import vista.Login;

public class SistemaRegalos {
	public SistemaRegalos sistemaRegalos;
	public int diasProximoAVencer;
	public boolean Login(String user, String pass) {
		return false;
	}
	
	public SistemaRegalos GetInstance() {
		return this.sistemaRegalos;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					Vector<Usuario> aux= AdmPersistenciaUsuario.getInstancia().buscarTodos();
					for (Usuario u : aux) {
						System.out.println(u.getApellido());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
