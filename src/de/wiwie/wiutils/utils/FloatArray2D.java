/**
 * 
 */
package de.wiwie.wiutils.utils;

import java.util.Arrays;

/**
 * @author Christian Wiwie
 * 
 */
public class FloatArray2D extends NumberArray2D {

	protected float[][] array;

	public FloatArray2D(final int rows, final int columns) {
		this(rows, columns, false);
	}

	public FloatArray2D(final int rows, final int columns,
			final boolean isSymmetric) {
		super(isSymmetric);
		if (isSymmetric) {
			this.array = new float[rows][];
			// TODO: assuming that the diagonal goes through [0][0]
			// storing the lower half of the matrix
			for (int i = 0; i < rows; i++) {
				this.array[i] = new float[i + 1];
			}
		} else
			this.array = new float[rows][columns];
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
			this.array[y][x] = (float) val;
			return;
		}
		this.array[x][y] = (float) val;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see utils.NumberArray2D#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof FloatArray2D))
			return false;

		FloatArray2D other = (FloatArray2D) o;
		return this.isSymmetric == other.isSymmetric
				&& Arrays.deepEquals(this.array, other.array);
	}
}
