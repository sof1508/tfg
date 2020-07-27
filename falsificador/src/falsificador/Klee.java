package falsificador;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Klee {
	
	public void accederKlee() {
		//export PATH=/home/sandra/klee/build/bin:$PATH
		try {
			Process process = Runtime.getRuntime().exec("ls");
			StringBuilder output = new StringBuilder();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}

			int exitVal = process.waitFor();
			if (exitVal == 0) {
				System.out.println("Success!");
				System.out.println(output);
				System.exit(0);
			} else {
				//abnormal...
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public File compilar(File archivo) {
		//clang -I ../../include -emit-llvm -c -g" + archivo 
		try {
			Process process = Runtime.getRuntime().exec("clang -I ../../include -emit-llvm -c -g" + archivo);
			StringBuilder output = new StringBuilder();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}

			int exitVal = process.waitFor();
			if (exitVal == 0) {
				System.out.println("Success!");
				System.out.println(output);
				System.exit(0);
			} else {
				//abnormal...
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return archivo;
	}
		

	
	public void ejecutar(File archivoCompilado) {
		//klee"
	}
	
	public void leerTest() {}
	
	public static void main(String[] args) throws Exception {
		Klee klee = new Klee();
		klee.accederKlee();
	}

}
