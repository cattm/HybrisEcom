package gratetech.bdd.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

public class LoggedIn extends PageObject {


	@FindBy(css="li[class='logged_in']")
	private WebElementFacade confirmMessage;
	
	public String getConfirmMessage() {
		return confirmMessage.getText();
	}
}
