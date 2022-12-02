package src.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import src.HibernateSession;
import src.model.Subject;
import src.model.Teacher;

import java.util.ArrayList;
import java.util.List;

public class TeacherDao implements Dao<Teacher> {

    public Teacher getById(int id) {
        Session session = HibernateSession.INSTANCE.getSessionFactory().getCurrentSession();
        if (!session.isOpen()){
            session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        }
        Teacher teacher = session.get(Teacher.class, id);
        session.close();
        return teacher;
    }

    @Override
    public List<Teacher> getAll() {
        Session session = HibernateSession.INSTANCE.getSessionFactory().getCurrentSession();
        if (!session.isOpen()){
            session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        }
        List<Teacher> teachers = new ArrayList<>();
        try {
            teachers = session.createQuery("SELECT t from Teacher t").getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        session.close();
        return teachers;
    }

    public void save(Teacher teacher) {
        Session session = HibernateSession.INSTANCE.getSessionFactory().getCurrentSession();
        if (!session.isOpen()){
            session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        }
        Transaction transaction = session.getTransaction();
        transaction.begin();
        try {
            session.persist(teacher);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            exception.printStackTrace();
        }
        session.close();
    }

    @Override
    public Teacher update(Teacher existing, Teacher updated) {
        Session session = HibernateSession.INSTANCE.getSessionFactory().getCurrentSession();
        if (!session.isOpen()){
            session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        }
        Transaction transaction = session.getTransaction();
        Teacher teacherMerged = null;
        transaction.begin();
        try {
            session.evict(existing);
            existing.setFName(updated.getFName());
            existing.setSName(updated.getSName());
            existing.setEmail(updated.getEmail());
            existing.setTitle(updated.getTitle());
            existing.setAddress(updated.getAddress());
            teacherMerged = (Teacher) session.merge(existing);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            exception.printStackTrace();
        }
        session.close();
        return teacherMerged;
    }
}