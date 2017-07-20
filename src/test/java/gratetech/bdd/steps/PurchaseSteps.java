package gratetech.bdd.steps;

import java.util.List;
import java.util.Map;

import gratetech.bdd.steps.serenity.UserPurchaseSteps;
import gratetech.bdd.steps.serenity.UserQuoteSteps;
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
	
	@Given("^I have obtained a quote for:$")
	public void obtainAQuote(DataTable quotefeeddata) throws Throwable {
		// so we should be logged in
		// then we need to fill in a form and press a button
	    log.info("I have a Quote");
	    List<Map<String, String>> data = quotefeeddata.asMaps(String.class, String.class);
		for (Map map : data) { 
			David.hasFilledInQuote(
					map.get("from").toString(),
					map.get("return").toString(),
					map.get("on date").toString(),
					map.get("coming back").toString(),
					map.get("vehicle").toString(),
					map.get("length").toString(),
					map.get("height").toString(),
					map.get("adults").toString(),
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
			log.info(map.get("RAC").toString());
			log.info(map.get("WIFI").toString());
			log.info(map.get("Club Lounge").toString());
		}
	}
	
	@Then("^I should be able to purchase a ticket using:$")
	public void purchaseATicket(DataTable travellingdetails) throws Throwable {
		log.info("purchase a ticket");
		List<Map<String, String>> data = travellingdetails.asMaps(String.class, String.class);	
		for (Map map : data) { 
			David.handleExtras();
			David.selectPassengersAndCar(
					map.get("person").toString(),
					map.get("vehicle").toString());
			David.checkBookingSummary();
			David.tickTnc();
			David.selectEVoucher(map.get("voucher").toString());
			David.selectPurchase();
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
