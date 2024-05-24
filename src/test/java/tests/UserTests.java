package tests;

import com.github.javafaker.Faker;
import config.Config;
import io.restassured.response.Response;
import model.Location;
import model.UserRequest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Locale;

import static endPoints.UserEndPoints.*;
import static io.restassured.RestAssured.given;

public class UserTests extends Config {

    SoftAssert softAssert;

    @BeforeMethod(alwaysRun = true)
    public void setup(){
        softAssert = new SoftAssert();
    }


    @Test
    public void getUsersTest() {

        Response response = given()
                .when().get(GET_ALL_USERS);

        Assert.assertEquals(response.getStatusCode(), 200, "Expect 200 but got " + response.getStatusCode());
        String actualFirstName = response.jsonPath().get("data[0].firstName");
        Assert.assertEquals(actualFirstName, "Sara");
    }

//    @Test
//    public void getUsersUsingJsonPathTests(){
//
//        String userId= "60d0fe4f5311236168a109ca";
//        JsonPath jsonPath= given()
//                .baseUri("https://dummyapi.io/data")
//                .basePath("/v1/")
//                .pathParam("id",userId)
//                .header("app-id","66476aa6c4ca19a8d50e8b42")
//                .when().get(GET_USER_BY_ID).getBody().jsonPath();
//
//        String actualUserId=jsonPath.getString("id");
//        System.out.println( actualUserId);
//       Assert.assertEquals(actualUserId,"60d0fe4f5311236168a109ca");
//
//    }

    @Test
    public void getUserByIdTest() {

        String userId = "66504e46f1263780e3b2c6ef";
        Response response = given()
                .pathParam("id", userId)
                .when().get(GET_USER_BY_ID);

        Assert.assertEquals(response.getStatusCode(), 200, "Expect 200 but got " + response.getStatusCode());
        String actualId = response.jsonPath().get("id");
        Assert.assertEquals(actualId, userId);
    }

    @Test
    public void deleteUserByIdTest() {

        String userId = "66504ea94b52247ebbdea830";
        Response response = given()
                .pathParam("id", userId)
                .when().delete(DELETE_USER_BY_ID);

        Assert.assertEquals(response.getStatusCode(), 200, "Expect 200 but got " + response.getStatusCode());
        String actualId = response.jsonPath().get("id");
        Assert.assertEquals(actualId, userId);

        Response responseError = given()
                .pathParam("id", userId)
                .when().delete(DELETE_USER_BY_ID);

        Assert.assertEquals(responseError.getStatusCode(), 404);
    }

    @Test
    public void createUserByUsingJavaObjectTest() {

        Response response = given()
                .body("{\n" +
                        "    \"title\": \"miss\",\n" +
                        "    \"firstName\": \"Naomi\",\n" +
                        "    \"lastName\": \"Rodrigues\",\n" +
                        "    \"picture\": \"https://randomuser.me/api/portraits/med/women/39.jpg\",\n" +
                        "    \"gender\": \"female\",\n" +
                        "    \"email\": \"naomi.ro1yuyfx@example.com\",\n" +
                        "    \"dateOfBirth\": \"1973-06-13T23:33:31.385Z\",\n" +
                        "    \"phone\": \"(40) 6623-4814\",\n" +
                        "    \"location\": {\n" +
                        "        \"street\": \"9134, Rua Castro Alves \",\n" +
                        "        \"city\": \"Garanhuns\",\n" +
                        "        \"state\": \"Roraima\",\n" +
                        "        \"country\": \"Brazil\",\n" +
                        "        \"timezone\": \"+9:00\"\n" +
                        "    }\n" +
                        "}")
                .when().post(CREATE_USER);

        Assert.assertEquals(response.getStatusCode(), 200, "Expect 200 but got " + response.getStatusCode());

    }

    @Test
    public void createUserTest() {
            Faker faker = new Faker(new Locale("en-US"));

            Location location = Location.builder()
                    .city(faker.address().city())
                    .state(faker.address().state())
                    .street(faker.address().streetAddress())
                    .country(faker.address().country())
                    .build();

            UserRequest userRequest =UserRequest.builder()
                    .email(faker.internet().emailAddress())
                    .phone(faker.phoneNumber().phoneNumber())
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .location(location)
                    .build();


            Response response= given()
                .body(userRequest)
                .when().post(CREATE_USER);

        Assert.assertEquals(response.getStatusCode(),200);

    }
}
