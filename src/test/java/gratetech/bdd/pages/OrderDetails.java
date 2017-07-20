package gratetech.bdd.pages;

import java.util.List;

import gratetech.bdd.steps.serenity.UserPurchaseSteps;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBys;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

/*
 * This page is a series of iFrames 
 * Initial approach is to handle these directly 
 * and not make full use of the factory methods 
 * Once I have certainty of behaviour I will revert
 * I will use long winded debug code to check the iframes
 */
public class OrderDetails extends PageObject {
	public static Logger log = Logger.getLogger(OrderDetails.class);

	@FindBy(id="goods")
	private WebElementFacade product;
	
	@FindBy(id="amount")
	private WebElementFacade amount;
	
	@FindBy(id="paymentMethod:0")
	private WebElementFacade card;
	
	@FindBy(id="paypageHeading")
	private WebElementFacade heading;

	private WebElementFacade stuff;
	
	public String getHeading() {
		String ret = "";
		if (findIframe()) {
			ret = heading.getText();
			resetIframe();
		}	
		return ret;
	}
	public void selectVisaPayment() {
		log.info("selectVisaPayment");		
		if (findIframe()) {
			card.click();
			resetIframe();
		}			
	}
	
	public String getProduct() {
		String ret = "";
		log.info("searching for goods");
		if (findIframe()) {
			ret = product.getText();
			resetIframe();
		}
		return ret;
	}
	
	public String getAmount() {
		String ret = "";
		log.info("searching for goods");
		if (findIframe()) {
			ret = amount.getText();
			resetIframe();
		}	
		return ret;
	}
	
	public String getAddress() {
		// this is complex each row is different
		log.info("getAddress not implemented");
		return "";
	}
	
	private boolean findIframe() {	
		List<WebElement> allFrameElements = this.getDriver().findElements(By.tagName("iframe"));
		for(WebElement nitem : allFrameElements )
		{
           String tmp = nitem.getAttribute("src");
           if (tmp.contains("SOLVEPANDO")) {
        	   this.getDriver().switchTo().frame(nitem);  
        	   return true;
           }
		}
		return false;
	}
	
	private void resetIframe() {
		this.getDriver().switchTo().defaultContent();
	}
}
