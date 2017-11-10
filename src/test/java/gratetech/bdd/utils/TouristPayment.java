package gratetech.bdd.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class TouristPayment {
	public static Logger log = Logger.getLogger(TouristPayment.class);
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
		return payment.get("payment type");
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
	
	public void dumpPayment() {
		log.info("Payment information is : ");
		for (String key : payment.keySet()) {
		    String value = payment.get(key);
		    log.info("Key = " + key + ", Value = " + value);
		}
		log.info("Payment Dumped ");
	}

}
