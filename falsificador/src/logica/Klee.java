package logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Hashtable;

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
	
	public boolean ejecutar(String archivo) {
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
				return true;
				//System.exit(0);
				
			} else {
				
				System.out.println("Se ha producido un ERROR a la hora de ejecutar el fichero en KLEE");
				System.out.println(outputError);
				return false;
				//System.exit(0);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
			
		}
		return false;
	}
			
	public ArrayList<CasoTest> leerTest() {
		
		int numeroTest = 1;
		int dato;
		String line;
		String aux;
		String hexadecimal;
		String nombre = "Nombre base";
		CasoTest casoTest;
		ArrayList<Integer> datos = new ArrayList<Integer>();
		
		File archivoTest = new File("klee-last/test"+ formatearNumero(numeroTest) + ".ktest");
		System.out.println(archivoTest.getName());
		
		while(archivoTest.exists()) {
			try {
		
				Process process = Runtime.getRuntime().exec("ktest-tool" + archivoTest);
			      			
				StringBuilder outputExito = new StringBuilder();
				outputExito.append("El test " + numeroTest + "esta constituido por:\n");
				BufferedReader readerExito = new BufferedReader(
					new InputStreamReader(process.getInputStream()));
			
				StringBuilder outputError = new StringBuilder();
				BufferedReader readerError = new BufferedReader(
					new InputStreamReader(process.getErrorStream()));
			
				while ((line = readerExito.readLine()) != null) {
					if (line.contains("name")){
						aux = line.substring(16);
						aux = aux.replaceAll("'", "");
						aux = aux.trim();
						
						nombre = aux;
						System.out.println(aux);
					}	
						
					if (line.contains("int")) {
						aux = line.substring(16);
						dato = Integer.parseInt(aux.trim());
						System.out.println(dato);
						datos.clear();
						datos.add(dato);
						
						outputExito.append("La variable " + nombre + "tiene el valor " + dato + "\n");
						casoTest = new CasoTest(nombre,datos);
						
					} else if (line.contains("hex")) {
						datos.clear();
						aux = line.substring(18);
						aux = aux.trim();
						
						for(int i = 0; i < aux.length()/4; i++) {
							hexadecimal = aux.substring(i*4, (i+1)*4);
							System.out.println(hexadecimal);
							dato = hexadecimalDecimal(hexadecimal);
							System.out.println(dato);
							datos.add(dato);	
						}
						outputExito.append("La variable " + nombre + "tiene el valor " + Arrays.toString(datos.toArray()));
						casoTest = new CasoTest(nombre,datos);
					}										
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
				
					System.out.println("Se ha producido un ERROR a la hora de leer el fichero Ktest");
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
		return null;
	}

	public static String formatearNumero(int numero) {
		try (Formatter formatear = new Formatter()) {
			String resultado = "" + formatear.format("%06d", numero);
			return resultado;
		}
	}
	
	public static int hexadecimalDecimal(String hexadecimal) {
		String digitos = "0123456789ABCDEF";
		int decimal = 0;
		
		hexadecimal = hexadecimal.toUpperCase();
		for(int i = 0; i < hexadecimal.length(); i++) {
			char c = hexadecimal.charAt(i);
			int aux = digitos.indexOf(c);
			decimal = 16*decimal + aux;
		
		}
		
		return decimal;
	}
	
	public static void main(String[] args) {
	}
	
}

	
