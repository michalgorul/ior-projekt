package src.dao.criteriaApi;

import org.hibernate.Session;
import src.HibernateSession;
import src.dto.AddressDto;
import src.model.Address;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class AddressDaoCriteriaApi {
    public AddressDto getByIdCriteria(int id) {
        Session session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        // Create query
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<AddressDto> query = cb.createQuery(AddressDto.class);
        // Define FROM clause
        Root<Address> root = query.from(Address.class);
        // Define WHERE clause
        query.where(cb.equal(root.get("id"), id));
        // Define AddressDto projection
        query.select(cb.construct(AddressDto.class,
                root.get("id"),
                root.get("country"),
                root.get("city"),
                root.get("postalCode"),
                root.get("street")));

        // Execute query
        return session.createQuery(query).getSingleResult();
    }

}