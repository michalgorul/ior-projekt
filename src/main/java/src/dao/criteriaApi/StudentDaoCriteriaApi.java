package src.dao.criteriaApi;

import org.hibernate.Session;
import src.HibernateSession;
import src.dto.StudentDto;
import src.dto.SubjectDto;
import src.model.Student;
import src.model.Subject;
import src.model.Test;

import javax.persistence.Tuple;
import javax.persistence.criteria.*;
import java.util.List;

public class StudentDaoCriteriaApi {

    public StudentDto getById(int id) {
        Session session = HibernateSession.INSTANCE.getSessionFactory().openSession();

        // Create query
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<StudentDto> query = cb.createQuery(StudentDto.class);
        // Define FROM clause
        Root<Student> root = query.from(Student.class);
        // Define WHERE clause
        query.where(cb.equal(root.get("id"), id));
        // Define Tuple projection

        query.select(cb.construct(StudentDto.class,
                root.get("id"),
                root.get("fName"),
                root.get("sName"),
                root.get("email"),
                root.get("indexNo"),
                root.get("semester")));

        // Execute query
        return session.createQuery(query).getSingleResult();
    }

    public List<String> getSubjectsByStudentIdCriteria(int id) {
        Session session = HibernateSession.INSTANCE.getSessionFactory().openSession();

        // Create query
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<String> query = cb.createQuery(String.class);
        // Define FROM clause
        Root<Student> root = query.from(Student.class);
        // Define JOIN clause
        Join<Student, Subject> subjects = root.join("subjects");
        // Define WHERE clause
        query.where(cb.equal(root.get("id"), id));
        // Define Tuple projection

        query.select(cb.construct(String.class, subjects.get("name")));

        // Execute query
        return session.createQuery(query).getResultList();
    }

}