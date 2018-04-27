package gratetech.bdd.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class CancelBookingConfirmation extends PageBase {
	
	@FindBy(id="backToMyAccount")
	private WebElementFacade backToAccount;
	
	@FindBy(css=".no-t-mar")
	private WebElementFacade bookingString;
	
	@FindBy(css=".ms500>strong")
	private WebElementFacade bookingRefund;
	
	public String getCancelledBooking() {
		return bookingString.getText();
	}
	
	public void clickReturnToBookings() {
		backToAccount.click();
	}

	public String getRefund() {
		return bookingRefund.getText();
	}
	
}
