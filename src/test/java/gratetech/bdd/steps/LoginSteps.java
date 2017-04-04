package gratetech.bdd.steps;

import gratetech.bdd.steps.serenity.UserLoginSteps;
import net.thucydides.core.annotations.Steps;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class LoginSteps {
	
	@Steps
	UserLoginSteps marcus;
	
	@Given("^the user is on the Home page$")
	public void onHomePage() throws Throwable {
	    marcus.isOnTheHomePage();
	}

	@When("^the user attempts to login$")
	public void AttemptToLogin() throws Throwable {
	    marcus.attemptsToLogin();
	}

	@Then("^they should see a message \"([^\"]*)\"$")
	public void seeConfirmSuccessMessage(String arg) throws Throwable {
	    marcus.seesConfirmMessage(arg);
	}
}
