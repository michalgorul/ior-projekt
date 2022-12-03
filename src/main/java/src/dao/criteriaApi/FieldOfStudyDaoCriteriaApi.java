package src.dao.criteriaApi;

import org.hibernate.Session;
import src.HibernateSession;
import src.dto.AddressDto;
import src.dto.FieldOfStudyDto;
import src.model.Address;
import src.model.FieldOfStudy;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class FieldOfStudyDaoCriteriaApi {

    public List<FieldOfStudyDto> getByTypeCriteria(String type) {
        Session session = HibernateSession.INSTANCE.getSessionFactory().openSession();

        // Create query
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<FieldOfStudyDto> query = cb.createQuery(FieldOfStudyDto.class);
        // Define FROM clause
        Root<FieldOfStudy> root = query.from(FieldOfStudy.class);
        // Define WHERE clause
        query.where(cb.equal(root.get("type"), type));
        // Define AddressDto projection
        query.select(cb.construct(FieldOfStudyDto.class,
                root.get("id"),
                root.get("name"),
                root.get("type")));

        // Execute query
        return session.createQuery(query).getResultList();
    }


}