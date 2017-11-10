package gratetech.bdd.utils;

import java.util.HashMap;
import java.util.Map;

public class TouristBooking {

	public TravelLeg outboundJ = new TravelLeg();
	public TravelLeg returnJ   = new TravelLeg();
	public TouristPayment payment = new TouristPayment();
	public Passengers obPassengers = new Passengers();
	public Passengers rtPassengers = new Passengers();
	private String voucher = null;
	private String promo = null;
	
	public enum onJourney {
		BOTH, OUTBOUND, RETURN
	}
	
	public enum Vehicle {
		NONE, CAR, MOTORCYCLE, VAN
	}
	
	public enum Offer {
		BRONZE, SILVER, GOLD, PLATINUM
	}
	
	public enum PassengerType {
		ADULT, CHILD, BABY, PENSIONER, STUDENT
	}


	public void setOutBoundJourney (String day, String time, String from, String to, String ship, String offer,
            String vehicle, String length, String height, String registration, String passengers,
            String rac, String wifi, String cabin) {
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
				cabin);
	}
	
	public void setOBSailing(String day, String time, String from, String to, String ship, String offer) {
		outboundJ.setUpLegSailing(day, time, from, to, ship, offer);
	}
	
	public void setOBVehicle(String vehicle, String length, String height, String registration) {
		outboundJ.setUpLegVehicle(vehicle, length, height, registration);
	}
	
	public void setOBExtras(String rac, String wifi, String cabin) {
		outboundJ.setUpLegExtras(rac, wifi, cabin);	
	}
	
	public void setOBNumberOfPassengers(String passengers) {
		outboundJ.setUpLegNumberOfPassengers(passengers);
	}
	
	public void setReturnJourney (String day, String time, String from, String to, String ship, String offer,
            String vehicle, String length, String height, String registration, String passengers,
            String rac, String wifi, String cabin) {
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
				cabin);
		
	}
	
	public void setRTSailing(String day, String time, String from, String to, String ship, String offer) {
		returnJ.setUpLegSailing(day, time, from, to, ship, offer);
	}
	
	public void setRTVehicle(String vehicle, String length, String height, String registration) {
		returnJ.setUpLegVehicle(vehicle, length, height, registration);
	}
	
	public void setRTExtras(String rac, String wifi, String cabin) {
		returnJ.setUpLegExtras(rac, wifi, cabin);	
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
}
