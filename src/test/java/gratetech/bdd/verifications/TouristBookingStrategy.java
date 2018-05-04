package gratetech.bdd.verifications;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import gratetech.bdd.interfaces.IBookingValidationStrategy;
import gratetech.bdd.models.TouristBooking;
import gratetech.bdd.pages.BookingSummary;

public class TouristBookingStrategy implements IBookingValidationStrategy {
	
	@Override
	public void PerformBookingValidation(TouristBooking booking, BookingSummary summary) {
		verifyVAT(summary);	
		
		}
		
		private void verifyVAT(BookingSummary summary) {
			assertThat(summary.getTotalVat(),equalToIgnoringCase("£0.00"));	
		}

}
