package logica;

import java.io.File;

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
	public void leerTest() {
		// TODO Esbozo de método generado automáticamente
		
	}

	@Override
	public void leerArchivo(File archivoContrato) {
		archivo.leerArchivo(archivoContrato);
		
	}

	@Override
	public void guardarArchivo(File archivoContrato) {
		archivo.guardarArchivo(archivoContrato);
	}


	

	
}
