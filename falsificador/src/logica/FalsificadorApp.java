package logica;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FalsificadorApp implements IFalsificadorService {
	
	Klee klee = new Klee();
	Archivo archivo = new Archivo();
	
	
	public FalsificadorApp() {
			
	}
	
	@Override
	public void accederKlee() {
		klee.accederKlee();
	}

	@Override
	public boolean compilarKlee(String archivoKlee) {
		return klee.compilar(archivoKlee);
	}

	@Override
	public boolean ejecutarKlee(String archivoKlee) {
		return klee.ejecutar(archivoKlee);		
	}
	
	@Override
	public ArrayList<CasoTest> leerTest() {
		return klee.leerTest();
		
	}
	
	@Override
	public void validarArchivo(File archivo) {
		// TODO Esbozo de método generado automáticamente
		
	}

	@Override
	public StringBuilder leerArchivo(File archivoLeer) throws IOException {
		return archivo.leerArchivo(archivoLeer);
		
	}
	
	@Override
	public void copiarArchivo(File archivoLeer, File contrato) {
		archivo.copiarArchivo(archivoLeer,contrato);
	}



	

	
}
