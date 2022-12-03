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
        System.out.println("\nZADANIE 1 - warunek + projekcja\n\n");
        getAddressById();
        getFieldOfStudyByType();
        getSubjectByName();
    }

    public void joinOperation() {
        System.out.println("\n\nZADANIE 2 - zlaczenie + operacja na kolekcjach\n\n");

        getTestByStudentId();
        getStudentWithSubjectsById();
    }

    public void aggregation() {
        System.out.println("\n\nZADANIE 3 - funkcja agregująca z ewentualną frazą HAVING\n\n");

        getTestGradeAvg();
        getStudentTestGradeAvgHavingGe();
    }

    private void getAddressById() {
        AddressDto byIdJpql = addressDaoHib.getByIdHib(1);
        System.out.println("\nAddress by ID=1 with JPQL:");
        System.out.println("\t" + byIdJpql.toString() + "\n\n=====\n");

        AddressDto byIdCriteria = addressDaoCriteriaApi.getByIdCriteria(1);
        System.out.println("\nAddress by ID=1 with Criteria Api:");
        System.out.println("\t" + byIdCriteria.toString());
        System.out.println("\n\n==============================================\n\n");
    }

    private void getFieldOfStudyByType() {
        List<FieldOfStudyDto> byTypeHib = fieldOfStudyDaoHib.getByTypeHib("SSM");
        System.out.println("\nFields Of Study by type=SSM with JPQL:");
        byTypeHib.forEach(f -> System.out.println("\t" + f.toString()));
        System.out.println("\n=====\n");

        List<FieldOfStudyDto> byTypeCriteria = fieldOfStudyDaoCriteriaApi.getByTypeCriteria("SSM");
        System.out.println("\nFields Of Study by type=SSM with Criteria Api:");
        byTypeCriteria.forEach(f -> System.out.println("\t" + f.toString()));
        System.out.println("\n\n==============================================\n\n");
    }

    private void getSubjectByName() {
        SubjectDto byNameHib = subjectDaoHib.getByNameHib("Filozofia");
        System.out.println("\nSubject by name=Filozofia with JPQL:");
        System.out.println("\t" + byNameHib.toString() + "\n\n=====\n");

        SubjectDto byNameCriteria = subjectDaoCriteriaApi.getByNameCriteria("Filozofia");
        System.out.println("\nSubject by name=Filozofia with Criteria Api:");
        System.out.println("\t" + byNameCriteria.toString());
        System.out.println("\n\n==============================================\n\n");
    }

    private void getTestByStudentId() {
        List<Tuple> daoHibByStudentId = testDaoHib.getByStudentIdHib(1);
        System.out.println("\nTests by Student ID=1 with JPQL:");
        daoHibByStudentId.forEach(TestDto::printTestTuple);
        System.out.println("\n=====\n");

        List<Tuple> daoCriteriaApiByStudentId = testDaoCriteriaApi.getByStudentIdCriteria(1);
        System.out.println("\nTests by Student ID=1 with Criteria Api:");
        daoCriteriaApiByStudentId.forEach(TestDto::printTestTuple);
        System.out.println("\n\n==============================================\n\n");
    }

    private void getStudentWithSubjectsById() {
        List<String> byStudentIdHib = studentDaoHib.getSubjectsByStudentIdHib(1);
        StudentDto byIdHib = studentDaoHib.getByIdHib(1);
        System.out.println("\nStudent's Subjects by student ID=1 with JPQL:");
        System.out.println(byIdHib);
        byStudentIdHib.forEach(s -> System.out.println("\t- " + s));
        System.out.println("\n=====\n");

        List<String> byStudentIdCriteria = studentDaoCriteriaApi.getSubjectsByStudentIdCriteria(1);
        StudentDto byIdCriteria = studentDaoCriteriaApi.getById(1);
        System.out.println("\nStudent's Subjects by student ID=1 with Criteria Api:");
        System.out.println(byIdCriteria);
        byStudentIdCriteria.forEach(s -> System.out.println("\t- " + s));
        System.out.println("\n\n==============================================\n\n");
    }

    private void getTestGradeAvg() {
        Double avgByStudentIdHib = testDaoHib.getGradeAvgByStudentIdHib(1);
        StudentDto byIdHib = studentDaoHib.getByIdHib(1);
        System.out.println("\nStudent's average grade by student ID=1 with JPQL:");
        System.out.println(byIdHib);
        System.out.println("\t- " + avgByStudentIdHib.toString() + "\n\n=====\n");

        Double avgByStudentIdCriteria = testDaoCriteriaApi.getGradeAvgByStudentIdCriteria(1);
        StudentDto byIdCriteria = studentDaoCriteriaApi.getById(1);
        System.out.println("\nStudent's average grade by student ID=1 with Criteria Api:");
        System.out.println(byIdCriteria);
        System.out.println("\t- " + avgByStudentIdCriteria.toString());
        System.out.println("\n\n==============================================\n\n");
    }

    private void getStudentTestGradeAvgHavingGe() {
        List<Tuple> gradeAvgHavingHib = testDaoHib.getGradeAvgHavingHib();
        System.out.println("\nStudent's average grade by student ID=1 Having more than 1 test with JPQL:");
        gradeAvgHavingHib.forEach(TestDto::printTestTupleWithAvg);
        System.out.println("\n=====\n");

        List<Tuple> gradeAvgHavingCriteria = testDaoCriteriaApi.getGradeAvgHavingCriteria();
        System.out.println("\nStudent's average grade by student ID=1 Having more than 1 test with Criteria Api:");
        gradeAvgHavingCriteria.forEach(TestDto::printTestTupleWithAvg);
    }
}
