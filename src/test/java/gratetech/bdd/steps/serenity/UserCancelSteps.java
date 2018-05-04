package gratetech.bdd.steps.serenity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import gratetech.bdd.commons.CommonConstants;
import gratetech.bdd.pages.BookingHistory;
import gratetech.bdd.pages.CancelBookingConfirmation;
import gratetech.bdd.pages.LoggedIn;
import gratetech.bdd.pages.MyJourneyDetails;
import net.thucydides.core.annotations.Step;

public class UserCancelSteps {
	public static Logger log = Logger.getLogger(UserCancelSteps.class);
	
	// pages to control
	private LoggedIn lgIn;
	private BookingHistory bkh;
	private MyJourneyDetails myj;
	private CancelBookingConfirmation mycancel;
	
	// TODO: these are maps for the page......they will need to be cleaned!
	private List<String> bookingsToRemove = new ArrayList<String>();
	private Map<String, String> ticketActive = new HashMap<String, String>();
	private Map<String, WebElement> amendLink = new HashMap<String, WebElement>();
	
	@Step
	public void hasSelectedMyBookings() {
		log.info("hasSelectedMyBookings");
		lgIn.selectMyBookings();
	}
	
	@Step
	public void moveToNextPage() {
		// TODO: should only do this if there IS a page to move to
		bkh.clickNextPage();
	}
	
	@Step 
	public boolean findBookingsOnPage() {
		log.info("findBookingsOnPage");	
		boolean activefound = false;
		bookingsToRemove.clear();
		bookingsToRemove.addAll(bkh.findAllBookingsOnPage());
		
		List<String> tmp = new ArrayList<String>();
		tmp.addAll(bkh.findBookingAge());
		List<WebElement> we = new ArrayList<WebElement>();
		we.addAll(bkh.findOpenAmendLinks());
		
		log.info("Booking ID size is " + bookingsToRemove.size());
		log.info("Bookings Status size is " + tmp.size());
		log.info("elements size is " + we.size());
		for (int x = 0; x < bookingsToRemove.size(); x++) {
			String s = bookingsToRemove.get(x);
			if (tmp.get(x).contains("ACTIVE")) {
				activefound = true;
			}
			ticketActive.put(s,tmp.get(x));
			log.info("Active Ticket for key " + s + " is " + ticketActive.get(s));
			amendLink.put(s,we.get(x));
		}
		return activefound;
	
	}
	
	@Step 
	public boolean findBookingsOnPage(String bookingtofind) {
		log.info("findBookingsOnPage for " + bookingtofind);	
		boolean found = false;
		bookingsToRemove.clear();
		bookingsToRemove.addAll(bkh.findAllBookingsOnPage());
		
		List<String> tmp = new ArrayList<String>();
		tmp.addAll(bkh.findBookingAge());
		List<WebElement> we = new ArrayList<WebElement>();
		we.addAll(bkh.findOpenAmendLinks());
		
		log.info("Booking ID size is " + bookingsToRemove.size());
		log.info("Bookings Status size is " + tmp.size());
		log.info("elements size is " + we.size());
		for (int x = 0; x < bookingsToRemove.size(); x++) {
			String s = bookingsToRemove.get(x);
			if (s.contains(bookingtofind)) {
				log.info("found booking " + bookingtofind);
				found = true;
			}
			ticketActive.put(s,tmp.get(x));
			log.info("Active Ticket for key " + s + " is " + ticketActive.get(s));
			amendLink.put(s,we.get(x));
		}
		return found;
	}
	
	@Step
	public String cancelBooking(String booking) {
		for (int x = 0; x < bookingsToRemove.size(); x++) {
			String status = ticketActive.get(bookingsToRemove.get(x));
			WebElement ele = amendLink.get(bookingsToRemove.get(x));
			if (status.contains("ACTIVE") && bookingsToRemove.get(x).contains(booking)) {
				log.info("attempting to Amend/Cancel " + bookingsToRemove.get(x));
				bkh.openAmend(ele);
				return booking;
			}
		}
		return "";
	}
	
	@Step
	public String cancelFirstValid() {
		// amend/cancel link --> Cancel --> pop up confirm
		for (int x = 0; x < bookingsToRemove.size(); x++) {
			String status = ticketActive.get(bookingsToRemove.get(x));
			WebElement ele = amendLink.get(bookingsToRemove.get(x));
			if (status.contains("ACTIVE")) {
				log.info("attempting to Amend/Cancel " + bookingsToRemove.get(x));
				bkh.openAmend(ele);
				return bookingsToRemove.get(x);
			}
		}
		return "";
	}
	
	@Step
	public void completeTheCancel() {
		log.info("Completing Cancel");
		myj.setImplicitTimeout(CommonConstants.PAGETIMEOUT, TimeUnit.SECONDS);
		myj.cancelBooking();
		myj.acceptCanceldlg();
		myj.resetImplicitTimeout();
	}
	
	@Step
	public String checkCancelSuccess(String booking) {
		mycancel.setImplicitTimeout(CommonConstants.PAGETIMEOUT, TimeUnit.SECONDS);
		String cancelString = mycancel.getCancelledBooking();
		log.info(cancelString);
		mycancel.clickReturnToBookings();
		mycancel.resetImplicitTimeout();
		String bkcancelled = cancelString.replaceAll("[^0-9]", "");
		if (!booking.contentEquals("")) { 
			assertThat(bkcancelled, equalToIgnoringCase(booking)); 
		}
		return bkcancelled;
	}
	
	
	@SuppressWarnings("rawtypes")
	@Step
	public List returnBookingsList() { return bookingsToRemove;}
}
