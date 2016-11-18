package ud.prog3.cap04.resueltos;

public class AquilesYLaTortuga {
	
	public static final double VEL_AQUILES = 10;   // metros / sg
	public static final double VEL_TORTUGA = 0.05; // m/sg (0.05m/sg = 1 metro cada 20 segs) 
	
	public static final double INICIO_AQUILES = 0;    // Aquiles empieza en el metro 0
	public static final double INICIO_TORTUGA = 1000; // La tortuga tiene 1 km de ventaja
	
	/** Devuelve la posición de Aquiles en la carrera, dado el tiempo transcurrido
	 * @param t	Tiempo transcurrido de carrera (en sgs)
	 * @return	Posición de Aquiles (en m)
	 */
	public static double dondeEstaAquiles( double t ) {
		return INICIO_AQUILES + VEL_AQUILES * t;
	}

	/** Devuelve la posición de la tortuga en la carrera, dado el tiempo transcurrido
	 * @param t	Tiempo transcurrido de carrera (en sgs)
	 * @return	Posición de la tortuga (en m)
	 */
	public static double dondeEstaLaTortuga( double t ) {
		return INICIO_TORTUGA + VEL_TORTUGA * t;
	}
	
	// Algoritmo recursivo
	// En t1 Aquiles no ha alcanzado a la tortuga y en t2 ya la ha pasado
	public static double cuandoSeEncuentran( double t1, double t2 ) {
		numLlams++; // Auxiliar para contar el número de llamadas
		if (t2-t1<=0.000000001) {
			return t1;
		} else {
			double tMedio = (t1 + t2) / 2;
			double dondeAquiles = dondeEstaAquiles( tMedio );
			double dondeTortuga = dondeEstaLaTortuga( tMedio );
			if (dondeAquiles<dondeTortuga) {  // Aquiles no ha llegado
				return cuandoSeEncuentran( tMedio, t2 );
			} else {  // Aquiles ha pasado a la tortuga
				return cuandoSeEncuentran(t1, tMedio);
			}
		}
	}
	
	private static int numLlams = 0;
	public static void main(String[] args) {
		double t = 50;
		System.out.println( "Ejemplo. Tiempo = " + t + " segundos" );
		System.out.println( " Aquiles está en " + dondeEstaAquiles(t));
		System.out.println( " La tortuga está en " + dondeEstaLaTortuga(t));
		System.out.println( "Solución:" );
		
		try {
			double tSol = cuandoSeEncuentran( 0,  1000000 );
			System.out.println( "Llamadas: " + numLlams );
			System.out.println( "Tiempo de encuentro = " + tSol );
			System.out.println( "  Distancia de encuentro = " + dondeEstaAquiles(tSol));
		} catch (StackOverflowError e) {
			System.out.println( "Stack overflow!! " + numLlams );
		}
		
	}
	
}
	
