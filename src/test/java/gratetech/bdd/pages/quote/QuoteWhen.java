package gratetech.bdd.pages.quote;

import gratetech.bdd.commons.CommonConstants;
import gratetech.bdd.pages.PageBase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

public class QuoteWhen extends PageBase {
	public static Logger log = Logger.getLogger(QuoteWhen.class);
	boolean makeVisible = false;
	
	@FindBy(id="singleJourneyDateTextBox")
	private WebElementFacade selectGoingOutDate;
	
	@FindBy(id="returnJourneyDateTextBox")
	private WebElementFacade selectReturnDate;
	
	@FindBy(id="singleJourneyTimeComboBoxSelectBoxIt")
	private WebElementFacade singleJourneyTimeEnable;
	
	@FindBy(id="returnJourneyTimeComboBoxSelectBoxIt")
	private WebElementFacade returnJourneyTimeEnable;
	
	
	@FindBy(id="singleJourneyTimeComboBox")
	private WebElementFacade selectOutTime;
	
	@FindBy(id="returnJourneyTimeComboBox")
	private WebElementFacade selectReturnTime;
	
	@FindBy(css="span.ui-datepicker-month")
	private WebElementFacade datePickerMonth;
	
	@FindBy(css="span.ui-datepicker-year")
	private WebElementFacade datePickerYear;
	
	@FindBy(css="span.ui-icon.ui-icon-circle-triangle-e")
	private WebElementFacade datePickerNext;
	
	// TBD These methods "work" but they appear to upset the sailing time box drop downs - get set to NO TIME?
	// TBD Also although singleJourneyDateTextBox is used for MiniCruise - it is set to read only!
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
	
	// MiniCruise has the date box set as read only
	// so we will need to either manipulate the calendar object
	// or we will need to run some javascript
	// we need to find the correct month
	// then the correct day
	// then hit continue
	public void setMCWhenDate(String when) {
		log.info(when);		
		String eDay = "";
		String eMonth = "";
		String eYear = "";
		
		SimpleDateFormat myf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat mym = new SimpleDateFormat("MMMM");
		SimpleDateFormat myy = new SimpleDateFormat("yyyy");
		SimpleDateFormat myd = new SimpleDateFormat("d");
		
		Date dtx;
		try {
			dtx = myf.parse(when);
			eDay = myd.format(dtx);
			eMonth = mym.format(dtx);
			eYear = myy.format(dtx);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		log.info("We want " + eDay + " " + eMonth + " " + eYear);	
		pageIsReady(CommonConstants.FAST);
		
		// turn the silly pop up off if you can
		evaluateJavascript("document.getElementById('widgetBackImage').style.display='none';");
		//evaluateJavascript("document.getElementById('floatButtonsWrapper').click();");
		
		selectGoingOutDate.click();
		pageIsReady(CommonConstants.FAST);
		
		// TODO JS frig - scroll onto view
		//evaluateJavascript("document.getElementById('firstname').focus()");
		//JavascriptExecutor jse2 = (JavascriptExecutor)this.getDriver();
		//jse2.executeScript("arguments[0].scrollIntoView()", datePickerYear); 
		scrollToElement(datePickerYear);
		
		String oMonth=datePickerMonth.getText(); 		
		String oYear=datePickerYear.getText(); 
		log.info("Cal Starting Date is " + oYear + " " + oMonth);
				
		//navigate the Picker
		while(!((eYear.contentEquals(oYear)) && (eMonth.contentEquals(oMonth)))) {
			pageIsReady(CommonConstants.FAST);
		    datePickerNext.click(); 
		    oMonth=datePickerMonth.getText(); 		
			oYear=datePickerYear.getText(); 	    
		}
		log.info("found correct year and month " + oYear + " " + oMonth);
		pageIsReady(CommonConstants.FAST);
		
		// need to strip any leading zero and locate the exact cell to pick
		// //td[not(contains(@class,'ui-datepicker-other-month'))]/a[text()=27]
		
		// TODO there is a problem here with the stupid popups - scrolling into view
		// we need to iconise and also scroll correctly
		By dayxpath = By.xpath("//td[not(contains(@class,'ui-datepicker-other-month'))]/a[text()='" + eDay + "']");
		//By daylink = By.linkText(eDay);
		log.info("Path is " + dayxpath);
		WebElement dayElement = getDriver().findElement(dayxpath);
		
		
		String oDay = dayElement.getText();
		if(eDay.equals(oDay)) {
			dayElement.click();
			log.info("selected Day " + eDay);
		} else {
			log.info("NO DAY SELECTED");
		}
		
		pageIsReady(CommonConstants.FAST);
		// //button[@type='button']
		getDriver().findElement(By.xpath("//*[@id='datepickerContainer_outbound']/div[2]/button")).click();
		log.info("Click Continue");
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
			jsListSelect("#singleJourneyTimeComboBoxSelectBoxItOptions li", t);				
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
			jsListSelect("#returnJourneyTimeComboBoxSelectBoxItOptions li", t);			
		}	
	}
	

}
