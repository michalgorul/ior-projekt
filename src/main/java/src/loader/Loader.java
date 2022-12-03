package src.loader;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.Transaction;
import src.HibernateSession;
import src.dao.hibernate.*;
import src.model.*;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
public class Loader {
    private static AddressDaoHib addressDaoHib = null;
    private static FieldOfStudyDaoHib fieldOfStudyDaoHib = null;
    private static PersonDaoHib personDaoHib = null;
    private static StudentDaoHib studentDaoHib = null;
    private static SubjectDaoHib subjectDaoHib = null;
    private static TeacherDaoHib teacherDaoHib = null;
    private static TestDaoHib testDaoHib = null;

    public Loader() {
        addressDaoHib = new AddressDaoHib();
        fieldOfStudyDaoHib = new FieldOfStudyDaoHib();
        personDaoHib = new PersonDaoHib();
        studentDaoHib = new StudentDaoHib();
        subjectDaoHib = new SubjectDaoHib();
        teacherDaoHib = new TeacherDaoHib();
        testDaoHib = new TestDaoHib();
    }

    public void load() {

        loadAddresses();
        loadFieldsOfStudies();
        loadSubjects();

        loadStudents();
        loadTeachers();
        loadTests();
//        HibernateSession.truncate();
    }

    private List<Address> getAddresses() {
        Address address1 = new Address("Poland", "Katowice", "40-400", "Paderewskiego");
        Address address2 = new Address("Poland", "Warszawa", "80-100", "Warszawska");
        Address address3 = new Address("Poland", "Sopot", "50-800", "Korfantego");
        Address address4 = new Address("Poland", "Warta", "48-450", "Sikorskiego");
        Address address5 = new Address("Poland", "Tymbark", "20-700", "Kosmiczna");
        Address address6 = new Address("Poland", "Lublin", "20-770", "3 Maja");
        Address address7 = new Address("Poland", "Szczecin", "20-930", "1 Maja");
        Address address8 = new Address("Poland", "Czestochowa", "10-930", "Wolnosci");

        return Stream.of(address1, address2, address3, address4, address5, address6,
                address7, address8).collect(Collectors.toList());
    }

    List<FieldOfStudy> getFieldOfStudies() {
        FieldOfStudy fieldOfStudy1 = new FieldOfStudy("Informatyka", "SSI");
        FieldOfStudy fieldOfStudy2 = new FieldOfStudy("Informatyka", "SSM");
        FieldOfStudy fieldOfStudy3 = new FieldOfStudy("Automatyka i Robotyka", "SSI");
        FieldOfStudy fieldOfStudy4 = new FieldOfStudy("Automatyka i Robotyka", "SSM");
        FieldOfStudy fieldOfStudy5 = new FieldOfStudy("Makro", "SSI");
        FieldOfStudy fieldOfStudy6 = new FieldOfStudy("Makro", "SSM");
        FieldOfStudy fieldOfStudy7 = new FieldOfStudy("Politologia", "SSI");
        FieldOfStudy fieldOfStudy8 = new FieldOfStudy("Politologia", "SSM");

        return Stream.of(fieldOfStudy1, fieldOfStudy2, fieldOfStudy3, fieldOfStudy4,
                fieldOfStudy5, fieldOfStudy6,
                fieldOfStudy7, fieldOfStudy8).collect(Collectors.toList());
    }

    private void loadAddresses() {
        List<Address> list = getAddresses();
        list.forEach(entry -> addressDaoHib.save(entry));
    }

    private void loadFieldsOfStudies() {
        List<FieldOfStudy> fieldOfStudies = getFieldOfStudies();
        fieldOfStudies.forEach(entry -> fieldOfStudyDaoHib.save(entry));
    }

    private void loadStudents() {
        Session session = HibernateSession.INSTANCE.getSessionFactory().getCurrentSession();
        if (!session.isOpen()) {
            session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        }
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Person person1 = new Person("Michal", "Goral", "mgoral@wp.pl");
        Person person2 = new Person("Wojciech", "Kowalski", "wkowalski@wp.pl");
        Person person3 = new Person("Kamil", "Nowak", "knowak@wp.pl");
        Person person4 = new Person("Karol", "Wojciechowski", "kwojciechowski@wp.pl");

        Student student1 = new Student(session.load(Address.class, 1), person1, 282642, 2,
                session.load(FieldOfStudy.class, 1));
        Student student2 = new Student(session.load(Address.class, 2), person2, 282642, 1,
                session.load(FieldOfStudy.class, 2));
        Student student3 = new Student(session.load(Address.class, 1), person3, 282542, 3,
                session.load(FieldOfStudy.class, 2));
        Student student4 = new Student(session.load(Address.class, 4), person4, 282632, 5,
                session.load(FieldOfStudy.class, 3));

        student1.addSubject(session.load(Subject.class, 1));
        student1.addSubject(session.load(Subject.class, 2));
        student2.addSubject(session.load(Subject.class, 3));
        student2.addSubject(session.load(Subject.class, 4));
        student3.addSubject(session.load(Subject.class, 5));
        student3.addSubject(session.load(Subject.class, 6));
        student4.addSubject(session.load(Subject.class, 7));
        student4.addSubject(session.load(Subject.class, 8));

        transaction.commit();
        session.close();

        Stream.of(student1, student2, student3, student4)
                .collect(Collectors.toList())
                .forEach(entry -> personDaoHib.save(entry));
    }

    private void loadSubjects() {
        Subject subject1 = new Subject("Podstawy Informatyki");
        Subject subject2 = new Subject("Interfejsy Obiektowo-Relacyjne");
        Subject subject3 = new Subject("Modelowanie cyfrowe");
        Subject subject4 = new Subject("Metody pracy zespołowej");
        Subject subject5 = new Subject("Trendy rozwojowe w inżynierii danych");
        Subject subject6 = new Subject("Zaawansowane Bazy Danych i Hurtownie Danych");
        Subject subject7 = new Subject("Filozofia");
        Subject subject8 = new Subject("Socjologia");

        Stream.of(subject1, subject2, subject3, subject4, subject5, subject6,
                        subject7, subject8)
                .collect(Collectors.toList())
                .forEach(entry -> subjectDaoHib.save(entry));
    }

    private void loadTeachers() {
        Session session = HibernateSession.INSTANCE.getSessionFactory().getCurrentSession();
        if (!session.isOpen()) {
            session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        }
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Person person5 = new Person("Adam", "Czyz", "aczyz@wp.pl");
        Person person6 = new Person("Marcin", "Zurowiec", "mzurowiec@wp.pl");
        Person person7 = new Person("Maciej", "Pradelski", "mpradelski@wp.pl");
        Person person8 = new Person("Dawid", "Kowalczyk", "skowalczyk@wp.pl");


        Teacher teacher1 = new Teacher(session.load(Address.class, 5), person5, "Magister");
        Teacher teacher2 = new Teacher(session.load(Address.class, 6), person6, "Doktor");
        Teacher teacher3 = new Teacher(session.load(Address.class, 7), person7, "Doktor " +
                "Habilitowany");
        Teacher teacher4 = new Teacher(session.load(Address.class, 8), person8, "Profesor " +
                "Zwyczajny");

        transaction.commit();
        session.close();

        Stream.of(teacher1, teacher2, teacher3, teacher4)
                .collect(Collectors.toList())
                .forEach(entry -> teacherDaoHib.save(entry));
    }

    private void loadTests() {
        Session session = HibernateSession.INSTANCE.getSessionFactory().getCurrentSession();
        if (!session.isOpen()) {
            session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        }
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Test test1 = new Test(new Date(1669907466884L), 5, session.load(Subject.class, 1),
                session.load(Student.class, 1));
        Test test2 = new Test(new Date(1669907466884L), 2, session.load(Subject.class, 2),
                session.load(Student.class, 1));
        Test test3 = new Test(new Date(1669907465884L), 3, session.load(Subject.class, 3),
                session.load(Student.class, 2));
        Test test4 = new Test(new Date(1669907436884L), 4, session.load(Subject.class, 4),
                session.load(Student.class, 3));
        Test test5 = new Test(new Date(1669907466884L), 4, session.load(Subject.class, 5),
                session.load(Student.class, 4));
        Test test6 = new Test(new Date(1669905466884L), 5, session.load(Subject.class, 6),
                session.load(Student.class, 4));
        Test test7 = new Test(new Date(1669906466884L), 5, session.load(Subject.class, 7),
                session.load(Student.class, 4));
        Test test8 = new Test(new Date(1669902466884L), 2, session.load(Subject.class, 8),
                session.load(Student.class, 2));
        Test test9 = new Test(new Date(1669909996884L), 5, session.load(Subject.class, 1),
                session.load(Student.class, 1));
        Test test10 = new Test(new Date(1669957466884L), 4, session.load(Subject.class, 1),
                session.load(Student.class, 1));
        Test test11 = new Test(new Date(1669807466884L), 3, session.load(Subject.class, 1),
                session.load(Student.class, 1));

        transaction.commit();
        session.close();

        Stream.of(test1, test2, test3, test4, test5, test6, test7, test8, test9, test10, test11)
                .collect(Collectors.toList())
                .forEach(event -> testDaoHib.save((event)));

    }
}