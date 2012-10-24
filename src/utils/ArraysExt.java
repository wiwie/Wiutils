/**
 * 
 */
package utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * @author Christian Wiwie
 * 
 */
public class ArraysExt {

	/**
	 * Returns an array with the absolute values of the input array.
	 * 
	 * @param template
	 *            The input array
	 * @return The array with absolute values.
	 */
	public static double[] abs(double[] template) {
		double[] result = new double[template.length];
		for (int i = 0; i < template.length; i++)
			result[i] = Math.abs(template[i]);
		return result;
	}

	/**
	 * Adds a double value to every element of the array, such that
	 * result[i]=template[i]+add.
	 * 
	 * @param template
	 *            The input array
	 * @param add
	 *            The double value to add to the elements of the input array
	 * @return The result array
	 */
	public static double[] add(double[] template, double add) {
		double[] result = new double[template.length];
		for (int i = 0; i < template.length; i++)
			result[i] = template[i] + add;
		return result;
	}

	/**
	 * Adds double values to elements of the input array, such that
	 * result[i]=template[i]+add[i].
	 * 
	 * @param template
	 *            The input array
	 * @param add
	 *            The array with double values to add to the elements of the
	 *            input array
	 * @return The result array
	 */
	public static double[] add(double[] template, double[] add) {
		double[] result = new double[Math.max(template.length, add.length)];
		for (int i = 0; i < template.length; i++)
			result[i] += template[i];
		for (int i = 0; i < add.length; i++)
			result[i] += add[i];
		return result;
	}

	/**
	 * Adds a double value to every element of the array, such that
	 * result[i]=template[i]+add.
	 * 
	 * @param template
	 *            The input array
	 * @param add
	 *            The double value to add to the elements of the input array
	 * @return The result array
	 */
	public static double[][] add(double[][] template, double add) {
		double[][] result = new double[template.length][];
		for (int i = 0; i < result.length; i++)
			result[i] = ArraysExt.add(result[i], add);
		return result;
	}

	/**
	 * Adds a double value to every element of the array, such that
	 * result[i]=template[i]+add.
	 * 
	 * @param template
	 *            The input array
	 * @param add
	 *            The double value to add to the elements of the input array
	 * @return The result array
	 */
	public static int[] add(int[] template, int add) {
		int[] result = new int[template.length];
		for (int i = 0; i < template.length; i++)
			result[i] = template[i] + add;
		return result;
	}

	/**
	 * Adds double values to elements of the input array, such that
	 * result[i]=template[i]+add[i].
	 * 
	 * @param template
	 *            The input array
	 * @param add
	 *            The array with double values to add to the elements of the
	 *            input array
	 * @return The result array
	 */
	public static int[] add(int[] template, int[] add) {
		int[] result = new int[template.length];
		for (int i = 0; i < template.length; i++)
			result[i] = template[i] + add[i];
		return result;
	}

	/**
	 * Interprets the passed string as a two dimensional separated list of
	 * double values and returns an array.
	 * 
	 * @param text
	 *            The input string
	 * @return The two dimensional double array with the values contained in the
	 *         string.
	 */
	public static double[][] double2DFromSeparatedString(String text) {
		return double2DFromSeparatedString(text, '\t');
	}

	/**
	 * Interprets the passed string as a two dimensional separated list of
	 * double values and returns an array.
	 * 
	 * @param text
	 *            The input string
	 * @param separator
	 *            The character that separates double values in the string.
	 * @return The two dimensional double array with the values contained in the
	 *         string.
	 */
	public static double[][] double2DFromSeparatedString(String text,
			char separator) {
		return double2DFromSeparatedString(text, separator, '\n');
	}

	/**
	 * Interprets the passed string as a two dimensional separated list of
	 * double values and returns an array.
	 * 
	 * @param text
	 *            The input string
	 * @param separator
	 *            The character that separates double values in the string.
	 * @param lineSeparator
	 *            The character that separates the sub-arrays in the string.
	 * @return The two dimensional double array with the values contained in the
	 *         string.
	 */
	public static double[][] double2DFromSeparatedString(String text,
			char separator, char lineSeparator) {
		String[] split = text.split(lineSeparator + "");
		double[][] result = new double[split.length][];
		for (int i = 0; i < result.length; i++)
			result[i] = doublesFromSeparatedString(split[i], separator);
		return result;
	}

	/**
	 * Interprets the passed string as a one dimensional separated list of
	 * double values and returns an array.
	 * 
	 * @param text
	 *            The input string
	 * @return The dimensional double array with the values contained in the
	 *         string.
	 */
	public static double[] doublesFromSeparatedString(String text) {
		return ArraysExt.doublesFromSeparatedString(text, '\t');
	}

	/**
	 * Interprets the passed string as a one dimensional separated list of
	 * double values and returns an array.
	 * 
	 * @param text
	 *            The input string
	 * @param separator
	 *            The character that separates double values in the string.
	 * @return The double array with the values contained in the string.
	 */
	public static double[] doublesFromSeparatedString(String text,
			char separator) {
		String[] split = text.split(separator + "");
		double[] result = new double[split.length];
		for (int i = 0; i < result.length; i++)
			result[i] = Double.valueOf(split[i]);
		return result;
	}

	/**
	 * Returns an array containing only those values of the input array passing
	 * the filter.
	 * 
	 * @param x
	 *            The input array
	 * @param filter
	 *            The filter
	 * @return The filtered array.
	 */
	public static int[] filter(int[] x, Filter filter) {
		List<Integer> result = new LinkedList<Integer>();
		for (int val : x)
			if (filter.filter(val))
				result.add(val);
		return ArraysExt.toPrimitive(result.toArray(new Integer[0]));
	}

	/**
	 * Returns an array containing the floored values of the input array.
	 * 
	 * @param array
	 *            The input array
	 * @return The result array
	 */
	public static double[] floor(double[] array) {
		double[] result = new double[array.length];
		for (int pos = 0; pos < array.length; pos++)
			result[pos] = Math.floor(array[pos]);
		return result;
	}

	/**
	 * Returns a histogram for the values of the input array.
	 * 
	 * @param min
	 *            The minimum value of the histogram.
	 * @param array
	 *            The input array.
	 * @return The histogram for the input array
	 */
	public static long[] hist(int min, int[] array) {
		long[] result = new long[ArraysExt.max(array) + 1 - min];

		for (int i : array)
			result[i - min]++;

		return result;
	}

	/**
	 * Returns a histogram for the values of the input array for x >= 0.
	 * 
	 * @param array
	 *            The input array.
	 * @return The histogram for the input array
	 */
	public static long[] hist(int[] array) {
		return hist(0, array);
	}

	/**
	 * Interprets the passed string as a two dimensional separated list of
	 * integer values and returns an array.
	 * 
	 * @param text
	 *            The input string
	 * @return The two dimensional integer array with the values contained in
	 *         the string.
	 */
	public static int[][] int2DFromSeparatedString(String text) {
		return int2DFromSeparatedString(text, '\t');
	}

	/**
	 * Interprets the passed string as a two dimensional separated list of
	 * integer values and returns an array.
	 * 
	 * @param text
	 *            The input string
	 * @param separator
	 *            The character that separates integer values in the string.
	 * @return The two dimensional integer array with the values contained in
	 *         the string.
	 */
	public static int[][] int2DFromSeparatedString(String text, char separator) {
		return int2DFromSeparatedString(text, separator, '\n');
	}

	/**
	 * Interprets the passed string as a two dimensional separated list of
	 * integer values and returns an array.
	 * 
	 * @param text
	 *            The input string
	 * @param separator
	 *            The character that separates integer values in the string.
	 * @param lineSeparator
	 *            The character that separates the sub-arrays in the string.
	 * @return The two dimensional integer array with the values contained in
	 *         the string.
	 */
	public static int[][] int2DFromSeparatedString(String text, char separator,
			char lineSeparator) {
		String[] split = text.split(lineSeparator + "");
		int[][] result = new int[split.length][];
		for (int i = 0; i < result.length; i++)
			result[i] = intsFromSeparatedString(split[i], separator);
		return result;
	}

	/**
	 * Interprets the passed string as a one dimensional separated list of
	 * integer values and returns an array.
	 * 
	 * @param text
	 *            The input string
	 * @return The dimensional integer array with the values contained in the
	 *         string.
	 */
	public static int[] intsFromSeparatedString(String text) {
		return ArraysExt.intsFromSeparatedString(text, '\t');
	}

	/**
	 * Interprets the passed string as a one dimensional separated list of
	 * integer values and returns an array.
	 * 
	 * @param text
	 *            The input string
	 * @param separator
	 *            The character that separates integer values in the string.
	 * @return The integer array with the values contained in the string.
	 */
	public static int[] intsFromSeparatedString(String text, char separator) {
		String[] split = text.split(separator + "");
		int[] result = new int[split.length];
		for (int i = 0; i < result.length; i++)
			result[i] = Integer.valueOf(split[i]);
		return result;
	}

	/**
	 * Returns an array containing the e-logs of the values in the input array.
	 * 
	 * @param array
	 *            The input array
	 * @return The result array
	 */
	public static double[] log(double[] array) {
		return ArraysExt.log(array, false, 0.0);
	}

	/**
	 * Returns an array containing the e-logs of the values in the input array.
	 * 
	 * @param array
	 *            The input array
	 * @param replaceInfty
	 *            True, if you want to replace infinity values in the input
	 *            array
	 * @param replacement
	 *            The value you want to replace infinity values with.
	 * @return The result array
	 */
	public static double[] log(double[] array, boolean replaceInfty,
			double replacement) {
		double[] result = new double[array.length];
		for (int pos = 0; pos < array.length; pos++)
			if (array[pos] <= 0 && replaceInfty)
				result[pos] = replacement;
			else
				// result[pos] = Math.log10(array[pos])/Math.log10(2);
				result[pos] = Math.log(array[pos]);
		return result;
	}

	/**
	 * Returns an array containing the e-logs of the values in the input array.
	 * 
	 * @param array
	 *            The input array
	 * @return The result array
	 */
	public static double[][] log(double[][] array) {
		return ArraysExt.log(array, false, 0.0);
	}

	/**
	 * Returns an array containing the e-logs of the values in the input array.
	 * 
	 * @param array
	 *            The input array
	 * @param replaceInfty
	 *            True, if you want to replace infinity values in the input
	 *            array
	 * @param replacement
	 *            The value you want to replace infinity values with.
	 * @return The result array
	 */
	public static double[][] log(double[][] array, boolean replaceInfty,
			double replacement) {
		double[][] result = new double[array.length][];
		for (int pos = 0; pos < array.length; pos++)
			result[pos] = ArraysExt.log(array[pos], replaceInfty, replacement);
		return result;
	}

	/**
	 * Returns an array containing the e-logs of the values in the input array.
	 * 
	 * @param array
	 *            The input array
	 * @return The result array
	 */
	public static double[] log(int[] array) {
		return ArraysExt.log(array, false, 0.0);
	}

	/**
	 * Returns an array containing the e-logs of the values in the input array.
	 * 
	 * @param array
	 *            The input array
	 * @param replaceInfty
	 *            True, if you want to replace infinity values in the input
	 *            array
	 * @param replacement
	 *            The value you want to replace infinity values with.
	 * @return The result array
	 */
	public static double[] log(int[] array, boolean replaceInfty,
			double replacement) {
		double[] result = new double[array.length];
		for (int pos = 0; pos < array.length; pos++) {
			if (array[pos] <= 0 && replaceInfty)
				result[pos] = replacement;
			else
				result[pos] = Math.log(array[pos]);
		}
		return result;
	}

	/**
	 * Interprets the passed string as a two dimensional separated list of
	 * integer values and returns an array.
	 * 
	 * @param text
	 *            The input string
	 * @return The two dimensional integer array with the values contained in
	 *         the string.
	 */
	public static long[][] long2DFromSeparatedString(String text) {
		return long2DFromSeparatedString(text, '\t');
	}

	/**
	 * Interprets the passed string as a two dimensional separated list of
	 * integer values and returns an array.
	 * 
	 * @param text
	 *            The input string
	 * @param separator
	 *            The character that separates integer values in the string.
	 * @return The two dimensional integer array with the values contained in
	 *         the string.
	 */
	public static long[][] long2DFromSeparatedString(String text, char separator) {
		return long2DFromSeparatedString(text, separator, '\n');
	}

	/**
	 * Interprets the passed string as a two dimensional separated list of
	 * integer values and returns an array.
	 * 
	 * @param text
	 *            The input string
	 * @param separator
	 *            The character that separates integer values in the string.
	 * @param lineSeparator
	 *            The character that separates the sub-arrays in the string.
	 * @return The two dimensional integer array with the values contained in
	 *         the string.
	 */
	public static long[][] long2DFromSeparatedString(String text,
			char separator, char lineSeparator) {
		String[] split = text.split(lineSeparator + "");
		long[][] result = new long[split.length][];
		for (int i = 0; i < result.length; i++)
			result[i] = longsFromSeparatedString(split[i], separator);
		return result;
	}

	/**
	 * Interprets the passed string as a one dimensional separated list of
	 * integer values and returns an array.
	 * 
	 * @param text
	 *            The input string
	 * @return The dimensional integer array with the values contained in the
	 *         string.
	 */
	public static long[] longsFromSeparatedString(String text) {
		return ArraysExt.longsFromSeparatedString(text, '\t');
	}

	/**
	 * Interprets the passed string as a one dimensional separated list of
	 * integer values and returns an array.
	 * 
	 * @param text
	 *            The input string
	 * @param separator
	 *            The character that separates integer values in the string.
	 * @return The integer array with the values contained in the string.
	 */
	public static long[] longsFromSeparatedString(String text, char separator) {
		String[] split = text.split(separator + "");
		long[] result = new long[split.length];
		for (int i = 0; i < result.length; i++)
			result[i] = Long.valueOf(split[i]);
		return result;
	}

	/**
	 * Returns the maximal value contained in the input values.
	 * 
	 * @param array
	 *            The input values
	 * @return The maximal value
	 */
	public static double max(double... array) {
		return array[maxPos(array)];
	}

	/**
	 * Returns the maximal value contained in the input values.
	 * 
	 * @param array
	 *            The input values
	 * @return The maximal value
	 */
	public static double max(double[][] array) {
		double result = Double.MIN_VALUE;
		for (double[] subArray : array)
			result = Math.max(result, max(subArray));
		return result;
	}

	/**
	 * Returns the maximal value contained in the input values.
	 * 
	 * @param array
	 *            The input values
	 * @return The maximal value
	 */
	public static int max(int... array) {
		if (array.length == 0)
			throw new IllegalArgumentException("Empty array given");
		int max = 0;
		for (int i : array)
			if (i > max)
				max = i;
		return max;
	}

	/**
	 * Returns the index of the input array containing the maximal value.
	 * 
	 * @param array
	 *            The input array
	 * @return The index containing the maximal value.
	 */
	public static int maxPos(double[] array) {
		if (array.length == 0)
			throw new IllegalArgumentException("Empty array given");
		double max = -Double.MAX_VALUE;
		int maxX = 0;
		for (int pos = 0; pos < array.length; pos++) {
			double val = array[pos];
			if (val > max) {
				max = val;
				maxX = pos;
			}
		}
		return maxX;
	}

	/**
	 * Returns the mean of the values in the given array.
	 * 
	 * @param array
	 *            The input array
	 * @return The mean of the input array.
	 */
	public static double mean(double... array) {
		return (sum(array)) / array.length;
	}

	/**
	 * Returns the mean of the values in the given array.
	 * 
	 * @param arrays
	 *            The input array
	 * @return The mean of the input array.
	 */
	public static double[] mean(double[][] arrays) {
		int maxSize = 0;
		for (double[] arr : arrays)
			if (arr.length > maxSize)
				maxSize = arr.length;
		return ArraysExt.mean(arrays, maxSize);
	}

	private static double[] mean(double[][] arrays, int targetSize) {
		double[] result = new double[targetSize];

		for (double[] arr : arrays) {
			result = ArraysExt.add(result, arr);
		}

		for (int i = 0; i < result.length; i++)
			result[i] /= arrays.length;
		return result;
	}

	/**
	 * Returns the mean of the values in the given array.
	 * 
	 * @param array
	 *            The input array
	 * @return The mean of the input array.
	 */
	public static double mean(int... array) {
		return ((double) sum(array)) / array.length;
	}

	/**
	 * Returns the mean of the values in the given array.
	 * 
	 * @param array
	 *            The input array
	 * @return The mean of the input array.
	 */
	public static double mean(long... array) {
		return ((double) sum(array)) / array.length;
	}

	/**
	 * @param ds
	 * @param count
	 * @return
	 */
	public static double[] merge(double[] ds, double[] count) {
		double[] result = new double[ds.length + count.length];
		for (int pos = 0; pos < ds.length; pos++)
			result[pos] = ds[pos];
		for (int pos = 0; pos < count.length; pos++)
			result[ds.length + pos] = count[pos];
		return result;
	}

	/**
	 * @param ds
	 * @param count
	 * @return
	 */
	public static int[] merge(int[] ds, int[] count) {
		int[] result = new int[ds.length + count.length];
		for (int pos = 0; pos < ds.length; pos++)
			result[pos] = ds[pos];
		for (int pos = 0; pos < count.length; pos++)
			result[ds.length + pos] = count[pos];
		return result;
	}

	public static double min(double... array) {
		return array[minPos(array)];
	}

	public static double min(double[][] array) {
		double result = Double.MAX_VALUE;
		for (double[] subArray : array)
			result = Math.min(result, subArray[minPos(subArray)]);
		return result;
	}

	/**
	 * @param array
	 * @return
	 */
	public static int min(int... array) {
		if (array.length == 0)
			throw new IllegalArgumentException("Empty array given");
		int min = 0;
		for (int i : array)
			if (i < min)
				min = i;
		return min;
	}

	/**
	 * @param array
	 * @return
	 */
	public static int minPos(double... array) {
		if (array.length == 0)
			throw new IllegalArgumentException("Empty array given");
		double min = Double.MAX_VALUE;
		int minX = 0;
		for (int pos = 0; pos < array.length; pos++) {
			double i = array[pos];
			if (i < min) {
				min = i;
				minX = pos;
			}
		}
		return minX;
	}

	public static double[] pow(double[] array, double exp) {
		double[] result = new double[array.length];
		for (int pos = 0; pos < result.length; pos++)
			result[pos] = Math.pow(array[pos], exp);
		return result;
	}

	public static double[][] pow(double[][] array, double exp) {
		double[][] result = new double[array.length][];
		for (int pos = 0; pos < result.length; pos++)
			result[pos] = ArraysExt.pow(array[pos], exp);
		return result;
	}

	public static long[] pow(int[] array, double exp) {
		long[] result = new long[array.length];
		for (int pos = 0; pos < result.length; pos++)
			result[pos] = (long) Math.pow(array[pos], exp);
		return result;
	}

	/**
	 * @param degrees
	 */
	public static void print(double[] degrees) {
		System.out.println(ArraysExt.toString(degrees));
	}

	/**
	 * @param degrees
	 */
	public static void print(double[] degrees, int precision) {
		System.out.println(ArraysExt.toString(degrees, precision));
	}

	/**
	 * @param degrees
	 */
	public static void print(double[][] degrees) {
		System.out.println("[");
		for (double[] bla : degrees)
			print(bla);
		System.out.println("]");
	}

	/**
	 * @param degrees
	 */
	public static void print(int[] degrees) {
		System.out.println(Arrays.toString(degrees));
	}

	/**
	 * @param degrees
	 */
	public static void print(int[][] degrees) {
		System.out.println("[");
		for (int[] bla : degrees)
			print(bla);
		System.out.println("]");
	}

	/**
	 * @param degrees
	 */
	public static void print(long[] degrees) {
		System.out.println(Arrays.toString(degrees));
	}

	/**
	 * @param degrees
	 */
	public static <T> void print(T[] degrees) {
		System.out.println(Arrays.toString(degrees));
	}

	/**
	 * @param degrees
	 */
	public static <T> void print(T[][] degrees) {
		System.out.println("[");
		for (T[] bla : degrees)
			print(bla);
		System.out.println("]");
	}

	/**
	 * @param start
	 * @param end
	 * @param stepsize
	 * @return
	 */
	public static double[] range(double start, double end, double stepsize) {
		double[] result = new double[(int) (end / stepsize)
				- (int) (start / stepsize)];
		for (int i = 0; i < result.length; i++) {
			result[i] = start + i * stepsize;
		}
		return result;
	}

	/**
	 * @param start
	 * @param end
	 * @param count
	 * @param inclusiveEnd
	 * @return
	 */
	public static double[] range(double start, double end, int count,
			boolean inclusiveEnd) {
		double[] result = new double[count];
		double constant = (inclusiveEnd
				? start * Math.max(count - 1, 1)
				: start * count);
		double diff = end - start;
		for (int i = 0; i < result.length; i++) {
			result[i] = (constant + i * diff)
					/ (inclusiveEnd ? Math.max(count - 1, 1) : count);
		}
		return result;
	}

	public static int[] range(int start, int end) {
		return range(start, end, 1);
	}

	public static int[] range(int start, int end, int stepsize) {
		int[] result = new int[(end - start) / stepsize];
		for (int i = 0; i < result.length; i++) {
			result[i] = start + i * stepsize;
		}
		return result;
	}

	/**
	 * @param start
	 * @param end
	 * @param count
	 * @param inclusiveEnd
	 * @return
	 * @throws RangeCreationException
	 */
	public static int[] range(int start, int end, int count,
			boolean inclusiveEnd) throws RangeCreationException {
		return range(start, end, count, inclusiveEnd, true);
	}

	public static int[] range(int start, int end, int count,
			boolean inclusiveEnd, boolean noDuplicates)
			throws RangeCreationException {
		if (noDuplicates && end - start < (inclusiveEnd ? count - 1 : count))
			throw new RangeCreationException(
					"Range will contain duplicate values");
		double[] tmp = range((double) start, (double) end, count, inclusiveEnd);
		int[] result = ArraysExt.toIntArray(tmp);
		return result;
	}

	public static double[] rangeOld(double start, double end, int count,
			boolean inclusiveEnd) {
		double[] result = new double[count];
		double stepsize = (end - start) / (inclusiveEnd ? count - 1 : count);
		for (int i = 0; i < result.length; i++) {
			result[i] = start + i * stepsize;
		}
		return result;
	}

	public static double[] rep(double val, int times) {
		double[] result = new double[(times > 0 ? times : 0)];

		for (int i = 0; i < result.length; i++)
			result[i] = val;

		return result;
	}

	/**
	 * @param array
	 * @param factor
	 * @return
	 */
	public static double[] scaleBy(double[] array, double factor) {
		return ArraysExt.scaleBy(array, factor, true);
	}

	/**
	 * @param array
	 * @param factor
	 * @return
	 */
	public static double[] scaleBy(double[] array, double factor, boolean down) {
		double[] result = new double[array.length];
		for (int i = 0; i < array.length; i++)
			if (down)
				result[i] = array[i] / factor;
			else
				result[i] = array[i] * factor;
		return result;
	}

	public static double[][] scaleBy(double[][] array, double factor) {
		double[][] result = new double[array.length][];

		for (int i = 0; i < array.length; i++) {
			result[i] = ArraysExt.scaleBy(array[i], factor);
		}

		return result;
	}

	/**
	 * @param array
	 * @param factor
	 * @return
	 */
	public static double[] scaleBy(int[] array, double factor) {
		return ArraysExt.scaleBy(ArraysExt.toDoubleArray(array), factor);
	}

	public static double[] subtract(double subtract, double[] template) {
		double[] result = new double[template.length];
		for (int i = 0; i < template.length; i++)
			result[i] = subtract - template[i];
		return result;
	}

	public static double[][] subtract(double subtract, double[][] template) {
		double[][] result = new double[template.length][];
		for (int i = 0; i < template.length; i++) {
			result[i] = new double[template[i].length];
			for (int j = 0; j < template[i].length; j++)
				result[i][j] = subtract - template[i][j];
		}
		return result;
	}

	public static double[] subtract(double[] template, double subtract) {
		double[] result = new double[template.length];
		for (int i = 0; i < template.length; i++)
			result[i] = template[i] - subtract;
		return result;
	}

	public static double[] subtract(double[] template, double[] subtract) {
		return subtract(template, subtract, false);
	}

	public static double[] subtract(double[] template, double[] subtract,
			boolean common) {
		double[] result = new double[common ? Math.min(template.length,
				subtract.length) : Math.max(template.length, subtract.length)];
		for (int i = 0; i < result.length && i < template.length; i++)
			result[i] = template[i];
		for (int i = 0; i < result.length && i < subtract.length; i++)
			result[i] -= subtract[i];
		return result;
	}

	public static double[][] subtract(double[][] template, double subtract) {
		double[][] result = new double[template.length][];
		for (int i = 0; i < result.length; i++)
			result[i] = ArraysExt.subtract(template[i], subtract);
		return result;
	}

	public static double[][] subtract(double[][] template, double[][] subtract) {
		double[][] result = new double[template.length][];
		for (int i = 0; i < result.length; i++)
			result[i] = subtract(template[i], subtract[i], false);
		return result;
	}

	public static int[] subtract(int[] template, int subtract) {
		int[] result = new int[template.length];
		for (int i = 0; i < template.length; i++)
			result[i] = template[i] - subtract;
		return result;
	}

	public static double sum(double[] array) {
		double result = 0.0;
		for (double d : array)
			result += d;
		return result;
	}

	public static double sum(double[][] array) {
		double result = 0.0;
		for (double[] d : array)
			result += ArraysExt.sum(d);
		return result;
	}

	/**
	 * @param array
	 * @return
	 */
	public static long sum(int[] array) {
		long result = 0;
		for (int i : array)
			result += i;
		return result;
	}

	/**
	 * @param array
	 * @return
	 */
	public static long sum(int[][] array) {
		long result = 0;
		for (int[] i : array)
			result += ArraysExt.sum(i);
		return result;
	}

	/**
	 * @param array
	 * @return
	 */
	public static long sum(long[] array) {
		long result = 0;
		for (long i : array)
			result += i;
		return result;
	}

	public static String SVD_LP(int[][] array) {
		StringBuilder sb = new StringBuilder();

		sb.append("min: x11;\n\n");

		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				sb.append("x" + i + j + " = " + array[i][j] + ";\n");
			}
		}

		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				sb.append("x" + i + j + " = ");
				for (int x = 0; x < array.length; x++)
					sb.append("u" + i + x + " d" + x + " v" + x + j + " +");
				sb.deleteCharAt(sb.length() - 1);
				sb.append(";\n");
			}
		}

		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				sb.append("u" + i + j + " >= 0;\n");
			}
		}

		for (int i = 0; i < array[0].length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				sb.append("v" + i + j + " >= 0;\n");
			}
		}

		for (int i = 0; i < Math.min(array.length, array[0].length); i++) {
			sb.append("d" + i + " >= 0;\n");
		}

		sb.append("int ");
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				sb.append("x" + i + j + ", ");
			}
		}
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				sb.append("u" + i + j + ", ");
			}
		}

		for (int i = 0; i < array[0].length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				sb.append("v" + i + j + ", ");
			}
		}

		for (int i = 0; i < Math.min(array.length, array[0].length); i++) {
			sb.append("d" + i + ", ");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.deleteCharAt(sb.length() - 1);
		sb.append(";");

		return sb.toString();
	}

	/**
	 * @param <T>
	 * @param list
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] toArray(List<T> list) {
		if (list.size() == 0)
			return null;
		T[] arr = (T[]) java.lang.reflect.Array.newInstance(list.get(0)
				.getClass(), list.size());
		for (int i = 0; i < list.size(); i++)
			arr[i] = list.get(i);
		return arr;
	}

	public static double[] toDoubleArray(int[] arr) {
		double[] result = new double[arr.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = arr[i];
		}
		return result;
	}

	public static double[][] toDoubleArray(int[][] arr) {
		double[][] result = new double[arr.length][];
		for (int i = 0; i < result.length; i++) {
			result[i] = toDoubleArray(arr[i]);
		}
		return result;
	}

	public static double[] toDoubleArray(long[] arr) {
		double[] result = new double[arr.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = arr[i];
		}
		return result;
	}

	public static double[][] toDoubleArray(long[][] arr) {
		double[][] result = new double[arr.length][];
		for (int i = 0; i < result.length; i++) {
			result[i] = toDoubleArray(arr[i]);
		}
		return result;
	}

	/**
	 * @param array
	 * @param buckets
	 * @return
	 */
	public static Pair<double[], int[]> toHistogram(final double[] array,
			final int buckets) {
		double min = ArraysExt.min(array);
		double max = ArraysExt.max(array);
		double[] minValues = ArraysExt.range(min, max, buckets, false);

		int[] result = new int[buckets];
		for (double d : array) {
			int pos = 0;
			while (pos < minValues.length && minValues[pos] <= d)
				pos++;
			pos = Math.max(pos - 1, 0);
			result[pos]++;
		}

		return Pair.getPair(minValues, result);
	}

	public static int[] toIntArray(double[] array) {
		int[] result = new int[array.length];

		for (int i = 0; i < result.length; i++) {
			result[i] = (int) array[i];
		}

		return result;
	}

	/**
	 * @param array
	 * @return
	 */
	public static double[] toPrimitive(final Double[] array) {
		double[] arr = new double[array.length];
		for (int i = 0; i < array.length; i++)
			arr[i] = array[i];
		return arr;
	}

	/**
	 * @param array
	 * @return
	 */
	public static float[] toPrimitive(final Float[] array) {
		float[] arr = new float[array.length];
		for (int i = 0; i < array.length; i++)
			arr[i] = array[i];
		return arr;
	}

	/**
	 * @param array
	 * @return
	 */
	public static int[] toPrimitive(final Integer[] array) {
		int[] arr = new int[array.length];
		for (int i = 0; i < array.length; i++)
			arr[i] = array[i];
		return arr;
	}

	/**
	 * @param array
	 * @return
	 */
	public static long[] toPrimitive(final Long[] array) {
		long[] arr = new long[array.length];
		for (int i = 0; i < array.length; i++)
			arr[i] = array[i];
		return arr;
	}

	public static String toSeparatedString(double[] result) {
		return ArraysExt.toSeparatedString(result, '\t');
	}

	public static String toSeparatedString(double[] result, char separator) {
		StringBuilder sb = new StringBuilder();
		for (double d : result) {
			sb.append(d);
			sb.append(separator);
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	public static String toSeparatedString(double[][] result) {
		return toSeparatedString(result, '\t');
	}

	/**
	 * @param result
	 * @param separator
	 * @return
	 */
	public static String toSeparatedString(double[][] result, char separator) {
		return toSeparatedString(result, separator, '\n');
	}

	/**
	 * @param result
	 * @param separator
	 * @param lineSeparator
	 * @return
	 */
	public static String toSeparatedString(double[][] result, char separator,
			char lineSeparator) {
		StringBuilder sb = new StringBuilder();

		for (double[] d : result) {
			sb.append(toSeparatedString(d, separator));
			sb.append(lineSeparator);
		}

		sb.deleteCharAt(sb.length() - 1);

		return sb.toString();
	}

	/**
	 * @param result
	 * @return Returns a tabseparated string
	 */
	public static String toSeparatedString(int[] result) {
		return ArraysExt.toSeparatedString(result, '\t');
	}

	public static String toSeparatedString(int[] result, char separator) {
		StringBuilder sb = new StringBuilder();
		for (int i : result) {
			sb.append(i);
			sb.append(separator);
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	public static String toSeparatedString(int[][] result) {
		return toSeparatedString(result, '\t');
	}

	public static String toSeparatedString(int[][] result, char separator) {
		return toSeparatedString(result, '\t', '\n');
	}

	public static String toSeparatedString(int[][] result, char separator,
			char lineSeparator) {
		StringBuilder sb = new StringBuilder();

		for (int[] i : result) {
			sb.append(toSeparatedString(i, separator));
			sb.append(lineSeparator);
		}

		sb.deleteCharAt(sb.length() - 1);

		return sb.toString();
	}

	/**
	 * @param result
	 * @return Returns a tabseparated string
	 */
	public static String toSeparatedString(long[] result) {
		return ArraysExt.toSeparatedString(result, '\t');
	}

	public static String toSeparatedString(long[] result, char separator) {
		StringBuilder sb = new StringBuilder();
		for (long i : result) {
			sb.append(i);
			sb.append(separator);
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	public static String toSeparatedString(long[][] result) {
		return toSeparatedString(result, '\t');
	}

	public static String toSeparatedString(long[][] result, char separator) {
		return toSeparatedString(result, '\t', '\n');
	}

	public static String toSeparatedString(long[][] result, char separator,
			char lineSeparator) {
		StringBuilder sb = new StringBuilder();

		for (long[] i : result) {
			sb.append(toSeparatedString(i, separator));
			sb.append(lineSeparator);
		}

		sb.deleteCharAt(sb.length() - 1);

		return sb.toString();
	}

	public static String toString(double[] a) {
		if (a == null)
			return "null";
		int iMax = a.length - 1;
		if (iMax == -1)
			return "[]";

		StringBuilder b = new StringBuilder();
		b.append('[');
		for (int i = 0;; i++) {
			b.append(String.format(Locale.US, "%.6f", a[i]));
			if (i == iMax)
				return b.append(']').toString();
			b.append(", ");
		}
	}

	public static String toString(double[] a, int precision) {
		if (a == null)
			return "null";
		int iMax = a.length - 1;
		if (iMax == -1)
			return "[]";

		StringBuilder b = new StringBuilder();
		b.append('[');
		for (int i = 0;; i++) {
			b.append(String.format(Locale.US, "%." + precision + "f", a[i]));
			if (i == iMax)
				return b.append(']').toString();
			b.append(", ");
		}
	}

	public static String[] toString(Object[] array) {
		String[] result = new String[array.length];
		for (int i = 0; i < array.length; i++)
			result[i] = array[i].toString();
		return result;
	}

	public static double[] unique(double[] array) {
		Set<Double> list = new HashSet<Double>();
		for (double d : array) {
			list.add(d);
		}
		double[] result = ArraysExt.toPrimitive(list.toArray(new Double[0]));
		return result;
	}

	public static int[] unique(int[] array) {
		Set<Integer> list = new HashSet<Integer>();
		for (int d : array) {
			list.add(d);
		}
		int[] result = ArraysExt.toPrimitive(list.toArray(new Integer[0]));
		return result;
	}
}
