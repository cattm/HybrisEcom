package gratetech.bdd.steps.serenity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import net.thucydides.core.annotations.Step;
import gratetech.bdd.pages.LoggedIn;
import gratetech.bdd.pages.PandOHomePage;
import gratetech.bdd.pages.PandOLogin;
import gratetech.bdd.pages.quote.QuoteForm;
import gratetech.bdd.pages.quote.QuoteHow;
import gratetech.bdd.pages.quote.QuoteResponseInfo;
import gratetech.bdd.pages.quote.QuoteWhen;
import gratetech.bdd.pages.quote.QuoteWhere;
import gratetech.bdd.pages.quote.QuoteWho;
import gratetech.bdd.commons.CommonConstants;

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
	public void hasFilledInMiniCruise(String from, String to, String vehicle, String height, String length, String when, String adults) {
		qForm.setImplicitTimeout(CommonConstants.PAGETIMEOUT, TimeUnit.SECONDS);
		qWho.setImplicitTimeout(CommonConstants.PAGETIMEOUT, TimeUnit.SECONDS);
		qWhere.setImplicitTimeout(CommonConstants.PAGETIMEOUT, TimeUnit.SECONDS);
		qWhen.setImplicitTimeout(CommonConstants.PAGETIMEOUT, TimeUnit.SECONDS);
		qHow.setImplicitTimeout(CommonConstants.PAGETIMEOUT, TimeUnit.SECONDS);
		
		// select minicruise first!
		qForm.selectMiniCruise();
		
		// Populate form
		// need to do FROM -> HOW -> TO in order to use a car
		
		// FROM 
		qWhere.setDeparturePort(from);
		
		// HOW
		qHow.setVehicleType(vehicle);
		if (vehicle.contentEquals("van") || vehicle.contentEquals("motorhome")) { vehicleIsVan=true;}
		if (vehicleIsVan) {
				qHow.setVehicleLength(length);
				qHow.setVehicleHeight(height);
		}

		
		// TO
		qWhere.setReturnPort(to);
		
		// WHEN
		qWhen.setMCWhenDate(when);
				
		// WHO
		qWho.setNumberOfAdults(adults);
		
		qHow.resetImplicitTimeout();
		qWhen.resetImplicitTimeout();
		qWhere.resetImplicitTimeout();
		qWho.resetImplicitTimeout();
		qForm.resetImplicitTimeout();
	}
	
	@Step
	public void addCabins(String cabins) {
		if (!cabins.contentEquals("")) {
			qForm.setNumberOfCabins(cabins);
		}
	}
	
	@Step
	public void setPromo(String promocode) {
		if (!promocode.contentEquals("")) {
			qForm.setPromoCode(promocode);
		}
	}
	
	@Step
	public void hasFilledInQuote(String from, String back, String dateout, String dateback, String vehicle, String len, String height, String adults, String outtime, String cabins, String promocode) {
	// Populate different parts of the fare finder
		
		qForm.setImplicitTimeout(CommonConstants.PAGETIMEOUT, TimeUnit.SECONDS);
		qWho.setImplicitTimeout(CommonConstants.PAGETIMEOUT, TimeUnit.SECONDS);
		qWhere.setImplicitTimeout(CommonConstants.PAGETIMEOUT, TimeUnit.SECONDS);
		qWhen.setImplicitTimeout(CommonConstants.PAGETIMEOUT, TimeUnit.SECONDS);
		qHow.setImplicitTimeout(CommonConstants.PAGETIMEOUT, TimeUnit.SECONDS);
		
		// WHERE
		qWhere.setDeparturePort(from);
		qWhere.setReturnPort(back);
		
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
			
		// save for later use in working out which id to use
		qWho.setFromRoute(qWhere.getFromRoute());
		qWho.setNumberOfAdults(adults);

		// ODDS
		if (!cabins.contentEquals("")) {
			qForm.setNumberOfCabins(cabins);
		}
		qForm.setPromoCode(promocode);
			
		qHow.resetImplicitTimeout();
		qWhen.resetImplicitTimeout();
		qWhere.resetImplicitTimeout();
		qWho.resetImplicitTimeout();
		qForm.resetImplicitTimeout();
		
	}
	

	@Step
	public void addInfants1(String babies) {
		qWho.setImplicitTimeout(CommonConstants.PAGETIMEOUT, TimeUnit.SECONDS);	
		qWho.setNumberOfInfants1(babies);
		qWho.resetImplicitTimeout();
	}

	@Step
	public void askForQuote() {
		log.info("submitting quote form");
		qForm.setImplicitTimeout(CommonConstants.PAGETIMEOUT, TimeUnit.SECONDS);
		qForm.getAQuote();
		qForm.continueWarningMessage();
		qForm.resetImplicitTimeout();
	}
	
	@Step
	public void askForMCQuote() {
		log.info("submitting quote form");
		qForm.setImplicitTimeout(CommonConstants.PAGETIMEOUT, TimeUnit.SECONDS);
		qForm.getAMCQuote();
		qForm.continueWarningMessage();
		qForm.resetImplicitTimeout();
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
