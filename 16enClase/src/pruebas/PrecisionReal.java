package pruebas;

/** Errores con double...
 * @author Andoni Eguíluz Morán
 * Facultad de Ingeniería - Universidad de Deusto
 */
public class PrecisionReal {

	public static void main(String[] args) {
		double d1 = 1E16;
		double d2 = 1;
		System.out.println( d1 + " + " + d2 + " = " + (d1+d2) );
		d1 = 3.2;
		d2 = 2.1;
		System.out.println( d1 + " + " + d2 + " = " + (d1+d2) );
	}

}
