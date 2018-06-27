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
	//d.testmail();
		d.sendEmail();
	}
	
	public void testmail (){
		 try
	        {
	            // Propiedades de la conexión
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
	
	
	private void init() {

		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.port",587);
		properties.put("mail.smtp.user", "pgplaystation@gmail.com");
		properties.put("mail.smtp.password", "silvetti"); 
		//properties.put("mail.smtp.mail.sender","mail@gmail.com");
		//properties.put("mail.smtp.user", "pgplaystation@gmail.com");
		properties.put("mail.smtp.auth", "true");

		session = Session.getDefaultInstance(properties);
		password = "silvetti";
	}

	public void sendEmail(){

		init();
		try{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("pgplaystation@gmail.com"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress("pablointiar@gmail.com"));
			message.setSubject("Prueba 1");
			message.setText("Texto 1");
			Transport t = session.getTransport("smtp");
			 t.connect("smtp.gmail.com", "pgplaystation@gmail.com", "silvetti");
			//t.connect((String)properties.get("mail.smtp.user"), password);
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
