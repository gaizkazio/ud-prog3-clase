package ud.prog3.pr00.simulador;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JPanel;

/** Clase abstracta que modela todo elemento del ecosistema. Tiene t�tulo, posici�n y tama�o
 * @author Andoni Egu�luz Mor�n
 * Facultad de Ingenier�a - Universidad de Deusto
 */
public abstract class ElementoEcosistema {
	protected String titulo;
	protected Point posicion;
	protected Dimension dimension;
	
	protected JPanel miPanel = null;  // Panel del elemento (tendr� posici�n y dimensi�n)
	protected JLabel lTitulo = new JLabel("", JLabel.CENTER);  // Label de t�tulo del panel (todos los paneles lo tienen)
	
	/** Devuelve el panel visual asociado al elemento del ecosistema
	 * @return	Panel visual del elemento
	 */
	public abstract JPanel getPanel();

	/** Devuelve el t�tulo del elemento de ecosistema
	 * @return	T�tulo (nombre)
	 */
	public String getTitulo() {
		return titulo;
	}

	/** Modifica el t�tulo (nombre) del elemento
	 * @param titulo nuevo t�tulo
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/** Devuelve la posici�n del elemento de ecosistema
	 * @return	Posici�n de la esquina superior izquierda
	 */
	public Point getPosicion() {
		return posicion;
	}

	/** Modifica la posici�n del elemento
	 * @param posicion nueva posici�n
	 */
	public void setPosicion(Point posicion) {
		this.posicion = posicion;
	}

	/** Devuelve el tama�o del elemento de ecosistema
	 * @return	Dimensi�n con ancho y alto del elemento
	 */
	public Dimension getDimension() {
		return dimension;
	}

	/** Modifica la dimensi�n del elemento
	 * @param dimension nuevo tama�o
	 */
	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}
	
}
