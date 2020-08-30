package logica;

import java.io.File;

public interface IFalsificadorService {
	
	public void accederKlee();
	
	public boolean compilarKlee(String archivo);

	public boolean ejecutarKlee(String archivo);
	
	public void leerTest();
	
	public void leerArchivo(File archivo);
	
	public void guardarArchivo(File archivo);
	
}
