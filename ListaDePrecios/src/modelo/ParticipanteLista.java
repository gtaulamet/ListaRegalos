package modelo;
import java.util.Date;
import java.util.List;


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
	
	}
}
