/**
 * 
 */
package de.wiwie.wiutils.utils;

import java.util.Collection;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Christian Wiwie
 * 
 */
public class StringExt {

	/**
	 * Merges the given strings into one string, using the delimiter.
	 * 
	 * @param delimiter
	 * @param strings
	 * @return
	 */
	public static String paste(final String delimiter, String... strings) {
		StringBuilder sb = new StringBuilder();

		for (String s : strings) {
			sb.append(s);
			sb.append(delimiter);
		}

		if (sb.length() > 0) {
			int pos = sb.lastIndexOf(delimiter);
			sb.delete(pos, pos + delimiter.length());
		}

		return sb.toString();
	}

	/**
	 * Merges the given objects into one string, using the delimiter.
	 * 
	 * @param delimiter
	 * @param objects
	 * @return
	 */
	public static String paste(final String delimiter, Collection<?> objects) {
		StringBuilder sb = new StringBuilder();

		for (Object o : objects) {
			sb.append(o);
			sb.append(delimiter);
		}

		if (sb.length() > 0) {
			int pos = sb.lastIndexOf(delimiter);
			sb.delete(pos, pos + delimiter.length());
		}

		return sb.toString();
	}

	/**
	 * Appends a string to every string in an array.
	 * 
	 * @param arr
	 * @param append
	 * @return
	 */
	public static String[] append(final String[] arr, final String append) {
		String[] result = new String[arr.length];

		for (int i = 0; i < result.length; i++)
			result[i] = arr[i] + append;

		return result;
	}

	/**
	 * 
	 * @param s
	 *            The string to split.
	 * @param delimiter
	 *            The string which delimits the substrings
	 * @return An array of the substrings of the given string delimited by the
	 *         delimiter.
	 */
	public static String[] split(final String s, final String delimiter) {
		// StringTokenizer tok = new StringTokenizer(s, delimiter, false);
		// // if the empty string is separated, we need to manually add the
		// string
		// // to the list
		// if (s.startsWith(delimiter)) {
		// final String[] elems = new String[tok.countTokens() + 1];
		// elems[0] = "";
		// for (int i = 1; i < elems.length; i++)
		// elems[i] = tok.nextToken();
		// return elems;
		// }
		//
		// final String[] elems = new String[tok.countTokens()];
		// for (int i = 0; i < elems.length; i++)
		// elems[i] = tok.nextToken();
		// return elems;
		return StringUtils.splitPreserveAllTokens(s, delimiter);
	}

	/**
	 * This split method also includes trailing empty strings
	 * 
	 * @param s
	 *            The string to split.
	 * @param delimiter
	 *            The string which delimits the substrings
	 * @return An array of the substrings of the given string delimited by the
	 *         delimiter.
	 */
	// public static String[] split(final String s, final String delimiter) {
	// return s.split(delimiter, -1);
	// }

	public static String ellipsis(final String s, final int targetLength) {
		String dots = "[..]";
		if (targetLength < s.length()) {
			if (targetLength < dots.length()) {
				if (targetLength > 2)
					dots = ".";
				else
					dots = "";
			}
		} else
			dots = "";
		int prefixLength = (int) Math
				.round(Math.ceil((targetLength - dots.length()) / 2.0));
		int postfixLength = (int) Math
				.round(Math.floor((targetLength - dots.length()) / 2.0));
		int prefixEndPos = Math.min(prefixLength, s.length());
		int postfixStartPos = Math.max(s.length() - postfixLength,
				prefixEndPos);
		StringBuilder sb = new StringBuilder();
		sb.append(s.substring(0, prefixEndPos));
		sb.append(dots);
		sb.append(s.substring(postfixStartPos));
		return sb.toString();
	}

	public static String repeat(final String s, final int times) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < times; i++)
			sb.append(s);

		return sb.toString();
	}

	public static void main(String[] args) {
		String test = "asdaidasasd9asd9asdiasidiasdiasisda";
		for (int i = 0; i <= test.length(); i++) {
			System.out.println(ellipsis(test, i));
		}
	}
}
