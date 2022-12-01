package src.loader;

import lombok.Getter;
import lombok.Setter;
import src.dao.*;
import src.model.*;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
public class Loader {
    private static AddressDao addressDao = null;
    private static FieldOfStudyDao fieldOfStudyDao = null;
    private static PersonDao personDao = null;
    private static StudentDao studentDao = null;
    private static SubjectDao subjectDao = null;
    private static TeacherDao teacherDao = null;
    private static TestDao testDao = null;

    public Loader() {
        addressDao = new AddressDao();
        fieldOfStudyDao = new FieldOfStudyDao();
        personDao = new PersonDao();
        studentDao = new StudentDao();
        subjectDao = new SubjectDao();
        teacherDao = new TeacherDao();
        testDao = new TestDao();
    }

    public void load(){
        loadAddresses();
        loadFieldsOfStudies();
        loadPeople();
        loadStudents();
        loadSubjects();
        loadTeachers();
        loadTests();
    }

    private List<Address> getAddresses(){
        Address address1 = new Address("Poland", "Katowice", "40-400", "Paderewskiego");
        Address address2 = new Address("Poland", "Warszawa", "80-100", "Warszawska");
        Address address3 = new Address("Poland", "Sopot", "50-800", "Korfantego");
        Address address4 = new Address("Poland", "Warta", "48-450", "Sikorskiego");
        Address address5 = new Address("Poland", "Tymbark", "20-700", "Kosmiczna");
        Address address6 = new Address("Poland", "Lublin", "20-770", "3 Maja");
        Address address7 = new Address("Poland", "Szczecin", "20-930", "1 Maja");
        Address address8 = new Address("Poland", "Częstochowa", "10-930", "Wolności");

        return Stream.of(address1, address2, address3, address4, address5, address6,
                address7, address8).collect(Collectors.toList());
    }

    private void loadAddresses() {
        List<Address> list = getAddresses();
        list.forEach(entry -> addressDao.save(entry));
    }

    private void loadFieldsOfStudies() {
        FieldOfStudy fieldOfStudy1 = new FieldOfStudy("Informatyka", "SSI");
        FieldOfStudy fieldOfStudy2 = new FieldOfStudy("Informatyka", "SSM");
        FieldOfStudy fieldOfStudy3 = new FieldOfStudy("Automatyka i Robotyka", "SSI");
        FieldOfStudy fieldOfStudy4 = new FieldOfStudy("Automatyka i Robotyka", "SSM");
        FieldOfStudy fieldOfStudy5 = new FieldOfStudy("Makro", "SSI");
        FieldOfStudy fieldOfStudy6 = new FieldOfStudy("Makro", "SSM");
        FieldOfStudy fieldOfStudy7 = new FieldOfStudy("Politologia", "SSI");
        FieldOfStudy fieldOfStudy8 = new FieldOfStudy("Politologia", "SSM");

        Stream.of(fieldOfStudy1, fieldOfStudy2, fieldOfStudy3,
                        fieldOfStudy4, fieldOfStudy5, fieldOfStudy6, fieldOfStudy7, fieldOfStudy8)
                .collect(Collectors.toList())
                .forEach(entry -> fieldOfStudyDao.save(entry));
    }

    private void loadPeople() {
        Person person1 = new Person("Michał", "Góral", "mgoral@wp.pl");
        Person person2 = new Person("Wojciech", "Kowalski", "wkowalski@wp.pl");
        Person person3 = new Person("Kamil", "Nowak", "knowak@wp.pl");
        Person person4 = new Person("Karol", "Wojciechowski", "kwojciechowski@wp.pl");
        Person person5 = new Person("Adam", "Czyż", "aczyz@wp.pl");
        Person person6 = new Person("Marcin", "Żurowiec", "mzurowiec@wp.pl");
        Person person7 = new Person("Maciej", "Pradelski", "mpradelski@wp.pl");
        Person person8 = new Person("Dawid", "Kowalczyk", "skowalczyk@wp.pl");

        Stream.of(person1, person2, person3, person4, person5, person6, person7, person8)
                .collect(Collectors.toList())
                .forEach(entry -> personDao.save(entry));
    }

    private void loadStudents() {
        List<Address> addresses = getAddresses();
        Student student1 = new Student(addresses.get(5), 282642, 2);
        Student student2 = new Student(addresses.get(2), 282642, 1);
        Student student3 = new Student(addresses.get(1), 282542, 3);
        Student student4 = new Student(addresses.get(4), 282632, 5);
        Student student5 = new Student(addresses.get(3), 281642, 6);
        Student student6 = new Student(addresses.get(7), 262642, 1);
        Student student7 = new Student(addresses.get(0), 482642, 7);
        Student student8 = new Student(addresses.get(6), 288682, 1);

        Stream.of(student1, student2, student3, student4, student5, student6, student7, student8)
                .collect(Collectors.toList())
                .forEach(entry -> personDao.save(entry));
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
                .forEach(entry -> subjectDao.save(entry));
    }

    private void loadTeachers() {
        List<Address> addresses = getAddresses();
        Teacher teacher1 = new Teacher(addresses.get(0), "Magister");
        Teacher teacher2 = new Teacher(addresses.get(1), "Doktor");
        Teacher teacher3 = new Teacher(addresses.get(2), "Doktor Habilitowany");
        Teacher teacher4 = new Teacher(addresses.get(3), "Profesor Zwyczajny");
        Teacher teacher5 = new Teacher(addresses.get(4), "Profesor Nadwyczajny");
        Teacher teacher6 = new Teacher(addresses.get(5), "Magister inżynier");
        Teacher teacher7 = new Teacher(addresses.get(6), "Doktor");
        Teacher teacher8 = new Teacher(addresses.get(7), "Profesor Zwyczajny");

        Stream.of(teacher1, teacher2, teacher3, teacher4, teacher5, teacher6, teacher7, teacher8)
                .collect(Collectors.toList())
                .forEach(entry -> teacherDao.save(entry));
    }

    private void loadTests() {
        Test test1 = new Test(new Date(1669907466884L), 5);
        Test test2 = new Test(new Date(1669907466884L), 2);
        Test test3 = new Test(new Date(1669907465884L), 3);
        Test test4 = new Test(new Date(1669907436884L), 4);
        Test test5 = new Test(new Date(1669907466884L), 4);
        Test test6 = new Test(new Date(1669905466884L), 5);
        Test test7 = new Test(new Date(1669906466884L), 5);
        Test test8 = new Test(new Date(1669902466884L), 2);

        Stream.of(test1, test2, test3, test4, test5, test6, test7, test8)
                .collect(Collectors.toList())
                .forEach(event -> testDao.save((event)));

    }
}