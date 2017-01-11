/*
 * This is a Java source file for the Range class, used in the OEIClassifier.
 */

/** @author Trevor O'Connor, @date 1/8/2017 6:23 PM */

package classifier;

class Range {

	/** This Range's minimum value. */
	private float _min;

	/** This Range's maximum value. */
	private float _max;

	/** Constructs a new Range object. */
	Range(float min, float max) {
		_min = min;
		_max = max;
	}

	/** Constructs a new Range object with no limits. */
	Range() {
		this(Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY);
	}

	/** Returns true if the given value N falls within this Range and false otherwise. */
	boolean inRange(float n) {
		return n >= _min && n <= _max;
	}

}
