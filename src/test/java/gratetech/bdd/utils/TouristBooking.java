package gratetech.bdd.utils;
// model of a Tourist Booking - with options to read and write to CSV file
import gratetech.bdd.interfaces.IWriteCSV;

import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TouristBooking {

	private static Boolean fileOpen = false;
	public TravelLeg outboundJ = new TravelLeg();
	public TravelLeg returnJ   = new TravelLeg();
	public TouristPayment payment = new TouristPayment();
	public Passengers obPassengers = new Passengers();
	public Passengers rtPassengers = new Passengers();
	private String voucher = null;
	private String promo = null;
	
	private String bookingSummary = null;
	private String product = null;
	private String price = null;
	private String referencePrice = null;
	
	
	public enum onJourney {
		BOTH, OUTBOUND, RETURN
	}
	
	public enum Vehicle {
		NONE, CAR, MINIBUS, MOTORCYCLE, MOTORHOME, VAN
	}
	
	public enum Offer {
		BRONZE, SILVER, GOLD, PLATINUM
	}
	
	public enum PassengerType {
		ADULT, CHILD, BABY, PENSIONER, STUDENT
	}



	public void setOutBoundJourney (String day, String time, String from, String to, String ship, String offer,
            String vehicle, String length, String height, String registration, String passengers,
            String rac, String wifi, String club) {
		outboundJ.setUpLeg(day, 
				time, 
				from, 
				to, 
				ship, 
				offer, 
				vehicle, 
				length, 
				height, 
				registration, 
				passengers, 
				rac, 
				wifi, 
				club);
	}
	
	public void setOBSailing(String day, String time, String from, String to, String ship, String offer) {
		outboundJ.setUpLegSailing(day, time, from, to, ship, offer);
	}
	
	public void setOBVehicle(String vehicle, String length, String height, String registration) {
		outboundJ.setUpLegVehicle(vehicle, length, height, registration);
	}
	
	public void setOBExtras(String rac, String wifi, String club) {
		outboundJ.setUpLegExtras(rac, wifi, club);	
	}
	
	public void setOBNumberOfPassengers(String passengers) {
		outboundJ.setUpLegNumberOfPassengers(passengers);
	}
	
	public void setReturnJourney (String day, String time, String from, String to, String ship, String offer,
            String vehicle, String length, String height, String registration, String passengers,
            String rac, String wifi, String club) {
		returnJ.setUpLeg(day, 
				time, 
				from, 
				to, 
				ship, 
				offer, 
				vehicle, 
				length, 
				height, 
				registration, 
				passengers, 
				rac, 
				wifi, 
				club);
		
	}
	
	public void setRTSailing(String day, String time, String from, String to, String ship, String offer) {
		returnJ.setUpLegSailing(day, time, from, to, ship, offer);
	}
	
	public void setRTVehicle(String vehicle, String length, String height, String registration) {
		returnJ.setUpLegVehicle(vehicle, length, height, registration);
	}
	
	public void setRTExtras(String rac, String wifi, String club) {
		returnJ.setUpLegExtras(rac, wifi, club);	
	}
	
	public void setRTNumberOfPassengers(String passengers) {
		returnJ.setUpLegNumberOfPassengers(passengers);
	}
	public void setObPassengers (onJourney theleg, int number, Map<String, PassengerType> nametype) {
		obPassengers.setUpPassengers(theleg, number);
		for (String key: nametype.keySet()) {
			obPassengers.addPassenger(key, nametype.get(key));
		}
	}
	
	public void setRtPassengers(onJourney theleg, int number, Map<String, PassengerType> nametype) {
		rtPassengers.setUpPassengers(theleg, number);
		for (String key: nametype.keySet()) {
			rtPassengers.addPassenger(key, nametype.get(key));
		}
	}
	
	public void setPayment(String type, String account, String cvv, String expiry) {
		payment.setUpPayment(type, account, cvv, expiry);
	}
	
	public void setVoucher(String v) {
		voucher = v;
	}
	
	public void setPromo(String p) {
		promo = p;
	}
	
	public String getVoucher() {
		return voucher;
	}
	
	public String getPromo() {
		return promo;
	}
	
	public void setBookingSummary(String p) {
		bookingSummary = p;
	}
	
	public String getBookingSummary() {
		return bookingSummary;
	}
	
	public void setProduct(String p) {
		product = p;
	}
	
	public String getProduct() {
		return product;
	}
	
	public void setPrice(String p) {
		price = p;
	}
	
	public String getPrice() {
		return price;
	}
	
	public void setReferencePrice(String p) {
		referencePrice = p;
	}
	
	public String getReferencePrice() {
		return referencePrice;
	}
	
	public String getOBPassengerOfType(PassengerType pt) {
		return obPassengers.getPassengersOfType(pt);
	}
	
	public int getNumberOBPassengerOfType(PassengerType pt) {
		return obPassengers.getNumberOfPassengersOfType(pt);
	}

	public boolean buildCSV(String tofile) {
		if (!fileOpen) {
			WriteCsv.configureWriter(tofile);
			return true;
		}
		return false;
	}
	
	public void writeBookingHeaders() {
		
		WriteCsv.writeRawString("OBJ H,");
		String v1 = outboundJ.legKeyList(",", null);
		WriteCsv.writeRawString(v1);

		WriteCsv.writeRawString("RTJ H,");
		String v2 = returnJ.legKeyList(",", null);
		WriteCsv.writeRawString(v2);

		WriteCsv.writeRawString("OBP H,");
		String v3 = obPassengers.passengerKeyList(",", null);
		WriteCsv.writeRawString(v3);

		WriteCsv.writeRawString("RTP H,");
		String v4 = rtPassengers.passengerKeyList(",", null);
		WriteCsv.writeRawString(v4);

		WriteCsv.writeRawString("PAY H,");
		String v5 = payment.paymentKeyList(",", null);
		WriteCsv.writeRawString(v5);	

		WriteCsv.writeRawString("Summary H,");	
		WriteCsv.writeRawString("voucher,");
		WriteCsv.writeRawString("promo,");		
		WriteCsv.writeRawString("bookingSummary,");
		WriteCsv.writeRawString("product,");
		WriteCsv.writeRawString("price,");
		WriteCsv.writeRawLine("referencePrice");
	}
	
	public void writeBookingContent() {
		WriteCsv.writeRawString("OBJ V,");
		String v1 = outboundJ.legValList(",", null);
		WriteCsv.writeRawString(v1);

		WriteCsv.writeRawString("RTJ V,");
		String v2 = returnJ.legValList(",", null);
		WriteCsv.writeRawString(v2);	

		WriteCsv.writeRawString("OBP V,");
		String v3 = obPassengers.passengerValList(",", null);
		WriteCsv.writeRawString(v3);

		WriteCsv.writeRawString("RTP V,");
		String v4 = rtPassengers.passengerValList(",", null);
		WriteCsv.writeRawString(v4);
		
		WriteCsv.writeRawString("PAY V,");
		String v5 = payment.paymentValList(",", null);
		WriteCsv.writeRawString(v5);
		
		WriteCsv.writeRawString("Summary V,");
		WriteCsv.writeRawString(voucher + ",");
		WriteCsv.writeRawString(promo + ",");		
		WriteCsv.writeRawString(bookingSummary + ",");
		WriteCsv.writeRawString(product + ",");
		WriteCsv.writeRawString(price + ",");
		WriteCsv.writeRawLine(referencePrice);
	}
	
	public void closeCSV() {
		WriteCsv.closeWriter();
	}
}
