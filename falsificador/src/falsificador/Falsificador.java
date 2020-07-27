package falsificador;

import java.io.File;
import java.util.concurrent.Callable;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(
	name = "falsificar",
	description = "Says hello"
)
public class Falsificador implements Callable<Integer> {
	
	public static void main(String[] args) throws Exception {
		//FalsificadorCommand falsificador = new FalsificadorCommand();
		//new CommandLine(falsificador).parseArgs(args);
		
		int exitCode = new CommandLine(new Falsificador()).execute(args);
        System.exit(exitCode);
	}
	
	@Option(names = {"-a", "--add"}, description = "Añadir fichero c")
	private File archivo;
	
	@Option(names = {"-h", "--help"}, description = "Ayuda")
	private String help;
	
	@Override
	public Integer call() throws Exception {
		System.out.println("Comprobando archivo");
		//Mirar si es un archivo en c y donde esta (mismo directorio que el actual)
//Si lo es
		//LLAMAR KLEE
		Klee klee = new Klee();
		//klee.accederKlee();
		klee.ejecutar(klee.compilar(archivo));
		
//Si no 
		//Mensaje con información
		
		
		return 0;
	}
} 
	


