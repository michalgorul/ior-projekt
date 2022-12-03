package src.dto;

import lombok.*;

import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class AddressDto implements Serializable {
    private int id;
    private String country;
    private String city;
    private String postalCode;
    private String street;
}
