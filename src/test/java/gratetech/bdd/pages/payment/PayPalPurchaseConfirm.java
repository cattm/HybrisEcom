package gratetech.bdd.pages.payment;

import gratetech.bdd.commons.CommonConstants;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

public class PayPalPurchaseConfirm extends PageObject {
	public static Logger log = Logger.getLogger(PayPalPurchaseConfirm.class);
	
	@FindBy(id="confirmButtonTop")
	private WebElementFacade confirm;
	
	public void confirmPurchase() {
		switchToWindow();
		// do I need to pause here?
		log.info("Before Click");
		confirm.click();
		log.info("After Click");
	}
	
	private void switchToWindow() {
		// check title of window perhaps - Review your payment	
		int check = 0;
		while (check < 4) {
			if (this.getTitle().contains("Checkout - Review your payment")) {
				// get out of here we are on the right page
				log.info(this.getTitle());
				break;
				
			} else {
				try {
					Thread.sleep(CommonConstants.VSLOW);
					log.info(this.getTitle());
					log.info("not on correct window - checking again");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				check++;
			}
		}
		// more delays to wait for slow web page
		try {
			Thread.sleep(CommonConstants.SLOW);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebDriver wd = this.getDriver();
		String last = "";
		for (String handle1 : wd.getWindowHandles()) {		 
        	log.info("window is " + handle1);
        	last = handle1;
        }
		wd.switchTo().window(last);

	}
}
