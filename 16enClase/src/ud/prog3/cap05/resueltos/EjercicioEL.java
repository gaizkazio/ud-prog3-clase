package ud.prog3.cap05.resueltos;

import java.util.*;

public class EjercicioEL {
	public static void main(String[] args) {
		String [] nombres = { "Luis", "María", "Elena", "Andoni", "Isabel", "Asier", "Andoni", "Luis", "Carlos", "Elena", "Luis", "Aitziber", "Luis" };
		LinkedList<String> llNombres = new LinkedList<String>(); 
		for (String nombre : nombres)
			llNombres.add( nombre );
		System.out.println( llNombres );
		ListIterator<String> itLL = llNombres.listIterator( llNombres.size() );
		ArrayList<String> alNombres = new ArrayList<String>();
		while (itLL.hasPrevious()) {
			alNombres.add( itLL.previous() );
		}
		System.out.println( alNombres );
		HashSet<CuentaNombre> hsNombres = new HashSet<>();
		for (String nombre : alNombres)
			hsNombres.add( new CuentaNombre( nombre ) );
		// Si no se define bien el hash + equals no funciona
		System.out.println( hsNombres.toString() );
		Iterator<CuentaNombre> itNombres = hsNombres.iterator();
		while (itNombres.hasNext()) {
			CuentaNombre cont = itNombres.next();
			for (String nombre : alNombres)
				if (nombre.equals(cont.nombre)) cont.contador++; 
		}
		System.out.println( hsNombres.toString() );
		TreeSet<CuentaNombre> tsNombres = new TreeSet<>();
		itNombres = hsNombres.iterator();
		while (itNombres.hasNext()) {
			tsNombres.add( itNombres.next() );
		}
		System.out.println( tsNombres );
		
		// Implementación mejor (n en vez n^2) con map (sin orden)
		HashMap<String, Integer> hmNombres = new HashMap<>();
		for (String nombre : alNombres)
			if (hmNombres.containsKey( nombre )) {
				int cont = hmNombres.get(nombre);
				hmNombres.replace( nombre, cont+1 );
			} else
				hmNombres.put( nombre, 1 );
		System.out.println( hmNombres.toString() );
		
		// Implementación mejor con map (con orden)
		TreeMap<String, CuentaNombre> tmNombres = new TreeMap<>();
		for (String nombre : alNombres)
			if (tmNombres.containsKey( nombre )) {
				tmNombres.get(nombre).inc();
			} else
				tmNombres.put( nombre, (new CuentaNombre(nombre)).inc() );
		System.out.println( tmNombres.toString() );
		ArrayList<CuentaNombre> contadores = new ArrayList<>();
		for (CuentaNombre cn : tmNombres.values()) {
			contadores.add( cn );
		}
		contadores.sort(null);
		System.out.println( contadores );
			
	}
	
	private static class CuentaNombre 
	// para el treeset
	implements Comparable<CuentaNombre>
	{
		String nombre;
		int contador = 0;
		public CuentaNombre( String nombre ) { this.nombre = nombre; }
		public CuentaNombre inc() { contador++; return this; }
		@Override
		public int hashCode() {
			return nombre.hashCode();
		}
		@Override
		public boolean equals(Object obj) {
			if (obj instanceof CuentaNombre) 
				return nombre.equals( ((CuentaNombre)obj).nombre );
			else return false;
		}
		@Override
		public String toString() { return nombre + "(" + contador + ")"; }
		@Override
		// Uso muy particular de esta clase -- se codifica por nombre, pero se ordena por contador-nombre
		public int compareTo(CuentaNombre o) {
			if (o.contador!=contador) return o.contador-contador; // Ordena al revés por contador
			return nombre.compareTo(o.nombre);  // Ordena al derecho por nombre
		}
	}
}
