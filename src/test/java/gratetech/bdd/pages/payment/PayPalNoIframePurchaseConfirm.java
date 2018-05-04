package gratetech.bdd.pages.payment;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

import org.apache.log4j.Logger;

public class PayPalNoIframePurchaseConfirm extends PageObject {
	public static Logger log = Logger.getLogger(PayPalNoIframePurchaseConfirm.class);
	
	@FindBy(id="continue")
	private WebElementFacade confirm;

	public void confirmPurchase() {
		log.info("Before Click");
		confirm.click();
		log.info("After Click");
	}

}
