package com.testautomation.tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;

//Se crea la tarea
public class GetUsers implements Task {

    private final int page;

    public GetUsers(int page){
        this.page = page;
    }
    // Se crea un metodo factory para que atravez del metodo instrumented se cree la clase,
    // Se crea una instancia de get user para que reciba el valor desde el constructor
    public static Performable fronPage(int page){
        return instrumented(GetUsers.class, page);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Get.resource("/users?page=" + page)
                        .with(requestSpecification
                                ->requestSpecification.contentType(ContentType.JSON)
                                .header("header1","value1")
                        )

        );

    }
}
