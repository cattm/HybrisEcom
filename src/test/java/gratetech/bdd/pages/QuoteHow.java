package gratetech.bdd.pages;

import gratetech.bdd.commons.CommonConstants;

import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class QuoteHow extends PageObject {
	public static Logger log = Logger.getLogger(QuoteHow.class);
	// use the original HTML listbox objects?
	private boolean makeVisible = false;

	@FindBy(id="vehicleTypeOutboundComboBox")
	private WebElementFacade selectVehicleType;
	
	@FindBy(id="ou_length")
	private WebElementFacade selectVehicleLength;
	
	@FindBy(id="ou_height")
	private WebElementFacade selectVehicleHeight;
	
	@FindBy(id="vehicleTypeOutboundComboBoxSelectBoxIt")
	private WebElementFacade outboundJourneyVehicleEnable;
	
	@FindBy(id="trailerOutboundComboBoxSelectBoxIt")
	private WebElementFacade outboundJourneyTrailerEnable;

	public void setVehicleType(String type){
		log.info(type);
		if (makeVisible) {
			evaluateJavascript("document.getElementById('vehicleTypeOutboundComboBox').style.display='block';");
			Select dropdown = new Select(selectVehicleType);
			dropdown.selectByValue(type);
		} else {
			pageIsReady(CommonConstants.FAST);
			outboundJourneyVehicleEnable.click();
			pageIsReady(CommonConstants.FAST);
			List<WebElement> wel = getDriver().findElements(By.cssSelector("#vehicleTypeOutboundComboBoxSelectBoxItOptions li"));
			for(WebElement e : wel) {				
				if (e.getText().equalsIgnoreCase(type)) {
					log.info("Setting vehicle to " + type);
					e.click();
					break;
				}
			}
		}
	}
	
	public void setTrailer(String trailer) {
		
		log.info(trailer);
		if (makeVisible) {
			log.info("makeVisible not coded yet");
		} else {
			pageIsReady(CommonConstants.FAST);
			outboundJourneyTrailerEnable.click();
			pageIsReady(CommonConstants.FAST);
			List<WebElement> wel = getDriver().findElements(By.cssSelector("#cabinsOutboundSelectBoxItOptions li"));
			for(WebElement e : wel) {				
				if (e.getText().equalsIgnoreCase(trailer)) {
					log.info("Setting vehicle to " + trailer);
					e.click();
					break;
				}
			}
		}
	}
	
	public void setVehicleLength(String length) {
		log.info(length);
		pageIsReady(CommonConstants.FAST);
		selectVehicleLength.clear();
		selectVehicleLength.type(length);
	}
	
	public void setVehicleHeight(String height) {
		log.info(height);
		pageIsReady(CommonConstants.FAST);
		selectVehicleHeight.clear();
		selectVehicleHeight.typeAndTab(height);
	}
	
	// TBD This code is duplicated in another page object
	// We will need to extract and refactor
	private void pageIsReady(long slow) {
		final int limit = 5;
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
	
	private Boolean isPageLoaded() {
		String jsQuery = "function pageLoaded() "
				+ "{var loadingStatus=(document.readyState=='complete');"
				+ "return loadingStatus;};"
				+ "return pageLoaded()";
		return (Boolean) evaluateJavascript(jsQuery);
	}
	
	public void jsListSelect(String selector, String setting) {
		List<WebElement> wel = getDriver().findElements(By.cssSelector(selector));
		for(WebElement e : wel) {				
			if (e.getText().equalsIgnoreCase(setting)) {
				log.info("Setting selector to " + setting);
				e.click();
				break;		
			}
		}
	}
	
}
