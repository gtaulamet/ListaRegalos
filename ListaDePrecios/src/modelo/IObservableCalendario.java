package modelo;
import java.util.List;

public interface IObservableCalendario {
	public List<IObserverCalendario> observersCalendario=null;
	public void Attach(IObserverCalendario o);
	
	public void Deattach(IObserverCalendario o);
	
	public void NotifyAll();
}
