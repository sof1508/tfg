package persistencia;

import java.io.*;

public class Archivo {
	
	public StringBuilder leerArchivo(File archivo) throws IOException {
		FileReader fr = new FileReader(archivo);
		BufferedReader br = new BufferedReader(fr);
		StringBuilder contenido = new StringBuilder();
		String linea;
		
		if(!archivo.exists()) {
			System.out.println("ERROR");
			System.out.println("El archivo no existe");
					
		}else {
			
			while((linea = br.readLine())!=null) {
			contenido.append(linea + "\n");
			}
		}
		
		return contenido;
		
	}

	public void copiarArchivo(File archivo, File contrato) {
		try {	
			String nombreArchivo = archivo.getName();
			nombreArchivo = nombreArchivo.substring(0, nombreArchivo.length()-2);
			File archivoNuevo = new File("./" + nombreArchivo + "Contrato.c");
			
			if(!archivoNuevo.exists()) {
				archivoNuevo.createNewFile();
			} else {
				archivoNuevo.delete();
				archivoNuevo.createNewFile();
			}
			
			FileWriter fw = new FileWriter(archivoNuevo,true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			StringBuilder lecturaArchivo = leerArchivo(archivo);
			bw.write(lecturaArchivo.toString());
			
			StringBuilder lecturaContrato = leerArchivo(contrato);
			bw.write(lecturaContrato.toString());
			
			bw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
