package modelo;
import java.util.List;

public interface IObservableMedioPago {
	public List<IObserverMedioPago> ObserversPago = null;
	public void Attach(IObserverMedioPago o);
	public void Deattach(IObserverMedioPago o);
	public void Notificar();
}
