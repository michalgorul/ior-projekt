package src.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "field_of_study")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class FieldOfStudy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "field_of_study_id")
    private int id;
    private String name;
    private String type;
}
