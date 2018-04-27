package gratetech.bdd.pages.payment;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

public class PayNowForm extends PageObject {
	public static Logger log = Logger.getLogger(PayNowForm.class);
	
	@FindBy(id="agree1")
	private WebElementFacade tickTnc;
	
	@FindBy(id="paynowButton")
	private WebElementFacade payNow;
	
	public void tickTnC () {
		// cant find this element?
		//tickTnc.click;
		WebElement tnclocal = this.getDriver().findElement(By.id("agree1"));
		log.info("Tag " + tnclocal.getTagName());
		tnclocal.click();
	}
	
	public void eVoucher(String voucher) {
		// check if the string is not "" and if so populate the voucher	
		log.info("eVoucher not implemented");
	}
	
	public void purchaseNow() {
		// this doesnt work either
		log.info("buy ticket");
		//payNow.submit();
		WebElement purchlocal = this.getDriver().findElement(By.id("paynowButton")); 
		if (purchlocal.isEnabled()) {
			log.info("purchase is enabled");
			purchlocal.click();
		} else {
			log.info("purchase is not enabled");
		}
	}
}
