package gratetech.bdd.utils;

import gratetech.bdd.utils.TouristBooking.PassengerType;
import gratetech.bdd.utils.TouristBooking.onJourney;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;


public class Passengers {
	public static Logger log = Logger.getLogger(Passengers.class);
	/* Leg
	 * number of passengers
	 * Passenger types - adult, child, baby, pensioner, student, pet - there are loads of them
	 * Passenger each name
	 * 
	 * 
	 */
	private class Passenger {
		private String name;
		private PassengerType pType;
		
		public Passenger (String n, PassengerType p) {
			name = n;
			pType = p;
		}
		
		public String getName() { return name; }
		public PassengerType getPassengerType() { return pType; }
		
	}
	private onJourney leg;
	private int numPassengers; // this is the total in this model -- we could model by leg or by type!!!!
	private ArrayList<Passenger> passengers = new ArrayList<Passenger>(); 
	
	public void setUpPassengers(onJourney theleg, int number) {
		this.leg = theleg;
		numPassengers = number;
		
	}	
	
	public void setNumberPassengers(int number){
		numPassengers = number;
	}
	
	public void addPassenger(String name, PassengerType pclass ) {
		passengers.add(new Passenger(name, pclass));
	}
	
	public int getNumPassengersOnLeg() {
		return numPassengers;
	}
	
	public String getPassengerName(int index){
		return passengers.get(index).getName();
	}
	
	public PassengerType getPassengerType (int index) {
		return passengers.get(index).getPassengerType();
	}
	
	public int getPassengerListSize() {
		return passengers.size();
	}
	
	public String getPassengersOfType(PassengerType pt) {
		String result = null;
		for (Passenger p : passengers) {
			String v = p.getName();
			if (pt == p.getPassengerType()) {
				result += v + ",";
			}
		}
		// now remove last ","
		result = result.replaceAll(", $", "");
		return result;
	}
	
	public int getNumberOfPassengersOfType(PassengerType pt) {
		int result = 0;
		for (Passenger p : passengers) {
			String v = p.getName();
			if (pt == p.getPassengerType()) {
				result++;
			}
		}
		return result;
	}
	
	public void dumpPassenger() {
		log.info("Passenger information for this booking : ");
		log.info("number of Passengers is " + numPassengers);
		log.info("leg is " + leg.toString());
		for (Passenger p : passengers) {
			String v = p.getName();
			PassengerType pt = p.getPassengerType();
			log.info( "Name of Passenger " + v + ", Type is " + pt.toString());
		}
		log.info("Passenger information Dumped ");
	}

	public String passengerKeyList (String sep, String ret) {
		StringBuilder builder = new StringBuilder();
		builder.append("onJourney");
		builder.append(sep);
		builder.append("Number of Passengers on this leg");
		builder.append(sep);
		for (int i = 0; i< passengers.size(); i++) {
		    builder.append("passenger Type " + String.valueOf(i));
		    builder.append(sep);
		    builder.append("passenger Name " + String.valueOf(i));
		    builder.append(sep);
		}
		if (ret != null) {
			builder.append(ret);
		}
		String content = builder.toString().trim();
		return content;
	}
	
	public String passengerValList(String sep, String ret) {
		StringBuilder builder = new StringBuilder();
		builder.append(leg.toString());
		builder.append(sep);
		builder.append(String.valueOf(numPassengers));
		builder.append(sep);
		for (Passenger p : passengers) {
		    builder.append(p.getPassengerType().toString());
		    builder.append(sep);
		    builder.append(p.getName());
		    builder.append(sep);
		}
		if (ret != null) {
			builder.append(ret);
		}
		String content = builder.toString().trim();
		return content;
	}
}
