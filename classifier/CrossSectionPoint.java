/* This is a Java source file for the CrossSectionPoint class, used in the
 * CrossSection class for the OEIClassifier. */

/** @author Trevor O'Connor */

package classifier;

import java.text.DecimalFormat;
import java.math.RoundingMode;

class CrossSectionPoint implements Comparable<CrossSectionPoint> {

	/** The x coordinate for this CrossSectionPoint. */
	private float _x;

	/** The y coordinate for this CrossSectionPoint. */
	private float _y;

	/** The inflection of this CrossSectionPoint. */
	private float _inflection;

	/** The rank of this CrossSectionPoint within its CrossSection. */
	private int _rank;

	/** The horizontal distance from the thalweg to this point.
	 *  If the value is negative, this point has a lower x-value
	 *  than the thalweg. If this value is 0, this point is the
	 *  thalweg. */
	private float _horizDistThal;

	/** The vertical distance from the thalweg to this point. Should
	 *  always have positive or 0 value. If this value is 0, this point
	 *  is the thalweg. */
	private float _vertDistThal;

	/** The DecimalFormat object for rounding values for String output. */
	private DecimalFormat _df;

	/** Constructs a new CrossSectionPoint with x-value X and y-value Y. */
	CrossSectionPoint(float x, float y) {
		_x = x;
		_y = y;
		_inflection = 0.0f;
		_rank = 0;
		_vertDistThal = 0.0f;
		_horizDistThal = 0.0f;
		_df = new DecimalFormat("#.###");
		_df.setRoundingMode(RoundingMode.HALF_UP);
	}

	/** Returns this CrossSectionPoint's x-value. */
	float getX() {
		return _x;
	}

	/** Returns this CrossSectionPoint's y-value. */
	float getY() {
		return _y;
	}

	/** Returns this CrossSectionPoint's inflection. */
	float getInflection() {
		return _inflection;
	}

	/** Set's this CrossSectionPoint's inflection to be INFLECTION. */
	void setInflection(float inflection) {
		_inflection = inflection;
	}

	/** Returns this CrossSectionPoint's rank. */
	int getRank() {
		return _rank;
	}

	/** Set's this CrossSectionPoint's rank to be RANK. */
	void setRank(int rank) {
		_rank = rank;
	}

	/** Returns this CrossSectionPoint's horizontal distance to the thalweg. */
	float getHorizDistThal() {
		return _horizDistThal;
	}

	/** Returns this CrossSectionPoint's vertical distance to the thalweg. */
	float getVertDistThal() {
		return _vertDistThal;
	}

	/** Calculates and sets this point's horizontal distance from the given
	 *  x-value of the thalweg X. */
	void setHorizDistThal(float x) {
		_horizDistThal = _x - x;
	}

	/** Calculates and sets this point's vertical distance from the given
	 *  y-value of the thalweg Y. */
	void setVertDistThal(float y) {
		_vertDistThal = _y - y;
	}

	/** Implements the compareTo method for the Comparable interface. */
	public int compareTo(CrossSectionPoint p) {
		if (_inflection > p.getInflection()) {
			return -1;
		} else if (_inflection == p.getInflection()) {
			return 0;
		} else {
			return 1;
		}
	}

	/** Returns a String representation of this CrossSectionPoint. For testing only. */
	String getStringRep() {
		DecimalFormat df = new DecimalFormat("#.###");
		df.setRoundingMode(RoundingMode.HALF_UP);
		String space = "      ";
		String x = Float.toString(_x);
		String y = Float.toString(_y);
		// String infl = Float.toString(_inflection); // TESTING ONLY
		String rank = Integer.toString(_rank);
		String hDist = _df.format(_horizDistThal);
		String vDist = _df.format(_vertDistThal);
		return x + space + y + space + rank + space + hDist + space + vDist;
	}
}
