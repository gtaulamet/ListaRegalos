package controlador;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import DTO.AdministradorDTO;
import DTO.ListaDeRegalosDTO;
import DTO.ParticipanteListaDTO;
import DTO.UsuarioDTO;
import modelo.ListaDeRegalos;
import modelo.ParticipanteLista;
import modelo.Usuario;
import persistencia.AdmPersistenciaListaRegalos;
import persistencia.AdmPersistenciaParticipanteLista;


public class ControladorListaRegalos {
	private static ControladorListaRegalos controladorListaRegalos;
	
	public static ControladorListaRegalos GetInstance() {
		if (controladorListaRegalos == null) {
			controladorListaRegalos = new ControladorListaRegalos();
		}
		return controladorListaRegalos;
	}
	
	private ControladorListaRegalos() {
	}
	
	
	public ListaDeRegalosDTO GetListaRegalos(int l) {
		ListaDeRegalosDTO lista = ListaDeRegalos.buscarLista(l);
		
		return lista;
	}
	
	public void VerificarEstadoListas(int dias) {
		//todo
	}
	
	public Map<Integer,ListaDeRegalosDTO> GetListasDelParticipante(int u) {
		//map con todos los participanteLista en los que el usuario esta referido
		
		return ListaDeRegalos.buscarListasParticipo(u);
	}
	
	public void BajarParticipanteLista(int usuario, int codigoLista) {
		ListaDeRegalos.BajarParticipanteLista(usuario,codigoLista);
	}
	
	public void AgregarParticipanteLista(int usuario, int codigoLista) {
		ListaDeRegalos.AgregarParticipanteLista(usuario,codigoLista);
	}
	
	public Map<Integer,ListaDeRegalosDTO> GetListasAdministrador(int u){
		return ListaDeRegalos.buscarListasAdministrador(u);
	}
	
	public void CrearListaRegalos(String nombreAgasajado, Date fechaAgasajo, String mailAgasajado,
			float montoRecaudado, Date fechaInicio, Date fechaFin, String estado, UsuarioDTO administrador,
			float montoARecaudarXIntegrante) {
		ListaDeRegalosDTO ldto = new ListaDeRegalosDTO();
		ldto.estado = estado;
		ldto.fechaAgasajo = fechaAgasajo;
		ldto.fechaFin = fechaFin;
		ldto.fechaInicio = fechaInicio;
		ldto.mailAgasajado = mailAgasajado;
		ldto.montoARecaudarXIntegrante = montoARecaudarXIntegrante;
		ldto.montoRecaudado = montoRecaudado;
		ldto.nombreAgasajado = nombreAgasajado;
		
		UsuarioDTO uDTO = new UsuarioDTO();
		uDTO.apellido = administrador.apellido;
		uDTO.codigo = administrador.codigo;
		uDTO.DNI = administrador.DNI;
		uDTO.mail = administrador.mail;
		uDTO.nombre = administrador.nombre;
		uDTO.pass = administrador.pass;
		uDTO.user = administrador.user;
		
		ldto.administrador = uDTO;
		
		ListaDeRegalos.insert(ldto);

	}

	public Map<Integer,ParticipanteListaDTO> BuscarParticipantesLista(int codigoLista){
		return ListaDeRegalos.GetParticipantes(codigoLista);
	}

	public void BorrarListaRegalos(Integer lr) {
		ListaDeRegalos.BajaLista(lr);
		

	}

	public boolean NotificarPago(int listaDeRegalos, Date fecha, int usuario, float monto) {
		ListaDeRegalosDTO lDTO = GetListaRegalos(listaDeRegalos);
		if (lDTO == null) {
			return false;
		} else if (lDTO.montoARecaudarXIntegrante != monto) {
			return false;
		}
		UsuarioDTO uDTO = ControladorUsuario.GetInstance().buscarUsuario(usuario);
		if (uDTO == null) {
			return false;
		}
		return ListaDeRegalos.CrearNuevoPago(listaDeRegalos,fecha,usuario,monto);
		
	}
	
	public void EnviarEmailAgasajado(Date fecha) {
		Map<Integer, ListaDeRegalosDTO> listas = ListaDeRegalos.getListasAgasajo(fecha);
		
		for (Map.Entry<Integer, ListaDeRegalosDTO> e : listas.entrySet()) {
			SendMails(1,e.getValue().codigo); //tipo 1 = Agasajo
		}
		
	}
	
	public void EnviarEmailInicioLista(Date fecha) {
		Map<Integer, ListaDeRegalosDTO> listas = ListaDeRegalos.getListasInicio(fecha);
		
		for (Map.Entry<Integer, ListaDeRegalosDTO> e : listas.entrySet()) {
			SendMails(3,e.getValue().codigo); //tipo 3 = Inicio
		}
		
	}
	
	public void EnviarEmailProximoAVencer(Date fecha) {
		Map<Integer, ListaDeRegalosDTO> listas = ListaDeRegalos.getListasAVencer(fecha);
		
		for (Map.Entry<Integer, ListaDeRegalosDTO> e : listas.entrySet()) {
			SendMails(2,e.getValue().codigo); //tipo 2 = A vencer
		}
		
	}
	
	public void ModificarEstado(Date fecha) {
		boolean proceso = ListaDeRegalos.ModificarEstadoLista(fecha);
	}
	
	private static void SendMails(int i, int lr) {
		// TODO Auto-generated method stub
		DESPACHADORMAILS o = DESPACHADORMAILS.getInstancia();
		ListaDeRegalosDTO lista = ListaDeRegalos.buscarLista(lr);
		switch (i){
			case 1:
					o.SendMailAgasajo(lista);
				break;
			case 2: 
					o.SendMailsProximoFinalizar(lista);
				break;
			case 3:
					o.SendMailsInicio(lista);
				break;
			default: break;
		}
	}
}
