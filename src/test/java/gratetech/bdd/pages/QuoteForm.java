package gratetech.bdd.pages;

import java.util.List;

import gratetech.bdd.commons.CommonConstants;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class QuoteForm extends PageBase {
	public static Logger log = Logger.getLogger(QuoteForm.class);
	
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
		if (getVisible()) {
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
		log.info("NOT IMPLEMENTED");
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
			log.info("Continue warning dealt with");
		}		
		getDriver().switchTo().defaultContent();
		pageIsReady(CommonConstants.FAST);
	}
	
/* BLOCK	
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
BLOCK */
} // End Class