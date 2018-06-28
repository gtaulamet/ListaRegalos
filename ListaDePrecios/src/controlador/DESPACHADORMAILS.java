package controlador;
import java.util.Map;

import modelo.IObserverMail;
import modelo.ListaDeRegalos;
import modelo.ParticipanteLista;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import DTO.ParticipanteListaDTO;


public class DESPACHADORMAILS implements IObserverMail {
	
	private final Properties properties = new Properties();
	

	private String password;

	private Session session;
	
	public static void main(String[] args) {
		DESPACHADORMAILS d = new DESPACHADORMAILS();
	//	String cuerpo="Felicitaciones ";
		//String destinatario= "pablointiar@gmaiL.com";
		//String asunto = "Tienes un agasajo";
		//d.sendEmail(destinatario,asunto,cuerpo);
	}
	
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
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
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
	
	@Override	
	public void SendMailAgasajo(ListaDeRegalos l) {
		String cuerpo="Felicitaciones "+l.getNombreAgasajado() + "Tienes un regalo de $" + l.getMontoRecaudado()+" para gastar. Saludos";
		String destinatario= l.getMailAgasajado();
		String asunto = "Tienes un agasajo";
		this.sendEmail(destinatario, asunto, cuerpo);
	}


	@Override
	public void SendMailsInicio(Map<Integer,ParticipanteLista> p) {
		// TODO Auto-generated method stub
	}

	@Override
	public void SendMailsProximoFinalizar(Map<Integer,ParticipanteListaDTO> p) {
		// TODO Auto-generated method stub
		
	}
}
