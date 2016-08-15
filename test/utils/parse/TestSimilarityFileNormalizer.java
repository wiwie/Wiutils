/**
 * 
 */
package utils.parse;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.wiwie.wiutils.utils.parse.SimilarityFileNormalizer;
import de.wiwie.wiutils.utils.parse.TextFileParser;
import de.wiwie.wiutils.utils.parse.SimFileParser.SIM_FILE_FORMAT;

/**
 * @author Christian Wiwie
 * 
 */
public class TestSimilarityFileNormalizer {

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

	@Test
	public void testSimMatrixNAs() throws IOException {
		TextFileParser parser = new SimilarityFileNormalizer(
				new File("testData/testMatrixWithNAs.txt").getAbsolutePath(),
				SIM_FILE_FORMAT.MATRIX_HEADER, new File(
						"testData/testMatrixWithNAs.txt.out").getAbsolutePath());
		parser.process();
	}
}
