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
	
	@Option(names = {"-c", "--contrato"}, description = "Añadir contrato")
	private File contrato;
	
	@Option(names = {"-a", "--archivo"}, description = "Añadir archivo C")
	private File archivo;

	@Override
	public Integer call() throws Exception {
		// LLAMAR LOGICA 
		servicio = MainPresentacion.crearInstancia();
		servicio.copiarArchivo(archivo, contrato);
		System.out.print("Archivos añadidos y nuevo archivo creado correctamente\n");
		return 0;
	}
	
	
} 
	



