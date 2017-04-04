package gratetech.bdd.steps.serenity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import jline.internal.Log;
import net.thucydides.core.annotations.Step;
import gratetech.bdd.pages.LoggedIn;
import gratetech.bdd.pages.PandOHomePage;
import gratetech.bdd.pages.PandOLogin;
import gratetech.bdd.pages.QuoteForm;
import gratetech.bdd.pages.QuoteResponseInfo;
import gratetech.bdd.steps.QuoteSteps;

public class UserQuoteSteps {
	public static Logger log = Logger.getLogger(UserQuoteSteps.class);
	PandOHomePage homePage;
	PandOLogin login;
	LoggedIn liMessage;
	QuoteForm qForm;
	QuoteResponseInfo quoteTable;
	
	@Step
	public void hasLoggedIn() {
		homePage.open();
		homePage.handleCookieMessage();
		homePage.clickMyAccount();
		login.setUserName("marcus.catt58@googlemail.com");
		login.setPassword("Dogfight789");
		login.clickSubmit();
		//assertThat(liMessage.getConfirmMessage(),equalToIgnoringCase("Hello marcus.catt58@googlemail...."));
		assertThat(liMessage.getConfirmMessage(),equalToIgnoringCase("Hello marcus"));
	}
	
	@Step
	public void hasFilledInQuote(String from, String back, String dateout, String dateback, String vehicle, String len, String height, String adults) {
		qForm.setImplicitTimeout(10, TimeUnit.SECONDS);
		//qForm.elementIsPresent();
		qForm.setDeparturePort(from);
		qForm.setReturnPort(back);
		qForm.setDepartureDate(dateout);
		qForm.setReturnDate(dateback);
		qForm.setVehicleType(vehicle);
		qForm.setVehicleLength(len);
		qForm.setVehicleHeight(height);
		qForm.setNumberOfAdults(adults);
		qForm.resetImplicitTimeout();
	}
	
	@Step
	public void askForQuote() {
		Log.info("submitting quote form");
		qForm.getAQuote();
	}
	
	@Step
	public void getQuoteTitle(String toverify) {
		log.info("get the quote Title  " + quoteTable.getIntro());
		log.info("And we want it to be " + toverify);
		assertThat(quoteTable.getIntro(), equalToIgnoringCase(toverify));
	}
}
