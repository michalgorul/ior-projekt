package src.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "teachers")
@PrimaryKeyJoinColumn(name = "teacher_id", foreignKey = @ForeignKey(name = "fk_teacher_person"))
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Teacher extends Person implements Serializable {

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "address_id", foreignKey = @ForeignKey(name = "fk_teacher_address"))
    private Address address;

    private String title;

    public Teacher(Address address, Person person, String title){
        this.title = title;
        this.address = address;
        this.fName = person.getFName();
        this.sName = person.getSName();
        this.email = person.getEmail();
    }
}