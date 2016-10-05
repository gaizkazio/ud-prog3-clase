package pruebas;

import java.lang.reflect.Method;
import ud.prog3.cap01.EjemploReflectividad;

public class AccesoAMetodoPorReflectividad {

	public static void main(String[] args) {
		Class<?> c = EjemploReflectividad.class;
		System.out.println( "M�todos p�blicos:");
		Method[] mets = c.getMethods();
		for (Method m : mets)
			System.out.println( m );
		System.out.println();
		System.out.println( "M�todos declarados:");
		mets = c.getDeclaredMethods();
		for (Method m : mets)
			System.out.println( m );
		System.out.println();
		System.out.println( "Llamada por reflectividad a un m�todo: " );
		for (Method m : mets)
			if (m.getName().equals("metodo1")) {
				try {
					m.setAccessible( true ); // Hala!!!  Incluso si es privado!!!
					m.invoke( new EjemploReflectividad(), 5 );
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	}
	
}
