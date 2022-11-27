package src.model;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "address")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private int id;
    private String country;
    private String city;
    @Column(name = "postal_code")
    private String postalCode;
    private String street;
}
