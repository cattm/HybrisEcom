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

public class QuoteWhere extends PageObject {
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
