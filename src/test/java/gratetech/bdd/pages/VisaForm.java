package gratetech.bdd.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

public class VisaForm extends PageObject {
	public static Logger log = Logger.getLogger(VisaForm.class);
	
	@FindBy(id="cardNumber")
	private WebElementFacade cardNumber;

	@FindBy(id="expiryMonth")
	private WebElementFacade eMonth;
	
	@FindBy(id="expiryYear")
	private WebElementFacade eYear;
	
	@FindBy(id="csc")
	private WebElementFacade cardSecurityCode;
	
	@FindBy(id="btnSubmit")
	private WebElementFacade payNow;
	
	public void setCardNumber(String card) {
		log.info(card);
		if (findIframe()) {
			cardNumber.clear();
			cardNumber.type(card);
			resetIframe();
		}
	}
	
	public void setCSC(String csc) {
		log.info(csc);
		if (findIframe()) {
			cardSecurityCode.clear();
			cardSecurityCode.type(csc);
			resetIframe();
		}
	}
	
	public void selectYear (String year) {
		log.info(year);
		if (findIframe()) {
			eYear.selectByVisibleText(year);
			resetIframe();
		}
	}
	
	public void selectMonth (String month) {
		log.info(month);
		if (findIframe()) {
			eMonth.selectByVisibleText(month);
			resetIframe();
		}
	}
	public void submitPayment() {
		if (findIframe()) {
			payNow.click();
			resetIframe();
		}
		
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
