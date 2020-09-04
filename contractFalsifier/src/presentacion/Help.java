package presentacion;

import java.util.concurrent.Callable;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(
	name = "ayuda",
	description = "si necesita alguna ayuda para ejecutar esta herraienta"
)
public class Help implements Callable<String> {
	
	public static void main(String[] args) throws Exception {
		
		int exitCode = new CommandLine(new Help()).execute(args);
        System.exit(exitCode);
	}
	
	@Option(names = {"-h", "--help"}, description = "Ayuda")
	

	@Override
	public String call() throws Exception {
		// LLAMAR LOGICA 
		return "hola ayuda";
	}
	
	
} 