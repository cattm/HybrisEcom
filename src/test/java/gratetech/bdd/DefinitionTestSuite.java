package gratetech.bdd;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features="src/test/resources/features/")
//@CucumberOptions(features="src/test/resources/features/pando_ferry_quote/GetQuote.feature")
public class DefinitionTestSuite {}
