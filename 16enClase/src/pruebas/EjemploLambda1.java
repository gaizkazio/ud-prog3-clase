package pruebas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class EjemploLambda1 {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(
			
			// Java 7
//			new Runnable() {
//				@Override
//				public void run() {
//					System.out.println( 5 );
//				}
//			}
				
			// Java 8
			() -> { System.out.println( 5 );}
			
		);
		
		JButton b = new JButton("");
		b.addActionListener( 
//				new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//			}
//				}
			(e) -> {}
		);
	}

}
