/*
 * This is a Java source file for the ErrorHandler class, which handles all
 * errors for the OEIClassifier.
 */

/** @author Trevor O'Connor, @date 1/9/2017 12:16 PM */

package classifier;

class ErrorHandler {

	/** Responds appropriately to the error indicated by CODE. */
	static void handle(ErrorCode code) {
		String errMsg;
		switch (code) {
			case INVALID_ARG_LEN:
				errMsg = "Invalid number of arguments. Check the format and number of arguments.";
				break;
			case INVALID_OPT_LEN:
				errMsg = "Invalid length of the options string: must be 3 binary digits.";
				break;
			case CS_ID_NAN:
				errMsg = "The CrossSection ID isn't a number, the file's format may be incorrect.";
				break;
			case CS_SIZE_NAN:
				errMsg = "The CrossSection size isn't a number, the file's format may be incorrect.";
				break;
			case PT_HAS_NAN:
				errMsg = "One of the points contains non-numerical character the file's format may be incorrect.";
				break;
			default:
				errMsg = "Unspecified error.";
				break;
		}
		System.err.println(errMsg);
		System.exit(1);
	}
			
	/** Responds appropriately to the error indicated by CODE; if MSG is empty
	 *  it is not needed. */
	static void handle(ErrorCode code, String msg) {
		String errMsg;
		switch (code) {
			case INVALID_OPT_CHAR:
				errMsg = "Invalid character \"" + msg + "\" in the options string.";
				break;
			case INVALID_RANGE:
				errMsg = "The range argument \"" + msg + "\" was improperly formatted.";
				break;
			case NEGATIVE_INT:
				errMsg = msg + " is negative. A nonnegative integer is required.";
				break;
			case ARG_NOT_INT:
				errMsg = "Invalid option argument \"" + msg + "\" detected. Must be a nonnegative integer.";
				break;
			case ARG_NOT_FLOAT:
				errMsg = msg + " is not a valid decimal number.";
				break;
			case FILE_NOT_FOUND:
				errMsg = "File \"" + msg + "\" not found.";
				break;
			case IO_ERROR:
				errMsg = "IO error on read of \"" + msg + "\".";
				break;
			default:
				errMsg = "Unspecified error.";
				break;
		}
		System.err.println(errMsg);
		System.exit(1);
	}
}
