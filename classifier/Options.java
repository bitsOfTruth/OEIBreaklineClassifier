/*
 * This is a Java source file for the Options class, used in the OEIClassifier.
 */

/** @author Trevor O'Connor, @date 1/8/2017 6:42 PM */

package classifier;

class Options {

	/** Length of the options string argument. */
	private static final int OPTIONS_LENGTH = 3;

	/** The number of points to output. */
	private int _n;

	/** The Range of horizontal points to allow. */
	private Range _hRange;

	/** The Range of vertical points to allow. */
	private Range _vRange;

	/** Constructs a new Options object given the input from ARGS. */
	Options(String[] args) {
		if (args.length == 1) {
			setEmpty();
		} else if (args[1].length() != OPTIONS_LENGTH) {
			ErrorHandler.handle(INVALID_OPT_LEN);
		} else {
			parse(args);
		}
	}

	/** Returns this Options' _n. */
	int getN() {
		return _n;
	}

	/** Sets each field to its "empty" value." */
	private void setEmpty() {
		_n = Integer.MAX_VALUE;
		_hRange = new Range();
		_vRange = new Range();
	}

	/** Parse the options in the arguments line ARGS. */
	private void parse(String[] args) {
		boolean[] opts = parseOpt(args[1]);
		int argIndex = 0;
		for (int i = 0; i < OPTIONS_LENGTH; i++) {
			if (i < 1) { // Handle single nonnegative integer case
				argIndex += handleSingle(opts[i], args[argIndex]);
			} else { // Handle the integer range case
				argIndex += handleRange(opts[i], args[argIndex], i);
			}
		}
	}

	/** Takes in a boolean flag and an argument string, parses the argument
	 *  and stores it, and returns 1 if the argument index should be incremented. */
	private int handleSingle(boolean flag, String arg) {
		if (!flag)
			return 0;

		int n;
		try {
			n = Integer.parseInt(arg);
		} catch (NumberFormatException excp) {
			// The argument wasn't a parsable integer
			ErrorHandler.handle(ARG_NOT_INT, arg);
		}

		if (n < 0)
			// The argument was a negative integer.
			ErrorHandler.handle(NEGATIVE_INT, arg);
		_n = n;
		return 1;
	}

	/** Takes in a boolean flag and an argument string, parses the argument
	 *  and stores it, and returns 1 if the argument index should be incremented;
	 *  expects a range in the format "min,max" or "max,min". */
	private int handleRange(boolean flag, String arg, int i) {
		if (!flag)
			return 0;

		try {	
			// Parse the range argument and construct a Range object
			Range range = parseRange(arg);
		} catch (NumberFormatException excp) {
			// One of the elements of the range was not a number
			ErrorHandler.handle(ARG_NOT_FLOAT, excp.getMessage());
		} catch (InvalidRangeException excp) {
			// The range argument was not properly formatted
			ErrorHandler.handle(INVALID_RANGE, arg);
		}

		if (i == 1) {
			_hRange = range;
		} else {
			_vRange = range;
		}

		return 1;
	}

	/** Attempts to read the range argument and construct the proper range object;
	 *  will throw exceptions to handle errors. */
	private Range parseRange(String arg) {
		String[] toks = arg.split(",");

		if (toks.length != 2)
			throw new InvalidRangeException();

		float first, second;

		try {
			first = Float.parseFloat(arg[0]);
		} catch (NumberFormatException excp) {
			first = checkInf(arg[0], excp);
		}

		try {
			second = Float.parseFloat(arg[1]);
		} catch (NumberFormatException excp) {
			second = checkInf(arg[1], excp);
		}

		return new Range(Math.min(first, second), Math.max(first, second));
	}

	/** Takes a string and checks if it is either "inf" (-> Float.POSITIVE_INFINITY)
	 *  or "-inf" (-> Float.NEGATIVE_INFINITY). */
	private float checkInf(String arg) {
		if (arg.equals("inf")) {
			return Float.POSITIVE_INFINITY;
		} else if (arg.equals("-inf")) {
			return Float.NEGATIVE_INFINITY;
		} else {
			throw new NumberFormatException(arg);
		}
	}

	/** Parses the options and returns a boolean array indicating which
	 *  options are in use. */
	private boolean[] parseOpt(String opts) {
		boolean[] result = new boolean[OPTIONS_LENGTH];
		char c;
		for (int i = 0; i < opts.length(); i++) {
			c = opts.charAt(i);
			if (c != '1' && c != '0') {
				ErrorHandler.handle(INVALID_OPT_CHAR, Character.toString(c));
			} else {
				result[i] = c == '1';
			}
		}
		return result;
	}

}
