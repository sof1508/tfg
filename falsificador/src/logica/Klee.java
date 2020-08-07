package logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Klee {
	
	public void accederKlee() {
		//export PATH=/home/sandra/klee/build/bin:$PATH
		try {
			Process process = Runtime.getRuntime().exec("export PATH=/home/sandra/klee/build/bin:$PATH");
			StringBuilder outputExito = new StringBuilder();
			
			BufferedReader readerExito = new BufferedReader(
					new InputStreamReader(process.getInputStream()));
			
			StringBuilder outputError = new StringBuilder();
			BufferedReader readerError = new BufferedReader(
					new InputStreamReader(process.getErrorStream()));
			
			
	
			String line;
			while ((line = readerExito.readLine()) != null) {
				outputExito.append(line + "\n");
			}
			
			while ((line = readerError.readLine()) != null) {
				outputError.append(line + "\n");
			}

			int exitVal = process.waitFor();
			if (exitVal == 0) {
				System.out.println("Success!");
				System.out.println(outputExito);
				System.exit(0);
			} else {
				System.out.println("Se ha producido un ERROR a la hora de compilar el fichero en KLEE");
				System.out.println(outputError);
				System.exit(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean compilar(String archivo) {

		try {
			Process process = Runtime.getRuntime().exec("clang -I ../../include -emit-llvm -c -g " + archivo);
			      			
			StringBuilder outputExito = new StringBuilder();
			
			BufferedReader readerExito = new BufferedReader(
					new InputStreamReader(process.getInputStream()));
			
			StringBuilder outputError = new StringBuilder();
			BufferedReader readerError = new BufferedReader(
					new InputStreamReader(process.getErrorStream()));
	
			String line;
			while ((line = readerExito.readLine()) != null) {
				outputExito.append(line + "\n");
			}
			
			while ((line = readerError.readLine()) != null) {
				outputError.append(line + "\n");
			}
			
			int exitVal = process.waitFor();
			if (exitVal == 0) {
				System.out.println("Success!");
				System.out.println("Compilado correctado");
				System.out.println(outputExito);
				System.exit(0);
				return true;
			} else {
				
				System.out.println("Se ha producido un ERROR a la hora de compilar el fichero en KLEE");
				System.out.println(outputError);
				System.exit(0);
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return false;
	}
		

	
	public void ejecutar(File archivoCompilado) {
		//klee
		try {
			Process process = Runtime.getRuntime().exec("klee " + archivoCompilado);
			      			
			StringBuilder outputExito = new StringBuilder();
			
			BufferedReader readerExito = new BufferedReader(
					new InputStreamReader(process.getInputStream()));
			
			StringBuilder outputError = new StringBuilder();
			BufferedReader readerError = new BufferedReader(
					new InputStreamReader(process.getErrorStream()));
			
			
	
			String line;
			while ((line = readerExito.readLine()) != null) {
				outputExito.append(line + "\n");
			}
			
			while ((line = readerError.readLine()) != null) {
				outputError.append(line + "\n");
			}
			

			int exitVal = process.waitFor();
			if (exitVal == 0) {
				System.out.println("Success!");
				System.out.println(outputExito);
				System.exit(0);
			} else {
				
				System.out.println("Se ha producido un ERROR a la hora de ejecutar el fichero en KLEE");
				System.out.println(outputError);
				System.exit(0);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		//return archivoCompilado;
	}
		
	
	
	public void leerTest() {

		try {
			Process process = Runtime.getRuntime().exec("");
			      			
			StringBuilder outputExito = new StringBuilder();
			
			BufferedReader readerExito = new BufferedReader(
					new InputStreamReader(process.getInputStream()));
			
			StringBuilder outputError = new StringBuilder();
			BufferedReader readerError = new BufferedReader(
					new InputStreamReader(process.getErrorStream()));
			
			String line;
			while ((line = readerExito.readLine()) != null) {
				outputExito.append(line + "\n");
			}
			
			while ((line = readerError.readLine()) != null) {
				outputError.append(line + "\n");
			}
			

			int exitVal = process.waitFor();
			if (exitVal == 0) {
				System.out.println("Success!");
				System.out.println(outputExito);
				System.exit(0);
			} else {
				
				System.out.println("Se ha producido un ERROR a la hora de compilar el fichero en KLEE");
				System.out.println(outputError);
				System.exit(0);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}

		
	}
	

}

