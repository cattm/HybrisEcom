package gratetech.bdd.pages;

import gratetech.bdd.commons.CommonConstants;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

public class QuoteWhen extends PageObject {
	public static Logger log = Logger.getLogger(QuoteWhen.class);
	boolean makeVisible = false;
	@FindBy(id="singleJourneyDateTextBox")
	private WebElementFacade selectGoingOutDate;
	
	@FindBy(id="returnJourneyDateTextBox")
	private WebElementFacade selectReturnDate;
	
	@FindBy(id="singleJourneyTimeComboBoxSelectBoxIt")
	private WebElementFacade singleJourneyTimeEnable;
	
	@FindBy(id="returnJourneyTimeComboBoxSelectBoxIt")
	private WebElementFacade returnJourneyTimeEnable;
	
	
	@FindBy(id="singleJourneyTimeComboBox")
	private WebElementFacade selectOutTime;
	
	@FindBy(id="returnJourneyTimeComboBox")
	private WebElementFacade selectReturnTime;
	
	// TBD These methods "work" but they appear to upset the sailing time box drop downs - get set to NO TIME?
	public void setDepartureDate(String when) {
		log.info(when);
		if (true) {
			pageIsReady(CommonConstants.FAST);
			selectGoingOutDate.clear();
			selectGoingOutDate.type(when);	
		}
	}
	
	public void setReturnDate(String when) {
		log.info(when);
		if (true) {
			pageIsReady(CommonConstants.FAST);
			selectReturnDate.clear();
			selectReturnDate.typeAndTab(when);
		
		}
	}
	
	// TBD need methods to set the TIME also
	public void setDepartureTime(String t) {
		log.info(t);
		// TODO: fix this horrible check
		if (t.isEmpty()) return;
		
		if (makeVisible) {
			evaluateJavascript("document.getElementById('singleJourneyTimeComboBox').style.display='block';");
			Select dropdown = new Select(selectOutTime);
			dropdown.selectByValue(t);
		} else {
		
			// #singleJourneyTimeComboBoxSelectBoxIt.selectboxit - click()? or open()
			// #singleJourneyTimeComboBoxSelectBoxItOptions.selectboxit-option li:nth-child(#{4}) doesnt work
			// but #singleJourneyTimeComboBoxSelectBoxItOptions li:nth-child(5) a does work and returns
			// 	
			pageIsReady(CommonConstants.FAST);
			singleJourneyTimeEnable.click();
			pageIsReady(CommonConstants.FAST);
			jsListSelect("#singleJourneyTimeComboBoxSelectBoxItOptions li", t);				
		}
	}
	
	public void setReturnTime(String t) {
		log.info(t);
		// TODO: fix this horrible check
		if (t.isEmpty()) return;
		if (makeVisible) {
			log.info("makeVisible not coded yet");
		} else {
			pageIsReady(CommonConstants.FAST);
			returnJourneyTimeEnable.click();
			pageIsReady(CommonConstants.FAST);
			jsListSelect("#returnJourneyTimeComboBoxSelectBoxItOptions li", t);			
		}	
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
