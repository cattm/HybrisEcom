package gratetech.bdd.pages;

import gratetech.bdd.commons.CommonConstants;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

public class LoggedIn extends PageBase {


	@FindBy(css="li[class='logged_in']")
	private WebElementFacade confirmMessage;
	
	@FindBy(css="a[href='/en/myaccountjourney']")
	private WebElementFacade myBookingsLink;
	
	public String getConfirmMessage() {
		if (confirmMessage.isPresent()) {
			return confirmMessage.getText();
		}
		return "";
	}
	
	public void selectMyBookings() {
		pageIsReady(CommonConstants.FAST);
		myBookingsLink.click();
	}
}
