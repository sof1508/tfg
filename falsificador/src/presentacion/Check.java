package presentacion;

import java.io.File;
import java.util.concurrent.Callable;

import logica.IFalsificadorService;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(
	name = "validar",
	description = "validar contrato software"
)
public class Check implements Callable<Integer> {
	IFalsificadorService servicio;
	Boolean check;
	
	public static void main(String[] args) throws Exception {
		
		int exitCode = new CommandLine(new Check()).execute(args);
        System.exit(exitCode);
	}
	
	@Option(names = {"-a", "--archivo"}, description = "Añadir archivo C")
	private File archivo;	

	@Override
	public Integer call() throws Exception {
		// LLAMAR LOGICA 
		servicio = MainPresentacion.crearInstancia();
		
		return 0;
	}
	
	
} 
