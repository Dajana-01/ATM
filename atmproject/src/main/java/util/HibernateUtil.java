package util;

import model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {

                // add classes used to map database tables
                Configuration configuration = new Configuration();
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Account.class);
                configuration.addAnnotatedClass(Bank.class);
                configuration.addAnnotatedClass(Atm.class);
                configuration.addAnnotatedClass(Transaction.class);
                configuration.addAnnotatedClass(Card.class);

                ServiceRegistry serviceRegistry = new
                        StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory =
                        configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
