package modelo;
import java.awt.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import DTO.ListaDeRegalosDTO;
import DTO.ParticipanteListaDTO;
import DTO.UsuarioDTO;
import persistencia.AdmPersistenciaListaRegalos;
import persistencia.AdmPersistenciaParticipanteLista;
import persistencia.AdmPersistenciaUsuario;
import controlador.DESPACHADORMAILS;

public class ListaDeRegalos implements IObservableMails, IObserverCalendario {
	private int codigo;
	private String nombreAgasajado;
	private Date fechaAgasajo;
	private String mailAgasajado;
	private float montoRecaudado;
	private Date fechaInicio;
	private Date fechaFin;
	private String estado;
	private Map<Integer,ParticipanteLista> participantes;
	private Usuario administrador;
	private float montoARecaudarXIntegrante;
	/*****/
	
	
	public ListaDeRegalos() {
		
	}

	public ListaDeRegalos(int codigo, String nombreAgasajado, Date fechaAgasajo, String mailAgasajado,
			float montoRecaudado, Date fechaInicio, Date fechaFin, String estado, Usuario administrador,
			float montoARecaudarXIntegrante) {
		super();
		this.codigo = codigo;
		this.nombreAgasajado = nombreAgasajado;
		this.fechaAgasajo = fechaAgasajo;
		this.mailAgasajado = mailAgasajado;
		this.montoRecaudado = montoRecaudado;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.estado = estado;
		this.administrador = administrador;
		this.montoARecaudarXIntegrante = montoARecaudarXIntegrante;
	}

//	public void CrearNuevoPago(Date fecha, Usuario u, float monto) {
//		//todo
//	}
	
	public Map<Integer,ParticipanteListaDTO> GetParticipantesImpagos() {
		//todo
		return null;
	}
	
	public Map<Integer,ParticipanteLista> GetParticipantes() {
		Map<Integer,ParticipanteLista> lista = ParticipanteLista.buscarTodosXLista(this.getCodigo()); 
		this.participantes = lista;
				
		return this.participantes;
		
	}
	
	public static Map<Integer,ParticipanteListaDTO> GetParticipantes(Integer l) {
		ListaDeRegalos li = AdmPersistenciaListaRegalos.getInstancia().buscarListaDeRegalos(l);
		Map<Integer,ParticipanteLista> lista = ParticipanteLista.buscarTodosXLista(li.getCodigo()); 
		li.participantes = lista;
		
		Map<Integer,ParticipanteListaDTO> listaDTO = new HashMap<Integer,ParticipanteListaDTO>();
		
		for (Map.Entry<Integer, ParticipanteLista> e : lista.entrySet()) {
			ParticipanteListaDTO pl = new ParticipanteListaDTO();
			pl.estado= e.getValue().getEstado();
			pl.pago = e.getValue().isPago();
			UsuarioDTO u= new UsuarioDTO();
			
			u.apellido = e.getValue().getUsuario().getApellido();
			u.codigo = e.getValue().getUsuario().getCodigo();
			u.DNI = e.getValue().getUsuario().getDNI();
			u.mail = e.getValue().getUsuario().getMail();
			u.nombre = e.getValue().getUsuario().getNombre();
			u.pass = e.getValue().getUsuario().getPass();
			u.user = e.getValue().getUsuario().getUser();
			
			pl.usuario = u;
			
			listaDTO.put(u.codigo, pl);
			
		}
		
		return listaDTO;
		
	}
	/*
	private ParticipanteLista GetParticipante(Usuario u) {
		return ParticipanteLista.buscarParticipante(u.getCodigo(), this.getCodigo());
	}
	*/
	
	public static void BajarParticipanteLista(Integer u, Integer l) {
//		ListaDeRegalos lista = AdmPersistenciaListaRegalos.getInstancia().buscarAListaDeRegalos(l);
//		lista.GetParticipantes();
		
//		Usuario usuario = AdmPersistenciaUsuario.getInstancia().buscarAUsuario(u);
		ParticipanteLista.darDeBaja(u, l);
//		lista.participantes.remove(usuario.getCodigo());
	}
	
	public static void AgregarParticipanteLista(Integer ul, Integer l) {
		ListaDeRegalos lista = AdmPersistenciaListaRegalos.getInstancia().buscarListaDeRegalos(l);
//		lista.GetParticipantes();
		Usuario usuario = AdmPersistenciaUsuario.getInstancia().buscarAUsuario(ul);
		//TODO
		//primero hay que revisar si no existe el participante para ese usuario inactivo.
		//Si es asi, se activa el participante. Sino se crea uno nuevo.
		ParticipanteLista partInactivo = ParticipanteLista.buscarParticipante(usuario.getCodigo(), lista.getCodigo());
		if (partInactivo != null) {
			partInactivo.setEstado("Activo");
			ParticipanteLista.update(partInactivo,lista.getCodigo());
			lista.participantes = new HashMap<Integer, ParticipanteLista>();
			lista.participantes.put(usuario.getCodigo(), partInactivo);
		} else {
			ParticipanteLista pl = new ParticipanteLista(usuario, false, "Activo");
			ParticipanteLista.insert(pl,lista.getCodigo());
			lista.participantes = new HashMap<Integer, ParticipanteLista>();
			lista.participantes.put(usuario.getCodigo(),pl);
		}
	}

	@Override
	public void ModificarEstado() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Attach(IObserverMail o) {
		observers.add(o);
		
	}

	@Override
	public void Deattach(IObserverMail o) {
		// TODO Auto-generated method stub
	}

	@Override
	public void SendMails(int i) {
		// TODO Auto-generated method stub
		switch (i){
			case 1:
				for (IObserverMail o : observers){
					//o.SendMailFinalizo(this);
					o.SendMailAgasajo(this);
				}
				break;
			case 2: 
				for (IObserverMail o : observers){
					o.SendMailsProximoFinalizar(this.GetParticipantesImpagos());
				}
				break;
			case 3:
				for (IObserverMail o : observers){
					o.SendMailsInicio(this.GetParticipantes());
				}
				break;
			default: break;
		}
	}
	

	public String getNombreAgasajado() {
		return nombreAgasajado;
	}

	public void setNombreAgasajado(String nombreAgasajado) {
		this.nombreAgasajado = nombreAgasajado;
	}

	public Date getFechaAgasajo() {
		return fechaAgasajo;
	}

	public void setFechaAgasajo(Date fechaAgasajo) {
		this.fechaAgasajo = fechaAgasajo;
	}

	public String getMailAgasajado() {
		return mailAgasajado;
	}

	public void setMailAgasajado(String mailAgasajado) {
		this.mailAgasajado = mailAgasajado;
	}

	public float getMontoRecaudado() {
		return montoRecaudado;
	}

	public void setMontoRecaudado(float montoRecaudado) {
		this.montoRecaudado = montoRecaudado;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Usuario getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Usuario administrador) {
		this.administrador = administrador;
	}

	public float getMontoARecaudarXIntegrante() {
		return montoARecaudarXIntegrante;
	}

	public void setMontoARecaudarXIntegrante(float montoARecaudarXIntegrante) {
		this.montoARecaudarXIntegrante = montoARecaudarXIntegrante;
	}

	public int getCodigo() {
		return codigo;
	}
	
	public static void insert(ListaDeRegalosDTO l) {
		Usuario ad = new Usuario(l.administrador.codigo, l.administrador.user, l.administrador.pass, l.administrador.nombre, l.administrador.apellido, l.administrador.DNI, l.administrador.mail);
		ListaDeRegalos lr = new ListaDeRegalos(0, l.nombreAgasajado, l.fechaAgasajo, l.mailAgasajado, l.montoRecaudado, l.fechaInicio, l.fechaFin, l.estado, ad, l.montoARecaudarXIntegrante);
		
		AdmPersistenciaListaRegalos.getInstancia().insert(lr);
	}
	public static void delete(ListaDeRegalos lr) {
		AdmPersistenciaListaRegalos.getInstancia().delete(lr);
	}
	public static void update(ListaDeRegalos lr) {
		AdmPersistenciaListaRegalos.getInstancia().update(lr);
	}
	public static void updateEstado(ListaDeRegalos lr) {
		AdmPersistenciaListaRegalos.getInstancia().updateEstado(lr);
	}
	public static Map<Integer,ListaDeRegalos> buscarTodas(){
		return AdmPersistenciaListaRegalos.getInstancia().buscarTodos();
	}
	public static Map<Integer,ListaDeRegalosDTO> buscarListasParticipo(int us){
		Map<Integer,ListaDeRegalos> listas = AdmPersistenciaListaRegalos.getInstancia().buscarListasParticipo(us);
		Map<Integer,ListaDeRegalosDTO> listasDTO = new HashMap<Integer, ListaDeRegalosDTO>();
		
		for (Map.Entry<Integer, ListaDeRegalos> e : listas.entrySet()) {
			ListaDeRegalosDTO listaDTO = new ListaDeRegalosDTO();
			listaDTO.codigo = e.getValue().getCodigo();
			listaDTO.estado = e.getValue().getEstado();
			listaDTO.fechaAgasajo = e.getValue().getFechaAgasajo();
			listaDTO.fechaFin = e.getValue().getFechaFin();
			listaDTO.fechaInicio = e.getValue().getFechaInicio();
			listaDTO.mailAgasajado = e.getValue().getMailAgasajado();
			listaDTO.montoARecaudarXIntegrante = e.getValue().getMontoARecaudarXIntegrante();
			listaDTO.montoRecaudado = e.getValue().getMontoRecaudado();
			listaDTO.nombreAgasajado = e.getValue().getNombreAgasajado();
			
			Map<Integer,ParticipanteLista> parts =e.getValue().GetParticipantes();
			Map<Integer,ParticipanteListaDTO> partsDTO = new HashMap<Integer, ParticipanteListaDTO>();
			
			for (Map.Entry<Integer, ParticipanteLista> f : parts.entrySet()) {
				ParticipanteListaDTO partDTO = new ParticipanteListaDTO();
				partDTO.estado = f.getValue().getEstado();
				partDTO.pago = f.getValue().isPago();
				
				UsuarioDTO usu = new UsuarioDTO();
				usu.apellido =  e.getValue().getAdministrador().getApellido();
				usu.codigo = e.getValue().getAdministrador().getCodigo();
				usu.DNI = e.getValue().getAdministrador().getDNI();
				usu.mail = e.getValue().getAdministrador().getMail();
				usu.nombre = e.getValue().getAdministrador().getNombre();
				usu.pass = e.getValue().getAdministrador().getPass();
				usu.user = e.getValue().getAdministrador().getUser();
				
				partDTO.usuario = usu;
				partsDTO.put(f.getKey(), partDTO);	
			}
			listaDTO.participantes = partsDTO;
			 
			
			UsuarioDTO u = new UsuarioDTO();
			u.apellido =  e.getValue().getAdministrador().getApellido();
			u.codigo = e.getValue().getAdministrador().getCodigo();
			u.DNI = e.getValue().getAdministrador().getDNI();
			u.mail = e.getValue().getAdministrador().getMail();
			u.nombre = e.getValue().getAdministrador().getNombre();
			u.pass = e.getValue().getAdministrador().getPass();
			u.user = e.getValue().getAdministrador().getUser();
			
			listaDTO.administrador = u;
			listasDTO.put(listaDTO.codigo, listaDTO);
		}
		
		return listasDTO; 
	}
	public static ListaDeRegalosDTO buscarLista(int codigo) {
		ListaDeRegalos lista =AdmPersistenciaListaRegalos.getInstancia().buscarListaDeRegalos(codigo);
		if (lista != null) {
			ListaDeRegalosDTO listaDTO = new ListaDeRegalosDTO();
			listaDTO.codigo = lista.getCodigo();
			listaDTO.estado = lista.getEstado();
			listaDTO.fechaAgasajo = lista.getFechaAgasajo();
			listaDTO.fechaFin = lista.getFechaFin();
			listaDTO.fechaInicio = lista.getFechaInicio();
			listaDTO.mailAgasajado = lista.getMailAgasajado();
			listaDTO.montoARecaudarXIntegrante = lista.getMontoARecaudarXIntegrante();
			listaDTO.montoRecaudado = lista.getMontoRecaudado();
			listaDTO.nombreAgasajado = lista.getNombreAgasajado();
			
			Map<Integer,ParticipanteLista> parts =lista.GetParticipantes();
			Map<Integer,ParticipanteListaDTO> partsDTO = new HashMap<Integer, ParticipanteListaDTO>();
			
			for (Map.Entry<Integer, ParticipanteLista> f : parts.entrySet()) {
				ParticipanteListaDTO partDTO = new ParticipanteListaDTO();
				partDTO.estado = f.getValue().getEstado();
				partDTO.pago = f.getValue().isPago();
				
				UsuarioDTO usu = new UsuarioDTO();
				usu.apellido =  f.getValue().getUsuario().getApellido();
				usu.codigo = f.getValue().getUsuario().getCodigo();
				usu.DNI = f.getValue().getUsuario().getDNI();
				usu.mail = f.getValue().getUsuario().getMail();
				usu.nombre = f.getValue().getUsuario().getNombre();
				usu.pass = f.getValue().getUsuario().getPass();
				usu.user = f.getValue().getUsuario().getUser();
				
				partDTO.usuario = usu;
				partsDTO.put(f.getKey(), partDTO);	
			}
			
			listaDTO.participantes = partsDTO;
			
			UsuarioDTO u = new UsuarioDTO();
			u.apellido =  lista.getAdministrador().getApellido();
			u.codigo = lista.getAdministrador().getCodigo();
			u.DNI = lista.getAdministrador().getDNI();
			u.mail = lista.getAdministrador().getMail();
			u.nombre = lista.getAdministrador().getNombre();
			u.pass = lista.getAdministrador().getPass();
			u.user = lista.getAdministrador().getUser();
			
			listaDTO.administrador = u;
			return listaDTO;			
		}				
		return null;
	}
	public static Map<Integer,ListaDeRegalosDTO> buscarListasAdministrador(int codigo){
		Map<Integer,ListaDeRegalos> listas = AdmPersistenciaListaRegalos.getInstancia().buscarListasAdministrador(codigo);
		Map<Integer,ListaDeRegalosDTO> listasDTO = new HashMap<Integer, ListaDeRegalosDTO>();
		
		for (Map.Entry<Integer, ListaDeRegalos> e : listas.entrySet()) {
			ListaDeRegalosDTO listaDTO = new ListaDeRegalosDTO();
			listaDTO.codigo = e.getValue().getCodigo();
			listaDTO.estado = e.getValue().getEstado();
			listaDTO.fechaAgasajo = e.getValue().getFechaAgasajo();
			listaDTO.fechaFin = e.getValue().getFechaFin();
			listaDTO.fechaInicio = e.getValue().getFechaInicio();
			listaDTO.mailAgasajado = e.getValue().getMailAgasajado();
			listaDTO.montoARecaudarXIntegrante = e.getValue().getMontoARecaudarXIntegrante();
			listaDTO.montoRecaudado = e.getValue().getMontoRecaudado();
			listaDTO.nombreAgasajado = e.getValue().getNombreAgasajado();
			
			Map<Integer,ParticipanteLista> parts =e.getValue().GetParticipantes();
			Map<Integer,ParticipanteListaDTO> partsDTO = new HashMap<Integer, ParticipanteListaDTO>();
			
			for (Map.Entry<Integer, ParticipanteLista> f : parts.entrySet()) {
				ParticipanteListaDTO partDTO = new ParticipanteListaDTO();
				partDTO.estado = f.getValue().getEstado();
				partDTO.pago = f.getValue().isPago();
				
				UsuarioDTO usu = new UsuarioDTO();
				usu.apellido =  f.getValue().getUsuario().getApellido();
				usu.codigo = f.getValue().getUsuario().getCodigo();
				usu.DNI = f.getValue().getUsuario().getDNI();
				usu.mail = f.getValue().getUsuario().getMail();
				usu.nombre = f.getValue().getUsuario().getNombre();
				usu.pass = f.getValue().getUsuario().getPass();
				usu.user = f.getValue().getUsuario().getUser();
				
				partDTO.usuario = usu;
				partsDTO.put(f.getKey(), partDTO);	
			}
			
			listaDTO.participantes = partsDTO;			
			
			
			
			UsuarioDTO u = new UsuarioDTO();
			u.apellido =  e.getValue().getAdministrador().getApellido();
			u.codigo = e.getValue().getAdministrador().getCodigo();
			u.DNI = e.getValue().getAdministrador().getDNI();
			u.mail = e.getValue().getAdministrador().getMail();
			u.nombre = e.getValue().getAdministrador().getNombre();
			u.pass = e.getValue().getAdministrador().getPass();
			u.user = e.getValue().getAdministrador().getUser();
			
			listaDTO.administrador = u;
			
			listasDTO.put(listaDTO.codigo, listaDTO);
		}
		
		return listasDTO; 
	}

	public static void BajaLista(Integer l) {
		// TODO Auto-generated method stub
		ListaDeRegalos lr = AdmPersistenciaListaRegalos.getInstancia().buscarListaDeRegalos(l);
		lr.setEstado("Inactivo");
		ListaDeRegalos.updateEstado(lr);
		Map<Integer, ParticipanteLista> participantes = lr.GetParticipantes();
		
		for (Map.Entry<Integer, ParticipanteLista> e : participantes.entrySet()) {
			lr.BajarParticipanteLista(e.getKey(), lr.getCodigo());
		}
	}

	public static boolean CrearNuevoPago(int listaDeRegalos, Date fecha, int usuario, float monto) {
		// TODO Auto-generated method stub
		ParticipanteLista pl = ParticipanteLista.buscarParticipante(usuario, listaDeRegalos);
		if (pl == null) {
			return false;
		}
		return pl.CrearNuevoPago(listaDeRegalos,usuario,fecha, monto);
		
	}
	
	public static boolean SendMailListasAgasajo (Date fecha) {
		try {
			//VER SI SE AGREGA ACA EL OBSERVER O DONDE
			Map<Integer,ListaDeRegalos> listas = AdmPersistenciaListaRegalos.getInstancia().BuscarListaAgasajo(fecha);
			DESPACHADORMAILS o = DESPACHADORMAILS.getInstancia();
		
			for (Map.Entry<Integer, ListaDeRegalos> e : listas.entrySet()) {
				e.getValue().Attach(o);
				e.getValue().SendMails(1); //tipo 1 = Agasajo
			}
			return true;
		} catch (Exception ex){
			return false;
		}
	}


}
