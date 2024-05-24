package model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.javafaker.Faker;
import lombok.*;

import java.util.Locale;

import static io.restassured.RestAssured.given;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@With
public class UserRequest {

    //@JsonProperty("firstName")
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String phone;
    private Location location;
}
