import java.io.*;

public class PruebaFile {

	public static void main(String[] args) {
		System.out.println( "Visualizando ficheros:");
		verFicheros();
	}
	
	private static void verFicheros() {
		File f = new File("d:/data");
		System.out.println( f + " es dir? " + f.isDirectory() );
		for (File f2 : f.listFiles()) {
			System.out.println( f2 );
			if (f2.isDirectory()) {
				for (File f3 : f2.listFiles()) {
					System.out.println( "  " + f3 + " " + f3.length() );
				}
			}
		}
		
	}

}
