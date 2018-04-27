package gratetech.bdd.pages;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

public class MyJourneys extends PageObject {

	@FindBy(css=".my-account-journey-top.col-xs-10.no-r-pad.pull-left>h2")
	WebElementFacade myJourneyHeading;
	
	@FindBy(css=".no-b-pad.no-t-mar.pull-left")
	WebElementFacade myBookingHistoryBanner;
	
	public String getMyJourneyText() {
		return myJourneyHeading.getText();
	}
	
	public String getMyBookingHistoryTopLine() {
		return myBookingHistoryBanner.getText();
	}
}
