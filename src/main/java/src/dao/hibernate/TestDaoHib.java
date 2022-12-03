package src.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import src.HibernateSession;
import src.dao.Dao;
import src.model.Test;

import javax.persistence.Tuple;
import java.util.ArrayList;
import java.util.List;

public class TestDaoHib implements Dao<Test> {

    @Override
    public Test getById(int id) {
        Session session = HibernateSession.INSTANCE.getSessionFactory().getCurrentSession();
        if (!session.isOpen()) {
            session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        }
        Test test = session.get(Test.class, id);
        session.close();
        return test;
    }

    public List<Tuple> getByStudentIdHib(int id) {
        Session session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        List<Tuple> tuples = new ArrayList<>();
        try {
            tuples = session.createQuery("SELECT " +
                            "t.date, t.grade, t.student.fName, t.student.sName, t.subject.name " +
                            "from Test t " +
                            "join t.student " +
                            "join t.subject " +
                            "where t.student.id = :id", Tuple.class)
                    .setParameter("id", id)
                    .getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        session.close();
        return tuples;
    }

    public Double getGradeAvgByStudentIdHib(int id) {
        Session session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        Double avg = null;
        try {
            avg = session.createQuery("SELECT " +
                            "AVG(t.grade) " +
                            "from Test t " +
                            "join t.student " +
                            "where t.student.id = :id", Double.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        session.close();
        return avg;
    }

    public List<Tuple> getGradeAvgHavingHib() {
        Session session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        List<Tuple> tuples = new ArrayList<>();
        try {
            tuples = session.createQuery("SELECT " +
                            "s.indexNo, s.fName, s.sName, AVG(t.grade) " +
                            "from Test t " +
                            "join t.student s " +
                            "group by s.indexNo, s.fName, s.sName " +
                            "having count(t.grade) >= 2", Tuple.class)
                    .getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        session.close();
        return tuples;
    }

    public void save(Test test) {
        Session session = HibernateSession.INSTANCE.getSessionFactory().getCurrentSession();
        if (!session.isOpen()) {
            session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        }
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
        Session session = HibernateSession.INSTANCE.getSessionFactory().getCurrentSession();
        if (!session.isOpen()) {
            session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        }
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
        Session session = HibernateSession.INSTANCE.getSessionFactory().getCurrentSession();
        if (!session.isOpen()) {
            session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        }
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