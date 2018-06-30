package controlador;
import java.util.Map;

import modelo.ListaDeRegalos;
import modelo.ParticipanteLista;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.DebugGraphics;

import DTO.ListaDeRegalosDTO;
import DTO.ParticipanteListaDTO;


public class DESPACHADORMAILS {
	
	private final Properties properties = new Properties();
	private static DESPACHADORMAILS instancia;

	private DESPACHADORMAILS() {
		
	}
	
	public static DESPACHADORMAILS getInstancia() {
		if (instancia == null) {
			instancia = new DESPACHADORMAILS();
		}
		return instancia;
	}
	private String password;

	private Session session;

	
	private void init() {

		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.port",587);
		properties.put("mail.smtp.user", "pgplaystation@gmail.com");
		properties.put("mail.smtp.password", "silvetti"); 
		properties.put("mail.smtp.auth", "true");

		session = Session.getDefaultInstance(properties);
		password = "silvetti";
	}

	public void sendEmail(String destinatario, String asunto, String cuerpo){
		 
		String remitente = "pgplaystation@gmail.com";
		String clave = "silvetti";
		init();
		try{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(remitente));
			message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
			message.setSubject(asunto);
			message.setText(cuerpo);
			Transport t = session.getTransport("smtp");
			t.connect("smtp.gmail.com", remitente, clave);
			t.sendMessage(message, message.getAllRecipients());
			t.close();
		}catch (MessagingException me)

		{
			me.printStackTrace();
			//Aqui se deberia o mostrar un mensaje de error o en lugar
                        //de no hacer nada con la excepcion, lanzarla para que el modulo
                        //superior la capture y avise al usuario con un popup, por ejemplo.
			return;
		}
		
	}
	
	public void SendMailAgasajo(ListaDeRegalosDTO l) {
		String cuerpo="Felicitaciones "+l.nombreAgasajado + "Tienes un regalo de $" + l.montoRecaudado+" para gastar. Saludos";
		String destinatario= l.mailAgasajado;
		String asunto = "Tienes un agasajo";
		this.sendEmail(destinatario, asunto, cuerpo);
	}


	public void SendMailsInicio(ListaDeRegalosDTO l) {
		
		String cuerpo;
		String destinatario="";
		String asunto;
		Map<Integer,ParticipanteListaDTO> participantes =  ControladorListaRegalos.GetInstance().BuscarParticipantesLista(l.codigo);
		
		cuerpo="Hola, Sos parte de la lista de regalo "+l.codigo + ". Para agasajar a "+ l.nombreAgasajado +" Con un monto de $" + l.montoARecaudarXIntegrante+". Saludos";
		asunto = "Participación para una nuevo agasajo";
		for (Map.Entry<Integer, ParticipanteListaDTO> e : participantes.entrySet()) {
		
			
			destinatario += e.getValue().usuario.mail+", ";
		}
			this.sendEmail(destinatario, asunto, cuerpo);
		
	}

	public void SendMailsProximoFinalizar(ListaDeRegalosDTO l) {
		String cuerpo;
		String destinatario="";
		String asunto;
		Map<Integer,ParticipanteListaDTO> participantes =  ListaDeRegalos.GetParticipantesImpagos(l.codigo);
		
		cuerpo="Hola, Sos parte de la lista de regalo "+l.codigo + ". Para agasajar a "+ l.nombreAgasajado +" Con un monto de $" + l.montoARecaudarXIntegrante+"."
				+ " Tenes tiempo hasta el "+ l.fechaFin +" para realizar el pago. Saludos";
		asunto = "Aviso de finalización de lista para agasajo";
		for (Map.Entry<Integer, ParticipanteListaDTO> e : participantes.entrySet()) {
		
			
			destinatario += e.getValue().usuario.mail+", ";
		}
		
		this.sendEmail(destinatario, asunto, cuerpo);
		
		
	}
}
