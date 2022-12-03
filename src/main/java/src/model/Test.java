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

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(
            name = "subject_id",
            foreignKey = @ForeignKey(name = "fk_test_subject"))
    private Subject subject;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(
            name = "student_id",
            foreignKey = @ForeignKey(name = "fk_test_student"))
    private Student student;

    public Test(Date date, int grade, Subject subject, Student student) {
        this.date = date;
        this.grade = grade;
        this.student = student;
        this.subject = subject;
    }
}