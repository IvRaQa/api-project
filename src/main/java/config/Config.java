package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import java.util.HashMap;
import java.util.Map;

public class Config {


    static {
        Map<String, String> map = new HashMap<>();

        map.put("app-id", "664091419c46c1abf28efb44");
        map.put("Content-Type", "application/json");
        map.put("Accept", "application/json");


        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setRelaxedHTTPSValidation()
                .setBaseUri("https://dummyapi.io/data")
                .setBasePath("/v1/")
                .addHeaders(map)
                .addFilter(new RequestLoggingFilter())
        //ovo ne ide u responseSpecification
                .addFilter(new ResponseLoggingFilter())
                .build();

        ResponseSpecification responseSpecification = new ResponseSpecBuilder()
                //.expectStatusCode(200)
                .build();
        RestAssured.requestSpecification = requestSpecification;
        RestAssured.responseSpecification = responseSpecification;

    }
}