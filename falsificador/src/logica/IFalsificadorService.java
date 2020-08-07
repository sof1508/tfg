package logica;

import java.io.File;

public interface IFalsificadorService {
	
	public void accederKlee();
	
	public boolean compilarKlee(String archivo);

	public void ejecutarKlee(String archivo);
	
	public void leerArchivo(File archivo);
	
	public void guardarArchivo(File archivo);
	
}
