package ud.prog3.cap04;

import static org.junit.Assert.*;
import org.junit.Test;
import static ud.prog3.cap04.MergeSortResuelto.*;

import java.util.Random;

public class MergeSortResueltoTest {
	
	@Test
	public void testMergeSort100Aleats() { // Test de orden de 100 n�meros aleatorios
		Random r = new Random();
		int cantidad = 100;
		int[] nums = new int[100];
		for (int i=0; i<cantidad; i++) nums[i] = r.nextInt( 200 );  // Entero entre 0 y 200
		mergeSort( nums );
		// Aseguramos que est� ordenada:
		int anterior = nums[0];
		for (int i=1; i<cantidad; i++) {
			assertTrue( nums[i] >= anterior );
			anterior = nums[i];
		}
	}
	
	@Test
	public void testMergeSortBasicos() { // Test b�sicos de mergesort
		// Deja vac�o un array vac�o
		int[] nums = {};
		mergeSort( nums );
		assertEquals( 0, nums.length );
		// Deja igual un array con un elemento
		nums = new int[] { 5 };
		mergeSort( nums );
		assertEquals( 5, nums[0] );
		assertEquals( 1, nums.length );
		// Orden de peque�os arrays: inverso
		nums = new int[] { 7, 5, 3, 1 };
		mergeSort( nums );
		assertArrayEquals( new int[] {1, 3, 5, 7}, nums );
		// Orden de peque�os arrays: ya ordenado
		nums = new int[] { 1, 3, 5, 7 };
		mergeSort( nums );
		assertArrayEquals( new int[] {1, 3, 5, 7}, nums );
		// Orden de peque�os arrays: ca�tico
		nums = new int[] { 7, 3, 1, 5 };
		mergeSort( nums );
		assertArrayEquals( new int[] {1, 3, 5, 7}, nums );
		// Orden de peque�os arrays: con repetidos
		nums = new int[] { 7, 3, 1, 5, 3, 3, 5, 7 };
		mergeSort( nums );
		assertArrayEquals( new int[] {1, 3, 3, 3, 5, 5, 7, 7}, nums );
	}
	
	@Test(expected = NullPointerException.class) 
    public void testMergeSortNull() {  // Test de excepci�n array nulo
		mergeSort( null );
	}

}
