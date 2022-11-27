package src.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "subject")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private int id;
    @Column(name = "name")
    private String name;

    public Subject(String name){
        this.name = name;
    }
}
