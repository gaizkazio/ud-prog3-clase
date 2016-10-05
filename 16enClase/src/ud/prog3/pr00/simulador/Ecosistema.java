package ud.prog3.pr00.simulador;

import java.util.ArrayList;

/** Clase de datos de ecosistema, planteada para tener solo una instancia (patrón Singleton).
 * @author Andoni Eguíluz Morán
 * Facultad de Ingeniería - Universidad de Deusto
 */
public class Ecosistema {
	private static Ecosistema ecoSistema = new Ecosistema();
	private ArrayList<ElementoEcosistema> listaElemsEcosistema;
	
	/** Constructor de ecosistema, vacío y privado (solo puede ejecutarse internamente, y solo se ejecuta una vez)
	 */
	private Ecosistema() {
		listaElemsEcosistema = new ArrayList<>();
	}
	/** Recupera el único ecosistema creado
	 * @return	Devuelve el ecosistema
	 */
	public static Ecosistema getMundo() {
		return ecoSistema;
	}
	
	/** Devuelve los elementos del ecosistema
	 * @return	Lista de los elementos existentes
	 */
	public ArrayList<ElementoEcosistema> getElementos() {
		return listaElemsEcosistema;
	}
	
	/** Método de cálculo de distancia entre dos elementos del ecosistema
	 * @param ee1	Elemento 1
	 * @param ee2	Elemento 2
	 * @return	Distancia lineal entre las coordenadas de los elementos
	 */
	public static int distancia( ElementoEcosistema ee1, ElementoEcosistema ee2 ) {
		return (int) (Math.sqrt( Math.pow( ee1.getPosicion().getX() - ee2.getPosicion().getX(), 2) + 
				                 Math.pow( ee1.getPosicion().getY() - ee2.getPosicion().getY(), 2) ) );
	}
	
}
