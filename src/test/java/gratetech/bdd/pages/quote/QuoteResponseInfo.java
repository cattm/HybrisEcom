package gratetech.bdd.pages.quote;



import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

public class QuoteResponseInfo extends PageObject  {

	@FindBy(css = "section[class='intro'] h2")
	private WebElementFacade intro;
	
	public String getIntro() {
		if (intro.isVisible()) {
			return intro.getText();
		}
		return "";
	}
}
