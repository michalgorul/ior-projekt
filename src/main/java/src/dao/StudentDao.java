package src.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import src.HibernateSession;
import src.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDao implements Dao<Student> {

    public Student getById(int id) {
        Session session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        Student student = session.get(Student.class, id);
        session.close();
        return student;
    }

    @Override
    public List<Student> getAll() {
        Session session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        List<Student> students = new ArrayList<>();
        try {
            students = session.createQuery("SELECT s from Student s").getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        session.close();
        return students;
    }

    public void save(Student student) {
        Session session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        try {
            session.persist(student);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            exception.printStackTrace();
        }
        session.close();
    }

}
