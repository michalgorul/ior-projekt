package src.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "tests")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_id")
    private int id;
    private Date date;
    private int grade;

    @ManyToOne(
            cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.LAZY,
            optional = false)
    @JoinColumn(
            name = "subject_id",
            foreignKey = @ForeignKey(name = "fk_test_subject"),
            insertable = false,
            updatable = false)
    private Subject subject;

    @ManyToOne(
            cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.LAZY,
            optional = false)
    @JoinColumn(
            name = "student_id",
            foreignKey = @ForeignKey(name = "fk_test_student"),
            insertable = false,
            updatable = false)
    private Student student;
}
