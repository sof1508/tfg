package logica;

import java.util.ArrayList;

public class CasoTest {
	
	String nombre;
	ArrayList<Long> valores;
	
	public CasoTest(String n, ArrayList<Long> v) {
		this.nombre = n;
		this.valores = v;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public ArrayList<Long> getValores() {
		return this.valores;
	}
	
}
