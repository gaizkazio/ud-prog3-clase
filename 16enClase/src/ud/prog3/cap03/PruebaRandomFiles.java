package ud.prog3.cap03;
import java.io.*;
import java.nio.ByteBuffer;

/** Prueba de ficheros random con longs
 * @author andoni.eguiluz @ ingenieria.deusto.es
 *
 */
public class PruebaRandomFiles {

	public static void main(String[] args) {
		// Crear un fichero binario 1000 impares iniciales
		guardarImpares();
		// Acceder random a cualquiera de esos impares
		leerRandom();
	}
	
	private static void guardarImpares() {
		try {
			FileOutputStream fos = new FileOutputStream( "impares.dat" );
			for (long l=1; l<2000; l=l+2) {
				byte[] bytes = longABytes(l);
				fos.write(bytes);
			}
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		private static byte[] longABytes( long l ) {
			ByteBuffer buffer = ByteBuffer.allocate( Long.BYTES );
			buffer.putLong( l );
			return buffer.array();
		}
	
	private static void leerRandom() {
		try {
			RandomAccessFile raf = new RandomAccessFile( "impares.dat", "r" );
			raf.seek(9 * Long.BYTES);
			long l = raf.readLong();
			System.out.println( "El impar décimo es: " + l);
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
