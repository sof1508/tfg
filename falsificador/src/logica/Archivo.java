package logica;

import java.io.*;

public class Archivo {
	
	public File leerArchivo(File archivo) {
		
		if(!archivo.exists()) {
			
			System.out.println("ERROR");
			System.out.println("El archivo no existe");
					
		}else {
			System.out.println("SUCCES");
			System.out.println("Archivo insertado correctamente");
			
			
		}
		
		return archivo;
		
	}

	public void guardarArchivo(File archivo) {
		if(archivo.exists()) {
			
		} else {
			
		}

		
	}
	
	
}
