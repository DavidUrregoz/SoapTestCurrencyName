package co.com.sofka.runner.soap.currencyname;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberSerenityRunner;
import org.junit.runner.RunWith;

@RunWith(CucumberSerenityRunner.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {"src/test/resources/features.information/currencyName.feature"},
        glue = {"co.com.sofka.stepdefnitions.soap.currencyname"}

)
public class CurrencyNameWithCucumberTest {
}
