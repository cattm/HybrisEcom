package gratetech.bdd.pages;

import gratetech.bdd.commons.CommonConstants;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import net.thucydides.core.pages.PageObject;


// TODO - also should put the help methods to get rid of unwanted popups (and iframes) and center the page on the require element as utilities here


public class PageBase extends PageObject {
	public static Logger log = Logger.getLogger(PageBase.class);
	
	private boolean makeVisible = false;
	
	
	protected void setVisible(boolean vis) { makeVisible = vis; }
	protected boolean getVisible() { return makeVisible; }
	
	protected void pageIsReady(long slow) {
		final int limit = CommonConstants.WAITLOOPCOUNT;
		int count = 0;
		try {
			Boolean isLoaded = false; 
			while (!isLoaded && (count < limit)) {
				isLoaded = isPageLoaded();
			    Thread.sleep(slow);
			    count++;
			}
			log.info("Exit pageIsReady on loop " + count);								
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected Boolean isPageLoaded() {
		String jsQuery = "function pageLoaded() "
				+ "{var loadingStatus=(document.readyState=='complete');"
				+ "return loadingStatus;};"
				+ "return pageLoaded()";
		return (Boolean) evaluateJavascript(jsQuery);
	}
	
	protected void jsListSelect(String selector, String setting) {
		List<WebElement> wel = getDriver().findElements(By.cssSelector(selector));
		for(WebElement e : wel) {				
			if (e.getText().equalsIgnoreCase(setting)) {
				log.info("Setting selector to " + setting);
				e.click();
				break;		
			}
		}
	}
	protected void handleMainCookieMessage() {
		// check if we have cookie iframe and handle it if we do
		try {
			getDriver().switchTo().frame("qb_cookie_consent_main");
			getDriver().findElement(By.id("closePopup")).click();
			getDriver().switchTo().defaultContent();
		}
		catch (Exception e) {
			log.info(e.getStackTrace());
		}
	}
	
	protected boolean findIframe(String texttofind) {	
		List<WebElement> allFrameElements = this.getDriver().findElements(By.tagName("iframe"));
		for(WebElement nitem : allFrameElements )
		{
           String tmp = nitem.getAttribute("src");
           if (tmp.contains(texttofind)) {
        	   this.getDriver().switchTo().frame(nitem);  
        	   return true;
           }
		}
		return false;
	}
	
	protected void resetIframe() {
		this.getDriver().switchTo().defaultContent();
	}
	
	protected WebElement getFromListBy(By myby, int toget) {
		List<WebElement> elements = this.getDriver().findElements(myby);		
		if (elements.size() >= toget) {
			return elements.get(toget);		
		}
		else return elements.get(0);
	}
	
	protected void selectFromListBy(By myby, String tofind) {
		List<WebElement> wel = getDriver().findElements(myby);
		for(WebElement e : wel) {				
			if (e.getText().equalsIgnoreCase(tofind)) {
				log.info("selecting from list " + tofind);
				e.click();
				break;
			}
		}	
	}
	
	protected void scrollToElement(WebElement element) {
		JavascriptExecutor jse2 = (JavascriptExecutor)this.getDriver();
		jse2.executeScript("arguments[0].scrollIntoView()", element); 
	}
	// TODO Element is visible!!!!
}
