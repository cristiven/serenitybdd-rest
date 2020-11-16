package com.testautomation.facts;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.facts.Fact;
import net.serenitybdd.screenplay.rest.interactions.Get;

import java.util.HashMap;
import java.util.List;

public class NetflixPlans implements Fact {

    private String plansInfo;

    // Constructor
    public static NetflixPlans toViewSeries(){
        return new NetflixPlans();
    }

    @Override
    public void setup(Actor actor) {
        // Con el metodo get se llama al metodo plans
        actor.attemptsTo(
                Get.resource("/plans")
        );
        // Recorre toda la lista de data con un mapa
        List<HashMap<String,String>> plans = SerenityRest.lastResponse().path("data");
        // Recuerda los planes para mas adelante
        actor.remember("plans", plans);
        // obtenemos la lista de string para ser imprimida en el reporte
        plansInfo = plans.toString();
    }

    // Sobre escribe el metodo toString
    public String toString(){
        return "Los planes son " + plansInfo;

    }

}
