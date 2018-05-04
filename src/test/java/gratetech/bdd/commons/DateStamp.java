package gratetech.bdd.commons;

import gratetech.bdd.steps.CancelBookings;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;

import jline.internal.Log;

public class DateStamp {
	public static Logger log = Logger.getLogger(DateStamp.class);
	private Locale theLocaleToUse = Locale.UK;
	
	public void setLocaleToUse(Locale locale) {
		this.theLocaleToUse = locale;
	}
	
	public String getReportDateFormat() {
		return new SimpleDateFormat("dd MMMM yyyy, HH:mm:ss", theLocaleToUse).format(new Date());
	}
	
	public String getSimpleDateFormat() {
		return new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss", theLocaleToUse).format(new Date());
	}
	
	public String getFileDateFormat() {
		return new SimpleDateFormat("yyyyMMdd_HHmmss", theLocaleToUse).format(new Date());
	}
	
	public String getRanDateFormat() {
		return new SimpleDateFormat("yyyyMMddHHmmss", theLocaleToUse).format(new Date());
	}
	
	public String getFileDayFormat() {
		return new SimpleDateFormat("yyyyMMdd", theLocaleToUse).format(new Date());
	}
	
	public String getFileTimeFormat() {
		return new SimpleDateFormat("HHmmss", theLocaleToUse).format(new Date());
	}
	
	public String getDateFormatFromString (String inputpattern, String outputpattern, String somedatearg ) {
		
		SimpleDateFormat myf = new SimpleDateFormat(inputpattern);
		SimpleDateFormat mym = new SimpleDateFormat(outputpattern);
		Date dtx;
		try {
			dtx = myf.parse(somedatearg);
			return mym.format(dtx);
		} catch (ParseException e) {
			Log.error("Date format parsing error");
			e.printStackTrace();
		}
		return "";
		
	}

}
