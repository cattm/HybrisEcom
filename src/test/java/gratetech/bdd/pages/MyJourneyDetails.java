package gratetech.bdd.pages;

import org.openqa.selenium.By;

import gratetech.bdd.commons.CommonConstants;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class MyJourneyDetails extends PageBase {
	@FindBy(css=".btn.btn-small.btn-purple.marg-t-20")
	private WebElementFacade amendButton;
	
	@FindBy(id="cancelButton")
	private WebElementFacade cancelButton;
	
	@FindBy(id="confirmCancelBookingPopup")
	private WebElementFacade modalDlg;
	
	@FindBy(id="tc")
	private WebElementFacade tanc;
	
	@FindBy(id="cancelnowButton")
	private WebElementFacade cancelNow;
		
	@FindBy(id="doConfirmCancel")
	private WebElementFacade cancelTheCancel;
	
	public void cancelBooking() {
		cancelButton.click();
	}
	
	// deal with bootstrap modal dialog 
	public void acceptCanceldlg() {
		pageIsReady(CommonConstants.FAST);
		if (modalDlg.isVisible()) {
			//getDriver().switchTo().activeElement();
			modalDlg.findBy(By.id("tc")).click();
			modalDlg.findBy(By.id("cancelnowButton")).click();
			//tanc.click();
			//cancelNow.click();
			//getDriver().switchTo().defaultContent();
		} else {
			log.info("No Dialog to interact with");
		}		
	}
	
	public void cancelCanceldlg() {
		if (modalDlg.isVisible()) {
			modalDlg.findBy(By.id("doConfirmCancel")).click();
			//cancelTheCancel.click();
			//getDriver().switchTo().defaultContent();
		}
		
	}
}