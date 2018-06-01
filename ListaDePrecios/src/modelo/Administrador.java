package modelo;

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
	
	public void insert(String user, String pass) {
	
	}
	
	public void delete(String user) {
		
	}

}
