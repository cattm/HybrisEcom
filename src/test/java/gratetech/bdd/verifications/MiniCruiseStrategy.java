package gratetech.bdd.verifications;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import gratetech.bdd.interfaces.IBookingValidationStrategy;
import gratetech.bdd.models.TouristBooking;
import gratetech.bdd.pages.BookingSummary;

public class MiniCruiseStrategy implements IBookingValidationStrategy {
	
	@Override
	public void PerformBookingValidation(TouristBooking booking, BookingSummary summary) {
		// should check return date
		verifyReturnDate(booking, summary);
		
		// outbound and inbound accommodation are the same
		verifyAccomodation(booking, summary);
		
		// coach travel
		verifyCoach(summary);
		
		// vat
		verifyVAT(summary);	
	
	}
	
	private void verifyReturnDate(TouristBooking booking, BookingSummary summary) {
		// day of travel is plus 1 unless there is a coach journey
		// how do we work this out - its listed in extras
		// there might be a hotel/coach
		// there will be a cabin
		String went = booking.outboundJ.getDayOfTravel();
	}
	
	private void verifyAccomodation(TouristBooking booking, BookingSummary summary) {
		// depending upon class of ticket and assuming default choices
		// out == in
		// price out == price in
		// Assumes ticket type out == ticket type in
		
	}
	
	private void verifyCoach(BookingSummary summary) {
		// work out if there is a coach (its an Extra) and if there is
		// check its INCLUDED
		
	}
	
	private void verifyVAT(BookingSummary summary) {
		// this should be a constant of nothing - so a safe check
		assertThat(summary.getTotalVat(),equalToIgnoringCase("£0.00"));	
	}
	
	/*
	 *  Ticket Type - Complex area poorly structured
	 *  div class booking-outbound
	 *  div class ticket-type dots amends extras-slot is the top of this area 
	 *  
	 *  eventually followed by
	 *  div class booking return
	 *  div class ticket-type dots amends extras-slot
	 *  
	 */

}
