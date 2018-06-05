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
import modelo.ParticipanteLista;
import modelo.Usuario;

public class AdmPersistenciaParticipanteLista extends AdministradorPersistencia 
{
	private static AdmPersistenciaParticipanteLista instancia;
	
	private AdmPersistenciaParticipanteLista()
	{
	}
	
	public static AdmPersistenciaParticipanteLista getInstancia()
	{
		if (instancia == null)
			instancia = new AdmPersistenciaParticipanteLista();
		return instancia;
	}

	@Override
	public void delete(Object d) 
	{
		try
		{
			ParticipanteLista p = (ParticipanteLista)d;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("delete from BD_ListaRegalos.dbo.ParticipanteLista where usuarioid = ? and listaDeRegalosId = ?");
			s.setInt(1, p.getUsuario().getCodigo());
			s.setInt(2, p.getListaDeRegalos().getCodigo());
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
			ParticipanteLista p = (ParticipanteLista)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("insert into BD_ListaRegalos.dbo.ParticipanteLista values (?,?,?,?)");
			//agregar campos
			s.setInt(1, p.getUsuario().getCodigo());
			s.setInt(2, p.getListaDeRegalos().getCodigo());
			s.setBoolean(3, p.isPago());
			s.setString(4, p.getEstado());
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

	//updatea si pagó o no y el estado
	@Override
	public void update(Object o) 
	{
		try
		{
			ParticipanteLista p = (ParticipanteLista)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("update BD_ListaRegalos.dbo.ParticipanteLista " +
					"set pago =?," +
					"set estado = ?) where usuarioId = ? and listaDeRegalosId = ?");
			//agregar campos
			s.setBoolean(1, p.isPago());
			s.setString(2, p.getEstado());
			s.setInt(3, p.getUsuario().getCodigo());
			s.setInt(4, p.getListaDeRegalos().getCodigo());
			
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}
		catch (Exception e)
		{
			System.out.println();
		}
		
	}
	
	public ParticipanteLista buscarAParticipanteLista(int usuario, int listaDeRegalos)
	{
		try
		{
			ParticipanteLista p = null;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("select * from BD_ListaRegalos.dbo.ParticipanteLista where usuarioId = ? and listaDeRegalosId = ?");			
			
			s.setInt(1,usuario);
			s.setInt(2, listaDeRegalos);
			
			ResultSet result = s.executeQuery();
			while (result.next())
			{
				int usuarioId = result.getInt(1);
				int lista = result.getInt(2);
				boolean pago = result.getBoolean(3);
				String estado = result.getString(4);
				
				Usuario us = AdmPersistenciaUsuario.getInstancia().buscarAUsuario(usuarioId);
				ListaDeRegalos li = AdmPersistenciaListaRegalos.getInstancia().buscarAListaDeRegalos(lista);
				p= new ParticipanteLista(us, li, pago, estado);
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
			return p;
		}
		catch (Exception e)
		{
			System.out.println();
		}
		return null;
	}

	public Map<Integer,ParticipanteLista> buscarTodos(int codigo){
		try {
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("select * from BD_ListaRegalos.dbo.ParticipanteLista where listaDeRegalosId = ? and estado like 'Activo'");
			s.setInt(1,codigo);
			
			ResultSet result = s.executeQuery();
			ParticipanteLista p = null;
			
			Map<Integer,ParticipanteLista> participantes = new HashMap<Integer,ParticipanteLista>();
			
			while (result.next())
			{
				int usuario = result.getInt(1);
				int lista = result.getInt(2);
				boolean pago = result.getBoolean(3);
				String estado= result.getString(4);
				
				Usuario us = AdmPersistenciaUsuario.getInstancia().buscarAUsuario(usuario);
				ListaDeRegalos li = AdmPersistenciaListaRegalos.getInstancia().buscarAListaDeRegalos(lista);
				
				p = new  ParticipanteLista(us, li, pago, estado);
				
				participantes.put(usuario,p);
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
			return participantes;
		}
		catch(Exception e) {
			System.out.println();
		}
		return null;
	}

	public Map<Integer,ParticipanteLista> buscarTodosXUsuario(int codigo){
		try {
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("select * from BD_ListaRegalos.dbo.ParticipanteLista where usuarioId = ? and estado like 'Activo'");
			s.setInt(1,codigo);
			
			ResultSet result = s.executeQuery();
			ParticipanteLista p = null;
			
			Map<Integer,ParticipanteLista> participantes = new HashMap<Integer,ParticipanteLista>();
			
			while (result.next())
			{
				int usuario = result.getInt(1);
				int lista = result.getInt(2);
				boolean pago = result.getBoolean(3);
				String estado= result.getString(4);
				
				Usuario us = AdmPersistenciaUsuario.getInstancia().buscarAUsuario(usuario);
				ListaDeRegalos li = AdmPersistenciaListaRegalos.getInstancia().buscarAListaDeRegalos(lista);
				
				p = new  ParticipanteLista(us, li, pago, estado);
				
				participantes.put(lista,p);
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
			return participantes;
		}
		catch(Exception e) {
			System.out.println();
		}
		return null;
	}
	
	
	public void darBajaParticipanteLista(int u, int lr) {
		try
		{
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("update BD_ListaRegalos.dbo.ParticipanteLista " +
					"set estado = 'Inactivo' where usuarioId = ? and listaDeRegalosId = ?");
			//agregar campos
			s.setInt(1, u);
			s.setInt(2, lr);
			
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}
		catch (Exception e)
		{
			System.out.println("Error al dar de baja el participante de la lista");
		}
	}
}
