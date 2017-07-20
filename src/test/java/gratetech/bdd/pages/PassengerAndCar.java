package gratetech.bdd.pages;

import gratetech.bdd.steps.serenity.UserPurchaseSteps;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

public class PassengerAndCar extends PageObject {
	public static Logger log = Logger.getLogger(PassengerAndCar.class);
	
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
		JavascriptExecutor executor = (JavascriptExecutor)dr;
		executor.executeScript("document.getElementById('oldPassengers1').style.display='block';");
	    Select dropdown = new Select(selectPassenger1);  
	    dropdown.selectByVisibleText(select);

	}
	
	private void selectFromExisting(String carreg) {
		// note the reg box is different if its a van!
		// note also that if you save it and come back sometimes you cannot proceed
		// get stuck on travel essentials
		log.info("selecting " + carreg); 
		
		WebDriver dr = this.getDriver();
		
		JavascriptExecutor executor = (JavascriptExecutor)dr;
		executor.executeScript("document.getElementById('vehiclesData0.existingCode').style.display='block';");
	    Select dropdown = new Select(selectVehicleDropSelect);    
	    dropdown.selectByVisibleText(carreg);
	    
		
	    //dr.findElement(By.id("vehiclesData0.existingCodeSelectBoxItText")).click();
	    //dr.findElement(By.id("vehiclesData0.existingCode")).click();
	    //dr.findElement(By.linkText(carreg)).click();
		
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
	
}
