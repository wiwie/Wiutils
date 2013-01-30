package utils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import utils.Log.PRIORITY;

public final class ProgressPrinter implements ChangeListener {

	/**
	 * @param progress
	 *            The ProgressPrinter that is to be initialized
	 * @param overallProgress
	 *            The values from this ProgressPrinter are copied into the other
	 *            object
	 * @return The progress object initialized with new values
	 */
	public static ProgressPrinter initWithValuesOf(
			final ProgressPrinter progress,
			final ProgressPrinter overallProgress) {
		progress.ownPos = overallProgress.ownPos;
		progress.linePrefix = overallProgress.linePrefix;
		progress.listener = overallProgress.listener;
		progress.percent = overallProgress.percent;
		progress.optStatus = overallProgress.optStatus;
		progress.printOnNewPercent = overallProgress.printOnNewPercent;
		progress.upperLimit = overallProgress.upperLimit;
		return progress;
	}

	protected Logger log;

	protected long upperLimit;
	private long ownPos, currentPos;
	protected int percent;
	protected String optStatus;
	protected boolean printOnNewPercent;

	protected String linePrefix;
	protected List<ChangeListener> listener;

	protected Map<ProgressPrinter, Long> subProgress;
	protected boolean newLineOnOptStatus = true;

	public ProgressPrinter() {
		this(-1l, false, null);
	}

	public ProgressPrinter(final long upperLimit,
			final boolean printOnNewPercent) {
		this(upperLimit, printOnNewPercent, null);
	}

	/**
	 * @param upperLimit
	 *            A number corresponding to 100% progress.
	 * @param printOnNewPercent
	 *            A boolean indicating, whether to print information on the
	 *            screen if a new percentage is reached.
	 * @param linePrefix
	 *            The prefix that should be put in front of every posted line.
	 */
	public ProgressPrinter(final long upperLimit,
			final boolean printOnNewPercent, final String linePrefix) {
		super();
		this.upperLimit = upperLimit;
		this.listener = new LinkedList<ChangeListener>();
		this.subProgress = new HashMap<ProgressPrinter, Long>();
		this.printOnNewPercent = printOnNewPercent;
		this.linePrefix = linePrefix;
		this.log = LoggerFactory.getLogger(this.getClass());
	}

	/**
	 * The copy constructor of progress printers.
	 * 
	 * @param other
	 *            The progress printer to copy.
	 */
	public ProgressPrinter(final ProgressPrinter other) {
		this(other.upperLimit, other.printOnNewPercent, other.linePrefix);
	}

	public void addChangeListener(final ChangeListener listener) {
		this.listener.add(listener);
	}

	public void addSubProgress(final ProgressPrinter progress,
			final long partOfSubProgress) {
		this.subProgress.put(progress, partOfSubProgress);
		progress.addChangeListener(this);
	}

	public void fireChangeListener() {
		for (final ChangeListener lis : this.listener) {
			lis.stateChanged(new ChangeEvent(this));
		}
	}

	/**
	 * @return the currentPos
	 */
	public long getCurrentPos() {
		return this.currentPos;
	}

	public int getPercent() {
		return this.percent;
	}

	/**
	 * @return
	 */
	public long getUpperLimit() {
		return this.upperLimit;
	}

	public void reset() {
		this.currentPos = 0;
		this.ownPos = 0;
		this.percent = 0;
	}

	public void setLinePrefix(final String linePrefix) {
		this.linePrefix = linePrefix;
	}

	public void setPrintOnNewPercent(final boolean printOnNewPercent) {
		this.printOnNewPercent = printOnNewPercent;
	}

	public void setUpperLimit(final long upperLimit) {
		this.upperLimit = upperLimit;
	}

	public void setNewlineOnOptStatus(final boolean newline) {
		this.newLineOnOptStatus = newline;
	}

	// subprogress changed
	@Override
	public void stateChanged(final ChangeEvent e) {
		this.update(this.ownPos);
	}

	public void update(final long newCurrent) {
		this.update(newCurrent, this.optStatus);
	}

	public void update(final long newCurrent, final String optStatus) {
		if (this.upperLimit == -1l) {
			throw new IllegalArgumentException(
					"This progress printer has not been initialized");
		}
		this.ownPos = newCurrent;

		// integrate progress of subprogresses
		long sumOfSubProgresses = 0L;
		this.currentPos = 0;
		for (final ProgressPrinter subProgress : this.subProgress.keySet()) {
			this.currentPos += (subProgress.currentPos
					/ (double) subProgress.upperLimit * this.subProgress
					.get(subProgress));
			sumOfSubProgresses += this.subProgress.get(subProgress);
		}
		final long remainingPart = this.upperLimit - sumOfSubProgresses;
		if (remainingPart < 0) {
			throw new IllegalArgumentException(
					"Invalid part-sums of sub progresses");
		}
		this.currentPos += this.ownPos;

		final int newPercent = this.currentPos > 0
				? (int) ((double) (this.currentPos) / this.upperLimit * 100.0)
				: 0;

		this.optStatus = optStatus;

		// status update
		if (this.upperLimit > 0 && newPercent > this.percent) {
			if (this.printOnNewPercent) {
				while (newPercent > this.percent) {
					if (((this.percent) % 10 == 0 && this.percent > 0)
							|| (this.optStatus != null && newLineOnOptStatus)) {
						// Log.println(PRIORITY.PROGRESS);
						if (this.linePrefix != null) {
							// Log.print(this.linePrefix, PRIORITY.PROGRESS);
						}
					}
					this.percent++;
					if (this.percent < 100) {
						// Log.print(this.percent
						// + "%"
						// + (this.optStatus != null ? " ["
						// + this.optStatus + "]" : "") + "\t",
						// PRIORITY.PROGRESS);
						log.debug(this.percent
								+ "%"
								+ (this.optStatus != null ? " ["
										+ this.optStatus + "]" : "") + "\t");
					} else {
						// Log.print(
						// this.percent
						// + "%"
						// + (this.optStatus != null ? " ["
						// + this.optStatus + "]" : "")
						// + System.getProperty("line.separator"),
						// PRIORITY.PROGRESS);
						log.debug(this.percent
								+ "%"
								+ (this.optStatus != null ? " ["
										+ this.optStatus + "]" : ""));
					}
				}
			} else {
				this.percent = newPercent;
			}

			this.fireChangeListener();
		}
	}
}
