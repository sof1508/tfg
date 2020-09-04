package logica;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface IFalsificadorService {
	
	public void accederKlee();
	
	public boolean compilarKlee(String archivo);

	public boolean ejecutarKlee(String archivo);
	
	public ArrayList<CasoTest> leerTest();
	
	public void validarArchivo(File archivo);
	
	public StringBuilder leerArchivo(File archivo) throws IOException;
	
	public void copiarArchivo(File archivo, File contrato);
	
}
