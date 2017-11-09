package gratetech.bdd.utils;

import java.util.HashMap;
import java.util.Map;

public class TravelLeg {

	/*
	 * Day
	 * Time
	 * From
	 * To
	 * Ship
	 * Offer
	 * Vehicle
	 * Length
	 * Height
	 * Registration
	 * Number Of Passengers
	 * RAC
	 * WiFi
	 * Cabin
	 */
	Map<String, String> leg = new HashMap<String, String>();
	
	public void setUpLeg(String day, String time, String from, String to, String ship, String offer,
			              String vehicle, String length, String height, String registration, String passengers,
			              String rac, String wifi, String cabin) {
		leg.put("day", day);
		leg.put("time", time);
		leg.put("from", from);
		leg.put("to", to);
		leg.put("ship", ship);
		leg.put("offer", offer);
		leg.put("vehicle", vehicle);
		leg.put("length", length);
		leg.put("height", height);
		leg.put("registration", registration);
		leg.put("passengers", passengers);
		leg.put("rac", rac);
		leg.put("wifi", wifi);
		leg.put("cabin", cabin);
	}
	
	public void setUpLegSailing(String day, String time, String from, String to, String ship, String offer) {
		leg.put("day", day);
		leg.put("time", time);
		leg.put("from", from);
		leg.put("to", to);
		leg.put("ship", ship);
		leg.put("offer", offer);
	}
	
	public void setUpLegVehicle(String vehicle, String length, String height, String registration) {
		leg.put("vehicle", vehicle);
		leg.put("length", length);
		leg.put("height", height);
		leg.put("registration", registration);
	}
	
	public void setUpLegNumberOfPassengers(String passengers) {
		leg.put("passengers", passengers);
	}
	
	public void setUpLegExtras(String rac, String wifi, String cabin) {
		leg.put("rac", rac);
		leg.put("wifi", wifi);
		leg.put("cabin", cabin);
	}
	
	public String getDayOfTravel () {
		return leg.get("day");
	}
	public String getTimeOfTravel () {
		return leg.get("time");
	}
	public String getDeparturePort () {
		return leg.get("from");
	}
	public String getArrivalPort() {
		return leg.get("to");
	}
	public String getShip() {
		return leg.get("ship");
	}
	public String getOffer() {
		return leg.get("offer");
	}
	public String getVehicleType() {
		return leg.get("vehicle");
	}
	public String getVehicleLength() {
		return leg.get("length");
	}
	public String getVehicleHeight() {
		return leg.get("height");
	}
	public String getVehicleReg() {
		return leg.get("registration");
	}
	public String getNumberOfPassgengers() {
		return leg.get("passengers");
	}
	public String getRACExtras() {
		return leg.get("rac");
	}	
	public String getWIFIExtra() {
		return leg.get("wifi");
	}
	public String getCabinExtras() {
		return leg.get("cabin");
	}
	
	
}
