package ud.prog3.pr0506;

import java.util.LinkedList;

public class AccesoAPersonasLL implements ProcesoProbable {

	private LinkedList<Persona> l;
	
	@Override
	public void init(int tamanyoTest) {
		l = new LinkedList<>();
		for (int i=0; i<tamanyoTest; i++) {
			l.add( new Persona( i*2+1, "Nombre " + i, "Apellido " + i ));
		}
	}

	public int cont;  // Se hace el contador atributo para que la actualizaci�n del contador del test no pueda ser optimizada (y eliminada) por el compilador
	@Override
	public Object test() {
		cont = 0;
		for (int i=0; i<l.size(); i++) {
			if (l.contains( new Persona(i,"","") )) cont++;
		}
		// System.out.println( "N�mero personas encontradas: " + cont );
		return l;
	}

	/** M�todo de prueba de la clase
	 * @param args
	 */
	public static void main(String[] args) {
		AccesoAPersonasLL proc = new AccesoAPersonasLL();
		long tiempo = BancoDePruebas.realizaTest( proc, 50000 );
		int espacio = BancoDePruebas.getTamanyoTest();
		System.out.println( "Prueba ArrayList de 50000 -- tiempo: " + tiempo + " msgs. / espacio = " + espacio + " bytes.");
	}

}
