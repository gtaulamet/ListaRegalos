package controlador;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import DTO.AdministradorDTO;
import DTO.ListaDeRegalosDTO;
import DTO.UsuarioDTO;
import modelo.Administrador;
import modelo.Usuario;



public class ControladorUsuario {
	private static ControladorUsuario controladorUsuario;
	private Map<Integer,UsuarioDTO> usuarios;
	private Map<String,AdministradorDTO> administradores;

	public static ControladorUsuario GetInstance() {
		if (controladorUsuario == null) {
			controladorUsuario = new ControladorUsuario();
		}
		return controladorUsuario;
	}	
	
	private ControladorUsuario() {
		this.usuarios = new HashMap<Integer,UsuarioDTO>(); 
	}
	
	public UsuarioDTO GetUsuario(int u) {
		if (usuarios.containsKey((Object)u)) {
			return usuarios.get((Object)u);
		}
				
		UsuarioDTO aux = null;
		aux = Usuario.buscarUsuario(u); 
		usuarios.put(aux.codigo,aux);
		
		return usuarios.get((Object)u);
	}
	
	public  UsuarioDTO GetUsuario(String u) {
		return Usuario.buscarUsuario(u);
	}
	
	public Map<Integer,UsuarioDTO> GetUsuarios(){
		//if (usuarios.isEmpty()) {
			this.usuarios = Usuario.buscarTodos();	
		//}		
		return usuarios;
	}
	
	public AdministradorDTO GetAdministrador(String a) {
		return Administrador.buscarAdministrador(a);
	}
	
	public Map<String,AdministradorDTO> GetAdministradores(){
//		if (administradores == null) {
			this.administradores = Administrador.buscarTodos();
//		}
		return this.administradores;
	}
	
	//Esto seria para ejecutar cada x cantidad de tiempo para actualizar las listas internas
	public void actualizarListas() {
		this.administradores = Administrador.buscarTodos();
		this.usuarios = Usuario.buscarTodos();
	}
	
	public void CrearUsuario(String user, String nombre, String apellido, String pass, String email, String DNI) throws Exception {
		UsuarioDTO uExistente = Usuario.buscarUsuario(user);
		if (uExistente==null) {
			UsuarioDTO usuario = Usuario.insert(user, SistemaRegalos.generarPass(pass), nombre, apellido, Integer.parseInt(DNI.isEmpty()?"0":DNI), email);
			usuarios.put(usuario.codigo,usuario);
		}else {
			throw new Exception("El User ya existe"); 
		}
	}
	
	public void ModificarUsuario(int codigo, String user, String nombre, String apellido, String pass, String email, String DNI) throws Exception {
		UsuarioDTO uExistente = buscarUsuario(codigo);
		if (uExistente!=null) {
			if(uExistente.apellido!=apellido)
				uExistente.apellido=apellido;
			if(uExistente.nombre!=nombre)
				uExistente.nombre=nombre;
			if(uExistente.DNI!=Integer.parseInt(DNI))
				uExistente.DNI=Integer.parseInt(DNI);
			if(uExistente.mail!=email)
				uExistente.mail=email;
			if((pass != null) && (!pass.equals("")))
				uExistente.pass=SistemaRegalos.generarPass(pass);
			Usuario.actualizarUsuario(uExistente);
//			uExistente.actualizarUsuario();
		}
	}


	public void DardeBajaUsuario(int codigo) throws Exception {
				
		UsuarioDTO uExistente = buscarUsuario(codigo);
	  	Map<Integer,ListaDeRegalosDTO> listasComoParticipante = ControladorListaRegalos.GetInstance().GetListasDelParticipante(uExistente.codigo);
    	Map<Integer,ListaDeRegalosDTO> listasComoAdmin = ControladorListaRegalos.GetInstance().GetListasAdministrador(uExistente.codigo);
    	if(listasComoParticipante.size()!=0) {
    		throw new Exception("El usuario esta particiando en alguna listas. ");
    	}if(listasComoAdmin.size()!=0) {
    		throw new Exception("El usuario es administrador en alguna listas. ");
    	}
    	Usuario.delete(uExistente);
    	usuarios.remove(uExistente.codigo);
		
	}
	
	public UsuarioDTO buscarUsuario (int Codigo) {
		UsuarioDTO u = usuarios.get(Codigo);
		if (u!=null) {
			return u;
		}
		u = Usuario.buscarUsuario(Codigo);
		if (u != null) {
			usuarios.put(u.codigo,u);
			return u;
		}
		return null;
	}

	public boolean CrearAdministrador(String user, String pass) {
		AdministradorDTO admin = Administrador.buscarAdministrador(user);
		if (admin == null) {
			admin = new AdministradorDTO();
			admin.user = user;
			admin.pass = SistemaRegalos.generarPass(pass);
			Administrador.insert(admin);
//			admin = new Administrador(user,SistemaRegalos.generarPass(pass));
//			Administrador.insert(admin);
			
			return true;
		}
		return false;
	}
}
