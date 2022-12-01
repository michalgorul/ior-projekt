package src.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "persons")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private int id;
    @Column(name = "f_name")
    private String fName;
    @Column(name = "s_name")
    private String sName;
    private String email;

    public Person(String fName, String sName, String email){
        this.fName = fName;
        this.sName = sName;
        this.email = email;
    }
}
