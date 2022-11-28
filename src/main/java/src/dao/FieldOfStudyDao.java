package src.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import src.HibernateSession;
import src.model.FieldOfStudy;

public class FieldOfStudyDao {
    public FieldOfStudy getById(int id) {
        Session session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        FieldOfStudy fieldOfStudy = session.get(FieldOfStudy.class, id);
        session.close();
        return fieldOfStudy;
    }

    public void save(FieldOfStudy fieldOfStudy) {
        Session session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        try {
            session.persist(fieldOfStudy);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            exception.printStackTrace();
        }
        session.close();
    }

}
