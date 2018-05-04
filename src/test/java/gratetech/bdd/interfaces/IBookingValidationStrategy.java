package gratetech.bdd.interfaces;

import gratetech.bdd.models.TouristBooking;
import gratetech.bdd.pages.BookingSummary;

public interface IBookingValidationStrategy {

	void PerformBookingValidation(TouristBooking booking, BookingSummary summary);
	
	
}
