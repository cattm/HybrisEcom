package gratetech.bdd.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

public class BookingSummary extends PageObject {

	@FindBy(id="hybrisref")
	private WebElementFacade bookingRef;

	@FindBy(id="total")
	private WebElementFacade totalprice;
	
	@FindBy(id="vat")
	private WebElementFacade vat;
	
	public String getBookingReference() {
		return bookingRef.getText();
	}
	
	public String getTotalPrice() {
		return totalprice.getText();
	}
	
	public String getTotalVat() {
		return vat.getText();
	}
}
