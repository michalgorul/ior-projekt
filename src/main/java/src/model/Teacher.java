package src.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "teacher")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private int id;
    private String teacher;
}
