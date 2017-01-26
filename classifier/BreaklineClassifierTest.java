/*
 * Java source file for testing the BreaklineClassifier class, which is essentially
 * integration testing of the entire application.
 */

/** @author Trevor O'Connor, @date 9/20/2016 9:53 AM */

package classifier;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

public class BreaklineClassifierTest {

	/** Output stream for checking error output. */
	private static ByteArrayOutputStream errContent;

	/** Input test file path. */
	private String filePath = "tests/Yellowjacket1.txt";

	private String dirPath = "tests/testdir";

	@Test
	public void testApp() {
		BreaklineClassifier.main(new String[] {filePath});
	}

	@Test
	public void testDirInput() {
		BreaklineClassifier.main(new String[] {dirPath});
	}

	@Test
	public void testErroneousInput() {
		errContent = new ByteArrayOutputStream();
		System.setErr(new PrintStream(errContent));
		BreaklineClassifier.main(new String[] {"foobar"});
		String result = errContent.toString();
		assertEquals("File \"foobar\" not found.\n", result);
		System.setErr(null);
	}

	/*
	@Test
	public void testMetersToFeet() {
		System.out.println("TESTING METERSTOFEET");
		System.out.println(OutputFormatter.metersToFeet((float) 72.093));
		System.out.println(OutputFormatter.metersToFeet((float) 71.181));
		System.out.println(OutputFormatter.metersToFeet((float) 72.193));
	}

	@Test
	public void testParseOptions() {
		errContent = new ByteArrayOutputStream();
		System.setErr(new PrintStream(errContent));
		
		// The full happy case.
		int[] out = BreaklineClassifier.parseOptions(new String[] {"foobar", "111", "10", "50", "50"});
		assert Arrays.equals(new int[] {10, 50, 50}, out);

		// The partially full happy case.
		out = BreaklineClassifier.parseOptions(new String[] {"foobar", "100", "10"});
		assert Arrays.equals(new int[] {10, -1, -1}, out);

		out = BreaklineClassifier.parseOptions(new String[] {"foobar", "001", "100"});
		assert Arrays.equals(new int[] {-1, -1, 100}, out);

		out = BreaklineClassifier.parseOptions(new String[] {"foobar"});
		assert Arrays.equals(new int[] {-1, -1, -1}, out);

		// Invalid options arg character
		out = BreaklineClassifier.parseOptions(new String[] {"foobar", "012", "50", "50"});
		String result = errContent.toString();
		assertEquals("Invalid character \"2\" in the options string.\n", result);

		// Invalid option
		errContent = new ByteArrayOutputStream();
		System.setErr(new PrintStream(errContent));
		out = BreaklineClassifier.parseOptions(new String[] {"foobar", "010", "a"});
		result = errContent.toString();
		assertEquals("Invalid option argument \"a\" detected. Must be a nonnegative integer.\n", result);

		// Invalid option value
		errContent = new ByteArrayOutputStream();
		System.setErr(new PrintStream(errContent));
		out = BreaklineClassifier.parseOptions(new String[] {"foobar", "011", "45", "-45"});
		result = errContent.toString();
		assertEquals("-45 is negative. A nonnegative integer is required.\n", result);

		System.setErr(null);
	}
	*/

}
