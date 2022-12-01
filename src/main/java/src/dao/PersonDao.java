package src.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import src.HibernateSession;
import src.model.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonDao  implements Dao<Person> {
    public Person getById(int id) {
        Session session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        Person person = session.get(Person.class, id);
        session.close();
        return person;
    }

    @Override
    public List<Person> getAll() {
        Session session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        List<Person> people = new ArrayList<>();
        try {
            people = session.createQuery("SELECT p from Person p").getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        session.close();
        return people;
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
