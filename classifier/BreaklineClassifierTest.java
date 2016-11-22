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

public class BreaklineClassifierTest {

	/** Output stream for checking error output. */
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

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
		System.setErr(new PrintStream(errContent));
		BreaklineClassifier.main(new String[] {"foobar"});
		String result = errContent.toString();
		assertEquals("File \"foobar\" not found.\n", result);
		System.setErr(null);
	}

}
