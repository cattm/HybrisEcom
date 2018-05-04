package gratetech.bdd.pages.quote;

import gratetech.bdd.commons.CommonConstants;
import gratetech.bdd.pages.PageBase;

import java.util.List;
import java.util.concurrent.TimeUnit;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class QuoteWho extends PageBase {
	public static Logger log = Logger.getLogger(QuoteWho.class);
	// use the original HTML listbox objects?
	private boolean makeVisible = false;
	private String fromRoute = "";
	private WebElement selectNumberAdultPassengers;
	@FindBy(id="ou_AD_pass_comboBoxSelectBoxIt")
	
	private WebElementFacade selectNumberAdultPassengersEnable;
	
	@FindBy(id="ou_AD1_pass_comboBoxSelectBoxIt")
	private WebElementFacade selectAltNumberAdultPassengersEnable;
	
	@FindBy(id="ou_IN_pass_comboBoxSelectBoxIt")
	private WebElementFacade selectNumberInfantPassengersEnable;
	
	@FindBy(id="ou_IN1_pass_comboBoxSelectBoxIt")
	private WebElementFacade selectNumberInfant1PassengersEnable;
	
	@FindBy(id="ou_IN2_pass_comboBoxSelectBoxIt")
	private WebElementFacade selectNumberInfant2PassengersEnable;
	
	@FindBy(id="ou_CH_pass_comboBoxSelectBoxIt")
	private WebElementFacade selectNumberChildrenEnable;
	
	@FindBy(id="ou_petsSelect_0SelectBoxIt")
	private WebElementFacade selectNumberOfPetsEnable;
	

	
	public void setFromRoute(String from) {
		this.fromRoute = from;
	}
	
	public void setNumberOfAdults(String number) {
		log.info(number);
		if (makeVisible) {
			// This is ou_AD1 on Larne route just to be brilliant!
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
			pageIsReady(CommonConstants.FAST);
			String selector = "";
			if (fromRoute.contains("Dover") || fromRoute.contains("Hull")) {
				selectNumberAdultPassengersEnable.withTimeoutOf((int) CommonConstants.SLOWSECS, TimeUnit.SECONDS ).click();
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
		log.info("NOT IMPLEMENTED");
	}
	
	public void setNumberOfOldPeople(String number) {
		log.info(number);
		log.info("NOT IMPLEMENTED");
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

}
