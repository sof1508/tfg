package logica;

import java.util.ArrayList;

public class CasoTest {
	
	String nombre;
	ArrayList<Integer> valores;
	
	public CasoTest(String n, ArrayList<Integer> v) {
		this.nombre = n;
		this.valores = v;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public ArrayList<Integer> getValores() {
		return this.valores;
	}
	
}
