package gratetech.bdd.utils;

import java.util.HashMap;
import java.util.Map;

public class TouristPayment {
	/*
	 * Payment type
	 * account
	 * cvv
	 * expiry
	 */
	Map<String, String> payment = new HashMap<String, String>();
	
	public void setUpPayment(String type, String account, String cvv, String expiry) {
		payment.put("payment type", type);
		payment.put("account", account);
		payment.put("cvv", cvv);
		payment.put("expiry", expiry);
		
	}
	
	public String getPaymentType () {
		return payment.get("type");
	}
	
	public String getAccount () {
		return payment.get("account");
	}
	
	public String getCVV () {
		return payment.get("cvv");
	}
	
	public String getExpiry () {
		return payment.get("expiry");
	}
}
