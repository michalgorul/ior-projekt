package src.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import src.HibernateSession;
import src.dao.Dao;
import src.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDaoHib implements Dao<Student> {

    public Student getById(int id) {
        Session session = HibernateSession.INSTANCE.getSessionFactory().getCurrentSession();
        if (!session.isOpen()) {
            session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        }
        Student student = session.get(Student.class, id);
        session.close();
        return student;
    }

//    @Transactional
//    public List<Student> getBySurname(String surname) {
//        Session session = HibernateSession.INSTANCE.getSessionFactory().getCurrentSession();
//        if (!session.isOpen()) {
//            session = HibernateSession.INSTANCE.getSessionFactory().openSession();
//        }
//        Transaction transaction = session.getTransaction();
//        transaction.begin();
//        List<Student> students = new ArrayList<>();
//        try {
//            Hibernate.initialize(students);
//            students = session.createQuery("SELECT s " +
//                            "FROM Student s left join fetch FieldOfStudy f on s.fieldOfStudy.id =" +
//                            " f.id " +
//                            "WHERE s.sName = :sname")
//                    .setParameter("sname", surname)
//                    .getResultList();
//            transaction.commit();
//
//        } catch (Exception ex) {
//            transaction.rollback();
//            ex.printStackTrace();
//        }
//        session.close();
//        return students;
//    }

    @Override
    public List<Student> getAll() {
        Session session = HibernateSession.INSTANCE.getSessionFactory().getCurrentSession();
        if (!session.isOpen()) {
            session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        }
        List<Student> students = new ArrayList<>();
        try {
            students = session.createQuery("SELECT s from StudentDto s").getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        session.close();
        return students;
    }

    public void save(Student student) {
        Session session = HibernateSession.INSTANCE.getSessionFactory().getCurrentSession();
        if (!session.isOpen()) {
            session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        }
        Transaction transaction = session.getTransaction();
        transaction.begin();
        try {
            session.persist(student);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            exception.printStackTrace();
        }
        session.close();
    }

    @Override
    public Student update(Student existing, Student updated) {
        Session session = HibernateSession.INSTANCE.getSessionFactory().getCurrentSession();
        if (!session.isOpen()) {
            session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        }
        Transaction transaction = session.getTransaction();
        Student studentMerged = null;
        transaction.begin();
        try {
            session.evict(existing);
            existing.setSName(updated.getSName());
            existing.setFName(updated.getFName());
            existing.setEmail(updated.getEmail());
            existing.setIndexNo(updated.getIndexNo());
            existing.setSemester(updated.getSemester());
            existing.setAddress(updated.getAddress());
            existing.setFieldOfStudy(updated.getFieldOfStudy());
            existing.setSubjects(updated.getSubjects());
            existing.setTests(updated.getTests());
            studentMerged = (Student) session.merge(existing);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            exception.printStackTrace();
        }
        session.close();
        return studentMerged;
    }

}