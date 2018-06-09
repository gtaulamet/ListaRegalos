package modelo;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import DTO.UsuarioDTO;
import persistencia.AdmPersistenciaUsuario;

public class Usuario {
	private int codigo;
	private String user;
	private String pass;
	private String nombre;
	private String apellido;
	private int DNI;
	private String mail;
	
	
	public Usuario(int codigo, String user, String pass, String nombre, String apellido, int dNI, String mail) {
		super();
		this.codigo = codigo;
		this.user = user;
		this.pass = pass;
		this.nombre = nombre;
		this.apellido = apellido;
		DNI = dNI;
		this.mail = mail;
	}


	public int getCodigo() {
		return codigo;
	}


	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public int getDNI() {
		return DNI;
	}


	public void setDNI(int dNI) {
		DNI = dNI;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}

	public static UsuarioDTO insert(String user, String pass, String nombre, String apellido, int dni,
			String email) {
		Usuario u = new Usuario(0, user, pass, nombre, apellido, dni, email);
		int codigo = AdmPersistenciaUsuario.getInstancia().insertCodigo(u);
		System.out.println(codigo);
		u.setCodigo(codigo);
		
		UsuarioDTO uDTO = new UsuarioDTO();
		uDTO.apellido = u.getApellido();
		uDTO.codigo = u.getCodigo();
		uDTO.DNI = u.getDNI();
		uDTO.mail = u.getMail();
		uDTO.nombre = u.getNombre();
		uDTO.pass = u.getPass();
		uDTO.user = u.getUser();
		
		return uDTO;
	}
	
	public static void actualizarUsuario(UsuarioDTO u) {
		Usuario usuario = new Usuario(u.codigo, u.user, u.pass, u.nombre, u.apellido, u.DNI, u.mail);
		AdmPersistenciaUsuario.getInstancia().update(usuario);
	}
	
	public static void delete(UsuarioDTO u) {
		Usuario usuario = new Usuario(u.codigo, u.user, u.pass, u.nombre, u.apellido, u.DNI, u.mail);
		AdmPersistenciaUsuario.getInstancia().delete(usuario);
	}
	public static void update(Usuario u) {
		AdmPersistenciaUsuario.getInstancia().update(u);
	}
	public static UsuarioDTO buscarUsuario(int codigo){
		Usuario u = AdmPersistenciaUsuario.getInstancia().buscarAUsuario(codigo);
		UsuarioDTO uDTO = new UsuarioDTO();
		uDTO.apellido = u.getApellido();
		uDTO.codigo = u.getCodigo();
		uDTO.DNI = u.getDNI();
		uDTO.mail = u.getMail();
		uDTO.nombre = u.getNombre();
		uDTO.pass = u.getPass();
		uDTO.user = u.getUser();
		
		return uDTO;
	}
	public static Map<Integer,UsuarioDTO> buscarTodos(){
		Map<Integer,Usuario> lista = AdmPersistenciaUsuario.getInstancia().buscarTodos();
		Map<Integer,UsuarioDTO> listaDTO = new HashMap<Integer, UsuarioDTO>();
		
		for (Map.Entry<Integer, Usuario> e : lista.entrySet()) {
			UsuarioDTO uDTO = new UsuarioDTO();
			uDTO.apellido = e.getValue().getApellido();
			uDTO.codigo = e.getValue().getCodigo();
			uDTO.DNI = e.getValue().getDNI();
			uDTO.mail = e.getValue().getMail();
			uDTO.nombre = e.getValue().getNombre();
			uDTO.pass = e.getValue().getPass();
			uDTO.user = e.getValue().getUser();
			listaDTO.put(uDTO.codigo, uDTO);
		}
		
		return listaDTO;
	}
	public static UsuarioDTO buscarUsuario(String user) {
		Usuario u = AdmPersistenciaUsuario.getInstancia().buscarAUsuario(user);
		if (u != null) {
			UsuarioDTO uDTO = new UsuarioDTO();
			uDTO.apellido = u.getApellido();
			uDTO.codigo = u.getCodigo();
			uDTO.DNI = u.getDNI();
			uDTO.mail = u.getMail();
			uDTO.nombre = u.getNombre();
			uDTO.pass = u.getPass();
			uDTO.user = u.getUser();
			
			return uDTO;
		}
		return null;
	}



}