package modelo;

import java.io.Console;
import java.util.Map;
import java.util.Vector;

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

	public static Usuario insert(Usuario u) {
		int codigo = AdmPersistenciaUsuario.getInstancia().insertCodigo(u);
		System.out.println(codigo);
		u.setCodigo(codigo);
		return u;
	}
	
	public static void delete(Usuario u) {
		AdmPersistenciaUsuario.getInstancia().delete(u);
	}
	public static void update(Usuario u) {
		AdmPersistenciaUsuario.getInstancia().update(u);
	}
	public static Usuario buscarUsuario(int codigo){
		return AdmPersistenciaUsuario.getInstancia().buscarAUsuario(codigo);
	}
	public static Map<Integer,Usuario> buscarTodos(){
		return AdmPersistenciaUsuario.getInstancia().buscarTodos();
	}
	public static Usuario buscarUsuario(String user) {
		return AdmPersistenciaUsuario.getInstancia().buscarAUsuario(user);
	}
}