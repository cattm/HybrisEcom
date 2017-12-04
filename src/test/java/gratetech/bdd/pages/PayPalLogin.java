package gratetech.bdd.pages;

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
		if (findIframe()) {
			log.info("in iframe");
			email.clear();
			email.type(str);
			resetIframe();
		}
	

		
		
	}
	
	public void setPassword(String str) {
		log.info(str);
		if (findIframe()) {
			log.info("in iframe");
			password.clear();
			password.type(str);	
			resetIframe();
		}
		
	}
	
	public void submit() {
		if (findIframe()) {
			log.info("in iframe");
			login.click();	
			resetIframe();
		}		
	}
	
	private boolean findAllWindows() {	
		Set handles = this.getDriver().getWindowHandles();
		for (String handle1 : this.getDriver().getWindowHandles()) {
			 
        	log.info(handle1);
        }

		return true;
		
	}
	
	private boolean findIframe() {	
		// this needs a dodgy timeout check 
		int count = 0;
		
		while (count < 3) {	
			List<WebElement> allFrameElements = this.getDriver().findElements(By.tagName("iframe"));
			for(WebElement nitem : allFrameElements )
			{
				String tmp = nitem.getAttribute("name");
				if (tmp.contains("injectedUl")) {
					this.getDriver().switchTo().frame(nitem);
					//log.info("found required iframe ");
					return true;
				}
			}
			try {
				log.info("iframe not found - sleep and check again");
				Thread.sleep(CommonConstants.SLOW);
				count++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
	private void resetIframe() {
		//log.info("reset iframe");
		this.getDriver().switchTo().defaultContent();
	}
}
