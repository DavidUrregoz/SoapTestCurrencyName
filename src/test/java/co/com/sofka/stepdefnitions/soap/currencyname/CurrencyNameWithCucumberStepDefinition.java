package co.com.sofka.stepdefnitions.soap.currencyname;

import co.com.sofka.stepdefnitions.soap.calculator.SetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static co.com.sofka.questions.ReturnStringValue.systemValue;
import static co.com.sofka.tasks.information.DoPost.doPost;
import static co.com.sofka.util.FileUtilities.readFile;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.containsString;

public class CurrencyNameWithCucumberStepDefinition extends SetUp {

    private static final String ADD_XML = System.getProperty("user.dir") + "\\src\\test\\resources\\information\\currency.xml";
    private static final String STRING_A = "[StringA]";

    @Given("que el usuario de la  app ah le interesa saber el nombre de una sigla")
    public void queElUsuarioDeLaAppAhLeInteresaSaberElNombreDeUnaSigla() {
        setUp();
    }

    @When("el usuario elige la opcion de obtener nombre he ingresa la sigla de una moneda {string}")
    public void elUsuarioEligeLaOpcionDeObtenerNombreHeIngresaLaSiglaDeUnaMoneda(String siglas) {
        bodyRequest = defineBodyRequest(siglas);
        actor.attemptsTo(
                doPost().
                        usingThe(RESOURCE).
                        with(headers()).
                        and(bodyRequest)
        );

    }

    @Then("el ususario debería obtener de correctamente  {string}")
    public void elUsusarioDeberíaObtenerDeCorrectamente(String currencyName) {
        actor.should(
                seeThatResponse(
                        "El código de respuesta HTTP debe ser: " + SC_OK,
                        response -> response
                                .statusCode(SC_OK)
                ),
                seeThat(
                        "El nombre de la moneda debe ser : ",
                        systemValue(fromLastResponseBy(actor)),
                        containsString("<m:CurrencyNameResult>" + currencyName + "</m:CurrencyNameResult>")
                )
        );
    }

    @When("el usuario elige la opcion de obtener nombre he ingresa algun dato herroneo {string}")
    public void elUsuarioEligeLaOpcionDeObtenerNombreHeIngresaAlgunDatoHerroneo(String siglas2) {
        bodyRequest = defineBodyRequest(siglas2);
        actor.attemptsTo(
                doPost().
                        usingThe(RESOURCE).
                        with(headers()).
                        and(bodyRequest)
        );
    }

    @Then("el ususario debería obtener como respuesta  {string}")
    public void elUsusarioDeberíaObtenerComoRespuesta(String error) {
        actor.should(
                seeThatResponse(
                        "El código de respuesta HTTP debe ser: " + SC_OK,
                        response -> response
                                .statusCode(SC_OK)
                ),
                seeThat(
                        "El nombre de la moneda debe ser : ",
                        systemValue(fromLastResponseBy(actor)),
                        containsString("<m:CurrencyNameResult>" + error + "</m:CurrencyNameResult>")

                )
        );
    }

    private String defineBodyRequest(String siglas){
        return readFile(ADD_XML)
                .replace(STRING_A, siglas);
    }


}
