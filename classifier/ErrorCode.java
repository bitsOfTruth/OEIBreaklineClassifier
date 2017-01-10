/*
 * This is a Java source file for the ErrorCode enum, used to identify
 * errors in the OEIClassifier.
 */

/** @author Trevor O'Connor, @date 1/9/2017, 12:37 PM */

package classifier;

enum ErrorCode {
	INVALID_ARG_LEN, // Wrong number of total arguments on command line
	INVALID_OPT_LEN, // Wrong length of the binary options string
	INVALID_OPT_CHAR, // Non-binary character in the binary options string
	INVALID_RANGE, // A range argument is formatted incorrectly
	NEGATIVE_INT, // There is a negative integer where only a non-negative is valid
	ARG_NOT_INT, // There is a non integer argument where an integer is called for
	ARG_NOT_FLOAT, // There is a non float element in a range argument
	FILE_NOT_FOUND, // The pathname passed does not point to a valid file
	IO_ERROR, // There was an IOException thrown on the reading of a file
	CS_ID_NAN, // A CrossSection ID isn't a number
	CS_SIZE_NAN, // A CrossSection size isn't a number
	PT_HAS_NAN // A point contains non-numerical character(s)
}
