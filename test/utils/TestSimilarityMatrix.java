/**
 * 
 */
package utils;

import junitx.framework.Assert;

import org.junit.Test;

import de.wiwie.wiutils.utils.SimilarityMatrix;
import de.wiwie.wiutils.utils.SimilarityMatrix.NUMBER_PRECISION;

/**
 * @author Christian Wiwie
 * 
 */
public class TestSimilarityMatrix {

	@Test
	public void test() throws InterruptedException {
		// Getting the runtime reference from system
		int mb = 1024 * 1024;
		Runtime runtime = Runtime.getRuntime();
		System.out.println("Used Memory:"
				+ (runtime.totalMemory() - runtime.freeMemory()) / mb);

		double[][] matrix = new double[5000][5000];
		// matrix = null;
		// System.gc();

		// Print used memory
		System.out.println("Used Memory:"
				+ (runtime.totalMemory() - runtime.freeMemory()) / mb);

		Double[][] matrix2 = new Double[5000][5000];
		for (int i = 0; i < matrix2.length; i++) {
			for (int j = 0; j < matrix2[i].length; j++)
				matrix2[i][j] = 1.0;
		}

		short bla = (short) 1.0d;

		// Print used memory
		System.out.println("Used Memory:"
				+ (runtime.totalMemory() - runtime.freeMemory()) / mb);
	}

	@Test
	public void testWithShort() throws InterruptedException {
		// Getting the runtime reference from system
		int mb = 1024 * 1024;
		Runtime runtime = Runtime.getRuntime();
		System.out.println("Used Memory:"
				+ (runtime.totalMemory() - runtime.freeMemory()) / mb);

		int x = 30000;
		int y = 30000;

		SimilarityMatrix m;
		try {
			m = new SimilarityMatrix(x, y, NUMBER_PRECISION.DOUBLE);

			// Print used memory
			System.out.println("Double:"
					+ (runtime.totalMemory() - runtime.freeMemory()) / mb);
		} catch (Throwable t) {
			System.out.println("failed");
		}
		m = null;
		System.gc();
		try {
			m = new SimilarityMatrix(x, y, NUMBER_PRECISION.DOUBLE, true);

			// Print used memory
			System.out.println("Double:"
					+ (runtime.totalMemory() - runtime.freeMemory()) / mb);
		} catch (Throwable t) {
			System.out.println("failed");
		}
		m = null;
		System.gc();
		try {
			m = new SimilarityMatrix(x, y, NUMBER_PRECISION.FLOAT);

			// Print used memory
			System.out.println("Float:"
					+ (runtime.totalMemory() - runtime.freeMemory()) / mb);
		} catch (Throwable t) {
			System.out.println("failed");
		}
		m = null;
		System.gc();
		try {
			m = new SimilarityMatrix(x, y, NUMBER_PRECISION.FLOAT, true);

			// Print used memory
			System.out.println("Float symm:"
					+ (runtime.totalMemory() - runtime.freeMemory()) / mb);
		} catch (Throwable t) {
			System.out.println("failed");
		}

		m = null;
		System.gc();
		try {
			m = new SimilarityMatrix(x, y, NUMBER_PRECISION.SHORT);

			// Print used memory
			System.out.println("Short:"
					+ (runtime.totalMemory() - runtime.freeMemory()) / mb);
		} catch (Throwable t) {
			System.out.println("failed");
		}

		m = null;
		System.gc();
		try {
			m = new SimilarityMatrix(x, y, NUMBER_PRECISION.SHORT, true);

			// Print used memory
			System.out.println("Short symm:"
					+ (runtime.totalMemory() - runtime.freeMemory()) / mb);
		} catch (Throwable t) {
			System.out.println("failed");
		}
	}

	@Test
	public void testSymmetric() {
		SimilarityMatrix matrix = new SimilarityMatrix(1000, 1000,
				NUMBER_PRECISION.SHORT, true);

		for (int i = 0; i < 1000; i++)
			for (int j = 0; j < 1000; j++)
				matrix.setSimilarity(i, j, 0.13);

		for (int i = 0; i < 1000; i++)
			for (int j = 0; j < 1000; j++)
				Assert.assertEquals(0.13, matrix.getSimilarity(i, j));
	}

	@Test
	public void testInvertNonSymmetric() {
		SimilarityMatrix matrix = new SimilarityMatrix(5, 5,
				NUMBER_PRECISION.DOUBLE, false);

		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 5; j++) {
				matrix.setSimilarity(i, j, i * 10 + j);
			}

		matrix.invert();

		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 5; j++)
				Assert.assertEquals(44.0 - (i * 10 + j),
						matrix.getSimilarity(i, j));
	}

	@Test
	public void testInvertSymmetric() {
		SimilarityMatrix matrix = new SimilarityMatrix(5, 5,
				NUMBER_PRECISION.DOUBLE, true);

		for (int i = 0; i < 5; i++)
			for (int j = i; j < 5; j++) {
				matrix.setSimilarity(i, j, i * 10 + j);
			}

		matrix.invert();

		for (int i = 0; i < 5; i++)
			for (int j = i; j < 5; j++)
				Assert.assertEquals(44.0 - (i * 10 + j),
						matrix.getSimilarity(i, j));
	}
}
