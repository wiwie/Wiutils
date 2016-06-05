/**
 * 
 */
package utils;

import junitx.framework.ArrayAssert;

import org.junit.Test;

import de.wiwie.wiutils.utils.ArraysExt;
import de.wiwie.wiutils.utils.RangeCreationException;

/**
 * @author Christian Wiwie
 * w
 */
public class TestArraysExt {

	@Test
	public void testDoubleRange2() throws RangeCreationException {
		double[] range = ArraysExt.range(2.0, 3.0, 1, true);
		double[] expected = new double[]{2.0};
		ArrayAssert.assertEquals(expected, range, 0.00000001);
	}

	@Test
	public void testDoubleRange3() throws RangeCreationException {
		double[] range = ArraysExt.range(0.0, 1.0, 1, true);
		double[] expected = new double[]{0.0};
		ArrayAssert.assertEquals(expected, range, 0.00000001);
	}

	@Test
	public void testDoubleRange5() throws RangeCreationException {
		double[] range = ArraysExt.range(0.0, 1.0, 3, true);
		double[] expected = new double[]{0.0, 0.5, 1.0};
		ArrayAssert.assertEquals(expected, range, 0.00000001);
	}

	@Test
	public void testDoubleRange3a() throws RangeCreationException {
		double[] range = ArraysExt.range(0.0, 1.0, 1, false);
		double[] expected = new double[]{0.0};
		ArrayAssert.assertEquals(expected, range, 0.00000001);
	}

	@Test
	public void testDoubleRange4() throws RangeCreationException {
		double[] range = ArraysExt.range(0.0, 1.0, 2, true);
		double[] expected = new double[]{0.0, 1.0};
		ArrayAssert.assertEquals(expected, range, 0.00000001);
	}

	@Test
	public void testIntRange4() throws RangeCreationException {
		int[] intRange = ArraysExt.range(0, 1, 1, true);
		int[] intExpected = new int[]{0};
		ArrayAssert.assertEquals(intExpected, intRange);
	}

	@Test
	public void testIntRange5() throws RangeCreationException {
		int[] intRange = ArraysExt.range(0, 1, 1, false);
		int[] intExpected = new int[]{0};
		ArrayAssert.assertEquals(intExpected, intRange);
	}

	@Test
	public void testIntRange6() throws RangeCreationException {
		int[] intRange = ArraysExt.range(2, 3, 1, true);
		int[] intExpected = new int[]{2};
		ArrayAssert.assertEquals(intExpected, intRange);
	}

	@Test
	public void testToHistogram() {
		double[] values = new double[]{0.0, 0.4, 0.3, 0.33, 0.2, 0.8, 0.6, 0.5,
				0.5, 0.8, 1.0};
		int[] histogram = ArraysExt.toHistogram(values, 10).getSecond();
		// minValues: 0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9
		int[] expected = new int[]{1, 0, 1, 2, 1, 2, 1, 0, 2, 1};
		ArrayAssert.assertEquals(expected, histogram);
	}
}
