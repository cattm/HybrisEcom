package gratetech.bdd.pages;

import java.util.Iterator;
import java.util.List;

import gratetech.bdd.commons.CommonConstants;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class MiniCruiseQuotePage extends PageBase {
	public static Logger log = Logger.getLogger(MiniCruiseQuotePage.class);
	
	@FindBy(css="input[value*='silver']")
	WebElementFacade silverOption;

	@FindBy(css="input[value*='bronze']")
	WebElementFacade bronzeOption;
	

	@FindBy(id="continue-button")
	private WebElementFacade continueButton;
	
	public boolean selectQuote(String offerclass) {
		pageIsReady(CommonConstants.FAST);
		
		
		// there are actually 2 elements of silver and bronze - no distinguishing marks!!!
		WebElement item = null;
		List<WebElement> elements;
		Iterator<WebElement> iter;
		switch (offerclass) {
		case "bronze" :
			//bronzeOption.click();
			elements = this.getDriver().findElements(By.cssSelector("input[value*='bronze']"));
			iter = elements.iterator();
			while (iter.hasNext()) {	
				item = iter.next();	
				log.info("located radio box bronze to click");
			}
			break;
		case "silver" :
			//silverOption.click();
			elements = this.getDriver().findElements(By.cssSelector("input[value*='silver']"));
			iter = elements.iterator();
			while (iter.hasNext()) {	
				item = iter.next();	
				log.info("located radio box silver to click");
			}
			break;
		default:
			log.info("no valid class");
			return false;
		}
		if (item != null) {
			item.click();
			pageIsReady(CommonConstants.FAST);
			return true;
		}
		return false;
	}
	
	public void continueToNextPage() {
		continueButton.click();
	}

}
