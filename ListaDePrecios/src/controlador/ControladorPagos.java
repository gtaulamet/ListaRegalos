package controlador;
import java.util.Date;
import java.util.Vector;


public class ControladorPagos  {
	private static ControladorPagos controladorPagos;
	
	public static ControladorPagos GetInstance() {
		if (controladorPagos == null) {
			controladorPagos = new ControladorPagos();
		}
		return controladorPagos;
	}
	
	public boolean NotificarPago(int listaDeRegalos, Date fecha, int usuario, float monto) {
		// TODO Auto-generated method stub
		return ControladorListaRegalos.GetInstance().NotificarPago(listaDeRegalos,fecha,usuario,monto);

	}
	
	
}
