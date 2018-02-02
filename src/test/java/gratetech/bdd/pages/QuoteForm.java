package gratetech.bdd.pages;

import java.util.List;

import gratetech.bdd.commons.CommonConstants;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

public class QuoteForm extends PageObject {
	public static Logger log = Logger.getLogger(QuoteForm.class);
	// use the original HTML listbox objects?
	private boolean makeVisible = false;

// new objects
/*
 *  outport and return port
 *  out time and return time
 *  Date pickers should not be impacted
 *  Car and trailer 
 *  passanger number identifiers
 */
	@FindBy(id="singleJourneyComboBoxSelectBoxIt")
	private WebElementFacade singleJourneyListEnable;
	
	@FindBy(id="returnJourneyComboBoxSelectBoxIt")
	private WebElementFacade returnJourneyListEnable;
	
	@FindBy(id="singleJourneyTimeComboBoxSelectBoxIt")
	private WebElementFacade singleJourneyTimeEnable;
	
	@FindBy(id="returnJourneyTimeComboBoxSelectBoxIt")
	private WebElementFacade returnJourneyTimeEnable;
	
	@FindBy(id="vehicleTypeOutboundComboBoxSelectBoxIt")
	private WebElementFacade outboundJourneyVehicleEnable;
	
	@FindBy(id="ou_petsSelect_0SelectBoxIt")
	private WebElementFacade selectNumberOfPetsEnable;
	
	@FindBy(id="trailerOutboundComboBoxSelectBoxIt")
	private WebElementFacade outboundJourneyTrailerEnable;
	
	@FindBy(id="ou_AD_pass_comboBoxSelectBoxIt")
	private WebElement selectNumberAdultPassengersEnable;
	
	@FindBy(id="ou_AD1_pass_comboBoxSelectBoxIt")
	private WebElement selectAltNumberAdultPassengersEnable;
	
	@FindBy(id="ou_IN_pass_comboBoxSelectBoxIt")
	private WebElement selectNumberInfantPassengersEnable;
	
	@FindBy(id="ou_IN1_pass_comboBoxSelectBoxIt")
	private WebElement selectNumberInfant1PassengersEnable;
	
	@FindBy(id="ou_IN2_pass_comboBoxSelectBoxIt")
	private WebElement selectNumberInfant2PassengersEnable;
	
	@FindBy(id="ou_CH_pass_comboBoxSelectBoxIt")
	private WebElementFacade selectNumberChildrenEnable;
	
	@FindBy(id="cabinsOutboundSelectBoxIt")
	private WebElementFacade outboundCabinEnable;
	
// end of new objects
	
	@FindBy(id="singleJourneyComboBox")
	private WebElementFacade selectOutJourney;
	
	@FindBy(id="returnJourneyComboBox")
	private WebElementFacade selectReturnJourney;
	
	@FindBy(id="singleJourneyDateTextBox")
	private WebElementFacade selectGoingOutDate;
	
	@FindBy(id="returnJourneyDateTextBox")
	private WebElementFacade selectReturnDate;
	
	@FindBy(id="singleJourneyTimeComboBox")
	private WebElementFacade selectOutTime;
	
	@FindBy(id="returnJourneyTimeComboBox")
	private WebElementFacade selectReturnTime;
	
	@FindBy(id="vehicleTypeOutboundComboBox")
	private WebElementFacade selectVehicleType;
	
	@FindBy(id="ou_length")
	private WebElementFacade selectVehicleLength;
	
	@FindBy(id="ou_height")
	private WebElementFacade selectVehicleHeight;
		
	private WebElement selectNumberAdultPassengers;
		
	@FindBy(id="discountCodeTextBox")
	private WebElementFacade promoCode;
	
	@FindBy(id="fareFindersubmitButton")
	private WebElementFacade submitGetAQuote;
	
	private String fromRoute = "";
	
	// this is a DEBUG method
	public void elementIsPresent() {
		// (JavascriptExecutor)driver).executeScript - its JQuery!
		// or $("#blah").click() jQuery selector
		// so may need to click...to make visible		
		List<WebElement> allFormChildElements = this.getDriver().findElements(By.id("fareFinderForm"));
		for(WebElement item : allFormChildElements )
		{
		
		   log.info("TAG " + item.getTagName() + " TEXT " + item.getText());
		
			if(item.getTagName().equals("select")) {log.info("SELECT FOUND ");}
		}
	}
	
	public void setFromRoute(String from) {this.fromRoute = from;}
	
	public void setDeparturePort(String from) {
		log.info(from);		
		fromRoute = from;
		if (makeVisible) {
			
			evaluateJavascript("document.getElementById('singleJourneyComboBox').style.display='block';");
			Select dropdown = new Select(selectOutJourney);  
			dropdown.selectByVisibleText(from);
		} else {
			
			// enable the list
			pageIsReady(CommonConstants.FAST);
			singleJourneyListEnable.click();
			pageIsReady(CommonConstants.FAST);
			// find the option that matches the search criteria and select it
			List<WebElement> wel = getDriver().findElements(By.cssSelector("#singleJourneyComboBoxSelectBoxItOptions li"));
			for(WebElement e : wel) {
				if (e.getText().equalsIgnoreCase(from)) {
					log.info("Setting Outbound port " + from);
					e.click();
					break;
				}
			}	
			
		}
	}
	
	public void setReturnPort(String from) {
		log.info(from);
		if (makeVisible) {		
			evaluateJavascript("document.getElementById('returnJourneyComboBox').style.display='block';");
			Select dropdown = new Select(selectReturnJourney);
			dropdown.selectByVisibleText(from);
		} else {
			// enable the list
			pageIsReady(CommonConstants.FAST);
			returnJourneyListEnable.click();
			pageIsReady(CommonConstants.FAST);
			// find the option that matches the search criteria and select it
			List<WebElement> wel = getDriver().findElements(By.cssSelector("#returnJourneyComboBoxSelectBoxItOptions li"));
			for(WebElement e : wel) {
				if (e.getText().equalsIgnoreCase(from)) {
					log.info("Setting Return port " + from);
					e.click();
					break;
				}
			}
		}
	}
	
	// TBD These methods "work" but they appear to upset the sailing time box drop downs - get set to NO TIME?
	public void setDepartureDate(String when) {
		log.info(when);
		if (true) {
			pageIsReady(CommonConstants.FAST);
			selectGoingOutDate.clear();
			selectGoingOutDate.type(when);	
		}
	}
	
	public void setReturnDate(String when) {
		log.info(when);
		if (true) {
			pageIsReady(CommonConstants.FAST);
			selectReturnDate.clear();
			selectReturnDate.typeAndTab(when);
		
		}
	}
	
	// TBD need methods to set the TIME also
	public void setDepartureTime(String t) {
		log.info(t);
		// TODO: fix this horrible check
		if (t.isEmpty()) return;
		
		if (makeVisible) {
			evaluateJavascript("document.getElementById('singleJourneyTimeComboBox').style.display='block';");
			Select dropdown = new Select(selectOutTime);
			dropdown.selectByValue(t);
		} else {
		
			// #singleJourneyTimeComboBoxSelectBoxIt.selectboxit - click()? or open()
			// #singleJourneyTimeComboBoxSelectBoxItOptions.selectboxit-option li:nth-child(#{4}) doesnt work
			// but #singleJourneyTimeComboBoxSelectBoxItOptions li:nth-child(5) a does work and returns
			// 	
			pageIsReady(CommonConstants.FAST);
			singleJourneyTimeEnable.click();
			pageIsReady(CommonConstants.FAST);
			List<WebElement> wel = getDriver().findElements(By.cssSelector("#singleJourneyTimeComboBoxSelectBoxItOptions li"));
			for(WebElement e : wel) {
				if (e.getText().equalsIgnoreCase(t)) {
					log.info("Setting Time to " + t);
					e.click();
					break;
				}
			}					
		}
	}
	
	public void setReturnTime(String t) {
		log.info(t);
		// TODO: fix this horrible check
		if (t.isEmpty()) return;
		if (makeVisible) {
			log.info("makeVisible not coded yet");
		} else {
			pageIsReady(CommonConstants.FAST);
			returnJourneyTimeEnable.click();
			pageIsReady(CommonConstants.FAST);
			List<WebElement> wel = getDriver().findElements(By.cssSelector("#returnJourneyTimeComboBoxSelectBoxItOptions li"));
			for(WebElement e : wel) {
				if (e.getText().equalsIgnoreCase(t)) {
					log.info("Setting Time to " + t);
					e.click();
					break;
				}
			}		
		}	
	}
		
	public void setVehicleType(String type) {
		log.info(type);
		if (makeVisible) {
			evaluateJavascript("document.getElementById('vehicleTypeOutboundComboBox').style.display='block';");
			Select dropdown = new Select(selectVehicleType);
			dropdown.selectByValue(type);
		} else {
			pageIsReady(CommonConstants.FAST);
			outboundJourneyVehicleEnable.click();
			pageIsReady(CommonConstants.FAST);
			List<WebElement> wel = getDriver().findElements(By.cssSelector("#vehicleTypeOutboundComboBoxSelectBoxItOptions li"));
			for(WebElement e : wel) {				
				if (e.getText().equalsIgnoreCase(type)) {
					log.info("Setting vehicle to " + type);
					e.click();
					break;
				}
			}
		}
	}
	
	public void setTrailer(String trailer) {
		
		log.info(trailer);
		if (makeVisible) {
			log.info("makeVisible not coded yet");
		} else {
			pageIsReady(CommonConstants.FAST);
			outboundJourneyTrailerEnable.click();
			pageIsReady(CommonConstants.FAST);
			List<WebElement> wel = getDriver().findElements(By.cssSelector("#cabinsOutboundSelectBoxItOptions li"));
			for(WebElement e : wel) {				
				if (e.getText().equalsIgnoreCase(trailer)) {
					log.info("Setting vehicle to " + trailer);
					e.click();
					break;
				}
			}
		}
	}
	
	public void setVehicleLength(String length) {
		log.info(length);
		pageIsReady(CommonConstants.FAST);
		selectVehicleLength.clear();
		selectVehicleLength.type(length);
	}
	
	public void setVehicleHeight(String height) {
		log.info(height);
		pageIsReady(CommonConstants.FAST);
		selectVehicleHeight.clear();
		selectVehicleHeight.type(height);
	}

	public void setNumberOfAdults(String number) {
		log.info(number);
		if (makeVisible) {
			// TODO: this is ou_AD1 on Larne route just to be brilliant!
			// this is HACK will tidy once proven
			if (fromRoute.contains("Dover") || fromRoute.contains("Hull")) {
				evaluateJavascript("document.getElementById('ou_AD_pass_comboBox').style.display='block';");
				selectNumberAdultPassengers = this.getDriver().findElement(By.id("ou_AD_pass_comboBox"));
			} else if (fromRoute.contains("Larne")) {
				evaluateJavascript("document.getElementById('ou_AD1_pass_comboBox').style.display='block';");
				selectNumberAdultPassengers = this.getDriver().findElement(By.id("ou_AD1_pass_comboBox"));
			
			} else {
				log.info("Route not implemented");
				return;
			}
			Select dropdown = new Select(selectNumberAdultPassengers);
			dropdown.selectByValue(number);
		} else {
			//selectNumberAdultPassengers
			pageIsReady(CommonConstants.FAST);
			String selector = "";
			if (fromRoute.contains("Dover") || fromRoute.contains("Hull")) {
				selectNumberAdultPassengersEnable.click();
				selector = "#ou_AD_pass_comboBoxSelectBoxItOptions li";
			} else if (fromRoute.contains("Larne")) {
				selectAltNumberAdultPassengersEnable.click();
				selector = "#ou_AD1_pass_comboBoxSelectBoxItOptions li";
			} else {
				log.info("Route not implemented");
				return;
			}
			pageIsReady(CommonConstants.FAST);		
			List<WebElement> wel = getDriver().findElements(By.cssSelector(selector));
			for(WebElement e : wel) {
				if (e.getText().equalsIgnoreCase(number)) {
					log.info("Setting Adults to " + number);
					e.click();
					break;
				}
			}
		}
	}
	
	public void setNumberOfChildren(String number) {
		log.info(number);
		if (makeVisible) {
			log.info("makeVisible not coded yet");
		} else {
			pageIsReady(CommonConstants.FAST);
			selectNumberChildrenEnable.click();
			pageIsReady(CommonConstants.FAST);
			List<WebElement> wel = getDriver().findElements(By.cssSelector("#ou_CH_pass_comboBoxSelectBoxItOptions li"));
			for(WebElement e : wel) {				
				if (e.getText().equalsIgnoreCase(number)) {
					log.info("Setting children to " + number);
					e.click();
					break;
				}
			}
		}
	}
	
	public void setNumberOfInfants(String number) {
		log.info(number);
		if (makeVisible) {
			log.info("makeVisible not coded yet");
		} else {
			pageIsReady(CommonConstants.FAST);
			selectNumberInfantPassengersEnable.click();
			pageIsReady(CommonConstants.FAST);
			List<WebElement> wel = getDriver().findElements(By.cssSelector("#ou_IN_pass_comboBoxSelectBoxItOptions li"));
			for(WebElement e : wel) {				
				if (e.getText().equalsIgnoreCase(number)) {
					log.info("Setting infants to " + number);
					e.click();
					break;
				}
			}
		}
	}
	
	public void setNumberOfInfants1(String number) {
		log.info(number);
		if (makeVisible) {
			log.info("makeVisible not coded yet");
		} else {
			pageIsReady(CommonConstants.FAST);
			selectNumberInfant1PassengersEnable.click();
			pageIsReady(CommonConstants.FAST);
			List<WebElement> wel = getDriver().findElements(By.cssSelector("#ou_IN1_pass_comboBoxSelectBoxItOptions li"));
			for(WebElement e : wel) {				
				if (e.getText().equalsIgnoreCase(number)) {
					log.info("Setting infants 1 to " + number);
					e.click();
					break;
				}
			}
		}
	}
	
	public void setNumberOfInfants2(String number) {
		log.info(number);
		if (makeVisible) {
			log.info("makeVisible not coded yet");
		} else {
			pageIsReady(CommonConstants.FAST);
			selectNumberInfant2PassengersEnable.click();
			pageIsReady(CommonConstants.FAST);
			List<WebElement> wel = getDriver().findElements(By.cssSelector("#ou_IN2_pass_comboBoxSelectBoxItOptions li"));
			for(WebElement e : wel) {				
				if (e.getText().equalsIgnoreCase(number)) {
					log.info("Setting infants 2 to " + number);
					e.click();
					break;
				}
			}
		}
	}
	
	public void setNumberOfStudents(String number) {
		log.info(number);
	}
	
	public void setNumberOfOldPeople(String number) {
		log.info(number);
	}
	
	public void setNumberOfPets(String number) {
		log.info(number);
		if (makeVisible) {
			log.info("makeVisible not coded yet");
		} else {
		pageIsReady(CommonConstants.FAST);
		selectNumberOfPetsEnable.click();
		pageIsReady(CommonConstants.FAST);
		List<WebElement> wel = getDriver().findElements(By.cssSelector("#ou_petsSelect_0SelectBoxItOptions li"));
			for(WebElement e : wel) {				
				if (e.getText().equalsIgnoreCase(number)) {
					log.info("Setting Pets to " + number);
					e.click();
					break;
				}
			}
		}
	}
	
	public void setNumberOfCabins(String number) {
		log.info(number);
		if (makeVisible) {
			log.info("makeVisible not coded yet");
		} else {
			pageIsReady(CommonConstants.FAST);
			outboundCabinEnable.click();
			pageIsReady(CommonConstants.FAST);
			List<WebElement> wel = getDriver().findElements(By.cssSelector("#cabinsOutboundSelectBoxItOptions li"));
			for(WebElement e : wel) {				
				if (e.getText().equalsIgnoreCase(number)) {
					log.info("Setting cabins to " + number);
					e.click();
					break;
				}
			}
		}
	}
	
	public void tickSameOnReturn() {
		
	}
	
	public void setPromoCode(String code) {
		log.info(code);
		pageIsReady(CommonConstants.FAST);
		promoCode.clear();
		promoCode.type(code);
		
	}
		
	public void getAQuote() {
		submitGetAQuote.submit(); 
		log.info("Getting Quote:");
	}
	
	
	// TBD This code is duplicated in another page object
	// We will need to extract and refactor
	private void pageIsReady(long slow) {
		final int limit = 5;
		int count = 0;
		try {
			Boolean isLoaded = false; 
			while (!isLoaded && (count < limit)) {
				isLoaded = isPageLoaded();
			    Thread.sleep(slow);
			    count++;
			}
			log.info("Exit pageIsReady");								
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Boolean isPageLoaded() {
		String jsQuery = "function pageLoaded() "
				+ "{var loadingStatus=(document.readyState=='complete');"
				+ "return loadingStatus;};"
				+ "return pageLoaded()";
		return (Boolean) evaluateJavascript(jsQuery);
	}
	
	public void jsListSelect(String selector, String setting) {
		List<WebElement> wel = getDriver().findElements(By.cssSelector(selector));
		for(WebElement e : wel) {				
			if (e.getText().equalsIgnoreCase(setting)) {
				log.info("Setting selector to " + setting);
				e.click();
				break;		
			}
		}
	}

} // End Class
/* 
 * This is a very interesting challenge - to control the farefinder quote form
 * This is because there are a number of controls using the JQuery based libary - selectBoxIt
 * This is particularly interesting with regards List boxes because there are all hidden and replaced by JS code
 * you can attempt to handle this in one of 3 ways
 * 1) interact with the element directly
 * 2) interact with the hidden element by enabling it and using js activity
 * 			executor.executeScript("document.getElementById('vehicleTypeOutboundComboBox').style.display='block';");
			Select dropdown = new Select(selectVehicleType);
			dropdown.selectByValue(type);
 * 3) locate the constituent parts of the selectboxit components and manage them 
 * luckily it looks like you only need to manage two parts 
 * class - selectboxit and selectboxit-options - a list of selectboxit-option
 * 	WebDriver dr = this.getDriver();
	WebElement we = dr.findElement(By.id("singleJourneyTimeComboBoxSelectBoxIt"));
	log.info(we.getText());
	we.click();
	we = dr.findElement(By.cssSelector("#singleJourneyTimeComboBoxSelectBoxItOptions li:nth-child(5) a"));
	log.info(we.getText());
	we.click();
 *  -- Note that we may need to "wait" between operations in order to ensure the JS has executed and rewritten the page
*/