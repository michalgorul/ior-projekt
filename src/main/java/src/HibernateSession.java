package src;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public enum HibernateSession {

    INSTANCE;

    public SessionFactory getSessionFactory() {
        return new Configuration().configure().buildSessionFactory();
    }

    public static void truncate() {
        Session session = INSTANCE.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        try {
            session.createQuery("DELETE from Address").executeUpdate();
            session.createQuery("DELETE from FieldOfStudy").executeUpdate();
            session.createQuery("DELETE from Person").executeUpdate();
            session.createQuery("DELETE from Student").executeUpdate();
            session.createQuery("DELETE from Subject").executeUpdate();
            session.createQuery("DELETE from Teacher").executeUpdate();
            session.createQuery("DELETE from Test").executeUpdate();
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            exception.printStackTrace();
        }
        session.close();
    }
}