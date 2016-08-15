/**
 * 
 */
package utils.parse;

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.wiwie.wiutils.utils.SimilarityMatrix;
import de.wiwie.wiutils.utils.parse.SimFileMatrixParser;
import de.wiwie.wiutils.utils.parse.SimFileParser.SIM_FILE_FORMAT;
import de.wiwie.wiutils.utils.parse.TextFileParser.OUTPUT_MODE;

/**
 * @author Christian Wiwie
 * 
 */
public class TestSimFileMatrixParser {

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

	// TODO: sparsity check of SimilarityMatrix does not work for now
	//@Test
	public void test() throws IOException {
		SimFileMatrixParser p = new SimFileMatrixParser(new File(
				"testData/testRowSimZeroToSimMatrix.txt").getAbsolutePath(),
				SIM_FILE_FORMAT.ID_ID_SIM, null, null,
				new File("testData/testRowSimZeroToSimMatrix.txt.out")
						.getAbsolutePath(), OUTPUT_MODE.BURST,
				SIM_FILE_FORMAT.MATRIX_HEADER);
		p.process();

		p = new SimFileMatrixParser(
				new File("testData/testRowSimZeroToSimMatrix.txt.out")
						.getAbsolutePath(),
				SIM_FILE_FORMAT.MATRIX_HEADER, null, null, null, null, null);
		p.process();

		SimilarityMatrix matrix = p.getSimilarities();
		Assert.assertEquals(0.0, matrix.getSimilarity("id1", "id3"));
		Assert.assertFalse(matrix.isSparse(0, 1));
		Assert.assertFalse(matrix.isSparse(0, 2));
		Assert.assertTrue(matrix.isSparse(0, 0));
	}

	@Test
	public void testWithComments() throws IOException {
		SimFileMatrixParser p = new SimFileMatrixParser(new File(
				"testData/testWithComments.txt").getAbsolutePath(),
				SIM_FILE_FORMAT.MATRIX_HEADER);
		p.process();

		SimilarityMatrix matrix = p.getSimilarities();
		Assert.assertEquals(Double.NaN, matrix.getSimilarity(0, 0));
		Assert.assertEquals(1.0, matrix.getSimilarity(0, 1));
		Assert.assertEquals(0.0, matrix.getSimilarity(0, 2));
		Assert.assertEquals(Double.NaN, matrix.getSimilarity(1, 0));
		Assert.assertEquals(Double.NaN, matrix.getSimilarity(1, 1));
		Assert.assertEquals(3.0, matrix.getSimilarity(1, 2));
		Assert.assertEquals(Double.NaN, matrix.getSimilarity(2, 0));
		Assert.assertEquals(Double.NaN, matrix.getSimilarity(2, 1));
		Assert.assertEquals(Double.NaN, matrix.getSimilarity(2, 2));
	}

}
