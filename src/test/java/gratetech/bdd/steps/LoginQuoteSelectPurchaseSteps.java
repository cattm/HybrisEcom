package gratetech.bdd.steps;

import java.util.HashMap;
import java.util.Map;

import gratetech.bdd.steps.serenity.UserPurchaseSteps;
import gratetech.bdd.utils.TouristBooking;
import gratetech.bdd.utils.TouristBooking.PassengerType;
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
	
	private TouristBooking booking = new TouristBooking();
	
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
		  
		  // start building the booking
		  // missing destination port and reg number of vehicle
		  booking.setOBSailing(this.outDate, this.time, this.fromPort, this.returnPort, this.outShip, this.offer);
		  booking.setOBVehicle(this.vehicle, this.length, this.height, this.reg);
		  //missing destination port
		  booking.setRTSailing(this.returnDate, this.returnTime, this.returnPort, this.fromPort, this.returnShip, this.returnTime);
		  
		  
		  booking.setPromo(promo);
		  booking.setVoucher(voucher);
		  
		  // currently assumes we are only taking adults
		  booking.setOBNumberOfPassengers(this.adults);		  
		  Map<String, PassengerType> pp = new HashMap<String, PassengerType>();
		  String mp = this.passenger;
		  pp.put(mp, TouristBooking.PassengerType.ADULT);		  
		  booking.setObPassengers(TouristBooking.onJourney.OUTBOUND, Integer.parseInt(this.adults), pp);
		  
		  // currently assumes those who leave will return
		  // and that all journeys are return
		  booking.setRTNumberOfPassengers(this.adults);
		  booking.setRtPassengers(TouristBooking.onJourney.RETURN, Integer.parseInt(this.adults), pp);
	}

	@And("^Have Added Extras ([^\"]*) ([^\"]*) ([^\"]*)$")
	public void haveAddedExtras(String rac, String wifi, String clublounge) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		  log.info("Extras");
		    this.extraRAC = rac;
		    this.extraWifi = wifi;
		    this.clubLounge = clublounge;
		   
		  booking.setOBExtras(rac, wifi, clublounge);
		  booking.setRTExtras(rac, wifi, clublounge);
	}

	@And("^Have Card Details ([^\"]*) ([^\"]*) ([^\"]*)$")
	public void haveCardDetails(String card, String account, String cvv) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		  log.info("Card Details loaded");
		  this.purchaseCard = card;
		  this.purchaseAccount = account;
		  this.purchaseCVV = cvv;
		  booking.setPayment(card, account, cvv, "12-30");
	}

	@Then("^My Purchase will succeed$")
	public void purchaseWillSucceed() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		  log.info("Attempting to purchase Ticket");
		 
		  log.info("Outbound Journey Data");
		  booking.outboundJ.dumpLeg();
		  booking.obPassengers.dumpPassenger();
		  log.info("Return Journey Data");
		  booking.returnJ.dumpLeg();
		  booking.rtPassengers.dumpPassenger();
		  log.info("Payment Data");
		  booking.payment.dumpPayment();
		  
		  clive.hasFilledInQuote(booking.outboundJ.getDeparturePort(),
				  booking.returnJ.getDeparturePort(),
				  booking.outboundJ.getDayOfTravel(),
				  booking.returnJ.getDayOfTravel(),
				  booking.outboundJ.getVehicleType(),
				  booking.outboundJ.getVehicleLength(),
				  booking.outboundJ.getVehicleHeight(),
				  // this will need to change
				  booking.outboundJ.getNumberOfPassgengers(),
				  booking.getPromo());
		  clive.askForQuote();
		  clive.selectTheQuote(booking.outboundJ.getTimeOfTravel(), 
				  booking.outboundJ.getShip(), 
				  booking.outboundJ.getOffer());
		  clive.handleExtras();
		  clive.selectPassengersAndCar(booking.obPassengers.getPassengerName(0), 
				  booking.outboundJ.getVehicleReg());
		  log.info("Vehicle is " + booking.outboundJ.getVehicleReg());
		  clive.checkBookingSummary();
		  clive.tickTnc();
		  clive.selectEVoucher(booking.getVoucher());
		  clive.selectPurchase();
		  clive.completePurchase(booking.payment.getPaymentType(), 
				  booking.payment.getAccount(), 
				  booking.payment.getCVV());
		  
	}

}
