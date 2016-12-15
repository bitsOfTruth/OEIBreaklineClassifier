/*
 * This is a Java source file for the CSPRankComparator class, part of the OEIClassifier.
 */

/** @author Trevor O'Connor, @date 12/14/2016 1:45 PM */

package classifier;

import java.util.Comparator;

class CSPRankComparator implements Comparator<CrossSectionPoint> {

	/** Implementation of compare as prescribed by the interface. */
	public int compare(CrossSectionPoint p1, CrossSectionPoint p2) {
		int r1 = p1.getRank();
		int r2 = p2.getRank();
		if (r1 < r2) {
			return -1;
		} else if (r1 == r2) {
			return 0;
		} else {
			return 1;
		}
	}

}
