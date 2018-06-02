package modelo;
import java.util.Date;
import java.util.List;


public class ParticipanteLista {
	private Usuario usuario;
	private boolean pago;
	private String estado;
	
	
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
