package gratetech.bdd.steps;

import gratetech.bdd.steps.serenity.UserPurchaseSteps;
import net.thucydides.core.annotations.Steps;

import org.apache.log4j.Logger;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class LoginQuoteSelectPurchaseSteps {
	public static Logger log = Logger.getLogger(LoginQuoteSelectPurchaseSteps.class);
	
	@Steps
	private UserPurchaseSteps clive;
	private String fromPort;
	private String returnPort;
	private String outDate;
	private String returnDate;
	private String vehicle;
	private String reg;
	private String length;
	private String height;
	private String adults;
	private String outShip;
	private String returnShip;
	private String time;
	private String returnTime;
	private String offer;
	private String promoCode;
	private String passenger;
	private String voucher;
	private String extraRAC;
	private String extraWifi;
	private String clubLounge;
	private String purchaseCard;
	private String purchaseAccount;
	private String purchaseCVV;
	
	@Given("^([^\"]*) are able to select an outbound ferry ([^\"]*) ([^\"]*) with a ([^\"]*) of ([^\"]*) and ([^\"]*) with a ([^\"]*) sailing ([^\"]*) and ([^\"]*)$")
	public void ableToSelectOutbound(String adults, String from, String ondate, String vehicle, String length, String height, String ship, String time, String offer) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    log.info("SelectOutbound");
	    this.fromPort = from;	    
	    this.outDate = ondate;	   
	    this.vehicle = vehicle;
	    this.length = length;
	    this.height = height;
	    this.adults = adults;
	    this.outShip = ship;
	    this.time = time;
	    this.offer = offer;
	
		
	}
	//They are <return> on <back date> using <back ship> sailing at <back time>
	@And("^They are ([^\"]*) on ([^\"]*) using ([^\"]*) sailing at ([^\"]*)$")
	public void theyAreComingBack(String returnport, String comingback, String retship, String sailtime) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		  log.info("And Coming Back");
		  this.returnPort = returnport;
		  this.returnDate = comingback;
		  this.returnShip = retship;
		  this.returnTime = sailtime;
	}

	@And("^Have ([^\"]*) and passenger details ([^\"]*) and car details ([^\"]*) and ([^\"]*)$")
	public void havePaassengerDetails(String promo, String passenger, String registration, String voucher) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		  log.info("Passenger and promo");
		  this.promoCode = promo;
		  this.passenger = passenger;
		  this.reg = registration;
		  this.voucher = voucher;
	}

	@And("^Have Added Extras ([^\"]*) ([^\"]*) ([^\"]*)$")
	public void haveAddedExtras(String rac, String wifi, String clublounge) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		  log.info("Extras");
		    this.extraRAC = rac;
		    this.extraWifi = wifi;
		    this.clubLounge = clublounge;
		   
	}

	@And("^Have Card Details ([^\"]*) ([^\"]*) ([^\"]*)$")
	public void haveCardDetails(String card, String account, String cvv) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		  log.info("Card Details loaded");
		  this.purchaseCard = card;
		  this.purchaseAccount = account;
		  this.purchaseCVV = cvv;
	}

	@Then("^My Purchase will succeed$")
	public void purchaseWillSucceed() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		  log.info("Attempting to purchase Ticket");
		  clive.hasFilledInQuote(this.fromPort,
					this.returnPort,
					this.outDate,
					this.returnDate,
					this.vehicle,
					this.length,
					this.height,
					this.adults,
					this.promoCode);
		  clive.askForQuote();
		  clive.selectTheQuote(this.time, this.outShip, this.offer);
		  clive.handleExtras();
		  clive.selectPassengersAndCar(this.passenger, this.reg);
		  clive.checkBookingSummary();
		  clive.tickTnc();
		  clive.selectEVoucher(this.voucher);
		  clive.selectPurchase();
		  clive.completePurchase(this.purchaseCard, this.purchaseAccount, this.purchaseCVV);
	}

}
