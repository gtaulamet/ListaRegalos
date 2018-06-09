package modelo;
import java.util.Date;

public class Pago {
	private int codigo;
	private float monto;
	private Date fecha;
	
	public Pago(int c, float m, Date f) {
		super();
		this.codigo = c;
		this.monto = m;
		this.fecha = f;
	}
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public float GetMonto() {
		return 0;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public void setMonto(float monto) {
		this.monto = monto;
	}
	
	public void insert(float monto, Date fecha, Usuario usuario, int codigo, int listaDeRegalos) {
		
	}
	
}
