/* This Java source file contains unit tests for the CrossSection class. */

/** @author Trevor O'Connor, @date 9/8/2016 11:38 AM */

package classifier;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

public class CrossSectionTest {

	/* Testing variables */
	private static CrossSectionPoint[] rankTestArr = { new CrossSectionPoint(0.0f, 1.0f),
							   new CrossSectionPoint(1.0f, 2.0f),
							   new CrossSectionPoint(2.0f, 3.0f),
							   new CrossSectionPoint(3.0f, 4.0f),
							   new CrossSectionPoint(4.0f, 5.0f) };

	private static CrossSectionPoint[] calcTestArr = { new CrossSectionPoint(0.0f, 10.0f),
							   new CrossSectionPoint(1.0f, 9.5f),
							   new CrossSectionPoint(2.0f, 9.0f),
							   new CrossSectionPoint(3.0f, 8.5f),
							   new CrossSectionPoint(4.0f, 7.0f),
							   new CrossSectionPoint(4.5f, 5.0f),
							   new CrossSectionPoint(5.0f, 3.0f),
							   new CrossSectionPoint(5.5f, 5.0f),
							   new CrossSectionPoint(6.0f, 7.0f),
							   new CrossSectionPoint(7.0f, 8.5f),
							   new CrossSectionPoint(8.0f, 9.0f),
							   new CrossSectionPoint(9.0f, 9.5f),
							   new CrossSectionPoint(10.0f, 10.0f) };

	@Test
	public void testRanking() {
		CrossSection c = new CrossSection("test", 1.0f, genRankArr(), 0);
		System.out.println("BEFORE");
		testDump(c);
		c.rank();
		System.out.println("AFTER");
		testDump(c);
		assert isCorrectRanking(c);
	}

	@Test
	public void testCalculationAndRanking() {
		CrossSection c = new CrossSection("test", 2.0f, new ArrayList<CrossSectionPoint>(Arrays.asList(calcTestArr)), 0);
		c.calculateInflections();
		System.out.println("BEFORE");
		testDump(c);
		c.rank();
		System.out.println("AFTER");
		testDump(c);
		assert isCorrectRanking(c);
	}

	/** Completely prints out the contents of a CrossSection in their current order. */
	static void testDump(CrossSection c) {
		ArrayList<CrossSectionPoint> points = c.getPoints();
		for (int i = 0; i < points.size(); i++) {
			System.out.println(points.get(i).getStringRep());
		}
	}

	/** Generate manual points. */
	private static ArrayList<CrossSectionPoint> genRankArr() {
		ArrayList<CrossSectionPoint> result = new ArrayList<CrossSectionPoint>(Arrays.asList(rankTestArr));
		for (int i = 0; i < result.size(); i++) {
			result.get(i).setInflection(getFloat());
		}
		return result;
	}

	/** Generates a random float f between 0 and 100. */
	private static float getFloat() {
		return (float) (Math.random() * 100.0);
	}

	/** Checks that the CrossSection C has its points stored in descending order
	 *  of inflection. */
	static boolean isCorrectRanking(CrossSection c) {
		ArrayList<CrossSectionPoint> points = c.getPoints();
		Collections.sort(points);
		for (int i = 0; i < points.size(); i++) {
			if (points.get(i).getRank() != i+1)
				return false;
		}
		return true;
	}

	/** Returns true if P1 has inflection greater than or equal to that of P2 and
	 *  false otherwise. */
	private static boolean comparePoints(CrossSectionPoint p1, CrossSectionPoint p2) {
		return p1.getInflection() >= p2.getInflection();
	}
}
