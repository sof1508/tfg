package presentacion;

import java.util.concurrent.Callable;

import logica.IFalsificadorService;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(
	name = "añadir",
	description = "añadir archivo"
)
public class Run implements Callable<Integer> {
	IFalsificadorService servicio;
	Boolean check;
	
	public static void main(String[] args) throws Exception {
		
		int exitCode = new CommandLine(new Run()).execute(args);
        System.exit(exitCode);
	}
	
	@Option(names = {"-r", "--run"}, description = "Ejecutar") 
	private String archivo;

	@Override
	public Integer call() throws Exception {
		// LLAMAR LOGICA 
		servicio = MainPresentacion.crearInstancia();
		check = servicio.compilarKlee(archivo);
		if(check == true) {
			servicio.ejecutarKlee(archivo);
		}
		
		return 0;
	}
	
	
} 