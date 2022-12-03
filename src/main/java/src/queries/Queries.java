package src.queries;

import src.dao.criteriaApi.AddressDaoCriteriaApi;
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
    private final SubjectDaoHib subjectDaoHib;

    public Queries() {
        this.addressDaoHib = new AddressDaoHib();
        this.fieldOfStudyDaoHib = new FieldOfStudyDaoHib();
        this.subjectDaoHib = new SubjectDaoHib();
        this.addressDaoCriteriaApi = new AddressDaoCriteriaApi();
    }

    public void conditionProjection(){
        AddressDto byIdJpql = addressDaoHib.getByIdJpql(1);
        System.out.println("\nAddress by ID=1 with JPQL:");
        System.out.println("\t" + byIdJpql.toString());

        AddressDto byIdCriteria = addressDaoCriteriaApi.getByIdCriteria(1);
        System.out.println("\nAddress by ID=1 with Criteria Api:");
        System.out.println("\t" + byIdCriteria.toString());

//        List<FieldOfStudyDto> fieldOfStudies = fieldOfStudyDaoHib.getByType("SSM");
//        System.out.println("\nFields Of Study by type=SSM with JPQL:");
//        fieldOfStudies.forEach(f -> System.out.println("\t" + f.toString()));
//
//        List<SubjectDto> subjectDtos = subjectDaoHib.getByName("Filozofia");
//        System.out.println("\nSubjects by name=Filozofia with JPQL:");
//        subjectDtos.forEach(s -> System.out.println("\t" + s.toString()));

    }
}
