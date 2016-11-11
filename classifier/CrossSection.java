/*
 * This is a Java source file for the CrossSection class, used in the OEIClassifier.
 */

/** @author Trevor O'Connor, @date 9/7/2016 3:55 PM */

package classifier;

import java.util.ArrayList;
import java.util.Collections;

class CrossSection {

	/** An array containing the CrossSectionPoints making up this CrossSection. */
	private ArrayList<CrossSectionPoint> _points;

	/** The name of the site this CrossSection came from. */
	private String _name;

	/** A float that will function as a unique identifier for this CrossSection
	 *  within the scope of its site. Will be the downstream distance of this
	 *  CrossSection. */
	private float _id;

	/** The thalweg of this CrossSection. */
	private CrossSectionPoint _thalweg;

	/** Constructs a new CrossSection with _name NAME, _id ID, and _points POINTS,
	 *  and the index of the thalweg THALIND. */
	CrossSection(String name, float id, ArrayList<CrossSectionPoint> points, int thalInd) {
		_name = name;
		_id = id;
		_points = points;
		_thalweg = points.get(thalInd);
	}

	/** Returns the name of this CrossSection's site. */
	String getName() {
		return _name;
	}

	/** Returns this CrossSection's _id. */
	float getId() {
		return _id;
	}

	/** Returns the array of points of this CrossSection. May or may not be sorted. */
	ArrayList<CrossSectionPoint> getPoints() {
		return _points;
	}

	/** Returns this CrossSection's thalweg. */
	CrossSectionPoint getThalweg() {
		return _thalweg;
	}

	/** Calculates the slope between the two given points. */
	private static float calculateSlope(CrossSectionPoint p1, CrossSectionPoint p2) {
		return (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
	}

	/** Calculates the inflections of every point in _points. Excludes the first
	 *  and last points. */
	void calculateInflections() {
		float s1;
		float s2;
		int size = _points.size();
		for (int i = 1; i < size-1; i++) {
			s1 = calculateSlope(_points.get(i-1), _points.get(i));
			s2 = calculateSlope(_points.get(i), _points.get(i+1));
			_points.get(i).setInflection(Math.abs(s1-s2));
		}
	}

	/** Performs all analytical calculations on this CrossSection:
	 *  inflections, subsequent ranking, and vertical and horizontal distances
	 *  from the thalweg. */
	void analyze() {
		calculateInflections();
		rank();
		calculateThalwegDistances();
	}

	/** Performs all thalweg distance calculations for this CrossSection. */
	void calculateThalwegDistances() {
		int size = _points.size();
		float thalX = _thalweg.getX();
		float thalY = _thalweg.getY();
		for (int i = 0; i < size; i++) {
			_points.get(i).setHorizDistThal(thalX);
			_points.get(i).setVertDistThal(thalY);
		}
	}

	/** Sorts _points in descending order of their inflections. */
	void rank() {
		ArrayList<CrossSectionPoint> dupPoints = (ArrayList) _points.clone();
		Collections.sort(dupPoints);
		int size = _points.size();
		for (int i = 0; i < size; i++) {
			dupPoints.get(i).setRank(i+1);
		}
		
	}

	/*
	private static CrossSectionPoint[] mergeSort(CrossSectionPoint[] points) {
		if (points.length > 1) {
			CrossSectionPoint[] pArr1 = half(points, true);
			CrossSectionPoint[] pArr2 = half(points, false);
			return merge(mergeSort(pArr1), mergeSort(pArr2));
		} else {
			return points;
		}
	}

	private static CrossSectionPoint[] half(CrossSectionPoint[] points, boolean first) {
		CrossSectionPoint[] result;
		int size;
		int start;
		if (first) {
			size = points.length/2;
			start = 0;
		} else {
			size = points.length - points.length/2;
			start = points.length/2 + 1;
		}
		result = new CrossSectionPoint[size];
		for (int i = start; i < result.length; i++) {
			result[i] = points[i-start];
		}
		return result;
	}

	private static CrossSectionPoint[] merge(CrossSectionPoint[] a1, CrossSectionPoint[] a2) {
		if (a1.length == 0)
			return a2;
		if (a2.length == 0)
			return a1;

		CrossSectionPoint[] result = new CrossSectionPoint[a1.length+a2.length];
		int resultIndex = 0, a1Index = 0, a2Index = 0, comparator = 0;
		CrossSectionPoint p1, p2;
		boolean a1Out = false, a2Out = false;
		while (resultIndex < result.length) {
			if (!a1Out && !a2Out) {
				p1 = a1[a1Index];
				p2 = a2[a2Index];
				comparator = compareInflections(p1, p2);
				if (comparator <= 0) {
					result[resultIndex] = p1;
					a1Index++;
				} else {
					result[resultIndex] = p2;
					a2Index++;
				}
			} else if (a1Out) {
				result[resultIndex] = a2[a2Index];
				a1Index++;
			} else {
				result[resultIndex] = a1[a1Index];
				a1Index++;
			}
			resultIndex++;
		}
		return result;
	}

	private static int compareInflections(CrossSectionPoint p1, CrossSectionPoint p2) {
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
	*/
}
