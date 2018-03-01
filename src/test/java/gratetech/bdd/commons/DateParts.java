package gratetech.bdd.commons;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParts {
	
	SimpleDateFormat myf;
	Date myd;
	
	public void setInFormat(String patterntofollow, String when) {
		myf = new SimpleDateFormat(patterntofollow);
		try {
			myd = myf.parse(when);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getOutFormat(SimpleDateFormat opf) {
		return opf.format(myd);
	}

}
