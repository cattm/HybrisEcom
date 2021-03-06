package gratetech.bdd.pages.quote;

import java.util.List;

import gratetech.bdd.commons.CommonConstants;
import gratetech.bdd.pages.PageBase;

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
	
	@FindBy(id="getQuote")
	private WebElementFacade submitMCGetAQuote;
	
	@FindBy(id="warning-continue-button")
	private WebElementFacade infoWarningContinue;
	
	@FindBy(linkText = "MINICRUISE")
	private WebElementFacade miniCruiseTab;
	
	@FindBy(linkText = "FERRY")
	private WebElementFacade ferryTab;
	
	
	public void selectMiniCruise() {
		log.info("selecting MiniCruise");
		pageIsReady(CommonConstants.FAST);
		miniCruiseTab.click();
		pageIsReady(CommonConstants.FAST);
	}
	
	public void selectFerry() {
		log.info("selecting MiniCruise");
		pageIsReady(CommonConstants.FAST);
		ferryTab.click();
		pageIsReady(CommonConstants.FAST);
	}
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
	
	public void getAMCQuote() {
		submitMCGetAQuote.submit(); 
		log.info("Getting MC Quote:");
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
	

} // End Class