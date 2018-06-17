import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

import javax.xml.stream.util.EventReaderDelegate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import controlador.ControladorPagos;

public class Demonio extends Thread{
	public String horario;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Demonio d = new Demonio();
		while (true) {
			
			d.run();	
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public Demonio() {
		
	}
	
	@Override
    public void run() {
		//Manejo de tiempos para ejecución del demonio
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		
		java.util.Date ahora = cal.getTime();
		cal.add(Calendar.MINUTE,2);
		java.util.Date proximo = cal.getTime();
		proximo.setSeconds(0);
		ahora.setSeconds(0);
		
		if (this.horario == "" || this.horario == null) {
			this.horario = sdf.format(ahora);
		}
		//-------------------------------------------

		
		if (sdf.format(ahora).equals(horario)) {
			//ejecución de tarea para cuando se cumple el tiempo establecido
			System.out.println(sdf.format(ahora)+" - Hello from a thread! Proximo: "+ sdf.format(proximo));
			
			//Procesamiento de archivos de pagos
			try {
				procesarArchivoPagos(".//IN//TCPagos.dat");
//				procesarArchivoPagos("TDPagos.dat");
//				procesarArchivoPagos("EPagos.dat");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Throwable e) {
				e.printStackTrace();
			}

			//Envio automatico de emails
			
			
			
			horario=sdf.format(proximo);
		}
		
        
    }
//
//	private void esperarXsegundos(int segundos) {
//		try {
//			Thread.sleep(segundos * 1000);
//		} catch (InterruptedException ex) {
//			Thread.currentThread().interrupt();
//		}
//	}

	private void procesarArchivoPagos(String ruta) throws Throwable {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new FileReader(ruta));
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();
		    boolean proceso = true;
		    while (line != null) {
		    	sb.append(line);
		        sb.append(System.lineSeparator());
		        String[] datos = sb.toString().split(";");
		        
		        //Si logra procesar los datos se deberia validar para ver si hago un append en el file de log de errores
		        proceso = procesarDatos(datos);
		        
		        if (!proceso) {
		        	//hacer un append de la linea que no se pudo procesar.
		        }
		        
		        
		        sb.delete(0, sb.length());
		        line = br.readLine();
		    }
		    String everything = sb.toString();
		    
		    System.out.println(everything);
		} finally {
		    br.close();
		}		
		
		//ControladorPagos.GetInstance().NotificarPago(1, new java.util.Date(), 1, 100);
		
	}

	private boolean procesarDatos(String[] datos) throws Exception {
		boolean OK = true;
		//Si la linea no coincide con el formato estipulado
		//IdListaRegalos ; fecha ; IdUsuario ; monto
		if (datos.length != 4) {
			System.out.println("Cantidad erronea.");
			OK = false;
			return OK;
		}
		//Valido que los datos tengan el tipo de dato correspondiente
		try {
			int aux = Integer.valueOf(datos[0]);
			aux = Integer.valueOf(datos[2]);
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date auxD = (java.util.Date) formatter.parse(datos[1]);
			
			float auxF = Float.valueOf(datos[3]);
		}catch (Exception ex) {
			System.out.println("Tipo de datos erroneos.");
			OK = false;
			return OK;
		}
		int idL = Integer.valueOf(datos[0]);
		int idU = Integer.valueOf(datos[2]);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date f = (java.util.Date) formatter.parse(datos[1]);
		float m = Float.valueOf(datos[3]);
		
		//Esto deberia devolver un valor que diga si pudo o no insertar el pago.
		ControladorPagos.GetInstance().NotificarPago(idL, f, idU, m);
		
		return OK;
	}
}
