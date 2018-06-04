package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import modelo.Administrador;
import modelo.Usuario;

public class AdmPersistenciaUsuario extends AdministradorPersistencia 
{
	private static AdmPersistenciaUsuario instancia;
	
	private AdmPersistenciaUsuario()
	{
	}
	
	public static AdmPersistenciaUsuario getInstancia()
	{
		if (instancia == null)
			instancia = new AdmPersistenciaUsuario();
		return instancia;
	}

	@Override
	public void delete(Object d) 
	{
		try
		{
			Usuario u = (Usuario)d;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("delete from BD_ListaRegalos.dbo.Usuario where codigo = ?");
			s.setInt(1, u.getCodigo());
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}
		catch (Exception e)
		{
			System.out.println();
		}
		
	}

	@Override
	public void insert (Object o) 
	{
		// TODO Auto-generated method stub
	}


	@Override
	public int insertDevolucionCodigo(Object o) 
	{
		int codigo = 0;
		try
		{
			Usuario a = (Usuario)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("insert into BD_ListaRegalos.dbo.Usuario values (?,?,?,?,?,?) SELECT @@IDENTITY AS 'Identity'");
			//agregar campos
			s.setString(1, a.getUser());
			s.setString(2,a.getPass());
			s.setString(3, a.getNombre());
			s.setString(4, a.getApellido());
			s.setInt(5,a.getDNI());
			s.setString(6, a.getMail());
			/*s.execute();*/
			ResultSet result = s.executeQuery();
			PoolConnection.getPoolConnection().realeaseConnection(con);
			result.next();
			codigo =result.getInt(1);
		}
		catch (Exception e)
		{
			System.out.println();
		}
		
		return codigo;

	}

	@Override
	public Vector<Object> select(Object o) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Object o) 
	{
		try
		{
			Usuario a = (Usuario)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("update BD_ListaRegalos.dbo.Usuario " +
					"set codigo = ?," +
					"set username = ?," +
					"set pass =?," +
					"set nombre =?," +
					"set apellido =?," +
					"set dni =?," +
					"set mail = ?)");
			//agregar campos
			s.setInt(1,a.getCodigo());
			s.setString(2, a.getUser());
			s.setString(3,a.getPass());
			s.setString(4, a.getNombre());
			s.setString(5, a.getApellido());
			s.setInt(6,a.getDNI());
			s.setString(7, a.getMail());
			
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}
		catch (Exception e)
		{
			System.out.println();
		}
		


	}
	public Usuario buscarAUsuario(int codigo)
	{
		try
		{
			Usuario a = null;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("select * from BD_ListaRegalos.dbo.Usuario where codigo = ?");			
			//agregar campos
			s.setInt(1,codigo);
			ResultSet result = s.executeQuery();
			while (result.next())
			{
				int cod = result.getInt(1);
				String user = result.getString(2);
				String pass = result.getString(3);
				String nombre = result.getString(4);
				String apellido = result.getString(5);
				int dni = result.getInt(6);
				String mail = result.getString(7);
				a = new Usuario(cod,user,pass,nombre,apellido,dni,mail);
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
			return a;
		}
		catch (Exception e)
		{
			System.out.println();
		}
		return null;
	}
	public Usuario buscarAUsuario(String username)
	{
		try
		{
			Usuario a = null;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("select * from BD_ListaRegalos.dbo.Usuario where username = ?");			
			//agregar campos
			s.setString(1,username);
			ResultSet result = s.executeQuery();
			while (result.next())
			{
				int cod = result.getInt(1);
				String user = result.getString(2);
				String pass = result.getString(3);
				String nombre = result.getString(4);
				String apellido = result.getString(5);
				int dni = result.getInt(6);
				String mail = result.getString(7);
				a = new Usuario(cod,user,pass,nombre,apellido,dni,mail);
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
			return a;
		}
		catch (Exception e)
		{
			System.out.println();
		}
		return null;
	}

	public Map<Integer,Usuario> buscarTodos(){
		try {
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("select * from BD_ListaRegalos.dbo.Usuario");
			
			ResultSet result = s.executeQuery();
			Usuario u = null;
			Map<Integer,Usuario> usuarios = new HashMap<Integer,Usuario>();
			
			while (result.next())
			{
				int cod = result.getInt(1);
				String user = result.getString(2);
				String pass = result.getString(3);
				String nombre = result.getString(4);
				String apellido = result.getString(5);
				int dni = result.getInt(6);
				String mail = result.getString(7);
				u = new Usuario(cod,user,pass,nombre,apellido,dni,mail);
				usuarios.put(cod,u);
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
			return usuarios;
		}
		catch(Exception e) {
			System.out.println();
		}
		return null;
	}
	
	public Administrador buscarAdministrador(String user) {
		try
		{
			Administrador a = null;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("select * from BD_ListaRegalos.dbo.Administrador where username = ?");			
			//agregar campos
			s.setString(1,user);
			ResultSet result = s.executeQuery();
			while (result.next())
			{
				String us = result.getString(1);
				String pw = result.getString(2);
				a = new Administrador(us, pw);
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
			return a;
		}
		catch (Exception e)
		{
			System.out.println();
		}
		return null;
	}
	
	public Map<String,Administrador> buscarAdministradores() {
		try
		{
			Administrador a = null;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("select * from BD_ListaRegalos.dbo.Administrador");			

			ResultSet result = s.executeQuery();
			Map<String,Administrador> admins = new HashMap<String, Administrador>();
			while (result.next())
			{
				String us = result.getString(1);
				String pw = result.getString(2);
				a = new Administrador(us, pw);
				admins.put(us, a);
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
			return admins;
		}
		catch (Exception e)
		{
			System.out.println();
		}
		return null;
	}
	
	public void insertAdmin(Object o) 
	{
		try
		{
			Administrador a = (Administrador)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("insert into BD_ListaRegalos.dbo.Administrador values (?,?)");
			//agregar campos
			s.setString(1, a.getUser());
			s.setString(2, a.getPass());
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}
		catch (Exception e)
		{
			System.out.println();
		}
	}
	public void deleteAdmin(Object d) 
	{
		try
		{
			Administrador u = (Administrador)d;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("delete from BD_ListaRegalos.dbo.Administrador where username = ?");
			s.setString(1, u.getUser());
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}
		catch (Exception e)
		{
			System.out.println();
		}
		
	}
}
