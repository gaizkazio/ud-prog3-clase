package ud.prog3.pr00.simulador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JPanel;

/** Clase para instanciar elementos de ecosistema de tipo abejas
 * @author Andoni Eguíluz Morán
 * Facultad de Ingeniería - Universidad de Deusto
 */
public class ColoniaAbejas extends ElementoEcosistema implements Evolucionable {
	protected long poblacion;  // Cantidad de abejas (en individuos)
	
	/** Constructor de colmena de abejas. La inicializa con una población = raíz cuadrada de anchura * altura
	 * @param titulo	Título (nombre)
	 * @param x	Coordenada x
	 * @param y	Coordenada y
	 * @param anch	Anchura (en píxels)
	 * @param alt	Altura (en píxels)
	 */
	public ColoniaAbejas( String titulo, int x, int y, int anch, int alt ) {
		this.titulo = titulo;
		poblacion = (long) (Math.sqrt( anch * alt )); 
		posicion = new Point( x, y );
		dimension = new Dimension( anch, alt );
	}
	
	private JLabel lPoblacion = new JLabel("", JLabel.CENTER);   // Label adicional para el panel de abejas
	@Override
	public JPanel getPanel() {
		if (miPanel == null) {
			miPanel = new JPanel();
			miPanel.setLayout( new BorderLayout() );
			miPanel.add( lTitulo, BorderLayout.NORTH );
			miPanel.add( lPoblacion, BorderLayout.CENTER );
			miPanel.add( new JLabel("Abejas", JLabel.CENTER), BorderLayout.SOUTH );
			lPoblacion.setText( ""+poblacion );
			lTitulo.setText( titulo );
			miPanel.setLocation( posicion );
			miPanel.setSize( dimension );
			miPanel.setBackground( Color.white );
		}
		return miPanel;
	}
	
	/** Devuelve la población de abejas en individuos
	 * @return	cantidad de agua
	 */
	public long getPoblacion() {
		return poblacion;
	}

	/** Modifica la población de abejas
	 * @param poblacion	Nuevo número d e abejas
	 */
	public void setPoblacion(long poblacion) {
		this.poblacion = poblacion;
	}

	@Override
	public void evoluciona(int dias) {
		double factorCrecimiento = 1.0;
		int numFlores = 0;
		for (ElementoEcosistema ee : Ecosistema.getMundo().getElementos()) {
			int dist = Ecosistema.distancia( this, ee );
			if (ee instanceof ColoniaAbejas && ee!=this) {  // La cercanía de otra colonia de abejas perjudica
				if (dist < 500) factorCrecimiento = factorCrecimiento * dist / 500;
			} else if (ee instanceof PlantacionFlores) {  // La cercanía de flores beneficia
				if (dist < 500) factorCrecimiento = factorCrecimiento / dist * 500;
				numFlores += ((PlantacionFlores)ee).getCantidad();
			}
		}
		if (numFlores < 50) factorCrecimiento *= 0.1; // Insuficientes flores mata
		poblacion = (long) (poblacion * factorCrecimiento * dias);
		if (poblacion > 5000) poblacion = 5000;
		lPoblacion.setText( "" + poblacion );  // Límite de población
	}

	@Override
	public String toString(){
		return "Abejas: " + titulo + " - " + poblacion + " indivs. - Coord (" + posicion.x + "," + posicion.y + ") - Tamaño (" + dimension.width + "," + dimension.height + ")";
	}

}
