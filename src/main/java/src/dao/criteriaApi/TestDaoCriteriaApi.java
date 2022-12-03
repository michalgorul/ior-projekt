package src.dao.criteriaApi;

import org.hibernate.Session;
import src.HibernateSession;
import src.model.Student;
import src.model.Subject;
import src.model.Test;

import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public class TestDaoCriteriaApi {

    public List<Tuple> getByStudentIdCriteria(int id) {
        Session session = HibernateSession.INSTANCE.getSessionFactory().openSession();

        // Create query
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Tuple> query = cb.createQuery(Tuple.class);
        // Define FROM clause
        Root<Test> root = query.from(Test.class);
        // Define JOIN clauses
        Join<Test, Student> student = root.join("student");
        Join<Test, Subject> subject = root.join("subject");
        // Define WHERE clause
        query.where(cb.equal(student.get("id"), id));
        // Define Tuple projection
        query.select(cb.construct(Tuple.class,
                root.get("date"),
                root.get("grade"),
                student.get("fName"),
                student.get("sName"),
                subject.get("name")));

        // Execute query
        return session.createQuery(query).getResultList();
    }

    public Double getGradeAvgByStudentIdCriteria(int id) {
        Session session = HibernateSession.INSTANCE.getSessionFactory().openSession();

        // Create query
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Double> query = cb.createQuery(Double.class);
        // Define FROM clause
        Root<Test> root = query.from(Test.class);
        // Define JOIN clauses
        Join<Test, Student> student = root.join("student");
        // Define WHERE clause
        query.where(cb.equal(student.get("id"), id));
        // Define Double projection
        query.select(cb.avg(root.get("grade")));

        // Execute query
        return session.createQuery(query).getSingleResult();
    }

    public List<Tuple> getGradeAvgHavingCriteria() {
        Session session = HibernateSession.INSTANCE.getSessionFactory().openSession();

        // Create query
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Tuple> query = cb.createQuery(Tuple.class);
        // Define FROM clause
        Root<Test> root = query.from(Test.class);
        // Define JOIN clauses
        Join<Test, Student> student = root.join("student");
        // Define Tuple projection
        query.multiselect(student.get("indexNo"), student.get("fName"), student.get("sName"), cb.avg(root.get("grade")))
                .groupBy(student.get("indexNo"), student.get("fName"), student.get("sName") )
                .having(cb.greaterThanOrEqualTo(cb.count(root.get("grade")), 2L));

        // Execute query
        return session.createQuery(query).getResultList();
    }

}