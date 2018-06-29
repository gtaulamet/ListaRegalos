import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.Locale;

import javax.xml.stream.util.EventReaderDelegate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import controlador.ControladorListaRegalos;
import controlador.ControladorPagos;
import controlador.SistemaRegalos;

public class Demonio extends Thread{
	public String horario;
	public String horarioMail;
	
	
	public static void main(String[] args) {
		Demonio d = new Demonio();
		d.start();	
	}
	
	@SuppressWarnings("deprecation")
	public Demonio() {
		//setDaemon(true);
	}
	
	@Override
    public void run() {
		while (true) {
			//Manejo de tiempos para ejecución del demonio
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			
			Calendar cal2 = (Calendar)cal.clone();
			SimpleDateFormat sdfDia = new SimpleDateFormat("yyyy-MM-dd");
			
			java.util.Date ahora = cal.getTime();
			java.util.Date ahoraMail = cal2.getTime();
			
			cal.add(Calendar.MINUTE,2);
			java.util.Date proximo = cal.getTime();
			proximo.setSeconds(0);
			ahora.setSeconds(0);
			ahoraMail.setHours(0);
			ahoraMail.setMinutes(0);
			ahoraMail.setSeconds(0);
			
			
			//MAIL
			cal2.add(Calendar.DAY_OF_YEAR,1);
			java.util.Date proximoDia = cal2.getTime();
			
//			System.out.println("cal "+ sdfDia.format(cal.getTime()) +"------"+ sdf.format(ahora)+" - ahora "+ sdfDia.format(ahoraMail) + "ahora mail");
//			System.out.println("cal2 "+ sdfDia.format(cal2.getTime()) +"------"+ sdf.format(ahora)+" - ahora "+ sdfDia.format(ahoraMail) + "ahora mail");

			
			if (this.horario == "" || this.horario == null) {
				this.horario = sdf.format(ahora);
			}
			
			if (this.horarioMail == "" || this.horarioMail == null) {
				this.horarioMail = sdfDia.format(ahoraMail);;
			}
			//-------------------------------------------
	
			
			if (sdf.format(ahora).equals(horario)) {
				//ejecución de tarea para cuando se cumple el tiempo establecido
				System.out.println(sdf.format(ahora)+" - Demonio de pagos - Proximo: "+ sdf.format(proximo));
				
				//Procesamiento de archivos de pagos
				try {
					procesarArchivoPagos(".//IN//TCPagos.dat");
					procesarArchivoPagos(".//IN//TDPagos.dat");
					procesarArchivoPagos(".//IN//EPagos.dat");
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Throwable e) {
					e.printStackTrace();
				}
				horario=sdf.format(proximo);
			}
			
			if (sdfDia.format(ahoraMail).equals(horarioMail)) {
				//ejecución de tarea para cuando se cumple el tiempo establecido - cada dia
				//Controlar para cambiar el estado de la lista
				System.out.println(sdf.format(ahoraMail)+" - Demonio de MAIL - Proximo: "+ sdfDia.format(proximoDia));
				
				//Envio automatico de emails
				try {
					modificarEstadoLista(ahoraMail);
					envioEmailAgasajado(ahoraMail);
					envioEmailInicioLista(ahoraMail);
					envioEmailProximoAVencer(ahoraMail);
					
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Throwable e) {
					e.printStackTrace();
				}
	
				horarioMail=sdfDia.format(proximoDia);
			}
		}
        
    }

	private void procesarArchivoPagos(String ruta) throws Throwable {
		// TODO Auto-generated method stub
		FileReader fReader;
		String[] paths= ruta.split("//");
		DateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ROOT);
		Calendar cal = Calendar.getInstance();
		java.util.Date now = cal.getTime();
		String newfile= ".\\OUT\\"+sdf.format(now)+" "+ paths[2];

		try {
			fReader = new FileReader(ruta);
		}catch(Exception e) {
			return;
		}
		
		BufferedReader br = new BufferedReader(fReader);
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
		        	System.out.println("Error en Notificación de pago --> Lista: "+datos[0]+" fecha: "+ datos[1] + " usuario: "+ datos[2] +" monto: "+datos[3]);
		        	
		        	try(FileWriter fw = new FileWriter(".\\LOGERROR\\LOG.txt", true);
		        		    BufferedWriter bw = new BufferedWriter(fw);
		        		    PrintWriter out = new PrintWriter(bw))
	        									{
								        		    out.println(newfile+"-->"+line);
	        									} catch (IOException e) {
	        										//exception handling left as an exercise for the reader
	        										System.out.println("Error al querer escribir archivo de errores");
	        									}
		        	
		        }else {
		        	System.out.println("Notificación de pago --> Lista: "+datos[0]+" fecha: "+ datos[1] + " usuario: "+ datos[2] +" monto: "+datos[3]);
		        }
		        	
		        sb.delete(0, sb.length());
		        line = br.readLine();
		    }

		} finally {
		    br.close();
		}		
		
		File filepago = new File(ruta);
		
		filepago.renameTo(new File(newfile));
		filepago.delete();
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
		
		//Esto deberia devolver un True o False para determinar si pudo o no insertar el pago.
		return ControladorPagos.GetInstance().NotificarPago(idL, f, idU, m);
		
	}
	
	private void envioEmailAgasajado(java.util.Date ahoraMail) throws Exception {
		ControladorListaRegalos.GetInstance().EnviarEmailAgasajado(ahoraMail);
	}
	
	private void envioEmailInicioLista(java.util.Date ahoraMail) throws Exception {
		ControladorListaRegalos.GetInstance().EnviarEmailInicioLista(ahoraMail);
	}
	
	private void envioEmailProximoAVencer(java.util.Date ahoraMail) throws Exception {
		int cantDias =SistemaRegalos.GetInstance().diasProximoAVencer;
	     Calendar calendar = Calendar.getInstance();
	     calendar.setTime(ahoraMail); 
      	 calendar.add(Calendar.DAY_OF_YEAR, cantDias);  
	
		ControladorListaRegalos.GetInstance().EnviarEmailProximoAVencer(calendar.getTime());
	}
	
	private void modificarEstadoLista(java.util.Date ahoraMail) throws Exception {
			
		ControladorListaRegalos.GetInstance().ModificarEstado(ahoraMail);
	}
}
