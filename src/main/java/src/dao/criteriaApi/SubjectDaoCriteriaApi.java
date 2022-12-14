package src.dao.criteriaApi;

import org.hibernate.Session;
import src.HibernateSession;
import src.dto.SubjectDto;
import src.model.Subject;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class SubjectDaoCriteriaApi {
    public SubjectDto getByNameCriteria(String name) {
        Session session = HibernateSession.INSTANCE.getSessionFactory().openSession();

        // Create query
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<SubjectDto> query = cb.createQuery(SubjectDto.class);
        // Define FROM clause
        Root<Subject> root = query.from(Subject.class);
        // Define WHERE clause
        query.where(cb.equal(root.get("name"), name));
        // Define SubjectDto projection
        query.select(cb.construct(SubjectDto.class,
                root.get("id"),
                root.get("name")));

        // Execute query
        return session.createQuery(query).getSingleResult();
    }
}