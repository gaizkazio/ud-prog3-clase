package ud.prog3.cap04;

import java.util.ArrayList;

public class EjemplosRecursivos {

	public static void main(String[] args) {
		// visuNats(0);
		// System.out.println( producto( 5, 12 ) );
		// System.out.println( factorial( 12 ));
		// ArrayList<Integer> lInts = new ArrayList<>();
		// lInts.add( 1 ); lInts.add( 3 ); lInts.add(5);
		// visuListRec( lInts, 0 );
		// System.out.println( fibonacci(45) );
		// System.out.println( "Llamadas = " + contLlams );
		int datos[] = { 1, 4, 7, 8, 11, 12, 14, 20, 21 };
		int posi = busqBinaria( datos, 22 );
		System.out.println( "Encontrado en " + posi );
	}
	
	public static int busqBinaria( int datos[], int buscado ) {
		return busqBinaria( datos, buscado, 0, datos.length-1 );
	}
	
	/** Devuelve la posición de un elemento en un array
	 * entre las posiciones desde, hasta indicadas
	 * -1 si el elemento no existe
	 * @param datos	Array de enteros 
	 * @param buscado	Elemento que se busca
	 * @return	Posición de ese elemento o -1 si no existe
	 */
	public static int busqBinaria( int datos[],
			int buscado, int desde, int hasta ) {
		if (desde==hasta) {  // Caso base
			if (datos[desde]==buscado) return desde;
			else return -1;
		} else {
			int mitad = (desde+hasta)/2;
			if (buscado <= datos[mitad]) {
				return busqBinaria( datos, buscado,
						desde, mitad );
			} else {
				return busqBinaria( datos, buscado,
						mitad+1, hasta );
			}
		}
	}
	
	public static void visuNats( int n ) {
		if (n<=1000) {
			System.out.println( n );
			visuNats( n+1 );
		}  // else caso base - se acaba
	}
	
	public static int producto( int a, int b ) {
		System.out.println( "Entro en " + a + " * " + b );
		// Def. a*b = a + (a * (b-1))
		if (b==0) return 0;  // Caso base
		return a + producto( a, b-1 );  // Caso recursivo
	}
	
	public static int factorial( int n ) {
		// Def. n! = n * (n-1)!
		if (n==0) return 1;
		else return n * factorial( n-1 );
	}

	public static void visuList( ArrayList<Integer> lI ) {
		int indice = 0;
		while (indice < lI.size()) {
			System.out.println( lI.get(indice) );
			indice++;
		}
	}
	
	// Visualiza toda la lista lI desde el elemento indice
	public static void visuListRec( ArrayList<Integer> lI, 
			int indice ) {
		if (indice >= lI.size()) {
			// Caso base
			System.out.println();
		} else {
			// ANTES
			visuListRec( lI, indice+1 ); // REC
			// DESPUES
			System.out.print( lI.get(indice) + " " );
		}
	}
	
	
	/** Devuelve el enésimo número de fibonacci
	 * (1, 1, 2, 3, 5, 8 ...)
	 * @param n	Posición de número de la serie a devolver
	 * 			n >= 1  
	 * @return	Número de fibonacci correspondiente
	 */
	private static long contLlams = 0;
	public static long fibonacci( int n ) {
		contLlams++;
		if (n==1) return 1;
		else if (n==2) return 1;
		return fibonacci(n-1) + fibonacci(n-2);
	}
	
}
