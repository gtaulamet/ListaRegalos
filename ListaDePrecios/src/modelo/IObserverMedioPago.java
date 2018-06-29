package modelo;
import java.util.Date;

public interface IObserverMedioPago {
	public boolean NotificarPago(int listaDeRegalos, Date fecha, int usuario, float monto);
}
