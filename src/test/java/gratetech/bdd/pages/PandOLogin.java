package gratetech.bdd.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

public class PandOLogin extends PageObject {

	@FindBy(name="j_username")
	private WebElementFacade userEmail;
	
	@FindBy(name="j_password")
	private WebElementFacade userPassword;
	
	@FindBy(id="myAccountButton")
	private WebElementFacade submit;
	
	public void setUserName(String name) {
		userEmail.type(name);
	}
	
	public void setPassword(String password) {
		userPassword.type(password);
	}
	
	public void clickSubmit() {
		submit.click();
	}
}
