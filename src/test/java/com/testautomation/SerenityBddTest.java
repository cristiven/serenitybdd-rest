package com.testautomation;

import com.testautomation.abilities.InteractWithDb;
import com.testautomation.database.DatabaseConnectionInfo;
import com.testautomation.database.DatabaseType;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.testautomation.database.entity.Example;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

//@RunWith(SerenityRunner.class)
public class SerenityBddTest {

    private final String restApiUrl = "http://localhost:5000/api";

    @Test
    public void dataBaseConnectionTest() {

        DatabaseConnectionInfo connectionInfo = DatabaseConnectionInfo
                .builder()
                // username con el que nos conectamos a la DB
                .username("root")
                .databaseType(DatabaseType.MYSQL)
                //url de la conexion
                .url("jdbc:mysql://localhost/test_automation")
                .password("my-secret-pw")
                .entityNames(Stream.of(
                        Example.class)
                        .map(Class::getName)
                        .collect(Collectors.toList()))
                .build();
        // Le damos una habilidad al actor para que se conecte a la DB con la habilidad que queremos
        Actor pepito = Actor.named("pepito");
        pepito.can(InteractWithDb.using(connectionInfo));

        EntityManager entityManager = InteractWithDb.as(pepito).getManager();
        //CriteriaBuilder permite crear las consultas
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Example> criteriaQuery = criteriaBuilder
                .createQuery(Example.class);
        // Se ejecuta el query
        Root<Example> userRoot = criteriaQuery.from(Example.class);

        Example queryResult = entityManager
                .createQuery(criteriaQuery
                        .select(userRoot))
                .getSingleResult();

        System.out.println(queryResult);
    }
}
