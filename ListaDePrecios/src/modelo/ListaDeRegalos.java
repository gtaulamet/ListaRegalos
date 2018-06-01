package modelo;
import java.util.Date;
import java.util.Vector;

public class ListaDeRegalos implements IObservableMails, IObserverCalendario {
	private int codigo;
	private String nombreAgasajado;
	private Date fechaAgasajo;
	private String mailAgasajado;
	private float montoRecaudado;
	private Date fechaInicio;
	private Date fechaFin;
	private String estado;
	private Vector<ParticipanteLista> participantes;
	private Usuario administrador;
	private float montoARecaudarXIntegrante;
	

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
	
	}
	
	public Vector<ParticipanteLista> GetParticipantesImpagos() {
		return null;
	}
	
	public Vector<ParticipanteLista> GetParticipantes() {
		return null;
	}
	
	private ParticipanteLista GetParticipante(Usuario u) {
		return null;
	}
	
	private void ActualizarMontoRecaudado(float monto) {
	
	}
	
	public void SetEstado(String estado) {
	
	}
	
	public void BajarParticipanteLista(Usuario u) {
	
	}
	
	public void AgregarParticipanteLista(Usuario ul) {
	
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
	
	public void insert(int codigo, String nombreAgasajado, Date fechaAgasajo, String mailAgasajado,float montoRecaudado,Date fechaInicio,Date fechaFin,String estado, Usuario administrador, float montoARecaudarXIntegrante) {
		
	}

	//ver si va un delete listaderegalos
}
