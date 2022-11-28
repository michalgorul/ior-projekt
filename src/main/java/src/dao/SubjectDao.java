package src.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import src.HibernateSession;
import src.model.Subject;

public class SubjectDao {

    public Subject getById(int id) {
        Subject subject = null;
        try (Session session = HibernateSession.INSTANCE.getSessionFactory().openSession()) {
            subject = (Subject) session
                    .createQuery(
                            "select s " +
                                    "from Subject s " +
                                    "left join fetch s.students " +
                                    "where s.id = :id")
                    .setParameter("id", id)
                    .uniqueResult();
            session.close();
            return subject;
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
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
}
