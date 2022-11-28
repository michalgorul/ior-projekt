package src.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import src.HibernateSession;
import src.model.Address;

public class AddressDao {

    public Address getById(int id) {
        Session session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        Address address = session.get(Address.class, id);
        session.close();
        return address;
    }

    public void save(Address address) {
        Session session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        try {
            session.persist(address);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            exception.printStackTrace();
        }
        session.close();
    }
}
