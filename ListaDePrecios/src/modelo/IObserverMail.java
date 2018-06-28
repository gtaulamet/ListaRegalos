package modelo;
import java.util.List;
import java.util.Map;

import DTO.ParticipanteListaDTO;

public interface IObserverMail {
	//public void SendMailFinalizo(ListaDeRegalos l);
	public void SendMailAgasajo(ListaDeRegalos l);
	public void SendMailsInicio(Map<Integer,ParticipanteLista> p);
	public void SendMailsProximoFinalizar(Map<Integer,ParticipanteListaDTO> p);
}
