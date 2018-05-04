package gratetech.bdd.verifications;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import gratetech.bdd.interfaces.IBookingValidationStrategy;
import gratetech.bdd.pages.BookingSummary;
import gratetech.bdd.utils.TouristBooking;

public class MiniCruiseStrategy implements IBookingValidationStrategy {
	
	@Override
	public void PerformBookingValidation(TouristBooking booking, BookingSummary summary) {
		// should check return date
		verifyReturnDate(booking, summary);
		// outbound and inbound accommodation are the same
		// duration
		// coach travel
		// vat
		verifyVAT(summary);	
	
	}
	
	private void verifyReturnDate(TouristBooking booking, BookingSummary summary) {
		// day of travel is plus 1 unless there is a coach journey
		booking.outboundJ.getDayOfTravel();
	}
	private void verifyVAT(BookingSummary summary) {
		assertThat(summary.getTotalVat(),equalToIgnoringCase("£0.00"));	
	}

}
