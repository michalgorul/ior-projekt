package src.loader;

import lombok.Getter;
import lombok.Setter;
import src.dao.*;
import src.model.Address;
import src.model.FieldOfStudy;
import src.model.Person;

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

    private void loadAddresses() {
        Address address1 = new Address("Poland", "Katowice", "40-400", "Paderewskiego");
        Address address2 = new Address("Poland", "Warszawa", "80-100", "Warszawska");
        Address address3 = new Address("Poland", "Sopot", "50-800", "Korfantego");
        Address address4 = new Address("Poland", "Warta", "48-450", "Sikorskiego");
        Address address5 = new Address("Poland", "Tymbark", "20-700", "Kosmiczna");
        Address address6 = new Address("Poland", "Lublin", "20-770", "3 Maja");
        Address address7 = new Address("Poland", "Szczecin", "20-930", "1 Maja");
        Address address8 = new Address("Poland", "Częstochowa", "10-930", "Wolności");

        List<Address> list = Stream.of(address1, address2, address3, address4, address5, address6,
                address7, address8).collect(Collectors.toList());

        for (Address entry : list) {
            addressDao.save(entry);
        }
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

        List<FieldOfStudy> list = Stream.of(fieldOfStudy1, fieldOfStudy2, fieldOfStudy3,
                        fieldOfStudy4, fieldOfStudy5, fieldOfStudy6, fieldOfStudy7, fieldOfStudy8)
                .collect(Collectors.toList());

        for (FieldOfStudy entry : list) {
            fieldOfStudyDao.save(entry);
        }
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


        List<Person> list = Stream.of(person1, person2, person3, person4, person5, person6,
                person7, person8).collect(Collectors.toList());

        for (Person entry : list) {
            personDao.save(entry);
        }
    }

    //    TODO: fill students
    private void loadStudents() {

    }

    //    TODO: fill subjects
    private void loadSubjects() {

    }

    //    TODO: fill teachers
    private void loadTeachers() {

    }

    //    TODO: fill tests
    private void loadTests() {

    }
}
