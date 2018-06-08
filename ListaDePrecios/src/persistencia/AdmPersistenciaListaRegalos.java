package persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
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
			PreparedStatement s = con.prepareStatement("insert into BD_ListaRegalos.dbo.ListaDeRegalos values (?,?,?,?,?,?,?,?,?)");
			//agregar campos
//			s.setInt(1,l.getCodigo());
			s.setString(1, l.getNombreAgasajado());
			s.setDate(2, new java.sql.Date(l.getFechaAgasajo().getTime()));
			s.setString(3, l.getMailAgasajado());
			s.setFloat(4, l.getMontoRecaudado());
			s.setDate(5,new java.sql.Date(l.getFechaInicio().getTime()));
			s.setDate(6,new java.sql.Date(l.getFechaFin().getTime()));
			s.setString(7, l.getEstado());
			s.setFloat(8, l.getMontoARecaudarXIntegrante());
			s.setInt(9, l.getAdministrador().getCodigo());
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}
		catch (Exception e)
		{
			System.out.println("Error al insertar una la lista de regalos.");
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
					"set nombreAgasajado = ?," +
					"fechaAgasajo =?," +
					"mailAgasajado =?," +
					"montoRecaudado =?," +
					"fechaInicio =?," +
					"fechaFin =?," +
					"estado =?," +
					"montoARecaudarXInteg =?," +
					"administradorId = ? where codigo = ?");
			
			s.setString(1, l.getNombreAgasajado());
			s.setDate(2,(Date) l.getFechaAgasajo());
			s.setString(3, l.getMailAgasajado());
			s.setFloat(4, l.getMontoRecaudado());
			s.setDate(5,(Date) l.getFechaInicio());
			s.setDate(6,(Date) l.getFechaFin());
			s.setString(7, l.getEstado());
			s.setFloat(8, l.getMontoARecaudarXIntegrante());
			s.setInt(9, l.getAdministrador().getCodigo());
			s.setInt(10,l.getCodigo());
			s.execute();
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}
		catch (Exception e)
		{
			System.out.println("Error al actualizar la lista de regalos");
		}
	}

	public void updateEstado(Object o) 
	{
		try
		{
			ListaDeRegalos l = (ListaDeRegalos)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("update BD_ListaRegalos.dbo.ListaDeRegalos " +
					"set estado =?" +
					" where codigo = ?");
			
			s.setString(1, l.getEstado());
			s.setInt(2,l.getCodigo());
			s.execute();
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}
		catch (Exception e)
		{
			System.out.println("Error al cambiar el estado de la lista de Regalos.");
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
			System.out.println("Error al buscar una lista de regalos.");
		}
		return null;
	}

	public Map<Integer,ListaDeRegalos> buscarTodos(){
		try {
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("select * from BD_ListaRegalos.dbo.ListaDeRegalos");
			
			ResultSet result = s.executeQuery();
			ListaDeRegalos l = null;
			Map<Integer,ListaDeRegalos> listaRegalos = new HashMap<Integer,ListaDeRegalos>();
			
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
				
				listaRegalos.put(cod,l);
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
			return listaRegalos;
		}
		catch(Exception e) {
			System.out.println("Error al buscar todas las listas de regalos.");
		}
		return null;
	}
	public Map<Integer,ListaDeRegalos> buscarListasAdministrador(int codigo)
	{
		try
		{
			ListaDeRegalos l = null;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("select * from BD_ListaRegalos.dbo.ListaDeRegalos where administradorid = ? and estado not like 'Inactivo'");			
			
			s.setInt(1,codigo);
			ResultSet result = s.executeQuery();
			Map<Integer,ListaDeRegalos> lista = new HashMap<Integer,ListaDeRegalos>();
			
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
				lista.put(cod, l);
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
			return lista;
		}
		catch (Exception e)
		{
			System.out.println("Error al buscar las listas de regalo que administra.");
		}
		return null;
	}

	public Map<Integer,ListaDeRegalos> buscarListasParticipo(int codigo)
	{
		try
		{
			ListaDeRegalos l = null;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("select lr.* from  BD_ListaRegalos.dbo.ListaDeRegalos lr\r\n" + 
					"inner join  BD_ListaRegalos.dbo.ParticipanteLista pl on pl.listaDeRegalosId = lr.codigo and pl.estado like 'Activo'\r\n" + 
					"inner join  BD_ListaRegalos.dbo.Usuario u on u.codigo=? and pl.usuarioId=u.codigo");			
			
			s.setInt(1,codigo);
			ResultSet result = s.executeQuery();
			Map<Integer,ListaDeRegalos> lista = new HashMap<Integer,ListaDeRegalos>();
			
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
				lista.put(cod, l);
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
			return lista;
		}
		catch (Exception e)
		{
			System.out.println("Error al buscar las listas de regalo que participo.");
		}
		return null;
	}	
}
