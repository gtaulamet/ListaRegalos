package persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Vector;

import modelo.ListaDeRegalos;
import modelo.Usuario;

public class AdmPersistenciaListaRegalos extends AdministradorPersistencia 
{
	private static AdmPersistenciaListaRegalos instancia;
	
	private AdmPersistenciaListaRegalos()
	{
	}
	
	public static AdmPersistenciaListaRegalos getInstancia()
	{
		if (instancia == null)
			instancia = new AdmPersistenciaListaRegalos();
		return instancia;
	}

	@Override
	public void delete(Object d) 
	{
		try
		{
			ListaDeRegalos l = (ListaDeRegalos)d;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("delete from BD_ListaRegalos.dbo.ListaDeRegalos where codigo = ?");
			s.setInt(1, l.getCodigo());
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
			ListaDeRegalos l = (ListaDeRegalos)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("insert into BD_ListaRegalos.dbo.ListaDeRegalos values (?,?,?,?,?,?,?,?,?,?)");
			//agregar campos
			s.setInt(1,l.getCodigo());
			s.setString(2, l.getNombreAgasajado());
			s.setDate(3,(Date) l.getFechaAgasajo());
			s.setString(4, l.getMailAgasajado());
			s.setFloat(5, l.getMontoRecaudado());
			s.setDate(6,(Date) l.getFechaInicio());
			s.setDate(7,(Date) l.getFechaFin());
			s.setString(8, l.getEstado());
			s.setFloat(9, l.getMontoARecaudarXIntegrante());
			s.setInt(10, l.getAdministrador().getCodigo());
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
			ListaDeRegalos l = (ListaDeRegalos)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("update BD_ListaRegalos.dbo.ListaDeRegalos " +
					"set codigo = ?," +
					"set nombreAgasajado = ?," +
					"set fechaAgasajo =?," +
					"set mailAgasajado =?," +
					"set montoRecaudado =?," +
					"set fechaInicio =?," +
					"set fechaFin =?," +
					"set estado =?," +
					"set montoARecaudarXInteg =?," +
					"set administradorId = ?)");
			//agregar campos
			s.setInt(1,l.getCodigo());
			s.setString(2, l.getNombreAgasajado());
			s.setDate(3,(Date) l.getFechaAgasajo());
			s.setString(4, l.getMailAgasajado());
			s.setFloat(5, l.getMontoRecaudado());
			s.setDate(6,(Date) l.getFechaInicio());
			s.setDate(7,(Date) l.getFechaFin());
			s.setString(8, l.getEstado());
			s.setFloat(9, l.getMontoARecaudarXIntegrante());
			s.setInt(10, l.getAdministrador().getCodigo());
			
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}
		catch (Exception e)
		{
			System.out.println();
		}
		


	}
	public ListaDeRegalos buscarAListaDeRegalos(int codigo)
	{
		try
		{
			ListaDeRegalos l = null;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("select * from BD_ListaRegalos.dbo.ListaDeRegalos where codigo = ?");			
			
			s.setInt(1,codigo);
			ResultSet result = s.executeQuery();
			while (result.next())
			{
				int cod = result.getInt(1);
				String nomAgas= result.getString(2);
				Date fAgas=result.getDate(3);
				String mailAgas=result.getString(4);
				float montoRec= result.getFloat(5);
				Date fInicio=result.getDate(6);
				Date fFin=result.getDate(7);
				String estado= result.getString(8);
				float montoInteg= result.getFloat(9);
				int adminId = result.getInt(10);
				Usuario admin = AdmPersistenciaUsuario.getInstancia().buscarAUsuario(adminId);
				l = new ListaDeRegalos(cod, nomAgas, fAgas, mailAgas, montoRec, fInicio, fFin, estado, admin, montoInteg);
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
			return l;
		}
		catch (Exception e)
		{
			System.out.println();
		}
		return null;
	}

	public Vector<ListaDeRegalos> buscarTodos(){
		Vector<ListaDeRegalos> listaRegalos = new Vector<ListaDeRegalos>();
		
		try {
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("select * from BD_ListaRegalos.dbo.ListaDeRegalos");
			
			ResultSet result = s.executeQuery();
			ListaDeRegalos l = null;
			
			while (result.next())
			{
				int cod = result.getInt(1);
				String nomAgas= result.getString(2);
				Date fAgas=result.getDate(3);
				String mailAgas=result.getString(4);
				float montoRec= result.getFloat(5);
				Date fInicio=result.getDate(6);
				Date fFin=result.getDate(7);
				String estado= result.getString(8);
				float montoInteg= result.getFloat(9);
				int adminId = result.getInt(10);
				Usuario admin = AdmPersistenciaUsuario.getInstancia().buscarAUsuario(adminId);
				l = new ListaDeRegalos(cod, nomAgas, fAgas, mailAgas, montoRec, fInicio, fFin, estado, admin, montoInteg);
				
				listaRegalos.add(l);
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}
		catch(Exception e) {
			System.out.println();
		}
		return listaRegalos;
	}
}
