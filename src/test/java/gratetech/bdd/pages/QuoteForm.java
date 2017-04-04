package gratetech.bdd.pages;

import java.util.List;

import gratetech.bdd.steps.QuoteSteps;

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

	@FindBy(id="singleJourneyComboBox")
	private WebElementFacade selectOutJourney;
	
	@FindBy(id="returnJourneyComboBox")
	private WebElementFacade selectReturnJourney;
	
	@FindBy(id="singleJourneyDateTextBox")
	private WebElementFacade selectGoingOutDate;
	
	@FindBy(id="returnJourneyDateTextBox")
	private WebElementFacade selectReturnDate;
	
	@FindBy(id="vehicleTypeOutboundComboBox")
	private WebElementFacade selectVehicleType;
	
	@FindBy(id="ou_length")
	private WebElementFacade selectVehicleLength;
	
	@FindBy(id="ou_height")
	private WebElementFacade selectVehicleHeight;
	
	@FindBy(id="ou_AD_pass_comboBox")
	private WebElementFacade selectNumberAdultPassengers;
	
	@FindBy(id="fareFindersubmitButton")
	private WebElementFacade submitGetAQuote;
	
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
	public void setDeparturePort(String from) {
		log.info(from);		
		WebDriver dr = this.getDriver();
		JavascriptExecutor executor = (JavascriptExecutor)dr;
		executor.executeScript("document.getElementById('singleJourneyComboBox').style.display='block';");
	    Select dropdown = new Select(selectOutJourney);  
	    dropdown.selectByVisibleText(from);

	}
	
	public void setReturnPort(String from) {
		log.info(from);
		WebDriver dr = this.getDriver();
		JavascriptExecutor executor = (JavascriptExecutor)dr;
		executor.executeScript("document.getElementById('returnJourneyComboBox').style.display='block';");
		Select dropdown = new Select(selectReturnJourney);
		dropdown.selectByVisibleText(from);
	}
	
	public void setDepartureDate(String when) {
		selectGoingOutDate.clear();
		selectGoingOutDate.type(when);	  
	}
	
	public void setReturnDate(String when) {
		selectReturnDate.clear();
		selectReturnDate.type(when);		  
	}
	
	public void setVehicleType(String type) {
		WebDriver dr = this.getDriver();
		JavascriptExecutor executor = (JavascriptExecutor)dr;
		executor.executeScript("document.getElementById('vehicleTypeOutboundComboBox').style.display='block';");
		Select dropdown = new Select(selectVehicleType);
		  dropdown.selectByValue(type);
	}
	
	public void setVehicleLength(String length) {
		selectVehicleLength.clear();
		selectVehicleLength.type(length);
	}
	
	public void setVehicleHeight(String height) {
		selectVehicleHeight.clear();
		selectVehicleHeight.type(height);
	}

	public void setNumberOfAdults(String number) {
		WebDriver dr = this.getDriver();
		JavascriptExecutor executor = (JavascriptExecutor)dr;
		executor.executeScript("document.getElementById('ou_AD_pass_comboBox').style.display='block';");	
		Select dropdown = new Select(selectNumberAdultPassengers);
		dropdown.selectByValue(number);
	}
	
	public void getAQuote() {
		submitGetAQuote.submit(); 
		log.info("Getting Quote:");
	}
}
