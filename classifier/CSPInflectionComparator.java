/*
 * This is a Java source file for the CSPInflectionComparator class, part of the
 * OEIClassifier.
 */

/** @author Trevor O'Connor, @date 12/14/2016 3:50 PM */

package classifier;

import java.util.Comparator;

class CSPInflectionComparator implements Comparator<CrossSectionPoint> {

	/** Implementation of compare as prescribed by the interface. */
	public int compare(CrossSectionPoint p1, CrossSectionPoint p2) {
		float i1 = p1.getInflection();
		float i2 = p2.getInflection();
		if (i1 > i2) {
			return -1;
		} else if (i1 == i2) {
			return 0;
		} else {
			return 1;
		}
	}

}
