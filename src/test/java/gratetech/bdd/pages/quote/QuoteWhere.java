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

public class QuoteWhere extends PageBase{
	public static Logger log = Logger.getLogger(QuoteWhere.class);
	// use the original HTML listbox objects?
	private boolean makeVisible = false;
	
	
	@FindBy(id="singleJourneyComboBox")
	private WebElementFacade selectOutJourney;
	
	@FindBy(id="returnJourneyComboBox")
	private WebElementFacade selectReturnJourney;

	@FindBy(id="singleJourneyComboBoxSelectBoxIt")
	private WebElementFacade singleJourneyListEnable;
	
	@FindBy(id="returnJourneyComboBoxSelectBoxIt")
	private WebElementFacade returnJourneyListEnable;

	@FindBy(id="singleJourneyRadio")
	private WebElementFacade singleJourneyRadio;
	
	@FindBy(id="returnJourneyRadio")
	private WebElementFacade returnJourneyRadio;
	
	String fromRoute = "";
	
	public void setDeparturePort(String from) {
		log.info(from);
		fromRoute = from;
		if (makeVisible) {
			
			evaluateJavascript("document.getElementById('singleJourneyComboBox').style.display='block';");
			Select dropdown = new Select(selectOutJourney);  
			dropdown.selectByVisibleText(from);
		} else {
			// enable the list
			pageIsReady(CommonConstants.FAST);
			singleJourneyListEnable.click();
			pageIsReady(CommonConstants.FAST);
			jsListSelect("#singleJourneyComboBoxSelectBoxItOptions li", from);
			
		}
	}
	
	public void setReturnPort(String from) {
		log.info(from);
		if (makeVisible) {		
			evaluateJavascript("document.getElementById('returnJourneyComboBox').style.display='block';");
			Select dropdown = new Select(selectReturnJourney);
			dropdown.selectByVisibleText(from);
		} else {
			// enable the list
			pageIsReady(CommonConstants.FAST);
			returnJourneyListEnable.click();
			pageIsReady(CommonConstants.FAST);
			jsListSelect("#returnJourneyComboBoxSelectBoxItOptions li", from);
		}
	}
	
	public void setSingleRadio() {
		singleJourneyRadio.click();
	}
	
	public void setReturnRadio() {
		returnJourneyRadio.click();
	}
	
	public String getFromRoute() { return fromRoute; }
	
}
