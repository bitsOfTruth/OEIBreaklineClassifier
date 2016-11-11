/*
 * Java source file for the OutputFormatter class. This class takes in a PrintWriter
 * and has methods for writing the analyzed and formatted data of a CrossSection.
 */

/** @author Trevor O'Connor, @date 9/17/2016 10:43 AM */

package classifier;

import java.util.ArrayList;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.math.RoundingMode;

class OutputFormatter {

	/** The PrintWriter to which this OutputFormatter should write all output. */
	private PrintWriter _writer;

	/** The DecimalFormat object for rounding values for String output. */
	private DecimalFormat _df;

	/** Constructs a new OutputFormatter with _writer WRITER. */
	OutputFormatter(PrintWriter writer) {
		_writer = writer;
		_df = new DecimalFormat("#.###");
		_df.setRoundingMode(RoundingMode.HALF_UP);
		writeHeader();
	}

	/** Writes the relevant data of the CrossSection C to _writer. */
	void write(CrossSection c) {
		ArrayList<CrossSectionPoint> points = c.getPoints();
		String name = c.getName();
		float id = c.getId();
		int size = points.size();
		for (int i = 0; i < size; i++) {
			writePoint(points.get(i), name, id);
		}
	}

	/** Writes the header of a CrossSection to _writer, given the name NAME
	 *  and the id ID. */
	private void writeHeader() {
		_writer.println("       River          Location     x        y       rank    x-dist   y-dist ");
		_writer.println("-------------------- ---------- -------- -------- -------- -------- --------");
	}

	/** Writes the data of a single CrossSectionPoint P with name NAME and
	 *  id ID to _writer. */
	private void writePoint(CrossSectionPoint p, String name, float id) {
		String idStr = Float.toString(id);
		String x = Float.toString(p.getX());
		String y = Float.toString(p.getY());
		String rank = Integer.toString(p.getRank());
		String hDist = _df.format(p.getHorizDistThal());
		String vDist = _df.format(p.getVertDistThal());
		name = name + getSpaces(20-name.length());
		idStr = idStr + getSpaces(10-idStr.length());
		x = x + getSpaces(8-x.length());
		y = y + getSpaces(8-y.length());
		rank = rank + getSpaces(8-rank.length());
		hDist = hDist + getSpaces(8-hDist.length());
		vDist = vDist + getSpaces(8-vDist.length());
		_writer.println(name+" "+idStr+" "+x+" "+y+" "+rank+" "+hDist+" "+vDist);
	}

	/** Returns a String of LEN whitespaces to fill the remaining space on a
	 *  particular cell in the output file to keep things aligned. */
	private String getSpaces(int len) {
		String result = "";
		for (int i = 0; i < len; i++) {
			result = result + " ";
		}
		return result;
	}

	/** Closes the _writer for this PrintWriter. Should only be called when after
	 *  an input file has been completely parsed. */
	void close() {
		_writer.close();
	}

}