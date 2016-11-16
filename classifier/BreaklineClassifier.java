/*
 * Java source file for the BreaklineClassifier class. This class contains the main method
 * and is the entry point into the BreaklineClassifier application.
 */

/** @author Trevor O'Connor, @date 9/13/2016 10:56 PM */

package classifier;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class BreaklineClassifier {

	/** Main method. */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		/* For now, assumes only a single input file. Takes this file
		 * in and constructs a reader for it, and initializes an output
		 * file. This file is associated with a PrintWriter, which will
		 * be wrapped in an OutputFormatter to handle the construction
		 * of the output file. */

		/* The call to reader() throws a FileNotFoundException, the MIKEFileReader
		 * constructor throws an IOException. The File call throws a NUllPointer.
		 * PrintWriter throws FileNotFoundException. */

		MIKEFileReader reader;
		File outputFile;
		OutputFormatter out;

		try {
			reader = new MIKEFileReader(reader(args[0]));
			outputFile = new File(getOutputPath(args[0]));
			out = new OutputFormatter(new PrintWriter(outputFile));

			/* Iterates through the input file, parsing a CrossSection at a time.
		 	 * Once each CrossSection is parsed, calculates the desired metrics,
		 	 * and writes them to the output file. */
			CrossSection c = null;
			while (reader.hasNext()) {
				c = reader.getNext();
				c.analyze();
				out.write(c);
			}

			/* After the input is entirely read and the output entirely written,
			 * close all Readers and Writers. */
			reader.close();
			out.close();
		} catch (FileNotFoundException excp) {
			System.err.println("File \"" + args[0] + "\" not found.");
			System.exit(1);
		} catch (IOException excp) {
			System.err.println("IO error on read of \"" + args[0] + "\".");
			System.exit(1);
		} catch (NullPointerException excp) {
			System.err.println("Null pointer error, call Trevor.");
			System.exit(1);
		}

		
	}

	/** Returns a BufferedReader wrapping a FileReader that will read the input. */
	private static BufferedReader reader(String path) throws FileNotFoundException {
		return new BufferedReader(new FileReader(path));
	}

	/** Returns the path for the output file corresponding to the input file with
	 *  path PATH. */
	private static String getOutputPath(String path) {
		// For now, all output files will be in the same directory as the
		// corresponding input file with a ".out" extension instead of ".txt".
		return path.substring(0, path.length()-4)+".out";
	}

}
