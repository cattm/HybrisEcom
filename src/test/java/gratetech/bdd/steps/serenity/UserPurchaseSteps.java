package gratetech.bdd.steps.serenity;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.containsString;

import java.util.concurrent.TimeUnit;

import gratetech.bdd.commons.CommonConstants;
import gratetech.bdd.pages.BookingConfirm;
import gratetech.bdd.pages.BookingSummary;
import gratetech.bdd.pages.OrderDetails;
import gratetech.bdd.pages.PassengerAndCar;
import gratetech.bdd.pages.PayNowForm;
import gratetech.bdd.pages.PayPalLogin;
import gratetech.bdd.pages.PayPalPurchaseConfirm;
import gratetech.bdd.pages.TheExtras;
import gratetech.bdd.pages.TheQuotePage;
import gratetech.bdd.pages.VisaForm;
import net.thucydides.core.annotations.Step;

import org.apache.log4j.Logger;
import org.junit.Assert;

public class UserPurchaseSteps extends UserQuoteSteps {
	public static Logger log = Logger.getLogger(UserPurchaseSteps.class);

	private TheQuotePage quote;
	private TheExtras extras;
	private PassengerAndCar traveller;
	private BookingSummary summary;
	private PayNowForm paynow;
	private OrderDetails order;
	private VisaForm visa;
	private PayPalLogin ppLogin;
	private BookingConfirm confirmationPage;
	private PayPalPurchaseConfirm ppConfirm;
	
	private String checkRef = "";
	private String product = "";
	private String offerPrice = "";
	private String booking = "";
	private String finalPrice = "";
	
	@Step
	public void selectTheQuote(String time, String ship, String offer) {
		quote.setImplicitTimeout(CommonConstants.PAGETIMEOUT, TimeUnit.SECONDS);
		boolean ok = quote.selectOutbound(time, ship, offer);
		if (!ok) { 
			log.error("We may not have selected the correct sailing fare class - CARRY ON"); 
			// TODO Temp code to crash out so I can work it out
			//Assert.fail();
		}
		quote.continueToNextPage();
		quote.resetImplicitTimeout();
	}
	
	@Step
	public void selectReturnQuote(String time, String ship, String offer) {
		log.info("Not implemented");
	}	
	
	
	@Step 
	public void handleExtras() {
		log.info("Not processing extras yet");
		extras.continueToNextPage();
	}
	
	//TODO - replace this very poor solution
	@Step
	public void selectPassengersAndCar(String passenger, String vehicle) {
		log.info("select Passenger and Car");
		traveller.setImplicitTimeout(CommonConstants.PAGETIMEOUT, TimeUnit.SECONDS);
		if (!passenger.contentEquals("")) { 
			traveller.selectPassenger1(passenger);	
		}
		if (!vehicle.contentEquals("")) {
			traveller.selectVehicle(vehicle);
		}
		traveller.continueToNextPage();	
		traveller.resetImplicitTimeout();
	}
	
	@Step
	public void selectVehicle(String vehicle) {
		log.info("select Vehicle " + vehicle);
		traveller.setImplicitTimeout(CommonConstants.PAGETIMEOUT, TimeUnit.SECONDS);
		if (!vehicle.contentEquals("")) {
			traveller.selectVehicle(vehicle);
		}
		traveller.resetImplicitTimeout();
	}
	
	@Step
	public void travellerContinue() {
		traveller.continueToNextPage();	
		traveller.resetImplicitTimeout();
	}
	
	@Step
	public void selectAdditionalPassengerN(String passenger, int n) {
		log.info("select Passenger case " + n + " name " + passenger);
		traveller.setImplicitTimeout(CommonConstants.PAGETIMEOUT, TimeUnit.SECONDS);
		if (!passenger.contentEquals("")) { 
			switch (n) {
			case 1 :
				traveller.selectPassenger1(passenger);
				break;
			case 2 : 
				traveller.selectPassenger2(passenger);
				break;
			case 3 : 
				traveller.selectPassenger3(passenger);
				break;
			default : 
				traveller.selectPassenger1(passenger);
				break;
			}
		}
		traveller.resetImplicitTimeout();
	}
	
	@Step 
	public void checkBookingSummary() {
		//REF: <47587110>
		
		checkRef = summary.getBookingReference().replace("REF: <", "");
		checkRef = checkRef.replace(">", "");
		log.info("checkBookingSummary " + checkRef);
	}
	
	@Step
	public void tickTnc() {
		paynow.setImplicitTimeout(CommonConstants.PAGETIMEOUT, TimeUnit.SECONDS);
		log.info("tick the Terms");
		paynow.tickTnC();
		
	}
	
	@Step 
	public void selectEVoucher(String voucher) {
		log.info("select voucher");
		paynow.eVoucher("some voucher");
		return;
	}

	@Step
	public void selectPurchaseNow () {
		log.info("do a purchase and check it");
		paynow.purchaseNow();
		paynow.resetImplicitTimeout();
		
	}
	
	@Step 
	public void completePurchase (String card, String account, String cvv) {
		// TBD remove these horrible wait loops
		// get some data for verification
		order.setImplicitTimeout(CommonConstants.PAGETIMEOUT, TimeUnit.SECONDS);
		/*try {
			Thread.sleep(5000);
			log.info("waiting....");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
		log.info(order.getHeading());
		product = order.getProduct();
		offerPrice = cleanNumericalString(order.getAmount());
		log.info( "Product is " + product);
		log.info("Price offered is " + offerPrice);
		log.info(" Address is " + order.getAddress());
		
		if (card.contains("visa")) {			
			order.selectVisaPayment();
			order.resetImplicitTimeout();
		
			// now we fill in the visa form
			visa.setCardNumber(account);
			visa.selectMonth("12");
			visa.selectYear("30");
			visa.setCSC(cvv);
			visa.submitPayment();
		} else if (card.contains(("paypal"))) {
			// assume Paypal
			// the data is marcus.catt@poferries.com/Password1
			// need to select paypal
			order.selectPayPalPayment();
			order.resetImplicitTimeout();
			
			// log in to paypal
			ppLogin.setImplicitTimeout(CommonConstants.PAGETIMEOUT, TimeUnit.SECONDS);
			ppLogin.setEmail("marcus.catt@poferries.com");
			ppLogin.setPassword("Password1");
			ppLogin.submit();
			//log.info("SUBMIT PRESSED");
			
			ppConfirm.setImplicitTimeout(CommonConstants.PAGETIMEOUT, TimeUnit.SECONDS);
			ppConfirm.confirmPurchase();
			ppLogin.resetImplicitTimeout();
			ppConfirm.resetImplicitTimeout();
			
		} // else ?
		
	}

	@Step
	public void verifyPurchase() {
		log.info("verifyPurchase");
		// TODO: remove this code
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		confirmationPage.setImplicitTimeout(CommonConstants.PAGETIMEOUT, TimeUnit.SECONDS);
		booking = cleanTheBooking();
		log.info(booking);
		
		log.info(confirmationPage.getTotalCost());
		finalPrice = cleanNumericalString(confirmationPage.getTotalCostCharged());
		log.info(finalPrice);
		// assertThat(booking, containsString(checkRef));
		assertThat(finalPrice,equalToIgnoringCase(offerPrice));
		confirmationPage.resetImplicitTimeout();
	}
	 
	// Getters and little utils
	public String getBookingID() {
		return booking;
	}
	
	public String getCheckRef() {
		return checkRef;
	}
	
	public String getfinalPrice() {
		return finalPrice;
	}
	
	public String getProduct() {
		return product;
	}
	
	public String getOfferPrice() {
		return offerPrice;
	}
	
	private String cleanNumericalString(String toclean) {
		String result = toclean.replaceAll("[^0-9.]", "");
		return result;
	}
	
	private String cleanTheBooking() {
		String tmp = confirmationPage.getBooking();
		return tmp.replaceAll("[^0-9]", "");
	}

}
