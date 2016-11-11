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

public class BreaklineClassifierTest {

	/** Input test file path. */
	private String filePath = "input/Yellowjacket1.txt";

	@Test
	public void testApp() throws FileNotFoundException, IOException {
		BreaklineClassifier.main(new String[] {filePath});
	}

}
