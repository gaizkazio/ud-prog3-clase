package pruebas;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

/** Ejemplo de clases internas y internas anónimas con apunte/curiosidad
 * sobre la sintaxis de this en el new del objeto interno.
 * @author Andoni Eguíluz Morán
 * Facultad de Ingeniería - Universidad de Deusto
 */
@SuppressWarnings("serial")
public class EjemploSintaxisClasesInternas extends JFrame {
	public EjemploSintaxisClasesInternas() {
		setSize( 600, 400 );
		// En cualquier clase en la que nos interese crear un objeto dependiente,
		// (como es el caso habitual de un escuchador de un componente Swing)
		// podemos hacerlo con una clase interna, que va a tener acceso a todos sus
		// atributos y donde el objeto interno va a estar siempre ligado al externo.
		// Esto se puede hacer de dos maneras:
		// 1.- Con clase interna 
		addWindowListener( new MiWindowAdapter() );   // Observa la declaración abajo de MiWindowAdapter
		// 2.- Con clase interna anónima
		addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		//
		// Curiosidad de sintaxis
		// Se puede explicitar que el objeto "interno" se crea referenciado al
		// objeto "externo" actual (this) en una clase interna:
		addWindowListener( this.new MiWindowAdapter() );
		// Pero no se puede explicitar igual en una clase interna anónima:
		// addWindowListener( this.new WindowAdapter() { ...    // ERROR DE COMPILACION
	}
	class MiWindowAdapter extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {
			EjemploSintaxisClasesInternas.this.dispose();
		}
	}
	
	public static void main(String[] args) {
		new EjemploSintaxisClasesInternas().setVisible( true );
	}

}
