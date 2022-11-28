package src.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import src.HibernateSession;
import src.model.Person;

public class PersonDao {
    public Person getById(int id) {
        Session session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        Person person = session.get(Person.class, id);
        session.close();
        return person;
    }

    public void save(Person person) {
        Session session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        try {
            session.persist(person);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            exception.printStackTrace();
        }
        session.close();
    }

}
