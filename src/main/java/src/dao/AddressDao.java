package src.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import src.HibernateSession;
import src.model.Address;

import java.util.ArrayList;
import java.util.List;

public class AddressDao implements Dao<Address> {

    public Address getById(int id) {
        Session session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        Address address = session.get(Address.class, id);
        session.close();
        return address;
    }

    @Override
    public List<Address> getAll() {
        Session session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        List<Address> addresses = new ArrayList<>();
        try {
            addresses = session.createQuery("SELECT a from Address a").getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        session.close();
        return addresses;
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
