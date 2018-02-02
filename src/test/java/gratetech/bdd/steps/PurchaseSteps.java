package gratetech.bdd.steps;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gratetech.bdd.steps.serenity.UserPurchaseSteps;
import gratetech.bdd.steps.serenity.UserQuoteSteps;
import gratetech.bdd.utils.TouristBooking;
import gratetech.bdd.utils.TouristBooking.PassengerType;
import net.thucydides.core.annotations.Steps;

import org.apache.log4j.Logger;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PurchaseSteps {
	
	
public static Logger log = Logger.getLogger(PurchaseSteps.class);
	
	@Steps
	private UserPurchaseSteps David;
	private TouristBooking booking = new TouristBooking();
	
	@Given("^I have obtained a quote for:$")
	public void obtainAQuote(DataTable quotefeeddata) throws Throwable {
		// so we should be logged in
		// then we need to fill in a form and press a button
	    log.info("I have a Quote");
	    List<Map<String, String>> data = quotefeeddata.asMaps(String.class, String.class);
		for (Map map : data) { 
			booking.outboundJ.setUpFrom(map.get("from").toString());
			booking.outboundJ.setUpTo(map.get("return").toString());
			booking.outboundJ.setUpDay(map.get("on date").toString());
			booking.outboundJ.setUpVehicleType(map.get("vehicle").toString());
			booking.outboundJ.setUpVehicleHeight(map.get("height").toString());
			booking.outboundJ.setUpVehicleLength(map.get("length").toString());
			booking.outboundJ.setUpNumberOfPassengers(map.get("adults").toString());
			
			
			// and return?
			booking.returnJ.setUpFrom(map.get("return").toString());
			booking.returnJ.setUpTo(map.get("from").toString());
			booking.returnJ.setUpDay(map.get("coming back").toString());
			booking.setRTNumberOfPassengers(map.get("adults").toString());
			
			booking.setPromo(map.get("promo code").toString());
			
			
			David.hasFilledInQuote(
					map.get("from").toString(),
					map.get("return").toString(),
					map.get("on date").toString(),
					map.get("coming back").toString(),
					map.get("vehicle").toString(),
					map.get("length").toString(),
					map.get("height").toString(),
					map.get("adults").toString(),
					"", // no time set yet 
					map.get("cabin").toString(),
					map.get("promo code").toString()
					);
			David.askForQuote();
		}
	}

	@When("^I select a quote:$")
	public void selectAQuote(DataTable selectthequote) throws Throwable {
		log.info("select a Quote");
		List<Map<String, String>> data = selectthequote.asMaps(String.class, String.class);			
		// so we want to select the quote based on class/time/boat
		for (Map map : data) { 
			
			booking.outboundJ.setUpSailTime(map.get("time").toString());
			booking.outboundJ.setUpShip(map.get("ship").toString());
			booking.outboundJ.setUpOffer(map.get("offer").toString());
			
			David.selectTheQuote(
					map.get("time").toString(),
					map.get("ship").toString(),
					map.get("offer").toString()
					);
		}
			
	}
	
	@And("^I add Extras:$")
	public void AddExtras(DataTable theExtras) throws Throwable {
		log.info("purchase a ticket");
		List<Map<String, String>> data = theExtras.asMaps(String.class, String.class);	
		for (Map map : data) { 
			booking.outboundJ.setUpLegExtras(map.get("RAC").toString(), 
					map.get("WIFI").toString(), 
					map.get("Club Lounge").toString());
			booking.returnJ.setUpLegExtras(map.get("RAC").toString(), 
					map.get("WIFI").toString(), 
					map.get("Club Lounge").toString());
		}
	}
	
	@Then("^I should be able to purchase a ticket using:$")
	public void purchaseATicket(DataTable travellingdetails) throws Throwable {
		log.info("purchase a ticket");
		List<Map<String, String>> data = travellingdetails.asMaps(String.class, String.class);	
		for (Map map : data) { 
			// currently assumes we are only taking adults	  
			  Map<String, PassengerType> pp = new HashMap<String, PassengerType>();
			  String mp = map.get("person").toString();
			  pp.put(mp, TouristBooking.PassengerType.ADULT);
			  int np  = Integer.parseInt(booking.outboundJ.getNumberOfPassgengers());
			  booking.setObPassengers(TouristBooking.onJourney.OUTBOUND, np, pp);
			  
			  // currently assumes those who leave will return
			  // and that all journeys are return
			  booking.setRtPassengers(TouristBooking.onJourney.RETURN, np, pp);
			
			  booking.outboundJ.setUpVehicleRegistration(map.get("vehicle").toString());
			  booking.setVoucher(map.get("voucher").toString());
			  
			  booking.setPayment(map.get("card").toString(), 
					  map.get("account").toString(), 
					  map.get("cvv").toString(), "12-30");
			  
			David.handleExtras();
			David.selectPassengersAndCar(
					map.get("person").toString(),
					map.get("vehicle").toString());
			David.checkBookingSummary();
			David.tickTnc();
			David.selectEVoucher(map.get("voucher").toString());
			David.selectPurchaseNow();
			David.completePurchase(
					map.get("card").toString(),
					map.get("account").toString(), 
					map.get("cvv").toString() );		
		}
		log.info("ticket Purchased - now check it");
		// now check the answer....
		David.verifyPurchase();

	}
}
