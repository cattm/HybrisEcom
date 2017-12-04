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

public class PassengerAndCar extends PageObject {
	public static Logger log = Logger.getLogger(PassengerAndCar.class);
	// use the original HTML listbox objects?
	private boolean makeVisible = false;
	
	@FindBy(id="oldPassengers1SelectBoxIt")
	private WebElementFacade oldPassengerEnable;
	
	@FindBy(id="vehiclesData0.existingCodeSelectBoxIt")
	private WebElementFacade existingVehicleEnable;
	
	@FindBy(id="oldPassengers1")
	private WebElementFacade selectPassenger1;
	
	@FindBy(id="vehiclesData0.existingCode")
	private WebElementFacade selectVehicleDropSelect;
	
	@FindBy(id="vehiclesData0.licensePlate")
	private WebElementFacade vehicleTextBox;
	
	@FindBy(id="confirmBooking")
	private WebElementFacade continueToNextPage;
	
	public void selectPassenger1(String select) {
		// need to use our custom find the js version of the box
		log.info("selecting " + select);
		WebDriver dr = this.getDriver();
		if (makeVisible) {
			JavascriptExecutor executor = (JavascriptExecutor)dr;
			executor.executeScript("document.getElementById('oldPassengers1').style.display='block';");
			Select dropdown = new Select(selectPassenger1);  
			dropdown.selectByVisibleText(select);
		} else {
			pageIsReady(CommonConstants.FAST);
			oldPassengerEnable.click();	
			pageIsReady(CommonConstants.FAST);
			List<WebElement> wel = dr.findElements(By.cssSelector("#oldPassengers1SelectBoxItOptions li"));
			for(WebElement e : wel) {				
				if (e.getText().equalsIgnoreCase(select)) {
					log.info("selecting passenger " + select);
					e.click();
					break;
				}
			}	
		}
	}
	
	private void selectFromExisting(String carreg) {
		// note the reg box is different if its a van!
		// note also that if you save it and come back sometimes you cannot proceed
		// get stuck on travel essentials
		log.info("selecting " + carreg); 	
		WebDriver dr = this.getDriver();
		
		if (makeVisible) {
			JavascriptExecutor executor = (JavascriptExecutor)dr;
			executor.executeScript("document.getElementById('vehiclesData0.existingCode').style.display='block';");
			Select dropdown = new Select(selectVehicleDropSelect);    
			dropdown.selectByVisibleText(carreg);
		} else {
			// this is a timing thing - when has the JS run?
			pageIsReady(CommonConstants.FAST);
			existingVehicleEnable.click();	
			pageIsReady(CommonConstants.FAST);
			// TBD little bit of js debug script trying to find out whats happening - this code is not 100% reliable
			JavascriptExecutor executor = (JavascriptExecutor)dr;
			String res = (String) executor.executeScript("return document.querySelector(\"span [id='vehiclesData0.existingCodeSelectBoxItOptions'] li\").innerHTML;"); 
			log.info(res);
			List<WebElement> wel = dr.findElements(By.cssSelector("span [id='vehiclesData0.existingCodeSelectBoxItOptions'] li"));
			for(WebElement e : wel) {
				log.info(e.getText());
				if (e.getText().equalsIgnoreCase(carreg)) {
					log.info("Selecting Vehicle " + carreg);
					e.click();
					break;
				}
			}
		}	
	}
	
	private void TypeNewReg(String reg) {
		log.info("typing in " + reg);
		vehicleTextBox.type(reg);
	}
	
	public void selectVehicle(String vehicle) {
		// vehicle may require a new reg or a drop down
		// for now we can make an assumption
		// lets assume that if the text box is present then we use it!
		 if (vehicleTextBox.isVisible()) {
			 TypeNewReg(vehicle);
		 } else {
			 selectFromExisting(vehicle);
		 }
		
	}
	
	public void continueToNextPage() {
		continueToNextPage.click();
	}
	
	private void pageIsReady(long slow) {
		final int limit = 5;
		int count = 0;
		try {
			Boolean isLoaded = false; // isPageLoaded();
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
	
}
