package gratetech.bdd.utils;

import gratetech.bdd.utils.TouristBooking.PassengerType;
import gratetech.bdd.utils.TouristBooking.onJourney;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Passengers {
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
	private int numPassengers;
	private ArrayList<Passenger> passengers = new ArrayList<Passenger>(); 
	
	public void setUpPassengers(onJourney theleg, int number) {
		this.leg = theleg;
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

}
