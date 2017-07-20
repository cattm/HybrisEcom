package gratetech.bdd.pages;

import gratetech.bdd.steps.serenity.UserQuoteSteps;

import org.apache.log4j.Logger;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

public class TheExtras extends PageObject {
	public static Logger log = Logger.getLogger(TheExtras.class);
	
	@FindBy(id="book-now-button")
	private WebElementFacade bookNow;
	
	
	public void continueToNextPage() {
		log.info("continueToNextPage");
		bookNow.click();
		return;
	}
}
