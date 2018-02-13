package gratetech.bdd.steps.serenity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import net.thucydides.core.annotations.Step;
import gratetech.bdd.pages.LoggedIn;
import gratetech.bdd.pages.PandOHomePage;
import gratetech.bdd.pages.PandOLogin;
import gratetech.bdd.pages.QuoteForm;
import gratetech.bdd.pages.QuoteHow;
import gratetech.bdd.pages.QuoteResponseInfo;
import gratetech.bdd.pages.QuoteWhen;
import gratetech.bdd.pages.QuoteWhere;
import gratetech.bdd.pages.QuoteWho;


public class UserQuoteSteps {
	public static Logger log = Logger.getLogger(UserQuoteSteps.class);
	protected PandOHomePage homePage;
	protected PandOLogin login;
	protected LoggedIn liMessage;
	// TODO: replace qForm with simpler models
	protected QuoteForm qForm;
	protected QuoteWhere qWhere;
	protected QuoteWhen qWhen;
	protected QuoteHow qHow;
	protected QuoteWho qWho;
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
	public void hasFilledInQuote(String from, String back, String dateout, String dateback, String vehicle, String len, String height, String adults, String outtime, String cabins, String promocode) {
		qForm.setImplicitTimeout(10, TimeUnit.SECONDS);
		qWho.setImplicitTimeout(20, TimeUnit.SECONDS);
		
		// WHERE
		qWhere.setDeparturePort(from);
		qWhere.setReturnPort(back);
		// save for later use in working out which id to use
		qWho.setFromRoute(qWhere.getFromRoute());
		
		// WHEN
		qWhen.setDepartureDate(dateout);
		qWhen.setReturnDate(dateback);		
		qWhen.setDepartureTime(outtime);
		
		// HOW
		qHow.setVehicleType(vehicle);
	
		if (vehicle.contentEquals("van") || vehicle.contentEquals("motorhome")) { vehicleIsVan=true;}
		if (vehicleIsVan) {
			qHow.setVehicleLength(len);
			qHow.setVehicleHeight(height);
		}
		
		// WHO
		// TODO: not dealing with Trailer/Caravan currently
		// TODO: Or anybody apart from Adults
		// TODO Remove this code
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		qWho.setNumberOfAdults(adults);

		// ODDS
		if (!cabins.contentEquals("")) {
			qForm.setNumberOfCabins(cabins);
		}
		qForm.setPromoCode(promocode);
			
		qWho.resetImplicitTimeout();
		qForm.resetImplicitTimeout();
	}
	

	@Step
	public void addInfants1(String babies) {
		qWho.setImplicitTimeout(20, TimeUnit.SECONDS);
		qWho.setNumberOfInfants1(babies);
		qWho.resetImplicitTimeout();
	}

	@Step
	public void askForQuote() {
		log.info("submitting quote form");
		qForm.getAQuote();
		qForm.continueWarningMessage();
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
