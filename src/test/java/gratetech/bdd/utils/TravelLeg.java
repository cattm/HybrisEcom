package gratetech.bdd.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class TravelLeg {
	public static Logger log = Logger.getLogger(TravelLeg.class);
	
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
	 * Number Of Passengers --- this is the total for the leg
	 * RAC
	 * WiFi
	 * Cabin
	 */
	Map<String, String> leg = new HashMap<String, String>();
	
	public void setUpLeg(String day, String time, String from, String to, String ship, String offer,
			              String vehicle, String length, String height, String registration, String passengers,
			              String rac, String wifi, String club) {
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
		leg.put("club", club);
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
	
	public void setUpLegExtras(String rac, String wifi, String club) {
		leg.put("rac", rac);
		leg.put("wifi", wifi);
		leg.put("club", club);
	}
	
	// individual setters
	public void setUpDay (String p) {
		leg.put("day", p);
	}	
	public void setUpSailTime(String p) {
		leg.put("time", p);
	}
	public void setUpFrom(String p) {
		leg.put("from", p);
	}
	public void setUpTo(String p) {
		leg.put("to", p);
	}
	public void setUpShip(String p) {
		leg.put("ship", p);
	}
	public void setUpOffer(String p) {
		leg.put("offer", p);
	}
	public void setUpVehicleType(String p) {
		leg.put("vehicle", p);
	}
	public void setUpVehicleLength(String p) {
		leg.put("length", p);
	}
	public void setUpVehicleHeight(String p) {
		leg.put("height", p);
	}
	public void setUpVehicleRegistration(String p) {
		leg.put("registration", p);
	}
	public void setUpNumberOfPassengers(String p) {
		leg.put("passengers", p);
	}
	public void setUpRAC(String p) {
		leg.put("rac", p);
	}
	public void setUpWIFI(String p) {
		leg.put("wifi", p);
	}
	public void setUpClub(String p) {
		leg.put("club", p);
	}
	public void setUpCabin(String p) {
		leg.put("cabin", p);
	}
	
	// individual getters
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
	public String getNumberOfPassengers() {
		return leg.get("passengers");
	}
	public String getRACExtras() {
		return leg.get("rac");
	}	
	public String getWIFIExtra() {
		return leg.get("wifi");
	}
	public String getClubExtras() {
		return leg.get("club");
	}
	
	public String getCabin() {
		return leg.get("cabin");
	}
	
	public void dumpLeg() {
		log.info("Travel Leg information is : ");
		for (String key : leg.keySet()) {
		    String value = leg.get(key);
		    log.info("Key = " + key + ", Value = " + value);
		}
		log.info("Travel Leg Dumped ");
	}
	
	public String legKeyList (String sep, String ret) {
		StringBuilder builder = new StringBuilder();
		for (Map.Entry<String, String> kvp : leg.entrySet()) {
		    builder.append(kvp.getKey());
		    builder.append(sep);	    
		}
		if (ret != null) {
			builder.append(ret);
		}
		String content = builder.toString().trim();
		return content;
	}
	
	public String legValList(String sep, String ret) {
		StringBuilder builder = new StringBuilder();
		for (Map.Entry<String, String> kvp : leg.entrySet()) {	    
		    builder.append(kvp.getValue());
		    builder.append(sep);
		}
		if (ret != null) {
			builder.append(ret);
		}
		String content = builder.toString().trim();
		return content;
	}
}
