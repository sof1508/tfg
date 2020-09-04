package presentacion;

import logica.FalsificadorApp;
import logica.IFalsificadorService;

public class MainPresentacion {
	
	public static IFalsificadorService crearInstancia () {
		IFalsificadorService falsificador = new FalsificadorApp();
		return falsificador;
		
	}
	
}
