/*
 * Java source file for tests for the MIKEFileReader class.
 */

/** @author Trevor O'Connor, @date 9/12/2016 8:24 PM */

package classifier;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MIKEFileReaderTest {

	/* Test variables */
	private static String filePath = "tests/MIKEreadertest.txt";

	// Currently not testing anything
	@Test
	public void testBasics() throws FileNotFoundException, IOException {
		BufferedReader r = new BufferedReader(new FileReader(filePath));
		MIKEFileReader reader = new MIKEFileReader(r, -1, -1);
		CrossSection c = reader.getNext();
		reader.close();
		/*
		assertEquals(c.getName(), "Yellowjacket Creek");
		assertEquals(c.getId(), 1645.920f, 0.0001f);
		assertEquals(c.getPoints().size(), 169);
		*/
	}

	@Test
	public void testFullExecution() throws FileNotFoundException, IOException {
		BufferedReader r = new BufferedReader(new FileReader(filePath));
		MIKEFileReader reader = new MIKEFileReader(r, -1, -1);
		CrossSection[] csArr = new CrossSection[5];
		CrossSection c;
		int i = 0;
		while (reader.hasNext()) {
			csArr[i] = reader.getNext();
			csArr[i].calculateInflections();
			csArr[i].rank();
			csArr[i].calculateThalwegDistances();
			assert CrossSectionTest.isCorrectRanking(csArr[i]);
			i++;
		}
		assertEquals(csArr[0].getName(), "Yellowjacket Creek");
		assertEquals(csArr[1].getName(), "Yellowjacket Creek");
		assertEquals(csArr[2].getName(), "Yellowjacket Creek");
		assertEquals(csArr[3].getName(), "Yellowjacket Creek");
		assertEquals(csArr[4].getName(), "Yellowjacket Creek");
		assertEquals(csArr[0].getId(), 106.68f, 0.0f);
		assertEquals(csArr[1].getId(), 411.48f, 0.0f);
		assertEquals(csArr[2].getId(), 685.8f, 0.0f);
		assertEquals(csArr[3].getId(), 1813.56f, 0.0f);
		assertEquals(csArr[4].getId(), 3002.28f, 0.0f);
		assertEquals(csArr[0].getPoints().size(), 131);
		assertEquals(csArr[1].getPoints().size(), 100);
		assertEquals(csArr[2].getPoints().size(), 115);
		assertEquals(csArr[3].getPoints().size(), 156);
		assertEquals(csArr[4].getPoints().size(), 162);
		reader.close();
	}

	/** Completely prints out the contents of a CrossSection in their current order. */
	private static void testDump(CrossSection c) {
		ArrayList<CrossSectionPoint> points = c.getPoints();
		System.out.println("Site: " + c.getName());
		System.out.println("Downstream distance (m): " + c.getId() + "\n");
		for (int i = 0; i < points.size(); i++) {
			CrossSectionPoint p = points.get(i);
			System.out.println(p.getX() + "         " + p.getY() + "         " + p.getInflection());
		}
	}

	// The below tests were written for private helper methods, and will fail under
	// the intended design of the class.

	/*
	@Test
	public void testIdExtraction() {
		String in = "              15.240\n";
		assertEquals(15.240f, MIKEFileReader.extractId(in), 0.0001f);
	}

	@Test
	public void testExtractPoint() {
		String in = "     0.000   165.687     1.000     <#1>     0     0.000     0\n";
		CrossSectionPoint p = MIKEFileReader.extractPoint(in);
		assertEquals(0.000f, p.getX(), 0.0001f);
		assertEquals(165.687f, p.getY(), 0.0001f);
		assertEquals(0.0f, p.getInflection(), 0.0001f);
	}

	@Test
	public void testSizeExtraction() {
		String in = "PROFILE        169\n";
		assertEquals(169, MIKEFileReader.getSize(in));
	}
	*/
}
