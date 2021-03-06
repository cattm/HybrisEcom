package gratetech.bdd.steps;

import gratetech.bdd.commons.CommonConstants;
import gratetech.bdd.steps.serenity.UserCancelSteps;
import net.thucydides.core.annotations.Steps;

import org.apache.log4j.Logger;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CancelBookings {
	public static Logger log = Logger.getLogger(CancelBookings.class);
	
	String booking = "";
	
	@Steps
	private UserCancelSteps marcus;
	
	
	@Given("^I have bookings to delete$")
	public void bookingsToDelete() throws Throwable {
		log.info("bookings to delete");
	    marcus.hasSelectedMyBookings();
	  
	}

	@And("^I can select the required booking$")
	public void selectRequiredBooking() throws Throwable {
		log.info("select booking");
		// there may be many pages to scroll through - this should be a while loop?
		int loopc = 0;
		while (!marcus.findBookingsOnPage() && (loopc < CommonConstants.DEFAULTPAGELOOP)) {
			 log.info("Processing Bookings On Page " + loopc);
			 //move to next page
			 marcus.moveToNextPage();
			 loopc++;
		} 
		log.info("NO ACTIVE BOOKINGS NOW");

	}
	
	@And("^I can select \"([^\"]*)\" booking$")
	public void selectRequiredBooking(String arg1) throws Throwable {
	    log.info ("select booking " + arg1);
	    booking = arg1;
	 // there may be many pages to scroll through - this should be a while loop?
	 		int loopc = 0;
	 		while (!marcus.findBookingsOnPage(booking) && (loopc < CommonConstants.DEFAULTPAGELOOP)) {
	 			 log.info("Processing Bookings On Page " + loopc + "moving to next page");
	 			 //move to next page
	 			 marcus.moveToNextPage();
	 			 loopc++;
	 		} 
	 		log.info("Required Booking Not found");
	}

	@And("^I select from ([^\"]*)$")
	public void selectFromRequiredBooking(String book) {
		log.info ("select booking " + book);
		booking = book;
		int loopc = 0;
 		while (!marcus.findBookingsOnPage(booking) && (loopc < CommonConstants.DEFAULTPAGELOOP)) {
 			 log.info("Processing Bookings On Page " + loopc + "moving to next page");
 			 //move to next page
 			 marcus.moveToNextPage();
 			 loopc++;
 		} 
 		log.info("Required Booking Not found");
		
	}
	
	@When("^I hit cancel and accept the cancelation pop up$")
	public void hitCancel() throws Throwable {
		log.info("Now Cancel it");
		String c = "";

		// lets cancel the first one only for now
		if (booking.contentEquals("")) {		
			c = marcus.cancelFirstValid();
			booking = c;
			
		} else {
			c = marcus.cancelBooking(booking);
		}
		
		if (!c.contentEquals("")) {marcus.completeTheCancel();}
		else {log.info("Nothing Cancelled");}
	}

	@Then("^I will see a confirmation page$")
	public void seeConfirmationPage() throws Throwable {
		
		log.info("See the confirmation page");
		// it should identify which item has been cancelled
		String thecancelledbooking = marcus.checkCancelSuccess(booking);
		log.info("we cancelled " + thecancelledbooking);
	}
}
