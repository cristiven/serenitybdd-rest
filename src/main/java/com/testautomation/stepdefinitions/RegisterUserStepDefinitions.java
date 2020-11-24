package com.testautomation.stepdefinitions;

import com.testautomation.models.users.RegisterUserInfo;
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

    private final String restApiUrl = "https://reqres.in/api";
    Actor pepito;

    @Given("^Pepito es un cliente que quiere poder administrar sus productos bancarios$")
    public void pepitoEsUnClienteQueQuierePoderAdministrarSusProductosBancarios() {
        //  Actor que se le da la Habilidad de poder comunicarse con la Api
        pepito = Actor.named("Pepito the trainer")
                .whoCan(CallAnApi.at(restApiUrl));

    }

    @When("^el envia la informacion requerida para el registro$")
    public void elEnviaLaInformacionRequeridaParaElRegistro() {
        /*Actor pepito = Actor.named("Pepito the trainer")
                .whoCan(CallAnApi.at(restApiUrl));

        String registerUserInfo = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\",\n" +
                "    \"email\": \"tracey.ramos@reqres.in\",\n" +
                "    \"password\": \"serenity\"\n" +
                "}";
        */

        RegisterUserInfo registerUserInfo = new RegisterUserInfo();

        registerUserInfo.setName("morpheus");
        registerUserInfo.setJob("leader");
        registerUserInfo.setEmail("tracey.ramos@reqres.in");
        registerUserInfo.setPassword("serenity");

        // Se invoca la tarea
        pepito.attemptsTo(
                RegisterUser.withInfo(registerUserInfo)
        );


        /*
        Otra manera de hacerlo pero usando loombok
        pepito.attemptsTo(
                RegisterUser
                        .withName("morpheus")
                        .andEmail("tracey.ramos@reqres.in")
                        .andPassword("security")
                        .andJob("leader")
        );
        */

    }

    @Then("^el debe obtener una cuenta virtual para poder ingresar cuando lo requiera$")
    public void elDebeObtenerUnaCuentaVirtualParaPoderIngresarCuandoLoRequiera() {
        pepito.should(
                seeThat("el codigo de respuesta", new ResponseCode(), equalTo(200))
        );
    }
}
