/**
 * 
 */
package utils;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import utils.parse.StringGroupParser;

/**
 * @author Christian Wiwie
 * 
 */
public class TestStringGroupParser {

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
	public void test() {
		StringGroupParser p = new StringGroupParser(
				"[as[test3]dasd[test2]]as[test4]", '[', ']');
		System.out.println(p.parse());
		p = new StringGroupParser("[[test1]]", '[', ']');
		System.out.println(p.parse());
		p = new StringGroupParser("test1", '[', ']');
		System.out.println(p.parse());
		p = new StringGroupParser("[test1][test2]", '[', ']');
		System.out.println(p.parse());
		p = new StringGroupParser(
				"// alias = EBV_gml// dataSetFormat = GMLDataSetFormat// dataSetType = GeneExpressionDataSetType// dataSetFormatVersion = 1graph [  node [    id 0    label 'BCRF1'    index 0  ]  node [    id 1    label 'BTRF1'    index 1  ]  node [    id 2    label 'BBLF3'    index 2  ]  edge [    source 0    target 38  ]  edge [    source 0    target 13  ]  edge [    source 0    target 49  ]]",
				'[', ']');
		System.out.println(p.parse());
	}
}
