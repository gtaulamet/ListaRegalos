package DTO;
import java.util.Date;
import java.util.Map;
import java.util.Vector;

import persistencia.AdmPersistenciaListaRegalos;
import persistencia.AdmPersistenciaParticipanteLista;

public class ListaDeRegalosDTO {
	public int codigo;
	public String nombreAgasajado;
	public Date fechaAgasajo;
	public String mailAgasajado;
	public float montoRecaudado;
	public Date fechaInicio;
	public Date fechaFin;
	public String estado;
	public Map<Integer,ParticipanteListaDTO> participantes;
	public UsuarioDTO administrador;
	public float montoARecaudarXIntegrante;
	
}
