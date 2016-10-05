package ud.prog3.cap01;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
// import static java.lang.Math.*;
import static ud.prog3.cap01.EjemploUtilidadesStringParaJUnit.*;

public class EjemploUtilidadesStringParaJUnitTest {

	private String test1;
	private String test2;
	@Before
	public void setUp() {
		test1 = "Hola\nEsto es un string con tres líneas\ny\tvarios\ttabuladores.";
		test2 = "Tengo 1\ttab";
	}
	//@After
	@Test
	public void quitarTabsYSaltosLineaTest() {
		assertEquals( "Hola#Esto es un string con tres líneas#y|varios|tabuladores.",
				quitarTabsYSaltosLinea( test1 ) );
		assertEquals( "Tengo 1|tab", quitarTabsYSaltosLinea( test2 ));
		assertEquals( null, quitarTabsYSaltosLinea( null ));
		// fail("Not yet implemented"); --> falla
	}

}
