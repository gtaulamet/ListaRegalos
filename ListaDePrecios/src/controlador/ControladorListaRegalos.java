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
import persistencia.AdmPersistenciaParticipanteLista;


public class ControladorListaRegalos {
	private static ControladorListaRegalos controladorListaRegalos;
	
	public Map<Integer,ListaDeRegalosDTO> listasDeRegalos;
	
	public static ControladorListaRegalos GetInstance() {
		if (controladorListaRegalos == null) {
			controladorListaRegalos = new ControladorListaRegalos();
		}
		return controladorListaRegalos;
	}
	
	private ControladorListaRegalos() {
		this.listasDeRegalos = new HashMap<Integer,ListaDeRegalosDTO>(); 
	}
	
	
	public ListaDeRegalosDTO GetListaRegalos(int l) {
		ListaDeRegalosDTO lista = ListaDeRegalos.buscarLista(l);
		
		return lista;
	}
	
	public void VerificarEstadoListas(int dias) {
		//todo
	}
	
	public Map<Integer,ListaDeRegalosDTO> GetListasDelParticipante(int u) {
		listasDeRegalos.clear();

		//map con todos los participanteLista en los que el usuario esta referido
//		//Integer=codigoLista
//		Map<Integer,ParticipanteLista> pl = ParticipanteLista.buscarTodosXUsuario(u);
//		Set<Map.Entry<Integer,ParticipanteLista>>	lista = pl.entrySet();
//		
//		for(Map.Entry<Integer, ParticipanteLista> e : lista){
//			ListaDeRegalos aux = ListaDeRegalos.buscarLista(e.getKey());
//			if (aux.getEstado()!="Inactivo")
//				listasDeRegalos.put(e.getKey(), aux);
//		}
		
		return ListaDeRegalos.buscarListasParticipo(u);
	}
	
	public void BajarParticipanteLista(int usuario, int codigoLista) {
//		ListaDeRegalosDTO lr = this.GetListaRegalos(codigoLista);
//		Usuario u = ControladorUsuario.GetInstance().GetUsuario(usuario);
		
		ListaDeRegalos.BajarParticipanteLista(usuario,codigoLista);
		
//		lr.BajarParticipanteLista(u);
	}
	
	public void AgregarParticipanteLista(int usuario, int codigoLista) {
//		Usuario u = ControladorUsuario.GetInstance().GetUsuario(usuario);
		ListaDeRegalos.AgregarParticipanteLista(usuario,codigoLista);
//		ListaDeRegalos lr = this.GetListaRegalos(codigoLista);
//		lr.AgregarParticipanteLista(u);
		
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
//		ListaDeRegalosDTO lr = this.GetListaRegalos(codigoLista);
		return ListaDeRegalos.GetParticipantes(codigoLista);
//		return lr.GetParticipantes();
	}

	public void BorrarListaRegalos(Integer lr) {
		ListaDeRegalos.BajaLista(lr);
		

	}

	public boolean NotificarPago(int listaDeRegalos, Date fecha, int usuario, float monto) {
		// TODO Auto-generated method stub
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
		boolean proceso = ListaDeRegalos.SendMailListasAgasajo(fecha);
	}
	
	public void EnviarEmailInicioLista(Date fecha) {
		boolean proceso = ListaDeRegalos.SendMailInicioLista(fecha);
	}

}
