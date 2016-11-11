/* This Java source file contains unit tests for the CrossSectionPoint class. */

/** @author Trevor O'Connor, @date 9/7/16 3:34 PM */

package classifier;

import org.junit.Test;
import static org.junit.Assert.*;

public class CrossSectionPointTest {

	@Test
	public void testCSP() {
		CrossSectionPoint p = new CrossSectionPoint(1.0f, 5.0f);
		assertEquals(p.getX(), 1.0f, 0.0001f);
		assertEquals(p.getY(), 5.0f, 0.0001f);
	}
}
