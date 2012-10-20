/**
 * 
 */
package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cern.colt.function.tdouble.DoubleProcedure;

/**
 * @author Christian Wiwie
 * 
 */
public class SimilarityMatrix {

	protected Map<String, Integer> ids;
	protected MySparseDoubleMatrix2D sparseMatrix;

	/**
	 * @param rows
	 * @param columns
	 */
	public SimilarityMatrix(final int rows, final int columns) {
		this(null, rows, columns);
	}

	/**
	 * @param ids
	 * @param rows
	 * @param columns
	 */
	public SimilarityMatrix(final String[] ids, final int rows,
			final int columns) {
		super();

		if (ids != null) {
			this.ids = new HashMap<String, Integer>();
			for (String id : ids)
				this.ids.put(id, this.ids.size());
		}

		this.sparseMatrix = new MySparseDoubleMatrix2D(rows, columns);
	}

	/**
	 * @param similarities
	 */
	public SimilarityMatrix(final double[][] similarities) {
		this(null, similarities);
	}

	/**
	 * @param ids
	 * @param similarities
	 */
	public SimilarityMatrix(final String[] ids, final double[][] similarities) {
		super();

		if (ids != null) {
			this.ids = new HashMap<String, Integer>();
			for (String id : ids)
				this.ids.put(id, this.ids.size());
		}

		this.sparseMatrix = new MySparseDoubleMatrix2D(similarities);
	}

	/**
	 * @param id1
	 * @param id2
	 * @return
	 */
	public double getSimilarity(final int id1, final int id2) {
		return this.sparseMatrix.get(id1, id2);
	}

	/**
	 * @param id1
	 * @param id2
	 * @return
	 */
	public double getSimilarity(final String id1, final String id2) {
		if (this.ids == null)
			throw new IllegalArgumentException(
					"No ids were set for this matrix!");
		return this.getSimilarity(this.ids.get(id1), this.ids.get(id2));
	}

	/**
	 * @param id1
	 * @param id2
	 * @param similarity
	 */
	public void setSimilarity(final int id1, final int id2,
			final double similarity) {
		this.sparseMatrix.set(id1, id2, similarity);
	}

	/**
	 * @param id1
	 * @param id2
	 * @param similarity
	 */
	public void setSimilarity(final String id1, final String id2,
			final double similarity) {
		if (this.ids == null)
			throw new IllegalArgumentException(
					"No ids were set for this matrix!");
		this.sparseMatrix.set(this.ids.get(id1), this.ids.get(id2), similarity);
	}

	/**
	 * @return
	 */
	public int getRows() {
		return this.sparseMatrix.rows();
	}

	/**
	 * @return
	 */
	public int getColumns() {
		return this.sparseMatrix.columns();
	}

	/**
	 * @return
	 */
	public double getMaxValue() {
		return this.sparseMatrix.getMaxLocation()[0];
	}

	/**
	 * @return
	 */
	public double getMinValue() {
		return this.sparseMatrix.getMinLocation()[0];
	}

	/**
	 * @return
	 */
	public double getMean() {
		double result = 0.0;
		for (long i = 0; i < this.sparseMatrix.elements().size(); i++)
			result += this.sparseMatrix.elements().get(i);
		result /= this.sparseMatrix.elements().size();

		return result;
	}

	/**
	 * @param array
	 */
	public void setIds(String[] array) {
		this.ids = new HashMap<String, Integer>();
		for (int pos = 0; pos < array.length; pos++) {
			String id = array[pos];
			this.ids.put(id, pos);
		}
	}

	/**
	 * @return
	 */
	public Map<String, Integer> getIds() {
		return this.ids;
	}

	/**
	 * @param row
	 * @param column
	 * @return
	 */
	public boolean isSparse(int row, int column) {
		return this.sparseMatrix.isSparse(row, column);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof SimilarityMatrix))
			return false;

		SimilarityMatrix other = (SimilarityMatrix) obj;

		return ((this.ids == null && other.ids == null) || this.ids
				.equals(other.ids))
				&& this.sparseMatrix.equals(other.sparseMatrix);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return (this.ids + "" + this.sparseMatrix).hashCode();
	}

	/**
	 * @param numberBuckets
	 * @return
	 * 
	 */
	public Map<Double, Integer> toDistribution(int numberBuckets) {

		DistributionBuilder builder = new DistributionBuilder(this,
				numberBuckets);

		this.sparseMatrix.elements().values().forEach(builder);

		return builder.getResult();
	}

	/**
	 * @return
	 */
	public double[][] toArray() {
		double[][] result = new double[this.ids.size()][this.ids.size()];

		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				result[i][j] = this.getSimilarity(i, j);
			}
		}

		return result;
	}

	/**
	 * @return
	 */
	public List<Double> toOrderedList() {
		List<Double> distr = new ArrayList<Double>();
		for (int i = 0; i < this.sparseMatrix.elements().values().size(); i++) {
			// for (int i = 0; i < this.ids.size(); i++) {
			// for (int j = i; j < this.ids.size(); j++) {
			// distr.add(this.getSimilarity(i, j));
			distr.add(this.sparseMatrix.elements().values().get(i));
			// }
		}
		Collections.sort(distr);
		return distr;
	}

	/**
	 * @param numberOfQuantiles
	 * @return
	 * @throws RangeCreationException
	 */
	public double[] getQuantiles(final int numberOfQuantiles)
			throws RangeCreationException {
		List<Double> orderedList = toOrderedList();
		int[] range = ArraysExt.range(0, orderedList.size() - 1,
				numberOfQuantiles, true);
		double[] result = new double[range.length];
		for (int i = 0; i < range.length; i++)
			result[i] = orderedList.get(range[i]);
		return result;
	}

	/**
	 * @param numberBuckets
	 * @return
	 */
	public Pair<double[], int[]> toDistributionArray(int numberBuckets) {

		DistributionBuilder builder = new DistributionBuilder(this,
				numberBuckets);

		this.sparseMatrix.elements().values().forEach(builder);

		return builder.getResultAsArray();
	}

	/**
	 * @param numberBuckets
	 * @param classIds
	 * @return
	 */
	// TODO: added 25th 07 2012
	public Pair<double[], int[][]> toClassDistributionArray(int numberBuckets,
			String[][] classIds) {

		ClassDistributionBuilder builder = new ClassDistributionBuilder(this,
				numberBuckets, classIds);

		// this.sparseMatrix.elements().values().forEach(builder);
		builder.fillArray(this);

		return builder.getResultAsArray();
	}

	/**
	 * @param numberBuckets
	 * @param idToClass
	 * @return
	 */
	// TODO: added 25th 07 2012
	public Pair<double[], int[][]> toIntraInterDistributionArray(
			int numberBuckets, Map<String, Integer> idToClass) {

		IntraInterDistributionBuilder builder = new IntraInterDistributionBuilder(
				this, numberBuckets, idToClass);

		builder.fillArray(this);

		return builder.getResultAsArray();
	}
}

class DistributionBuilder implements DoubleProcedure {

	protected HashMap<Double, Integer> result;
	protected int[] resultArray;
	protected double[] range;

	public DistributionBuilder(final SimilarityMatrix matrix, int numberBuckets) {
		super();

		this.result = new HashMap<Double, Integer>();

		double minValue = matrix.getMinValue();
		double maxValue = matrix.getMaxValue();

		this.range = ArraysExt.range(minValue, maxValue, numberBuckets, true);

		this.resultArray = new int[this.range.length];

		for (double d : range) {
			result.put(d, 0);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cern.colt.function.tdouble.DoubleFunction#apply(double)
	 */
	@Override
	public boolean apply(double argument) {
		/*
		 * Do binary search for bucket
		 */
		int lower = 0;
		int upper = this.range.length - 1;

		while (lower < upper) {
			int middle = lower + (int) Math.round((upper - lower) / 2.0);

			if (lower == upper - 1) {
				if (argument < this.range[upper]) {
					upper = lower;
				} else
					lower = upper;
			} else if (argument < this.range[middle]) {
				upper = middle;
			} else {
				lower = middle;
			}
		}

		this.result.put(this.range[lower],
				this.result.get(this.range[lower]) + 1);
		this.resultArray[lower]++;
		return true;
	}

	public HashMap<Double, Integer> getResult() {
		return this.result;
	}

	/**
	 * @return
	 */
	public Pair<double[], int[]> getResultAsArray() {
		return Pair.getPair(this.range, this.resultArray);
	}
}

class ClassDistributionBuilder {

	protected List<Map<Double, Integer>> result;
	protected int[][] resultArray;
	protected double[] range;
	protected String[][] classIds;

	public ClassDistributionBuilder(final SimilarityMatrix matrix,
			int numberBuckets, String[][] classIds) {
		super();

		this.classIds = classIds;

		this.result = new ArrayList<Map<Double, Integer>>();

		double minValue = matrix.getMinValue();
		double maxValue = matrix.getMaxValue();

		this.range = ArraysExt.range(minValue, maxValue, numberBuckets, true);

		this.resultArray = new int[classIds.length][this.range.length];

		for (int c = 0; c < classIds.length; c++) {
			Map<Double, Integer> tmp = new HashMap<Double, Integer>();
			for (double d : range) {
				tmp.put(d, 0);
			}
			result.add(tmp);
		}
	}

	public boolean fillArray(SimilarityMatrix matrix) {
		for (int c = 0; c < this.classIds.length; c++) {
			for (int x = 0; x < this.classIds[c].length; x++) {
				for (int y = 0; y < this.classIds[c].length; y++) {
					double argument = matrix.getSimilarity(this.classIds[c][x],
							this.classIds[c][y]);

					/*
					 * Do binary search for bucket
					 */
					int lower = 0;
					int upper = this.range.length - 1;

					while (lower < upper) {
						int middle = lower
								+ (int) Math.round((upper - lower) / 2.0);

						if (lower == upper - 1) {
							if (argument < this.range[upper]) {
								upper = lower;
							} else
								lower = upper;
						} else if (argument < this.range[middle]) {
							upper = middle;
						} else {
							lower = middle;
						}
					}

					Map<Double, Integer> tmp = this.result.get(c);
					tmp.put(this.range[lower], tmp.get(this.range[lower]) + 1);
					this.resultArray[c][lower]++;
				}
			}
		}

		return true;
	}

	public List<Map<Double, Integer>> getResult() {
		return this.result;
	}

	/**
	 * @return
	 */
	public Pair<double[], int[][]> getResultAsArray() {
		return Pair.getPair(this.range, this.resultArray);
	}
}

class IntraInterDistributionBuilder {

	protected List<Map<Double, Integer>> result;
	protected int[][] resultArray;
	protected double[] range;
	protected Map<String, Integer> idToClass;

	public IntraInterDistributionBuilder(final SimilarityMatrix matrix,
			int numberBuckets, Map<String, Integer> idToClass) {
		super();

		this.idToClass = idToClass;

		this.result = new ArrayList<Map<Double, Integer>>();

		double minValue = matrix.getMinValue();
		double maxValue = matrix.getMaxValue();

		this.range = ArraysExt.range(minValue, maxValue, numberBuckets, true);

		this.resultArray = new int[2][this.range.length];

		/*
		 * Add one for intra, and one for inter distribution
		 */
		for (int c = 0; c < this.resultArray.length; c++) {
			Map<Double, Integer> tmp = new HashMap<Double, Integer>();
			for (double d : range) {
				tmp.put(d, 0);
			}
			result.add(tmp);
		}
	}

	public boolean fillArray(SimilarityMatrix matrix) {
		Set<String> ids = matrix.getIds().keySet();

		Iterator<String> idIterator = ids.iterator();
		while (idIterator.hasNext()) {
			String id1 = idIterator.next();
			Iterator<String> idIterator2 = ids.iterator();
			while (idIterator2.hasNext()) {
				String id2 = idIterator2.next();
				double argument = matrix.getSimilarity(id1, id2);

				/*
				 * Do binary search for bucket
				 */
				int lower = 0;
				int upper = this.range.length - 1;

				while (lower < upper) {
					int middle = lower
							+ (int) Math.round((upper - lower) / 2.0);

					if (lower == upper - 1) {
						if (argument < this.range[upper]) {
							upper = lower;
						} else
							lower = upper;
					} else if (argument < this.range[middle]) {
						upper = middle;
					} else {
						lower = middle;
					}
				}

				Map<Double, Integer> tmp;
				if (this.idToClass.get(id1).equals(this.idToClass.get(id2))) {
					tmp = this.result.get(0);
					this.resultArray[0][lower]++;
				} else {
					tmp = this.result.get(1);
					this.resultArray[1][lower]++;
				}

				tmp.put(this.range[lower], tmp.get(this.range[lower]) + 1);
			}
		}

		return true;
	}

	public List<Map<Double, Integer>> getResult() {
		return this.result;
	}

	/**
	 * @return
	 */
	public Pair<double[], int[][]> getResultAsArray() {
		return Pair.getPair(this.range, this.resultArray);
	}
}
