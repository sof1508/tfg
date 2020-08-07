package presentacion;

import java.io.File;
import java.util.concurrent.Callable;

import logica.IFalsificadorService;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(
	name = "añadir",
	description = "añadir archivo"
)
public class Add implements Callable<Integer> {
	IFalsificadorService servicio;
	
	public static void main(String[] args) throws Exception {
		
		int exitCode = new CommandLine(new Add()).execute(args);
        System.exit(exitCode);
	}
	
	@Option(names = {"-a", "--add"}, description = "Añadir contrato")
	private File nombreArchivo;
	

	@Override
	public Integer call() throws Exception {
		// LLAMAR LOGICA 
		servicio = MainPresentacion.crearInstancia();
		servicio.leerArchivo(nombreArchivo);
		System.out.print("holi");
		return 0;
	}
	
	
} 
	



