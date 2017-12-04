package gratetech.bdd.pages;

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
		confirm.click();
	}
	
	private void switchToWindow() {
		// check title of window perhaps - Review your payment	
		int check = 0;
		while (check < 3) {
			if (this.getTitle().contains("Checkout - Review your payment")) {
				// get out of here we are on the right page
				log.info(this.getTitle());
				check = 3;
			} else {
				try {
					Thread.sleep(10000);
					log.info(this.getTitle());
					log.info("not on correct window - checking again");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				check++;
			}
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
