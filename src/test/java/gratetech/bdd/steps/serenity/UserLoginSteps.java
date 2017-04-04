package gratetech.bdd.steps.serenity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;


import org.apache.log4j.Logger;
import gratetech.bdd.pages.LoggedIn;
import gratetech.bdd.pages.PandOHomePage;
import gratetech.bdd.pages.PandOLogin;
import net.thucydides.core.annotations.Step;

public class UserLoginSteps {
	public static Logger log = Logger.getLogger(UserLoginSteps.class);
	PandOHomePage homePage;
	PandOLogin login;
	LoggedIn liMessage;
	
	@Step
	public void isOnTheHomePage() {
		homePage.open();
		homePage.handleCookieMessage();
	}
	
	@Step
	public void attemptsToLogin() {		
		homePage.clickMyAccount();
		login.setUserName("marcus.catt58@googlemail.com");
		login.setPassword("Dogfight789");
		login.clickSubmit();
	}
	
	@Step
	public void seesConfirmMessage(String arg) {
		assertThat(liMessage.getConfirmMessage(),equalToIgnoringCase(arg));
	}
}
