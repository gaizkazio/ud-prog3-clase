package pruebas;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

@SuppressWarnings("serial")   // Para evitar el warning de serializaci�n (no nos interesa en este caso serializar esta ventana)
public class PruebaVentana extends JFrame {
	
	private JTextArea taDatos = new JTextArea(20,30);
	private JLabel lMensaje = new JLabel(" ");
	
	public PruebaVentana() {
		// Inicializaci�n
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setSize( 640, 480 );
		setResizable( true );
		// Creaci�n de componentes y contenedores
		JButton bAceptar = new JButton("Aceptar");
		JPanel pBotonera = new JPanel();
		JPanel pInformacion = new JPanel();
		// Formato y layouts
		getContentPane().setLayout( new BorderLayout() );
		// Asignaci�n de componentes a contenedores
		getContentPane().add( lMensaje, BorderLayout.NORTH );
		pBotonera.add( bAceptar );
		getContentPane().add( pBotonera, BorderLayout.SOUTH );
		getContentPane().add( new JScrollPane(taDatos), BorderLayout.WEST );   // Observa lo f�cil que es a�adir barras de scroll... 
		getContentPane().add( pInformacion, BorderLayout.CENTER );
		// (1) Ojo a la definici�n de variables locales del constructor...
		// Eventos
		// MouseListener -> Saca mensajes en textarea
		pInformacion.addMouseListener( new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				taDatos.append( "mouseReleased en" + e.getPoint() + "\n" );
				// (2) ... si quisi�ramos utilizarlas en los m�todos del escuchador
				// (Distinto �mbito, distinto hilo)
				// S�lo funciona si son finals, y en ese caso Java hace un truco interno
				// que es hacer una copia de su valor a un atributo oculto del escuchador
			}
			@Override
			public void mousePressed(MouseEvent e) {
				taDatos.append( "mousePressed en" + e.getPoint() + "\n" );
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				taDatos.append( "mouseExited en" + e.getPoint() + "\n" );
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				taDatos.append( "mouseEntered en" + e.getPoint() + "\n" );
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				taDatos.append( "mouseClicked en" + e.getPoint() + "\n" );
			}
		});
		// MouseMotionListener -> Dibuja el rastro en el panel
		pInformacion.addMouseMotionListener( new MouseMotionListener() {
			private Point pAnt = null;
			@Override
			public void mouseMoved(MouseEvent e) {
				Graphics2D g2 = (Graphics2D) pInformacion.getGraphics();
				g2.setColor( Color.blue );
				g2.setStroke( new BasicStroke( 0.5f ) );
				if (pAnt!=null) {
					g2.drawLine( e.getX(), e.getY(), e.getX(), e.getY() );  // Dibuja puntos al moverse
				}
				pAnt = e.getPoint();
			}
			@Override
			public void mouseDragged(MouseEvent e) {
				Graphics2D g2 = (Graphics2D) pInformacion.getGraphics();
				g2.setColor( Color.green );
				g2.setStroke( new BasicStroke( 1.5f ) );
				if (pAnt!=null) {
					g2.drawLine( pAnt.x, pAnt.y, e.getX(), e.getY() );  // Dibuja l�neas al arrastrar
				}
				pAnt = e.getPoint();
			}
		});
		// FocusListener -> Saca info en el label (ejemplo de uso de clase no interna)
		taDatos.addFocusListener( new MiEscuchadorDeFoco( lMensaje ));
		// WindowListener -> Saca en consola informaci�n del flujo de eventos.
		// Observa c�mo cambian los eventos cuando se minimiza la ventana, o cuando se
		// activa otra ventana del SO y esta aplicaci�n pasa a segundo plano
		// Flujo normal:
		// 		windowActivated
		//		windowOpened
		//		windowClosing
		//		windowDeactivated
		//		windowClosed
		// Y por medio si ocurre un cambio de ventana...
		//		Evento ventana windowDeactivated    al salir
		//		Evento ventana windowActivated      al volver
		// Y si la minimizamos...
		//		Evento ventana windowIconified      al minimizar
		//		Evento ventana windowDeactivated
		//		Evento ventana windowDeiconified    al volver a desminimizar
		//		Evento ventana windowActivated
		this.addWindowListener( new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {
				System.out.println( "Evento ventana windowOpened" );
			}
			@Override
			public void windowIconified(WindowEvent e) {
				System.out.println( "Evento ventana windowIconified" );
			}
			@Override
			public void windowDeiconified(WindowEvent e) {
				System.out.println( "Evento ventana windowDeiconified" );
			}
			@Override
			public void windowDeactivated(WindowEvent e) {
				System.out.println( "Evento ventana windowDeactivated" );
			}
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println( "Evento ventana windowClosing" );
			}
			@Override
			public void windowClosed(WindowEvent e) {
				System.out.println( "Evento ventana windowClosed" );
			}
			@Override
			public void windowActivated(WindowEvent e) {
				System.out.println( "Evento ventana windowActivated" );
			}
		});
		// ActionListener -> bot�n, mueve la ventana de forma aleatoria (en las coordenadas 0,0 a 99,99)
		// Observa como el mismo evento se genera con el click de rat�n en el bot�n,
		// y tambi�n pulsando espacio cuando el bot�n tiene el foco.
		bAceptar.addActionListener( new ActionListener() {
			private Random r = new Random();
			@Override
			public void actionPerformed(ActionEvent e) {
				// Si alguna vez hace falta deshacer la ambiguedad
				// de qu� (this) necesitamos, se puede preceder del nombre de la clase
				// (en este caso, la externa)
				PruebaVentana.this.setLocation( r.nextInt(100), r.nextInt(100) );
			}
		});
		// KeyListener -> en textarea, saca informaci�n de lo que ocurre en la consola.
		// Observa c�mo las combinaciones de teclas (Ctrl, Shift, cursores...) a veces no hacen 
		// nada aparente pero s� se detectan en este evento.
		// Es muy t�pico utilizarlo en control de juegos o animaciones, o sistemas en tiempo real
		// Observa que solo funciona cuando el componente tiene el foco!!!!
		taDatos.addKeyListener( new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				System.out.println( "Tecla \"tecleada\": KeyTyped. C�digo " + e.getKeyCode() + " - c�d. extendido " + e.getExtendedKeyCode() );
			}
			@Override
			public void keyReleased(KeyEvent e) {
				System.out.println( "Tecla soltada: KeyReleased. C�digo " + e.getKeyCode() + " - c�d. extendido " + e.getExtendedKeyCode() );
			}
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println( "Tecla pulsada: KeyTyped. C�digo " + e.getKeyCode() + " - c�d. extendido " + e.getExtendedKeyCode() );
			}
		});
		// ComponentListener -> en el panel. Observa c�mo se lanza cuando hay un cambio en el panel, 
		// lo cual ocurre en este caso solo cuando cambiamos el tama�o de la ventana (lo cual hace
		// que swing cambie tambi�n el tama�o del panel)
		pInformacion.addComponentListener( new ComponentListener() {
			@Override
			public void componentShown(ComponentEvent e) {
				taDatos.append( "componentShown en el panel central\n" );
			}
			@Override
			public void componentResized(ComponentEvent e) {
				taDatos.append( "componentResized en el panel central\n" );
			}
			@Override
			public void componentMoved(ComponentEvent e) {
				taDatos.append( "componentMoved en el panel central\n" );
			}
			@Override
			public void componentHidden(ComponentEvent e) {
				taDatos.append( "componentHidden en el panel central\n" );
			}
		});
	}

	public static void main(String[] args) {
		JFrame f = new PruebaVentana();
		f.setVisible(true);
	}

}

// Ejemplo de escuchador con clase no interna
// (Obs�rvese el engorro de tener que recibir la instancia(s) sobre la que hay que trabajar)
// Ventaja: nos vendr�a bien si queremos utilizar el mismo c�digo de escuchador en varios sitios
// diferentes, en distintas clases de fuentes de eventos (si es para la misma ventana se puede hacer
// tambi�n con un solo objeto de clase interna an�nima, pasando ese objeto a varios set...Listener)
class MiEscuchadorDeFoco implements FocusListener {
	private JLabel miLabel; // Sitio donde queremos dar el feedback
	public MiEscuchadorDeFoco( JLabel lSalida ) {
		miLabel = lSalida;
	}
	@Override
	public void focusGained(FocusEvent e) {
		miLabel.setText( "Se recibe el foco!" );
	}
	@Override
	public void focusLost(FocusEvent e) {
		miLabel.setText( "Se pierde el foco!" );
	}
}
