package src.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "teachers")
@PrimaryKeyJoinColumn(name = "student_id", foreignKey = @ForeignKey(name = "fk_teacher_person"))
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Teacher extends Person implements Serializable {

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "address_id", foreignKey = @ForeignKey(name = "fk_teacher_address"))
    private Address address;

    private String title;
}
