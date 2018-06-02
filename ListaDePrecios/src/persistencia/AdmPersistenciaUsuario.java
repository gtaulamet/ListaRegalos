package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Vector;
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
			s.setLong(1, u.getCodigo());
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}
		catch (Exception e)
		{
			System.out.println();
		}
		
	}

	@Override
	public void insert(Object o) 
	{
		try
		{
			Usuario a = (Usuario)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("insert into BD_ListaRegalos.dbo.Usuario values (?,?,?,?,?,?,?)");
			//agregar campos
			s.setLong(1,a.getCodigo());
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

	public Vector<Usuario> buscarTodos(){
		Vector<Usuario> usuarios = new Vector<Usuario>();
		
		try {
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("select * from BD_ListaRegalos.dbo.Usuario");
			
			ResultSet result = s.executeQuery();
			Usuario u = null;
			
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
				usuarios.add(u);
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}
		catch(Exception e) {
			System.out.println();
		}
		return usuarios;
	}
}
