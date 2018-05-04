package gratetech.bdd.steps;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gratetech.bdd.interfaces.IBookingValidationStrategy;
import gratetech.bdd.models.TouristBooking;
import gratetech.bdd.models.TouristBooking.PassengerType;
import gratetech.bdd.steps.serenity.UserPurchaseSteps;
import gratetech.bdd.verifications.TouristBookingStrategy;
import net.thucydides.core.annotations.Steps;

import org.apache.log4j.Logger;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class ComplexPurchaseWithCabins {
	public static Logger log = Logger.getLogger(ComplexPurchaseWithCabins.class);
	
	@Steps
	private UserPurchaseSteps david;
	private TouristBooking booking = new TouristBooking();
	
	// verification object
	private IBookingValidationStrategy strategy = new TouristBookingStrategy();
	
	// TODO - need to factor out these two temporary variables
	private Integer numPassengersOut;	
	private String adults;
	
	
	
	@Given("^payment model is:$")
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


	// ([^\"]*)
	@Given("([^\"]*) are able to select an outbound ferry ([^\"]*) ([^\"]*) and ([^\"]*) on ([^\"]*)$")
	public void adultsAreAbleToSelectAnOutboundFerryOnDate(String adults, String from, String ondate, String time, String ship) throws Throwable {
		numPassengersOut = Integer.parseInt(adults);
		this.adults = adults;
		// leg
		booking.setOBNumberOfPassengers(adults);
		
		booking.outboundJ.setUpFrom(from);
		booking.outboundJ.setUpDay(ondate);
		booking.outboundJ.setUpSailTime(time);
		booking.outboundJ.setUpShip(ship);
		log.info("Adults Selected Outbound Ferry");
	}

	//And <adults> are able to <return> on <back date> using <back ship> sailing at <back time>
	@And("([^\"]*) are able to ([^\"]*) on ([^\"]*) using ([^\"]*) sailing at ([^\"]*)$")
	public void adultsAreAbletoReturnOnDate(String adults, String returnport, String backdate, String backship, String backtime) throws Throwable {
		// currently assumes we are using same number on both
		booking.setRTNumberOfPassengers(adults);

		booking.returnJ.setUpFrom(returnport);
		booking.returnJ.setUpDay(backdate);
		booking.returnJ.setUpShip(backship);
		booking.returnJ.setUpSailTime(backtime);
		log.info("Adults returning on.......");
		
	}

	
	@And("^The Adults are ([^\"]*)$")
	public void theAdultsAre(String names) throws Throwable {
	    // this is a "," list that we need to parse - assume one name less than number (booking account holder is first adult
		List<String> adults = Arrays.asList(names.split(","));	
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

	
	@Given("^There are ([^\"]*) of ([^\"]*)$")
	public void alsoHavePassengersOfType(String names, String type) throws Throwable {
	    // parse the names and the type - which we will need to convert to an ENUM
		// need to add to the number of passengers and store
		if (!names.contentEquals("")) {
			List<String> ps = Arrays.asList(names.split(","));
		
			PassengerType pt = PassengerType.valueOf(type.toUpperCase());
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
	}

	@And("^require a ([^\"]*) offering$")
	public void requireOffering(String offer) throws Throwable {
		 booking.outboundJ.setUpOffer(offer);
		 log.info("Offer Set up.....");
	}
	
	//And require <cabins> for accommodation
	//Given They require <cabins> for accommodation
	@Given("^They require ([^\"]*) for accommodation$")
	public void haveSetupCabins( String cabins) throws Throwable {
		booking.outboundJ.setUpCabin(cabins);
		booking.returnJ.setUpCabin(cabins);
		log.info("Cabin set up......");
	}

	@Given("^They have a ([^\"]*) with a size of ([^\"]*) by ([^\"]*)$")
	public void haveAVehicle(String vehicle, String length, String height) throws Throwable {
		booking.outboundJ.setUpVehicleType(vehicle);
	    booking.outboundJ.setUpVehicleHeight(height);
	    booking.outboundJ.setUpVehicleLength(length);
	    log.info("Vehicle set up......");
	}
	
	//And identification <registration>
	@And("^identification ([^\"]*)$")
	public void indentification(String registration) throws Throwable {
		booking.outboundJ.setUpVehicleRegistration(registration);
		log.info("Registration set up....");
	}

	//Then if they have  <promo code> and <voucher> with Card Details <card> <account> <cvv>
	@Then("^if they have ([^\"]*) and ([^\"]*)$")
	public void haveCodesAndCards(String promo, String voucher) throws Throwable {
		booking.setPromo(promo);
		booking.setVoucher(voucher);
		log.info("Vouchers set up.....");
	}

	@Then("^They will succeed$")
	public void theyWillSucceed() throws Throwable {
		
		log.info("Attempting to purchase Ticket");
		 
		log.info("Outbound Journey Data");
		booking.outboundJ.dumpLeg();
		booking.obPassengers.dumpPassenger();
		log.info("Return Journey Data");
		booking.returnJ.dumpLeg();
		booking.rtPassengers.dumpPassenger();
		log.info("Payment Data");
		booking.payment.dumpPayment();
		  
		david.hasFilledInQuote(booking.outboundJ.getDeparturePort(),
				  booking.returnJ.getDeparturePort(),
				  booking.outboundJ.getDayOfTravel(),
				  booking.returnJ.getDayOfTravel(),
				  booking.outboundJ.getVehicleType(),
				  booking.outboundJ.getVehicleLength(),
				  booking.outboundJ.getVehicleHeight(),
				  // this will need to change
				  //booking.outboundJ.getNumberOfPassengers(),
				  this.adults,
				  booking.outboundJ.getTimeOfTravel(),
				  booking.outboundJ.getCabin(),
				  booking.getPromo());
		
		// TODO: now need to populate the kids box - this needs to come from booking data stored - currently using OB
		Integer numbabies = Integer.valueOf(booking.getNumberOBPassengerOfType(PassengerType.BABY)); 
		if (numbabies > 0) {
			log.info("We have Children");
			david.addInfants1(numbabies.toString());
		}
		
		david.askForQuote();
		david.selectTheQuote(booking.outboundJ.getTimeOfTravel(), 
				  booking.outboundJ.getShip(), 
				  booking.outboundJ.getOffer());
		david.handleExtras();
		  
		// Note this works for upto 4 passengers including a mix of adults and children
		for (int i = 1; i < numPassengersOut; i++) {
			log.info("Selecting passenger " + i + " name of " +  booking.obPassengers.getPassengerName(i-1));
			david.selectAdditionalPassengerN(booking.obPassengers.getPassengerName(i-1), i);
		}
			
		david.selectVehicle(booking.outboundJ.getVehicleReg());
		log.info("Vehicle is " + booking.outboundJ.getVehicleReg());
		david.travellerContinue(); 
		
		david.tickTnc();
		david.selectEVoucher(booking.getVoucher());
		david.checkBookingSummary(booking, strategy);
		  
		david.selectPurchaseNow();
		david.completePurchase(booking.payment.getPaymentType(), 
				  booking.payment.getAccount(), 
				  booking.payment.getCVV());
		// TBD verify purchase should do some assert checking
		david.verifyPurchase(); 
		  
		  // little bit of storing for later
		booking.setPrice(david.getfinalPrice());
		booking.setReferencePrice(david.getOfferPrice());
		booking.setBookingSummary(david.getBookingID());
		booking.setProduct(david.getProduct());
	}

}
