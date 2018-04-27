package gratetech.bdd.pages;

import gratetech.bdd.commons.CommonConstants;
import gratetech.bdd.steps.CancelBookings;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WebElementState;

public class BookingHistory extends PageBase {
	public static Logger log = Logger.getLogger(BookingHistory.class);

	
	@FindBy(css=".held-fare-content.cb")
	private WebElementFacade heldContent;
	
	@FindBy(css="a[href*='javascript:scrollNextJourney']")
	private WebElementFacade nextPage;
	
	public List findAllBookingsOnPage() {		
	
		pageIsReady(CommonConstants.FAST);
		//List<WebElement> elements = getDriver().findElements(By.cssSelector(".reference-no"));
		List<WebElement> elements = heldContent.findElements(By.cssSelector(".reference-no"));
		log.info("Found elements - " + elements.size());
		String [] results = new String [elements.size()];
		int i = 0;
        for(WebElement w : elements){
            //log.info("Found booking : " + w.getText());
            results[i++] = w.getText();
        }
        List list = (List) Arrays.asList(results);
        return list;
	}
	
	public List findBookingAge() {
		pageIsReady(CommonConstants.FAST);
		List<WebElement> elements = heldContent.findElements(By.cssSelector(".active-booking.pr.z-index101"));
		log.info("Found elements - " + elements.size());
		String [] results = new String [elements.size()];
		int i = 0;
        for(WebElement w : elements){
            //log.info("Booking Age Status is : " + w.getText());
            results[i++] = w.getText();
        }
        List list = (List) Arrays.asList(results);
        return list;
	}
	
	public List findOpenAmendLinks() {
		pageIsReady(CommonConstants.FAST);
		List<WebElement> elements = heldContent.findElements(By.cssSelector(".delete.cb"));
		log.info("Found elements to amend - " + elements.size());    
        return elements;
	}
	
	public void clickNextPage() {
		/*
		 * 	<a href="javascript:scrollNextJourney('en', '2');" class="firefinder-match">
				<span class="big-pink-arrows-right">next</span>
			</a>
			driver.findElement(By.cssSelector("a[href*='javascript:scrollNextJourney']")).click();
			might return 2 elements - both the same :)
		 */
		nextPage.click();
		
	}
	
	public void openAmend(WebElement ele) {
		pageIsReady(CommonConstants.FAST);
		ele.click();	
	}
}
