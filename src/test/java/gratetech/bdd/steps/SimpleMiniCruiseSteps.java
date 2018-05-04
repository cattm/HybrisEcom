package gratetech.bdd.steps;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gratetech.bdd.interfaces.IBookingValidationStrategy;
import gratetech.bdd.steps.serenity.UserMiniCruiseSteps;
import gratetech.bdd.steps.serenity.UserPurchaseSteps;
import gratetech.bdd.utils.TouristBooking;
import gratetech.bdd.utils.TouristBooking.PassengerType;
import gratetech.bdd.verifications.MiniCruiseStrategy;
import gratetech.bdd.verifications.TouristBookingStrategy;
import net.thucydides.core.annotations.Steps;

import org.apache.log4j.Logger;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class SimpleMiniCruiseSteps {
	public static Logger log = Logger.getLogger(SimpleMiniCruiseSteps.class);
	
	@Steps
	private UserMiniCruiseSteps jalna;
	
	// TODO - the booking should not be visible at this level
	// the user should have the booking
	// and implement an interface to manipulate it
	// there should also be a test policy model on the booking and the workflow
	private TouristBooking booking = new TouristBooking();
	
	// verification object
	private IBookingValidationStrategy strategy = new MiniCruiseStrategy();
	
	// TODO - need to factor out these two temporary variables
	private Integer numPassengersOut;	
	private String adults;
	
	
	@Given("^payment details are:$")
	public void paymentModelIs(DataTable paymentdata) throws Throwable {
		List<Map<String, String>> data = paymentdata.asMaps(String.class, String.class);
		String card = "";
		String account = "";
		String cvv = "";
		for (Map map : data) { 
			card = map.get("card").toString();
			account = map.get("account").toString();
			cvv = map.get("cvv").toString();
		}
		log.info("Card Details loaded......");
		booking.setPayment(card, account, cvv, "12-30");
	}
	
	@Given("^([^\"]*) are able to select a minicruise ([^\"]*) ([^\"]*) ([^\"]*)$")
	public void areAbleToSelectMiniCruise(String adults, String from, String when, String to) throws Throwable {
	    numPassengersOut = Integer.parseInt(adults);
		this.adults = adults;
		
		booking.setOBNumberOfPassengers(adults);	
		booking.outboundJ.setUpFrom(from);
		booking.outboundJ.setUpDay(when);
		booking.returnJ.setUpFrom(to);
		log.info("Minicruise destination set up....");
	}

	
	@And("^they are using a ([^\"]*) of ([^\"]*) and ([^\"]*) identified by ([^\"]*)$")
	public void usingVehicle(String vehicle, String length, String height, String registration) throws Throwable {
		booking.outboundJ.setUpVehicleType(vehicle);
	    booking.outboundJ.setUpVehicleHeight(height);
	    booking.outboundJ.setUpVehicleLength(length);
	    log.info("Vehicle set up......");
	    booking.outboundJ.setUpVehicleRegistration(registration);
		log.info("Registration set up....");
	}
	
	@And("^([^\"]*) are required for ([^\"]*) of ([^\"]*) and ([^\"]*)$")
	public void requireCabinsForPassengers(String cabins, String passengers, String oftype, String offer) throws Throwable {
		// this data may be empty - we may not set cabins or need additional passenger types;
		if (!passengers.contentEquals("")) {
			List<String> ps = Arrays.asList(passengers.split(","));
		
			PassengerType pt = PassengerType.valueOf(oftype.toUpperCase());
			for (String nm : ps ) {
				numPassengersOut++;
				booking.obPassengers.addPassenger(nm, pt);
				booking.rtPassengers.addPassenger(nm, pt);
			}
		
			// Note - setting up the passengers - all legs ASSUMED the same at the moment
			booking.obPassengers.setNumberPassengers(numPassengersOut);
			booking.rtPassengers.setNumberPassengers(numPassengersOut);
			booking.setRTNumberOfPassengers(numPassengersOut.toString());
			booking.setOBNumberOfPassengers(numPassengersOut.toString());
			log.info("And they also had Passengers.....SET UP");
		}	
			
			// CABINS
		if (!cabins.contentEquals("")) {
			booking.outboundJ.setUpCabin(cabins);
			booking.returnJ.setUpCabin(cabins);
			log.info("Cabin set up......");
		}
		
		booking.outboundJ.setUpOffer(offer);
		 log.info("Offer Set up.....");
					
	}
	
	@Given("^they have ([^\"]*) and ([^\"]*) valid for passenger details ([^\"]*)$")
	public void theyHaveVouchersForPassengers(String promo, String voucher, String theadults) throws Throwable {
		//pass straight through
		booking.setPromo(promo);
		booking.setVoucher(voucher);
		log.info("Vouchers set up.....");
		
		// TODO now map the passenger details
		// this data may be empty if there is only one passenger - the booker!
		List<String> adults = Arrays.asList(theadults.split(","));	
		// now add each passenger
		Map<String, PassengerType> pp = new HashMap<String, PassengerType>();
		for (String mp : adults) {
			pp.put(mp, TouristBooking.PassengerType.ADULT);	
		}
		
		// passengers
		booking.setObPassengers(TouristBooking.onJourney.OUTBOUND, numPassengersOut, pp);
		booking.setRtPassengers(TouristBooking.onJourney.RETURN, numPassengersOut, pp);
		log.info("The Adults are......SET UP");
		
	}
	


	@Then("^They Will Book a MiniCruise$")
	public void willBookMiniCruise() throws Throwable {
		log.info("Attempting to Book a MiniCruise");		 
		log.info("Outbound Journey Data");
		booking.outboundJ.dumpLeg();
		booking.obPassengers.dumpPassenger();
		log.info("Return Journey Data");
		booking.returnJ.dumpLeg();
		booking.rtPassengers.dumpPassenger();
		log.info("Payment Data");
		booking.payment.dumpPayment();	
		
		// select minicruise
		// fill in quote
		jalna.hasFilledInMiniCruise(booking.outboundJ.getDeparturePort(), 
				booking.returnJ.getDeparturePort(),
				booking.outboundJ.getVehicleType(),
				booking.outboundJ.getVehicleLength(),
				booking.outboundJ.getVehicleHeight(),
				booking.outboundJ.getDayOfTravel(),
				this.adults);
		
		// add cabins and promo
		jalna.addCabins(booking.outboundJ.getCabin());
		jalna.setPromo(booking.getPromo());
		
		// add additional passenger types?
		
		
		// select quote
		jalna.askForMCQuote();
		
		jalna.selectTheMCQuote(booking.outboundJ.getOffer());
		jalna.handleMCExtras();
				
		// rest of journey is as per tourist
		// people
		// payment
		// ticket verification
		for (int i = 1; i < numPassengersOut; i++) {
			log.info("Selecting passenger " + i + " name of " +  booking.obPassengers.getPassengerName(i-1));
			jalna.selectAdditionalPassengerN(booking.obPassengers.getPassengerName(i-1), i);
		}
			
		// TODO there is something about minicruise and CARS
		// if you do FROM before HOW - you can have cars
		// otherwise no cars
	
		//jalna.selectVehicle(booking.outboundJ.getVehicleReg());
		//log.info("Vehicle is " + booking.outboundJ.getVehicleReg());
		jalna.travellerContinue(); 
		
		jalna.tickTnc();
		jalna.selectEVoucher(booking.getVoucher());
		jalna.checkBookingSummary(booking, strategy);
		  
		jalna.selectPurchaseNow();
		jalna.completePurchase(booking.payment.getPaymentType(), 
				  booking.payment.getAccount(), 
				  booking.payment.getCVV());
		// TBD verify purchase should do some assert checking
		jalna.verifyPurchase(); 
		  
		  // little bit of storing for later
		booking.setPrice(jalna.getfinalPrice());
		booking.setReferencePrice(jalna.getOfferPrice());
		booking.setBookingSummary(jalna.getBookingID());
		booking.setProduct(jalna.getProduct());
	}	
}
