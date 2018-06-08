package controlador;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import modelo.Administrador;
import modelo.ListaDeRegalos;
import modelo.ParticipanteLista;
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
		//if (usuarios.isEmpty()) {
			this.usuarios = Usuario.buscarTodos();	
		//}		
		return usuarios;
	}
	
	public Administrador GetAdministrador(String a) {
		return Administrador.buscarAdministrador(a);
	}
	
	public Map<String,Administrador> GetAdministradores(){
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
		Usuario uExistente = Usuario.buscarUsuario(user);
		if (uExistente==null) {
			Usuario u = new Usuario(0,user, SistemaRegalos.generarPass(pass), nombre, apellido, Integer.parseInt(DNI.isEmpty()?"0":DNI), email);
			u = Usuario.insert(u);
			usuarios.put(u.getCodigo(),u);
		}else {
			throw new Exception("El User ya existe"); 
		}
	}
	
	public void ModificarUsuario(int codigo, String user, String nombre, String apellido, String pass, String email, String DNI) throws Exception {
		Usuario uExistente = buscarUsuario(codigo);
		if (uExistente!=null) {
			if(uExistente.getApellido()!=apellido)
				uExistente.setApellido(apellido);
			if(uExistente.getNombre()!=nombre)
				uExistente.setNombre(nombre);
			if(uExistente.getDNI()!=Integer.parseInt(DNI))
				uExistente.setDNI(Integer.parseInt(DNI));
			if(uExistente.getMail()!=email)
				uExistente.setMail(email);
			if((pass != null) && (!pass.equals("")))
				uExistente.setPass(SistemaRegalos.generarPass(pass));
			uExistente.actualizarUsuario();
		}
	}


	public void DardeBajaUsuario(int codigo) throws Exception {
				
		Usuario uExistente = buscarUsuario(codigo);
	  	Map<Integer,ListaDeRegalos> listasComoParticipante = ControladorListaRegalos.GetInstance().GetListasDelParticipante(uExistente.getCodigo());
    	Map<Integer,ListaDeRegalos> listasComoAdmin = ControladorListaRegalos.GetInstance().GetListasAdministrador(uExistente.getCodigo());
    	if(listasComoParticipante.size()!=0) {
    		throw new Exception("El usuario esta particiando en alguna listas. ");
    	}if(listasComoAdmin.size()!=0) {
    		throw new Exception("El usuario es administrador en alguna listas. ");
    	}
    	Usuario.delete(uExistente);
    	usuarios.remove(uExistente.getCodigo());
		
	}
	
	public Usuario buscarUsuario (int Codigo) {
		Usuario u = usuarios.get(Codigo);
		if (u!=null) {
			return u;
		}
		u = Usuario.buscarUsuario(Codigo);
		if (u != null) {
			usuarios.put(u.getCodigo(),u);
			return u;
		}
		return null;
	}

	public boolean CrearAdministrador(String user, String pass) {
		Administrador admin = Administrador.buscarAdministrador(user);
		if (admin == null) {
			admin = new Administrador(user,SistemaRegalos.generarPass(pass));
			Administrador.insert(admin);
			
			return true;
		}
		return false;
	}
}
