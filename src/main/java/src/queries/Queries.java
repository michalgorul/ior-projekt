package src.queries;

import src.dao.criteriaApi.*;
import src.dao.hibernate.*;
import src.dto.*;

import javax.persistence.Tuple;
import java.util.List;

public class Queries {
    private final AddressDaoHib addressDaoHib;
    private final AddressDaoCriteriaApi addressDaoCriteriaApi;
    private final FieldOfStudyDaoHib fieldOfStudyDaoHib;
    private final FieldOfStudyDaoCriteriaApi fieldOfStudyDaoCriteriaApi;
    private final SubjectDaoHib subjectDaoHib;
    private final SubjectDaoCriteriaApi subjectDaoCriteriaApi;
    private final TestDaoHib testDaoHib;
    private final TestDaoCriteriaApi testDaoCriteriaApi;
    private final StudentDaoHib studentDaoHib;
    private final StudentDaoCriteriaApi studentDaoCriteriaApi;

    public Queries() {
        this.addressDaoHib = new AddressDaoHib();
        this.addressDaoCriteriaApi = new AddressDaoCriteriaApi();
        this.fieldOfStudyDaoHib = new FieldOfStudyDaoHib();
        this.fieldOfStudyDaoCriteriaApi = new FieldOfStudyDaoCriteriaApi();
        this.subjectDaoHib = new SubjectDaoHib();
        this.subjectDaoCriteriaApi = new SubjectDaoCriteriaApi();
        this.testDaoHib = new TestDaoHib();
        this.testDaoCriteriaApi = new TestDaoCriteriaApi();
        this.studentDaoHib = new StudentDaoHib();
        this.studentDaoCriteriaApi = new StudentDaoCriteriaApi();
    }

    public void conditionProjection() {
//        getAddressById();
//        getFieldOfStudyByType();
//        getSubjectByName();
    }

    public void joinOperation() {
//        getTestByStudentId();
//        getStudentWithSubjectsById();
    }

    private void getAddressById() {
        AddressDto byIdJpql = addressDaoHib.getByIdHib(1);
        System.out.println("\nAddress by ID=1 with JPQL:");
        System.out.println("\t" + byIdJpql.toString());

        AddressDto byIdCriteria = addressDaoCriteriaApi.getByIdCriteria(1);
        System.out.println("\nAddress by ID=1 with Criteria Api:");
        System.out.println("\t" + byIdCriteria.toString());
    }

    private void getFieldOfStudyByType() {
        List<FieldOfStudyDto> byTypeHib = fieldOfStudyDaoHib.getByTypeHib("SSM");
        System.out.println("\nFields Of Study by type=SSM with JPQL:");
        byTypeHib.forEach(f -> System.out.println("\t" + f.toString()));

        List<FieldOfStudyDto> byTypeCriteria = fieldOfStudyDaoCriteriaApi.getByTypeCriteria("SSM");
        System.out.println("\nFields Of Study by type=SSM with Criteria Api:");
        byTypeCriteria.forEach(f -> System.out.println("\t" + f.toString()));
    }

    private void getSubjectByName() {
        SubjectDto byNameHib = subjectDaoHib.getByNameHib("Filozofia");
        System.out.println("\nSubject by name=Filozofia with JPQL:");
        System.out.println("\t" + byNameHib.toString());

        SubjectDto byNameCriteria = subjectDaoCriteriaApi.getByNameCriteria("Filozofia");
        System.out.println("\nSubject by name=Filozofia with Criteria Api:");
        System.out.println("\t" + byNameCriteria.toString());

    }

    private void getTestByStudentId() {
        List<Tuple> daoHibByStudentId = testDaoHib.getByStudentIdHib(1);
        System.out.println("\nTests by Student ID=1 with JPQL:");
        daoHibByStudentId.forEach(TestDto::printTestTuple);

        List<Tuple> daoCriteriaApiByStudentId = testDaoCriteriaApi.getByStudentIdCriteria(1);
        System.out.println("\nTests by Student ID=1 with Criteria Api:");
        daoCriteriaApiByStudentId.forEach(TestDto::printTestTuple);
    }

    private void getStudentWithSubjectsById() {
        List<String> byStudentIdHib = studentDaoHib.getSubjectsByStudentIdHib(1);
        StudentDto byIdHib = studentDaoHib.getByIdHib(1);
        System.out.println("\nStudent's Subjects by student ID=1 with Criteria Api:");
        System.out.println(byIdHib);
        byStudentIdHib.forEach(s -> System.out.println("\t- " + s));

        List<String> byStudentIdCriteria = studentDaoCriteriaApi.getSubjectsByStudentIdCriteria(1);
        StudentDto byIdCriteria = studentDaoCriteriaApi.getById(1);
        System.out.println("\nStudent's Subjects by student ID=1 with Criteria Api:");
        System.out.println(byIdCriteria);
        byStudentIdCriteria.forEach(s -> System.out.println("\t- " + s));

    }
}
