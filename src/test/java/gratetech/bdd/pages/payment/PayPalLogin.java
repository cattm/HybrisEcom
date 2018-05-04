package gratetech.bdd.pages.payment;

import gratetech.bdd.commons.CommonConstants;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

public class PayPalLogin extends PageObject {
	public static Logger log = Logger.getLogger(PayPalLogin.class);
	@FindBy(id="email")
	private WebElementFacade email;

	@FindBy(id="password")
	private WebElementFacade password;
	
	@FindBy(id="btnLogin")
	private WebElementFacade login;

	String handle;
	
	public void setEmail(String str) {
		log.info(str);
		if (findPPLIframe()) {
			log.info("in iframe");
			email.clear();
			email.type(str);
			resetPPLIframe();
		}	
	}
	
	public void setPassword(String str) {
		log.info(str);
		if (findPPLIframe()) {
			log.info("in iframe");
			password.clear();
			password.type(str);	
			resetPPLIframe();
		}		
	}
	
	public void submit() {
		if (findPPLIframe()) {
			log.info("in iframe");
			login.click();	
			resetPPLIframe();
		}		
	}
	
	private boolean findAllWindows() {	
		Set handles = this.getDriver().getWindowHandles();
		for (String handle1 : this.getDriver().getWindowHandles()) {
			 
        	log.info(handle1);
        }
		return true;	
	}
	
	private boolean findPPLIframe() {	
		// this needs a dodgy timeout check 
		int count = 0;
		
		while (count < 3) {	
			List<WebElement> allFrameElements = this.getDriver().findElements(By.tagName("iframe"));
			for(WebElement nitem : allFrameElements )
			{
				String tmp = nitem.getAttribute("name");
				if (tmp.contains("injectedUl")) {
					this.getDriver().switchTo().frame(nitem);
					return true;
				}
			}
			try {
				log.info("iframe not found - sleep and check again");
				Thread.sleep(CommonConstants.SLOW);
				count++;
			} catch (InterruptedException e) {
				log.error("Sleep interrupted while waiting for iframe");
				e.printStackTrace();
			}
		}
		return false;
	}
	
	private void resetPPLIframe() {
		this.getDriver().switchTo().defaultContent();
	}
}
