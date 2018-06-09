package modelo;

import java.util.HashMap;
import java.util.Map;

import DTO.AdministradorDTO;
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
	
	public static void insert(AdministradorDTO a) {
		Administrador admin = new Administrador(a.user,a.pass);
//		Administrador.insert(admin);
		AdmPersistenciaUsuario.getInstancia().insertAdmin(admin);
	}
	
	public static void delete(Administrador a) {
		AdmPersistenciaUsuario.getInstancia().deleteAdmin(a);
	}

	public static Map<String,AdministradorDTO> buscarTodos(){
		Map<String,Administrador> ads = AdmPersistenciaUsuario.getInstancia().buscarAdministradores();
		Map<String,AdministradorDTO> adsDTO = new HashMap<String, AdministradorDTO>();
		
		for (Map.Entry<String, Administrador> e : ads.entrySet()) {
			AdministradorDTO aDTO = new AdministradorDTO();
			aDTO.user = e.getValue().getUser();
			aDTO.pass = e.getValue().getPass();
			adsDTO.put(aDTO.user, aDTO);
		}
		return adsDTO;
	}
	
	public static AdministradorDTO buscarAdministrador(String a) {
		Administrador ad = AdmPersistenciaUsuario.getInstancia().buscarAdministrador(a);
		if (ad != null) {
			AdministradorDTO aDTO = new AdministradorDTO();
			aDTO.user = ad.getUser();
			aDTO.pass = ad.getPass();
			return aDTO;
		}
		return null;
	}
}
