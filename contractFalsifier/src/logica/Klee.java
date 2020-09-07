package logica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
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
			archivoCompilado = archivoCompilado + ".bc";
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
				System.out.println("Se ha ejecutado correctamente");
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
		Boolean intVisitado = false;
		Long dato;
		String line;
		String aux;
		String datoAux;
		String hexadecimal;
		String nombre = "Nombre base";
		CasoTest casoTest;
		ArrayList<Long> datos = new ArrayList<Long>();
		ArrayList<CasoTest> resultados = new ArrayList<CasoTest>();
		StringBuilder contenidoArchivo = new StringBuilder();
		StringBuilder outputError;
		BufferedReader readerExito;
		BufferedReader readerError; 
		
		File archivoTest = new File("klee-last/test"+ formatearNumero(numeroTest) + ".ktest");
		
			try {
				while(archivoTest.exists()) {
					Process process = Runtime.getRuntime().exec("ktest-tool " + archivoTest);
			      			
					//StringBuilder outputExito = new StringBuilder();				
					contenidoArchivo.append("El test " + numeroTest + " está constituido por:\n");
					readerExito = new BufferedReader(new InputStreamReader(process.getInputStream()));
			
					outputError = new StringBuilder();
					readerError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			
					while ((line = readerExito.readLine()) != null) {
						if (line.contains("name")){
							aux = line.substring(16);
							aux = aux.replaceAll("'", "");
							aux = aux.trim();
							nombre = aux;
							intVisitado = false;
						}  else if (line.contains("hex")) {
							datos.clear();
							aux = line.substring(18);
							aux = aux.trim();
							for(int i = 0; i < aux.length()/4; i++) {
								hexadecimal = aux.substring(i*4, (i+1)*4);
								datoAux = hexadecimalDecimal(hexadecimal);
								dato = Long.parseLong(datoAux);
								datos.add(dato);	
							}
						} else if (line.contains(": int")) {
							aux = line.substring(16);
							dato = Long.parseLong(aux.trim());
							datos.clear();
							datos.add(dato);
						
							contenidoArchivo.append("La variable \"" + nombre + "\" tiene el valor " + dato + "\n");
							casoTest = new CasoTest(nombre,datos);
							resultados.add(casoTest);
							intVisitado = true;
						} else if (line.contains("text") && intVisitado == false) {
							contenidoArchivo.append("La variable \"" + nombre + "\" tiene el valor " + Arrays.toString(datos.toArray()) + "\n");						
							casoTest = new CasoTest(nombre,datos);
							resultados.add(casoTest);
						}
		
						
					}
			
					while ((line = readerError.readLine()) != null) {
						outputError.append(line + "\n");
					}
					
					int exitVal = process.waitFor();
					if (exitVal == 0) {
						System.out.println("Se ha leido correctamente el caso de prueba " + numeroTest);
						System.out.println("Guardando resultado en una archivo externo...");
						
					
					} else {
						System.out.println("Se ha producido un ERROR a la hora de leer el caso de prueba " + numeroTest);
						System.out.println(outputError);
					}
					
					numeroTest++;
					archivoTest = new File("klee-last/test"+ formatearNumero(numeroTest) + ".ktest");
				}
			
			} catch (Exception e) {
				e.printStackTrace();
			
			}
			crearArchivo(contenidoArchivo, "casosTest");
			return resultados;

	}
		
	public void validarArchivo(File archivo) {
		int numeroTest = 1;
		String line;
		String aux;
		String datoAux;
		String[] ktestString;
		StringBuilder contenidoArchivo = new StringBuilder();
		StringBuilder outputError;
		BufferedReader readerExito;
		BufferedReader readerError; 
		Process process;
		ProcessBuilder processBuilder = new ProcessBuilder();
		
		File archivoTest = new File("klee-last/test"+ formatearNumero(numeroTest) + ".ktest");
		System.out.println("Inicio de la validación...");
		System.out.println("");
		
		try {	
			while(archivoTest.exists()) {
				System.out.println("Ejecutando el test " + numeroTest);
				String[] command =
					{
							"bash",
					};
				
				process= Runtime.getRuntime().exec(command);
					
				
			       //new Thread(new SyncPipe(process.getErrorStream(), System.err)).start();
			      // new Thread(new SyncPipe(process.getInputStream(), System.out)).start();
			       
			       PrintWriter stdin = new PrintWriter(process.getOutputStream());
			       
			    	   stdin.println("gcc -L /home/sandra/klee/build/lib/ " + archivo.getName() + " -lkleeRuntest");
			    	   stdin.println("KTEST_FILE=klee-last/" + archivoTest.getName() + " ./a.out");
			    	   stdin.println("echo $?");
			    	   stdin.close();
			    	   
			    	   
			    	  
						readerExito = new BufferedReader(new InputStreamReader(process.getInputStream()));
				
						outputError = new StringBuilder();
						readerError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			    	   

			    	while((line = readerExito.readLine()) != null) {
			    		  	try {
			    		  			line = line.trim();
			    		  			long x = Long.parseLong(line);
			    		  			contenidoArchivo.append("El resultado de la ejecución del test " + numeroTest + " es " + x + " \n");
			    		
			    		   }catch (Exception e) {
							
			    		   }
			    	   }
			/*
			    	   while((line = readerError.readLine()) != null) {
			    		   try {
			    			   line = line.trim();
			    			   long x = Long.parseLong(line);
			    			   contenidoArchivo.append("El resultado de la ejecución del test " + numeroTest + " es " + x);
			    		
			    		   }catch (Exception e) {
							// TODO: handle exception
			    		   }
					*/
			    	   
			    	   numeroTest++;
			    	   archivoTest = new File("klee-last/test"+ formatearNumero(numeroTest) + ".ktest");
				
			       }					       
			       
			       System.out.println("COMPLETADO");
			       System.out.println("Generando fichero....");
					crearArchivo(contenidoArchivo, "ResultadosEjecucion");
			}
			 catch (Exception e) {
				e.printStackTrace();
			
			}
	}

	public static String formatearNumero(int numero) {
		try (Formatter formatear = new Formatter()) {
			String resultado = "" + formatear.format("%06d", numero);
			return resultado;
		}
	}
	
	public static String hexadecimalDecimal(String hexadecimal) {
		String resultado = "";
		String digitos = "0123456789ABCDEF";
		int decimal = 0;
		
		hexadecimal = hexadecimal.toUpperCase();
		for(int i = 0; i < hexadecimal.length(); i++) {
			char c = hexadecimal.charAt(i);
			int aux = digitos.indexOf(c);
			decimal = 16*decimal + aux;
		
		}
		resultado = resultado + decimal;
		return resultado;
	}
	
	public static void crearArchivo(StringBuilder contenido, String nombre) {
		try {
			File archivo = new File("./" + nombre + ".txt");
			if(!archivo.exists()) {
				archivo.createNewFile();
			} else {
				archivo.delete();
				archivo.createNewFile();
			}
			FileWriter fw = new FileWriter(archivo);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write(contenido.toString());
			bw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}

	
