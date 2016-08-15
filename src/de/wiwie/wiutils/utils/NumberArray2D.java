/**
 * 
 */
package de.wiwie.wiutils.utils;

/**
 * @author Christian Wiwie
 * 
 */
public abstract class NumberArray2D {

	protected boolean isSymmetric;

	public NumberArray2D(final boolean isSymmetric) {
		super();
		this.isSymmetric = isSymmetric;
	}

	public abstract int getRows();

	public abstract int getColumns();

	public abstract double get(int x, int y);

	public abstract void set(int x, int y, double val);
	
	public abstract boolean isSet(int x, int y);

	@Override
	public abstract boolean equals(Object o);

	public boolean isSymmetric() {
		return this.isSymmetric;
	}
}
