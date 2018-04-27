package gratetech.bdd.pages.quote;

import gratetech.bdd.commons.CommonConstants;
import gratetech.bdd.pages.PageBase;

import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class QuoteHow extends PageBase {
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
		if (type == null ||  type.isEmpty()) return;	
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
	

}
