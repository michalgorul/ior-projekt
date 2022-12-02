package src.dao.criteriaApi;

import org.hibernate.Session;
import src.HibernateSession;
import src.dto.AddressDto;
import src.model.Address;

import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class AddressDaoCriteriaApi {
    public AddressDto getByIdCriteria(int id) {
        Session session = HibernateSession.INSTANCE.getSessionFactory().openSession();
        // Create query
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<AddressDto> cq = cb.createQuery(AddressDto.class);
        // Define FROM clause
        Root<Address> root = cq.from(Address.class);
        // Define WHERE clause
//        cq.where(cb.equal(root.get(Book_.id), paramTitle));
//        // Define AddressDto projection
//        cq.select(cb.construct(AddressDto.class, Address_.id));

        // Execute query
        return null;
    }

}