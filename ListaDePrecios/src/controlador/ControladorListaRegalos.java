package controlador;
import java.util.Vector;

import modelo.ListaDeRegalos;


public class ControladorListaRegalos {
	private static ControladorListaRegalos controladorListaRegalos;
	public Vector<ListaDeRegalos> listasDeRegalos;
	
	public ControladorListaRegalos GetInstance() {
		if (this.controladorListaRegalos == null) {
			this.controladorListaRegalos = new ControladorListaRegalos();
		}
		return this.controladorListaRegalos;
	}
	
	private ControladorListaRegalos() {
		this.listasDeRegalos = new Vector<ListaDeRegalos>(); 
	}
	
	
	public ListaDeRegalos GetListaRegalos(int l) {
		return null;
	}
	
	public void VerificarEstadoListas(int dias) {
		
	}
	
	public Vector<ListaDeRegalos> GetListasDelParticipante(int u) {
		return null;
	}
	
	public void BajarParticipanteLista(int usuario, int codigoLista) {
	
	}
	
	public void AgregarParticipanteLista(int usuario, int codigoLista) {
	
	}
}
