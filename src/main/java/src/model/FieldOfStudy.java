package src.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "fields_of_studies")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class FieldOfStudy implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "field_of_study_id")
    private int id;

    private String name;

    private String type;

    @OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY, mappedBy = "fieldOfStudy")
    private Set<Student> students = new HashSet<>(10);
}
