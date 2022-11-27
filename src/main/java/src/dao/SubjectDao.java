package src.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import src.HibernateSession;
import src.model.Subject;

public class SubjectDao {

    public Subject get(int id) {
        Session session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        Subject subject = session.get(Subject.class, id);
        session.close();
        return subject;
    }

    public void save() {
        Session session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        try {
            session.persist(new Subject("IOR"));
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            exception.printStackTrace();
        }
        session.close();
    }
}
