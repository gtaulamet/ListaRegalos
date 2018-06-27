import java.awt.EventQueue;
import java.util.List;
import java.util.Map;

import modelo.IObserverMail;
import modelo.ListaDeRegalos;
import modelo.ParticipanteLista;
import vista.Login;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import DTO.ParticipanteListaDTO;


public class DESPACHADORMAILS implements IObserverMail {
	public static void main(String[] args) {
		DESPACHADORMAILS d = new DESPACHADORMAILS();
		d.testmail();
	}
	
	public void testmail (){
		 try
	        {
	            // Propiedades de la conexi�n
	            Properties props = new Properties();
	            props.setProperty("mail.smtp.host", "smtp.gmail.com");
	            props.setProperty("mail.smtp.starttls.enable", "true");
	            props.setProperty("mail.smtp.port", "25");
	            props.setProperty("mail.smtp.user", "pgplaystation@gmail.com");
	            props.setProperty("mail.smtp.auth", "true");

	            // Preparamos la sesion
	            Session session = Session.getDefaultInstance(props);

	            // Construimos el mensaje
	            MimeMessage message = new MimeMessage(session);
	            message.setFrom(new InternetAddress("pablointiar@gmail.com"));
	            message.addRecipient(
	                Message.RecipientType.TO,
	                new InternetAddress("pgplaystation@gmail.com"));
	            message.setSubject("Hola");
	            message.setText(
	                "Mensajito con Java Mail" + "de los buenos." + "poque si");

	            // Lo enviamos.
	            Transport t = session.getTransport("smtp");
	            t.connect("pgplaystation@gmail.com", "silvetti");
	            t.sendMessage(message, message.getAllRecipients());

	            // Cierre.
	            t.close();
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	}
	
	@Override
	public void SendMailFinalizo(ListaDeRegalos l) {
		// TODO Auto-generated method stub
		
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
