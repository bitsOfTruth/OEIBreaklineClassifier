/* 
 * Java source file for the MIKEFileReader class.
 */

/** @author Trevor O'Connor, @date 9/12/2016 1:34 PM */

package classifier;

import java.io.IOException;
import java.lang.NumberFormatException;
import java.io.BufferedReader;
import java.util.regex.Pattern;
import java.util.ArrayList;

class MIKEFileReader {

	/** This MIKEFileReader's reader. */
	private BufferedReader _reader;

	/** True if there is another breakline to be parsed. */
	private boolean _hasNext;

	/** Constructs a new MIKEFileReader with the reader READER containing
	 *  the input file. */
	MIKEFileReader(BufferedReader reader) throws IOException {
		_reader = reader;
		_hasNext = true;
		_reader.readLine();
	}

	/** Returns true if this MFR has another CrossSection to be read and
	 *  returned and false otherwise. */
	boolean hasNext() {
		return _hasNext;
	}

	/** Returns the next CrossSection in the file. */
	CrossSection getNext() throws IOException {
		String name = _reader.readLine(); /* This will give the site name. */
		float id = extractId(_reader.readLine());
		skipLines(20); /* Can skip because of defined file format, unneeded information */
		int size = getSize(_reader.readLine());
		ArrayList<CrossSectionPoint> points = new ArrayList<CrossSectionPoint>(size);

		int i = 0; 
		CrossSectionPoint p, thalCheck;
		CrossSectionPoint thalweg = null;
		while (i < size) {
			p = extractPoint(_reader.readLine());
			/* Check for duplicate points */
			if ((i > 0 && p.getY() != points.get(i-1).getY()) || i == 0 ) {
				points.add(i, p);
				i++;
			} else {
				size--;
			}

			thalCheck = points.get(points.size()-1);

			/* Check if the thalweg needs to be updated. */
			if (thalweg == null || thalCheck.getY() < thalweg.getY())
				thalweg = thalCheck;

		}
		points.trimToSize();

		skipLines(3);
		_hasNext = _reader.readLine() != null;
	
		return new CrossSection(name, id, points, points.indexOf(thalweg));
	}

	/** Extracts the id number of the CrossSection from the given line.
	 *  Behavior is undefined if the correct line is not given. */
	private static float extractId(String line) {
		String[] strArr = line.split("\\s+");
		String str;
		for (int i = 0; i < strArr.length; i++) {
			str = strArr[i];
			if (!str.equals("")) {
				try {
					return Float.parseFloat(str);
				} catch (NumberFormatException excp) {
					ErrorHandler.handle(CS_ID_NAN);
				}
			}
		}
		return 0.0f;
	}

	/** Extracts the size of the CrossSection (number of points) from the
	 *  given line. Behavior is undefined if the correct line is not given. */
	private static int getSize(String line) {
		String[] strArr = line.split("\\s+");
		try {
			return Integer.parseInt(strArr[1]);
		} catch (NumberFormatException excp) {
			ErrorHandler.handle(CS_SIZE_NAN);
		}

		return 0;
	}

	/** Extracts the data for a CrossSectionPoint, constructs it, and returns it. */
	private static CrossSectionPoint extractPoint(String line) {
		String[] strArr = line.split("\\s+");
		try {
			return new CrossSectionPoint(Float.parseFloat(strArr[1]),
					     Float.parseFloat(strArr[2]));
		} catch (NumberFormatException excp) {
			ErrorHandler.handle(PT_HAS_NAN);
		}

		return null;
	}

	/** Skips N lines of the file wrapped by _reader. */
	private void skipLines(int n) throws IOException {
		for (int i = 0; i < n; i++) {
			_reader.readLine();
		}
	}

	/** Closes the BufferedReader associated with this MIKEFileReader. */
	void close() throws IOException {
		_reader.close();
	}

}
