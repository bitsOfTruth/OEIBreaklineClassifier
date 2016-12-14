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
import java.lang.NumberFormatException;

public class BreaklineClassifier {

	/** Length of the options string argument. */
	private static final int OPTIONS_LENGTH = 3;

	/** Main method. */
	public static void main(String[] args) {
		
		int[] opt;

		/* Check the format of the arguments and format accordingly. */
		if (!argLenIsValid(args)) {
			System.err.println("Invalid number of arguments. Check the format and number of arguments.");
			System.exit(1);
		}

		opt = parseOptions(args);
		
		/* Takes this file in and constructs a reader for it, and 
		 * initializes an output file. This file is associated with a
		 * PrintWriter, which will be wrapped in an OutputFormatter to
		 * handle the construction of the output file. */

		File path = new File(args[0]);

		/* Check if this File is a directory, and if so, analyzes each file.
		 * Otherwise, analyze the File. */
		if (path.isDirectory()) {
			for (File file : path.listFiles()) {
				run(file, opt);
			}
		} else {
			run(path, opt);
		}
	}

	/** Takes in a String array and determines if it is a valid argument array
	 *  for the BreaklineClassifier. */
	private static boolean argLenIsValid(String[] args) {
		return args.length == 1 || (args.length >= 3 && args.length < 3 + OPTIONS_LENGTH);
	}

	/** Takes in a String array ARGS and determines if it has a valid options argument,
	 *  and if the argument array contains the according number of arguments following
	 *  the options. */
	private static int[] parseOptions(String[] args) {

		int[] result = new int[OPTIONS_LENGTH];
		if (args.length == 1) {
			for (int i = 0; i < OPTIONS_LENGTH; i++) {
				result[i] = -1;
			}
			return result;
		}

		/* Extract the options string argument from the arguments string
		 * and check that it is the correct length. */
		String opt = args[1];
		if (opt.length() != OPTIONS_LENGTH) {
			System.err.println("Invalid length of the options string.");
			System.exit(1);
		}

		/* Iterate through the options string, checking for invalid characters as
		 * we go. Set each value of the result array. */
		char c;
		int argIndex = 2;
		for (int i = 0; i < OPTIONS_LENGTH; i++) {
			c = opt.charAt(i);
			if (c != '1' && c != '0') {
				System.err.println("Invalid character \"" + Character.toString(c) + "\" in the options string.");
				System.exit(1);
			} else if (c == '1') {
				try {
					result[i] = checkArg(Integer.parseInt(args[argIndex]));
					argIndex++;
				} catch (NumberFormatException excp) {
					System.err.println("Invalid option argument \"" + args[argIndex] + "\" detected. Must be a nonnegative integer.");
					System.exit(1);
				}
			} else {
				result[i] = -1;
			}
		}

		return result;
	}

	/** Takes an integer and returns it if it is nonnegative, otherwise prints an error
	 *  message and exits. */
	private static int checkArg(int i) {
		if (i < 0) {
			System.err.println(Integer.toString(i) + " is negative. A nonnegative integer is required.");
			System.exit(1);
		}
		return i;
	}

	/** Takes in a single input file and writes the corresponding output file
	 *  to the same directory. */
	private static void run(File pathname, int[] options) {

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
				c.analyze(options);
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
