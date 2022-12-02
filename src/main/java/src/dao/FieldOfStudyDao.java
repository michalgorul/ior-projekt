package src.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import src.HibernateSession;
import src.model.Address;
import src.model.FieldOfStudy;

import java.util.ArrayList;
import java.util.List;

public class FieldOfStudyDao implements Dao<FieldOfStudy> {
    public FieldOfStudy getById(int id) {
        Session session = HibernateSession.INSTANCE.getSessionFactory().getCurrentSession();
        if (!session.isOpen()){
            session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        }
        FieldOfStudy fieldOfStudy = session.get(FieldOfStudy.class, id);
        session.close();
        return fieldOfStudy;
    }

    @Override
    public List<FieldOfStudy> getAll() {
        Session session = HibernateSession.INSTANCE.getSessionFactory().getCurrentSession();
        if (!session.isOpen()){
            session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        }
        List<FieldOfStudy> fieldOfStudies = new ArrayList<>();
        try {
            fieldOfStudies = session.createQuery("SELECT f from FieldOfStudy f").getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        session.close();
        return fieldOfStudies;
    }

    public void save(FieldOfStudy fieldOfStudy) {
        Session session = HibernateSession.INSTANCE.getSessionFactory().getCurrentSession();
        if (!session.isOpen()){
            session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        }
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

    @Override
    public FieldOfStudy update(FieldOfStudy existing, FieldOfStudy updated) {
        Session session = HibernateSession.INSTANCE.getSessionFactory().getCurrentSession();
        if (!session.isOpen()){
            session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        }
        Transaction transaction = session.getTransaction();
        FieldOfStudy fieldOfStudyMerged = null;
        transaction.begin();
        try {
            session.evict(existing);
            existing.setName(updated.getName());
            existing.setType(updated.getType());
            existing.setStudents(updated.getStudents());
            fieldOfStudyMerged = (FieldOfStudy) session.merge(existing);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            exception.printStackTrace();
        }
        session.close();
        return fieldOfStudyMerged;
    }

}