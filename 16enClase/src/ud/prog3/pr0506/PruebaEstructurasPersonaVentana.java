package ud.prog3.pr0506;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

import ud.prog3.pr0506.Utils;
import ud.prog3.pr0506.gui.VentanaBancoDePruebas;

/** Prueba combinada de distintas estructuras de datos y distintos tama�os
 * @author Andoni Egu�luz Mor�n
 * Facultad de Ingenier�a - Universidad de Deusto
 */
public class PruebaEstructurasPersonaVentana {

	public static void main(String[] args) {
		Utils.muestraThreadsActivos();
		String[] pruebas = { "ArrayList", "LinkedList", "HashSet", "TreeSet" };
		ArrayList<ProcesoProbable> procs = new ArrayList<ProcesoProbable>();
		procs.add( new AccesoAPersonasAL() );
		procs.add( new AccesoAPersonasLL() );
		procs.add( new AccesoAPersonasHS() );
		procs.add( new AccesoAPersonasTS() );
		VentanaBancoDePruebas vent = new VentanaBancoDePruebas();
		vent.setProcesos( pruebas, procs );
		vent.setVisible( true );
		Utils.muestraThreadsActivos();
	}

}
