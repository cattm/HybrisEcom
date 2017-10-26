package gratetech.bdd.pages;

import gratetech.bdd.steps.serenity.UserPurchaseSteps;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

public class TheQuotePage extends PageObject  {
	public static Logger log = Logger.getLogger(TheQuotePage.class);
	
	@FindBy(id="continue-button")
	private WebElementFacade continueButton;
	
	public boolean selectOutbound(String departime, String boat, String classoffare) {
		// find the time of sailing
		// css = table[class="table outbound-results-table"] tbody tr td[class*="col-one"] returns the outbound times
		// css = table[class="table outbound-results-table"] tbody tr returns the row 
		// or table[class="table outbound-results-table"] tbody tr:nth-child(1) td:nth-child(3) finds a Ship "Pride of Kent"
		// or table[class="table outbound-results-table"] tbody tr:nth-child(1) td:nth-child(1) returns the time...
		// check the boat "col-three" returns list of boats - string match and use array index

		log.info("selectOutbound time " + departime + " on " + boat + " fare class " + classoffare);
		List<WebElement> timeelements = this.getDriver().findElements(By.cssSelector("table[class='table outbound-results-table'] tbody tr td[class*='col-one']"));
		int size = timeelements.size();
		log.info("number of elements is " + size);
		int offset = 0;
		Iterator<WebElement> iter = timeelements.iterator();
		while (iter.hasNext()) {
			offset++;
			WebElement item = iter.next();
			String label = item.getText();
			if (label.contentEquals(departime)) {
				log.info("found departure time - offset is " + offset);
				break;
			}
	
		}
		// TBD need to put in a check here to make sure we have a sailing
		String findboatcss = "table[class='table outbound-results-table'] tbody tr:nth-of-type(" + offset + ") td:nth-child(3)";
		log.info(findboatcss);
	
		WebElement myboat = this.getDriver().findElement(By.cssSelector(findboatcss));
		
		String boatname = myboat.getText();
		log.info(boatname);
		String selectfarecss = "";
		if (boatname.contentEquals(boat)) {
			// click the class....bronze/silver/gold/platinum
			switch (classoffare) {
			case "bronze" :
				selectfarecss = "table[class='table outbound-results-table'] tbody tr:nth-of-type(" + offset + ") td:nth-child(4)";
				break;
			case "silver" :
				selectfarecss = "table[class='table outbound-results-table'] tbody tr:nth-of-type(" + offset + ") td:nth-child(5)";
				break;
			case "gold"   :
				selectfarecss = "table[class='table outbound-results-table'] tbody tr:nth-of-type(" + offset + ") td:nth-child(6)";
				break;
			case "platinum" :
				selectfarecss = "table[class='table outbound-results-table'] tbody tr:nth-of-type(" + offset + ") td:nth-child(7)";
			default :
				break;
			}
			log.info(selectfarecss);
			// TODO: there is an occasional issue finding this - this is just a hack for now
			try {
				this.getDriver().findElement(By.cssSelector(selectfarecss)).click();
			} 
			catch (Exception e ) {
				log.error("OUCH We did not find the fare");
				return false;
			}
			return true;
		}

		return false;
	}
	
	public boolean selectReturn(String departime, String boat, String classoffare) {

		log.info("selectReturn time " + departime + " on " + boat + " fare class " + classoffare);
		List<WebElement> timeelements = this.getDriver().findElements(By.cssSelector("table[class='table inbound-results-table'] tbody tr td[class*='col-one']"));
		int size = timeelements.size();
		log.info("number of elements is " + size);
		int offset = 0;
		Iterator<WebElement> iter = timeelements.iterator();
		while (iter.hasNext()) {
			offset++;
			WebElement item = iter.next();
			String label = item.getText();
			if (label.contentEquals(departime)) {
				log.info("found departure time - offset is " + offset);
				break;
			}
			
	
		}
		String findboatcss = "table[class='table inbound-results-table'] tbody tr:nth-of-type(" + offset + ") td:nth-child(3)";
		log.info(findboatcss);
	
		WebElement myboat = this.getDriver().findElement(By.cssSelector(findboatcss));
		
		String boatname = myboat.getText();
		log.info(boatname);
		String selectfarecss = "";
		if (boatname.contentEquals(boat)) {
			// click the class....bronze/silver/gold/platinum
			switch (classoffare) {
			case "bronze" :
				selectfarecss = "table[class='table inbound-results-table'] tbody tr:nth-of-type(" + offset + ") td:nth-child(4)";
				break;
			case "silver" :
				selectfarecss = "table[class='table inbound-results-table'] tbody tr:nth-of-type(" + offset + ") td:nth-child(5)";
				break;
			case "gold"   :
				selectfarecss = "table[class='table inbound-results-table'] tbody tr:nth-of-type(" + offset + ") td:nth-child(6)";
				break;
			case "platinum" :
				selectfarecss = "table[class='table inbound-results-table'] tbody tr:nth-of-type(" + offset + ") td:nth-child(7)";
			default :
				break;
			}
			log.info(selectfarecss);
			this.getDriver().findElement(By.cssSelector(selectfarecss)).click();
			return true;
		}

		return false;
	
	}
	
	public void continueToNextPage() {
		continueButton.click();
	}
}
