package controlador;
import java.util.Date;
import java.util.Vector;

import modelo.IObserverMedioPago;
import modelo.Pago;

public class ControladorPagos implements IObserverMedioPago {
	private static ControladorPagos controladorPagos;
	public Vector<Pago> pagos;
	
	public static ControladorPagos GetInstance() {
		if (controladorPagos == null) {
			controladorPagos = new ControladorPagos();
		}
		return controladorPagos;
	}
	
	private ControladorPagos() {
		this.pagos = new Vector<Pago>();
	}
	
	@Override
	public boolean NotificarPago(int listaDeRegalos, Date fecha, int usuario, float monto) {
		// TODO Auto-generated method stub
		return ControladorListaRegalos.GetInstance().NotificarPago(listaDeRegalos,fecha,usuario,monto);

	}
	public Vector<Pago> getPagos() {
		return pagos;
	}
	
	
}
