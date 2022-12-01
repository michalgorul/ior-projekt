package src.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import src.HibernateSession;
import src.model.Test;

import java.util.ArrayList;
import java.util.List;

public class TestDao implements Dao<Test> {

    @Override
    public Test getById(int id) {
        Session session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        Test test = session.get(Test.class, id);
        session.close();
        return test;
    }

    public void save(Test test) {
        Session session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        try {
            session.persist(test);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            exception.printStackTrace();
        }
        session.close();
    }

    @Override
    public List<Test> getAll() {
        Session session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        List<Test> tests = new ArrayList<>();
        try {
            tests = session.createQuery("SELECT t from Test t").getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        session.close();
        return tests;
    }

    @Override
    public Test update(Test existing, Test updated) {
        Session session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        Test testMerged = null;
        transaction.begin();
        try {
            session.evict(existing);
            existing.setDate(updated.getDate());
            existing.setGrade(updated.getGrade());
            existing.setStudent(updated.getStudent());
            existing.setSubject(updated.getSubject());
            testMerged = (Test) session.merge(existing);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            exception.printStackTrace();
        }
        session.close();
        return testMerged;
    }
}