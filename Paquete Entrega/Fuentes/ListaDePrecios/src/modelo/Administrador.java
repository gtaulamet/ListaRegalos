package modelo;

import java.util.Map;

import persistencia.AdmPersistenciaUsuario;

public class Administrador {
	private String user;
	private String pass;

	public Administrador(String user, String pass) {
		super();
		this.user = user;
		this.pass = pass;
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
	
	public static void insert(Administrador a) {
		AdmPersistenciaUsuario.getInstancia().insertAdmin(a);
	}
	
	public static void delete(Administrador a) {
		AdmPersistenciaUsuario.getInstancia().deleteAdmin(a);
	}

	public static Map<String,Administrador> buscarTodos(){
		return AdmPersistenciaUsuario.getInstancia().buscarAdministradores();
	}
	
	public static Administrador buscarAdministrador(String a) {
		return AdmPersistenciaUsuario.getInstancia().buscarAdministrador(a);
	}
}
