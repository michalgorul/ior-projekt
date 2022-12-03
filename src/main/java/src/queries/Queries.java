package src.queries;

import src.dao.criteriaApi.AddressDaoCriteriaApi;
import src.dao.criteriaApi.FieldOfStudyDaoCriteriaApi;
import src.dao.criteriaApi.SubjectDaoCriteriaApi;
import src.dao.hibernate.AddressDaoHib;
import src.dao.hibernate.FieldOfStudyDaoHib;
import src.dao.hibernate.SubjectDaoHib;
import src.dto.AddressDto;
import src.dto.FieldOfStudyDto;
import src.dto.SubjectDto;

import java.util.List;

public class Queries {
    private final AddressDaoHib addressDaoHib;
    private final AddressDaoCriteriaApi addressDaoCriteriaApi;
    private final FieldOfStudyDaoHib fieldOfStudyDaoHib;
    private final FieldOfStudyDaoCriteriaApi fieldOfStudyDaoCriteriaApi;
    private final SubjectDaoHib subjectDaoHib;
    private final SubjectDaoCriteriaApi subjectDaoCriteriaApi;

    public Queries() {
        this.addressDaoHib = new AddressDaoHib();
        this.addressDaoCriteriaApi = new AddressDaoCriteriaApi();
        this.fieldOfStudyDaoHib = new FieldOfStudyDaoHib();
        this.fieldOfStudyDaoCriteriaApi = new FieldOfStudyDaoCriteriaApi();
        this.subjectDaoHib = new SubjectDaoHib();
        this.subjectDaoCriteriaApi = new SubjectDaoCriteriaApi();
    }

    public void conditionProjection(){
//        getAddressById();
//        getFieldOfStudyByType();
        getSubjectByName();
    }

    private void getAddressById(){
        AddressDto byIdJpql = addressDaoHib.getByIdJpql(1);
        System.out.println("\nAddress by ID=1 with JPQL:");
        System.out.println("\t" + byIdJpql.toString());

        AddressDto byIdCriteria = addressDaoCriteriaApi.getByIdCriteria(1);
        System.out.println("\nAddress by ID=1 with Criteria Api:");
        System.out.println("\t" + byIdCriteria.toString());
    }

    private void getFieldOfStudyByType(){
        List<FieldOfStudyDto> byTypeHib = fieldOfStudyDaoHib.getByTypeHib("SSM");
        System.out.println("\nFields Of Study by type=SSM with JPQL:");
        byTypeHib.forEach(f -> System.out.println("\t" + f.toString()));

        List<FieldOfStudyDto> byTypeCriteria = fieldOfStudyDaoCriteriaApi.getByTypeCriteria("SSM");
        System.out.println("\nFields Of Study by type=SSM with Criteria Api:");
        byTypeCriteria.forEach(f -> System.out.println("\t" + f.toString()));
    }

    private void getSubjectByName(){
        SubjectDto byNameHib = subjectDaoHib.getByNameHib("Filozofia");
        System.out.println("\nSubject by name=Filozofia with JPQL:");
        System.out.println("\t" + byNameHib.toString());

        SubjectDto byNameCriteria = subjectDaoCriteriaApi.getByNameCriteria("Filozofia");
        System.out.println("\nSubject by name=Filozofia with Criteria Api:");
        System.out.println("\t" + byNameCriteria.toString());

    }
}
