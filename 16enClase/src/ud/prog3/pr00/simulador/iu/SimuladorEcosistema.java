package ud.prog3.pr00.simulador.iu;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import ud.prog3.pr00.simulador.*;

/** Clase de prueba del ecosistema con ventana.
 * @author Andoni Eguíluz Morán
 * Facultad de Ingeniería - Universidad de Deusto
 */
@SuppressWarnings("serial")
public class SimuladorEcosistema extends JFrame {
	private JPanel pMundo;
	
	private Hilo hilo;
	
	/** Constructor de ventana de gestión de ecosistema 
	 */
	public SimuladorEcosistema() {
		setSize(800,600);
		setTitle( "Simulador ecosistema - Prog III" );
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setLocationRelativeTo( null );
		
		pMundo = new JPanel();
		JPanel pBotonera = new JPanel();
		JToggleButton tbMover = new JToggleButton( "Mover" );
		JToggleButton tbCrear = new JToggleButton( "Crear" );
		JComboBox<String> cbElemento = new JComboBox<>( new String[] { 
			"Abejas", "Flores", "Agua"
		} );
		JButton bVida = new JButton( "Vida" );
		
		pMundo.setLayout( null );
		tbCrear.setSelected( true );
		
		pBotonera.add( tbMover );
		pBotonera.add( tbCrear );
		pBotonera.add( cbElemento );
		pBotonera.add( bVida );
		getContentPane().add( pMundo, BorderLayout.CENTER );
		getContentPane().add( pBotonera, BorderLayout.SOUTH );
		
		tbMover.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tbMover.isSelected()) tbCrear.setSelected( false );
			}
		});
		tbCrear.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tbCrear.isSelected()) tbMover.setSelected( false );
			}
		});
		pMundo.addMouseListener( new MouseListener() {
			Point coordPulsacion = null;
			@Override
			public void mouseReleased(MouseEvent e) {
				Point coordSuelta = e.getPoint();
				if (tbMover.isSelected()) {  // Acción: mover elemento
					// System.out.println( "Mover " + coordPulsacion + " a " + coordSuelta );
					for (ElementoEcosistema ee : Ecosistema.getMundo().getElementos()) {
						if (ee.getPanel().getBounds().contains( coordPulsacion )) {   // Si la pulsación está dentro del panel, se arrastra
							coordSuelta.translate( -coordPulsacion.x, -coordPulsacion.y);
							coordSuelta.translate( ee.getPanel().getLocation().x, ee.getPanel().getLocation().y );
							ee.getPanel().setLocation( coordSuelta );
							ee.setPosicion( coordSuelta );
							break;   // Solo se mueve un panel (el primero que está en las coordenadas)
						}
					}
				} else if (tbCrear.isSelected()) {  // Acción: crear elemento
					// System.out.println( "Crear " + coordPulsacion + " a " + coordSuelta );
					ElementoEcosistema ee = null;
					if (cbElemento.getSelectedItem().equals("Abejas")) {
						ee = new ColoniaAbejas( "Colonia " + (Ecosistema.getMundo().getElementos().size()+1), 
								coordPulsacion.x, coordPulsacion.y, Math.abs(coordSuelta.x-coordPulsacion.x), Math.abs(coordSuelta.y-coordPulsacion.y) );
					} else if (cbElemento.getSelectedItem().equals("Flores")) {
						ee = new PlantacionFlores( "Pradera " + (Ecosistema.getMundo().getElementos().size()+1), 
								coordPulsacion.x, coordPulsacion.y, Math.abs(coordSuelta.x-coordPulsacion.x), Math.abs(coordSuelta.y-coordPulsacion.y) );
					} else if (cbElemento.getSelectedItem().equals("Agua")) {
						ee = new Agua( "Lago " + (Ecosistema.getMundo().getElementos().size()+1), 
								coordPulsacion.x, coordPulsacion.y, Math.abs(coordSuelta.x-coordPulsacion.x), Math.abs(coordSuelta.y-coordPulsacion.y) );
					}
					Ecosistema.getMundo().getElementos().add( ee );
					JPanel pNuevo = ee.getPanel();
					pMundo.add( pNuevo );
					pMundo.revalidate();
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				coordPulsacion = e.getPoint();
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		
		bVida.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (bVida.getText().equals("Vida")) {  // Activar sistema
					bVida.setText( "Parar" );
					hilo = new Hilo();
					hilo.start();
				} else {  // Parar sistema
					bVida.setText( "Vida" );
					if (hilo != null) hilo.parar();
					hilo = null;
				}
			}
		});
		
		addWindowListener( new WindowAdapter() {  // Acabar el hilo cuando se desactiva la ventana
			@Override
			public void windowDeactivated(WindowEvent e) {
				if (hilo!=null) hilo.parar();
			}
		});
	}

	/** Método principal.
	 * @param args	No utilizado
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {
				SimuladorEcosistema se = new SimuladorEcosistema();
				se.setVisible( true  );
			}
		});
	}

	/** Clase interna para hilo de ejecución de la simulación del ecosistema.
	 * Se crea y lanza un hilo cada vez que quiera simularse el ecosistema.
	 * La simulación ocurre en pasos discretos que ocurren una vez por segundo. Cada paso 
	 * simula un día del ecosistema.
	 * @author Andoni Eguíluz Morán
	 * Facultad de Ingeniería - Universidad de Deusto
	 */
	private class Hilo extends Thread {
		private boolean activo = true;
		private long tick = 1000;  // Milisegundos por tick - 1 tick por segundo = 1000 milisegundos
		/** Constructor de nuevo hilo
		 */
		public Hilo() {
		}
		/** Detiene el funcionamiento del hilo al acabar el tick actual
		 */
		public void parar() {
			activo = false;
		}
		@Override
		public void run() {
			while (activo) {
				try {
					Thread.sleep( tick );
				} catch (InterruptedException e) {}
				for (ElementoEcosistema ee : Ecosistema.getMundo().getElementos()) {
					if (ee instanceof Evolucionable) {
						Evolucionable ev = (Evolucionable) ee;
						ev.evoluciona( 1 );
						// ((Evolucionable) ee).evoluciona(1);
					}
				}
			}
		}
	}
	
}
