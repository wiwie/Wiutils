/**
 * 
 */
package de.wiwie.wiutils.utils;

import java.util.Arrays;

/**
 * @author Christian Wiwie
 * 
 */
public class ShortArray2D extends NumberArray2D {

	protected short[][] array;

	public ShortArray2D(final int rows, final int columns) {
		this(rows, columns, false);
	}

	public ShortArray2D(final int rows, final int columns,
			final boolean isSymmetric) {
		super(isSymmetric);
		if (isSymmetric) {
			this.array = new short[rows][];
			// TODO: assuming that the diagonal goes through [0][0]
			// storing the lower half of the matrix
			for (int i = 0; i < rows; i++) {
				this.array[i] = new short[i + 1];
			}
		} else
			this.array = new short[rows][columns];
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
			return this.get(y, x);
		return this.array[x][y] / 1000.0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see utils.NumberArray2D#set(int, int, double)
	 */
	@Override
	public void set(int x, int y, double val) {
		if (isSymmetric() && y > x) {
			this.set(y, x, val);
			return;
		}
		this.array[x][y] = (short) (val * 1000);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see utils.NumberArray2D#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof ShortArray2D))
			return false;

		ShortArray2D other = (ShortArray2D) o;
		return this.isSymmetric == other.isSymmetric
				&& Arrays.deepEquals(this.array, other.array);
	}
}
