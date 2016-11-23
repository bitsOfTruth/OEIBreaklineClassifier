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
	public static void main(String[] args) {
		
		/* Takes this file in and constructs a reader for it, and 
		 * initializes an output file. This file is associated with a
		 * PrintWriter, which will be wrapped in an OutputFormatter to
		 * handle the construction of the output file. */

		File path = new File(args[0]);

		/* Check if this File is a directory, and if so, analyzes each file.
		 * Otherwise, analyze the File. */
		if (path.isDirectory()) {
			for (File file : path.listFiles()) {
				run(file);
			}
		} else {
			run(path);
		}
	}

	/** Takes in a single input file and writes the corresponding output file
	 *  to the same directory. */
	private static void run(File pathname) {

		MIKEFileReader reader;
		File outputFile;
		OutputFormatter out;

		try {
			reader = new MIKEFileReader(reader(pathname));
			outputFile = new File(getOutputPath(pathname.getAbsolutePath()));
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

			reader.close();
			out.close();
		} catch (FileNotFoundException excp) {
			System.err.println("File \"" + pathname.getName() + "\" not found.");
		} catch (IOException excp) {
			System.err.println("IO error on read of \"" + pathname.getName() + "\".");
		} catch (NullPointerException excp) {
			System.err.println("Null pointer error, call Trevor.");
		}

	}

	/** Returns a BufferedReader wrapping a FileReader that will read the input. */
	private static BufferedReader reader(File path) throws FileNotFoundException {
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
