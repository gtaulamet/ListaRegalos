package controlador;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import modelo.ListaDeRegalos;
import modelo.ParticipanteLista;
import modelo.Usuario;
import persistencia.AdmPersistenciaParticipanteLista;
import vista.ListaRegaloAdministrador;


public class ControladorListaRegalos {
	private static ControladorListaRegalos controladorListaRegalos;
	
	public Map<Integer,ListaDeRegalos> listasDeRegalos;
	
	public static ControladorListaRegalos GetInstance() {
		if (controladorListaRegalos == null) {
			controladorListaRegalos = new ControladorListaRegalos();
		}
		return controladorListaRegalos;
	}
	
	private ControladorListaRegalos() {
		this.listasDeRegalos = new HashMap<Integer,ListaDeRegalos>(); 
	}
	
	
	public ListaDeRegalos GetListaRegalos(int l) {
		return ListaDeRegalos.buscarLista(l);
	}
	
	public void VerificarEstadoListas(int dias) {
		//todo
	}
	
	public Map<Integer,ListaDeRegalos> GetListasDelParticipante(int u) {
		if (listasDeRegalos.isEmpty()) {
			//map con todos los participanteLista en los que el usuario esta referido
			//Integer=codigoLista
			Map<Integer,ParticipanteLista> pl = ParticipanteLista.buscarTodosXUsuario(u);
			Set<Map.Entry<Integer,ParticipanteLista>>	lista = pl.entrySet();
			
			for(Map.Entry<Integer, ParticipanteLista> e : lista){
				ListaDeRegalos aux = ListaDeRegalos.buscarLista(e.getKey());
				listasDeRegalos.put(e.getKey(), aux);
			}
		}
		return listasDeRegalos;
	}
	
	public void BajarParticipanteLista(int usuario, int codigoLista) {
		ParticipanteLista.darDeBaja(usuario, codigoLista);
	}
	
	public void AgregarParticipanteLista(int usuario, int codigoLista) {
		Usuario u = Usuario.buscarUsuario(usuario);
		ListaDeRegalos lr = ListaDeRegalos.buscarLista(codigoLista);
		
		ParticipanteLista pl = new ParticipanteLista(u, lr, false, "Activo");
		ParticipanteLista.insert(pl);
	}
	
	public Map<Integer,ListaDeRegalos> GetListasAdministrador(int u){
		return ListaDeRegalos.buscarListasAdministrador(u);
	}
	
	public void crearListaRegalos(int codigo, String nombreAgasajado, Date fechaAgasajo, String mailAgasajado,
			float montoRecaudado, Date fechaInicio, Date fechaFin, String estado, Usuario administrador,
			float montoARecaudarXIntegrante) {
		
		ListaDeRegalos lr = new ListaDeRegalos(codigo, nombreAgasajado, fechaAgasajo, mailAgasajado, montoRecaudado, fechaInicio, fechaFin, estado, administrador, montoARecaudarXIntegrante);
		ListaDeRegalos.insert(lr);
	}
}
