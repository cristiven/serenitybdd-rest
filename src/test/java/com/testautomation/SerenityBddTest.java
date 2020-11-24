package com.testautomation;

//@RunWith(SerenityRunner.class)
public class SerenityBddTest {

    private final String restApiUrl = "http://localhost:5000/api";

   /* @Test
    public void dataBaseConnectionTest() {

        DatabaseConnectionInfo connectionInfo = DatabaseConnectionInfo
                .builder()
                .username("root")
                .databaseType(DatabaseType.MYSQL)
                .url("jdbc:mysql://localhost/test_automation")
                .password("my-secret-pw")
                .entityNames(Stream.of(
                        Example.class)
                        .map(Class::getName)
                        .collect(Collectors.toList()))
                .build();


        Actor pepito = Actor.named("pepito");
        pepito.can(InteractWithDb.using(connectionInfo));

        EntityManager entityManager = InteractWithDb.as(pepito).getManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Example> criteriaQuery = criteriaBuilder
                .createQuery(Example.class);

        Root<Example> userRoot = criteriaQuery.from(Example.class);

        Example queryResult = entityManager
                .createQuery(criteriaQuery
                        .select(userRoot))
                .getSingleResult();

        System.out.println(queryResult);
    }*/

}
