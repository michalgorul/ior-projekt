package src.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import src.HibernateSession;
import src.dao.Dao;
import src.model.Address;

import java.util.ArrayList;
import java.util.List;

public class AddressDaoHib implements Dao<Address> {

    public Address getById(int id) {
        Session session = HibernateSession.INSTANCE.getSessionFactory().getCurrentSession();
        if (!session.isOpen()) {
            session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        }
        Address address = session.get(Address.class, id);
        session.close();
        return address;
    }

    @Override
    public List<Address> getAll() {
        Session session = HibernateSession.INSTANCE.getSessionFactory().getCurrentSession();
        if (!session.isOpen()) {
            session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        }
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
        Session session = HibernateSession.INSTANCE.getSessionFactory().getCurrentSession();
        if (!session.isOpen()) {
            session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        }
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

    @Override
    public Address update(Address existing, Address updated) {
        Session session = HibernateSession.INSTANCE.getSessionFactory().getCurrentSession();
        if (!session.isOpen()) {
            session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        }
        Transaction transaction = session.getTransaction();
        Address mergedAddress = null;
        transaction.begin();
        try {
            session.evict(existing);
            existing.setCountry(updated.getCountry());
            existing.setCity(updated.getCity());
            existing.setPostalCode(updated.getPostalCode());
            existing.setStreet(updated.getStreet());
            mergedAddress = (Address) session.merge(existing);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            exception.printStackTrace();
        }
        session.close();
        return mergedAddress;
    }
}