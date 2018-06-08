package modelo;
import java.util.Date;
import java.util.Map;
import java.util.Vector;

import persistencia.AdmPersistenciaListaRegalos;
import persistencia.AdmPersistenciaParticipanteLista;

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

	public void CrearNuevoPago(Date fecha, Usuario u, float monto) {
		//todo
	}
	
	public Map<Integer,ParticipanteLista> GetParticipantesImpagos() {
		//todo
		return null;
	}
	
	public Map<Integer,ParticipanteLista> GetParticipantes() {
		Map<Integer,ParticipanteLista> lista = ParticipanteLista.buscarTodosXLista(this.getCodigo()); 
		this.participantes = lista;
				
		return this.participantes;
		
	}
	
	private ParticipanteLista GetParticipante(Usuario u) {
		return ParticipanteLista.buscarParticipante(u.getCodigo(), this.getCodigo());
	}
	
	public void BajarParticipanteLista(Usuario u) {
		GetParticipantes();
		ParticipanteLista.darDeBaja(u.getCodigo(), this.getCodigo());
		this.participantes.remove(u.getCodigo());
	}
	
	public void AgregarParticipanteLista(Usuario ul) {
		GetParticipantes();
		//TODO
		//primero hay que revisar si no existe el participante para ese usuario inactivo.
		//Si es asi, se activa el participante. Sino se crea uno nuevo.
		ParticipanteLista partInactivo = ParticipanteLista.buscarParticipante(ul.getCodigo(), this.getCodigo());
		if (partInactivo != null) {
			partInactivo.setEstado("Activo");
			ParticipanteLista.update(partInactivo,this.getCodigo());
			this.participantes.put(ul.getCodigo(), partInactivo);
		} else {
			ParticipanteLista pl = new ParticipanteLista(ul, false, "Activo");
			ParticipanteLista.insert(pl,this.getCodigo());
			this.participantes.put(ul.getCodigo(),pl);
		}
	}

	@Override
	public void ModificarEstado() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Attach(IObserverMail o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Deattach(IObserverMail o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void SendMails() {
		// TODO Auto-generated method stub
		
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
	
	public static void insert(ListaDeRegalos lr) {
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
	public static Map<Integer,ListaDeRegalos> buscarListasParticipo(int u){
		return AdmPersistenciaListaRegalos.getInstancia().buscarListasParticipo(u);
	}
	public static ListaDeRegalos buscarLista(int codigo) {
		return AdmPersistenciaListaRegalos.getInstancia().buscarAListaDeRegalos(codigo);
	}
	public static Map<Integer,ListaDeRegalos> buscarListasAdministrador(int codigo){
		return AdmPersistenciaListaRegalos.getInstancia().buscarListasAdministrador(codigo);
	}
}
