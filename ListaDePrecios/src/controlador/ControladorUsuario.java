package controlador;
import java.util.Vector;

import modelo.Administrador;
import modelo.Usuario;


public class ControladorUsuario {
	private static ControladorUsuario controladorUsuario;
	private Vector<Usuario> usuarios;
	private Vector<Administrador> administradores;

	public ControladorUsuario GetInstance() {
		if (this.controladorUsuario == null) {
			this.controladorUsuario = new ControladorUsuario();
		}
		return this.controladorUsuario;
	}	
	
	private ControladorUsuario() {
		this.usuarios = new Vector<Usuario>(); 
	}
	
	public Usuario GetUsuario(int u) {
		return null;
	}
	
	public Vector<Usuario> GetUsuarios(){
		return this.usuarios;
	}
	
	public Vector<Administrador> GetAdministradores(){
		return this.administradores;
	}
	

}
