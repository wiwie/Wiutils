/**
 * 
 */
package utils;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cern.colt.matrix.tdouble.impl.SparseDoubleMatrix2D;

/**
 * @author Christian Wiwie
 * 
 */
public class TestMySparseDoubleMatrix2D {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	//@Test
	// baseline = 25,4mb RAM
	// 1000x1000 0-array = 8,2mb RAM (expected 7,6mb)
	// 1000x1000 1-array = 8,2mb RAM
	// 1000x1000 empty sparse 0-matrix 112mb
	// 1000x1000 sparse 1-matrix 120mb
	// 1000x1000 sparse 1/2 1-matrix 70mb
	// 1000x1000 sparse 1/4 1-matrix 25mb
	// 1000x1000 sparse 1/8 1-matrix 16mb
	// 1000x1000 sparse 1/16 1-matrix 12mb
	// 1000x1000 sparse 1/32 1-matrix 10mb
	// 2000x2000 sparse 1-matrix = 353mb RAM
	// 3000x3000 sparse 1-matrix = 823mb RAM
	// 4000x4000 1-array = 126mb RAM
	// 4000x4000 sparse 1-matrix = 1413mb RAM
	// 4000x4000 sparse 1/2 1-matrix = 823mb RAM
	// 4000x4000 sparse 1/4 1-matrix = 353mb RAM
	// 4000x4000 sparse 1/8 1-matrix = 235mb RAM
	// 4000x4000 sparse 1/16 1-matrix = 123mb RAM -> ab 1/16 lohnt es sich
	// 10000x10000 0-array = 763mb RAM (expected 763mb)
	// 10000x10000 1-array = 763mb RAM (expected 763mb)
	// 10000x10000 empty sparse 0-matrix = 27mb RAM
	// 10000x10000 sparse 1-matrix = > 1,6gb
	// 10000x10000 sparse 1/2 1-matrix = > 1,6gb
	// 10000x10000 sparse 1/4 1-matrix = > 1413mb
	// 10000x10000 sparse 1/8 1-matrix = > 863mb
	// 10000x10000 sparse 1/16 1-matrix = > 394mb
	// 10000x10000 sparse 1/32 1-matrix = > 276mb
	// 12000x12000 1-array = 1099mb RAM
	public void test() throws InterruptedException {
		// Getting the runtime reference from system
		int mb = 1024 * 1024;
		Runtime runtime = Runtime.getRuntime();
		System.out.println("Used Memory:"
				+ (runtime.totalMemory() - runtime.freeMemory()) / mb);

//		double[][] matrix = new double[12000][12000];
//		for (int i = 0; i < matrix.length; i++)
//			for (int j = 0; j < matrix[i].length; j++)
//				matrix[i][j]++;
		SparseDoubleMatrix2D sparse = new SparseDoubleMatrix2D(10000, 10000);
		for (int i = 0; i < sparse.rows(); i++)
			for (int j = 0; j < sparse.columns()/2; j++)
				sparse.set(i,j,1);
//		matrix = null;
//		System.gc();

		// Print used memory
		System.out.println("Used Memory:"
				+ (runtime.totalMemory() - runtime.freeMemory()) / mb);
	}

}
