package gratetech.bdd.steps;

import java.util.List;
import java.util.Map;

import gratetech.bdd.steps.serenity.UserLoginSteps;
import gratetech.bdd.steps.serenity.UserQuoteSteps;

import org.apache.log4j.Logger;
import net.thucydides.core.annotations.Steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class QuoteSteps {
	public static Logger log = Logger.getLogger(QuoteSteps.class);
	
	@Steps
	UserQuoteSteps Marcus;
		
	@Given("^user has logged in:$")
	public void userLoggedIn(DataTable logindata) throws Throwable {
		
		List<Map<String, String>> data = logindata.asMaps(String.class, String.class);
		for (Map map : data) { 
			Marcus.hasLoggedIn(
					map.get("username").toString(),
					map.get("pw").toString(),
					map.get("greeting").toString());
		}
	}

	@Given("^the user has selected:$")
	public void hasSelected(DataTable quotefeeddata) throws Throwable {    
		log.info("Select for Quote");
		List<Map<String, String>> data = quotefeeddata.asMaps(String.class, String.class);
		for (Map map : data) { 
			Marcus.hasFilledInQuote(
					map.get("from").toString(),
					map.get("return").toString(),
					map.get("on date").toString(),
					map.get("coming back").toString(),
					map.get("vehicle").toString(),
					map.get("length").toString(),
					map.get("height").toString(),
					map.get("adults").toString(),
					"", // no time set for quote
					map.get("promo code").toString()
					);
		}
		 
	}

	@When("^the user asks for a quote$")
	public void asksForQuote() throws Throwable {
		log.info("Ask for Quote");
		Marcus.askForQuote();
	}

	@Then("^they should see a quote page:$")
	public void shouldSeeQuotePage(DataTable checkdata) throws Throwable {
		log.info("Should See a Quote");
		List<Map<String, String>> data = checkdata.asMaps(String.class, String.class);
		for (Map map : data) { 
			Marcus.getQuoteTitle(map.get("greeting").toString());
		}
	}
}
