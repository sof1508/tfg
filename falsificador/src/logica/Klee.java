package logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Formatter;

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
				System.out.println("Compilado correctamente");
				System.out.println(outputExito);
				return true;
				//System.exit(0);
				
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
		

	
	public void ejecutar(String archivo) {
		//klee
		try {
			String archivoCompilado = archivo.substring(0, archivo.length()-2);
			System.out.println(archivoCompilado);
			archivoCompilado = archivoCompilado + ".bc";
			System.out.println(archivoCompilado);
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
		
	
	
	public static void leerTest() {
		
		int numeroTest = 1;
		File archivoTest = new File("klee-last/test"+ formatearNumero(numeroTest) + ".ktest");
		System.out.println(archivoTest.getName());
		
		while(archivoTest.exists()) {
			try {
		
				Process process = Runtime.getRuntime().exec("ktest-tool" + archivoTest);
			      			
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
			
			numeroTest++;
			archivoTest = new File("klee-last/test"+ formatearNumero(numeroTest) + ".ktest");
			System.out.println(archivoTest.getName());

		}
	}

	public static String formatearNumero(int numero) {
		Formatter formatear = new Formatter();
		String resultado = "" + formatear.format("%06d", numero);
		return resultado;
	}
	
	public static void main(String[] args) {
		leerTest();
	}
	
}

	
