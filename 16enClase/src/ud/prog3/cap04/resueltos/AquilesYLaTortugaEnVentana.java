package ud.prog3.cap04.resueltos;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AquilesYLaTortugaEnVentana {			
	
	public static double VEL_AQUILES = 10;   // metros / sg
	public static double VEL_TORTUGA = 0.5;  // m/sg (0.05m/sg = 1 metro cada 20 segs) 
	public static double INICIO_AQUILES = 0;    // Aquiles empieza en el metro 0
	public static double INICIO_TORTUGA = 1000; // La tortuga tiene 1 km de ventaja
	
	public static final double GRAF_AQUILES_ESCALA = 1.0;
	public static final double GRAF_AQUILES_ANCHO = 346;
	public static final double GRAF_AQUILES_ALTO = 254;
	public static final double GRAF_AQUILES_REFX = 33;
	public static final double GRAF_AQUILES_REFY = 248;
	public static final double GRAF_TORTUGA_ESCALA = 2.0;
	public static final double GRAF_TORTUGA_ANCHO = 71 * GRAF_TORTUGA_ESCALA;
	public static final double GRAF_TORTUGA_ALTO = 41 * GRAF_TORTUGA_ESCALA;
	public static final double GRAF_TORTUGA_REFX = 34 * GRAF_TORTUGA_ESCALA;
	public static final double GRAF_TORTUGA_REFY = 32 * GRAF_TORTUGA_ESCALA;

	
	/** Devuelve la posición de Aquiles en la carrera, dado el tiempo transcurrido
	 * @param t	Tiempo transcurrido de carrera (en sgs)
	 * @return	Posición de Aquiles (en m)
	 */
	public static double dondeEstaAquiles( double t ) {
		return INICIO_AQUILES + VEL_AQUILES * t;
	}

	/** Devuelve la posición de la tortuga en la carrera, dado el tiempo transcurrido
	 * @param t	Tiempo transcurrido de carrera (en sgs)
	 * @return	Posición de la tortuga (en m)
	 */
	public static double dondeEstaLaTortuga( double t ) {
		return INICIO_TORTUGA + VEL_TORTUGA * t;
	}
	
	// Algoritmo recursivo planteado
	public static double cuandoSeEncuentran() {
		return 0;  // TODO ???
	}
	
	public static void main(String[] args) {
		VentanaAquiles v = new VentanaAquiles();
		v.setVisible( true );
		/*
		double t = 100;
		System.out.println( "Ejemplo. Tiempo = " + t + " segundos" );
		System.out.println( " Aquiles está en " + dondeEstaAquiles(t));
		System.out.println( " La tortuga está en " + dondeEstaLaTortuga(t));
		System.out.println( "Solución:" );
		try {
			double tSol = cuandoSeEncuentranRes( 0, 1000000 );
			System.out.println( "Tiempo de encuentro = " + tSol );
			System.out.println( "  Distancia de encuentro = " + dondeEstaAquiles(tSol));
		} catch (StackOverflowError e) {
			
		}
		*/
	}
	
	
	// Algoritmo recursivo resuelto
	
	private static int numLlams = 0;
	// Algoritmo recursivo resuelto
	// Pre: en t1 Aquiles no ha alcanzado a la tortuga
	// Pre: en t2 Aquiles ha pasado a la tortuga
	public static double cuandoSeEncuentranRes( double t1, double t2 ) {
		numLlams++; System.out.println( "    Llamada " + numLlams + " (dif. ts = " + (t2-t1) + ")" );
		System.out.println( "       Tiempos llamada: " + t1 + " - " + t2);
		if (t2-t1<=0.00000000000002)
			return t1;
		else {
			double tInt = (t1+t2)/2;
			double seAcerca = dondeEstaLaTortuga(tInt) - dondeEstaAquiles(tInt);
			if (seAcerca>0) // Aún no llega Aquiles
				return cuandoSeEncuentranRes(tInt,t2);
			else // se ha pasado
				return cuandoSeEncuentranRes(t1, tInt);
		}
	}

	
	@SuppressWarnings("serial")
	public static class VentanaAquiles extends JFrame {
		JTextField tfIniAquiles, tfIniTortuga, tfVelAquiles, tfVelTortuga, tfVistaPantalla;
		JButton bSimular, bStop, bPlay;
		JTextField tfTiempoFinal;
		JLabel lAquiles, lTortuga, lMetrosIni, lMetrosFin;
		JLabelGrafico lAquilesIni, lAquilesFin;
		JLabelGrafico lTortugaIni, lTortugaFin;
		JPanel pCarrera;
		JLabel lMensaje;
		double posAquiles = 0;
		double posTortuga = 0;
		double vistaPantalla = 1800;
		double tiempoInicial = 0;
		double tiempoFinal = 150;
		HiloSimulacion hilo = null;
		public VentanaAquiles() {
			// Inicialización contenedores
			JPanel pSupIzq = new JPanel(); pSupIzq.setLayout( new GridLayout( 4, 1 ) );
			JPanel pSupCent = new JPanel(); pSupCent.setLayout( new BoxLayout( pSupCent, BoxLayout.Y_AXIS ) );
			JPanel pSupDer = new JPanel(); pSupDer.setLayout( new BoxLayout( pSupDer, BoxLayout.Y_AXIS ) );
			JPanel pSup = new JPanel(); pSup.setLayout( new BorderLayout() );
			JPanel pLinea1izq = new JPanel( new FlowLayout(FlowLayout.LEFT) );
			JPanel pLinea2izq = new JPanel( new FlowLayout(FlowLayout.LEFT) );
			JPanel pLinea3izq = new JPanel( new FlowLayout(FlowLayout.LEFT) );
			JPanel pLinea1der = new JPanel( new FlowLayout(FlowLayout.LEFT) );
			JPanel pLinea2der = new JPanel( new FlowLayout(FlowLayout.LEFT) );
			JPanel pLinea3der = new JPanel( new FlowLayout(FlowLayout.LEFT) );
			// Inicialización componentes
				JLabel l1 = new JLabel( "Configuración inicial", JLabel.CENTER );
				l1.setBorder( BorderFactory.createLineBorder( Color.blue, 1 ));
			pSupIzq.add( l1 );
				pLinea1izq.add( new JLabel( "Inicio (m) - Aquiles" ) );
				tfIniAquiles = new JTextField( "" + INICIO_AQUILES ); tfIniAquiles.setColumns( 5 ); 
				posAquiles = INICIO_AQUILES;
				pLinea1izq.add( tfIniAquiles );
				pLinea1izq.add( new JLabel( " Tortuga" ) );
				tfIniTortuga = new JTextField( "" + INICIO_TORTUGA ); tfIniTortuga.setColumns( 5 ); 
				posTortuga = INICIO_TORTUGA;
				pLinea1izq.add( tfIniTortuga );
			pSupIzq.add( pLinea1izq );
				pLinea2izq.add( new JLabel( "Velocidad (m/sg) - Aquiles" ) );
				tfVelAquiles = new JTextField( "" + VEL_AQUILES ); tfVelAquiles.setColumns( 5 ); 
				pLinea2izq.add( tfVelAquiles );
				pLinea2izq.add( new JLabel( " Tortuga" ) );
				tfVelTortuga = new JTextField( "" + VEL_TORTUGA ); tfVelTortuga.setColumns( 5 ); 
				pLinea2izq.add( tfVelTortuga );
			pSupIzq.add( pLinea2izq );
				pLinea3izq.add( new JLabel( "Vista en pantalla (m)" ) );
				tfVistaPantalla = new JTextField( "" + vistaPantalla ); tfVistaPantalla.setColumns( 6 ); 
				pLinea3izq.add( tfVistaPantalla );
			pSupIzq.add( pLinea3izq );
				JLabel l3 = new JLabel( "Simulación" );
				JPanel p1 = new JPanel();
				p1.add(l3);
			pSupCent.add( p1 );
			pSupCent.add( new JLabel(" ") );
				bSimular = new JButton(); bSimular.setIcon( new ImageIcon( AquilesYLaTortuga.class.getResource( "img/Button Play.png" ) ) );
			pSupCent.add( bSimular );
				JLabel l2 = new JLabel( "Aprox. sucesivas", JLabel.CENTER );
				JPanel p2 = new JPanel();
				p2.setBorder( BorderFactory.createLineBorder( Color.blue, 1 ));
				p2.add(l2);
			pSupDer.add( p2 );
				pLinea1der.add( new JLabel( "Tiempo inicio:" ) );
				pLinea1der.add( new JLabel( "  0  " ) );
			pSupDer.add( pLinea1der );
				pLinea2der.add( new JLabel( "Tiempo final:" ) );
				tfTiempoFinal = new JTextField( "" + tiempoFinal ); tfTiempoFinal.setColumns( 6 ); 
				pLinea2der.add( tfTiempoFinal );
			pSupDer.add( pLinea2der );
				bStop = new JButton(); bStop.setIcon( new ImageIcon( AquilesYLaTortuga.class.getResource( "img/Button Stop.png" ) ) );
				pLinea3der.add( bStop );
				bPlay = new JButton(); bPlay.setIcon( new ImageIcon( AquilesYLaTortuga.class.getResource( "img/Button Play Pause.png" ) ) );
				pLinea3der.add( bPlay );
			pSupDer.add( pLinea3der );
			pSup.add( pSupIzq, BorderLayout.WEST );
			pSup.add( pSupCent, BorderLayout.CENTER );
			pSup.add( pSupDer, BorderLayout.EAST );
			getContentPane().add( pSup, BorderLayout.NORTH );
				pCarrera = new JPanel(); pCarrera.setLayout( null ); pCarrera.setPreferredSize( new Dimension( 800, 400 ) );
				pCarrera.setBackground( Color.white );
				lAquiles = new JLabelGrafico( (int)GRAF_AQUILES_ANCHO, (int)GRAF_AQUILES_ALTO, "img/Aquiles.png", GRAF_AQUILES_REFX, GRAF_AQUILES_REFY );
				lAquilesIni = new JLabelGrafico( (int)GRAF_AQUILES_ANCHO, (int)GRAF_AQUILES_ALTO, "img/Aquiles.png", GRAF_AQUILES_REFX, GRAF_AQUILES_REFY );
				lAquilesFin = new JLabelGrafico( (int)GRAF_AQUILES_ANCHO, (int)GRAF_AQUILES_ALTO, "img/Aquiles.png", GRAF_AQUILES_REFX, GRAF_AQUILES_REFY );
				lAquilesIni.setTransparencia( 0.6f ); lAquilesIni.setVisible( false );
				lAquilesFin.setTransparencia( 0.6f ); lAquilesFin.setVisible( false );
				lTortuga = new JLabelGrafico( (int)GRAF_TORTUGA_ANCHO, (int)GRAF_TORTUGA_ALTO, "img/tortuga.png", GRAF_TORTUGA_REFX, GRAF_TORTUGA_REFY );
				lTortugaIni = new JLabelGrafico( (int)GRAF_TORTUGA_ANCHO, (int)GRAF_TORTUGA_ALTO, "img/tortuga.png", GRAF_TORTUGA_REFX, GRAF_TORTUGA_REFY );
				lTortugaFin = new JLabelGrafico( (int)GRAF_TORTUGA_ANCHO, (int)GRAF_TORTUGA_ALTO, "img/tortuga.png", GRAF_TORTUGA_REFX, GRAF_TORTUGA_REFY );
				lTortugaIni.setTransparencia( 0.6f ); lTortugaIni.setVisible( false );
				lTortugaFin.setTransparencia( 0.6f ); lTortugaFin.setVisible( false );
				pCarrera.add( lTortuga ); pCarrera.add( lTortugaIni ); pCarrera.add( lTortugaFin );
				pCarrera.add( lAquiles ); pCarrera.add( lAquilesIni ); pCarrera.add( lAquilesFin );
				lMetrosIni = new JLabel( "0" );
				lMetrosFin = new JLabel( "" + vistaPantalla ); lMetrosFin.setHorizontalAlignment( SwingConstants.RIGHT );
				lMetrosIni.setFont( new Font( "Arial", Font.PLAIN, 18 ) );
				lMetrosFin.setFont( new Font( "Arial", Font.PLAIN, 18 ) );
				pCarrera.add( lMetrosIni );
				pCarrera.add( lMetrosFin );
			getContentPane().add( pCarrera, BorderLayout.CENTER );
				lMensaje = new JLabel( " " ); lMensaje.setFont( new Font( "Arial", Font.BOLD, 24 )); lMensaje.setHorizontalAlignment( SwingConstants.CENTER );
			getContentPane().add( lMensaje, BorderLayout.SOUTH );
			// Formato general
			setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
			pack();
			// Eventos
			addWindowListener( new WindowAdapter() {
				@Override
				public void windowActivated(WindowEvent e) {
					recolocaCarrera();
				}
			});
			pCarrera.addComponentListener( new ComponentAdapter() {
				@Override
				public void componentResized(ComponentEvent e) {
					recolocaCarrera();
				}
			});
			tfIniAquiles.addFocusListener( new EscFocoVal( tfIniAquiles ) );
			tfVelAquiles.addFocusListener( new EscFocoVal( tfVelAquiles ) );
			tfIniTortuga.addFocusListener( new EscFocoVal( tfIniTortuga ) );
			tfVelTortuga.addFocusListener( new EscFocoVal( tfVelTortuga ) );
			tfVistaPantalla.addFocusListener( new EscFocoVal( tfVistaPantalla ) );
			tfTiempoFinal.addFocusListener( new EscFocoVal( tfTiempoFinal ) );
			bSimular.addActionListener( (e) -> {
				lAquilesIni.setVisible( false ); lAquilesFin.setVisible( false );
				lTortugaIni.setVisible( false ); lTortugaFin.setVisible( false );
				if (hilo==null) {
					hilo = new HiloSimulacion();
					hilo.start();
				} else {
					hilo.parar();
				}
			} );
			bStop.addActionListener( (e) -> {
				lAquilesIni.setVisible( false ); lAquilesFin.setVisible( false );
				lTortugaIni.setVisible( false ); lTortugaFin.setVisible( false );
				tiempoInicial = 0;
				try {
					tiempoFinal = Double.parseDouble( tfTiempoFinal.getText() );
				} catch (NumberFormatException ex) {}
				ponAquiles( dondeEstaAquiles( 0 ) );
				ponTortuga( dondeEstaLaTortuga( 0 ) );
				recolocaCarrera();
			} );
			bPlay.addActionListener( (e) -> {
				lAquilesIni.setVisible( true ); lAquilesFin.setVisible( true );
				lTortugaIni.setVisible( true ); lTortugaFin.setVisible( true );
				muestraAquiles( lAquilesIni, tiempoInicial );
				muestraAquiles( lAquilesFin, tiempoFinal );
				muestraLaTortuga( lTortugaIni, tiempoInicial );
				muestraLaTortuga( lTortugaFin, tiempoFinal );
				double tiempoMedio = (tiempoInicial + tiempoFinal) / 2;
				ponAquiles( dondeEstaAquiles( tiempoMedio ) );
				ponTortuga( dondeEstaLaTortuga( tiempoMedio ) );
				if (posAquiles<posTortuga) {
					tiempoInicial = tiempoMedio;
				} else {
					tiempoFinal = tiempoMedio;
				}
				ponMensaje( String.format( "Tiempo medio %1$,5.3f . Aquiles en %2$,5.3f - tortuga en %3$,5.3f", tiempoMedio, posAquiles, posTortuga ) );
			} );
		}
		private void recolocaCarrera() {
			lMetrosIni.setBounds( 0, pCarrera.getHeight() - 40, 100, 40 );
			lMetrosFin.setBounds( pCarrera.getWidth() - 100, pCarrera.getHeight() - 40, 100, 40 );
			ponAquiles( posAquiles );
			ponTortuga( posTortuga );
		}
		public void ponMensaje( String mensaje ) {
			lMensaje.setText( mensaje );
		}
		public void ponAquiles( double posAquiles ) {
			this.posAquiles = posAquiles;
			lAquiles.setLocation( (int) (posAquiles / vistaPantalla * pCarrera.getWidth() - GRAF_AQUILES_REFX),
					              (int) (pCarrera.getHeight() - 50 - GRAF_AQUILES_REFY) );
		}
		public void muestraAquiles( JLabel labelAquiles, double tAquiles ) {
			double posAquiles = dondeEstaAquiles( tAquiles );
			labelAquiles.setLocation( (int) (posAquiles / vistaPantalla * pCarrera.getWidth() - GRAF_AQUILES_REFX),
					                  (int) (pCarrera.getHeight() - 50 - GRAF_AQUILES_REFY) );
		}
		public void ponTortuga( double posTortuga ) {
			this.posTortuga = posTortuga;
			lTortuga.setLocation( (int) (posTortuga / vistaPantalla * pCarrera.getWidth() - GRAF_TORTUGA_REFX),
		                          (int) (pCarrera.getHeight() - 50 - GRAF_TORTUGA_REFY) );
		}
		public void muestraLaTortuga( JLabel labelTortuga, double tTortuga ) {
			double posTortuga = dondeEstaLaTortuga( tTortuga );
			labelTortuga.setLocation( (int) (posTortuga / vistaPantalla * pCarrera.getWidth() - GRAF_TORTUGA_REFX),
					                  (int) (pCarrera.getHeight() - 50 - GRAF_TORTUGA_REFY) );
		}
		
		private class EscFocoVal implements FocusListener {
			private JTextField miCampo;
			public EscFocoVal( JTextField tf ) {
				miCampo = tf;
			}
			private String valAnt = "";
			@Override
			public void focusGained(FocusEvent e) {
				valAnt = miCampo.getText();
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (!valAnt.equals( miCampo.getText() )) {   // Si ha habido cambio validar valor
					try {
						double d = Double.parseDouble( miCampo.getText() );
						if (d<0) throw new NumberFormatException();
						if (miCampo==tfVistaPantalla) {
							vistaPantalla = d;
							lMetrosFin.setText( tfVistaPantalla.getText() );
							recolocaCarrera();
						} else if (miCampo==tfIniAquiles) {
							INICIO_AQUILES = d;
							ponAquiles( dondeEstaAquiles( d ) );
							recolocaCarrera();
						} else if (miCampo==tfIniTortuga) {
							INICIO_TORTUGA = d;
							ponTortuga( dondeEstaLaTortuga( d ) );
							recolocaCarrera();
						} else if (miCampo==tfVelAquiles) {
							VEL_AQUILES = d;
						} else if (miCampo==tfVelTortuga) {
							VEL_TORTUGA = d;
						} else if (miCampo==tfTiempoFinal) {
							tiempoFinal = d;
						}
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog( VentanaAquiles.this, "El valor debe ser un real válido y positivo", "Error en valor", JOptionPane.ERROR_MESSAGE );
						miCampo.requestFocus();
					}
				}
			}
		}
		
		private class HiloSimulacion extends Thread {
			boolean sigue = true;
			@Override
			public void run() {
				JComponent[] comps = { tfIniAquiles, tfIniTortuga, tfVelAquiles, tfVelTortuga, tfVistaPantalla, tfTiempoFinal, bStop, bPlay };
				bSimular.setIcon( new ImageIcon( AquilesYLaTortuga.class.getResource( "img/Button Stop.png" ) ) );
				for (JComponent comp : comps) comp.setEnabled( false );
				double tiempo = 0;
				posAquiles = 0;
				while (sigue && posAquiles<posTortuga && posAquiles<vistaPantalla && posTortuga < vistaPantalla) {
					ponAquiles( dondeEstaAquiles( tiempo ));
					ponTortuga( dondeEstaLaTortuga( tiempo ));
					tiempo = tiempo + 0.01;
					ponMensaje( String.format( "Tiempo %1$,5.3f . Aquiles en %2$,5.3f - tortuga en %3$,5.3f", tiempo, posAquiles, posTortuga ) );
					try {Thread.sleep( 1 ); } catch (Exception e) {}
				}
				for (JComponent comp : comps) comp.setEnabled( true );
				hilo = null;  // Borrar el hilo al acabar
				bSimular.setIcon( new ImageIcon( AquilesYLaTortuga.class.getResource( "img/Button Play.png" ) ) );
			}
			public void parar() {
				sigue = false;
			}
		}
		
	}
	
	@SuppressWarnings("serial")
	public static class JLabelGrafico extends JLabel {
		double refx = -1, refy = -1;   // Punto de referencia  (negativo si no existe)
		int imagenAncho = -1, imagenAlto = -1;  // Tamaño original imagen
		float opacidad = -1.0f;  // Opacidad (negativo = no se considera. En caso contrario en rango 0-1)
		
		/** Construye y devuelve el JLabel del gráfico con el tamaño y nombre de fichero dado
		 * @param ancho	Ancho en pixels
		 * @param alto	Alto en pixels
		 * @param ficGrafico	Nombre de recurso partiendo de esta clase
		 */
		public JLabelGrafico( int ancho, int alto, String ficGrafico ) {
			try {
				setIcon( new ImageIcon( JLabelGrafico.class.getResource( ficGrafico) ) );
			} catch (Exception e) {
				System.err.println( "Error en carga de recurso: " + ficGrafico + " no encontrado" );
				e.printStackTrace();
			}
			setBounds( 0, 0, ancho, alto );
		}
		
		/** Construye y devuelve el JLabel del gráfico con el tamaño y nombre de fichero dado
		 * y con punto de referencia que se marcará en el dibujado
		 * @param ancho	Ancho en pixels
		 * @param alto	Alto en pixels
		 * @param ficGrafico	Nombre de recurso partiendo de esta clase
		 */
		public JLabelGrafico( int ancho, int alto, String ficGrafico, double refx, double refy ) {
			try {
				setIcon( new ImageIcon( JLabelGrafico.class.getResource( ficGrafico) ) );
				imagenAncho = ancho; imagenAlto = alto;
				this.refx = refx; this.refy = refy;
			} catch (Exception e) {
				System.err.println( "Error en carga de recurso: " + ficGrafico + " no encontrado" );
				e.printStackTrace();
			}
			setBounds( 0, 0, ancho, alto );
		}
		
		// giro
		private double miGiro = 0; 
		/** Cambia el giro del gráfico de JLabel
		 * @param gradosGiro	Grados a los que tiene que "apuntar" el coche,
		 * 						considerados con el 0 en el eje OX positivo,
		 * 						positivo en sentido antihorario, negativo horario.
		 */
		public void setGiro( double gradosGiro ) {
			miGiro = gradosGiro/180*Math.PI;   // De grados a radianes...
			miGiro = -miGiro;  // Cambio el sentido del giro (el giro en la pantalla es en sentido horario (inverso))
			miGiro = miGiro + Math.PI/2; // Sumo 90º para que corresponda al origen OX (el gráfico del coche apunta hacia arriba (en lugar de derecha OX))
		}

		/** Cambia la opacidad de la imagen
		 * @param opacity	Valor de opacidad en el rango: 0.0f = transparente, 1.0f = opaco
		 */
		public void setTransparencia( float opacidad ) {
			this.opacidad = opacidad;
		}
		
		// Redefinición del paintComponent para que se escale y se rote el gráfico
		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;  // El Graphics realmente es Graphics2D
			Image img = ((ImageIcon)getIcon()).getImage();
			// Escalado más fino con estos 3 parámetros:
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);	
	        g2.rotate( miGiro, getWidth()/2, getHeight()/2 );  // Prepara rotación
	        Composite old = g2.getComposite();
	        if (opacidad>=0.0f) {
		        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacidad));
	        }
	        g2.drawImage( img, 0, 0, getWidth(), getHeight(), null );  // Dibujado de la imagen
	        g2.setComposite(old);	        
	        if (refx>=0 && refy>=0) {  // Si hay punto de referencia lo dibuja
	        	double centrox = refx/imagenAncho*getWidth();
	        	double centroy = refy/imagenAlto*getHeight();
	        	g2.setColor( Color.green );
	        	g2.setStroke( new BasicStroke( 1.5f ) );
	        	g2.fillOval( (int)(centrox-4), (int)(centroy-4), 8, 8 );
	        	g2.drawOval( (int)(centrox-6), (int)(centroy-6), 12, 12 );
	        }
		}
	}
	
}
	
