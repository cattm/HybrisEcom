package gratetech.bdd.pages;

import gratetech.bdd.steps.QuoteSteps;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;

//@DefaultUrl("http://www.poferries.com/en/portal")
@DefaultUrl("http://qa1.poferries.com/en/portal")
public class PandOHomePage extends PageObject{
	public static Logger log = Logger.getLogger(PandOHomePage.class);
	
	@FindBy(css="a[href='/en/myaccount']")
	private WebElementFacade accountButton;
	
	
	public void clickMyAccount() {
		accountButton.click();
	}
	
	public void handleCookieMessage() {
		// check if we have cookie iframe and handle it if we do
		try {
			WebDriver local = this.getDriver();
			local.switchTo().frame("qb_cookie_consent_main");
			local.findElement(By.id("closePopup")).click();
			local.switchTo().defaultContent();
			//WebElement element =local.findElement(By.xpath(".//*[@id='qb_cookie_consent_main']//iframe"))
		}
		catch (Exception e) {
			log.info(e.getStackTrace());
		}
	}
}
