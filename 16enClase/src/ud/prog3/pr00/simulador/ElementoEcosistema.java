package ud.prog3.pr00.simulador;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JPanel;

/** Clase abstracta que modela todo elemento del ecosistema. Tiene título, posición y tamaño
 * @author Andoni Eguíluz Morán
 * Facultad de Ingeniería - Universidad de Deusto
 */
public abstract class ElementoEcosistema {
	protected String titulo;
	protected Point posicion;
	protected Dimension dimension;
	
	protected JPanel miPanel = null;  // Panel del elemento (tendrá posición y dimensión)
	protected JLabel lTitulo = new JLabel("", JLabel.CENTER);  // Label de título del panel (todos los paneles lo tienen)
	
	/** Devuelve el panel visual asociado al elemento del ecosistema
	 * @return	Panel visual del elemento
	 */
	public abstract JPanel getPanel();

	/** Devuelve el título del elemento de ecosistema
	 * @return	Título (nombre)
	 */
	public String getTitulo() {
		return titulo;
	}

	/** Modifica el título (nombre) del elemento
	 * @param titulo nuevo título
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/** Devuelve la posición del elemento de ecosistema
	 * @return	Posición de la esquina superior izquierda
	 */
	public Point getPosicion() {
		return posicion;
	}

	/** Modifica la posición del elemento
	 * @param posicion nueva posición
	 */
	public void setPosicion(Point posicion) {
		this.posicion = posicion;
	}

	/** Devuelve el tamaño del elemento de ecosistema
	 * @return	Dimensión con ancho y alto del elemento
	 */
	public Dimension getDimension() {
		return dimension;
	}

	/** Modifica la dimensión del elemento
	 * @param dimension nuevo tamaño
	 */
	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}
	
}
