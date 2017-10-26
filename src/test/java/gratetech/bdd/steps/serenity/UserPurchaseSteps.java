package gratetech.bdd.steps.serenity;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.containsString;
import java.util.concurrent.TimeUnit;
import gratetech.bdd.pages.BookingConfirm;
import gratetech.bdd.pages.BookingSummary;
import gratetech.bdd.pages.OrderDetails;
import gratetech.bdd.pages.PassengerAndCar;
import gratetech.bdd.pages.PayNowForm;
import gratetech.bdd.pages.TheExtras;
import gratetech.bdd.pages.TheQuotePage;
import gratetech.bdd.pages.VisaForm;
import net.thucydides.core.annotations.Step;

import org.apache.log4j.Logger;

public class UserPurchaseSteps extends UserQuoteSteps {
	public static Logger log = Logger.getLogger(UserPurchaseSteps.class);

	private TheQuotePage quote;
	private TheExtras extras;
	private PassengerAndCar traveller;
	private BookingSummary summary;
	private PayNowForm paynow;
	private OrderDetails order;
	private VisaForm visa;
	private BookingConfirm confirmationPage;
	
	private String checkRef = "";
	private String product = "";
	private String offerPrice = "";
	private String booking = "";
	private String finalPrice = "";
	
	@Step
	public void selectTheQuote(String time, String ship, String offer) {
		quote.setImplicitTimeout(10, TimeUnit.SECONDS);
		quote.selectOutbound(time, ship, offer);
		quote.continueToNextPage();
		quote.resetImplicitTimeout();
	}
	
	@Step
	public void selectReturnQuote(String time, String ship, String offer) {
		log.info("Not implemented");
	}	
	
	
	@Step 
	public void handleExtras() {
		extras.continueToNextPage();
	}
	
	@Step
	public void selectPassengersAndCar(String passenger, String vehicle) {
		log.info("select Passenger and Car");
		traveller.setImplicitTimeout(10, TimeUnit.SECONDS);
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
	public void checkBookingSummary() {
		//REF: <47587110>
		
		checkRef = summary.getBookingReference().replace("REF: <", "");
		checkRef = checkRef.replace(">", "");
		log.info("checkBookingSummary " + checkRef);
	}
	
	@Step
	public void tickTnc() {
		paynow.setImplicitTimeout(10, TimeUnit.SECONDS);
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
	public void selectPurchase () {
		log.info("do a purchase and check it");
		paynow.purchaseNow();
		paynow.resetImplicitTimeout();
		
	}
	
	@Step 
	public void completePurchase (String card, String account, String cvv) {
			
		// get some data for verification
		order.setImplicitTimeout(10, TimeUnit.SECONDS);
		try {
			Thread.sleep(5000);
			log.info("waiting....");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info(order.getHeading());
		product = order.getProduct();
		offerPrice = order.getAmount();
		log.info( "Product is " + product);
		log.info("Price offered is " + offerPrice);
		log.info(order.getAddress());
		
		if (card.contains("visa")) {			
			order.selectVisaPayment();
			order.resetImplicitTimeout();
		
			// now we fill in the visa form
			visa.setCardNumber(account);
			visa.selectMonth("12");
			visa.selectYear("30");
			visa.setCSC(cvv);
			visa.submitPayment();
		} // else paypal
		
	}

	@Step
	public void verifyPurchase() {
		log.info("verifyPurchase");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		confirmationPage.setImplicitTimeout(10, TimeUnit.SECONDS);
		booking = confirmationPage.getBooking();
		log.info(booking);
		log.info(confirmationPage.getTotalCost());
		finalPrice = confirmationPage.getTotalCostCharged();
		log.info(finalPrice);
		// assertThat(booking, containsString(checkRef));
		assertThat(finalPrice,equalToIgnoringCase(offerPrice));
		confirmationPage.resetImplicitTimeout();
	}
	
	@Step 
	public String getBookingID() {
		return booking;
	}
	
	// Tests are:
	// 
	//
	//
}
