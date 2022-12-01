package src.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "addresses")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private int id;

    private String country;

    private String city;

    @Column(name = "postal_code")
    private String postalCode;

    private String street;

    public Address(String country, String city, String postalCode, String street){
        this.country = country;
        this.city = city;
        this.postalCode = postalCode;
        this.street = street;
    }
}
