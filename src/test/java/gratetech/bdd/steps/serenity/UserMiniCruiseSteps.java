package gratetech.bdd.steps.serenity;

import gratetech.bdd.commons.CommonConstants;
import gratetech.bdd.pages.MiniCruiseQuotePage;
import gratetech.bdd.pages.TheExtras;

import java.util.concurrent.TimeUnit;

import net.thucydides.core.annotations.Step;

import org.apache.log4j.Logger;


// Specific user interaction steps for handing minicruise
public class UserMiniCruiseSteps extends UserPurchaseSteps {
	public static Logger log = Logger.getLogger(UserMiniCruiseSteps.class);
	
	private MiniCruiseQuotePage mcQuotePage;
	private TheExtras extras;
	
	@Step
	public void selectTheMCQuote(String offer) {
		// need to click a button - or not
		// then move on
		//we have bronze and silver available to select and move on as radio boxes
		//<input type="radio" value="bruges,bruges_00240777_00241139,destination_minicruise_silver_euhu_hueu_zehu_huze" name="mcOutboundCrossingBundleRadio">

		mcQuotePage.setImplicitTimeout(CommonConstants.PAGETIMEOUT, TimeUnit.SECONDS);
		boolean ok = mcQuotePage.selectQuote(offer);
		if (!ok) { 
			log.error("We may not have selected the correct sailing fare class - CARRY ON"); 
			// TODO Temp code to crash out so I can work it out
			//Assert.fail();
		}
		mcQuotePage.continueToNextPage();
		mcQuotePage.resetImplicitTimeout();
	
	}
	
	@Step 
	public void handleMCExtras() {
		log.info("Not processing extras yet");
		extras.continueToNextPage();
		
	}
}
