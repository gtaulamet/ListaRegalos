package controlador;
import java.awt.EventQueue;
import java.util.Map;
import java.util.Vector;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import modelo.Administrador;
import modelo.Usuario;
import persistencia.AdmPersistenciaUsuario;
import vista.Login;

public class SistemaRegalos {
	private static SistemaRegalos sistemaRegalos;
	public int diasProximoAVencer;
	private Usuario usuarioLogueado;
	
	private SistemaRegalos() {
		this.diasProximoAVencer = 3;
	}
	
	public boolean Login(String user, String pass) {
		Usuario u = ControladorUsuario.GetInstance().GetUsuario(user);
		try {
			String aux= generarPass(pass);
			if (aux.equals(u.getPass())) {
				this.usuarioLogueado = u;
				return true;
			}
		}
		catch(Exception e) {
			
		}
		
		return false;
	}
	
	public boolean LoginAdmin(String user, String pass) {
		Administrador a = ControladorUsuario.GetInstance().GetAdministrador(user);
		try {
			String aux= generarPass(pass);
			if (aux.equals(a.getPass())) {
				return true;
			}
		}
		catch(Exception e) {
			
		}
		
		return false;
	}
	
	
	public Usuario getUsuarioLogueado() {
		return usuarioLogueado;
	}
	
	public static SistemaRegalos GetInstance() {
		if (sistemaRegalos == null) {
			sistemaRegalos = new SistemaRegalos();
		}
		return sistemaRegalos;
	}
	/*
	 public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					Map<Integer, Usuario> aux= AdmPersistenciaUsuario.getInstancia().buscarTodos();
					
					for (Map.Entry<Integer, Usuario> e : aux.entrySet()) {
						System.out.println(e.getValue().getApellido());

						
						System.out.println("pass: "+generarPass(e.getValue().getPass()));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
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
