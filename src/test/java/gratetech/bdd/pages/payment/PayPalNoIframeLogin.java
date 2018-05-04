package gratetech.bdd.pages.payment;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

import org.apache.log4j.Logger;

public class PayPalNoIframeLogin extends PageObject {
	public static Logger log = Logger.getLogger(PayPalNoIframeLogin.class);
	
	@FindBy(id="login_email")
	private WebElementFacade email;

	@FindBy(id="login_password")
	private WebElementFacade password;
	
	@FindBy(id="submitLogin")
	private WebElementFacade login;
	
	
	public void setEmail(String str) {
		log.info(str);	
		email.clear();
		email.type(str);	
	}
	
	public void setPassword(String str) {
		log.info(str);	
		password.clear();
		password.type(str);				
	}
	
	public void submit() {
		log.info("submit");
		login.click();				
	}

}
