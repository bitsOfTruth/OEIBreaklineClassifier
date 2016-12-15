/*
 * This is a Java source file for the CSPXComparator class, part of the OEIClassifier.
 */

/** @author Trevor O'Connor, @date 12/14/3:35 PM */

package classifier;

import java.util.Comparator;

class CSPXComparator implements Comparator<CrossSectionPoint> {

	/** Implementation of compare as prescribed by the interface. */
	public int compare(CrossSectionPoint p1, CrossSectionPoint p2) {
		float x1 = p1.getX();
		float x2 = p2.getX();
		if (x1 < x2) {
			return -1;
		} else if (x1 == x2) {
			return 0;
		} else {
			return 1;
		}
	}

}
