package src;

import org.hibernate.SessionFactory;
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
}
