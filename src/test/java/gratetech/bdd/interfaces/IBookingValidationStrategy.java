package gratetech.bdd.interfaces;

import gratetech.bdd.pages.BookingSummary;
import gratetech.bdd.utils.TouristBooking;

public interface IBookingValidationStrategy {

	void PerformBookingValidation(TouristBooking booking, BookingSummary summary);
	
	
}
