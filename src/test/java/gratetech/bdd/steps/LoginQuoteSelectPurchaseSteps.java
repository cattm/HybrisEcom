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
	private String adults;	
	private TouristBooking booking = new TouristBooking();
	
	@Given("^([^\"]*) are able to select an outbound ferry ([^\"]*) ([^\"]*) with a ([^\"]*) of ([^\"]*) and ([^\"]*) with a ([^\"]*) sailing ([^\"]*) and ([^\"]*)$")
	public void ableToSelectOutbound(String adults, String from, String ondate, String vehicle, String length, String height, String ship, String time, String offer) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    log.info("SelectOutbound");     
	    this.adults = adults;
	   
	    booking.outboundJ.setUpFrom(from);
	    booking.outboundJ.setUpDay(ondate);
	    booking.outboundJ.setUpVehicleType(vehicle);
	    booking.outboundJ.setUpVehicleHeight(height);
	    booking.outboundJ.setUpVehicleLength(length);
	    booking.outboundJ.setUpShip(ship);
	    booking.outboundJ.setUpSailTime(time);
	    booking.outboundJ.setUpOffer(offer);
	    
	 
	}
	//They are <return> on <back date> using <back ship> sailing at <back time>
	@And("^They are ([^\"]*) on ([^\"]*) using ([^\"]*) sailing at ([^\"]*)$")
	public void theyAreComingBack(String returnport, String comingback, String retship, String sailtime) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		  log.info("And Coming Back");	  
		  booking.outboundJ.setUpTo(returnport);
		 
		  //missing destination port
		  booking.setRTSailing(comingback, 
				  sailtime, 
				  returnport, 
				  booking.outboundJ.getDeparturePort(), 
				  retship, 
				  booking.outboundJ.getOffer());
		
	}

	@And("^Have ([^\"]*) and passenger details ([^\"]*) and car details ([^\"]*) and ([^\"]*)$")
	public void havePaassengerDetails(String promo, String passenger, String registration, String voucher) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		  log.info("Passenger and promo");	  
		  booking.outboundJ.setUpVehicleRegistration(registration);
		  booking.setPromo(promo);
		  booking.setVoucher(voucher);
	  
		  // currently assumes we are only taking adults
		  booking.setOBNumberOfPassengers(this.adults);		  
		  Map<String, PassengerType> pp = new HashMap<String, PassengerType>();
		  String mp = passenger;
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
		  booking.setOBExtras(rac, wifi, clublounge);
		  booking.setRTExtras(rac, wifi, clublounge);
	}

	@And("^Have Card Details ([^\"]*) ([^\"]*) ([^\"]*)$")
	public void haveCardDetails(String card, String account, String cvv) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		  log.info("Card Details loaded");
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
				  booking.outboundJ.getTimeOfTravel(),
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
		  clive.selectPurchaseNow();
		  clive.completePurchase(booking.payment.getPaymentType(), 
				  booking.payment.getAccount(), 
				  booking.payment.getCVV());
		  // TBD verify purchase should do some assert checking
		  clive.verifyPurchase(); 
		  
		  // little bit of storing
		  booking.setPrice(clive.getfinalPrice());
		  booking.setReferencePrice(clive.getOfferPrice());
		  booking.setBookingSummary(clive.getBookingID());
		  booking.setProduct(clive.getProduct());
		  
		  // we want to build or append the "booking" to a csv file
		  // we will param this off test instance and properties later
		  // we may need to create a new file or append a row to existing and close if last row
		  Boolean newfile = booking.buildCSV("/Users/marcus/Documents/edge2016/pando/target/scenario/interim/testscenario.csv");
		  if (newfile) {
			  booking.writeBookingHeaders();
		  }
		  booking.writeBookingContent();
		  booking.closeCSV();
		  
	}

}
