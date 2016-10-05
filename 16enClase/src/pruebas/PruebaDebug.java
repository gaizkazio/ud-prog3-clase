package pruebas;

public class PruebaDebug {

	private static void a(int i) {
		System.out.println( i );
	}
	private static void b(String s) {
		System.out.println( s.length() );
		System.out.println( s );
	}
	public static void main(String[] args) {
		a(5);
		b(null);
	}

}
