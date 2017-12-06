package gratetech.bdd.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class WriteCsv {

	 private static final char DEFAULT_SEPARATOR = ',';
    
     	static FileWriter writer = null;

     	public static void configureWriter(String file) { 
     		if (writer == null) {
     			try {
     				writer =  new FileWriter(file);
     			} catch (IOException e) {
     				// TODO Auto-generated catch block
     				e.printStackTrace();
     			} 
     		}
     	}
     	
     	public static void closeWriter() {
     		 try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
             
     	}
     	
     	public static void writeRawLine(String value) {
     		try {
				writer.append(value);
				writer.append("\r\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
     		
     	}
     	
     	public static void writeRawString(String value) {
     		try {
				writer.append(value);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
     		
     	}
     	public static void writeLine(List<String> values)  {
	        try {
				writeLine(writer, values, DEFAULT_SEPARATOR, ' ');
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
     	
	    public static void writeLine(Writer w, List<String> values)  {
	        try {
				writeLine(w, values, DEFAULT_SEPARATOR, ' ');
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    public static void writeLine(Writer w, List<String> values, char separators)  {
	        try {
				writeLine(w, values, separators, ' ');
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    //https://tools.ietf.org/html/rfc4180
	    private static String followCVSformat(String value) {

	        String result = value;
	        if (result.contains("\"")) {
	            result = result.replace("\"", "\"\"");
	        }
	        return result;

	    }
	    
	    public static void writeLine(Writer w, List<String> values, char separators, char customQuote) throws IOException {

	        boolean first = true;

	        //default customQuote is empty

	        if (separators == ' ') {
	            separators = DEFAULT_SEPARATOR;
	        }

	        StringBuilder sb = new StringBuilder();
	        for (String value : values) {
	            if (!first) {
	                sb.append(separators);
	            }
	            if (customQuote == ' ') {
	                sb.append(followCVSformat(value));
	            } else {
	                sb.append(customQuote).append(followCVSformat(value)).append(customQuote);
	            }

	            first = false;
	        }
	        sb.append("\n");
	        w.append(sb.toString());
	    }	    
}
