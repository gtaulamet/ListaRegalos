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
		boolean encuentra = false;
		
		for (Usuario usuario : usuarios){
			if (usuario.getCodigo() == u) {
				encuentra = true;
				return usuario;
			} 
		}
		Usuario aux = null;
		if (!encuentra) {
			aux = Usuario.buscarUsuario(u); 
			usuarios.add(aux);
		}
		return aux;
	}
	
	public Vector<Usuario> GetUsuarios(){
		this.usuarios = Usuario.buscarTodos();
		return usuarios;
	}
	
	public Vector<Administrador> GetAdministradores(){
		return this.administradores;
	}
	

}
