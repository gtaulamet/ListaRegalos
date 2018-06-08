package controlador;
import java.util.Date;
import java.util.Vector;

import modelo.IObserverMedioPago;
import modelo.Pago;

public class ControladorPagos implements IObserverMedioPago {
	private static ControladorPagos controladorPagos;
	public Vector<Pago> pagos;
	
	public ControladorPagos GetInstance() {
		if (this.controladorPagos != null) {
			this.controladorPagos = new ControladorPagos();
		}
		return this.controladorPagos;
	}
	
	private ControladorPagos() {
		this.pagos = new Vector<Pago>();
	}
	
	@Override
	public void NotificarPago(int listaDeRegalos, Date fecha, int usuario, float monto) {
		// TODO Auto-generated method stub
		
	}
	public Vector<Pago> getPagos() {
		return pagos;
	}
	
	
}
