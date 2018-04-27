package gratetech.bdd.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

public class BookingConfirm extends PageObject{

	@FindBy(css=".header-ref.no-r-mar")
	private WebElementFacade theBooking;

	@FindBy(css=".journey-total>dd")
	private WebElementFacade theTotalCostCharged;
	
	@FindBy(css=".journey-total>dd")
	private WebElementFacade theTotalCostIncVAT;
	
	public String getBooking() {
		return theBooking.waitUntilVisible().getText();
	}
	
	public String getTotalCost () {
		return theTotalCostIncVAT.waitUntilVisible().getText();
	}
	
	public String getTotalCostCharged () {
		return theTotalCostCharged.waitUntilVisible().getText();
	}
}
