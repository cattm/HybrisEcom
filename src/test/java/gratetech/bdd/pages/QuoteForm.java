package gratetech.bdd.pages;

import java.util.List;

import gratetech.bdd.commons.CommonConstants;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;


import org.openqa.selenium.WebElement;


import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

public class QuoteForm extends PageObject {
	public static Logger log = Logger.getLogger(QuoteForm.class);
	// use the original HTML listbox objects?
	private boolean makeVisible = false;

	@FindBy(id="cabinsOutboundSelectBoxIt")
	private WebElementFacade outboundCabinEnable;
			
	@FindBy(id="discountCodeTextBox")
	private WebElementFacade promoCode;
	
	@FindBy(id="fareFindersubmitButton")
	private WebElementFacade submitGetAQuote;
	
	@FindBy(id="warning-continue-button")
	private WebElementFacade infoWarningContinue;

	public void setNumberOfCabins(String number) {
		log.info(number);
		if (makeVisible) {
			log.info("makeVisible not coded yet");
		} else {
			pageIsReady(CommonConstants.FAST);
			outboundCabinEnable.click();
			pageIsReady(CommonConstants.FAST);
			List<WebElement> wel = getDriver().findElements(By.cssSelector("#cabinsOutboundSelectBoxItOptions li"));
			for(WebElement e : wel) {				
				if (e.getText().equalsIgnoreCase(number)) {
					log.info("Setting cabins to " + number);
					e.click();
					break;
				}
			}
		}
	}
	
	public void tickSameOnReturn() {
		
	}
	
	public void setPromoCode(String code) {
		log.info(code);
		if (!code.contains("")) {
			pageIsReady(CommonConstants.FAST);
			promoCode.clear();
			promoCode.type(code);
		}
		
	}
		
	public void getAQuote() {
		submitGetAQuote.submit(); 
		log.info("Getting Quote:");
	}
	
	public void continueWarningMessage() {
		//#warning-continue-button
		pageIsReady(CommonConstants.FAST);
		getDriver().switchTo().activeElement();
		if (infoWarningContinue.isVisible()) {
			infoWarningContinue.click();
			log.info("CLICK");
		}
		getDriver().switchTo().defaultContent();


		pageIsReady(CommonConstants.FAST);
	}
	
	// TBD This code is duplicated in another page object
	// We will need to extract and refactor
	protected void pageIsReady(long slow) {
		final int limit = CommonConstants.WAITLOOPCOUNT;
		int count = 0;
		try {
			Boolean isLoaded = false; 
			while (!isLoaded && (count < limit)) {
				isLoaded = isPageLoaded();
			    Thread.sleep(slow);
			    count++;
			}
			log.info("Exit pageIsReady");								
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected Boolean isPageLoaded() {
		String jsQuery = "function pageLoaded() "
				+ "{var loadingStatus=(document.readyState=='complete');"
				+ "return loadingStatus;};"
				+ "return pageLoaded()";
		return (Boolean) evaluateJavascript(jsQuery);
	}
	
	protected void jsListSelect(String selector, String setting) {
		List<WebElement> wel = getDriver().findElements(By.cssSelector(selector));
		for(WebElement e : wel) {				
			if (e.getText().equalsIgnoreCase(setting)) {
				log.info("Setting selector to " + setting);
				e.click();
				break;		
			}
		}
	}

} // End Class
/* 
 * This is a very interesting challenge - to control the farefinder quote form
 * This is because there are a number of controls using the JQuery based libary - selectBoxIt
 * This is particularly interesting with regards List boxes because there are all hidden and replaced by JS code
 * you can attempt to handle this in one of 3 ways
 * 1) interact with the element directly
 * 2) interact with the hidden element by enabling it and using js activity
 * 			executor.executeScript("document.getElementById('vehicleTypeOutboundComboBox').style.display='block';");
			Select dropdown = new Select(selectVehicleType);
			dropdown.selectByValue(type);
 * 3) locate the constituent parts of the selectboxit components and manage them 
 * luckily it looks like you only need to manage two parts 
 * class - selectboxit and selectboxit-options - a list of selectboxit-option
 * 	WebDriver dr = this.getDriver();
	WebElement we = dr.findElement(By.id("singleJourneyTimeComboBoxSelectBoxIt"));
	log.info(we.getText());
	we.click();
	we = dr.findElement(By.cssSelector("#singleJourneyTimeComboBoxSelectBoxItOptions li:nth-child(5) a"));
	log.info(we.getText());
	we.click();
 *  -- Note that we may need to "wait" between operations in order to ensure the JS has executed and rewritten the page
*/