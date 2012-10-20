/**
 * 
 */
package utils;

import cern.colt.matrix.tdouble.impl.SparseDoubleMatrix2D;

/**
 * @author Christian Wiwie
 * 
 */
public class MySparseDoubleMatrix2D extends SparseDoubleMatrix2D {

	/**
	 * 
	 */
	private static final long serialVersionUID = 233743232077965868L;

	/**
	 * @param rows
	 * @param columns
	 */
	public MySparseDoubleMatrix2D(int rows, int columns) {
		super(rows, columns);
	}

	/**
	 * @param values
	 */
	public MySparseDoubleMatrix2D(double[][] values) {
		super(values);
	}

	/**
	 * @param row
	 * @param column
	 * @return
	 */
	public boolean isSparse(int row, int column) {
		return !this.elements.containsKey(index(row, column));
	}
}
