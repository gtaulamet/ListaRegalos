package modelo;
import java.util.Date;
import java.util.List;
import java.util.Map;

import persistencia.AdmPersistenciaParticipanteLista;


public class ParticipanteLista {
	private Usuario usuario;
	private ListaDeRegalos listaDeRegalos;
	private boolean pago;
	private String estado;

	public ParticipanteLista(Usuario usuario, ListaDeRegalos listaDeRegalos, boolean pago, String estado) {
		super();
		this.usuario = usuario;
		this.listaDeRegalos = listaDeRegalos;
		this.pago = pago;
		this.estado = estado;
	}

	public ListaDeRegalos getListaDeRegalos() {
		return listaDeRegalos;
	}

	public void setListaDeRegalos(ListaDeRegalos listaDeRegalos) {
		this.listaDeRegalos = listaDeRegalos;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isPago() {
		return pago;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	
	public List<Pago> GetPagos() {
		return null;
	}

	
	public void CrearNuevoPago(Date fecha, float monto) {
		//todo
	}
	
	public static void insert(ParticipanteLista pl) {
		AdmPersistenciaParticipanteLista.getInstancia().insert(pl);
	}
	public static void delete(ParticipanteLista pl) {
		AdmPersistenciaParticipanteLista.getInstancia().delete(pl);
	}
	public static void update(ParticipanteLista pl) {
		AdmPersistenciaParticipanteLista.getInstancia().update(pl);
	}
	public static Map<Integer,ParticipanteLista> buscarTodosXLista(int lista){
		return AdmPersistenciaParticipanteLista.getInstancia().buscarTodos(lista);
	}
	public static Map<Integer,ParticipanteLista> buscarTodosXUsuario(int usuario){
		return AdmPersistenciaParticipanteLista.getInstancia().buscarTodosXUsuario(usuario);
	}
	public static ParticipanteLista buscarParticipante(int u,int lr) {
		return AdmPersistenciaParticipanteLista.getInstancia().buscarAParticipanteLista(u, lr);
	}
	public static void darDeBaja(int u, int lr) {
		AdmPersistenciaParticipanteLista.getInstancia().darBajaParticipanteLista(u, lr);
	}
}
