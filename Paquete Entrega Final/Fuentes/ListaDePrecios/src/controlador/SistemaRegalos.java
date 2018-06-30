package controlador;
import java.awt.EventQueue;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import DTO.AdministradorDTO;
import DTO.ListaDeRegalosDTO;
import DTO.UsuarioDTO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import modelo.Administrador;
import modelo.ListaDeRegalos;
import modelo.Usuario;
import persistencia.AdmPersistenciaUsuario;
import vista.Login;

public class SistemaRegalos {
	private static SistemaRegalos sistemaRegalos;
	public int diasProximoAVencer;
	private UsuarioDTO usuarioLogueado;
	private Map<Integer, ListaDeRegalosDTO> listaParticipante = new HashMap<Integer, ListaDeRegalosDTO>();
	private Map<Integer, ListaDeRegalosDTO> listaAdministrador = new HashMap<Integer, ListaDeRegalosDTO>();
	
	
	private SistemaRegalos() {
		this.diasProximoAVencer = 3;
	}
	
	public boolean Login(String user, String pass) {
		UsuarioDTO u = ControladorUsuario.GetInstance().GetUsuario(user);
		try {
			String aux= generarPass(pass);
			if (aux.equals(u.pass)) {
				this.usuarioLogueado = u;
				this.listaParticipante = ControladorListaRegalos.GetInstance().GetListasDelParticipante(u.codigo);
				this.listaAdministrador = ControladorListaRegalos.GetInstance().GetListasAdministrador(u.codigo);
				return true;
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return false;
	}
	
	public boolean LoginAdmin(String user, String pass) {
		AdministradorDTO a = ControladorUsuario.GetInstance().GetAdministrador(user);
		try {
			String aux= generarPass(pass);
			if (aux.equals(a.pass)) {
				return true;
			}
		}
		catch(Exception e) {
			
		}
		
		return false;
	}
	
	public Map<Integer, ListaDeRegalosDTO> getListaParticipante() {
		this.listaParticipante = ControladorListaRegalos.GetInstance().GetListasDelParticipante(this.usuarioLogueado.codigo);
		return this.listaParticipante;
	}

	public void setListaParticipante(Map<Integer, ListaDeRegalosDTO> listaParticipante) {
		this.listaParticipante = listaParticipante;
	}

	public Map<Integer, ListaDeRegalosDTO> getListaAdministrador() {
		this.listaAdministrador = ControladorListaRegalos.GetInstance().GetListasAdministrador(this.usuarioLogueado.codigo);
		return this.listaAdministrador;
	}

	public void setListaAdministrador(Map<Integer, ListaDeRegalosDTO> listaAdministrador) {
		this.listaAdministrador = listaAdministrador;
	}
	
	
	
	public UsuarioDTO getUsuarioLogueado() {
		return usuarioLogueado;
	}
	
	public static SistemaRegalos GetInstance() {
		if (sistemaRegalos == null) {
			sistemaRegalos = new SistemaRegalos();
		}
		return sistemaRegalos;
	}
	
	public static String generarPass(String pass) {
		String passwordToHash = pass;
	    String generatedPassword = null;
	     try {
	         // Create MessageDigest instance for MD5
	         MessageDigest md = MessageDigest.getInstance("MD5");
	         //Add password bytes to digest
	         md.update(passwordToHash.getBytes());
	         //Get the hash's bytes
	         byte[] bytes = md.digest();
	         //This bytes[] has bytes in decimal format;
	         //Convert it to hexadecimal format
	         StringBuilder sb = new StringBuilder();
	         for(int i=0; i< bytes.length ;i++)
	         {
	             sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	         }
	         //Get complete hashed password in hex format
	         generatedPassword = sb.toString();
	     }
	     catch (NoSuchAlgorithmException e)
	     {
	         e.printStackTrace();
	     }
	     System.out.println(generatedPassword);
	     return generatedPassword;
     }	
}
