package src.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import src.HibernateSession;
import src.model.Student;
import src.model.Subject;

import java.util.ArrayList;
import java.util.List;

public class SubjectDao implements Dao<Subject> {

    public Subject getById(int id) {
        Subject subject = null;
        try (Session session = HibernateSession.INSTANCE.getSessionFactory().openSession()) {
            subject = (Subject) session.createQuery("select s " + "from Subject s " + "left join " +
                    "fetch s.students " + "where s.id = :id").setParameter("id", id).uniqueResult();
            session.close();
            return subject;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Subject> getAll() {
        Session session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        List<Subject> subjects = new ArrayList<>();
        try {
            subjects = session.createQuery("SELECT s from Subject s").getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        session.close();
        return subjects;
    }

    public void save(Subject subject) {
        Session session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        try {
            session.persist(subject);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            exception.printStackTrace();
        }
        session.close();
    }

    @Override
    public Subject update(Subject existing, Subject updated) {
        Session session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        Subject subjectMerged = null;
        transaction.begin();
        try {
            session.evict(existing);
            existing.setName(updated.getName());
            existing.setStudents(updated.getStudents());
            existing.setTests(updated.getTests());
            subjectMerged = (Subject) session.merge(existing);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            exception.printStackTrace();
        }
        session.close();
        return subjectMerged;
    }
}