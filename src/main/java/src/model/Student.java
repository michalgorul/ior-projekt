package src.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
@PrimaryKeyJoinColumn(name = "student_id", foreignKey = @ForeignKey(name = "fk_student_person"))
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Student extends Person implements Serializable {

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "address_id", foreignKey = @ForeignKey(name = "fk_student_address"))
    private Address address;

    @Column(name = "index_no")
    private int indexNo;

    private int semester;

    @ManyToOne(
            cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.LAZY,
            optional = false)
    @JoinColumn(
            name = "field_of_study_id",
            foreignKey = @ForeignKey(name = "fk_field_student"),
            insertable = false,
            updatable = false)
    private FieldOfStudy fieldOfStudy;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "students_subjects",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"),
            foreignKey = @ForeignKey(name = "fk_students_subjects"))
    private Set<Subject> subjects = new HashSet<>();

    @OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY, mappedBy = "student")
    private Set<Test> tests = new HashSet<>(10);

    public void addSubject(Subject subject){
        this.subjects.add(subject);
        subject.getStudents().add(this);
    }
}
