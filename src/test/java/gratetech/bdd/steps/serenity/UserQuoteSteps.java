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
	protected PandOHomePage homePage;
	protected PandOLogin login;
	protected LoggedIn liMessage;
	protected QuoteForm qForm;
	protected QuoteResponseInfo quoteTable;
	protected boolean vehicleIsVan = false;
	
	@Step
	public void hasLoggedIn(String user, String password, String greeting) {
		homePage.open();
		log.info("user " + user + " pw " + password);
		
		if (liMessage.getConfirmMessage().contentEquals("")) {
			log.info("not already logged in");
			homePage.handleCookieMessage();
			homePage.clickMyAccount();
			login.setUserName(user);
			login.setPassword(password);
			login.clickSubmit();
		} else {
			homePage.clickMyAccount();
		}
		log.info("logged in");
		assertThat(liMessage.getConfirmMessage(),equalToIgnoringCase(greeting));
	
	}
	
	@Step
	public void hasFilledInQuote(String from, String back, String dateout, String dateback, String vehicle, String len, String height, String adults, String promocode) {
		qForm.setImplicitTimeout(10, TimeUnit.SECONDS);
		//qForm.elementIsPresent();
		qForm.setDeparturePort(from);
		qForm.setReturnPort(back);
		qForm.setDepartureDate(dateout);
		qForm.setReturnDate(dateback);
		qForm.setVehicleType(vehicle);
		if (vehicle.contentEquals("van") || vehicle.contentEquals("motorhome")) { vehicleIsVan=true;}
		if (vehicleIsVan) {
			qForm.setVehicleLength(len);
			qForm.setVehicleHeight(height);
		}
		
		qForm.setNumberOfAdults(adults);
		qForm.resetImplicitTimeout();
		qForm.setPromoCode(promocode);
	}
	


	@Step
	public void askForQuote() {
		log.info("submitting quote form");
		qForm.getAQuote();
	}
	
	@Step
	public void getQuoteTitle(String toverify) {
		// TODO: if the promo code is used and is not valid for sailings then this will display an error message instead
		// of PICK YOUR ......
		log.info("get the quote Title  " + quoteTable.getIntro());
		log.info("And we want it to be " + toverify);
		assertThat(quoteTable.getIntro(), equalToIgnoringCase(toverify));
	}
}
