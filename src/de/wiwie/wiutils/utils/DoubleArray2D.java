/**
 * 
 */
package de.wiwie.wiutils.utils;

import java.util.Arrays;

/**
 * @author Christian Wiwie
 * 
 */
public class DoubleArray2D extends NumberArray2D {

	protected double[][] array;

	public DoubleArray2D(final int rows, final int columns) {
		this(rows, columns, false);
	}

	public DoubleArray2D(final int rows, final int columns, final boolean isSymmetric) {
		super(isSymmetric);
		if (isSymmetric) {
			this.array = new double[rows][];
			// TODO: assuming that the diagonal goes through [0][0]
			// storing the lower half of the matrix
			for (int i = 0; i < rows; i++) {
				this.array[i] = new double[i + 1];
			}
		} else {
			this.array = new double[rows][columns];
		}
		for (int i = 0; i < this.array.length; i++) {
			for (int j = 0; j < this.array[i].length; j++) {
				this.array[i][j] = Double.NaN;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.wiwie.wiutils.utils.NumberArray2D#isSet(int, int)
	 */
	@Override
	public boolean isSet(int x, int y) {
		if (this.isSymmetric && x < y)
			return !Double.isNaN(this.array[y][x]);
		return !Double.isNaN(this.array[x][y]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see utils.NumberArray2D#getX()
	 */
	@Override
	public int getRows() {
		return this.array.length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see utils.NumberArray2D#getY()
	 */
	@Override
	public int getColumns() {
		if (isSymmetric())
			return this.getRows();
		// TODO
		return this.array[0].length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see utils.NumberArray2D#get(int, int)
	 */
	@Override
	public double get(int x, int y) {
		if (isSymmetric() && y > x)
			return this.array[y][x];
		return this.array[x][y];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see utils.NumberArray2D#set(int, int, double)
	 */
	@Override
	public void set(int x, int y, double val) {
		if (isSymmetric() && y > x) {
			this.array[y][x] = val;
			return;
		}
		this.array[x][y] = val;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see utils.NumberArray2D#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof DoubleArray2D))
			return false;

		DoubleArray2D other = (DoubleArray2D) o;
		return this.isSymmetric == other.isSymmetric && Arrays.deepEquals(this.array, other.array);
	}
}
