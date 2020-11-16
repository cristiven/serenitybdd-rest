package com.testautomation.stepdefinitions;

import com.testautomation.questions.ResponseCode;
import com.testautomation.tasks.RegisterUser;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class RegisterUserStepDefinitions {

    private final String restApiUrl = "http://localhost:5000/api";
    Actor pepito;

    /*@Given("^Pepito es un cliente que quiere poder administrar sus productos bancarios$")
    public void julianEsUnClienteQueQuierePoderAdministrarSusProductosBancarios() {
        pepito = Actor.named("Julian the trainer")
                .whoCan(CallAnApi.at(restApiUrl));

    }

    @When("^el envia la informacion requerida para el registro$")
    public void elEnviaLaInformacionRequeridaParaElRegistro() {
        String registerUserInfo = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\",\n" +
                "    \"email\": \"tracey.ramos@reqres.in\",\n" +
                "    \"password\": \"serenity\"\n" +
                "}";

        // Se invoca la tarea
        pepito.attemptsTo(
                RegisterUser.withInfo(registerUserInfo)
        );

    }

    @Then("^el debe obtener una cuenta virtual para poder ingresar cuando lo requiera$")
    public void elDebeObtenerUnaCuentaVirtualParaPoderIngresarCuandoLoRequiera() {
        pepito.should(
                seeThat("el codigo de respuesta", new ResponseCode(), equalTo(200))
        );
    }*/

    @Given("^Pepito es un cliente que quiere poder administrar sus productos bancarios$")
    public void pepito_es_un_cliente_que_quiere_poder_administrar_sus_productos_bancarios() {
        pepito = Actor.named("Julian the trainer")
                .whoCan(CallAnApi.at(restApiUrl));
    }

    @When("^el envia la informacion requerida para el registro$")
    public void el_envia_la_informacion_requerida_para_el_registro() {
        /*String registerUserInfo = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\",\n" +
                "    \"email\": \"tracey.ramos@reqres.in\",\n" +
                "    \"password\": \"serenity\"\n" +
                "}";
        // Se invoca la tarea
        pepito.attemptsTo(
                RegisterUser.withInfo(registerUserInfo)
        );*/

        pepito.attemptsTo(
                RegisterUser
                        .withName("morpheus")
                        .andEmail("tracey.ramos@reqres.in")
                        .andPassword("serenity")
                        .andJob("leader")
        );
    }

    @Then("^el debe obtener una cuenta virtual para poder ingresar cuando lo requiera$")
    public void el_debe_obtener_una_cuenta_virtual_para_poder_ingresar_cuando_lo_requiera() {
        pepito.should(
                seeThat("el codigo de respuesta", new ResponseCode(), equalTo(200))
        );
    }
}

