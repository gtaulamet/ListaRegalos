package controlador;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import modelo.Administrador;
import modelo.Usuario;


public class ControladorUsuario {
	private static ControladorUsuario controladorUsuario;
	private Map<Integer,Usuario> usuarios;
	private Map<String,Administrador> administradores;

	public static ControladorUsuario GetInstance() {
		if (controladorUsuario == null) {
			controladorUsuario = new ControladorUsuario();
		}
		return controladorUsuario;
	}	
	
	private ControladorUsuario() {
		this.usuarios = new HashMap<Integer,Usuario>(); 
	}
	
	public Usuario GetUsuario(int u) {
		if (usuarios.containsKey((Object)u)) {
			return usuarios.get((Object)u);
		}
				
		Usuario aux = null;
		aux = Usuario.buscarUsuario(u); 
		usuarios.put(aux.getCodigo(),aux);
		
		return usuarios.get((Object)u);
	}
	
	public static Usuario GetUsuario(String u) {
		return Usuario.buscarUsuario(u);
	}
	
	public Map<Integer,Usuario> GetUsuarios(){
		if (usuarios.isEmpty()) {
			this.usuarios = Usuario.buscarTodos();	
		}		
		return usuarios;
	}
	
	public static Administrador GetAdministrador(String a) {
		return Administrador.buscarAdministrador(a);
	}
	
	public Map<String,Administrador> GetAdministradores(){
		if (administradores.isEmpty()) {
			this.administradores = Administrador.buscarTodos();
		}
		return this.administradores;
	}
	
	//Esto seria para ejecutar cada x cantidad de tiempo para actualizar las listas internas
	public void actualizarListas() {
		this.administradores = Administrador.buscarTodos();
		this.usuarios = Usuario.buscarTodos();
	}
	
	public void CrearUsuario(String user, String nombre, String apellido, String pass, String email, String DNI) throws Exception {
		Usuario uExistente = Usuario.buscarUsuario(user);
		if (uExistente==null) {
			Usuario u = new Usuario(0,user, SistemaRegalos.generarPass(pass), nombre, apellido, Integer.parseInt(DNI), email);
			u = Usuario.insert(u);
			usuarios.put(u.getCodigo(),u);
		}else {
			throw new Exception("El User ya existe"); 
		}
	}
}
