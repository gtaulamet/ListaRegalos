package modelo;
import java.util.List;
import java.util.Map;

import DTO.ParticipanteListaDTO;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : IObserverMail.java
//  @ Date : 19/05/2018
//  @ Author : 
//
//




public interface IObserverMail {
	public void SendMailFinalizo(ListaDeRegalos l);
	public void SendMailsInicio(Map<Integer,ParticipanteLista> p);
	public void SendMailsProximoFinalizar(Map<Integer,ParticipanteListaDTO> p);
}
