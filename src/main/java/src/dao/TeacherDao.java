package src.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import src.HibernateSession;
import src.model.Teacher;

import java.util.ArrayList;
import java.util.List;

public class TeacherDao implements Dao<Teacher> {

    public Teacher getById(int id) {
        Session session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        Teacher teacher = session.get(Teacher.class, id);
        session.close();
        return teacher;
    }

    @Override
    public List<Teacher> getAll() {
        Session session = HibernateSession.INSTANCE.getSessionFactory().openSession();
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
        Session session = HibernateSession.INSTANCE.getSessionFactory().openSession();
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
    public void update(Teacher teacher) {

    }
}