package model;

import com.github.javafaker.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Location {

    private String street;
    private String city;
    private String state;
    private String country;
    private String timezone;

}
