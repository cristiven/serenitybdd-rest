import com.testautomation.abilities.InteractWithDb;
import com.testautomation.builders.FooBuilder;
import com.testautomation.database.DatabaseConnectionInfo;
import com.testautomation.database.DatabaseType;
import com.testautomation.database.entity.Example;
import com.testautomation.facts.NetflixPlans;
import com.testautomation.models.Foo;
import com.testautomation.users.Datum;
import com.testautomation.users.RegisterUserInfo;
import com.testautomation.users.UpdateUserInfo;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.testautomation.questions.GetUsersQuestion;
import com.testautomation.questions.ResponseCode;
import com.testautomation.tasks.GetUsers;
import com.testautomation.tasks.RegisterUser;
import com.testautomation.tasks.UpdateUser;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.filter;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(SerenityRunner.class)
public class SerenityInitialTests {

    // Url base
    private static String restApiUrl = "http://localhost:5000/api";

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
        //CriteriaBuilder permite crear las consultas como si fuera un builder
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

    @Test
    public void initial(){
        /*Foo foo = new Foo();
        foo.setName("algo");
        foo.setAge(10);
        foo.setLastName("algomas");

        Foo foo1 = new Foo();
        foo1.setName("algo");
        foo1.setAge(10);
        System.out.println(foo.equals(foo1));
        */
        /*
        Foo foo2 = Foo.builder()
                .age(10)
                .email("email")
                .lastName("lastName")
                .build();

        Foo foo3 = Foo.builder()
                .age(10)
                .email("email")
                .lastName("lastName")
                .build();
        */

        Foo foo2 = FooBuilder
                .withName("Pepito")
                .build();

        Foo foo3 = FooBuilder
                .withName("Pepito")
                .andAge(50)
                .andLastName("Rodriguez")
                .build();

        System.out.println(foo2.toString());
        System.out.println(foo3.toString());



    }


    @Test
    public void getUsers(){
        //  Actor que se le da la abilidad de poder comunicarse con la Api
        Actor pepito = Actor.named("Pepito the trainer")
                .whoCan(CallAnApi.at(restApiUrl));
        // Se invoca la tarea
        pepito.attemptsTo(
                GetUsers.fronPage(1)
        );
        //Se valida el estado de la respuesta de la peticion
        //assertThat(SerenityRest.lastResponse().statusCode()).isEqualTo(200);
        pepito.should(
                seeThat("el codigo de respuesta: ", ResponseCode.was(),equalTo(200))
        );

        // Se trae sobre el question que retorna los datos se aplica un filtro para que traiga el usuario con id 1
        Datum user = new GetUsersQuestion().answeredBy(pepito).
                getData().stream().filter(x -> x.getId() == 1 ).findFirst().orElse(null);

        pepito.should(
                seeThat("El usuario no es nulo", actor -> user, notNullValue())
        );

        pepito.should(
                seeThat("El usuario no es nulo", actor -> user.getEmail(), equalTo("george.bluth@reqres.in")),
                seeThat("el avatar del usuario", actor -> user.getAvatar(), equalTo("avatar"))
        );
    }

    @Test
    public void registerUserTest(){
        /*
        //  Actor que se le da la abilidad de poder comunicarse con la Api
        Actor pepito = Actor.named("Pepito the trainer")
                .whoCan(CallAnApi.at(restApiUrl));

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

        pepito.should(
                seeThat("el codigo de respuesta: ", ResponseCode.was(),equalTo(200))
        );
        */
    }

    @Test
    public void registerUserTest2(){
        /*
        //  Actor que se le da la abilidad de poder comunicarse con la Api
        Actor pepito = Actor.named("Pepito the trainer")
                .whoCan(CallAnApi.at(restApiUrl));

        RegisterUserInfo registerUserInfo = new RegisterUserInfo();

        registerUserInfo.setName("morpheus");
        registerUserInfo.setJob("leader");
        registerUserInfo.setEmail("tracey.ramos@reqres.in");
        registerUserInfo.setPassword("serenity");

        // Se invoca la tarea
        pepito.attemptsTo(
                RegisterUser.withInfo(registerUserInfo)
        );

        pepito.should(
                seeThat("el codigo de respuesta: ", ResponseCode.was(),equalTo(200))
        );
        */
    }

    @Test
    public void updateUserTest(){
        //  Actor que se le da la abilidad de poder comunicarse con la Api
        Actor pepito = Actor.named("Pepito the trainer")
                .whoCan(CallAnApi.at(restApiUrl));

        UpdateUserInfo updateUserInfo = new UpdateUserInfo();

        updateUserInfo.setName("morpheus");
        updateUserInfo.setJob("leader");

        // Se invoca la tarea
        pepito.attemptsTo(
                UpdateUser.withInfo(updateUserInfo)
        );

        pepito.should(
                seeThat("el codigo de respuesta: ", ResponseCode.was(),equalTo(200))
        );

    }

    @Test
    public void factTest(){
        //  Actor que se le da la abilidad de poder comunicarse con la Api
        Actor pepito = Actor.named("Pepito the trainer")
                .whoCan(CallAnApi.at(restApiUrl));

        pepito.has(NetflixPlans.toViewSeries());

    }


}
