package gratetech.bdd.interfaces;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public interface IWriteCSV {

	public void writeLine(Writer w, List<String> values);
	public void writeLine(Writer w, List<String> values, char separators);
	public void writeLine(Writer w, List<String> values, char separators, char customQuote);
	

}
