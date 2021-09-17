package repository;

import model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

public class UserRepository {

    public static User findUser(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query query = session.createQuery("SELECT u FROM User u inner join u.cards c " +
                " WHERE c.user.id = :id");

        query.setParameter("id", id);

        User user = (User) query.getSingleResult();

        session.close();

        return user;
    }

}
