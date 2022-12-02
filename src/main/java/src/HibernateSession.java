package src;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public enum HibernateSession {

    INSTANCE;

    public SessionFactory getSessionFactory() {
        return new Configuration().configure().buildSessionFactory();
//        StandardServiceRegistry standardServiceRegistry =
//                new StandardServiceRegistryBuilder().configure().build();
//        MetadataSources metadataSources = new MetadataSources(standardServiceRegistry);
//        Metadata metadata = metadataSources.getMetadataBuilder().build();
//        return metadata.getSessionFactoryBuilder().build();
    }

    public static void truncate(){
        Session session = INSTANCE.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        try {
            session.createQuery("DELETE from AddressDto").executeUpdate();
            session.createQuery("DELETE from FieldOfStudyDto").executeUpdate();
            session.createQuery("DELETE from PersonDto").executeUpdate();
            session.createQuery("DELETE from StudentDto").executeUpdate();
            session.createQuery("DELETE from SubjectDto").executeUpdate();
            session.createQuery("DELETE from TeacherDto").executeUpdate();
            session.createQuery("DELETE from TestDto").executeUpdate();
            transaction.commit();
        }
        catch (Exception exception) {
            transaction.rollback();
            exception.printStackTrace();
        }
        session.close();
    }
}