import java.util.Date;

import DTO.ListaDeRegalosDTO;
import DTO.UsuarioDTO;
import controlador.ControladorListaRegalos;
import controlador.ControladorUsuario;
import modelo.ListaDeRegalos;
import modelo.Usuario;



public class FacadeMobil {
	public void NuevoPago(int listaDeRegalos, Date fecha, int usuario, float monto) {
		boolean resultado = ControladorListaRegalos.GetInstance().NotificarPago(listaDeRegalos, fecha, usuario, monto);
		if (resultado){
			System.out.println("Nuevo pago Facade ok");
		}
	}
	
	public ListaDeRegalosDTO GetListaRegalos(int l) {
		ListaDeRegalosDTO lista = ControladorListaRegalos.GetInstance().GetListaRegalos(l);
		return lista;
	}
	
	public UsuarioDTO GetUsuario(int u) {
		return ControladorUsuario.GetInstance().GetUsuario(u);
	}
}
