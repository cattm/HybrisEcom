package gratetech.bdd.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

public class BookingSummary extends PageObject {

	@FindBy(id="hybrisref")
	private WebElementFacade bookingRef;

	
	public String getBookingReference() {
		return bookingRef.getText();
	}
	

}
