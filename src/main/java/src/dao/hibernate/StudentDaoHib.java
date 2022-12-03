package src.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import src.HibernateSession;
import src.dao.Dao;
import src.dto.AddressDto;
import src.dto.StudentDto;
import src.model.Student;

import javax.persistence.Tuple;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoHib implements Dao<Student> {

    public StudentDto getByIdHib(int id) {
        Session session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        StudentDto studentDto = null;
        try {
            studentDto = session.createQuery("SELECT " +
                            "new src.dto.StudentDto(s.id, s.fName, s.sName, s.email, s.indexNo, s.semester) " +
                            "from Student s " +
                            "WHERE s.id = :id", StudentDto.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        session.close();
        return studentDto;
    }


    @Override
    public Student getById(int id) {
        return null;
    }

    @Override
    public List<Student> getAll() {
        Session session = HibernateSession.INSTANCE.getSessionFactory().getCurrentSession();
        if (!session.isOpen()) {
            session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        }
        List<Student> students = new ArrayList<>();
        try {
            students = session.createQuery("SELECT s from Student s").getResultList();
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

    public List<String> getSubjectsByStudentIdHib(int id) {
        Session session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        List<String> subjects = new ArrayList<>();
        try {
            subjects = session.createQuery("SELECT " +
                            "s.name " +
                            "from Student t " +
                            "join t.subjects s " +
                            "where t.id = :id", String.class)
                    .setParameter("id", id)
                    .getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        session.close();
        return subjects;
    }
}